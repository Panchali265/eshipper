package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.UserNotificationSettings;
import com.eshipper.repository.UserNotificationSettingsRepository;
import com.eshipper.service.UserNotificationSettingsService;
import com.eshipper.service.dto.UserNotificationSettingsDTO;
import com.eshipper.service.mapper.UserNotificationSettingsMapper;
import com.eshipper.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.eshipper.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link UserNotificationSettingsResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class UserNotificationSettingsResourceIT {

    private static final Boolean DEFAULT_WEB = false;
    private static final Boolean UPDATED_WEB = true;

    private static final Boolean DEFAULT_EMAIL = false;
    private static final Boolean UPDATED_EMAIL = true;

    private static final String DEFAULT_CONFIGURABLE_MAP = "AAAAAAAAAA";
    private static final String UPDATED_CONFIGURABLE_MAP = "BBBBBBBBBB";

    @Autowired
    private UserNotificationSettingsRepository userNotificationSettingsRepository;

    @Autowired
    private UserNotificationSettingsMapper userNotificationSettingsMapper;

    @Autowired
    private UserNotificationSettingsService userNotificationSettingsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restUserNotificationSettingsMockMvc;

    private UserNotificationSettings userNotificationSettings;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UserNotificationSettingsResource userNotificationSettingsResource = new UserNotificationSettingsResource(userNotificationSettingsService);
        this.restUserNotificationSettingsMockMvc = MockMvcBuilders.standaloneSetup(userNotificationSettingsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserNotificationSettings createEntity(EntityManager em) {
        UserNotificationSettings userNotificationSettings = new UserNotificationSettings()
            .web(DEFAULT_WEB)
            .email(DEFAULT_EMAIL)
            .configurableMap(DEFAULT_CONFIGURABLE_MAP);
        return userNotificationSettings;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserNotificationSettings createUpdatedEntity(EntityManager em) {
        UserNotificationSettings userNotificationSettings = new UserNotificationSettings()
            .web(UPDATED_WEB)
            .email(UPDATED_EMAIL)
            .configurableMap(UPDATED_CONFIGURABLE_MAP);
        return userNotificationSettings;
    }

    @BeforeEach
    public void initTest() {
        userNotificationSettings = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserNotificationSettings() throws Exception {
        int databaseSizeBeforeCreate = userNotificationSettingsRepository.findAll().size();

        // Create the UserNotificationSettings
        UserNotificationSettingsDTO userNotificationSettingsDTO = userNotificationSettingsMapper.toDto(userNotificationSettings);
        restUserNotificationSettingsMockMvc.perform(post("/api/user-notification-settings")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userNotificationSettingsDTO)))
            .andExpect(status().isCreated());

        // Validate the UserNotificationSettings in the database
        List<UserNotificationSettings> userNotificationSettingsList = userNotificationSettingsRepository.findAll();
        assertThat(userNotificationSettingsList).hasSize(databaseSizeBeforeCreate + 1);
        UserNotificationSettings testUserNotificationSettings = userNotificationSettingsList.get(userNotificationSettingsList.size() - 1);
        assertThat(testUserNotificationSettings.isWeb()).isEqualTo(DEFAULT_WEB);
        assertThat(testUserNotificationSettings.isEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testUserNotificationSettings.getConfigurableMap()).isEqualTo(DEFAULT_CONFIGURABLE_MAP);
    }

    @Test
    @Transactional
    public void createUserNotificationSettingsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userNotificationSettingsRepository.findAll().size();

        // Create the UserNotificationSettings with an existing ID
        userNotificationSettings.setId(1L);
        UserNotificationSettingsDTO userNotificationSettingsDTO = userNotificationSettingsMapper.toDto(userNotificationSettings);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserNotificationSettingsMockMvc.perform(post("/api/user-notification-settings")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userNotificationSettingsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserNotificationSettings in the database
        List<UserNotificationSettings> userNotificationSettingsList = userNotificationSettingsRepository.findAll();
        assertThat(userNotificationSettingsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllUserNotificationSettings() throws Exception {
        // Initialize the database
        userNotificationSettingsRepository.saveAndFlush(userNotificationSettings);

        // Get all the userNotificationSettingsList
        restUserNotificationSettingsMockMvc.perform(get("/api/user-notification-settings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userNotificationSettings.getId().intValue())))
            .andExpect(jsonPath("$.[*].web").value(hasItem(DEFAULT_WEB.booleanValue())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.booleanValue())))
            .andExpect(jsonPath("$.[*].configurableMap").value(hasItem(DEFAULT_CONFIGURABLE_MAP)));
    }
    
    @Test
    @Transactional
    public void getUserNotificationSettings() throws Exception {
        // Initialize the database
        userNotificationSettingsRepository.saveAndFlush(userNotificationSettings);

        // Get the userNotificationSettings
        restUserNotificationSettingsMockMvc.perform(get("/api/user-notification-settings/{id}", userNotificationSettings.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userNotificationSettings.getId().intValue()))
            .andExpect(jsonPath("$.web").value(DEFAULT_WEB.booleanValue()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.booleanValue()))
            .andExpect(jsonPath("$.configurableMap").value(DEFAULT_CONFIGURABLE_MAP));
    }

    @Test
    @Transactional
    public void getNonExistingUserNotificationSettings() throws Exception {
        // Get the userNotificationSettings
        restUserNotificationSettingsMockMvc.perform(get("/api/user-notification-settings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserNotificationSettings() throws Exception {
        // Initialize the database
        userNotificationSettingsRepository.saveAndFlush(userNotificationSettings);

        int databaseSizeBeforeUpdate = userNotificationSettingsRepository.findAll().size();

        // Update the userNotificationSettings
        UserNotificationSettings updatedUserNotificationSettings = userNotificationSettingsRepository.findById(userNotificationSettings.getId()).get();
        // Disconnect from session so that the updates on updatedUserNotificationSettings are not directly saved in db
        em.detach(updatedUserNotificationSettings);
        updatedUserNotificationSettings
            .web(UPDATED_WEB)
            .email(UPDATED_EMAIL)
            .configurableMap(UPDATED_CONFIGURABLE_MAP);
        UserNotificationSettingsDTO userNotificationSettingsDTO = userNotificationSettingsMapper.toDto(updatedUserNotificationSettings);

        restUserNotificationSettingsMockMvc.perform(put("/api/user-notification-settings")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userNotificationSettingsDTO)))
            .andExpect(status().isOk());

        // Validate the UserNotificationSettings in the database
        List<UserNotificationSettings> userNotificationSettingsList = userNotificationSettingsRepository.findAll();
        assertThat(userNotificationSettingsList).hasSize(databaseSizeBeforeUpdate);
        UserNotificationSettings testUserNotificationSettings = userNotificationSettingsList.get(userNotificationSettingsList.size() - 1);
        assertThat(testUserNotificationSettings.isWeb()).isEqualTo(UPDATED_WEB);
        assertThat(testUserNotificationSettings.isEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testUserNotificationSettings.getConfigurableMap()).isEqualTo(UPDATED_CONFIGURABLE_MAP);
    }

    @Test
    @Transactional
    public void updateNonExistingUserNotificationSettings() throws Exception {
        int databaseSizeBeforeUpdate = userNotificationSettingsRepository.findAll().size();

        // Create the UserNotificationSettings
        UserNotificationSettingsDTO userNotificationSettingsDTO = userNotificationSettingsMapper.toDto(userNotificationSettings);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserNotificationSettingsMockMvc.perform(put("/api/user-notification-settings")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userNotificationSettingsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserNotificationSettings in the database
        List<UserNotificationSettings> userNotificationSettingsList = userNotificationSettingsRepository.findAll();
        assertThat(userNotificationSettingsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUserNotificationSettings() throws Exception {
        // Initialize the database
        userNotificationSettingsRepository.saveAndFlush(userNotificationSettings);

        int databaseSizeBeforeDelete = userNotificationSettingsRepository.findAll().size();

        // Delete the userNotificationSettings
        restUserNotificationSettingsMockMvc.perform(delete("/api/user-notification-settings/{id}", userNotificationSettings.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserNotificationSettings> userNotificationSettingsList = userNotificationSettingsRepository.findAll();
        assertThat(userNotificationSettingsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

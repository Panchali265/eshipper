package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.NotificationType;
import com.eshipper.repository.NotificationTypeRepository;
import com.eshipper.service.NotificationTypeService;
import com.eshipper.service.dto.NotificationTypeDTO;
import com.eshipper.service.mapper.NotificationTypeMapper;
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
 * Integration tests for the {@link NotificationTypeResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class NotificationTypeResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ENUM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ENUM_NAME = "BBBBBBBBBB";

    @Autowired
    private NotificationTypeRepository notificationTypeRepository;

    @Autowired
    private NotificationTypeMapper notificationTypeMapper;

    @Autowired
    private NotificationTypeService notificationTypeService;

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

    private MockMvc restNotificationTypeMockMvc;

    private NotificationType notificationType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NotificationTypeResource notificationTypeResource = new NotificationTypeResource(notificationTypeService);
        this.restNotificationTypeMockMvc = MockMvcBuilders.standaloneSetup(notificationTypeResource)
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
    public static NotificationType createEntity(EntityManager em) {
        NotificationType notificationType = new NotificationType()
            .name(DEFAULT_NAME)
            .enumName(DEFAULT_ENUM_NAME);
        return notificationType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NotificationType createUpdatedEntity(EntityManager em) {
        NotificationType notificationType = new NotificationType()
            .name(UPDATED_NAME)
            .enumName(UPDATED_ENUM_NAME);
        return notificationType;
    }

    @BeforeEach
    public void initTest() {
        notificationType = createEntity(em);
    }

    @Test
    @Transactional
    public void createNotificationType() throws Exception {
        int databaseSizeBeforeCreate = notificationTypeRepository.findAll().size();

        // Create the NotificationType
        NotificationTypeDTO notificationTypeDTO = notificationTypeMapper.toDto(notificationType);
        restNotificationTypeMockMvc.perform(post("/api/notification-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notificationTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the NotificationType in the database
        List<NotificationType> notificationTypeList = notificationTypeRepository.findAll();
        assertThat(notificationTypeList).hasSize(databaseSizeBeforeCreate + 1);
        NotificationType testNotificationType = notificationTypeList.get(notificationTypeList.size() - 1);
        assertThat(testNotificationType.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testNotificationType.getEnumName()).isEqualTo(DEFAULT_ENUM_NAME);
    }

    @Test
    @Transactional
    public void createNotificationTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = notificationTypeRepository.findAll().size();

        // Create the NotificationType with an existing ID
        notificationType.setId(1L);
        NotificationTypeDTO notificationTypeDTO = notificationTypeMapper.toDto(notificationType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNotificationTypeMockMvc.perform(post("/api/notification-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notificationTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NotificationType in the database
        List<NotificationType> notificationTypeList = notificationTypeRepository.findAll();
        assertThat(notificationTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllNotificationTypes() throws Exception {
        // Initialize the database
        notificationTypeRepository.saveAndFlush(notificationType);

        // Get all the notificationTypeList
        restNotificationTypeMockMvc.perform(get("/api/notification-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(notificationType.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].enumName").value(hasItem(DEFAULT_ENUM_NAME)));
    }
    
    @Test
    @Transactional
    public void getNotificationType() throws Exception {
        // Initialize the database
        notificationTypeRepository.saveAndFlush(notificationType);

        // Get the notificationType
        restNotificationTypeMockMvc.perform(get("/api/notification-types/{id}", notificationType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(notificationType.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.enumName").value(DEFAULT_ENUM_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingNotificationType() throws Exception {
        // Get the notificationType
        restNotificationTypeMockMvc.perform(get("/api/notification-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNotificationType() throws Exception {
        // Initialize the database
        notificationTypeRepository.saveAndFlush(notificationType);

        int databaseSizeBeforeUpdate = notificationTypeRepository.findAll().size();

        // Update the notificationType
        NotificationType updatedNotificationType = notificationTypeRepository.findById(notificationType.getId()).get();
        // Disconnect from session so that the updates on updatedNotificationType are not directly saved in db
        em.detach(updatedNotificationType);
        updatedNotificationType
            .name(UPDATED_NAME)
            .enumName(UPDATED_ENUM_NAME);
        NotificationTypeDTO notificationTypeDTO = notificationTypeMapper.toDto(updatedNotificationType);

        restNotificationTypeMockMvc.perform(put("/api/notification-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notificationTypeDTO)))
            .andExpect(status().isOk());

        // Validate the NotificationType in the database
        List<NotificationType> notificationTypeList = notificationTypeRepository.findAll();
        assertThat(notificationTypeList).hasSize(databaseSizeBeforeUpdate);
        NotificationType testNotificationType = notificationTypeList.get(notificationTypeList.size() - 1);
        assertThat(testNotificationType.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testNotificationType.getEnumName()).isEqualTo(UPDATED_ENUM_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingNotificationType() throws Exception {
        int databaseSizeBeforeUpdate = notificationTypeRepository.findAll().size();

        // Create the NotificationType
        NotificationTypeDTO notificationTypeDTO = notificationTypeMapper.toDto(notificationType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNotificationTypeMockMvc.perform(put("/api/notification-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notificationTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NotificationType in the database
        List<NotificationType> notificationTypeList = notificationTypeRepository.findAll();
        assertThat(notificationTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNotificationType() throws Exception {
        // Initialize the database
        notificationTypeRepository.saveAndFlush(notificationType);

        int databaseSizeBeforeDelete = notificationTypeRepository.findAll().size();

        // Delete the notificationType
        restNotificationTypeMockMvc.perform(delete("/api/notification-types/{id}", notificationType.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NotificationType> notificationTypeList = notificationTypeRepository.findAll();
        assertThat(notificationTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

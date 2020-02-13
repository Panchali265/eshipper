package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.NotificationMethod;
import com.eshipper.repository.NotificationMethodRepository;
import com.eshipper.service.NotificationMethodService;
import com.eshipper.service.dto.NotificationMethodDTO;
import com.eshipper.service.mapper.NotificationMethodMapper;
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
 * Integration tests for the {@link NotificationMethodResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class NotificationMethodResourceIT {

    private static final String DEFAULT_METHOD_NAME = "AAAAAAAAAA";
    private static final String UPDATED_METHOD_NAME = "BBBBBBBBBB";

    @Autowired
    private NotificationMethodRepository notificationMethodRepository;

    @Autowired
    private NotificationMethodMapper notificationMethodMapper;

    @Autowired
    private NotificationMethodService notificationMethodService;

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

    private MockMvc restNotificationMethodMockMvc;

    private NotificationMethod notificationMethod;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NotificationMethodResource notificationMethodResource = new NotificationMethodResource(notificationMethodService);
        this.restNotificationMethodMockMvc = MockMvcBuilders.standaloneSetup(notificationMethodResource)
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
    public static NotificationMethod createEntity(EntityManager em) {
        NotificationMethod notificationMethod = new NotificationMethod()
            .methodName(DEFAULT_METHOD_NAME);
        return notificationMethod;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NotificationMethod createUpdatedEntity(EntityManager em) {
        NotificationMethod notificationMethod = new NotificationMethod()
            .methodName(UPDATED_METHOD_NAME);
        return notificationMethod;
    }

    @BeforeEach
    public void initTest() {
        notificationMethod = createEntity(em);
    }

    @Test
    @Transactional
    public void createNotificationMethod() throws Exception {
        int databaseSizeBeforeCreate = notificationMethodRepository.findAll().size();

        // Create the NotificationMethod
        NotificationMethodDTO notificationMethodDTO = notificationMethodMapper.toDto(notificationMethod);
        restNotificationMethodMockMvc.perform(post("/api/notification-methods")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notificationMethodDTO)))
            .andExpect(status().isCreated());

        // Validate the NotificationMethod in the database
        List<NotificationMethod> notificationMethodList = notificationMethodRepository.findAll();
        assertThat(notificationMethodList).hasSize(databaseSizeBeforeCreate + 1);
        NotificationMethod testNotificationMethod = notificationMethodList.get(notificationMethodList.size() - 1);
        assertThat(testNotificationMethod.getMethodName()).isEqualTo(DEFAULT_METHOD_NAME);
    }

    @Test
    @Transactional
    public void createNotificationMethodWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = notificationMethodRepository.findAll().size();

        // Create the NotificationMethod with an existing ID
        notificationMethod.setId(1L);
        NotificationMethodDTO notificationMethodDTO = notificationMethodMapper.toDto(notificationMethod);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNotificationMethodMockMvc.perform(post("/api/notification-methods")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notificationMethodDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NotificationMethod in the database
        List<NotificationMethod> notificationMethodList = notificationMethodRepository.findAll();
        assertThat(notificationMethodList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllNotificationMethods() throws Exception {
        // Initialize the database
        notificationMethodRepository.saveAndFlush(notificationMethod);

        // Get all the notificationMethodList
        restNotificationMethodMockMvc.perform(get("/api/notification-methods?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(notificationMethod.getId().intValue())))
            .andExpect(jsonPath("$.[*].methodName").value(hasItem(DEFAULT_METHOD_NAME)));
    }
    
    @Test
    @Transactional
    public void getNotificationMethod() throws Exception {
        // Initialize the database
        notificationMethodRepository.saveAndFlush(notificationMethod);

        // Get the notificationMethod
        restNotificationMethodMockMvc.perform(get("/api/notification-methods/{id}", notificationMethod.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(notificationMethod.getId().intValue()))
            .andExpect(jsonPath("$.methodName").value(DEFAULT_METHOD_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingNotificationMethod() throws Exception {
        // Get the notificationMethod
        restNotificationMethodMockMvc.perform(get("/api/notification-methods/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNotificationMethod() throws Exception {
        // Initialize the database
        notificationMethodRepository.saveAndFlush(notificationMethod);

        int databaseSizeBeforeUpdate = notificationMethodRepository.findAll().size();

        // Update the notificationMethod
        NotificationMethod updatedNotificationMethod = notificationMethodRepository.findById(notificationMethod.getId()).get();
        // Disconnect from session so that the updates on updatedNotificationMethod are not directly saved in db
        em.detach(updatedNotificationMethod);
        updatedNotificationMethod
            .methodName(UPDATED_METHOD_NAME);
        NotificationMethodDTO notificationMethodDTO = notificationMethodMapper.toDto(updatedNotificationMethod);

        restNotificationMethodMockMvc.perform(put("/api/notification-methods")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notificationMethodDTO)))
            .andExpect(status().isOk());

        // Validate the NotificationMethod in the database
        List<NotificationMethod> notificationMethodList = notificationMethodRepository.findAll();
        assertThat(notificationMethodList).hasSize(databaseSizeBeforeUpdate);
        NotificationMethod testNotificationMethod = notificationMethodList.get(notificationMethodList.size() - 1);
        assertThat(testNotificationMethod.getMethodName()).isEqualTo(UPDATED_METHOD_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingNotificationMethod() throws Exception {
        int databaseSizeBeforeUpdate = notificationMethodRepository.findAll().size();

        // Create the NotificationMethod
        NotificationMethodDTO notificationMethodDTO = notificationMethodMapper.toDto(notificationMethod);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNotificationMethodMockMvc.perform(put("/api/notification-methods")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notificationMethodDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NotificationMethod in the database
        List<NotificationMethod> notificationMethodList = notificationMethodRepository.findAll();
        assertThat(notificationMethodList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNotificationMethod() throws Exception {
        // Initialize the database
        notificationMethodRepository.saveAndFlush(notificationMethod);

        int databaseSizeBeforeDelete = notificationMethodRepository.findAll().size();

        // Delete the notificationMethod
        restNotificationMethodMockMvc.perform(delete("/api/notification-methods/{id}", notificationMethod.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NotificationMethod> notificationMethodList = notificationMethodRepository.findAll();
        assertThat(notificationMethodList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

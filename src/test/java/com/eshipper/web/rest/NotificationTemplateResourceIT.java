package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.NotificationTemplate;
import com.eshipper.repository.NotificationTemplateRepository;
import com.eshipper.service.NotificationTemplateService;
import com.eshipper.service.dto.NotificationTemplateDTO;
import com.eshipper.service.mapper.NotificationTemplateMapper;
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
 * Integration tests for the {@link NotificationTemplateResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class NotificationTemplateResourceIT {

    private static final String DEFAULT_SUBJECT = "AAAAAAAAAA";
    private static final String UPDATED_SUBJECT = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private NotificationTemplateRepository notificationTemplateRepository;

    @Autowired
    private NotificationTemplateMapper notificationTemplateMapper;

    @Autowired
    private NotificationTemplateService notificationTemplateService;

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

    private MockMvc restNotificationTemplateMockMvc;

    private NotificationTemplate notificationTemplate;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NotificationTemplateResource notificationTemplateResource = new NotificationTemplateResource(notificationTemplateService);
        this.restNotificationTemplateMockMvc = MockMvcBuilders.standaloneSetup(notificationTemplateResource)
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
    public static NotificationTemplate createEntity(EntityManager em) {
        NotificationTemplate notificationTemplate = new NotificationTemplate()
            .subject(DEFAULT_SUBJECT)
            .description(DEFAULT_DESCRIPTION);
        return notificationTemplate;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NotificationTemplate createUpdatedEntity(EntityManager em) {
        NotificationTemplate notificationTemplate = new NotificationTemplate()
            .subject(UPDATED_SUBJECT)
            .description(UPDATED_DESCRIPTION);
        return notificationTemplate;
    }

    @BeforeEach
    public void initTest() {
        notificationTemplate = createEntity(em);
    }

    @Test
    @Transactional
    public void createNotificationTemplate() throws Exception {
        int databaseSizeBeforeCreate = notificationTemplateRepository.findAll().size();

        // Create the NotificationTemplate
        NotificationTemplateDTO notificationTemplateDTO = notificationTemplateMapper.toDto(notificationTemplate);
        restNotificationTemplateMockMvc.perform(post("/api/notification-templates")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notificationTemplateDTO)))
            .andExpect(status().isCreated());

        // Validate the NotificationTemplate in the database
        List<NotificationTemplate> notificationTemplateList = notificationTemplateRepository.findAll();
        assertThat(notificationTemplateList).hasSize(databaseSizeBeforeCreate + 1);
        NotificationTemplate testNotificationTemplate = notificationTemplateList.get(notificationTemplateList.size() - 1);
        assertThat(testNotificationTemplate.getSubject()).isEqualTo(DEFAULT_SUBJECT);
        assertThat(testNotificationTemplate.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createNotificationTemplateWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = notificationTemplateRepository.findAll().size();

        // Create the NotificationTemplate with an existing ID
        notificationTemplate.setId(1L);
        NotificationTemplateDTO notificationTemplateDTO = notificationTemplateMapper.toDto(notificationTemplate);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNotificationTemplateMockMvc.perform(post("/api/notification-templates")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notificationTemplateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NotificationTemplate in the database
        List<NotificationTemplate> notificationTemplateList = notificationTemplateRepository.findAll();
        assertThat(notificationTemplateList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllNotificationTemplates() throws Exception {
        // Initialize the database
        notificationTemplateRepository.saveAndFlush(notificationTemplate);

        // Get all the notificationTemplateList
        restNotificationTemplateMockMvc.perform(get("/api/notification-templates?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(notificationTemplate.getId().intValue())))
            .andExpect(jsonPath("$.[*].subject").value(hasItem(DEFAULT_SUBJECT)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getNotificationTemplate() throws Exception {
        // Initialize the database
        notificationTemplateRepository.saveAndFlush(notificationTemplate);

        // Get the notificationTemplate
        restNotificationTemplateMockMvc.perform(get("/api/notification-templates/{id}", notificationTemplate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(notificationTemplate.getId().intValue()))
            .andExpect(jsonPath("$.subject").value(DEFAULT_SUBJECT))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }

    @Test
    @Transactional
    public void getNonExistingNotificationTemplate() throws Exception {
        // Get the notificationTemplate
        restNotificationTemplateMockMvc.perform(get("/api/notification-templates/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNotificationTemplate() throws Exception {
        // Initialize the database
        notificationTemplateRepository.saveAndFlush(notificationTemplate);

        int databaseSizeBeforeUpdate = notificationTemplateRepository.findAll().size();

        // Update the notificationTemplate
        NotificationTemplate updatedNotificationTemplate = notificationTemplateRepository.findById(notificationTemplate.getId()).get();
        // Disconnect from session so that the updates on updatedNotificationTemplate are not directly saved in db
        em.detach(updatedNotificationTemplate);
        updatedNotificationTemplate
            .subject(UPDATED_SUBJECT)
            .description(UPDATED_DESCRIPTION);
        NotificationTemplateDTO notificationTemplateDTO = notificationTemplateMapper.toDto(updatedNotificationTemplate);

        restNotificationTemplateMockMvc.perform(put("/api/notification-templates")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notificationTemplateDTO)))
            .andExpect(status().isOk());

        // Validate the NotificationTemplate in the database
        List<NotificationTemplate> notificationTemplateList = notificationTemplateRepository.findAll();
        assertThat(notificationTemplateList).hasSize(databaseSizeBeforeUpdate);
        NotificationTemplate testNotificationTemplate = notificationTemplateList.get(notificationTemplateList.size() - 1);
        assertThat(testNotificationTemplate.getSubject()).isEqualTo(UPDATED_SUBJECT);
        assertThat(testNotificationTemplate.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingNotificationTemplate() throws Exception {
        int databaseSizeBeforeUpdate = notificationTemplateRepository.findAll().size();

        // Create the NotificationTemplate
        NotificationTemplateDTO notificationTemplateDTO = notificationTemplateMapper.toDto(notificationTemplate);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNotificationTemplateMockMvc.perform(put("/api/notification-templates")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notificationTemplateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NotificationTemplate in the database
        List<NotificationTemplate> notificationTemplateList = notificationTemplateRepository.findAll();
        assertThat(notificationTemplateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNotificationTemplate() throws Exception {
        // Initialize the database
        notificationTemplateRepository.saveAndFlush(notificationTemplate);

        int databaseSizeBeforeDelete = notificationTemplateRepository.findAll().size();

        // Delete the notificationTemplate
        restNotificationTemplateMockMvc.perform(delete("/api/notification-templates/{id}", notificationTemplate.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NotificationTemplate> notificationTemplateList = notificationTemplateRepository.findAll();
        assertThat(notificationTemplateList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

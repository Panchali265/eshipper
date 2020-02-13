package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.EmailNotification;
import com.eshipper.repository.EmailNotificationRepository;
import com.eshipper.service.EmailNotificationService;
import com.eshipper.service.dto.EmailNotificationDTO;
import com.eshipper.service.mapper.EmailNotificationMapper;
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
 * Integration tests for the {@link EmailNotificationResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class EmailNotificationResourceIT {

    private static final String DEFAULT_TO_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_TO_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_MAP = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_MAP = "BBBBBBBBBB";

    @Autowired
    private EmailNotificationRepository emailNotificationRepository;

    @Autowired
    private EmailNotificationMapper emailNotificationMapper;

    @Autowired
    private EmailNotificationService emailNotificationService;

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

    private MockMvc restEmailNotificationMockMvc;

    private EmailNotification emailNotification;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EmailNotificationResource emailNotificationResource = new EmailNotificationResource(emailNotificationService);
        this.restEmailNotificationMockMvc = MockMvcBuilders.standaloneSetup(emailNotificationResource)
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
    public static EmailNotification createEntity(EntityManager em) {
        EmailNotification emailNotification = new EmailNotification()
            .toEmail(DEFAULT_TO_EMAIL)
            .emailMap(DEFAULT_EMAIL_MAP);
        return emailNotification;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EmailNotification createUpdatedEntity(EntityManager em) {
        EmailNotification emailNotification = new EmailNotification()
            .toEmail(UPDATED_TO_EMAIL)
            .emailMap(UPDATED_EMAIL_MAP);
        return emailNotification;
    }

    @BeforeEach
    public void initTest() {
        emailNotification = createEntity(em);
    }

    @Test
    @Transactional
    public void createEmailNotification() throws Exception {
        int databaseSizeBeforeCreate = emailNotificationRepository.findAll().size();

        // Create the EmailNotification
        EmailNotificationDTO emailNotificationDTO = emailNotificationMapper.toDto(emailNotification);
        restEmailNotificationMockMvc.perform(post("/api/email-notifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emailNotificationDTO)))
            .andExpect(status().isCreated());

        // Validate the EmailNotification in the database
        List<EmailNotification> emailNotificationList = emailNotificationRepository.findAll();
        assertThat(emailNotificationList).hasSize(databaseSizeBeforeCreate + 1);
        EmailNotification testEmailNotification = emailNotificationList.get(emailNotificationList.size() - 1);
        assertThat(testEmailNotification.getToEmail()).isEqualTo(DEFAULT_TO_EMAIL);
        assertThat(testEmailNotification.getEmailMap()).isEqualTo(DEFAULT_EMAIL_MAP);
    }

    @Test
    @Transactional
    public void createEmailNotificationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = emailNotificationRepository.findAll().size();

        // Create the EmailNotification with an existing ID
        emailNotification.setId(1L);
        EmailNotificationDTO emailNotificationDTO = emailNotificationMapper.toDto(emailNotification);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmailNotificationMockMvc.perform(post("/api/email-notifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emailNotificationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EmailNotification in the database
        List<EmailNotification> emailNotificationList = emailNotificationRepository.findAll();
        assertThat(emailNotificationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEmailNotifications() throws Exception {
        // Initialize the database
        emailNotificationRepository.saveAndFlush(emailNotification);

        // Get all the emailNotificationList
        restEmailNotificationMockMvc.perform(get("/api/email-notifications?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(emailNotification.getId().intValue())))
            .andExpect(jsonPath("$.[*].toEmail").value(hasItem(DEFAULT_TO_EMAIL)))
            .andExpect(jsonPath("$.[*].emailMap").value(hasItem(DEFAULT_EMAIL_MAP)));
    }
    
    @Test
    @Transactional
    public void getEmailNotification() throws Exception {
        // Initialize the database
        emailNotificationRepository.saveAndFlush(emailNotification);

        // Get the emailNotification
        restEmailNotificationMockMvc.perform(get("/api/email-notifications/{id}", emailNotification.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(emailNotification.getId().intValue()))
            .andExpect(jsonPath("$.toEmail").value(DEFAULT_TO_EMAIL))
            .andExpect(jsonPath("$.emailMap").value(DEFAULT_EMAIL_MAP));
    }

    @Test
    @Transactional
    public void getNonExistingEmailNotification() throws Exception {
        // Get the emailNotification
        restEmailNotificationMockMvc.perform(get("/api/email-notifications/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEmailNotification() throws Exception {
        // Initialize the database
        emailNotificationRepository.saveAndFlush(emailNotification);

        int databaseSizeBeforeUpdate = emailNotificationRepository.findAll().size();

        // Update the emailNotification
        EmailNotification updatedEmailNotification = emailNotificationRepository.findById(emailNotification.getId()).get();
        // Disconnect from session so that the updates on updatedEmailNotification are not directly saved in db
        em.detach(updatedEmailNotification);
        updatedEmailNotification
            .toEmail(UPDATED_TO_EMAIL)
            .emailMap(UPDATED_EMAIL_MAP);
        EmailNotificationDTO emailNotificationDTO = emailNotificationMapper.toDto(updatedEmailNotification);

        restEmailNotificationMockMvc.perform(put("/api/email-notifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emailNotificationDTO)))
            .andExpect(status().isOk());

        // Validate the EmailNotification in the database
        List<EmailNotification> emailNotificationList = emailNotificationRepository.findAll();
        assertThat(emailNotificationList).hasSize(databaseSizeBeforeUpdate);
        EmailNotification testEmailNotification = emailNotificationList.get(emailNotificationList.size() - 1);
        assertThat(testEmailNotification.getToEmail()).isEqualTo(UPDATED_TO_EMAIL);
        assertThat(testEmailNotification.getEmailMap()).isEqualTo(UPDATED_EMAIL_MAP);
    }

    @Test
    @Transactional
    public void updateNonExistingEmailNotification() throws Exception {
        int databaseSizeBeforeUpdate = emailNotificationRepository.findAll().size();

        // Create the EmailNotification
        EmailNotificationDTO emailNotificationDTO = emailNotificationMapper.toDto(emailNotification);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmailNotificationMockMvc.perform(put("/api/email-notifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emailNotificationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EmailNotification in the database
        List<EmailNotification> emailNotificationList = emailNotificationRepository.findAll();
        assertThat(emailNotificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEmailNotification() throws Exception {
        // Initialize the database
        emailNotificationRepository.saveAndFlush(emailNotification);

        int databaseSizeBeforeDelete = emailNotificationRepository.findAll().size();

        // Delete the emailNotification
        restEmailNotificationMockMvc.perform(delete("/api/email-notifications/{id}", emailNotification.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EmailNotification> emailNotificationList = emailNotificationRepository.findAll();
        assertThat(emailNotificationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

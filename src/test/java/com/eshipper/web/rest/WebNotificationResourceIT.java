package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.WebNotification;
import com.eshipper.repository.WebNotificationRepository;
import com.eshipper.service.WebNotificationService;
import com.eshipper.service.dto.WebNotificationDTO;
import com.eshipper.service.mapper.WebNotificationMapper;
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
 * Integration tests for the {@link WebNotificationResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class WebNotificationResourceIT {

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_READ = false;
    private static final Boolean UPDATED_IS_READ = true;

    private static final String DEFAULT_WEB_MAP = "AAAAAAAAAA";
    private static final String UPDATED_WEB_MAP = "BBBBBBBBBB";

    @Autowired
    private WebNotificationRepository webNotificationRepository;

    @Autowired
    private WebNotificationMapper webNotificationMapper;

    @Autowired
    private WebNotificationService webNotificationService;

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

    private MockMvc restWebNotificationMockMvc;

    private WebNotification webNotification;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final WebNotificationResource webNotificationResource = new WebNotificationResource(webNotificationService);
        this.restWebNotificationMockMvc = MockMvcBuilders.standaloneSetup(webNotificationResource)
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
    public static WebNotification createEntity(EntityManager em) {
        WebNotification webNotification = new WebNotification()
            .url(DEFAULT_URL)
            .isRead(DEFAULT_IS_READ)
            .webMap(DEFAULT_WEB_MAP);
        return webNotification;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WebNotification createUpdatedEntity(EntityManager em) {
        WebNotification webNotification = new WebNotification()
            .url(UPDATED_URL)
            .isRead(UPDATED_IS_READ)
            .webMap(UPDATED_WEB_MAP);
        return webNotification;
    }

    @BeforeEach
    public void initTest() {
        webNotification = createEntity(em);
    }

    @Test
    @Transactional
    public void createWebNotification() throws Exception {
        int databaseSizeBeforeCreate = webNotificationRepository.findAll().size();

        // Create the WebNotification
        WebNotificationDTO webNotificationDTO = webNotificationMapper.toDto(webNotification);
        restWebNotificationMockMvc.perform(post("/api/web-notifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(webNotificationDTO)))
            .andExpect(status().isCreated());

        // Validate the WebNotification in the database
        List<WebNotification> webNotificationList = webNotificationRepository.findAll();
        assertThat(webNotificationList).hasSize(databaseSizeBeforeCreate + 1);
        WebNotification testWebNotification = webNotificationList.get(webNotificationList.size() - 1);
        assertThat(testWebNotification.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testWebNotification.isIsRead()).isEqualTo(DEFAULT_IS_READ);
        assertThat(testWebNotification.getWebMap()).isEqualTo(DEFAULT_WEB_MAP);
    }

    @Test
    @Transactional
    public void createWebNotificationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = webNotificationRepository.findAll().size();

        // Create the WebNotification with an existing ID
        webNotification.setId(1L);
        WebNotificationDTO webNotificationDTO = webNotificationMapper.toDto(webNotification);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWebNotificationMockMvc.perform(post("/api/web-notifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(webNotificationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WebNotification in the database
        List<WebNotification> webNotificationList = webNotificationRepository.findAll();
        assertThat(webNotificationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllWebNotifications() throws Exception {
        // Initialize the database
        webNotificationRepository.saveAndFlush(webNotification);

        // Get all the webNotificationList
        restWebNotificationMockMvc.perform(get("/api/web-notifications?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(webNotification.getId().intValue())))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL)))
            .andExpect(jsonPath("$.[*].isRead").value(hasItem(DEFAULT_IS_READ.booleanValue())))
            .andExpect(jsonPath("$.[*].webMap").value(hasItem(DEFAULT_WEB_MAP)));
    }
    
    @Test
    @Transactional
    public void getWebNotification() throws Exception {
        // Initialize the database
        webNotificationRepository.saveAndFlush(webNotification);

        // Get the webNotification
        restWebNotificationMockMvc.perform(get("/api/web-notifications/{id}", webNotification.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(webNotification.getId().intValue()))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL))
            .andExpect(jsonPath("$.isRead").value(DEFAULT_IS_READ.booleanValue()))
            .andExpect(jsonPath("$.webMap").value(DEFAULT_WEB_MAP));
    }

    @Test
    @Transactional
    public void getNonExistingWebNotification() throws Exception {
        // Get the webNotification
        restWebNotificationMockMvc.perform(get("/api/web-notifications/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWebNotification() throws Exception {
        // Initialize the database
        webNotificationRepository.saveAndFlush(webNotification);

        int databaseSizeBeforeUpdate = webNotificationRepository.findAll().size();

        // Update the webNotification
        WebNotification updatedWebNotification = webNotificationRepository.findById(webNotification.getId()).get();
        // Disconnect from session so that the updates on updatedWebNotification are not directly saved in db
        em.detach(updatedWebNotification);
        updatedWebNotification
            .url(UPDATED_URL)
            .isRead(UPDATED_IS_READ)
            .webMap(UPDATED_WEB_MAP);
        WebNotificationDTO webNotificationDTO = webNotificationMapper.toDto(updatedWebNotification);

        restWebNotificationMockMvc.perform(put("/api/web-notifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(webNotificationDTO)))
            .andExpect(status().isOk());

        // Validate the WebNotification in the database
        List<WebNotification> webNotificationList = webNotificationRepository.findAll();
        assertThat(webNotificationList).hasSize(databaseSizeBeforeUpdate);
        WebNotification testWebNotification = webNotificationList.get(webNotificationList.size() - 1);
        assertThat(testWebNotification.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testWebNotification.isIsRead()).isEqualTo(UPDATED_IS_READ);
        assertThat(testWebNotification.getWebMap()).isEqualTo(UPDATED_WEB_MAP);
    }

    @Test
    @Transactional
    public void updateNonExistingWebNotification() throws Exception {
        int databaseSizeBeforeUpdate = webNotificationRepository.findAll().size();

        // Create the WebNotification
        WebNotificationDTO webNotificationDTO = webNotificationMapper.toDto(webNotification);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWebNotificationMockMvc.perform(put("/api/web-notifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(webNotificationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WebNotification in the database
        List<WebNotification> webNotificationList = webNotificationRepository.findAll();
        assertThat(webNotificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWebNotification() throws Exception {
        // Initialize the database
        webNotificationRepository.saveAndFlush(webNotification);

        int databaseSizeBeforeDelete = webNotificationRepository.findAll().size();

        // Delete the webNotification
        restWebNotificationMockMvc.perform(delete("/api/web-notifications/{id}", webNotification.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WebNotification> webNotificationList = webNotificationRepository.findAll();
        assertThat(webNotificationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

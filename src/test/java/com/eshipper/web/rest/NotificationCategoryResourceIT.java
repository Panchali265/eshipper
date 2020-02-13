package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.NotificationCategory;
import com.eshipper.repository.NotificationCategoryRepository;
import com.eshipper.service.NotificationCategoryService;
import com.eshipper.service.dto.NotificationCategoryDTO;
import com.eshipper.service.mapper.NotificationCategoryMapper;
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
 * Integration tests for the {@link NotificationCategoryResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class NotificationCategoryResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ROLE = "AAAAAAAAAA";
    private static final String UPDATED_ROLE = "BBBBBBBBBB";

    @Autowired
    private NotificationCategoryRepository notificationCategoryRepository;

    @Autowired
    private NotificationCategoryMapper notificationCategoryMapper;

    @Autowired
    private NotificationCategoryService notificationCategoryService;

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

    private MockMvc restNotificationCategoryMockMvc;

    private NotificationCategory notificationCategory;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NotificationCategoryResource notificationCategoryResource = new NotificationCategoryResource(notificationCategoryService);
        this.restNotificationCategoryMockMvc = MockMvcBuilders.standaloneSetup(notificationCategoryResource)
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
    public static NotificationCategory createEntity(EntityManager em) {
        NotificationCategory notificationCategory = new NotificationCategory()
            .name(DEFAULT_NAME)
            .role(DEFAULT_ROLE);
        return notificationCategory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NotificationCategory createUpdatedEntity(EntityManager em) {
        NotificationCategory notificationCategory = new NotificationCategory()
            .name(UPDATED_NAME)
            .role(UPDATED_ROLE);
        return notificationCategory;
    }

    @BeforeEach
    public void initTest() {
        notificationCategory = createEntity(em);
    }

    @Test
    @Transactional
    public void createNotificationCategory() throws Exception {
        int databaseSizeBeforeCreate = notificationCategoryRepository.findAll().size();

        // Create the NotificationCategory
        NotificationCategoryDTO notificationCategoryDTO = notificationCategoryMapper.toDto(notificationCategory);
        restNotificationCategoryMockMvc.perform(post("/api/notification-categories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notificationCategoryDTO)))
            .andExpect(status().isCreated());

        // Validate the NotificationCategory in the database
        List<NotificationCategory> notificationCategoryList = notificationCategoryRepository.findAll();
        assertThat(notificationCategoryList).hasSize(databaseSizeBeforeCreate + 1);
        NotificationCategory testNotificationCategory = notificationCategoryList.get(notificationCategoryList.size() - 1);
        assertThat(testNotificationCategory.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testNotificationCategory.getRole()).isEqualTo(DEFAULT_ROLE);
    }

    @Test
    @Transactional
    public void createNotificationCategoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = notificationCategoryRepository.findAll().size();

        // Create the NotificationCategory with an existing ID
        notificationCategory.setId(1L);
        NotificationCategoryDTO notificationCategoryDTO = notificationCategoryMapper.toDto(notificationCategory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNotificationCategoryMockMvc.perform(post("/api/notification-categories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notificationCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NotificationCategory in the database
        List<NotificationCategory> notificationCategoryList = notificationCategoryRepository.findAll();
        assertThat(notificationCategoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllNotificationCategories() throws Exception {
        // Initialize the database
        notificationCategoryRepository.saveAndFlush(notificationCategory);

        // Get all the notificationCategoryList
        restNotificationCategoryMockMvc.perform(get("/api/notification-categories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(notificationCategory.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE)));
    }
    
    @Test
    @Transactional
    public void getNotificationCategory() throws Exception {
        // Initialize the database
        notificationCategoryRepository.saveAndFlush(notificationCategory);

        // Get the notificationCategory
        restNotificationCategoryMockMvc.perform(get("/api/notification-categories/{id}", notificationCategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(notificationCategory.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.role").value(DEFAULT_ROLE));
    }

    @Test
    @Transactional
    public void getNonExistingNotificationCategory() throws Exception {
        // Get the notificationCategory
        restNotificationCategoryMockMvc.perform(get("/api/notification-categories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNotificationCategory() throws Exception {
        // Initialize the database
        notificationCategoryRepository.saveAndFlush(notificationCategory);

        int databaseSizeBeforeUpdate = notificationCategoryRepository.findAll().size();

        // Update the notificationCategory
        NotificationCategory updatedNotificationCategory = notificationCategoryRepository.findById(notificationCategory.getId()).get();
        // Disconnect from session so that the updates on updatedNotificationCategory are not directly saved in db
        em.detach(updatedNotificationCategory);
        updatedNotificationCategory
            .name(UPDATED_NAME)
            .role(UPDATED_ROLE);
        NotificationCategoryDTO notificationCategoryDTO = notificationCategoryMapper.toDto(updatedNotificationCategory);

        restNotificationCategoryMockMvc.perform(put("/api/notification-categories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notificationCategoryDTO)))
            .andExpect(status().isOk());

        // Validate the NotificationCategory in the database
        List<NotificationCategory> notificationCategoryList = notificationCategoryRepository.findAll();
        assertThat(notificationCategoryList).hasSize(databaseSizeBeforeUpdate);
        NotificationCategory testNotificationCategory = notificationCategoryList.get(notificationCategoryList.size() - 1);
        assertThat(testNotificationCategory.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testNotificationCategory.getRole()).isEqualTo(UPDATED_ROLE);
    }

    @Test
    @Transactional
    public void updateNonExistingNotificationCategory() throws Exception {
        int databaseSizeBeforeUpdate = notificationCategoryRepository.findAll().size();

        // Create the NotificationCategory
        NotificationCategoryDTO notificationCategoryDTO = notificationCategoryMapper.toDto(notificationCategory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNotificationCategoryMockMvc.perform(put("/api/notification-categories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notificationCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NotificationCategory in the database
        List<NotificationCategory> notificationCategoryList = notificationCategoryRepository.findAll();
        assertThat(notificationCategoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNotificationCategory() throws Exception {
        // Initialize the database
        notificationCategoryRepository.saveAndFlush(notificationCategory);

        int databaseSizeBeforeDelete = notificationCategoryRepository.findAll().size();

        // Delete the notificationCategory
        restNotificationCategoryMockMvc.perform(delete("/api/notification-categories/{id}", notificationCategory.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NotificationCategory> notificationCategoryList = notificationCategoryRepository.findAll();
        assertThat(notificationCategoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

package com.eshipper.web.rest;

import com.eshipper.service.NotificationCategoryService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.NotificationCategoryDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.eshipper.domain.NotificationCategory}.
 */
@RestController
@RequestMapping("/api")
public class NotificationCategoryResource {

    private final Logger log = LoggerFactory.getLogger(NotificationCategoryResource.class);

    private static final String ENTITY_NAME = "notificationCategory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NotificationCategoryService notificationCategoryService;

    public NotificationCategoryResource(NotificationCategoryService notificationCategoryService) {
        this.notificationCategoryService = notificationCategoryService;
    }

    /**
     * {@code POST  /notification-categories} : Create a new notificationCategory.
     *
     * @param notificationCategoryDTO the notificationCategoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new notificationCategoryDTO, or with status {@code 400 (Bad Request)} if the notificationCategory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/notification-categories")
    public ResponseEntity<NotificationCategoryDTO> createNotificationCategory(@RequestBody NotificationCategoryDTO notificationCategoryDTO) throws URISyntaxException {
        log.debug("REST request to save NotificationCategory : {}", notificationCategoryDTO);
        if (notificationCategoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new notificationCategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NotificationCategoryDTO result = notificationCategoryService.save(notificationCategoryDTO);
        return ResponseEntity.created(new URI("/api/notification-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /notification-categories} : Updates an existing notificationCategory.
     *
     * @param notificationCategoryDTO the notificationCategoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated notificationCategoryDTO,
     * or with status {@code 400 (Bad Request)} if the notificationCategoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the notificationCategoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/notification-categories")
    public ResponseEntity<NotificationCategoryDTO> updateNotificationCategory(@RequestBody NotificationCategoryDTO notificationCategoryDTO) throws URISyntaxException {
        log.debug("REST request to update NotificationCategory : {}", notificationCategoryDTO);
        if (notificationCategoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NotificationCategoryDTO result = notificationCategoryService.save(notificationCategoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, notificationCategoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /notification-categories} : get all the notificationCategories.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of notificationCategories in body.
     */
    @GetMapping("/notification-categories")
    public List<NotificationCategoryDTO> getAllNotificationCategories() {
        log.debug("REST request to get all NotificationCategories");
        return notificationCategoryService.findAll();
    }

    /**
     * {@code GET  /notification-categories/:id} : get the "id" notificationCategory.
     *
     * @param id the id of the notificationCategoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the notificationCategoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/notification-categories/{id}")
    public ResponseEntity<NotificationCategoryDTO> getNotificationCategory(@PathVariable Long id) {
        log.debug("REST request to get NotificationCategory : {}", id);
        Optional<NotificationCategoryDTO> notificationCategoryDTO = notificationCategoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(notificationCategoryDTO);
    }

    /**
     * {@code DELETE  /notification-categories/:id} : delete the "id" notificationCategory.
     *
     * @param id the id of the notificationCategoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/notification-categories/{id}")
    public ResponseEntity<Void> deleteNotificationCategory(@PathVariable Long id) {
        log.debug("REST request to delete NotificationCategory : {}", id);
        notificationCategoryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

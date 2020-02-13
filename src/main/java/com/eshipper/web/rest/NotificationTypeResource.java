package com.eshipper.web.rest;

import com.eshipper.service.NotificationTypeService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.NotificationTypeDTO;

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
 * REST controller for managing {@link com.eshipper.domain.NotificationType}.
 */
@RestController
@RequestMapping("/api")
public class NotificationTypeResource {

    private final Logger log = LoggerFactory.getLogger(NotificationTypeResource.class);

    private static final String ENTITY_NAME = "notificationType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NotificationTypeService notificationTypeService;

    public NotificationTypeResource(NotificationTypeService notificationTypeService) {
        this.notificationTypeService = notificationTypeService;
    }

    /**
     * {@code POST  /notification-types} : Create a new notificationType.
     *
     * @param notificationTypeDTO the notificationTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new notificationTypeDTO, or with status {@code 400 (Bad Request)} if the notificationType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/notification-types")
    public ResponseEntity<NotificationTypeDTO> createNotificationType(@RequestBody NotificationTypeDTO notificationTypeDTO) throws URISyntaxException {
        log.debug("REST request to save NotificationType : {}", notificationTypeDTO);
        if (notificationTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new notificationType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NotificationTypeDTO result = notificationTypeService.save(notificationTypeDTO);
        return ResponseEntity.created(new URI("/api/notification-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /notification-types} : Updates an existing notificationType.
     *
     * @param notificationTypeDTO the notificationTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated notificationTypeDTO,
     * or with status {@code 400 (Bad Request)} if the notificationTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the notificationTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/notification-types")
    public ResponseEntity<NotificationTypeDTO> updateNotificationType(@RequestBody NotificationTypeDTO notificationTypeDTO) throws URISyntaxException {
        log.debug("REST request to update NotificationType : {}", notificationTypeDTO);
        if (notificationTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NotificationTypeDTO result = notificationTypeService.save(notificationTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, notificationTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /notification-types} : get all the notificationTypes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of notificationTypes in body.
     */
    @GetMapping("/notification-types")
    public List<NotificationTypeDTO> getAllNotificationTypes() {
        log.debug("REST request to get all NotificationTypes");
        return notificationTypeService.findAll();
    }

    /**
     * {@code GET  /notification-types/:id} : get the "id" notificationType.
     *
     * @param id the id of the notificationTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the notificationTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/notification-types/{id}")
    public ResponseEntity<NotificationTypeDTO> getNotificationType(@PathVariable Long id) {
        log.debug("REST request to get NotificationType : {}", id);
        Optional<NotificationTypeDTO> notificationTypeDTO = notificationTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(notificationTypeDTO);
    }

    /**
     * {@code DELETE  /notification-types/:id} : delete the "id" notificationType.
     *
     * @param id the id of the notificationTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/notification-types/{id}")
    public ResponseEntity<Void> deleteNotificationType(@PathVariable Long id) {
        log.debug("REST request to delete NotificationType : {}", id);
        notificationTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

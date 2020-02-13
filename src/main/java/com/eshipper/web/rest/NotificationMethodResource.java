package com.eshipper.web.rest;

import com.eshipper.service.NotificationMethodService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.NotificationMethodDTO;

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
 * REST controller for managing {@link com.eshipper.domain.NotificationMethod}.
 */
@RestController
@RequestMapping("/api")
public class NotificationMethodResource {

    private final Logger log = LoggerFactory.getLogger(NotificationMethodResource.class);

    private static final String ENTITY_NAME = "notificationMethod";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NotificationMethodService notificationMethodService;

    public NotificationMethodResource(NotificationMethodService notificationMethodService) {
        this.notificationMethodService = notificationMethodService;
    }

    /**
     * {@code POST  /notification-methods} : Create a new notificationMethod.
     *
     * @param notificationMethodDTO the notificationMethodDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new notificationMethodDTO, or with status {@code 400 (Bad Request)} if the notificationMethod has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/notification-methods")
    public ResponseEntity<NotificationMethodDTO> createNotificationMethod(@RequestBody NotificationMethodDTO notificationMethodDTO) throws URISyntaxException {
        log.debug("REST request to save NotificationMethod : {}", notificationMethodDTO);
        if (notificationMethodDTO.getId() != null) {
            throw new BadRequestAlertException("A new notificationMethod cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NotificationMethodDTO result = notificationMethodService.save(notificationMethodDTO);
        return ResponseEntity.created(new URI("/api/notification-methods/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /notification-methods} : Updates an existing notificationMethod.
     *
     * @param notificationMethodDTO the notificationMethodDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated notificationMethodDTO,
     * or with status {@code 400 (Bad Request)} if the notificationMethodDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the notificationMethodDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/notification-methods")
    public ResponseEntity<NotificationMethodDTO> updateNotificationMethod(@RequestBody NotificationMethodDTO notificationMethodDTO) throws URISyntaxException {
        log.debug("REST request to update NotificationMethod : {}", notificationMethodDTO);
        if (notificationMethodDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NotificationMethodDTO result = notificationMethodService.save(notificationMethodDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, notificationMethodDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /notification-methods} : get all the notificationMethods.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of notificationMethods in body.
     */
    @GetMapping("/notification-methods")
    public List<NotificationMethodDTO> getAllNotificationMethods() {
        log.debug("REST request to get all NotificationMethods");
        return notificationMethodService.findAll();
    }

    /**
     * {@code GET  /notification-methods/:id} : get the "id" notificationMethod.
     *
     * @param id the id of the notificationMethodDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the notificationMethodDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/notification-methods/{id}")
    public ResponseEntity<NotificationMethodDTO> getNotificationMethod(@PathVariable Long id) {
        log.debug("REST request to get NotificationMethod : {}", id);
        Optional<NotificationMethodDTO> notificationMethodDTO = notificationMethodService.findOne(id);
        return ResponseUtil.wrapOrNotFound(notificationMethodDTO);
    }

    /**
     * {@code DELETE  /notification-methods/:id} : delete the "id" notificationMethod.
     *
     * @param id the id of the notificationMethodDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/notification-methods/{id}")
    public ResponseEntity<Void> deleteNotificationMethod(@PathVariable Long id) {
        log.debug("REST request to delete NotificationMethod : {}", id);
        notificationMethodService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

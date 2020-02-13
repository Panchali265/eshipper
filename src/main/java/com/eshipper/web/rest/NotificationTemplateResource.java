package com.eshipper.web.rest;

import com.eshipper.service.NotificationTemplateService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.NotificationTemplateDTO;

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
 * REST controller for managing {@link com.eshipper.domain.NotificationTemplate}.
 */
@RestController
@RequestMapping("/api")
public class NotificationTemplateResource {

    private final Logger log = LoggerFactory.getLogger(NotificationTemplateResource.class);

    private static final String ENTITY_NAME = "notificationTemplate";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NotificationTemplateService notificationTemplateService;

    public NotificationTemplateResource(NotificationTemplateService notificationTemplateService) {
        this.notificationTemplateService = notificationTemplateService;
    }

    /**
     * {@code POST  /notification-templates} : Create a new notificationTemplate.
     *
     * @param notificationTemplateDTO the notificationTemplateDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new notificationTemplateDTO, or with status {@code 400 (Bad Request)} if the notificationTemplate has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/notification-templates")
    public ResponseEntity<NotificationTemplateDTO> createNotificationTemplate(@RequestBody NotificationTemplateDTO notificationTemplateDTO) throws URISyntaxException {
        log.debug("REST request to save NotificationTemplate : {}", notificationTemplateDTO);
        if (notificationTemplateDTO.getId() != null) {
            throw new BadRequestAlertException("A new notificationTemplate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NotificationTemplateDTO result = notificationTemplateService.save(notificationTemplateDTO);
        return ResponseEntity.created(new URI("/api/notification-templates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /notification-templates} : Updates an existing notificationTemplate.
     *
     * @param notificationTemplateDTO the notificationTemplateDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated notificationTemplateDTO,
     * or with status {@code 400 (Bad Request)} if the notificationTemplateDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the notificationTemplateDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/notification-templates")
    public ResponseEntity<NotificationTemplateDTO> updateNotificationTemplate(@RequestBody NotificationTemplateDTO notificationTemplateDTO) throws URISyntaxException {
        log.debug("REST request to update NotificationTemplate : {}", notificationTemplateDTO);
        if (notificationTemplateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NotificationTemplateDTO result = notificationTemplateService.save(notificationTemplateDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, notificationTemplateDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /notification-templates} : get all the notificationTemplates.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of notificationTemplates in body.
     */
    @GetMapping("/notification-templates")
    public List<NotificationTemplateDTO> getAllNotificationTemplates() {
        log.debug("REST request to get all NotificationTemplates");
        return notificationTemplateService.findAll();
    }

    /**
     * {@code GET  /notification-templates/:id} : get the "id" notificationTemplate.
     *
     * @param id the id of the notificationTemplateDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the notificationTemplateDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/notification-templates/{id}")
    public ResponseEntity<NotificationTemplateDTO> getNotificationTemplate(@PathVariable Long id) {
        log.debug("REST request to get NotificationTemplate : {}", id);
        Optional<NotificationTemplateDTO> notificationTemplateDTO = notificationTemplateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(notificationTemplateDTO);
    }

    /**
     * {@code DELETE  /notification-templates/:id} : delete the "id" notificationTemplate.
     *
     * @param id the id of the notificationTemplateDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/notification-templates/{id}")
    public ResponseEntity<Void> deleteNotificationTemplate(@PathVariable Long id) {
        log.debug("REST request to delete NotificationTemplate : {}", id);
        notificationTemplateService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

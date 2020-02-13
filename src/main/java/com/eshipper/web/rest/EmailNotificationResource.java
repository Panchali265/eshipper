package com.eshipper.web.rest;

import com.eshipper.service.EmailNotificationService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.EmailNotificationDTO;

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
 * REST controller for managing {@link com.eshipper.domain.EmailNotification}.
 */
@RestController
@RequestMapping("/api")
public class EmailNotificationResource {

    private final Logger log = LoggerFactory.getLogger(EmailNotificationResource.class);

    private static final String ENTITY_NAME = "emailNotification";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EmailNotificationService emailNotificationService;

    public EmailNotificationResource(EmailNotificationService emailNotificationService) {
        this.emailNotificationService = emailNotificationService;
    }

    /**
     * {@code POST  /email-notifications} : Create a new emailNotification.
     *
     * @param emailNotificationDTO the emailNotificationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new emailNotificationDTO, or with status {@code 400 (Bad Request)} if the emailNotification has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/email-notifications")
    public ResponseEntity<EmailNotificationDTO> createEmailNotification(@RequestBody EmailNotificationDTO emailNotificationDTO) throws URISyntaxException {
        log.debug("REST request to save EmailNotification : {}", emailNotificationDTO);
        if (emailNotificationDTO.getId() != null) {
            throw new BadRequestAlertException("A new emailNotification cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EmailNotificationDTO result = emailNotificationService.save(emailNotificationDTO);
        return ResponseEntity.created(new URI("/api/email-notifications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /email-notifications} : Updates an existing emailNotification.
     *
     * @param emailNotificationDTO the emailNotificationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated emailNotificationDTO,
     * or with status {@code 400 (Bad Request)} if the emailNotificationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the emailNotificationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/email-notifications")
    public ResponseEntity<EmailNotificationDTO> updateEmailNotification(@RequestBody EmailNotificationDTO emailNotificationDTO) throws URISyntaxException {
        log.debug("REST request to update EmailNotification : {}", emailNotificationDTO);
        if (emailNotificationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EmailNotificationDTO result = emailNotificationService.save(emailNotificationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, emailNotificationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /email-notifications} : get all the emailNotifications.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of emailNotifications in body.
     */
    @GetMapping("/email-notifications")
    public List<EmailNotificationDTO> getAllEmailNotifications() {
        log.debug("REST request to get all EmailNotifications");
        return emailNotificationService.findAll();
    }

    /**
     * {@code GET  /email-notifications/:id} : get the "id" emailNotification.
     *
     * @param id the id of the emailNotificationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the emailNotificationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/email-notifications/{id}")
    public ResponseEntity<EmailNotificationDTO> getEmailNotification(@PathVariable Long id) {
        log.debug("REST request to get EmailNotification : {}", id);
        Optional<EmailNotificationDTO> emailNotificationDTO = emailNotificationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(emailNotificationDTO);
    }

    /**
     * {@code DELETE  /email-notifications/:id} : delete the "id" emailNotification.
     *
     * @param id the id of the emailNotificationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/email-notifications/{id}")
    public ResponseEntity<Void> deleteEmailNotification(@PathVariable Long id) {
        log.debug("REST request to delete EmailNotification : {}", id);
        emailNotificationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

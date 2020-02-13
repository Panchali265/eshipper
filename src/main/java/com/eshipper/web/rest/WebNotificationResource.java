package com.eshipper.web.rest;

import com.eshipper.service.WebNotificationService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.WebNotificationDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.eshipper.domain.WebNotification}.
 */
@RestController
@RequestMapping("/api")
public class WebNotificationResource {

    private final Logger log = LoggerFactory.getLogger(WebNotificationResource.class);

    private static final String ENTITY_NAME = "webNotification";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WebNotificationService webNotificationService;

    public WebNotificationResource(WebNotificationService webNotificationService) {
        this.webNotificationService = webNotificationService;
    }

    /**
     * {@code POST  /web-notifications} : Create a new webNotification.
     *
     * @param webNotificationDTO the webNotificationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new webNotificationDTO, or with status {@code 400 (Bad Request)} if the webNotification has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/web-notifications")
    public ResponseEntity<WebNotificationDTO> createWebNotification(@RequestBody WebNotificationDTO webNotificationDTO) throws URISyntaxException {
        log.debug("REST request to save WebNotification : {}", webNotificationDTO);
        if (webNotificationDTO.getId() != null) {
            throw new BadRequestAlertException("A new webNotification cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WebNotificationDTO result = webNotificationService.save(webNotificationDTO);
        return ResponseEntity.created(new URI("/api/web-notifications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /web-notifications} : Updates an existing webNotification.
     *
     * @param webNotificationDTO the webNotificationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated webNotificationDTO,
     * or with status {@code 400 (Bad Request)} if the webNotificationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the webNotificationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/web-notifications")
    public ResponseEntity<WebNotificationDTO> updateWebNotification(@RequestBody WebNotificationDTO webNotificationDTO) throws URISyntaxException {
        log.debug("REST request to update WebNotification : {}", webNotificationDTO);
        if (webNotificationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WebNotificationDTO result = webNotificationService.save(webNotificationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, webNotificationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /web-notifications} : get all the webNotifications.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of webNotifications in body.
     */
    @GetMapping("/web-notifications")
    public ResponseEntity<List<WebNotificationDTO>> getAllWebNotifications(Pageable pageable) {
        log.debug("REST request to get a page of WebNotifications");
        Page<WebNotificationDTO> page = webNotificationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /web-notifications/:id} : get the "id" webNotification.
     *
     * @param id the id of the webNotificationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the webNotificationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/web-notifications/{id}")
    public ResponseEntity<WebNotificationDTO> getWebNotification(@PathVariable Long id) {
        log.debug("REST request to get WebNotification : {}", id);
        Optional<WebNotificationDTO> webNotificationDTO = webNotificationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(webNotificationDTO);
    }

    /**
     * {@code DELETE  /web-notifications/:id} : delete the "id" webNotification.
     *
     * @param id the id of the webNotificationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/web-notifications/{id}")
    public ResponseEntity<Void> deleteWebNotification(@PathVariable Long id) {
        log.debug("REST request to delete WebNotification : {}", id);
        webNotificationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

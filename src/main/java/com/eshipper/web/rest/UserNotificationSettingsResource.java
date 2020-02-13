package com.eshipper.web.rest;

import com.eshipper.service.UserNotificationSettingsService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.UserNotificationSettingsDTO;

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
 * REST controller for managing {@link com.eshipper.domain.UserNotificationSettings}.
 */
@RestController
@RequestMapping("/api")
public class UserNotificationSettingsResource {

    private final Logger log = LoggerFactory.getLogger(UserNotificationSettingsResource.class);

    private static final String ENTITY_NAME = "userNotificationSettings";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserNotificationSettingsService userNotificationSettingsService;

    public UserNotificationSettingsResource(UserNotificationSettingsService userNotificationSettingsService) {
        this.userNotificationSettingsService = userNotificationSettingsService;
    }

    /**
     * {@code POST  /user-notification-settings} : Create a new userNotificationSettings.
     *
     * @param userNotificationSettingsDTO the userNotificationSettingsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userNotificationSettingsDTO, or with status {@code 400 (Bad Request)} if the userNotificationSettings has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-notification-settings")
    public ResponseEntity<UserNotificationSettingsDTO> createUserNotificationSettings(@RequestBody UserNotificationSettingsDTO userNotificationSettingsDTO) throws URISyntaxException {
        log.debug("REST request to save UserNotificationSettings : {}", userNotificationSettingsDTO);
        if (userNotificationSettingsDTO.getId() != null) {
            throw new BadRequestAlertException("A new userNotificationSettings cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserNotificationSettingsDTO result = userNotificationSettingsService.save(userNotificationSettingsDTO);
        return ResponseEntity.created(new URI("/api/user-notification-settings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-notification-settings} : Updates an existing userNotificationSettings.
     *
     * @param userNotificationSettingsDTO the userNotificationSettingsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userNotificationSettingsDTO,
     * or with status {@code 400 (Bad Request)} if the userNotificationSettingsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userNotificationSettingsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-notification-settings")
    public ResponseEntity<UserNotificationSettingsDTO> updateUserNotificationSettings(@RequestBody UserNotificationSettingsDTO userNotificationSettingsDTO) throws URISyntaxException {
        log.debug("REST request to update UserNotificationSettings : {}", userNotificationSettingsDTO);
        if (userNotificationSettingsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserNotificationSettingsDTO result = userNotificationSettingsService.save(userNotificationSettingsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userNotificationSettingsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /user-notification-settings} : get all the userNotificationSettings.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userNotificationSettings in body.
     */
    @GetMapping("/user-notification-settings")
    public List<UserNotificationSettingsDTO> getAllUserNotificationSettings() {
        log.debug("REST request to get all UserNotificationSettings");
        return userNotificationSettingsService.findAll();
    }

    /**
     * {@code GET  /user-notification-settings/:id} : get the "id" userNotificationSettings.
     *
     * @param id the id of the userNotificationSettingsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userNotificationSettingsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-notification-settings/{id}")
    public ResponseEntity<UserNotificationSettingsDTO> getUserNotificationSettings(@PathVariable Long id) {
        log.debug("REST request to get UserNotificationSettings : {}", id);
        Optional<UserNotificationSettingsDTO> userNotificationSettingsDTO = userNotificationSettingsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userNotificationSettingsDTO);
    }

    /**
     * {@code DELETE  /user-notification-settings/:id} : delete the "id" userNotificationSettings.
     *
     * @param id the id of the userNotificationSettingsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-notification-settings/{id}")
    public ResponseEntity<Void> deleteUserNotificationSettings(@PathVariable Long id) {
        log.debug("REST request to delete UserNotificationSettings : {}", id);
        userNotificationSettingsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

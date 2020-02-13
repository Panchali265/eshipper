package com.eshipper.service;

import com.eshipper.service.dto.WebNotificationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.WebNotification}.
 */
public interface WebNotificationService {

    /**
     * Save a webNotification.
     *
     * @param webNotificationDTO the entity to save.
     * @return the persisted entity.
     */
    WebNotificationDTO save(WebNotificationDTO webNotificationDTO);

    /**
     * Get all the webNotifications.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WebNotificationDTO> findAll(Pageable pageable);

    /**
     * Get the "id" webNotification.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WebNotificationDTO> findOne(Long id);

    /**
     * Delete the "id" webNotification.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

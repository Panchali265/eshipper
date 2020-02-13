package com.eshipper.service;

import com.eshipper.service.dto.EmailNotificationDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.EmailNotification}.
 */
public interface EmailNotificationService {

    /**
     * Save a emailNotification.
     *
     * @param emailNotificationDTO the entity to save.
     * @return the persisted entity.
     */
    EmailNotificationDTO save(EmailNotificationDTO emailNotificationDTO);

    /**
     * Get all the emailNotifications.
     *
     * @return the list of entities.
     */
    List<EmailNotificationDTO> findAll();

    /**
     * Get the "id" emailNotification.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EmailNotificationDTO> findOne(Long id);

    /**
     * Delete the "id" emailNotification.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

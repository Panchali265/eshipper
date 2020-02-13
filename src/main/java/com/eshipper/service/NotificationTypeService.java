package com.eshipper.service;

import com.eshipper.service.dto.NotificationTypeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.NotificationType}.
 */
public interface NotificationTypeService {

    /**
     * Save a notificationType.
     *
     * @param notificationTypeDTO the entity to save.
     * @return the persisted entity.
     */
    NotificationTypeDTO save(NotificationTypeDTO notificationTypeDTO);

    /**
     * Get all the notificationTypes.
     *
     * @return the list of entities.
     */
    List<NotificationTypeDTO> findAll();

    /**
     * Get the "id" notificationType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NotificationTypeDTO> findOne(Long id);

    /**
     * Delete the "id" notificationType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

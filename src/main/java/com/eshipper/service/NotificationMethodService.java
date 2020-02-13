package com.eshipper.service;

import com.eshipper.service.dto.NotificationMethodDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.NotificationMethod}.
 */
public interface NotificationMethodService {

    /**
     * Save a notificationMethod.
     *
     * @param notificationMethodDTO the entity to save.
     * @return the persisted entity.
     */
    NotificationMethodDTO save(NotificationMethodDTO notificationMethodDTO);

    /**
     * Get all the notificationMethods.
     *
     * @return the list of entities.
     */
    List<NotificationMethodDTO> findAll();

    /**
     * Get the "id" notificationMethod.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NotificationMethodDTO> findOne(Long id);

    /**
     * Delete the "id" notificationMethod.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

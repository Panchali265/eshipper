package com.eshipper.service;

import com.eshipper.service.dto.NotificationCategoryDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.NotificationCategory}.
 */
public interface NotificationCategoryService {

    /**
     * Save a notificationCategory.
     *
     * @param notificationCategoryDTO the entity to save.
     * @return the persisted entity.
     */
    NotificationCategoryDTO save(NotificationCategoryDTO notificationCategoryDTO);

    /**
     * Get all the notificationCategories.
     *
     * @return the list of entities.
     */
    List<NotificationCategoryDTO> findAll();

    /**
     * Get the "id" notificationCategory.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NotificationCategoryDTO> findOne(Long id);

    /**
     * Delete the "id" notificationCategory.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

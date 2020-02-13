package com.eshipper.service;

import com.eshipper.service.dto.UserNotificationSettingsDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.UserNotificationSettings}.
 */
public interface UserNotificationSettingsService {

    /**
     * Save a userNotificationSettings.
     *
     * @param userNotificationSettingsDTO the entity to save.
     * @return the persisted entity.
     */
    UserNotificationSettingsDTO save(UserNotificationSettingsDTO userNotificationSettingsDTO);

    /**
     * Get all the userNotificationSettings.
     *
     * @return the list of entities.
     */
    List<UserNotificationSettingsDTO> findAll();

    /**
     * Get the "id" userNotificationSettings.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserNotificationSettingsDTO> findOne(Long id);

    /**
     * Delete the "id" userNotificationSettings.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

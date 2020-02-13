package com.eshipper.service;

import com.eshipper.service.dto.NotificationTemplateDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.NotificationTemplate}.
 */
public interface NotificationTemplateService {

    /**
     * Save a notificationTemplate.
     *
     * @param notificationTemplateDTO the entity to save.
     * @return the persisted entity.
     */
    NotificationTemplateDTO save(NotificationTemplateDTO notificationTemplateDTO);

    /**
     * Get all the notificationTemplates.
     *
     * @return the list of entities.
     */
    List<NotificationTemplateDTO> findAll();

    /**
     * Get the "id" notificationTemplate.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NotificationTemplateDTO> findOne(Long id);

    /**
     * Delete the "id" notificationTemplate.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

package com.eshipper.service.impl;

import com.eshipper.service.NotificationTemplateService;
import com.eshipper.domain.NotificationTemplate;
import com.eshipper.repository.NotificationTemplateRepository;
import com.eshipper.service.dto.NotificationTemplateDTO;
import com.eshipper.service.mapper.NotificationTemplateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link NotificationTemplate}.
 */
@Service
@Transactional
public class NotificationTemplateServiceImpl implements NotificationTemplateService {

    private final Logger log = LoggerFactory.getLogger(NotificationTemplateServiceImpl.class);

    private final NotificationTemplateRepository notificationTemplateRepository;

    private final NotificationTemplateMapper notificationTemplateMapper;

    public NotificationTemplateServiceImpl(NotificationTemplateRepository notificationTemplateRepository, NotificationTemplateMapper notificationTemplateMapper) {
        this.notificationTemplateRepository = notificationTemplateRepository;
        this.notificationTemplateMapper = notificationTemplateMapper;
    }

    /**
     * Save a notificationTemplate.
     *
     * @param notificationTemplateDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public NotificationTemplateDTO save(NotificationTemplateDTO notificationTemplateDTO) {
        log.debug("Request to save NotificationTemplate : {}", notificationTemplateDTO);
        NotificationTemplate notificationTemplate = notificationTemplateMapper.toEntity(notificationTemplateDTO);
        notificationTemplate = notificationTemplateRepository.save(notificationTemplate);
        return notificationTemplateMapper.toDto(notificationTemplate);
    }

    /**
     * Get all the notificationTemplates.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<NotificationTemplateDTO> findAll() {
        log.debug("Request to get all NotificationTemplates");
        return notificationTemplateRepository.findAll().stream()
            .map(notificationTemplateMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one notificationTemplate by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NotificationTemplateDTO> findOne(Long id) {
        log.debug("Request to get NotificationTemplate : {}", id);
        return notificationTemplateRepository.findById(id)
            .map(notificationTemplateMapper::toDto);
    }

    /**
     * Delete the notificationTemplate by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NotificationTemplate : {}", id);
        notificationTemplateRepository.deleteById(id);
    }
}

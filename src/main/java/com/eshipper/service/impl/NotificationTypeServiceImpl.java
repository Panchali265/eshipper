package com.eshipper.service.impl;

import com.eshipper.service.NotificationTypeService;
import com.eshipper.domain.NotificationType;
import com.eshipper.repository.NotificationTypeRepository;
import com.eshipper.service.dto.NotificationTypeDTO;
import com.eshipper.service.mapper.NotificationTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link NotificationType}.
 */
@Service
@Transactional
public class NotificationTypeServiceImpl implements NotificationTypeService {

    private final Logger log = LoggerFactory.getLogger(NotificationTypeServiceImpl.class);

    private final NotificationTypeRepository notificationTypeRepository;

    private final NotificationTypeMapper notificationTypeMapper;

    public NotificationTypeServiceImpl(NotificationTypeRepository notificationTypeRepository, NotificationTypeMapper notificationTypeMapper) {
        this.notificationTypeRepository = notificationTypeRepository;
        this.notificationTypeMapper = notificationTypeMapper;
    }

    /**
     * Save a notificationType.
     *
     * @param notificationTypeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public NotificationTypeDTO save(NotificationTypeDTO notificationTypeDTO) {
        log.debug("Request to save NotificationType : {}", notificationTypeDTO);
        NotificationType notificationType = notificationTypeMapper.toEntity(notificationTypeDTO);
        notificationType = notificationTypeRepository.save(notificationType);
        return notificationTypeMapper.toDto(notificationType);
    }

    /**
     * Get all the notificationTypes.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<NotificationTypeDTO> findAll() {
        log.debug("Request to get all NotificationTypes");
        return notificationTypeRepository.findAll().stream()
            .map(notificationTypeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one notificationType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NotificationTypeDTO> findOne(Long id) {
        log.debug("Request to get NotificationType : {}", id);
        return notificationTypeRepository.findById(id)
            .map(notificationTypeMapper::toDto);
    }

    /**
     * Delete the notificationType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NotificationType : {}", id);
        notificationTypeRepository.deleteById(id);
    }
}

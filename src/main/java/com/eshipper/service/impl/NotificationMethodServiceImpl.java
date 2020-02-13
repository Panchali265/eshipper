package com.eshipper.service.impl;

import com.eshipper.service.NotificationMethodService;
import com.eshipper.domain.NotificationMethod;
import com.eshipper.repository.NotificationMethodRepository;
import com.eshipper.service.dto.NotificationMethodDTO;
import com.eshipper.service.mapper.NotificationMethodMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link NotificationMethod}.
 */
@Service
@Transactional
public class NotificationMethodServiceImpl implements NotificationMethodService {

    private final Logger log = LoggerFactory.getLogger(NotificationMethodServiceImpl.class);

    private final NotificationMethodRepository notificationMethodRepository;

    private final NotificationMethodMapper notificationMethodMapper;

    public NotificationMethodServiceImpl(NotificationMethodRepository notificationMethodRepository, NotificationMethodMapper notificationMethodMapper) {
        this.notificationMethodRepository = notificationMethodRepository;
        this.notificationMethodMapper = notificationMethodMapper;
    }

    /**
     * Save a notificationMethod.
     *
     * @param notificationMethodDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public NotificationMethodDTO save(NotificationMethodDTO notificationMethodDTO) {
        log.debug("Request to save NotificationMethod : {}", notificationMethodDTO);
        NotificationMethod notificationMethod = notificationMethodMapper.toEntity(notificationMethodDTO);
        notificationMethod = notificationMethodRepository.save(notificationMethod);
        return notificationMethodMapper.toDto(notificationMethod);
    }

    /**
     * Get all the notificationMethods.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<NotificationMethodDTO> findAll() {
        log.debug("Request to get all NotificationMethods");
        return notificationMethodRepository.findAll().stream()
            .map(notificationMethodMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one notificationMethod by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NotificationMethodDTO> findOne(Long id) {
        log.debug("Request to get NotificationMethod : {}", id);
        return notificationMethodRepository.findById(id)
            .map(notificationMethodMapper::toDto);
    }

    /**
     * Delete the notificationMethod by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NotificationMethod : {}", id);
        notificationMethodRepository.deleteById(id);
    }
}

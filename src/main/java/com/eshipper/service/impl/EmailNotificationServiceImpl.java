package com.eshipper.service.impl;

import com.eshipper.service.EmailNotificationService;
import com.eshipper.domain.EmailNotification;
import com.eshipper.repository.EmailNotificationRepository;
import com.eshipper.service.dto.EmailNotificationDTO;
import com.eshipper.service.mapper.EmailNotificationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EmailNotification}.
 */
@Service
@Transactional
public class EmailNotificationServiceImpl implements EmailNotificationService {

    private final Logger log = LoggerFactory.getLogger(EmailNotificationServiceImpl.class);

    private final EmailNotificationRepository emailNotificationRepository;

    private final EmailNotificationMapper emailNotificationMapper;

    public EmailNotificationServiceImpl(EmailNotificationRepository emailNotificationRepository, EmailNotificationMapper emailNotificationMapper) {
        this.emailNotificationRepository = emailNotificationRepository;
        this.emailNotificationMapper = emailNotificationMapper;
    }

    /**
     * Save a emailNotification.
     *
     * @param emailNotificationDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EmailNotificationDTO save(EmailNotificationDTO emailNotificationDTO) {
        log.debug("Request to save EmailNotification : {}", emailNotificationDTO);
        EmailNotification emailNotification = emailNotificationMapper.toEntity(emailNotificationDTO);
        emailNotification = emailNotificationRepository.save(emailNotification);
        return emailNotificationMapper.toDto(emailNotification);
    }

    /**
     * Get all the emailNotifications.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EmailNotificationDTO> findAll() {
        log.debug("Request to get all EmailNotifications");
        return emailNotificationRepository.findAll().stream()
            .map(emailNotificationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one emailNotification by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EmailNotificationDTO> findOne(Long id) {
        log.debug("Request to get EmailNotification : {}", id);
        return emailNotificationRepository.findById(id)
            .map(emailNotificationMapper::toDto);
    }

    /**
     * Delete the emailNotification by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EmailNotification : {}", id);
        emailNotificationRepository.deleteById(id);
    }
}

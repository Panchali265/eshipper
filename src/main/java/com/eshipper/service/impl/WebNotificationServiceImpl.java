package com.eshipper.service.impl;

import com.eshipper.service.WebNotificationService;
import com.eshipper.domain.WebNotification;
import com.eshipper.repository.WebNotificationRepository;
import com.eshipper.service.dto.WebNotificationDTO;
import com.eshipper.service.mapper.WebNotificationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link WebNotification}.
 */
@Service
@Transactional
public class WebNotificationServiceImpl implements WebNotificationService {

    private final Logger log = LoggerFactory.getLogger(WebNotificationServiceImpl.class);

    private final WebNotificationRepository webNotificationRepository;

    private final WebNotificationMapper webNotificationMapper;

    public WebNotificationServiceImpl(WebNotificationRepository webNotificationRepository, WebNotificationMapper webNotificationMapper) {
        this.webNotificationRepository = webNotificationRepository;
        this.webNotificationMapper = webNotificationMapper;
    }

    /**
     * Save a webNotification.
     *
     * @param webNotificationDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public WebNotificationDTO save(WebNotificationDTO webNotificationDTO) {
        log.debug("Request to save WebNotification : {}", webNotificationDTO);
        WebNotification webNotification = webNotificationMapper.toEntity(webNotificationDTO);
        webNotification = webNotificationRepository.save(webNotification);
        return webNotificationMapper.toDto(webNotification);
    }

    /**
     * Get all the webNotifications.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<WebNotificationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WebNotifications");
        return webNotificationRepository.findAll(pageable)
            .map(webNotificationMapper::toDto);
    }

    /**
     * Get one webNotification by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<WebNotificationDTO> findOne(Long id) {
        log.debug("Request to get WebNotification : {}", id);
        return webNotificationRepository.findById(id)
            .map(webNotificationMapper::toDto);
    }

    /**
     * Delete the webNotification by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete WebNotification : {}", id);
        webNotificationRepository.deleteById(id);
    }
}

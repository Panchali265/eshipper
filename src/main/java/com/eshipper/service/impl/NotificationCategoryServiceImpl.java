package com.eshipper.service.impl;

import com.eshipper.service.NotificationCategoryService;
import com.eshipper.domain.NotificationCategory;
import com.eshipper.repository.NotificationCategoryRepository;
import com.eshipper.service.dto.NotificationCategoryDTO;
import com.eshipper.service.mapper.NotificationCategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link NotificationCategory}.
 */
@Service
@Transactional
public class NotificationCategoryServiceImpl implements NotificationCategoryService {

    private final Logger log = LoggerFactory.getLogger(NotificationCategoryServiceImpl.class);

    private final NotificationCategoryRepository notificationCategoryRepository;

    private final NotificationCategoryMapper notificationCategoryMapper;

    public NotificationCategoryServiceImpl(NotificationCategoryRepository notificationCategoryRepository, NotificationCategoryMapper notificationCategoryMapper) {
        this.notificationCategoryRepository = notificationCategoryRepository;
        this.notificationCategoryMapper = notificationCategoryMapper;
    }

    /**
     * Save a notificationCategory.
     *
     * @param notificationCategoryDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public NotificationCategoryDTO save(NotificationCategoryDTO notificationCategoryDTO) {
        log.debug("Request to save NotificationCategory : {}", notificationCategoryDTO);
        NotificationCategory notificationCategory = notificationCategoryMapper.toEntity(notificationCategoryDTO);
        notificationCategory = notificationCategoryRepository.save(notificationCategory);
        return notificationCategoryMapper.toDto(notificationCategory);
    }

    /**
     * Get all the notificationCategories.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<NotificationCategoryDTO> findAll() {
        log.debug("Request to get all NotificationCategories");
        return notificationCategoryRepository.findAll().stream()
            .map(notificationCategoryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one notificationCategory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NotificationCategoryDTO> findOne(Long id) {
        log.debug("Request to get NotificationCategory : {}", id);
        return notificationCategoryRepository.findById(id)
            .map(notificationCategoryMapper::toDto);
    }

    /**
     * Delete the notificationCategory by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NotificationCategory : {}", id);
        notificationCategoryRepository.deleteById(id);
    }
}

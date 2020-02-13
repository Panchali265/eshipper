package com.eshipper.service.impl;

import com.eshipper.service.UserNotificationSettingsService;
import com.eshipper.domain.UserNotificationSettings;
import com.eshipper.repository.UserNotificationSettingsRepository;
import com.eshipper.service.dto.UserNotificationSettingsDTO;
import com.eshipper.service.mapper.UserNotificationSettingsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link UserNotificationSettings}.
 */
@Service
@Transactional
public class UserNotificationSettingsServiceImpl implements UserNotificationSettingsService {

    private final Logger log = LoggerFactory.getLogger(UserNotificationSettingsServiceImpl.class);

    private final UserNotificationSettingsRepository userNotificationSettingsRepository;

    private final UserNotificationSettingsMapper userNotificationSettingsMapper;

    public UserNotificationSettingsServiceImpl(UserNotificationSettingsRepository userNotificationSettingsRepository, UserNotificationSettingsMapper userNotificationSettingsMapper) {
        this.userNotificationSettingsRepository = userNotificationSettingsRepository;
        this.userNotificationSettingsMapper = userNotificationSettingsMapper;
    }

    /**
     * Save a userNotificationSettings.
     *
     * @param userNotificationSettingsDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public UserNotificationSettingsDTO save(UserNotificationSettingsDTO userNotificationSettingsDTO) {
        log.debug("Request to save UserNotificationSettings : {}", userNotificationSettingsDTO);
        UserNotificationSettings userNotificationSettings = userNotificationSettingsMapper.toEntity(userNotificationSettingsDTO);
        userNotificationSettings = userNotificationSettingsRepository.save(userNotificationSettings);
        return userNotificationSettingsMapper.toDto(userNotificationSettings);
    }

    /**
     * Get all the userNotificationSettings.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserNotificationSettingsDTO> findAll() {
        log.debug("Request to get all UserNotificationSettings");
        return userNotificationSettingsRepository.findAll().stream()
            .map(userNotificationSettingsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one userNotificationSettings by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UserNotificationSettingsDTO> findOne(Long id) {
        log.debug("Request to get UserNotificationSettings : {}", id);
        return userNotificationSettingsRepository.findById(id)
            .map(userNotificationSettingsMapper::toDto);
    }

    /**
     * Delete the userNotificationSettings by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserNotificationSettings : {}", id);
        userNotificationSettingsRepository.deleteById(id);
    }
}

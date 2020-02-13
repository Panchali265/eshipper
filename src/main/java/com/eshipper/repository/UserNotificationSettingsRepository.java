package com.eshipper.repository;

import com.eshipper.domain.UserNotificationSettings;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the UserNotificationSettings entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserNotificationSettingsRepository extends JpaRepository<UserNotificationSettings, Long> {

}

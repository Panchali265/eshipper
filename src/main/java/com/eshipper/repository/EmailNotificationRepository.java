package com.eshipper.repository;

import com.eshipper.domain.EmailNotification;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EmailNotification entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmailNotificationRepository extends JpaRepository<EmailNotification, Long> {

}

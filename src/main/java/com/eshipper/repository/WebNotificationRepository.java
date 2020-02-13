package com.eshipper.repository;

import com.eshipper.domain.WebNotification;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the WebNotification entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WebNotificationRepository extends JpaRepository<WebNotification, Long> {

}

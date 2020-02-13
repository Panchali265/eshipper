package com.eshipper.repository;

import com.eshipper.domain.NotificationTemplate;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the NotificationTemplate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NotificationTemplateRepository extends JpaRepository<NotificationTemplate, Long> {

}

package com.eshipper.repository;

import com.eshipper.domain.NotificationCategory;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the NotificationCategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NotificationCategoryRepository extends JpaRepository<NotificationCategory, Long> {

}

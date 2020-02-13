package com.eshipper.repository;

import com.eshipper.domain.NotificationMethod;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the NotificationMethod entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NotificationMethodRepository extends JpaRepository<NotificationMethod, Long> {

}

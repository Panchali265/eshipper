package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.NotificationCategoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link NotificationCategory} and its DTO {@link NotificationCategoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NotificationCategoryMapper extends EntityMapper<NotificationCategoryDTO, NotificationCategory> {


    @Mapping(target = "notificationTypes", ignore = true)
    @Mapping(target = "removeNotificationType", ignore = true)
    NotificationCategory toEntity(NotificationCategoryDTO notificationCategoryDTO);

    default NotificationCategory fromId(Long id) {
        if (id == null) {
            return null;
        }
        NotificationCategory notificationCategory = new NotificationCategory();
        notificationCategory.setId(id);
        return notificationCategory;
    }
}

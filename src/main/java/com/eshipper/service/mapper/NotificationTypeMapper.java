package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.NotificationTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link NotificationType} and its DTO {@link NotificationTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {NotificationCategoryMapper.class})
public interface NotificationTypeMapper extends EntityMapper<NotificationTypeDTO, NotificationType> {

    @Mapping(source = "notificationCategory.id", target = "notificationCategoryId")
    NotificationTypeDTO toDto(NotificationType notificationType);

    @Mapping(target = "notificationTemplates", ignore = true)
    @Mapping(target = "removeNotificationTemplate", ignore = true)
    @Mapping(source = "notificationCategoryId", target = "notificationCategory")
    NotificationType toEntity(NotificationTypeDTO notificationTypeDTO);

    default NotificationType fromId(Long id) {
        if (id == null) {
            return null;
        }
        NotificationType notificationType = new NotificationType();
        notificationType.setId(id);
        return notificationType;
    }
}

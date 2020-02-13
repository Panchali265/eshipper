package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.NotificationTemplateDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link NotificationTemplate} and its DTO {@link NotificationTemplateDTO}.
 */
@Mapper(componentModel = "spring", uses = {NotificationMethodMapper.class, NotificationTypeMapper.class})
public interface NotificationTemplateMapper extends EntityMapper<NotificationTemplateDTO, NotificationTemplate> {

    @Mapping(source = "notificationMethod.id", target = "notificationMethodId")
    @Mapping(source = "notificationType.id", target = "notificationTypeId")
    NotificationTemplateDTO toDto(NotificationTemplate notificationTemplate);

    @Mapping(source = "notificationMethodId", target = "notificationMethod")
    @Mapping(source = "notificationTypeId", target = "notificationType")
    NotificationTemplate toEntity(NotificationTemplateDTO notificationTemplateDTO);

    default NotificationTemplate fromId(Long id) {
        if (id == null) {
            return null;
        }
        NotificationTemplate notificationTemplate = new NotificationTemplate();
        notificationTemplate.setId(id);
        return notificationTemplate;
    }
}

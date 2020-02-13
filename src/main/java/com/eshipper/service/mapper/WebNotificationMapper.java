package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.WebNotificationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WebNotification} and its DTO {@link WebNotificationDTO}.
 */
@Mapper(componentModel = "spring", uses = {NotificationTemplateMapper.class, User1Mapper.class})
public interface WebNotificationMapper extends EntityMapper<WebNotificationDTO, WebNotification> {

    @Mapping(source = "notificationTemplate.id", target = "notificationTemplateId")
    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "toUser.id", target = "toUserId")
    WebNotificationDTO toDto(WebNotification webNotification);

    @Mapping(source = "notificationTemplateId", target = "notificationTemplate")
    @Mapping(source = "createdById", target = "createdBy")
    @Mapping(source = "toUserId", target = "toUser")
    WebNotification toEntity(WebNotificationDTO webNotificationDTO);

    default WebNotification fromId(Long id) {
        if (id == null) {
            return null;
        }
        WebNotification webNotification = new WebNotification();
        webNotification.setId(id);
        return webNotification;
    }
}

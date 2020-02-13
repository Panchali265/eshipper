package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.EmailNotificationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EmailNotification} and its DTO {@link EmailNotificationDTO}.
 */
@Mapper(componentModel = "spring", uses = {NotificationTemplateMapper.class, User1Mapper.class})
public interface EmailNotificationMapper extends EntityMapper<EmailNotificationDTO, EmailNotification> {

    @Mapping(source = "notificationTemplate.id", target = "notificationTemplateId")
    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "toUser.id", target = "toUserId")
    EmailNotificationDTO toDto(EmailNotification emailNotification);

    @Mapping(source = "notificationTemplateId", target = "notificationTemplate")
    @Mapping(source = "createdById", target = "createdBy")
    @Mapping(source = "toUserId", target = "toUser")
    EmailNotification toEntity(EmailNotificationDTO emailNotificationDTO);

    default EmailNotification fromId(Long id) {
        if (id == null) {
            return null;
        }
        EmailNotification emailNotification = new EmailNotification();
        emailNotification.setId(id);
        return emailNotification;
    }
}

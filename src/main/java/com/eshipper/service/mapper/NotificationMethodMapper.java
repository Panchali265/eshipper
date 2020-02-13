package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.NotificationMethodDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link NotificationMethod} and its DTO {@link NotificationMethodDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NotificationMethodMapper extends EntityMapper<NotificationMethodDTO, NotificationMethod> {


    @Mapping(target = "notificationTemplates", ignore = true)
    @Mapping(target = "removeNotificationTemplate", ignore = true)
    NotificationMethod toEntity(NotificationMethodDTO notificationMethodDTO);

    default NotificationMethod fromId(Long id) {
        if (id == null) {
            return null;
        }
        NotificationMethod notificationMethod = new NotificationMethod();
        notificationMethod.setId(id);
        return notificationMethod;
    }
}

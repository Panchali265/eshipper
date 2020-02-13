package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.User1DTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link User1} and its DTO {@link User1DTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface User1Mapper extends EntityMapper<User1DTO, User1> {


    @Mapping(target = "webNotifications", ignore = true)
    @Mapping(target = "removeWebNotification", ignore = true)
    @Mapping(target = "webNotifications", ignore = true)
    @Mapping(target = "removeWebNotification", ignore = true)
    @Mapping(target = "emailNotifications", ignore = true)
    @Mapping(target = "removeEmailNotification", ignore = true)
    @Mapping(target = "emailNotifications", ignore = true)
    @Mapping(target = "removeEmailNotification", ignore = true)
    @Mapping(target = "userNotificationSettings", ignore = true)
    @Mapping(target = "removeUserNotificationSettings", ignore = true)
    User1 toEntity(User1DTO user1DTO);

    default User1 fromId(Long id) {
        if (id == null) {
            return null;
        }
        User1 user1 = new User1();
        user1.setId(id);
        return user1;
    }
}

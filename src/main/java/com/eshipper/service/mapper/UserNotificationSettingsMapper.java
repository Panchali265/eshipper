package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.UserNotificationSettingsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserNotificationSettings} and its DTO {@link UserNotificationSettingsDTO}.
 */
@Mapper(componentModel = "spring", uses = {User1Mapper.class})
public interface UserNotificationSettingsMapper extends EntityMapper<UserNotificationSettingsDTO, UserNotificationSettings> {

    @Mapping(source = "user.id", target = "userId")
    UserNotificationSettingsDTO toDto(UserNotificationSettings userNotificationSettings);

    @Mapping(source = "userId", target = "user")
    UserNotificationSettings toEntity(UserNotificationSettingsDTO userNotificationSettingsDTO);

    default UserNotificationSettings fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserNotificationSettings userNotificationSettings = new UserNotificationSettings();
        userNotificationSettings.setId(id);
        return userNotificationSettings;
    }
}

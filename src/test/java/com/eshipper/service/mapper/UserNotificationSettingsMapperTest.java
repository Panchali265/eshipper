package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class UserNotificationSettingsMapperTest {

    private UserNotificationSettingsMapper userNotificationSettingsMapper;

    @BeforeEach
    public void setUp() {
        userNotificationSettingsMapper = new UserNotificationSettingsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(userNotificationSettingsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(userNotificationSettingsMapper.fromId(null)).isNull();
    }
}

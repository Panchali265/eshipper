package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class NotificationTemplateMapperTest {

    private NotificationTemplateMapper notificationTemplateMapper;

    @BeforeEach
    public void setUp() {
        notificationTemplateMapper = new NotificationTemplateMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(notificationTemplateMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(notificationTemplateMapper.fromId(null)).isNull();
    }
}

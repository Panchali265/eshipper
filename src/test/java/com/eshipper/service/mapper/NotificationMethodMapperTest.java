package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class NotificationMethodMapperTest {

    private NotificationMethodMapper notificationMethodMapper;

    @BeforeEach
    public void setUp() {
        notificationMethodMapper = new NotificationMethodMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(notificationMethodMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(notificationMethodMapper.fromId(null)).isNull();
    }
}

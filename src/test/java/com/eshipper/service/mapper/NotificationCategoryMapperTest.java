package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class NotificationCategoryMapperTest {

    private NotificationCategoryMapper notificationCategoryMapper;

    @BeforeEach
    public void setUp() {
        notificationCategoryMapper = new NotificationCategoryMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(notificationCategoryMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(notificationCategoryMapper.fromId(null)).isNull();
    }
}

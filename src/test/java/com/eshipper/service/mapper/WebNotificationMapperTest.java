package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class WebNotificationMapperTest {

    private WebNotificationMapper webNotificationMapper;

    @BeforeEach
    public void setUp() {
        webNotificationMapper = new WebNotificationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(webNotificationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(webNotificationMapper.fromId(null)).isNull();
    }
}

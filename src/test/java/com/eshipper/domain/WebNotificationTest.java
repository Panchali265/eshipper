package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class WebNotificationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WebNotification.class);
        WebNotification webNotification1 = new WebNotification();
        webNotification1.setId(1L);
        WebNotification webNotification2 = new WebNotification();
        webNotification2.setId(webNotification1.getId());
        assertThat(webNotification1).isEqualTo(webNotification2);
        webNotification2.setId(2L);
        assertThat(webNotification1).isNotEqualTo(webNotification2);
        webNotification1.setId(null);
        assertThat(webNotification1).isNotEqualTo(webNotification2);
    }
}

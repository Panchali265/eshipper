package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class NotificationTemplateTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotificationTemplate.class);
        NotificationTemplate notificationTemplate1 = new NotificationTemplate();
        notificationTemplate1.setId(1L);
        NotificationTemplate notificationTemplate2 = new NotificationTemplate();
        notificationTemplate2.setId(notificationTemplate1.getId());
        assertThat(notificationTemplate1).isEqualTo(notificationTemplate2);
        notificationTemplate2.setId(2L);
        assertThat(notificationTemplate1).isNotEqualTo(notificationTemplate2);
        notificationTemplate1.setId(null);
        assertThat(notificationTemplate1).isNotEqualTo(notificationTemplate2);
    }
}

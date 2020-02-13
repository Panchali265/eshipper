package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class NotificationMethodTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotificationMethod.class);
        NotificationMethod notificationMethod1 = new NotificationMethod();
        notificationMethod1.setId(1L);
        NotificationMethod notificationMethod2 = new NotificationMethod();
        notificationMethod2.setId(notificationMethod1.getId());
        assertThat(notificationMethod1).isEqualTo(notificationMethod2);
        notificationMethod2.setId(2L);
        assertThat(notificationMethod1).isNotEqualTo(notificationMethod2);
        notificationMethod1.setId(null);
        assertThat(notificationMethod1).isNotEqualTo(notificationMethod2);
    }
}

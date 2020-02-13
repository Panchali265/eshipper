package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class NotificationCategoryTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotificationCategory.class);
        NotificationCategory notificationCategory1 = new NotificationCategory();
        notificationCategory1.setId(1L);
        NotificationCategory notificationCategory2 = new NotificationCategory();
        notificationCategory2.setId(notificationCategory1.getId());
        assertThat(notificationCategory1).isEqualTo(notificationCategory2);
        notificationCategory2.setId(2L);
        assertThat(notificationCategory1).isNotEqualTo(notificationCategory2);
        notificationCategory1.setId(null);
        assertThat(notificationCategory1).isNotEqualTo(notificationCategory2);
    }
}

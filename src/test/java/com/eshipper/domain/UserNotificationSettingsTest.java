package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class UserNotificationSettingsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserNotificationSettings.class);
        UserNotificationSettings userNotificationSettings1 = new UserNotificationSettings();
        userNotificationSettings1.setId(1L);
        UserNotificationSettings userNotificationSettings2 = new UserNotificationSettings();
        userNotificationSettings2.setId(userNotificationSettings1.getId());
        assertThat(userNotificationSettings1).isEqualTo(userNotificationSettings2);
        userNotificationSettings2.setId(2L);
        assertThat(userNotificationSettings1).isNotEqualTo(userNotificationSettings2);
        userNotificationSettings1.setId(null);
        assertThat(userNotificationSettings1).isNotEqualTo(userNotificationSettings2);
    }
}

package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class UserNotificationSettingsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserNotificationSettingsDTO.class);
        UserNotificationSettingsDTO userNotificationSettingsDTO1 = new UserNotificationSettingsDTO();
        userNotificationSettingsDTO1.setId(1L);
        UserNotificationSettingsDTO userNotificationSettingsDTO2 = new UserNotificationSettingsDTO();
        assertThat(userNotificationSettingsDTO1).isNotEqualTo(userNotificationSettingsDTO2);
        userNotificationSettingsDTO2.setId(userNotificationSettingsDTO1.getId());
        assertThat(userNotificationSettingsDTO1).isEqualTo(userNotificationSettingsDTO2);
        userNotificationSettingsDTO2.setId(2L);
        assertThat(userNotificationSettingsDTO1).isNotEqualTo(userNotificationSettingsDTO2);
        userNotificationSettingsDTO1.setId(null);
        assertThat(userNotificationSettingsDTO1).isNotEqualTo(userNotificationSettingsDTO2);
    }
}

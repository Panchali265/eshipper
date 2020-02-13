package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class WebNotificationDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WebNotificationDTO.class);
        WebNotificationDTO webNotificationDTO1 = new WebNotificationDTO();
        webNotificationDTO1.setId(1L);
        WebNotificationDTO webNotificationDTO2 = new WebNotificationDTO();
        assertThat(webNotificationDTO1).isNotEqualTo(webNotificationDTO2);
        webNotificationDTO2.setId(webNotificationDTO1.getId());
        assertThat(webNotificationDTO1).isEqualTo(webNotificationDTO2);
        webNotificationDTO2.setId(2L);
        assertThat(webNotificationDTO1).isNotEqualTo(webNotificationDTO2);
        webNotificationDTO1.setId(null);
        assertThat(webNotificationDTO1).isNotEqualTo(webNotificationDTO2);
    }
}

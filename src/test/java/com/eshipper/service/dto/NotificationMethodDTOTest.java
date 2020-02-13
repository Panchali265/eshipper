package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class NotificationMethodDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotificationMethodDTO.class);
        NotificationMethodDTO notificationMethodDTO1 = new NotificationMethodDTO();
        notificationMethodDTO1.setId(1L);
        NotificationMethodDTO notificationMethodDTO2 = new NotificationMethodDTO();
        assertThat(notificationMethodDTO1).isNotEqualTo(notificationMethodDTO2);
        notificationMethodDTO2.setId(notificationMethodDTO1.getId());
        assertThat(notificationMethodDTO1).isEqualTo(notificationMethodDTO2);
        notificationMethodDTO2.setId(2L);
        assertThat(notificationMethodDTO1).isNotEqualTo(notificationMethodDTO2);
        notificationMethodDTO1.setId(null);
        assertThat(notificationMethodDTO1).isNotEqualTo(notificationMethodDTO2);
    }
}

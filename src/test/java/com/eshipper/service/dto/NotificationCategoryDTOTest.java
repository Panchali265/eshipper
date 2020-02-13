package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class NotificationCategoryDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotificationCategoryDTO.class);
        NotificationCategoryDTO notificationCategoryDTO1 = new NotificationCategoryDTO();
        notificationCategoryDTO1.setId(1L);
        NotificationCategoryDTO notificationCategoryDTO2 = new NotificationCategoryDTO();
        assertThat(notificationCategoryDTO1).isNotEqualTo(notificationCategoryDTO2);
        notificationCategoryDTO2.setId(notificationCategoryDTO1.getId());
        assertThat(notificationCategoryDTO1).isEqualTo(notificationCategoryDTO2);
        notificationCategoryDTO2.setId(2L);
        assertThat(notificationCategoryDTO1).isNotEqualTo(notificationCategoryDTO2);
        notificationCategoryDTO1.setId(null);
        assertThat(notificationCategoryDTO1).isNotEqualTo(notificationCategoryDTO2);
    }
}

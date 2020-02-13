package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.NotificationTemplate} entity.
 */
public class NotificationTemplateDTO implements Serializable {

    private Long id;

    private String subject;

    private String description;


    private Long notificationMethodId;

    private Long notificationTypeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getNotificationMethodId() {
        return notificationMethodId;
    }

    public void setNotificationMethodId(Long notificationMethodId) {
        this.notificationMethodId = notificationMethodId;
    }

    public Long getNotificationTypeId() {
        return notificationTypeId;
    }

    public void setNotificationTypeId(Long notificationTypeId) {
        this.notificationTypeId = notificationTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NotificationTemplateDTO notificationTemplateDTO = (NotificationTemplateDTO) o;
        if (notificationTemplateDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), notificationTemplateDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NotificationTemplateDTO{" +
            "id=" + getId() +
            ", subject='" + getSubject() + "'" +
            ", description='" + getDescription() + "'" +
            ", notificationMethodId=" + getNotificationMethodId() +
            ", notificationTypeId=" + getNotificationTypeId() +
            "}";
    }
}

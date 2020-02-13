package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.EmailNotification} entity.
 */
public class EmailNotificationDTO implements Serializable {

    private Long id;

    private String toEmail;

    private String emailMap;


    private Long notificationTemplateId;

    private Long createdById;

    private Long toUserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getEmailMap() {
        return emailMap;
    }

    public void setEmailMap(String emailMap) {
        this.emailMap = emailMap;
    }

    public Long getNotificationTemplateId() {
        return notificationTemplateId;
    }

    public void setNotificationTemplateId(Long notificationTemplateId) {
        this.notificationTemplateId = notificationTemplateId;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long user1Id) {
        this.createdById = user1Id;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long user1Id) {
        this.toUserId = user1Id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EmailNotificationDTO emailNotificationDTO = (EmailNotificationDTO) o;
        if (emailNotificationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), emailNotificationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EmailNotificationDTO{" +
            "id=" + getId() +
            ", toEmail='" + getToEmail() + "'" +
            ", emailMap='" + getEmailMap() + "'" +
            ", notificationTemplateId=" + getNotificationTemplateId() +
            ", createdById=" + getCreatedById() +
            ", toUserId=" + getToUserId() +
            "}";
    }
}

package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.WebNotification} entity.
 */
public class WebNotificationDTO implements Serializable {

    private Long id;

    private String url;

    private Boolean isRead;

    private String webMap;


    private Long notificationTemplateId;

    private Long createdById;

    private Long toUserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean isIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public String getWebMap() {
        return webMap;
    }

    public void setWebMap(String webMap) {
        this.webMap = webMap;
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

        WebNotificationDTO webNotificationDTO = (WebNotificationDTO) o;
        if (webNotificationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), webNotificationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WebNotificationDTO{" +
            "id=" + getId() +
            ", url='" + getUrl() + "'" +
            ", isRead='" + isIsRead() + "'" +
            ", webMap='" + getWebMap() + "'" +
            ", notificationTemplateId=" + getNotificationTemplateId() +
            ", createdById=" + getCreatedById() +
            ", toUserId=" + getToUserId() +
            "}";
    }
}

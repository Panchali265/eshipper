package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.NotificationType} entity.
 */
public class NotificationTypeDTO implements Serializable {

    private Long id;

    private String name;

    private String enumName;


    private Long notificationCategoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnumName() {
        return enumName;
    }

    public void setEnumName(String enumName) {
        this.enumName = enumName;
    }

    public Long getNotificationCategoryId() {
        return notificationCategoryId;
    }

    public void setNotificationCategoryId(Long notificationCategoryId) {
        this.notificationCategoryId = notificationCategoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NotificationTypeDTO notificationTypeDTO = (NotificationTypeDTO) o;
        if (notificationTypeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), notificationTypeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NotificationTypeDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", enumName='" + getEnumName() + "'" +
            ", notificationCategoryId=" + getNotificationCategoryId() +
            "}";
    }
}

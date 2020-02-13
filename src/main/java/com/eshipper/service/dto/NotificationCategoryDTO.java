package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.NotificationCategory} entity.
 */
public class NotificationCategoryDTO implements Serializable {

    private Long id;

    private String name;

    private String role;


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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NotificationCategoryDTO notificationCategoryDTO = (NotificationCategoryDTO) o;
        if (notificationCategoryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), notificationCategoryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NotificationCategoryDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", role='" + getRole() + "'" +
            "}";
    }
}

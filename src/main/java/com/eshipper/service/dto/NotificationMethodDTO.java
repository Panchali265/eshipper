package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.NotificationMethod} entity.
 */
public class NotificationMethodDTO implements Serializable {

    private Long id;

    private String methodName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NotificationMethodDTO notificationMethodDTO = (NotificationMethodDTO) o;
        if (notificationMethodDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), notificationMethodDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NotificationMethodDTO{" +
            "id=" + getId() +
            ", methodName='" + getMethodName() + "'" +
            "}";
    }
}

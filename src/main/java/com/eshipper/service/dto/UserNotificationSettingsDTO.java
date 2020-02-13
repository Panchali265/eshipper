package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.UserNotificationSettings} entity.
 */
public class UserNotificationSettingsDTO implements Serializable {

    private Long id;

    private Boolean web;

    private Boolean email;

    private String configurableMap;


    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isWeb() {
        return web;
    }

    public void setWeb(Boolean web) {
        this.web = web;
    }

    public Boolean isEmail() {
        return email;
    }

    public void setEmail(Boolean email) {
        this.email = email;
    }

    public String getConfigurableMap() {
        return configurableMap;
    }

    public void setConfigurableMap(String configurableMap) {
        this.configurableMap = configurableMap;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long user1Id) {
        this.userId = user1Id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserNotificationSettingsDTO userNotificationSettingsDTO = (UserNotificationSettingsDTO) o;
        if (userNotificationSettingsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userNotificationSettingsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserNotificationSettingsDTO{" +
            "id=" + getId() +
            ", web='" + isWeb() + "'" +
            ", email='" + isEmail() + "'" +
            ", configurableMap='" + getConfigurableMap() + "'" +
            ", userId=" + getUserId() +
            "}";
    }
}

package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A UserNotificationSettings.
 */
@Entity
@Table(name = "user_notification_settings")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserNotificationSettings implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "web")
    private Boolean web;

    @Column(name = "email")
    private Boolean email;

    @Column(name = "configurable_map")
    private String configurableMap;

    @ManyToOne
    @JsonIgnoreProperties("userNotificationSettings")
    private User1 user;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isWeb() {
        return web;
    }

    public UserNotificationSettings web(Boolean web) {
        this.web = web;
        return this;
    }

    public void setWeb(Boolean web) {
        this.web = web;
    }

    public Boolean isEmail() {
        return email;
    }

    public UserNotificationSettings email(Boolean email) {
        this.email = email;
        return this;
    }

    public void setEmail(Boolean email) {
        this.email = email;
    }

    public String getConfigurableMap() {
        return configurableMap;
    }

    public UserNotificationSettings configurableMap(String configurableMap) {
        this.configurableMap = configurableMap;
        return this;
    }

    public void setConfigurableMap(String configurableMap) {
        this.configurableMap = configurableMap;
    }

    public User1 getUser() {
        return user;
    }

    public UserNotificationSettings user(User1 user1) {
        this.user = user1;
        return this;
    }

    public void setUser(User1 user1) {
        this.user = user1;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserNotificationSettings)) {
            return false;
        }
        return id != null && id.equals(((UserNotificationSettings) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "UserNotificationSettings{" +
            "id=" + getId() +
            ", web='" + isWeb() + "'" +
            ", email='" + isEmail() + "'" +
            ", configurableMap='" + getConfigurableMap() + "'" +
            "}";
    }
}

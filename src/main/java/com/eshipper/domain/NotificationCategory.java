package com.eshipper.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A NotificationCategory.
 */
@Entity
@Table(name = "notification_category")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NotificationCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "notificationCategory")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<NotificationType> notificationTypes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public NotificationCategory name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public NotificationCategory role(String role) {
        this.role = role;
        return this;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<NotificationType> getNotificationTypes() {
        return notificationTypes;
    }

    public NotificationCategory notificationTypes(Set<NotificationType> notificationTypes) {
        this.notificationTypes = notificationTypes;
        return this;
    }

    public NotificationCategory addNotificationType(NotificationType notificationType) {
        this.notificationTypes.add(notificationType);
        notificationType.setNotificationCategory(this);
        return this;
    }

    public NotificationCategory removeNotificationType(NotificationType notificationType) {
        this.notificationTypes.remove(notificationType);
        notificationType.setNotificationCategory(null);
        return this;
    }

    public void setNotificationTypes(Set<NotificationType> notificationTypes) {
        this.notificationTypes = notificationTypes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NotificationCategory)) {
            return false;
        }
        return id != null && id.equals(((NotificationCategory) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "NotificationCategory{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", role='" + getRole() + "'" +
            "}";
    }
}

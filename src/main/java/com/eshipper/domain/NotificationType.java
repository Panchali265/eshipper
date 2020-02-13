package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A NotificationType.
 */
@Entity
@Table(name = "notification_type")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NotificationType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "enum_name")
    private String enumName;

    @OneToMany(mappedBy = "notificationType")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<NotificationTemplate> notificationTemplates = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("notificationTypes")
    private NotificationCategory notificationCategory;

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

    public NotificationType name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnumName() {
        return enumName;
    }

    public NotificationType enumName(String enumName) {
        this.enumName = enumName;
        return this;
    }

    public void setEnumName(String enumName) {
        this.enumName = enumName;
    }

    public Set<NotificationTemplate> getNotificationTemplates() {
        return notificationTemplates;
    }

    public NotificationType notificationTemplates(Set<NotificationTemplate> notificationTemplates) {
        this.notificationTemplates = notificationTemplates;
        return this;
    }

    public NotificationType addNotificationTemplate(NotificationTemplate notificationTemplate) {
        this.notificationTemplates.add(notificationTemplate);
        notificationTemplate.setNotificationType(this);
        return this;
    }

    public NotificationType removeNotificationTemplate(NotificationTemplate notificationTemplate) {
        this.notificationTemplates.remove(notificationTemplate);
        notificationTemplate.setNotificationType(null);
        return this;
    }

    public void setNotificationTemplates(Set<NotificationTemplate> notificationTemplates) {
        this.notificationTemplates = notificationTemplates;
    }

    public NotificationCategory getNotificationCategory() {
        return notificationCategory;
    }

    public NotificationType notificationCategory(NotificationCategory notificationCategory) {
        this.notificationCategory = notificationCategory;
        return this;
    }

    public void setNotificationCategory(NotificationCategory notificationCategory) {
        this.notificationCategory = notificationCategory;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NotificationType)) {
            return false;
        }
        return id != null && id.equals(((NotificationType) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "NotificationType{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", enumName='" + getEnumName() + "'" +
            "}";
    }
}

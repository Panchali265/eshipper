package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A NotificationTemplate.
 */
@Entity
@Table(name = "notification_template")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NotificationTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subject")
    private String subject;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JsonIgnoreProperties("notificationTemplates")
    private NotificationMethod notificationMethod;

    @ManyToOne
    @JsonIgnoreProperties("notificationTemplates")
    private NotificationType notificationType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public NotificationTemplate subject(String subject) {
        this.subject = subject;
        return this;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public NotificationTemplate description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public NotificationMethod getNotificationMethod() {
        return notificationMethod;
    }

    public NotificationTemplate notificationMethod(NotificationMethod notificationMethod) {
        this.notificationMethod = notificationMethod;
        return this;
    }

    public void setNotificationMethod(NotificationMethod notificationMethod) {
        this.notificationMethod = notificationMethod;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public NotificationTemplate notificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
        return this;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NotificationTemplate)) {
            return false;
        }
        return id != null && id.equals(((NotificationTemplate) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "NotificationTemplate{" +
            "id=" + getId() +
            ", subject='" + getSubject() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}

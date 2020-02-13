package com.eshipper.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A NotificationMethod.
 */
@Entity
@Table(name = "notification_method")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NotificationMethod implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "method_name")
    private String methodName;

    @OneToMany(mappedBy = "notificationMethod")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<NotificationTemplate> notificationTemplates = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMethodName() {
        return methodName;
    }

    public NotificationMethod methodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Set<NotificationTemplate> getNotificationTemplates() {
        return notificationTemplates;
    }

    public NotificationMethod notificationTemplates(Set<NotificationTemplate> notificationTemplates) {
        this.notificationTemplates = notificationTemplates;
        return this;
    }

    public NotificationMethod addNotificationTemplate(NotificationTemplate notificationTemplate) {
        this.notificationTemplates.add(notificationTemplate);
        notificationTemplate.setNotificationMethod(this);
        return this;
    }

    public NotificationMethod removeNotificationTemplate(NotificationTemplate notificationTemplate) {
        this.notificationTemplates.remove(notificationTemplate);
        notificationTemplate.setNotificationMethod(null);
        return this;
    }

    public void setNotificationTemplates(Set<NotificationTemplate> notificationTemplates) {
        this.notificationTemplates = notificationTemplates;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NotificationMethod)) {
            return false;
        }
        return id != null && id.equals(((NotificationMethod) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "NotificationMethod{" +
            "id=" + getId() +
            ", methodName='" + getMethodName() + "'" +
            "}";
    }
}

package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A WebNotification.
 */
@Entity
@Table(name = "web_notification")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WebNotification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url")
    private String url;

    @Column(name = "is_read")
    private Boolean isRead;

    @Column(name = "web_map")
    private String webMap;

    @OneToOne
    @JoinColumn(unique = true)
    private NotificationTemplate notificationTemplate;

    @ManyToOne
    @JsonIgnoreProperties("webNotifications")
    private User1 createdBy;

    @ManyToOne
    @JsonIgnoreProperties("webNotifications")
    private User1 toUser;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public WebNotification url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean isIsRead() {
        return isRead;
    }

    public WebNotification isRead(Boolean isRead) {
        this.isRead = isRead;
        return this;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public String getWebMap() {
        return webMap;
    }

    public WebNotification webMap(String webMap) {
        this.webMap = webMap;
        return this;
    }

    public void setWebMap(String webMap) {
        this.webMap = webMap;
    }

    public NotificationTemplate getNotificationTemplate() {
        return notificationTemplate;
    }

    public WebNotification notificationTemplate(NotificationTemplate notificationTemplate) {
        this.notificationTemplate = notificationTemplate;
        return this;
    }

    public void setNotificationTemplate(NotificationTemplate notificationTemplate) {
        this.notificationTemplate = notificationTemplate;
    }

    public User1 getCreatedBy() {
        return createdBy;
    }

    public WebNotification createdBy(User1 user1) {
        this.createdBy = user1;
        return this;
    }

    public void setCreatedBy(User1 user1) {
        this.createdBy = user1;
    }

    public User1 getToUser() {
        return toUser;
    }

    public WebNotification toUser(User1 user1) {
        this.toUser = user1;
        return this;
    }

    public void setToUser(User1 user1) {
        this.toUser = user1;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WebNotification)) {
            return false;
        }
        return id != null && id.equals(((WebNotification) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WebNotification{" +
            "id=" + getId() +
            ", url='" + getUrl() + "'" +
            ", isRead='" + isIsRead() + "'" +
            ", webMap='" + getWebMap() + "'" +
            "}";
    }
}

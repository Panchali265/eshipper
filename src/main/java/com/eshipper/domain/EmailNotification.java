package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A EmailNotification.
 */
@Entity
@Table(name = "email_notification")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EmailNotification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "to_email")
    private String toEmail;

    @Column(name = "email_map")
    private String emailMap;

    @OneToOne
    @JoinColumn(unique = true)
    private NotificationTemplate notificationTemplate;

    @ManyToOne
    @JsonIgnoreProperties("emailNotifications")
    private User1 createdBy;

    @ManyToOne
    @JsonIgnoreProperties("emailNotifications")
    private User1 toUser;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToEmail() {
        return toEmail;
    }

    public EmailNotification toEmail(String toEmail) {
        this.toEmail = toEmail;
        return this;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getEmailMap() {
        return emailMap;
    }

    public EmailNotification emailMap(String emailMap) {
        this.emailMap = emailMap;
        return this;
    }

    public void setEmailMap(String emailMap) {
        this.emailMap = emailMap;
    }

    public NotificationTemplate getNotificationTemplate() {
        return notificationTemplate;
    }

    public EmailNotification notificationTemplate(NotificationTemplate notificationTemplate) {
        this.notificationTemplate = notificationTemplate;
        return this;
    }

    public void setNotificationTemplate(NotificationTemplate notificationTemplate) {
        this.notificationTemplate = notificationTemplate;
    }

    public User1 getCreatedBy() {
        return createdBy;
    }

    public EmailNotification createdBy(User1 user1) {
        this.createdBy = user1;
        return this;
    }

    public void setCreatedBy(User1 user1) {
        this.createdBy = user1;
    }

    public User1 getToUser() {
        return toUser;
    }

    public EmailNotification toUser(User1 user1) {
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
        if (!(o instanceof EmailNotification)) {
            return false;
        }
        return id != null && id.equals(((EmailNotification) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EmailNotification{" +
            "id=" + getId() +
            ", toEmail='" + getToEmail() + "'" +
            ", emailMap='" + getEmailMap() + "'" +
            "}";
    }
}

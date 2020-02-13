package com.eshipper.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A User1.
 */
@Entity
@Table(name = "user_1")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User1 implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "createdBy")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<WebNotification> webNotifications = new HashSet<>();

    @OneToMany(mappedBy = "toUser")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<WebNotification> webNotifications = new HashSet<>();

    @OneToMany(mappedBy = "createdBy")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<EmailNotification> emailNotifications = new HashSet<>();

    @OneToMany(mappedBy = "toUser")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<EmailNotification> emailNotifications = new HashSet<>();

    @OneToMany(mappedBy = "user")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<UserNotificationSettings> userNotificationSettings = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<WebNotification> getWebNotifications() {
        return webNotifications;
    }

    public User1 webNotifications(Set<WebNotification> webNotifications) {
        this.webNotifications = webNotifications;
        return this;
    }

    public User1 addWebNotification(WebNotification webNotification) {
        this.webNotifications.add(webNotification);
        webNotification.setCreatedBy(this);
        return this;
    }

    public User1 removeWebNotification(WebNotification webNotification) {
        this.webNotifications.remove(webNotification);
        webNotification.setCreatedBy(null);
        return this;
    }

    public void setWebNotifications(Set<WebNotification> webNotifications) {
        this.webNotifications = webNotifications;
    }

    public Set<WebNotification> getWebNotifications() {
        return webNotifications;
    }

    public User1 webNotifications(Set<WebNotification> webNotifications) {
        this.webNotifications = webNotifications;
        return this;
    }

    public User1 addWebNotification(WebNotification webNotification) {
        this.webNotifications.add(webNotification);
        webNotification.setToUser(this);
        return this;
    }

    public User1 removeWebNotification(WebNotification webNotification) {
        this.webNotifications.remove(webNotification);
        webNotification.setToUser(null);
        return this;
    }

    public void setWebNotifications(Set<WebNotification> webNotifications) {
        this.webNotifications = webNotifications;
    }

    public Set<EmailNotification> getEmailNotifications() {
        return emailNotifications;
    }

    public User1 emailNotifications(Set<EmailNotification> emailNotifications) {
        this.emailNotifications = emailNotifications;
        return this;
    }

    public User1 addEmailNotification(EmailNotification emailNotification) {
        this.emailNotifications.add(emailNotification);
        emailNotification.setCreatedBy(this);
        return this;
    }

    public User1 removeEmailNotification(EmailNotification emailNotification) {
        this.emailNotifications.remove(emailNotification);
        emailNotification.setCreatedBy(null);
        return this;
    }

    public void setEmailNotifications(Set<EmailNotification> emailNotifications) {
        this.emailNotifications = emailNotifications;
    }

    public Set<EmailNotification> getEmailNotifications() {
        return emailNotifications;
    }

    public User1 emailNotifications(Set<EmailNotification> emailNotifications) {
        this.emailNotifications = emailNotifications;
        return this;
    }

    public User1 addEmailNotification(EmailNotification emailNotification) {
        this.emailNotifications.add(emailNotification);
        emailNotification.setToUser(this);
        return this;
    }

    public User1 removeEmailNotification(EmailNotification emailNotification) {
        this.emailNotifications.remove(emailNotification);
        emailNotification.setToUser(null);
        return this;
    }

    public void setEmailNotifications(Set<EmailNotification> emailNotifications) {
        this.emailNotifications = emailNotifications;
    }

    public Set<UserNotificationSettings> getUserNotificationSettings() {
        return userNotificationSettings;
    }

    public User1 userNotificationSettings(Set<UserNotificationSettings> userNotificationSettings) {
        this.userNotificationSettings = userNotificationSettings;
        return this;
    }

    public User1 addUserNotificationSettings(UserNotificationSettings userNotificationSettings) {
        this.userNotificationSettings.add(userNotificationSettings);
        userNotificationSettings.setUser(this);
        return this;
    }

    public User1 removeUserNotificationSettings(UserNotificationSettings userNotificationSettings) {
        this.userNotificationSettings.remove(userNotificationSettings);
        userNotificationSettings.setUser(null);
        return this;
    }

    public void setUserNotificationSettings(Set<UserNotificationSettings> userNotificationSettings) {
        this.userNotificationSettings = userNotificationSettings;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User1)) {
            return false;
        }
        return id != null && id.equals(((User1) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "User1{" +
            "id=" + getId() +
            "}";
    }
}

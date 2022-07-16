package com.example.artstore.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NOTIFICATIONS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserNotification {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CONTENT")
    private String content;

    public static UserNotificationBuilder builder() {
        return new UserNotificationBuilder();
    }

    public static class UserNotificationBuilder {
        private Long id;
        private String content;

        UserNotificationBuilder() {
        }

        public UserNotificationBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        public UserNotificationBuilder content(final String content) {
            this.content = content;
            return this;
        }

        public UserNotification build() {
            return new UserNotification(this.id, this.content);
        }

        public String toString() {
            return "UserNotification.UserNotificationBuilder(id=" + this.id + ", content=" + this.content + ")";
        }
    }
}

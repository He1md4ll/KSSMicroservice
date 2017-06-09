package edu.hs.bremen.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotEmpty
    @Column(unique = true)
    private String uuid;

    private User() {}

    public String getUuid() {
        return uuid;
    }

    private void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public static class UserBuilder {
        private User user = new User();

        public UserBuilder withUuid(String uuid) {
            user.setUuid(uuid);
            return this;
        }

        public User build() {
            return user;
        }
    }
}
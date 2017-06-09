package edu.hs.bremen.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotEmpty
    @Column(unique = true)
    private String uuid;

    private UserEntity() {}

    public String getUuid() {
        return uuid;
    }

    private void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public static class UserBuilder {
        private UserEntity userEntity = new UserEntity();

        public UserBuilder withUuid(String uuid) {
            userEntity.setUuid(uuid);
            return this;
        }

        public UserEntity build() {
            return userEntity;
        }
    }
}
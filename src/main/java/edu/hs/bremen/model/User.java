package edu.hs.bremen.model;

public class User {
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
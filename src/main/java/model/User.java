package models;

import java.util.UUID;

public class User {
    private String username;
    private String uuid;

    public User(String username) {
        this.username = username;
        this.uuid = UUID.randomUUID().toString();
    }

    public String getUsername() {
        return username;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}

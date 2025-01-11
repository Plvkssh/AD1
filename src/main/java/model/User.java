package model;

import java.util.UUID;

public class User {
    private final UUID userId;
    
    public User() {
        this.userId = UUID.randomUUID();
    }
    
    public UUID getUserId() {
        return userId;
    }
}

package model;

import java.time.LocalDateTime;

public class ShortenedLink {
    private String originalUrl;
    private String shortenedUrl;
    private int remainingClicks;
    private LocalDateTime expiryDate;
    private User owner;

    public ShortenedLink(String originalUrl, String shortenedUrl, int remainingClicks, LocalDateTime expiryDate, User owner) {
        this.originalUrl = originalUrl;
        this.shortenedUrl = shortenedUrl;
        this.remainingClicks = remainingClicks;
        this.expiryDate = expiryDate;
        this.owner = owner;
    }

    // Getters and setters
    // ...
}

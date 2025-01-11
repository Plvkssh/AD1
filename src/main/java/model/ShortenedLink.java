package model;

import java.time.LocalDateTime;

public class ShortenedLink {
    private final String originalUrl;
    private final String shortUrl;
    private final User owner;
    private LocalDateTime expiryDate;
    private int remainingClicks;

    public ShortenedLink(String originalUrl, String shortUrl, User owner, LocalDateTime expiryDate, int remainingClicks) {
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
        this.owner = owner;
        this.expiryDate = expiryDate;
        this.remainingClicks = remainingClicks;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public User getOwner() {
        return owner;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getRemainingClicks() {
        return remainingClicks;
    }

    public void setRemainingClicks(int remainingClicks) {
        this.remainingClicks = remainingClicks;
    }
}

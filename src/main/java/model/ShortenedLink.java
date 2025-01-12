package model;

import java.time.LocalDateTime;

public class ShortenedLink {
    private String originalUrl;
    private String shortUrl;
    private User owner;
    private LocalDateTime expiryDate;
    private int remainingClicks;

    // Конструктор
    public ShortenedLink(String originalUrl, String shortUrl, User owner, LocalDateTime expiryDate, int remainingClicks) {
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
        this.owner = owner;
        this.expiryDate = expiryDate; 
        this.remainingClicks = remainingClicks;
    }

    // Геттеры
    public String getOriginalUrl() {
        return this.originalUrl;
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

    // Сеттеры
    public void setExpiryDate(LocalDateTime expiryDate) {
        if (expiryDate.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Дата истечения не может быть в прошлом");
        }
        this.expiryDate = expiryDate;
    }

    public int getRemainingClicks() {
        return remainingClicks;
    }

    public void setRemainingClicks(int remainingClicks) {
        if (remainingClicks < 0) {
            System.out.println("Остаток кликов не может быть отрицательным."); 
        } else {
            this.remainingClicks = remainingClicks;
        }
    }

    // Метод toString — для вывода
    @Override
    public String toString() {
        return "ShortenedLink{" +
                "originalUrl='" + originalUrl + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                ", owner=" + owner +
                ", expiryDate=" + expiryDate +
                ", remainingClicks=" + remainingClicks +
                '}';
    }

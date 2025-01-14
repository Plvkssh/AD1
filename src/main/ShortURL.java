package src;

import java.time.LocalDateTime;

public class ShortURL {
    // Оригинальный длинный URL
    private String originalURL;
    // Сгенерированный короткий URL
    private String shortURL;
    // Количество оставшихся переходов
    private int remainingClicks;
    // Время создания ссылки
    private LocalDateTime creationTime;
    // Время жизни ссылки в часах
    private static final int URL_LIFETIME_HOURS = 24;

    public ShortURL(String originalURL, String shortURL, int maxClicks) {
        this.originalURL = originalURL;
        this.shortURL = shortURL;
        this.remainingClicks = maxClicks;
        this.creationTime = LocalDateTime.now();
    }

    public String getOriginalURL() {
        return originalURL;
    }

    public String getShortURL() {
        return shortURL;
    }

    public int getRemainingClicks() {
        return remainingClicks;
    }

    public void decrementClicks() {
        if (remainingClicks > 0) {
            remainingClicks--;
        }
    }

    public boolean isExpired() {
        LocalDateTime expiryTime = creationTime.plusHours(URL_LIFETIME_HOURS);
        return LocalDateTime.now().isAfter(expiryTime);
    }

    public boolean isAvailable() {
        return remainingClicks > 0 && !isExpired();
    }
}

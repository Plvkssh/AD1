package service;

import model.ShortenedLink;
import model.User;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ShorteningService {
    private final Map<String, ShortenedLink> urlStorage = new HashMap<>();

    public String shortenUrl(String originalUrl, User user, int clickLimit) {
        String shortUrl = generateShortUrl();

        int effectiveClickLimit = Math.min(clickLimit, util.ConfigLoader.getDefaultClickLimit());
        LocalDateTime expiryDate = LocalDateTime.now().plusDays(util.ConfigLoader.getDefaultExpiryDays());

        ShortenedLink link = new ShortenedLink(originalUrl, shortUrl, user, expiryDate, effectiveClickLimit);
        urlStorage.put(shortUrl, link);
        return shortUrl;
    }

    public void cleanExpiredLinks() {
        urlStorage.entrySet().removeIf(entryLocalDateTime.now().isAfter(entry.getValue().getExpiryDate())
        );
    }

    public void updateClickLimit(String shortUrl, int newLimit, User user) throws Exception {
        ShortenedLink link = urlStorage.get(shortUrl);
        if (link == null || !link.getOwner().equals(user)) {
            throw new Exception("У вас нет прав на изменение этой ссылки.");
        }
        link.setRemainingClicks(newLimit);
    }

    public void deleteLink(String shortUrl, User user) throws Exception {
        ShortenedLink link = urlStorage.get(shortUrl);
        if (link == null || !link.getOwner().equals(user)) {
            throw new Exception("У вас нет прав на удаление этой ссылки.");
        }
        urlStorage.remove(shortUrl);
    }

    private String generateShortUrl() {
        return "short" + System.currentTimeMillis(); // Простая генерация ссылки
    }
}

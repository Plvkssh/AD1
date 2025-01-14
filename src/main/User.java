package src;

import java.util.ArrayList;
import java.util.UUID;

public class User {
    // Уникальный идентификатор пользователя
    private UUID uuid;
    // Список сокращенных ссылок пользователя
    private ArrayList<ShortURL> urlList;

    public User() {
        this.uuid = UUID.randomUUID();
        this.urlList = new ArrayList<>();
    }

    public UUID getUuid() {
        return uuid;
    }

    public ArrayList<ShortURL> getUrlList() {
        return urlList;
    }

    public void addShortURL(ShortURL shortURL) {
        urlList.add(shortURL);
    }
}

### ShortURL.java

Java

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

### URLService.java

Java

package src;

import java.util.HashMap;
import java.util.UUID;

public class URLService {
    // Хранение всех пользователей по UUID
    private HashMap<UUID, User> users;
    // Хранение всех коротких ссылок
    private HashMap<String, ShortURL> urlMap;

    public URLService() {
        users = new HashMap<>();
        urlMap = new HashMap<>();
    }

    // Генерация короткой ссылки
    public String generateShortURL(String originalURL, User user, int maxClicks) {
        // Уникальная строка для короткой ссылки
        String shortCode = UUID.randomUUID().toString().substring(0, 6);
        String shortURL = "clck.ru/" + shortCode;

        ShortURL newShortURL = new ShortURL(originalURL, shortURL, maxClicks);
        user.addShortURL(newShortURL);
        urlMap.put(shortURL, newShortURL);

        return shortURL;
    }

    // Получение оригинального URL по короткой ссылке
    public String getOriginalURL(String shortURL) {
        ShortURL shortLink = urlMap.get(shortURL);
        if (shortLink != null && shortLink.isAvailable()) {
            shortLink.decrementClicks();
            return shortLink.getOriginalURL();
        } else {
            return null;
        }
    }

    // Удаление просроченных ссылок
    public void removeExpiredLinks() {
      urlMap.values().removeIf(shortURL -> shortURL.isExpired());
    }

    // Получение или создание пользователя по UUID
    public User getUser(UUID uuid) {
        return users.computeIfAbsent(uuid, k -> new User());
    }
}

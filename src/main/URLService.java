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

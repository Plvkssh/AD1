package service;

import model.ShortenedLink;
import model.User;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ShorteningService {
    private final Map<String, ShortenedLink> urlStorage = new HashMap<>();

    // Метод для сокращения URL
    public String shortenUrl(String originalUrl, User user, int clickLimit) {
        // Генерируем "короткую" ссылку (на самом деле просто заглушка)
        String shortUrl = generateShortUrl();

        // Учитываем лимиты и время истечения из конфигурации
        int effectiveClickLimit = Math.min(clickLimit, util.ConfigLoader.getDefaultClickLimit());
        LocalDateTime expiryDate = LocalDateTime.now()
                .plusDays(util.ConfigLoader.getDefaultExpiryDays());

        // Создаем объект ссылки
        ShortenedLink link = new ShortenedLink(originalUrl, shortUrl, user, expiryDate, effectiveClickLimit);

        // Сохраняем её в хранилище
        urlStorage.put(shortUrl, link);

        // Возвращаем саму короткую ссылку
        return shortUrl;
    }

    // Метод для очистки устаревших ссылок
    public void cleanExpiredLinks() {
        urlStorage.entrySet().removeIf(entry -> {
            LocalDateTime expiryDate = entry.getValue().getExpiryDate();
            return LocalDateTime.now().isAfter(expiryDate); // Удаляем, если срок вышел
        });
    }

    // Изменение лимита кликов
    public void updateClickLimit(String shortUrl, int newLimit, User user) throws Exception {
        ShortenedLink link = urlStorage.get(shortUrl);

        if (link == null) {
            throw new Exception("Ссылка не найдена.");
        }

        if (!link.getOwner().equals(user)) {
            throw new Exception("У вас нет прав на изменение этой ссылки.");
        }

        // Обновляем лимит кликов
        link.setRemainingClicks(newLimit);
    }

    // Удаление короткой ссылки
    public void deleteLink(String shortUrl, User user) throws Exception {
        ShortenedLink link = urlStorage.get(shortUrl);

        if (link == null) {
            throw new Exception("Ссылка не найдена.");
        }

        if (!link.getOwner().equals(user)) {
            throw new Exception("У вас нет прав на удаление этой ссылки.");
        }

        // Удаляем ссылку из хранилища
        urlStorage.remove(shortUrl);
    }

    // Простая генерация короткой ссылки
    private String generateShortUrl() {
        return "short-" + (int)(Math.random() * 100000);
    }
}

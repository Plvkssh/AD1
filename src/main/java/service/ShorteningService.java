package service;

import model.ShortenedLink;
import model.User;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ShorteningService {
    private final Map<String, ShortenedLink> urlStorage = new HashMap<>();
    private static final String DOMAIN = "clck.ru/";

    public String shortenUrl(String originalUrl, User user, int clickLimit) {
        String shortUrl = DOMAIN + generateRandomCode();
        LocalDateTime expiryDate = LocalDateTime.now().plusDays(1); // Срок жизни — 1 сутки

        ShortenedLink link = new ShortenedLink(originalUrl, shortUrl, clickLimit, expiryDate, user);
        urlStorage.put(shortUrl, link);
        return shortUrl;
    }

    public String redirect(String shortUrl) throws Exception {
        ShortenedLink link = urlStorage.get(shortUrl);

        if (link == null || link.getRemainingClicks() <= 0 || LocalDateTime.now().isAfter(link.getExpiryDate())) {
            throw new Exception("Ссылка истекла или недоступна.");
        }

        link.setRemainingClicks(link.getRemainingClicks() - 1);

        if (link.getRemainingClicks() == 0) {
            notifyUser(link.getOwner());
        }
        
        return link.getOriginalUrl();
    }

    private String generateRandomCode() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) { // Сгенерируем код длиной 6 символов
            code.append(chars.charAt(random.nextInt(chars.length())));
        }
        return code.toString();
    }

    private void notifyUser(User user) {
        System.out.println("Уведомление для пользователя " + user.getUserId() + ": ссылка недоступна.");
    }
}

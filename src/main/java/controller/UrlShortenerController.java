package controller;

import service.ShorteningService;
import java.awt.Desktop;
import java.net.URI;
import java.util.Scanner;

public class UrlShortenerController {
    private final ShorteningService service = new ShorteningService();

    public void start() throws Exception {
        Scanner scanner = new Scanner(System.in);
        User user = new User();
        System.out.println("Приветствую! Ваш UUID: " + user.getUserId());

        while (true) {
            System.out.println("\n1. Сократить ссылку\n2. Перейти по сокращенной ссылке\n3. Выйти");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    handleUrlShortening(scanner, user);
                    break;
                case "2":
                    handleUrlRedirect(scanner);
                    break;
                case "3":
                    System.out.println("Выход...");
                    return;
                default:
                    System.out.println("Неверный ввод.");
                    break;
            }
        }
    }

    private void handleUrlShortening(Scanner scanner, User user) throws Exception {
        System.out.println("Введите оригинальный URL:");
        String originalUrl = scanner.nextLine();

        if (!UrlValidator.isValid(originalUrl)) {
            System.out.println("Ошибка! Введен некорректный URL.");
            return;
        }

        System.out.println("Введите лимит переходов:");
        int limit = Integer.parseInt(scanner.nextLine());

        String shortUrl = service.shortenUrl(originalUrl, user, limit);
        System.out.println("Сокращенная ссылка: " + shortUrl);
    }

    private void handleUrlRedirect(Scanner scanner) throws Exception {
        System.out.println("Введите короткую ссылку:");
        String shortUrl = scanner.nextLine();

        try {
            String originalUrl = service.redirect(shortUrl);
            Desktop.getDesktop().browse(new URI(originalUrl));
            System.out.println("Переход совершен.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

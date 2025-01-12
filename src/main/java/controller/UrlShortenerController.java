package controller;

import service.ShorteningService;
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

            if ("1".equals(option)) {
                System.out.println("Введите оригинальный URL:");
                String originalUrl = scanner.nextLine();

                if (!UrlValidator.isValid(originalUrl)) {
                    System.out.println("Ошибка! Введен некорректный URL.");
                    continue;
                }

                System.out.println("Введите лимит переходов:");
                int limit = Integer.parseInt(scanner.nextLine());

                String shortUrl = service.shortenUrl(originalUrl, user, limit);
                System.out.println("Сокращенная ссылка: " + shortUrl);

            } else if ("2".equals(option)) {
                System.out.println("Введите короткую ссылку:");
                String shortUrl = scanner.nextLine();

                try {
                    String originalUrl = service.redirect(shortUrl);
                    Desktop.getDesktop().browse(new URI(originalUrl));
                    System.out.println("Переход совершен.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            } else if ("3".equals(option)) {
                System.out.println("Выход...");
                break;
            } else {
                System.out.println("Неверный ввод.");
            }
        }
    }

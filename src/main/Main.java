package src;

import java.awt.Desktop;
import java.net.URI;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        URLService service = new URLService();
        Scanner scanner = new Scanner(System.in);
        User user = new User();

        System.out.println("Ваш UUID: " + user.getUuid());

        while (true) {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Сократить ссылку");
            System.out.println("2. Перейти по короткой ссылке");
            System.out.println("3. Выйти");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    // Сокращение ссылки
                    System.out.print("Введите оригинальный URL: ");
                    String originalURL = scanner.nextLine();

                    System.out.print("Введите максимальное количество переходов: ");
                    int maxClicks = Integer.parseInt(scanner.nextLine());

                    String shortURL = service.generateShortURL(originalURL, user, maxClicks);
                    System.out.println("Короткая ссылка: " + shortURL);
                    break;

                case "2":
                    // Переход по короткой ссылке
                    System.out.print("Введите короткую ссылку: ");
                    String inputShortURL = scanner.nextLine();

                    String redirectURL = service.getOriginalURL(inputShortURL);

                    if (redirectURL != null) {
                        System.out.println("Переходим по ссылке: " + redirectURL);
                        try {
                            Desktop.getDesktop().browse(new URI(redirectURL));
                        } catch (Exception e) {
                            System.out.println("Не удалось открыть браузер.");
                        }
                    } else {
                        System.out.println("Ссылка недоступна (лимит исчерпан или срок действия истек).");
                    }
                    break;

                case "3":
                    System.out.println("Выход из программы.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Некорректный выбор. Пожалуйста, попробуйте снова.");
                    break;
            }

            // Удаление просроченных ссылок
            service.removeExpiredLinks();
        }
    }
}

package main;

import controller.UrlShortenerController;
import service.ShorteningService;

public class Main {
    public static void main(String[] args) {
        // Инициализация сервиса и контроллера
        ShorteningService shorteningService = new ShorteningService();
        UrlShortenerController controller = new UrlShortenerController(shorteningService);

        // Запуск программы
        controller.start();
    }
}

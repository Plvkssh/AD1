package controller;

import service.ShorteningService;
import java.util.Scanner;

public class UrlShortenerController {
    private final ShorteningService service;

    public UrlShortenerController(ShorteningService service) {
        this.service = service;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать в URL Shortener!");
        // Добавьте логику интерфейса взаимодействия с пользователем
    }
}

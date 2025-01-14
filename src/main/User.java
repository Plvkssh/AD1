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

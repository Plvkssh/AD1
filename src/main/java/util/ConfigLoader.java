package util;

import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private static final Properties properties = new Properties();

    static {
        try {
            // Загружаем конфигурацию из файла
            properties.load(ConfigLoader.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            // Бросаем RuntimeException с понятным сообщением
            throw new RuntimeException("Не удалось загрузить конфигурационный файл!", e);
        }
    }

    // Получаем значение срока действия
    public static int getDefaultExpiryDays() {
        return Integer.parseInt(properties.getProperty("defaultExpiryDays", "1"));
    }

    // Получаем значение лимита кликов
    public static int getDefaultClickLimit() {
        return Integer.parseInt(properties.getProperty("defaultClickLimit", "10"));
    }
}

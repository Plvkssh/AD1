package util;

import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(ConfigLoader.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить конфигурацию!", e);
        }
    }

    public static int getDefaultExpiryDays() {
        return Integer.parseInt(properties.getProperty("defaultExpiryDays", "1"));
    }

    public static int getDefaultClickLimit() {
        return Integer.parseInt(properties.getProperty("defaultClickLimit", "10"));
    }
}

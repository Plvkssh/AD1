import controller.UrlShortenerController;

public class Main {
    public static void main(String[] args) {
        try {
            new UrlShortenerController().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

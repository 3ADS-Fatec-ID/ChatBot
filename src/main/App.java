package main;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import services.Telegram;

/**
 * Application class.
 *
 * @author joao
 */
public class App {

    /**
     * Bootstrap the app.
     *
     * @param args
     * @throws InterruptedException
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws InterruptedException, java.io.IOException {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Telegram());
        } catch (TelegramApiException e) {
            System.err.println(e.toString());
        }
    }
}

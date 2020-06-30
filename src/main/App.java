package main;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import services.Telegram;

public class App {

    public static void main(String[] args) throws InterruptedException, java.io.IOException {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Telegram());

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}

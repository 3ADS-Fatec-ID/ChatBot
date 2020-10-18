package main;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import services.Telegram;
import services.Http;
import spark.*;

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
        
        Spark.port(4567);
        
        Spark.options("/*",
                (request, response) -> {

                    String accessControlRequestHeaders = request
                            .headers("Access-Control-Request-Headers");
                    if (accessControlRequestHeaders != null) {
                        response.header("Access-Control-Allow-Headers",
                                accessControlRequestHeaders);
                    }

                    String accessControlRequestMethod = request
                            .headers("Access-Control-Request-Method");
                    if (accessControlRequestMethod != null) {
                        response.header("Access-Control-Allow-Methods",
                                accessControlRequestMethod);
                    }

                    return "OK";
                });

        Spark.before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
        
        Spark.post("/messages", Http.messages);
        Spark.post("/audios", "multipart/form-data", Http.audios);
        Spark.post("/start", Http.start);
        
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Telegram());
        } catch (TelegramApiException e) {
            System.err.println(e.toString());
        }
    }
}

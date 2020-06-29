package teste;

import io.github.crew102.rapidrake.RakeAlgorithm;
import io.github.crew102.rapidrake.data.SmartWords;
import io.github.crew102.rapidrake.model.RakeParams;
import io.github.crew102.rapidrake.model.Result;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import services.Telegram;

public class App {

    public static void main(String[] args) throws InterruptedException, java.io.IOException {

        // Create an object to hold algorithm parameters
        String[] stopWords = new SmartWords().getSmartWords();
        String[] stopPOS = {"VB", "VBD", "VBG", "VBN", "VBP", "VBZ"};
        int minWordChar = 1;
        boolean shouldStem = true;
        String phraseDelims = "[-,.?():;\"!/]";
        RakeParams params = new RakeParams(stopWords, stopPOS, minWordChar, shouldStem, phraseDelims);

        // Create a RakeAlgorithm object
        String POStaggerURL = "src/lib/pt-pos-maxent.bin"; // The path to your POS tagging model
        String SentDetecURL = "src/lib/pt-sent.bin"; // The path to your sentence detection model
        RakeAlgorithm rakeAlg = new RakeAlgorithm(params, POStaggerURL, SentDetecURL);

        // Call the rake method
        String txt = "Como fazer o cabeçalho do meu TCC?";
        Result result = rakeAlg.rake(txt);

        System.out.println(result.distinct());

//        ApiContextInitializer.init();
//        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
//        try {
//            telegramBotsApi.registerBot(new Telegram());
//
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
    }
}

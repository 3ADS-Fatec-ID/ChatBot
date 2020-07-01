package services;

import intent.main.MainIntent;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Voice;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileNotFoundException;
import org.json.JSONObject;

/**
 * Bot class. Forwards all the messages to the MainIntent.
 *
 * @author joao
 */
public class Telegram extends TelegramLongPollingBot {

    private final static String JSON_PATH = "./src/lib/telegram.json";
    private String name, token;

    public Telegram() {
        try (FileReader reader = new FileReader(JSON_PATH)) {
            JsonParser jsonParser = new JsonParser();
            JsonObject obj = jsonParser.parse(reader).getAsJsonObject();

            name = obj.get("name").getAsString();
            token = obj.get("token").getAsString();
        } catch (FileNotFoundException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    /**
     *
     * @param update
     */
    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();

        String messageText = message.getText();

        /**
         * If the given message is an Audio, we'll download it and send to be
         * parsed by the Watson Speech to Text API
         */
        if (message.getVoice() != null) {
            try {
                Voice voice = message.getVoice();
                URL url = new URL("https://api.telegram.org/bot" + getBotToken() + "/getFile?file_id=" + voice.getFileId() + "");
                InputStream inputStream = url.openStream();
                String filePath = "";
                try {
                    String fileData = IOUtils.toString(inputStream, "utf8");
                    JSONObject obj = new JSONObject(fileData);
                    filePath = obj.getJSONObject("result").get("file_path").toString();
                } catch (IOException ex) {
                    System.err.println(ex.toString());
                } finally {
                    inputStream.close();
                }
                InputStream audio = this.downloadFileAsStream(filePath);
                Watson watson = new Watson();
                messageText = watson.convert(audio);
            } catch (TelegramApiException ex) {
                System.err.println(ex.toString());
            } catch (MalformedURLException ex) {
                Logger.getLogger(Telegram.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Telegram.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        /**
         * Send the message to the MainIntent, to be handled. Every message is
         * expected to have a return, hence the "sendMessage" call, on every new
         * message.
         */
        intent.IntentDTO response = (new MainIntent()).run(
                message.getFrom().getId().toString(),
                message.getFrom().getFirstName(), messageText);
        sendMessage(response.getMessage(), response.getIdTelegram());
    }

    /**
     *
     * @return
     */
    @Override
    public String getBotUsername() {
        return name;
    }

    /**
     *
     * @return
     */
    @Override
    public String getBotToken() {
        return token;
    }

    /**
     *
     * @param msg
     * @param chatId
     */
    public void sendMessage(String msg, Long chatId) {
        SendMessage message = new SendMessage();
        message.setText(msg);
        message.setChatId(chatId);

        try {
            execute(message);
            System.out.println("Mensagem Enviada: " + msg + " - Para: " + chatId);
        } catch (TelegramApiException e) {
            System.err.println(e.toString());
            System.out.println("Erro no envio da mensagem: " + msg + " - Para: " + chatId);
        }
    }

}

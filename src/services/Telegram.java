package services;

import intent.main.MainIntent;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Bot class. Forwards all the messages to the MainIntent.
 *
 * @author joao
 */
public class Telegram extends TelegramLongPollingBot {

    /**
     *
     * @param update
     */
    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();

        if (message.getVoice() != null) {
            try {
                InputStream voice = this.downloadFileAsStream(message.getVoice().getFileId());
                Watson watson = new Watson();
                watson.convert(voice);
            } catch (TelegramApiException ex) {
                System.err.println(ex.toString());
            }

        }

        /**
         * Send the message to the MainIntent, to be handled. Every message is
         * expected to have a return, hence the "sendMessage" call, on every new
         * message.
         */
        intent.IntentDTO response = (new MainIntent()).run(
                message.getFrom().getId().toString(),
                message.getFrom().getFirstName(), message.getText());
        sendMessage(response.getMessage(), response.getIdTelegram());
    }

    /**
     *
     * @return
     */
    @Override
    public String getBotUsername() {
        return "FATEC_HELPER_BOT";
    }

    /**
     *
     * @return
     */
    @Override
    public String getBotToken() {
        return "1291405301:AAEGX5gQaD0aRa6TGu1Iw1hjzuXhxF-sccM";
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

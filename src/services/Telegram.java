package services;

import intent.MainIntent;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.objects.Message;

public class Telegram extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();

        MainIntent mainIntent = new MainIntent();
        intent.IntentDTO response = mainIntent.run(message.getFrom().getId().toString(), message.getFrom().getFirstName(), message.getText());
        sendMessage(response.getMessage(), response.getIdTelegram());
    }

    @Override
    public String getBotUsername() {
        return "FATEC_HELPER_BOT";
    }

    @Override
    public String getBotToken() {
        return "1291405301:AAEGX5gQaD0aRa6TGu1Iw1hjzuXhxF-sccM";
    }

    public void sendMessage(String msg, Long chatId) {
        SendMessage message = new SendMessage();
        message.setParseMode(ParseMode.MARKDOWNV2);
        message.setText(msg);
        message.setChatId(chatId);

        try {
            execute(message);
            System.out.println("Mensagem Enviada: " + msg + " - Para: " + chatId);
        } catch (TelegramApiException e) {
            System.out.println("Erro no envio da mensagem: " + msg + " - Para: " + chatId);
        }
    }

}

package services;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import model.AlunoDAO;


public class Telegram extends TelegramLongPollingBot{

	@Override
	public void onUpdateReceived(Update update) {

		
		AlunoDAO aluno = new AlunoDAO();
			
		aluno.id_telegram = update.getMessage().getFrom().getId();
		aluno.nomeUsuario = update.getMessage().getFrom().getFirstName();
		String msgRec = update.getMessage().getText();
		
		System.out.println("Mensagem Recebida: "+msgRec+" - Nome: "+aluno.nomeUsuario+" - ChatId: "+aluno.id_telegram);
		
		/**
		 * Aluno Casdastrado
		 */
		if (aluno.verificarCadastro()) {
			
			String msg = "Olá {nome}, gostaríamos de conhecer um pouco mais sobre você para que eu possa te ajudar.";
			msg = MessageManager.replaceValue(msg,"nome", aluno.nomeUsuario);
			
			sendMessage(msg,aluno.id_telegram);
			
		}
		/**
		 * Fazer cadastro do aluno
		 */
		else {
			//fazer cadastro
			aluno.id_curso_universidade = 1;
			aluno.termoAceite = true;
			System.out.println(update.getMessage().getFrom().getFirstName() + " - Não cadastrado - "+update.getMessage().getFrom().getId());
			SendMessage message = new SendMessage();
			
			message.setText("Não cadastrado " + update.getMessage().getFrom().getFirstName());
			
			message.setChatId(update.getMessage().getChatId());
			System.out.println(aluno.cadastrar());
			try {
				execute(message);
			} catch (TelegramApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	}

	@Override
	public String getBotUsername() {
		// TODO Auto-generated method stub
		return "FATEC_HELPER_BOT";
	}

	@Override
	public String getBotToken() {
		return "1291405301:AAEGX5gQaD0aRa6TGu1Iw1hjzuXhxF-sccM";
	}
	
	public void sendMessage(String msg, Long chatId) {
		
		SendMessage message = new SendMessage();
		message.setText(msg);
		message.setChatId(chatId);
		
		try {
			execute(message);
			System.out.println("Mensagem Enviada: "+msg+" - Para: "+chatId);
		} catch (TelegramApiException e) {
			e.printStackTrace();
			System.out.println("Erro no envio da mensagem: "+msg+" - Para: "+chatId);
		}
	}
	
	
	

}

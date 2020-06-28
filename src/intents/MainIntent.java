/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intents;

import model.AlunoDAO;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import services.MessageManager;

/**
 *
 * @author joao
 */
public class MainIntent extends Intent {

    /**
     * 0 - idTelegram 1 - nomeUsuario 2 - msgRec
     *
     * @param args
     * @return 
     */
    @Override
    public IntentDTO run(String... args) {
        AlunoDAO aluno = new AlunoDAO();

        aluno.idTelegram = Long.parseLong(args[0]);
        aluno.nomeUsuario = args[1];
        String msgRec = args[2];

        System.out.println("Mensagem Recebida: " + msgRec + " - Nome: " + aluno.nomeUsuario + " - ChatId: " + aluno.idTelegram);

        if (aluno.verificarCadastro()) {
            /**
             * Aluno Casdastrado
             */

            String msg = "Ol� {nome}, gostar�amos de conhecer um pouco mais sobre voc� para que eu possa te ajudar.";
            msg = MessageManager.replaceValue(msg, "nome", aluno.nomeUsuario);

            return new IntentDTO(msg, aluno.idTelegram);
        } else {
            /**
             * Fazer cadastro do aluno
             */

            aluno.idCursoUniversidade = 1;
            aluno.termoAceite = true;
//            System.out.println(update.getMessage().getFrom().getFirstName() + " - N�o cadastrado - " + update.getMessage().getFrom().getId());
//            SendMessage message = new SendMessage();

//            message.setText("N�o cadastrado " + update.getMessage().getFrom().getFirstName());
//            message.setChatId(update.getMessage().getChatId());
            System.out.println(aluno.cadastrar());

            String msg = "Aluno não cadastrado";
            return new IntentDTO(msg, aluno.idTelegram);
        }
    }

}

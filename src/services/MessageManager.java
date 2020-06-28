package services;

import dao.MensagemDominioDAO;
import java.util.ArrayList;
import model.MensagemDominio;
import model.Progresso;

public class MessageManager {

    public static String replaceValue(String text, String variable, String value) {
        text = text.replaceAll("[{]" + variable + "[}]", value);
        return text;
    }

    public static boolean checkAnswer(String text) {
        MensagemDominioDAO mensagemDominioDAO = new MensagemDominioDAO();

        ArrayList<MensagemDominio> negativas = mensagemDominioDAO.listMessage(Progresso.negacao);
        if (!negativas.stream().noneMatch(mensagemDominio -> (mensagemDominio.corpoMensagemDominio.equals(text)))) {
            return false;
        }

        ArrayList<MensagemDominio> positivas = mensagemDominioDAO.listMessage(Progresso.confirmacao);
        return positivas.stream().anyMatch(mensagemDominio -> (mensagemDominio.corpoMensagemDominio.equals(text)));
    }

}

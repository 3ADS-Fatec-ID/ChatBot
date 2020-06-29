package services;

import dao.MensagemDominioDAO;
import io.github.crew102.rapidrake.RakeAlgorithm;
import io.github.crew102.rapidrake.data.SmartWords;
import io.github.crew102.rapidrake.model.RakeParams;
import io.github.crew102.rapidrake.model.Result;
import java.io.IOException;
import java.text.Normalizer;
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
        if (!negativas.stream().noneMatch(mensagemDominio -> (mensagemDominio.corpoMensagemDominio.equals(text.toLowerCase())))) {
            return false;
        }

        ArrayList<MensagemDominio> positivas = mensagemDominioDAO.listMessage(Progresso.confirmacao);
        return positivas.stream().anyMatch(mensagemDominio -> (mensagemDominio.corpoMensagemDominio.equals(text.toLowerCase())));
    }

    public static String[] extractKeywords(String text) throws IOException {
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
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
        String accentRemoved = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        Result result = rakeAlg.rake(accentRemoved);

        String[] preparedKeywords = result.getFullKeywords();
        String whitespacedKeywords = String.join(" ", preparedKeywords);
        return whitespacedKeywords.split(" ");
    }

}

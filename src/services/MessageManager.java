package services;

import dao.DomainMessageDAO;
import io.github.crew102.rapidrake.RakeAlgorithm;
import io.github.crew102.rapidrake.data.SmartWords;
import io.github.crew102.rapidrake.model.RakeParams;
import io.github.crew102.rapidrake.model.Result;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import model.DomainMessage;
import model.Progress;

/**
 * Helper methods for dealing with text
 *
 * @author joao
 */
public class MessageManager {

    /**
     * Replaces the given variable for the value, using a certain rule
     *
     * @param text
     * @param variable
     * @param value
     * @return
     */
    public static String replaceValue(String text, String variable, String value) {
        text = text.replaceAll("[{]" + variable + "[}]", value);
        return text;
    }

    /**
     * Checks if the text is considerer negative or positive, by the application
     * standards
     *
     * @param text
     * @return
     */
    public static boolean checkAnswer(String text) {
        DomainMessageDAO domainMessageDAO = new DomainMessageDAO();

        ArrayList<DomainMessage> negatives = domainMessageDAO.list(Progress.negative);
        if (!negatives.stream().noneMatch(domainMessage -> (domainMessage.body.equals(text.toLowerCase())))) {
            return false;
        }

        ArrayList<DomainMessage> positives = domainMessageDAO.list(Progress.confirmation);
        return positives.stream().anyMatch(domainMessage -> (domainMessage.body.equals(text.toLowerCase())));
    }

    /**
     * Apply the RAKE to the given text, and return an array of words
     *
     * @param text
     * @return
     */
    public static String[] extractKeywords(String text) {
        try {
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

        } catch (IOException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
}

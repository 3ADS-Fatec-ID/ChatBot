/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.speech_to_text.v1.SpeechToText;
import com.ibm.watson.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.speech_to_text.v1.model.SpeechRecognitionResults;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author joao
 */
public class Watson {

    private static final String XML_PATH = "../lib/Watson.xml";
    private String apiKey;
    private String serviceUrl;
    private final SpeechToText speechToText;

    public Watson() {
        this.parseXML();
        IamAuthenticator authenticator = new IamAuthenticator(apiKey);
        speechToText = new SpeechToText(authenticator);
        speechToText.setServiceUrl(serviceUrl);
    }

    private void parseXML() {
        try {
            File inputFile = new File(XML_PATH);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            this.apiKey = doc.getDocumentElement().getAttribute("api-key");
            this.serviceUrl = doc.getDocumentElement().getAttribute("service-url");
        } catch (IOException | ParserConfigurationException | SAXException e) {
            System.err.println(e.toString());
        }
    }

    public String convert(InputStream inputStream) {
        RecognizeOptions recognizeOptions = new RecognizeOptions.Builder()
                .audio(inputStream)
                .contentType("audio/ogg")
                .model("pt-BR_BroadbandModel")
                .build();

        SpeechRecognitionResults speechRecognitionResults
                = speechToText.recognize(recognizeOptions).execute().getResult();
        System.out.println(speechRecognitionResults);
        return "";
    }
}

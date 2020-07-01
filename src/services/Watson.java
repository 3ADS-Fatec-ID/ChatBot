/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.speech_to_text.v1.SpeechToText;
import com.ibm.watson.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.speech_to_text.v1.model.SpeechRecognitionAlternative;
import com.ibm.watson.speech_to_text.v1.model.SpeechRecognitionResult;
import com.ibm.watson.speech_to_text.v1.model.SpeechRecognitionResults;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author joao
 */
public class Watson {

    private static final String JSON_PATH = "./src/lib/watson.json";
    private String apiKey;
    private String serviceUrl;
    private final SpeechToText speechToText;

    public Watson() {
        parseJSON();
        IamAuthenticator authenticator = new IamAuthenticator(apiKey);
        speechToText = new SpeechToText(authenticator);
        speechToText.setServiceUrl(serviceUrl);
    }

    private void parseJSON() {
        try (FileReader reader = new FileReader(JSON_PATH)) {
            JsonParser jsonParser = new JsonParser();
            JsonObject obj = jsonParser.parse(reader).getAsJsonObject();

            apiKey = obj.get("api_key").getAsString();
            serviceUrl = obj.get("service_url").getAsString();
        } catch (FileNotFoundException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
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
        String voice = "";
        for (SpeechRecognitionResult result : speechRecognitionResults.getResults()) {
            if (result.isXFinal()) {
                for (SpeechRecognitionAlternative alternative : result.getAlternatives()) {
                    voice += alternative.getTranscript();
                }
            }
        }
        System.out.println(voice);
        return voice;
    }
}

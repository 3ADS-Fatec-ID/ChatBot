/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intent;

/**
 * DTO for the intent response.
 * @author joao
 */
public class IntentDTO {

    String message;
    Long idTelegram;

    /**
     * Constrctor
     * @param message
     * @param idTelegram 
     */
    public IntentDTO(String message, Long idTelegram) {
        this.message = message;
        this.idTelegram = idTelegram;
    }

    /**
     * 
     * @return 
     */
    public String getMessage() {
        return message;
    }

    /**
     * 
     * @return 
     */
    public Long getIdTelegram() {
        return idTelegram;
    }
}

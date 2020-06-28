/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intent;

/**
 *
 * @author joao
 */
public class IntentDTO {

    String message;
    Long idTelegram;

    public IntentDTO(String message, Long idTelegram) {
        this.message = message;
        this.idTelegram = idTelegram;
    }

    public String getMessage() {
        return message;
    }

    public Long getIdTelegram() {
        return idTelegram;
    }   
}

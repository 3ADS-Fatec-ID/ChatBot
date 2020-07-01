/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intent.commad;

import dao.DomainMessageDAO;
import intent.Intent;
import intent.IntentDTO;
import model.DomainMessage;
import model.Progress;

/**
 *
 * @author joao
 */
public class CommandIntent extends Intent {

    public static String[] commands = {"/ajuda", "/reset"};

    @Override
    public IntentDTO run(String... args) {
        this.setup(args);

        switch (message) {
            case "/reset":
                return reset();
            case "/ajuda":
            default:
                return help();
        }
    }

    /**
     *
     * @return
     */
    private IntentDTO help() {
        DomainMessageDAO domainMessageDAO = new DomainMessageDAO();
        DomainMessage domainMessage = domainMessageDAO.find(Progress.help);

        String response = domainMessage.body;
        return new IntentDTO(response, foundStudent.telegramId);
    }

    private IntentDTO reset() {
        if (foundStudent != null) {
            studentDAO.delete();
            return new IntentDTO("Seu registro foi resetado.", student.telegramId);
        } else {
            return new IntentDTO("Você ainda não está registrado.", student.telegramId);
        }
    }

}

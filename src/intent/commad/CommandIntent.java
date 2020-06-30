/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intent.commad;

import dao.DomainMessageDAO;
import dao.ProgressDAO;
import dao.SearchDAO;
import intent.Intent;
import intent.IntentDTO;
import model.DomainMessage;
import model.Progress;
import model.Search;
import services.MessageManager;

/**
 *
 * @author joao
 */
public class CommandIntent extends Intent {

    public static String[] commands = {"/ajuda"};

    @Override
    public IntentDTO run(String... args) {
        this.setup(args);

        switch (message) {
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

}

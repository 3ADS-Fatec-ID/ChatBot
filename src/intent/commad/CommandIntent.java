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
        return new IntentDTO(response, student.telegramId);
    }

    private IntentDTO reset() {
        if (foundStudent != null) {
            studentDAO.delete();
            
            DomainMessageDAO domainMessageDAO = new DomainMessageDAO();
            DomainMessage domainMessage = domainMessageDAO.find(Progress.reset);

            Progress progress = (new ProgressDAO()).find(Progress.reset);
            Search search = new Search(progress.id, foundStudent.id, message);
            SearchDAO searchDAO = new SearchDAO(search);
            searchDAO.add();

            String response = domainMessage.body;
            return new IntentDTO(response, student.telegramId);
        } else {
            DomainMessageDAO domainMessageDAO = new DomainMessageDAO();
            DomainMessage domainMessage = domainMessageDAO.find(Progress.resetFailed);

            String response = domainMessage.body;
            return new IntentDTO(response, student.telegramId);
        }
    }

}

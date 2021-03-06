/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intent.register;

import dao.DomainMessageDAO;
import dao.ProgressDAO;
import dao.SearchDAO;
import intent.Intent;
import intent.IntentDTO;
import model.DomainMessage;
import model.Progress;
import model.Search;
import model.Student;
import services.MessageManager;

/**
 * @author joao
 */
public class RegistrationCanceledIntent extends Intent {

    /**
     * Responds the user, when they first refused to register
     *
     * @param args
     * @return
     */
    @Override
    public IntentDTO run(String... args) {
        this.setup(args);

        DomainMessageDAO domainMessageDAO = new DomainMessageDAO();
        DomainMessage domainMessage = domainMessageDAO.find(Progress.initialRegistration);

        String response = domainMessage.body;
        response = MessageManager.replaceValue(response, "nome", foundStudent.name);

        Progress progress = (new ProgressDAO()).find(Progress.initialRegistration);
        Search search = new Search(progress.id, foundStudent.id, message);
        SearchDAO searchDAO = new SearchDAO(search);
        searchDAO.add();

        return new IntentDTO(response, foundStudent.telegramId);
    }
}

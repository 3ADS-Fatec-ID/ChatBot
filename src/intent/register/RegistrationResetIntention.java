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
import services.MessageManager;

/**
 *
 * @author joao
 */
public class RegistrationResetIntention extends Intent {

    /**
     * Response when the user was reseted.
     *
     * @param args
     * @return
     */
    @Override
    public IntentDTO run(String... args) {
        DomainMessageDAO domainMessageDAO = new DomainMessageDAO();
        DomainMessage domainMessage = domainMessageDAO.find(Progress.initialRegistration);

        String response = domainMessage.body;
        response = MessageManager.replaceValue(response, "nome", student.name);

        student = studentDAO.find();
        Progress progress = (new ProgressDAO()).find(Progress.initialRegistration);
        Search search = new Search(progress.id, student.id, message);
        SearchDAO searchDAO = new SearchDAO(search);
        searchDAO.add();

        return new IntentDTO(response, student.telegramId);
    }

}

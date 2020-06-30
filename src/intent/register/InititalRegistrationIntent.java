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
public class InititalRegistrationIntent extends Intent {

    /**
     * @param args
     * @return
     */
    @Override
    public IntentDTO run(String... args) {
        this.setup(args);

        if (MessageManager.checkAnswer(message)) {
            return askUniversity();
        } else {
            return cancelRegister();
        }
    }

    /**
     * Asks for the student's university
     *
     * @return
     */
    private IntentDTO askUniversity() {
        DomainMessageDAO domainMessageDAO = new DomainMessageDAO();
        DomainMessage domainMessage = domainMessageDAO.find(Progress.universityRegistration);

        Progress progress = (new ProgressDAO()).find(Progress.universityRegistration);
        Search search = new Search(progress.id, foundStudent.id, message);
        SearchDAO searchDAO = new SearchDAO(search);
        searchDAO.add();

        String response = domainMessage.body;
        return new IntentDTO(response, foundStudent.telegramId);
    }

    /**
     * Handles the cancellation of a registration
     *
     * @return
     */
    private IntentDTO cancelRegister() {
        DomainMessageDAO domainMessageDAO = new DomainMessageDAO();
        DomainMessage domainMessage = domainMessageDAO.find(Progress.registrationCanceled);

        Progress progress = (new ProgressDAO()).find(Progress.registrationCanceled);
        Search search = new Search(progress.id, foundStudent.id, message);
        SearchDAO searchDAO = new SearchDAO(search);
        searchDAO.add();

        String response = domainMessage.body;
        return new IntentDTO(response, foundStudent.telegramId);
    }
}

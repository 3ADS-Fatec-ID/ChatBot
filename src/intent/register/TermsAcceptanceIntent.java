/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intent.register;

import dao.DomainMessageDAO;
import dao.ProgressDAO;
import dao.SearchDAO;
import dao.StudentDAO;
import intent.Intent;
import intent.IntentDTO;
import model.DomainMessage;
import model.Progress;
import model.Search;
import model.Student;
import services.MessageManager;

/**
 *
 * @author joao
 */
public class TermsAcceptanceIntent extends Intent {

    @Override
    public IntentDTO run(String... args) {
        this.setup(args);

        if (MessageManager.checkAnswer(message)) {
            return registerTerms();
        } else {
            return warnInability();
        }
    }

    /**
     * The user has accepted our terms of use
     *
     * @param student
     * @param message
     * @return
     */
    private IntentDTO registerTerms() {
        foundStudent.termAccepted = true;
        studentDAO = new StudentDAO(foundStudent);
        studentDAO.acceptTerms();

        DomainMessageDAO domainMessageDAO = new DomainMessageDAO();
        DomainMessage domainMessage = domainMessageDAO.find(Progress.registrationCompleted);

        Progress progress = (new ProgressDAO()).find(Progress.registrationCompleted);
        Search search = new Search(progress.id, foundStudent.id, message);
        SearchDAO searchDAO = new SearchDAO(search);
        searchDAO.add();

        String response = domainMessage.body;
        return new IntentDTO(response, foundStudent.telegramId);
    }

    /**
     * The student has not accepted our term of use
     *
     * @return
     */
    private IntentDTO warnInability() {
        DomainMessageDAO domainMessageDAO = new DomainMessageDAO();
        DomainMessage domainMessage = domainMessageDAO.find(Progress.termsRefused);

        String response = domainMessage.body;
        response = MessageManager.replaceValue(response, "nome", foundStudent.name);

        Progress progress = (new ProgressDAO()).find(Progress.termsRefused);
        Search search = new Search(progress.id, foundStudent.id, message);
        SearchDAO searchDAO = new SearchDAO(search);
        searchDAO.add();

        return new IntentDTO(response, foundStudent.telegramId);
    }

}

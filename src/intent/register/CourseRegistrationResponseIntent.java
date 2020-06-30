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
public class CourseRegistrationResponseIntent extends Intent {

    @Override
    public IntentDTO run(String... args) {
        this.setup(args);

        if (MessageManager.checkAnswer(message)) {
            return askTerms();
        } else {
            return cancelCourse();
        }
    }

    /**
     * Asks for the student to accept the terms of use
     *
     * @return
     */
    private IntentDTO askTerms() {
        DomainMessageDAO domainMessageDAO = new DomainMessageDAO();
        DomainMessage domainMessage = domainMessageDAO.find(Progress.termsAcceptance);

        Progress progress = (new ProgressDAO()).find(Progress.termsAcceptance);
        Search search = new Search(progress.id, foundStudent.id, message);
        SearchDAO searchDAO = new SearchDAO(search);
        searchDAO.add();

        String response = domainMessage.body;
        return new IntentDTO(response, foundStudent.telegramId);
    }

    /**
     * Handles the user negative response when we aks if the found course is
     * their own.
     *
     * @return
     */
    private IntentDTO cancelCourse() {
        studentDAO.deleteCourse();

        DomainMessageDAO domainMessageDAO = new DomainMessageDAO();
        DomainMessage domainMessage = domainMessageDAO.find(Progress.courseRegistrationResponseNegative);

        Progress progress = (new ProgressDAO()).find(Progress.courseRegistrationResponseNegative);
        Search search = new Search(progress.id, foundStudent.id, message);
        SearchDAO searchDAO = new SearchDAO(search);
        searchDAO.add();

        String response = domainMessage.body;
        return new IntentDTO(response, foundStudent.telegramId);
    }
}

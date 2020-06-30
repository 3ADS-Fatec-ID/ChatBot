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
 *
 * @author joao
 */
public class UniversityRegistrationResponseIntent extends Intent {

    @Override
    public IntentDTO run(String... args) {
        this.setup(args);

        if (MessageManager.checkAnswer(message)) {
            return askCourse();
        } else {
            return cancelUniversity();
        }
    }

    /**
     * Asks for the students course
     *
     * @return
     */
    private IntentDTO askCourse() {
        DomainMessageDAO domainMessageDAO = new DomainMessageDAO();
        DomainMessage domainMessage = domainMessageDAO.find(Progress.courseRegistration);

        Progress progress = (new ProgressDAO()).find(Progress.courseRegistration);
        Search search = new Search(progress.id, student.id, message);
        SearchDAO searchDAO = new SearchDAO(search);
        searchDAO.add();

        String response = domainMessage.body;
        return new IntentDTO(response, student.telegramId);
    }

    /**
     * The user said no, when we asked if the found university was their own
     *
     * @return
     */
    private IntentDTO cancelUniversity() {
        studentDAO.deleteUniversity();

        DomainMessageDAO domainMessageDAO = new DomainMessageDAO();
        DomainMessage domainMessage = domainMessageDAO.find(Progress.universityRegistrationCanceled);

        Progress progress = (new ProgressDAO()).find(Progress.universityRegistrationCanceled);
        Search search = new Search(progress.id, foundStudent.id, message);
        SearchDAO searchDAO = new SearchDAO(search);
        searchDAO.add();

        String response = domainMessage.body;
        return new IntentDTO(response, foundStudent.telegramId);
    }
}

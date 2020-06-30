/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intent.register;

import dao.DomainMessageDAO;
import dao.ProgressDAO;
import dao.SearchDAO;
import dao.UniversityDAO;
import intent.Intent;
import intent.IntentDTO;
import model.DomainMessage;
import model.Progress;
import model.Search;
import model.University;
import services.MessageManager;

/**
 * @author joao
 */
public class RegisterUniversityIntent extends Intent {

    /**
     * @param args
     * @return
     */
    @Override
    public IntentDTO run(String... args) {
        this.setup(args);

        University university = new University();
        university.name = message;
        UniversityDAO universityDAO = new UniversityDAO(university);
        University foundUniversity = universityDAO.find();

        String response;

        DomainMessageDAO domainMessageDAO = new DomainMessageDAO();
        if (foundUniversity == null) {
            DomainMessage domainMessage = domainMessageDAO.find(Progress.universityRegistrationResponseNegative);

            response = domainMessage.body;

            Progress progress = (new ProgressDAO()).find(Progress.universityRegistrationResponseNegative);
            Search search = new Search(progress.id, foundStudent.id, message);
            SearchDAO searchDAO = new SearchDAO(search);
            searchDAO.add();
        } else {
            foundStudent.universityId = foundUniversity.id;
            studentDAO.updateUniversity();

            DomainMessage domainMessage = domainMessageDAO.find(Progress.universityRegistrationResponse);

            response = domainMessage.body;
            response = MessageManager.replaceValue(response, "universidade", foundUniversity.name);

            Progress progress = (new ProgressDAO()).find(Progress.universityRegistrationResponse);
            Search search = new Search(progress.id, foundStudent.id, message);
            SearchDAO searchDAO = new SearchDAO(search);
            searchDAO.add();
        }

        return new IntentDTO(response, foundStudent.telegramId);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intent.register;

import dao.DomainMessageDAO;
import dao.SearchDAO;
import dao.ProgressDAO;
import intent.Intent;
import intent.IntentDTO;
import model.DomainMessage;
import model.Search;
import model.Progress;
import services.MessageManager;

/**
 *
 * @author joao
 */
public class RegisterIntent extends Intent {

    /**
     * 0 - telegramId 1 - userName 2- message
     *
     * @param args
     * @return
     */
    @Override
    public IntentDTO run(String... args) {
        this.setup(args);

        if (foundStudent != null) {
            ProgressDAO progressDAO = new ProgressDAO();
            Progress progress = progressDAO.pegarProgresso(foundStudent);

            if (progress != null) {
                if (progress.name.equals(Progress.reset)) {
                    return (new RegistrationResetIntention()).run(args);
                } else if (progress.name.equals(Progress.initialRegistration)) {
                    return (new InititalRegistrationIntent()).run(args);
                } else if (progress.name.equals(Progress.registrationCanceled)) {
                    return (new RegistrationCanceledIntent()).run(args);
                } else if (shouldRegisterUniversity(progress)) {
                    return (new RegisterUniversityIntent()).run(args);
                } else if (progress.name.equals(Progress.universityRegistrationResponse)) {
                    return (new UniversityRegistrationResponseIntent()).run(args);
                } else if (shouldRegisterCourse(progress)) {
                    return (new RegisterCourseIntent()).run(args);
                } else if (progress.name.equals(Progress.courseRegistrationResponse)) {
                    return (new CourseRegistrationResponseIntent()).run(args);
                } else if (shouldForwardToTermsAcceptanceIntent(progress)) {
                    return (new TermsAcceptanceIntent()).run(args);
                }
            }
        }

        return firstAccess();
    }

    /**
     * Checks whatever we should try to register the student's university
     *
     * @param progress
     * @return
     */
    private boolean shouldRegisterUniversity(Progress progress) {
        return progress.name.equals(Progress.universityRegistration)
                || progress.name.equals(Progress.universityRegistrationResponseNegative)
                || progress.name.equals(Progress.universityRegistrationCanceled);
    }

    /**
     * Checks whatever we should try to register the student's course
     *
     * @param progress
     * @return
     */
    private boolean shouldRegisterCourse(Progress progress) {
        return progress.name.equals(Progress.courseRegistration)
                || progress.name.equals(Progress.courseRegistrationCanceled)
                || progress.name.equals(Progress.courseRegistrationResponseNegative);
    }

    /**
     * Checks whatever we should forward the message to the
     * "TermsAcceptanceIntent"
     *
     * @param progress
     * @return
     */
    private boolean shouldForwardToTermsAcceptanceIntent(Progress progress) {
        return progress.name.equals(Progress.termsAcceptance)
                || progress.name.equals(Progress.termsRefused);
    }

    /**
     * We've never seen the user
     *
     * @return
     */
    private IntentDTO firstAccess() {
        DomainMessageDAO domainMessageDAO = new DomainMessageDAO();
        DomainMessage domainMessage = domainMessageDAO.find(Progress.initialRegistration);

        String response = domainMessage.body;
        response = MessageManager.replaceValue(response, "nome", student.name);

        if (studentDAO.add()) {
            student = studentDAO.find();
            Progress progress = (new ProgressDAO()).find(Progress.initialRegistration);
            Search search = new Search(progress.id, student.id, message);
            SearchDAO searchDAO = new SearchDAO(search);
            searchDAO.add();
        }

        return new IntentDTO(response, student.telegramId);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intent.register;

import dao.CourseDAO;
import dao.DomainMessageDAO;
import dao.ProgressDAO;
import dao.SearchDAO;
import intent.Intent;
import intent.IntentDTO;
import model.Course;
import model.DomainMessage;
import model.Progress;
import model.Search;
import services.MessageManager;

/**
 *
 * @author joao
 */
public class RegisterCourseIntent extends Intent {

    /**
     * Register the student's course
     *
     * @param args
     * @return
     */
    @Override
    public IntentDTO run(String... args) {
        this.setup(args);

        Course course = new Course();
        course.name = message;
        course.initials = message;
        CourseDAO courseDAO = new CourseDAO(course);
        Course foundCourse = courseDAO.find(foundStudent.universityId);

        String response;

        DomainMessageDAO domainMessageDAO = new DomainMessageDAO();
        if (foundCourse == null) {
            DomainMessage domainMessage = domainMessageDAO.find(Progress.courseRegistrationCanceled);

            response = domainMessage.body;

            Progress progress = (new ProgressDAO()).find(Progress.courseRegistrationCanceled);
            Search search = new Search(progress.id, foundStudent.id, message);
            SearchDAO searchDAO = new SearchDAO(search);
            searchDAO.add();
        } else {
            foundStudent.universityCourseId = foundCourse.universityCourseId;
            studentDAO.updateCourse();

            DomainMessage domainMessage = domainMessageDAO.find(Progress.courseRegistrationResponse);

            response = domainMessage.body;
            response = MessageManager.replaceValue(response, "curso", foundCourse.name);

            Progress progress = (new ProgressDAO()).find(Progress.courseRegistrationResponse);
            Search search = new Search(progress.id, foundStudent.id, message);
            SearchDAO searchDAO = new SearchDAO(search);
            searchDAO.add();
        }

        return new IntentDTO(response, foundStudent.telegramId);
    }

}

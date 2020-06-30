/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intent;

import dao.StudentDAO;
import model.Student;

/**
 * Abstract class for every intent.
 *
 * @author joao
 */
public abstract class Intent {

    protected Student student, foundStudent;
    protected StudentDAO studentDAO;
    protected String message;

    protected void setup(String... args) {
        student = new Student();
        student.telegramId = Long.parseLong(args[0]);
        student.name = args[1];
        message = args[2];
        studentDAO = new StudentDAO(student);
        foundStudent = studentDAO.find();
        if (foundStudent != null) {
            studentDAO = new StudentDAO(foundStudent);
        }
    }

    /**
     * Run the intent and returns a response.
     *
     * @param args
     * @return
     */
    public abstract IntentDTO run(String... args);
}

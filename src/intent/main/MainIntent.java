/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intent.main;

import intent.search.SearchIntent;
import intent.register.RegisterIntent;
import dao.StudentDAO;
import intent.Intent;
import intent.IntentDTO;
import model.Student;

/**
 * The Main Intent, every message goes throught it.
 *
 * @author joao
 */
public class MainIntent extends Intent {

    /**
     * @param args
     * @return
     */
    @Override
    public IntentDTO run(String... args) {
        this.setup(args);

        System.out.println("Mensagem Recebida: " + message + " - Nome: " + student.name + " - ChatId: " + student.telegramId);

        if (studentDAO.exists()) {
            /**
             * The Student is registered, handle the searching
             */

            return (new SearchIntent()).run(args[0], args[1], args[2]);
        } else {
            /**
             * The student needs to be registered
             */

            return (new RegisterIntent()).run(args[0], args[1], args[2]);
        }
    }

}

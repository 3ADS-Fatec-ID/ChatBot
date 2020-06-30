/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intent.main;

import intent.search.SearchIntent;
import intent.register.RegisterIntent;
import dao.AlunoDAO;
import intent.Intent;
import intent.IntentDTO;
import model.Student;

/**
 *
 * @author joao
 */
public class MainIntent extends Intent {

    /**
     * 0 - idTelegram 1 - nomeUsuario 2 - msgRec
     *
     * @param args
     * @return
     */
    @Override
    public IntentDTO run(String... args) {
        Student aluno = new Student();
        
        aluno.telegramId = Long.parseLong(args[0]);
        aluno.name = args[1];
        
        AlunoDAO alunoDAO = new AlunoDAO(aluno);
        String msgRec = args[2];
        
        System.out.println("Mensagem Recebida: " + msgRec + " - Nome: " + aluno.name + " - ChatId: " + aluno.telegramId);
        
        if (alunoDAO.verificarCadastro()) {
            /**
             * Aluno Casdastrado
             */
           
            return (new SearchIntent()).run(args[0], args[1], args[2]);
        } else {
            /**
             * Fazer cadastro do aluno
             */
            
            return (new RegisterIntent()).run(args[0], args[1], args[2]);
        }
    }
    
}

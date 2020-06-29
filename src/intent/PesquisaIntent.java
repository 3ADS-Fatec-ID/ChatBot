/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intent;

import dao.AlunoDAO;
import java.io.IOException;
import model.Aluno;
import services.MessageManager;

/**
 *
 * @author joao
 */
public class PesquisaIntent extends Intent {

    @Override
    public IntentDTO run(String... args) {
        Aluno aluno = new Aluno();
        aluno.idTelegram = Long.parseLong(args[0]);
        aluno.nomeUsuario = args[1];
        String message = args[2];

        AlunoDAO alunoDAO = new AlunoDAO(aluno);

        Aluno alunoEncontrado = alunoDAO.encontrarAluno();
        
        try {
            return new IntentDTO(MessageManager.extractKeywords(message).toString(), alunoEncontrado.idTelegram);
        } catch (IOException ex) {
            System.err.println(ex.toString());
            return new IntentDTO(ex.toString(), alunoEncontrado.idTelegram);
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intent;

import dao.AlunoDAO;
import model.Aluno;

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
        Aluno aluno = new Aluno();
        
        aluno.idTelegram = Long.parseLong(args[0]);
        aluno.nomeUsuario = args[1];
        
        AlunoDAO alunoDAO = new AlunoDAO(aluno);
        String msgRec = args[2];
        
        System.out.println("Mensagem Recebida: " + msgRec + " - Nome: " + aluno.nomeUsuario + " - ChatId: " + aluno.idTelegram);
        
        if (alunoDAO.verificarCadastro()) {
            /**
             * Aluno Casdastrado
             */
           
            return (new PesquisaIntent()).run(args[0], args[1], args[2]);
        } else {
            /**
             * Fazer cadastro do aluno
             */
            
            return (new CadastroIntent()).run(args[0], args[1], args[2]);
        }
    }
    
}

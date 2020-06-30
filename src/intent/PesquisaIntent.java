/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intent;

import dao.AlunoDAO;
import dao.PalavraChavePesquisaDAO;
import java.io.IOException;
import java.util.ArrayList;
import model.Aluno;
import model.PalavraChavePesquisa;
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
            String[] keywords = MessageManager.extractKeywords(message);
            ArrayList<PalavraChavePesquisa> palavraChavePesquisas = new ArrayList<>();
            if (keywords.length > 0) {
                for (String keyword : keywords) {
                    PalavraChavePesquisaDAO palavraChavePesquisaDAO = new PalavraChavePesquisaDAO();
                    palavraChavePesquisas.addAll(palavraChavePesquisaDAO.listarPalavraChavePesquisas(keyword, alunoEncontrado));
                }
                PalavraChavePesquisa[] palavraChavePesquisasDistinct = palavraChavePesquisas.stream().distinct().toArray(PalavraChavePesquisa[]::new);
                for (PalavraChavePesquisa palavraChavePesquisa : palavraChavePesquisasDistinct) {
                    System.out.println(palavraChavePesquisa.toString());
                }
                return new IntentDTO("Parabéns", alunoEncontrado.idTelegram);
            } else {
                return new IntentDTO("Não foi possível processar sua mensagem, tente novamente!", alunoEncontrado.idTelegram);
            }
        } catch (IOException ex) {
            System.err.println(ex.toString());
            return new IntentDTO(ex.toString(), alunoEncontrado.idTelegram);
        }
    }
    
}

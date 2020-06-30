/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intent;

import dao.AcervoDAO;
import dao.AlunoDAO;
import dao.DuvidaDAO;
import dao.PalavraChavePesquisaDAO;
import dao.PesquisavelDAO;
import java.io.IOException;
import java.util.ArrayList;
import model.Acervo;
import model.Aluno;
import model.Duvida;
import model.PalavraChavePesquisa;
import model.Pesquisavel;
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
            if (keywords.length > 0 && !"".equals(keywords[0])) {
                for (String keyword : keywords) {
                    System.out.println(keyword);
                    PalavraChavePesquisaDAO palavraChavePesquisaDAO = new PalavraChavePesquisaDAO();
                    palavraChavePesquisas.addAll(palavraChavePesquisaDAO.listarPalavraChavePesquisas(keyword, alunoEncontrado));
                }

                String finalMessage = "Os resultados encontrados foram:\n";
                PalavraChavePesquisa[] palavraChavePesquisasDistinct = palavraChavePesquisas.stream().distinct().limit(5).toArray(PalavraChavePesquisa[]::new);

                if (palavraChavePesquisasDistinct.length > 0) {
                    for (PalavraChavePesquisa palavraChavePesquisa : palavraChavePesquisasDistinct) {
                        PesquisavelDAO pesquisavelDAO = new PesquisavelDAO();
                        Pesquisavel pesquisavel = pesquisavelDAO.pesquisarPesquisavel(palavraChavePesquisa.idPesquisavel);

                        if (pesquisavel.idAcervo != 0) {
                            AcervoDAO acervoDAO = new AcervoDAO();
                            Acervo acervo = acervoDAO.pesquisarAcervo(pesquisavel.idAcervo);
                            finalMessage += "";
                        } else {
                            DuvidaDAO duvidaDAO = new DuvidaDAO();
                            Duvida duvida = duvidaDAO.pesquisarDuvida(pesquisavel.idDuvida);
                            String titulo = "<b>Dúvida: </b>" + duvida.nomeDuvida;
                            String corpo = "<b>Resposta: </b>" + duvida.descricaoDuvida;
                            finalMessage += "\n" + titulo + "\n" + corpo + "\n";
                        }

                    }
                    return new IntentDTO(finalMessage, alunoEncontrado.idTelegram);
                } else {
                    return new IntentDTO("Nenhum resultado foi encontrado, tente novamente!", alunoEncontrado.idTelegram);

                }

            } else {
                return new IntentDTO("Não foi possível processar sua mensagem, tente novamente!", alunoEncontrado.idTelegram);
            }
        } catch (IOException ex) {
            System.err.println(ex.toString());
            return new IntentDTO(ex.toString(), alunoEncontrado.idTelegram);
        }
    }

}

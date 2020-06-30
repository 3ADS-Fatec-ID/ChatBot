/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intent;

import dao.AcervoDAO;
import dao.AlunoDAO;
import dao.DuvidaDAO;
import dao.MensagemDominioDAO;
import dao.PalavraChavePesquisaDAO;
import dao.PesquisaDAO;
import dao.PesquisavelDAO;
import dao.ProgressoDAO;

import java.io.IOException;
import java.util.ArrayList;
import model.Acervo;
import model.Aluno;
import model.Duvida;
import model.MensagemDominio;
import model.PalavraChavePesquisa;
import model.Pesquisa;
import model.Pesquisavel;
import model.Progresso;
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

                PalavraChavePesquisa[] palavraChavePesquisasDistinct = palavraChavePesquisas.stream().distinct().limit(5).toArray(PalavraChavePesquisa[]::new);
                String[] finalMessage;

                if (palavraChavePesquisasDistinct.length > 0) {
                    int index = 1;
                    finalMessage = new String[palavraChavePesquisasDistinct.length + 1];
                    finalMessage[0] = "Os resultados encontrados foram:";

                    for (PalavraChavePesquisa palavraChavePesquisa : palavraChavePesquisasDistinct) {
                        PesquisavelDAO pesquisavelDAO = new PesquisavelDAO();
                        Pesquisavel pesquisavel = pesquisavelDAO.pesquisarPesquisavel(palavraChavePesquisa.idPesquisavel);

                        if (pesquisavel.idAcervo != 0) {
                            AcervoDAO acervoDAO = new AcervoDAO();
                            Acervo acervo = acervoDAO.pesquisarAcervo(pesquisavel.idAcervo);
                            String tema = "Tema: " + acervo.tema;
                            String autor = "Autor: " + acervo.autor;
                            String orientador = "Orientador: " + acervo.orientador;
                            finalMessage[index] = "\n" + tema + "\n" + autor + "\n" + orientador;
                        } else {
                            DuvidaDAO duvidaDAO = new DuvidaDAO();
                            Duvida duvida = duvidaDAO.pesquisarDuvida(pesquisavel.idDuvida);
                            String titulo = "Dúvida: " + duvida.nomeDuvida;
                            String corpo = "Resposta: " + duvida.descricaoDuvida;
                            finalMessage[index] = "\n" + titulo + "\n" + corpo.replace(". ", ".\n").replace(": ", ":\n");
                        }
                        index++;
                    }

                    return new IntentDTO(String.join("\n", finalMessage), alunoEncontrado.idTelegram);
                } else {
                    return pesquisaNaoEncontrada(alunoEncontrado, message);

                }

            } else {
                return new IntentDTO("Não foi possível processar sua mensagem, tente novamente!", alunoEncontrado.idTelegram);
            }
        } catch (IOException ex) {
            System.err.println(ex.toString());
            return new IntentDTO(ex.toString(), alunoEncontrado.idTelegram);
        }
    }
    
    private IntentDTO pesquisaNaoEncontrada(Aluno aluno, String message) {
        /**
         * O pesquisa usuário não encontrada
         */

        AlunoDAO alunoDAO = new AlunoDAO(aluno);

        MensagemDominioDAO mensagemDominioDAO = new MensagemDominioDAO();
        MensagemDominio mensagemDominio = mensagemDominioDAO.findMessage(Progresso.pesquisaNegativaResposta);

        Progresso progresso = (new ProgressoDAO()).pegarProgresso(Progresso.pesquisaNegativaResposta);
        Pesquisa pesquisa = new Pesquisa(progresso.id, aluno.id, message);
        PesquisaDAO pesquisaDAO = new PesquisaDAO(pesquisa);
        pesquisaDAO.criarPesquisa();

        String msg = mensagemDominio.corpoMensagemDominio;
        return new IntentDTO(msg, aluno.idTelegram);
    }

}

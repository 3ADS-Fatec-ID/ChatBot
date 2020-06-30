/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intent.search;

import dao.AcervoDAO;
import dao.AlunoDAO;
import dao.DuvidaDAO;
import dao.MensagemDominioDAO;
import dao.PalavraChavePesquisaDAO;
import dao.PesquisaDAO;
import dao.PesquisavelDAO;
import dao.ProgressoDAO;
import intent.Intent;
import intent.IntentDTO;

import java.io.IOException;
import java.util.ArrayList;
import model.Collection;
import model.Student;
import model.Question;
import model.DomainMessage;
import model.KeywordSearch;
import model.Search;
import model.Searchable;
import model.Progress;
import services.MessageManager;
import services.Predicates;

/**
 *
 * @author joao
 */
public class SearchIntent extends Intent {

    @Override
    public IntentDTO run(String... args) {
        Student aluno = new Student();
        aluno.telegramId = Long.parseLong(args[0]);
        aluno.name = args[1];
        String message = args[2];

        AlunoDAO alunoDAO = new AlunoDAO(aluno);

        Student alunoEncontrado = alunoDAO.encontrarAluno();

        try {
            String[] keywords = MessageManager.extractKeywords(message);
            ArrayList<KeywordSearch> palavraChavePesquisas = new ArrayList<>();
            if (keywords.length > 0 && !"".equals(keywords[0])) {
                for (String keyword : keywords) {
                    System.out.println(keyword);
                    PalavraChavePesquisaDAO palavraChavePesquisaDAO = new PalavraChavePesquisaDAO();
                    palavraChavePesquisas.addAll(palavraChavePesquisaDAO.listarPalavraChavePesquisas(keyword, alunoEncontrado));
                }

                KeywordSearch[] palavraChavePesquisasDistinct;
                palavraChavePesquisasDistinct = palavraChavePesquisas.stream()
                        .filter(Predicates.distinctByKey(p -> p.searchableId))
                        .limit(5).toArray(KeywordSearch[]::new);
                String[] finalMessage;

                if (palavraChavePesquisasDistinct.length > 0) {
                    int index = 1;
                    finalMessage = new String[palavraChavePesquisasDistinct.length + 1];
                    finalMessage[0] = "Os resultados encontrados foram:";

                    for (KeywordSearch palavraChavePesquisa : palavraChavePesquisasDistinct) {
                        PesquisavelDAO pesquisavelDAO = new PesquisavelDAO();
                        Searchable pesquisavel = pesquisavelDAO.pesquisarPesquisavel(palavraChavePesquisa.searchableId);

                        if (pesquisavel.collectionId != 0) {
                            AcervoDAO acervoDAO = new AcervoDAO();
                            Collection acervo = acervoDAO.pesquisarAcervo(pesquisavel.collectionId);
                            String tema = "Tema: " + acervo.theme;
                            String autor = "Autor: " + acervo.author;
                            String orientador = "Orientador: " + acervo.advisor;
                            finalMessage[index] = "\n" + tema + "\n" + autor + "\n" + orientador;
                        } else {
                            DuvidaDAO duvidaDAO = new DuvidaDAO();
                            Question duvida = duvidaDAO.pesquisarDuvida(pesquisavel.questionId);
                            String titulo = "Dúvida: " + duvida.name;
                            String corpo = "Resposta: " + duvida.description;
                            finalMessage[index] = "\n" + titulo + "\n" + corpo
                                    .replace(". ", ".\n")
                                    .replace(": ", ":\n");
                        }
                        index++;
                    }

                    return new IntentDTO(String.join("\n", finalMessage), alunoEncontrado.telegramId);
                }
            }

            return pesquisaNaoEncontrada(alunoEncontrado, message);
        } catch (IOException ex) {
            System.err.println(ex.toString());
            return new IntentDTO(ex.toString(), alunoEncontrado.telegramId);
        }
    }

    private IntentDTO pesquisaNaoEncontrada(Student aluno, String message) {
        /**
         * O pesquisa usuário não encontrada
         */

        MensagemDominioDAO mensagemDominioDAO = new MensagemDominioDAO();
        DomainMessage mensagemDominio = mensagemDominioDAO.findMessage(Progress.searchNotFound);

        Progress progresso = (new ProgressoDAO()).pegarProgresso(Progress.searchNotFound);
        Search pesquisa = new Search(progresso.id, aluno.id, message);
        PesquisaDAO pesquisaDAO = new PesquisaDAO(pesquisa);
        pesquisaDAO.criarPesquisa();

        String msg = mensagemDominio.body;
        return new IntentDTO(msg, aluno.telegramId);
    }

}

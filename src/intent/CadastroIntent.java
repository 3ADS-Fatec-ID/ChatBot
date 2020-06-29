/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intent;

import dao.AlunoDAO;
import java.lang.invoke.SwitchPoint;

import dao.MensagemDominioDAO;
import dao.PesquisaDAO;
import dao.ProgressoDAO;
import dao.UniversidadeDAO;
import model.Aluno;
import model.MensagemDominio;
import model.Pesquisa;
import model.Progresso;
import model.Universidade;
import services.MessageManager;
import services.Str;

/**
 *
 * @author joao
 */
public class CadastroIntent extends Intent {

    /**
     * 0 - idTelegram 1 - nomeUsuario 2- msgRes
     *
     * @param args
     * @return
     */
    @Override
    public IntentDTO run(String... args) {
        Aluno aluno = new Aluno();
        aluno.idTelegram = Long.parseLong(args[0]);
        aluno.nomeUsuario = args[1];
        String message = args[2];

        AlunoDAO alunoDAO = new AlunoDAO(aluno);

        Aluno alunoEncontrado = alunoDAO.encontrarAluno();
        if (alunoEncontrado != null) {
            ProgressoDAO progressoDAO = new ProgressoDAO();
            Progresso progresso = progressoDAO.pegarProgresso(alunoEncontrado);

            if (Str.equals(progresso.nomeProgresso, Progresso.cadastroInicial)) {
                if (MessageManager.checkAnswer(message)) {
                    return pedirUniversidade(alunoEncontrado, message);
                } else {
                    return cancelarCadastro(alunoEncontrado, message);
                }
            } else if (Str.equals(progresso.nomeProgresso, Progresso.cadastroCancelado)) {
                return primeiroAcessoCancelado(alunoDAO, alunoEncontrado, message);
            } else if (Str.equals(progresso.nomeProgresso, Progresso.cadastroUniversidade) || Str.equals(progresso.nomeProgresso, Progresso.cadastroUniversidadeRespostaNegativa) || Str.equals(progresso.nomeProgresso, Progresso.cadastroUniversidadeCancelado)) {
                return cadastrarUniversidade(alunoEncontrado, message);
            } else if (Str.equals(progresso.nomeProgresso, Progresso.cadastroUniversidadeResposta)) {
                if (MessageManager.checkAnswer(message)) {
                    return pedirCurso(alunoEncontrado, message);
                } else {
                    return cancelarUniversidade(alunoEncontrado, message);
                }
            }
        }

        return primeiroAcesso(alunoDAO, aluno, message);
    }

    private IntentDTO primeiroAcesso(AlunoDAO alunoDAO, Aluno aluno, String message) {
        /**
         * Nunca vimos o usuário
         */

        MensagemDominioDAO mensagemDominioDAO = new MensagemDominioDAO();
        MensagemDominio mensagemDominio = mensagemDominioDAO.findMessage(Progresso.cadastroInicial);

        String msg = mensagemDominio.corpoMensagemDominio;
        msg = MessageManager.replaceValue(msg, "nome", aluno.nomeUsuario);

        if (alunoDAO.cadastrar()) {
            aluno = alunoDAO.encontrarAluno();
            Progresso progresso = (new ProgressoDAO()).pegarProgresso(Progresso.cadastroInicial);
            Pesquisa pesquisa = new Pesquisa(progresso.id, aluno.id, message);
            PesquisaDAO pesquisaDAO = new PesquisaDAO(pesquisa);
            pesquisaDAO.criarPesquisa();
        }

        return new IntentDTO(msg, aluno.idTelegram);
    }

    private IntentDTO pedirUniversidade(Aluno aluno, String message) {
        /**
         * Solicita a universidade do usuário
         */

        MensagemDominioDAO mensagemDominioDAO = new MensagemDominioDAO();
        MensagemDominio mensagemDominio = mensagemDominioDAO.findMessage(Progresso.cadastroUniversidade);

        Progresso progresso = (new ProgressoDAO()).pegarProgresso(Progresso.cadastroUniversidade);
        Pesquisa pesquisa = new Pesquisa(progresso.id, aluno.id, message);
        PesquisaDAO pesquisaDAO = new PesquisaDAO(pesquisa);
        pesquisaDAO.criarPesquisa();

        String msg = mensagemDominio.corpoMensagemDominio;
        return new IntentDTO(msg, aluno.idTelegram);
    }

    private IntentDTO cancelarCadastro(Aluno aluno, String message) {
        /**
         * O usuário cancelou o cadastro
         */
        MensagemDominioDAO mensagemDominioDAO = new MensagemDominioDAO();
        MensagemDominio mensagemDominio = mensagemDominioDAO.findMessage(Progresso.cadastroCancelado);

        Progresso progresso = (new ProgressoDAO()).pegarProgresso(Progresso.cadastroCancelado);
        Pesquisa pesquisa = new Pesquisa(progresso.id, aluno.id, message);
        PesquisaDAO pesquisaDAO = new PesquisaDAO(pesquisa);
        pesquisaDAO.criarPesquisa();

        String msg = mensagemDominio.corpoMensagemDominio;
        return new IntentDTO(msg, aluno.idTelegram);
    }

    private IntentDTO primeiroAcessoCancelado(AlunoDAO alunoDAO, Aluno aluno, String message) {
        /**
         * Nunca vimos o usuário
         */

        MensagemDominioDAO mensagemDominioDAO = new MensagemDominioDAO();
        MensagemDominio mensagemDominio = mensagemDominioDAO.findMessage(Progresso.cadastroInicial);

        String msg = mensagemDominio.corpoMensagemDominio;
        msg = MessageManager.replaceValue(msg, "nome", aluno.nomeUsuario);

        Progresso progresso = (new ProgressoDAO()).pegarProgresso(Progresso.cadastroInicial);
        Pesquisa pesquisa = new Pesquisa(progresso.id, aluno.id, message);
        PesquisaDAO pesquisaDAO = new PesquisaDAO(pesquisa);
        pesquisaDAO.criarPesquisa();

        return new IntentDTO(msg, aluno.idTelegram);
    }

    private IntentDTO cadastrarUniversidade(Aluno aluno, String message) {
        Universidade universidade = new Universidade();
        universidade.nomeUniversidade = message;
        UniversidadeDAO universidadeDAO = new UniversidadeDAO(universidade);
        Universidade universidadeEncontrada = universidadeDAO.pegarUniversidade();

        String msg;

        if (universidadeEncontrada == null) {
            MensagemDominioDAO mensagemDominioDAO = new MensagemDominioDAO();
            MensagemDominio mensagemDominio = mensagemDominioDAO.findMessage(Progresso.cadastroUniversidadeRespostaNegativa);

            msg = mensagemDominio.corpoMensagemDominio;

            Progresso progresso = (new ProgressoDAO()).pegarProgresso(Progresso.cadastroUniversidadeRespostaNegativa);
            Pesquisa pesquisa = new Pesquisa(progresso.id, aluno.id, message);
            PesquisaDAO pesquisaDAO = new PesquisaDAO(pesquisa);
            pesquisaDAO.criarPesquisa();
        } else {
            aluno.idUniversidade = universidadeEncontrada.id;
            AlunoDAO alunoDAO = new AlunoDAO(aluno);
            alunoDAO.alterarUniversidade();

            MensagemDominioDAO mensagemDominioDAO = new MensagemDominioDAO();
            MensagemDominio mensagemDominio = mensagemDominioDAO.findMessage(Progresso.cadastroUniversidadeResposta);

            msg = mensagemDominio.corpoMensagemDominio;
            msg = MessageManager.replaceValue(msg, "universidade", universidadeEncontrada.nomeUniversidade);

            Progresso progresso = (new ProgressoDAO()).pegarProgresso(Progresso.cadastroUniversidadeResposta);
            Pesquisa pesquisa = new Pesquisa(progresso.id, aluno.id, message);
            PesquisaDAO pesquisaDAO = new PesquisaDAO(pesquisa);
            pesquisaDAO.criarPesquisa();
        }

        return new IntentDTO(msg, aluno.idTelegram);
    }

    private IntentDTO pedirCurso(Aluno aluno, String message) {
        /**
         * Solicita o curso do usuário
         */

        MensagemDominioDAO mensagemDominioDAO = new MensagemDominioDAO();
        MensagemDominio mensagemDominio = mensagemDominioDAO.findMessage(Progresso.cadastroCurso);

        Progresso progresso = (new ProgressoDAO()).pegarProgresso(Progresso.cadastroCurso);
        Pesquisa pesquisa = new Pesquisa(progresso.id, aluno.id, message);
        PesquisaDAO pesquisaDAO = new PesquisaDAO(pesquisa);
        pesquisaDAO.criarPesquisa();

        String msg = mensagemDominio.corpoMensagemDominio;
        return new IntentDTO(msg, aluno.idTelegram);
    }

    private IntentDTO cancelarUniversidade(Aluno aluno, String message) {
        /**
         * O usuário cancelou a universidade
         */

        AlunoDAO alunoDAO = new AlunoDAO(aluno);
        alunoDAO.cancelarUniversidade();

        MensagemDominioDAO mensagemDominioDAO = new MensagemDominioDAO();
        MensagemDominio mensagemDominio = mensagemDominioDAO.findMessage(Progresso.cadastroCancelado);

        Progresso progresso = (new ProgressoDAO()).pegarProgresso(Progresso.cadastroCancelado);
        Pesquisa pesquisa = new Pesquisa(progresso.id, aluno.id, message);
        PesquisaDAO pesquisaDAO = new PesquisaDAO(pesquisa);
        pesquisaDAO.criarPesquisa();

        String msg = mensagemDominio.corpoMensagemDominio;
        return new IntentDTO(msg, aluno.idTelegram);
    }
}

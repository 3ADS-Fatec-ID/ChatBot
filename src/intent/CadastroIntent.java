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
import model.Aluno;
import model.MensagemDominio;
import model.Pesquisa;
import model.Progresso;
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

        ProgressoDAO progressoDAO = new ProgressoDAO();
        Progresso progresso = progressoDAO.pegarProgresso(aluno);

        if (Str.equals(progresso.nomeProcesso, Progresso.cadastroInicial)) {
            System.out.println("Progresso está em cadastroInicial");
            if (MessageManager.checkAnswer(message)) {
                return cadastrarUniversidade(aluno);
            } else {
                return cancelarCadastro(aluno);
            }
        } else if (Str.equals(progresso.nomeProcesso, Progresso.cadastroCancelado)) {
            return primeiroAcesso(alunoDAO, aluno, message);
        } else {
            return primeiroAcesso(alunoDAO, aluno, message);
        }
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

    private IntentDTO cadastrarUniversidade(Aluno aluno) {
        /**
         * Solicita a universidade do usuário
         */

        MensagemDominioDAO mensagemDominioDAO = new MensagemDominioDAO();
        MensagemDominio mensagemDominio = mensagemDominioDAO.findMessage(Progresso.cadastroInicial);

        String msg = mensagemDominio.corpoMensagemDominio;
        msg = MessageManager.replaceValue(msg, "nome", aluno.nomeUsuario);

//        if (alunoDAO.cadastrar()) {
//            aluno = alunoDAO.encontrarAluno();
//            Progresso progresso = (new ProgressoDAO()).pegarProgresso(Progresso.cadastroInicial);
//            Pesquisa pesquisa = new Pesquisa(progresso.id, aluno.id, message);
//            PesquisaDAO pesquisaDAO = new PesquisaDAO(pesquisa);
//            pesquisaDAO.criarPesquisa();
//        }
        return new IntentDTO(msg, aluno.idTelegram);
    }

    private IntentDTO cancelarCadastro(Aluno aluno) {
        /**
         * O usuário cancelou o cadastro
         */
        MensagemDominioDAO mensagemDominioDAO = new MensagemDominioDAO();
        MensagemDominio mensagemDominio = mensagemDominioDAO.findMessage(Progresso.cadastroCancelado);

        String msg = mensagemDominio.corpoMensagemDominio;
        return new IntentDTO(msg, aluno.idTelegram);
    }
}

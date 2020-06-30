/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intent.register;

import dao.AlunoDAO;
import dao.CursoDAO;

import dao.MensagemDominioDAO;
import dao.PesquisaDAO;
import dao.ProgressoDAO;
import dao.UniversidadeDAO;
import intent.Intent;
import intent.IntentDTO;
import model.Student;
import model.Course;
import model.DomainMessage;
import model.Search;
import model.Progress;
import model.University;
import services.MessageManager;
import services.Str;

/**
 *
 * @author joao
 */
public class RegisterIntent extends Intent {

    /**
     * 0 - idTelegram 1 - nomeUsuario 2- msgRes
     *
     * @param args
     * @return
     */
    @Override
    public IntentDTO run(String... args) {
        Student aluno = new Student();
        aluno.telegramId = Long.parseLong(args[0]);
        aluno.name = args[1];
        String message = args[2];

        AlunoDAO alunoDAO = new AlunoDAO(aluno);

        Student alunoEncontrado = alunoDAO.encontrarAluno();
        if (alunoEncontrado != null) {
            ProgressoDAO progressoDAO = new ProgressoDAO();
            Progress progresso = progressoDAO.pegarProgresso(alunoEncontrado);

            if (Str.equals(progresso.name, Progress.initialRegistration)) {
                if (MessageManager.checkAnswer(message)) {
                    return pedirUniversidade(alunoEncontrado, message);
                } else {
                    return cancelarCadastro(alunoEncontrado, message);
                }
            } else if (Str.equals(progresso.name, Progress.registrationCanceled)) {
                return primeiroAcessoCancelado(alunoEncontrado, message);
            } else if (Str.equals(progresso.name, Progress.universityRegistration) || Str.equals(progresso.name, Progress.universityRegistrationResponseNegative) || Str.equals(progresso.name, Progress.universityRegistrationCanceled)) {
                return cadastrarUniversidade(alunoEncontrado, message);
            } else if (Str.equals(progresso.name, Progress.universityRegistrationResponse)) {
                if (MessageManager.checkAnswer(message)) {
                    return pedirCurso(alunoEncontrado, message);
                } else {
                    return cancelarUniversidade(alunoEncontrado, message);
                }
            } else if (Str.equals(progresso.name, Progress.courseRegistration) || Str.equals(progresso.name, Progress.courseRegistrationCanceled) || Str.equals(progresso.name, Progress.courseRegistrationResponseNegative)) {
                return cadastrarCurso(alunoEncontrado, message);
            } else if (Str.equals(progresso.name, Progress.courseRegistrationResponse)) {
                if (MessageManager.checkAnswer(message)) {
                    return pedirTermo(alunoEncontrado, message);
                } else {
                    return cancelarCurso(alunoEncontrado, message);
                }
            }
            else if (Str.equals(progresso.name, Progress.termsAcceptance)) {
                if (MessageManager.checkAnswer(message)) {
                	return cadastrarTermo(alunoEncontrado, message);
                } else {
                	return pedirTermo(alunoEncontrado, message);
                }
            }
        }
      
        return primeiroAcesso(alunoDAO, aluno, message);
    }

    private IntentDTO primeiroAcesso(AlunoDAO alunoDAO, Student aluno, String message) {
        /**
         * Nunca vimos o usuário
         */

        MensagemDominioDAO mensagemDominioDAO = new MensagemDominioDAO();
        DomainMessage mensagemDominio = mensagemDominioDAO.findMessage(Progress.initialRegistration);

        String msg = mensagemDominio.body;
        msg = MessageManager.replaceValue(msg, "nome", aluno.name);

        if (alunoDAO.cadastrar()) {
            aluno = alunoDAO.encontrarAluno();
            Progress progresso = (new ProgressoDAO()).pegarProgresso(Progress.initialRegistration);
            Search pesquisa = new Search(progresso.id, aluno.id, message);
            PesquisaDAO pesquisaDAO = new PesquisaDAO(pesquisa);
            pesquisaDAO.criarPesquisa();
        }

        return new IntentDTO(msg, aluno.telegramId);
    }

    private IntentDTO pedirUniversidade(Student aluno, String message) {
        /**
         * Solicita a universidade do usuário
         */

        MensagemDominioDAO mensagemDominioDAO = new MensagemDominioDAO();
        DomainMessage mensagemDominio = mensagemDominioDAO.findMessage(Progress.universityRegistration);

        Progress progresso = (new ProgressoDAO()).pegarProgresso(Progress.universityRegistration);
        Search pesquisa = new Search(progresso.id, aluno.id, message);
        PesquisaDAO pesquisaDAO = new PesquisaDAO(pesquisa);
        pesquisaDAO.criarPesquisa();

        String msg = mensagemDominio.body;
        return new IntentDTO(msg, aluno.telegramId);
    }

    private IntentDTO cancelarCadastro(Student aluno, String message) {
        /**
         * O usuário cancelou o cadastro
         */
        MensagemDominioDAO mensagemDominioDAO = new MensagemDominioDAO();
        DomainMessage mensagemDominio = mensagemDominioDAO.findMessage(Progress.registrationCanceled);

        Progress progresso = (new ProgressoDAO()).pegarProgresso(Progress.registrationCanceled);
        Search pesquisa = new Search(progresso.id, aluno.id, message);
        PesquisaDAO pesquisaDAO = new PesquisaDAO(pesquisa);
        pesquisaDAO.criarPesquisa();

        String msg = mensagemDominio.body;
        return new IntentDTO(msg, aluno.telegramId);
    }

    private IntentDTO primeiroAcessoCancelado(Student aluno, String message) {
        /**
         * Nunca vimos o usuário
         */

        MensagemDominioDAO mensagemDominioDAO = new MensagemDominioDAO();
        DomainMessage mensagemDominio = mensagemDominioDAO.findMessage(Progress.initialRegistration);

        String msg = mensagemDominio.body;
        msg = MessageManager.replaceValue(msg, "nome", aluno.name);

        Progress progresso = (new ProgressoDAO()).pegarProgresso(Progress.initialRegistration);
        Search pesquisa = new Search(progresso.id, aluno.id, message);
        PesquisaDAO pesquisaDAO = new PesquisaDAO(pesquisa);
        pesquisaDAO.criarPesquisa();

        return new IntentDTO(msg, aluno.telegramId);
    }

    private IntentDTO cadastrarUniversidade(Student aluno, String message) {
        University universidade = new University();
        universidade.name = message;
        UniversidadeDAO universidadeDAO = new UniversidadeDAO(universidade);
        University universidadeEncontrada = universidadeDAO.pegarUniversidade();

        String msg;

        if (universidadeEncontrada == null) {
            MensagemDominioDAO mensagemDominioDAO = new MensagemDominioDAO();
            DomainMessage mensagemDominio = mensagemDominioDAO.findMessage(Progress.universityRegistrationResponseNegative);

            msg = mensagemDominio.body;

            Progress progresso = (new ProgressoDAO()).pegarProgresso(Progress.universityRegistrationResponseNegative);
            Search pesquisa = new Search(progresso.id, aluno.id, message);
            PesquisaDAO pesquisaDAO = new PesquisaDAO(pesquisa);
            pesquisaDAO.criarPesquisa();
        } else {
            aluno.universityId = universidadeEncontrada.id;
            AlunoDAO alunoDAO = new AlunoDAO(aluno);
            alunoDAO.alterarUniversidade();

            MensagemDominioDAO mensagemDominioDAO = new MensagemDominioDAO();
            DomainMessage mensagemDominio = mensagemDominioDAO.findMessage(Progress.universityRegistrationResponse);

            msg = mensagemDominio.body;
            msg = MessageManager.replaceValue(msg, "universidade", universidadeEncontrada.name);

            Progress progresso = (new ProgressoDAO()).pegarProgresso(Progress.universityRegistrationResponse);
            Search pesquisa = new Search(progresso.id, aluno.id, message);
            PesquisaDAO pesquisaDAO = new PesquisaDAO(pesquisa);
            pesquisaDAO.criarPesquisa();
        }

        return new IntentDTO(msg, aluno.telegramId);
    }

    private IntentDTO pedirCurso(Student aluno, String message) {
        /**
         * Solicita o curso do usuário
         */

        MensagemDominioDAO mensagemDominioDAO = new MensagemDominioDAO();
        DomainMessage mensagemDominio = mensagemDominioDAO.findMessage(Progress.courseRegistration);

        Progress progresso = (new ProgressoDAO()).pegarProgresso(Progress.courseRegistration);
        Search pesquisa = new Search(progresso.id, aluno.id, message);
        PesquisaDAO pesquisaDAO = new PesquisaDAO(pesquisa);
        pesquisaDAO.criarPesquisa();

        String msg = mensagemDominio.body;
        return new IntentDTO(msg, aluno.telegramId);
    }

    private IntentDTO cancelarUniversidade(Student aluno, String message) {
        /**
         * O usuário cancelou a universidade
         */

        AlunoDAO alunoDAO = new AlunoDAO(aluno);
        alunoDAO.cancelarUniversidade();

        MensagemDominioDAO mensagemDominioDAO = new MensagemDominioDAO();
        DomainMessage mensagemDominio = mensagemDominioDAO.findMessage(Progress.universityRegistrationCanceled);

        Progress progresso = (new ProgressoDAO()).pegarProgresso(Progress.universityRegistrationCanceled);
        Search pesquisa = new Search(progresso.id, aluno.id, message);
        PesquisaDAO pesquisaDAO = new PesquisaDAO(pesquisa);
        pesquisaDAO.criarPesquisa();

        String msg = mensagemDominio.body;
        return new IntentDTO(msg, aluno.telegramId);
    }

    private IntentDTO cadastrarCurso(Student aluno, String message) {
        Course curso = new Course();
        curso.name = message;
        curso.initials = message;
        CursoDAO cursoDAO = new CursoDAO(curso);
        Course cursoEncontrado = cursoDAO.pegarCurso(aluno.universityId);

        String msg;

        if (cursoEncontrado == null) {
            MensagemDominioDAO mensagemDominioDAO = new MensagemDominioDAO();
            DomainMessage mensagemDominio = mensagemDominioDAO.findMessage(Progress.courseRegistrationCanceled);

            msg = mensagemDominio.body;

            Progress progresso = (new ProgressoDAO()).pegarProgresso(Progress.courseRegistrationCanceled);
            Search pesquisa = new Search(progresso.id, aluno.id, message);
            PesquisaDAO pesquisaDAO = new PesquisaDAO(pesquisa);
            pesquisaDAO.criarPesquisa();
        } else {
            aluno.universityCourseId = cursoEncontrado.universityCourseId;
            AlunoDAO alunoDAO = new AlunoDAO(aluno);
            alunoDAO.alterarCurso();

            MensagemDominioDAO mensagemDominioDAO = new MensagemDominioDAO();
            DomainMessage mensagemDominio = mensagemDominioDAO.findMessage(Progress.courseRegistrationResponse);

            msg = mensagemDominio.body;
            msg = MessageManager.replaceValue(msg, "curso", cursoEncontrado.name);

            Progress progresso = (new ProgressoDAO()).pegarProgresso(Progress.courseRegistrationResponse);
            Search pesquisa = new Search(progresso.id, aluno.id, message);
            PesquisaDAO pesquisaDAO = new PesquisaDAO(pesquisa);
            pesquisaDAO.criarPesquisa();
        }

        return new IntentDTO(msg, aluno.telegramId);
    }
    
    private IntentDTO cancelarCurso(Student aluno, String message) {
        /**
         * O usuário cancelou a universidade
         */

        AlunoDAO alunoDAO = new AlunoDAO(aluno);
        alunoDAO.cancelarCurso();

        MensagemDominioDAO mensagemDominioDAO = new MensagemDominioDAO();
        DomainMessage mensagemDominio = mensagemDominioDAO.findMessage(Progress.courseRegistrationResponseNegative);

        Progress progresso = (new ProgressoDAO()).pegarProgresso(Progress.courseRegistrationResponseNegative);
        Search pesquisa = new Search(progresso.id, aluno.id, message);
        PesquisaDAO pesquisaDAO = new PesquisaDAO(pesquisa);
        pesquisaDAO.criarPesquisa();

        String msg = mensagemDominio.body;
        return new IntentDTO(msg, aluno.telegramId);
    }
    
    private IntentDTO pedirTermo(Student aluno, String message) {
        /**
         * Solicita o termo de Aceite
         */

        MensagemDominioDAO mensagemDominioDAO = new MensagemDominioDAO();
        DomainMessage mensagemDominio = mensagemDominioDAO.findMessage(Progress.termsAcceptance);

        Progress progresso = (new ProgressoDAO()).pegarProgresso(Progress.termsAcceptance);
        Search pesquisa = new Search(progresso.id, aluno.id, message);
        PesquisaDAO pesquisaDAO = new PesquisaDAO(pesquisa);
        pesquisaDAO.criarPesquisa();

        String msg = mensagemDominio.body;
        return new IntentDTO(msg, aluno.telegramId);
    }
    
    private IntentDTO cadastrarTermo(Student aluno, String message) {
        /**
         * Cadastra o Aluno
         */
    	aluno.termAccepted = true;
        AlunoDAO alunoDAO = new AlunoDAO(aluno);
        alunoDAO.alterarTermoAceite();
        
        MensagemDominioDAO mensagemDominioDAO = new MensagemDominioDAO();
        DomainMessage mensagemDominio = mensagemDominioDAO.findMessage(Progress.registrationCompleted);

        Progress progresso = (new ProgressoDAO()).pegarProgresso(Progress.registrationCompleted);
        Search pesquisa = new Search(progresso.id, aluno.id, message);
        PesquisaDAO pesquisaDAO = new PesquisaDAO(pesquisa);
        pesquisaDAO.criarPesquisa();

        String msg = mensagemDominio.body;
        return new IntentDTO(msg, aluno.telegramId);
    }

}

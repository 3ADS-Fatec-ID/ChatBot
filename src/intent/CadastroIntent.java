/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intent;

import java.lang.invoke.SwitchPoint;

import dao.MensagemDominioDAO;
import dao.PesquisaDAO;
import dao.ProgressoDAO;
import model.Aluno;
import model.MensagemDominio;
import model.Pesquisa;
import model.Progresso;
import services.MessageManager;

/**
 *
 * @author joao
 */
public class CadastroIntent extends Intent {

    @Override
    public IntentDTO run(String... args) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public IntentDTO realizarCadastro(Aluno aluno) {
    	
    ProgressoDAO progressoDAO = new ProgressoDAO();
    Progresso progresso = new Progresso();
    progresso = progressoDAO.pegarProgresso(aluno.id);
    String nomeProgresso = progresso.nomeProcesso;
    
    switch (nomeProgresso) {  
      case "Vitor".hashCode() : System.out.println("Valor de nome é Vitor");  
      break;   
      case "Paulo".hashCode() : System.out.println("Valor de nome é Paulo");  
      break;   
      default: System.out.println("Default");  
    }
    
    
    	if (true) {
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
                Pesquisa pesquisa = new Pesquisa(progresso.id, aluno.id, msgRec);
                PesquisaDAO pesquisaDAO = new PesquisaDAO(pesquisa);
                pesquisaDAO.criarPesquisa();
            }

            return new IntentDTO(msg, aluno.idTelegram);
        }
    }

    
}

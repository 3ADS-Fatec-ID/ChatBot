package teste;

import model.AlunoDAO;

public class AlunoInserir {

	public static void main(String[] args) {
		AlunoDAO dao = new AlunoDAO();
		dao.nomeUsuario = "Fernando";
		dao.id_telegram = 880756362;
		dao.termoAceite = true;
		dao.id_curso_universidade = 1 ;
		System.out.println(dao.cadastrar());

	}

}

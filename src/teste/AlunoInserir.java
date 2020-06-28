package teste;

import dao.AlunoDAO;
import model.Aluno;

public class AlunoInserir {

    public static void main(String[] args) {
        Aluno aluno = new Aluno();
        aluno.nomeUsuario = "Fernando";
        aluno.idTelegram = 880756362;
        aluno.termoAceite = true;
        aluno.idCursoUniversidade = 1;

        AlunoDAO dao = new AlunoDAO(aluno);
        System.out.println(dao.cadastrar());
    }
}

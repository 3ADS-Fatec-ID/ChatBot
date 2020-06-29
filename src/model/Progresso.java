/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author joao
 */
public class Progresso {

    public int id;
    public String nomeProgresso;

    public static String cadastroInicial = "CAD_INICIO";
    public static String cadastroCancelado = "CAD_INICIO_NEG";
    public static String cadastroUniversidade = "CAD_UNI";
    public static String cadastroUniversidadeCancelado = "CAD_UNI_NEG";
    public static String cadastroUniversidadeResposta = "CAD_RES_UNI";
    public static String cadastroUniversidadeRespostaNegativa = "CAD_RES_UNI_NEG";
    public static String cadastroCurso = "CAD_CURSO";
    public static String cadastroCursoNegativa = "CAD_CURSO_NEG";
    public static String cadastroCursoResposta = "CAD_CURSO_RESP";
    public static String cadastroCursoRespostaNegativa = "CAD_CURSO_RESP_NEG";

//INSERT INTO Progresso VALUES ('CAD_RES_UNI')
//INSERT INTO Progresso VALUES ('CAD_RES_UNI_NEG')
//INSERT INTO Progresso VALUES ('CAD_RES_UNI_NEG_LISTA')
//INSERT INTO Progresso VALUES ('CAD_CURSO')
//INSERT INTO Progresso VALUES ('CAD_CURSO_NEG')
//INSERT INTO Progresso VALUES ('CAD_CURSO_RESP')
//INSERT INTO Progresso VALUES ('CAD_CURSO_RESP_NEG')
//INSERT INTO Progresso VALUES ('CAD_FIM')
//INSERT INTO Progresso VALUES ('CAD_ERRO')
    public static String confirmacao = "CONF";
    public static String negacao = "NEG";

    public Progresso() {
    }

    public Progresso(int id, String nomeProcesso) {
        this.id = id;
        this.nomeProgresso = nomeProcesso;
    }

    @Override
    public String toString() {
        return "Progresso{" + "id=" + id + ", nomeProgresso=" + nomeProgresso + '}';
    }
}

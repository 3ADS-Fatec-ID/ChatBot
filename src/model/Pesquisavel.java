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
public class Pesquisavel {

    public int id, idAcervo, idDuvida;

    public Pesquisavel() {
    }

    public Pesquisavel(int id, int idAcervo, int idDuvida) {
        this.id = id;
        this.idAcervo = idAcervo;
        this.idDuvida = idDuvida;
    }

    @Override
    public String toString() {
        return "Pesquisavel{" + "id=" + id + ", idAcervo=" + idAcervo + ", idDuvida=" + idDuvida + '}';
    }
}

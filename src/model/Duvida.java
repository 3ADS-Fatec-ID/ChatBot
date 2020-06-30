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
public class Duvida {

    public int id;
    public String nomeDuvida, descricaoDuvida;

    public Duvida() {
    }

    public Duvida(int id, String nomeDuvida, String descricaoDuvida) {
        this.id = id;
        this.nomeDuvida = nomeDuvida;
        this.descricaoDuvida = descricaoDuvida;
    }

    @Override
    public String toString() {
        return "Duvida{" + "id=" + id + ", nomeDuvida=" + nomeDuvida + ", descricaoDuvida=" + descricaoDuvida + '}';
    }
}

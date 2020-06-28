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
    public String nomeProcesso;

    public Progresso() {
    }

    public Progresso(int id, String nomeProcesso) {
        this.id = id;
        this.nomeProcesso = nomeProcesso;
    }

    @Override
    public String toString() {
        return "Progresso{" + "id=" + id + ", nomeProcesso=" + nomeProcesso + '}';
    }
}

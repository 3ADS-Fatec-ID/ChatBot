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
public class PalavraChave {

    public int id;
    public String nomePalavraChave;

    public PalavraChave() {
    }

    public PalavraChave(int id, String nomePalavraChave) {
        this.id = id;
        this.nomePalavraChave = nomePalavraChave;
    }

    @Override
    public String toString() {
        return "PalavraChave{" + "id=" + id + ", nomePalavraChave=" + nomePalavraChave + '}';
    }
}

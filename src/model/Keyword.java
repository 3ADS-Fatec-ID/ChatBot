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
public class Keyword {

    public int id;
    public String name;

    public Keyword() {
    }

    public Keyword(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "PalavraChave{" + "id=" + id + ", nomePalavraChave=" + name + '}';
    }
}

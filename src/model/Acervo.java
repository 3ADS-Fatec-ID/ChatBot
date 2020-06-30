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
public class Acervo {

    public int id;
    public String autor, tema, orientador;

    public Acervo() {
    }

    public Acervo(int id, String autor, String tema, String orientador) {
        this.id = id;
        this.autor = autor;
        this.tema = tema;
        this.orientador = orientador;
    }

    @Override
    public String toString() {
        return "Acervo{" + "id=" + id + ", autor=" + autor + ", tema=" + tema + ", orientador=" + orientador + '}';
    }
}

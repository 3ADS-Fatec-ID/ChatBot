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
public class Collection {

    public int id;
    public String author, theme, advisor;

    public Collection() {
    }

    public Collection(int id, String author, String theme, String advisor) {
        this.id = id;
        this.author = author;
        this.theme = theme;
        this.advisor = advisor;
    }

    @Override
    public String toString() {
        return "Acervo{" + "id=" + id + ", autor=" + author + ", tema=" + theme + ", orientador=" + advisor + '}';
    }
}

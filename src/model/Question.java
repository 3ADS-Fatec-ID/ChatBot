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
public class Question {

    public int id;
    public String name, description;

    public Question() {
    }

    public Question(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Duvida{" + "id=" + id + ", nomeDuvida=" + name + ", descricaoDuvida=" + description + '}';
    }
}

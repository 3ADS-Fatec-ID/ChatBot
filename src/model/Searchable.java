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
public class Searchable {

    public int id, collectionId, questionId;

    public Searchable() {
    }

    public Searchable(int id, int collectionId, int questionId) {
        this.id = id;
        this.collectionId = collectionId;
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return "Pesquisavel{" + "id=" + id + ", idAcervo=" + collectionId + ", idDuvida=" + questionId + '}';
    }
}

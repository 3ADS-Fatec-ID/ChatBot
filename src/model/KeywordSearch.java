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
public class KeywordSearch {

    public int id, keywordId, courseId, searchableId;

    public KeywordSearch() {
    }

    public KeywordSearch(int id, int keywordId, int courseId, int searchableId) {
        this.id = id;
        this.keywordId = keywordId;
        this.courseId = courseId;
        this.searchableId = searchableId;
    }

    @Override
    public String toString() {
        return "PalavraChavePesquisa{" + "id=" + id + ", idPalavraChave=" + keywordId + ", idCurso=" + courseId + ", idPesquisavel=" + searchableId + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        final KeywordSearch other = (KeywordSearch) obj;
        return this.searchableId == other.searchableId;
    }
}

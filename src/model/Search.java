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
public class Search {

    public int id, progressId, userId;
    public String body;

    public Search() {
    }

    public Search(int id, int progressId, int userId, String body) {
        this.id = id;
        this.progressId = progressId;
        this.userId = userId;
        this.body = body;
    }

    public Search(int progressId, int userId, String body) {
        this.progressId = progressId;
        this.userId = userId;
        this.body = body;
    }

    public Search(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Pesquisa{" + "id=" + id + ", progressId=" + progressId + ", userId=" + userId + ", body=" + body + '}';
    }
}

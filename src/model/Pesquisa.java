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
public class Pesquisa {

    public int id, idProgresso, idUsuario;
    public String corpo;

    public Pesquisa() {
    }

    public Pesquisa(int id, int idProgresso, int idUsuario, String corpo) {
        this.id = id;
        this.idProgresso = idProgresso;
        this.idUsuario = idUsuario;
        this.corpo = corpo;
    }

    public Pesquisa(int idProgresso, int idUsuario, String corpo) {
        this.idProgresso = idProgresso;
        this.idUsuario = idUsuario;
        this.corpo = corpo;
    }

    public Pesquisa(int idUsuario) {
        super();
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "Pesquisa{" + "id=" + id + ", idProgresso=" + idProgresso + ", idUsuario=" + idUsuario + ", corpo=" + corpo + '}';
    }
}

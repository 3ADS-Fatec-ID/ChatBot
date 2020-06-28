/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Progresso;

/**
 *
 * @author joao
 */
public class ProgressoDAO extends DAO {

    private final Progresso progresso;

    public ProgressoDAO(Progresso progresso) {
        this.progresso = progresso;
    }
}

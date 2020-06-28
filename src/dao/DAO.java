/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import services.BD;

/**
 *
 * @author joao
 */
public class DAO {

    protected BD bd;

    public DAO() {
        this.bd = new BD();
    }

}

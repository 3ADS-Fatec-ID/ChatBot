/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Timestamp;
import services.BD;

/**
 *
 * @author joao
 */
public class DAO {

    public static Timestamp getCurrentTimeStamp() {

        java.util.Date today = new java.util.Date();
        return new Timestamp(today.getTime());

    }

    protected BD bd;

    public DAO() {
        this.bd = new BD();
    }
}

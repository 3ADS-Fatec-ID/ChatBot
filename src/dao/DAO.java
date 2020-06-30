/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Timestamp;
import services.DatabaseManager;

/**
 *
 * @author joao
 */
public class DAO {

    public static Timestamp getCurrentTimeStamp() {

        java.util.Date today = new java.util.Date();
        return new Timestamp(today.getTime());

    }

    protected DatabaseManager bd;

    public DAO() {
        this.bd = new DatabaseManager();
    }
}

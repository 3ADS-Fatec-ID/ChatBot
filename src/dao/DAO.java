/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import services.BD;

/**
 *
 * @author joao
 */
public class DAO {

    LocalDateTime localDateTime = LocalDateTime.now();
    LocalDate localDate = localDateTime.toLocalDate();
    protected BD bd;

    public DAO() {
        this.bd = new BD();
    }

}

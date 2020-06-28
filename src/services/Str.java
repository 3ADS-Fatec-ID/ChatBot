/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author joao
 */
public class Str {

    public static boolean equals(String value, String equalsTo) {
        return value == null ? equalsTo == null : value.equals(equalsTo);
    }
}

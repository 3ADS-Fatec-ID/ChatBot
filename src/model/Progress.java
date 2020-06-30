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
public class Progress {

    public int id;
    public String name;

    public static String initialRegistration = "CAD_INICIO";
    public static String registrationCanceled = "CAD_INICIO_NEG";
    public static String universityRegistration = "CAD_UNI";
    public static String universityRegistrationCanceled = "CAD_UNI_NEG";
    public static String universityRegistrationResponse = "CAD_RES_UNI";
    public static String universityRegistrationResponseNegative = "CAD_RES_UNI_NEG";
    public static String courseRegistration = "CAD_CURSO";
    public static String courseRegistrationCanceled = "CAD_CURSO_NEG";
    public static String courseRegistrationResponse = "CAD_CURSO_RESP";
    public static String courseRegistrationResponseNegative = "CAD_CURSO_RESP_NEG";
    public static String termsAcceptance = "CAD_TERMO";
    public static String termsRefused = "CAD_TERMO_NEG";
    public static String registrationCompleted = "CAD_FIM";
    public static String searchNotFound = "PES_RES_NEG";
    public static String help = "HELP";
    public static String confirmation = "CONF";
    public static String negative = "NEG";

    public Progress() {
    }

    public Progress(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Progresso{" + "id=" + id + ", nomeProgresso=" + name + '}';
    }
}

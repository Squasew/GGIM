/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.model;

/**
 * Interfaz de GestionUsuarios
 * @author Ismael Molano
 */
public interface GestionUsuarios {

    /**
     * Metodo publico que recibe dos strings y devuelve un booleano a true si
     * coinciden los datos de nombre de usuario y contraseña, mientras que devuelve
     * false si no conciden
     * @param uname nombre de usuario
     * @param pass contraseña de usuario
     * @return boolean
     */
    public Boolean existUser(String uname, String pass);

    /**
     * Metodo publico que recibe un strings y devuelve un booleano a true si
     * el nombre de usuario es administrador, mientras que devuelve
     * false si no lo es 
     * @param uname nombre de usuario
     * @return boolean
     */
    public Boolean isAdmin(String uname);
}

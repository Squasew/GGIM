/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.model;

import java.util.logging.Logger;

/**
 * Clase que comprueba un usuario
 * @author Ismael Molano
 */
public class GestionUsuariosTest implements GestionUsuarios {
    private static final Logger LOGGER = Logger.getLogger( GestionUsuariosTest.class.getName() );
    /**
     * Metodo publico que recibe dos strings y devuelve un booleano a true si
     * coinciden los datos de nombre de usuario y contraseña, mientras que devuelve
     * false si no conciden
     * @param uname nombre de usuario
     * @param pass contraseña de usuario
     * @return boolean
     */
    @Override
    public Boolean existUser(String uname, String pass) {
        boolean ok=false;
        if(uname.equals("isma")&&pass.equals("123")){
            ok= true;
            LOGGER.info("nombre de usuario y contraseña correcta");
        }else{
            ok= false;
            LOGGER.info("nombre de usuario o contraseña incorrecto");
        }
        return ok;
    }
    /**
     * Metodo publico que recibe un strings y devuelve un booleano a true si
     * el nombre de usuario es administrador, mientras que devuelve
     * false si no lo es 
     * @param uname nombre de usuario
     * @return boolean
     */
    @Override
    public Boolean isAdmin(String uname) {
        if(uname.equals("isma")){
            LOGGER.info("Es administrador");
            return true;
        }else{
            LOGGER.info("No es administrador");
            return false;
        }
    }
    
}

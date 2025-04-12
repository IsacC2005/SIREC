/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conect.error_manager;

import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author isacc-PC
 */
public class error_manager {
    private static int error = 0;
    public static void error(SQLException e){
        error = e.getErrorCode();
        System.out.println(error);
        switch(error){
            case 0:
                show_mensaje(String.valueOf("Error: " + error +", el servidor de mysql no esta activo :/"));
                break;
            case 1045:
                show_mensaje(String.valueOf("Error: "+error+" Los credeciales de la base de datos no son correctos,  :/"));
                break;
            case 1049:
                show_mensaje(String.valueOf("La base de datos no existe"));
                break;
            default:
                show_mensaje(String.valueOf("Error " + error + ", el sistema no pudo inicar correctamente :( \n"+"Mensaje de error "+ e.getMessage()+",  :/"));
        }
    }
    
    private static void show_mensaje(String e){
        JOptionPane.showMessageDialog(null, e);
        System.exit(0);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author isacc
 */
public class ingresar {
    
   public static void main(){
       Connection con = OpenBd.conectar();
           PreparedStatement query = null;
           String nombre = "";
           String apellido = "";
       
       try {
           query = con.prepareStatement("insert into habitante values (null, 32662, ?,?)");
           query.setString(2, nombre);
           query.setString(3, apellido);
           
           query.execute();
       } catch (SQLException ex) {
           ex.printStackTrace();
       }finally{
           OpenBd.desconectar();
       }
   }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conect;
import com.mysql.jdbc.CommunicationsException;
import conect.error_manager.error_manager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


/**
 *
 * @author isacc
 */
public class OpenBd {
    
    static Connection con = null;
    static String host = "jdbc:mysql://localhost:3306/nucleocomunal";
    static String user = "root";
    static String clave = "";
    
  public static Connection conectar(){
      
        try {
            con = DriverManager.getConnection(host, user, clave);
            
        }  catch(SQLException ex){
            error_manager.error(ex);
        }
      
      return con;
  }
  public static Connection desconectar(){
        try {
            con.close();
        } catch (SQLException ex) {
            error_manager.error(ex);
        }
      return con;
  } 
}

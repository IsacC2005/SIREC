/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conect.OpenBd;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author isacc
 */
public class usuario {

    static String usuarioo = "";
    static String psw = "";
    static String tipo = "";
    static String semilla = "";
    static String pregunta = "";
    static String respuesta = "";

    public static String getUsuarioo() {
        return usuarioo;
    }

    public static void setUsuarioo(String usuarioo) {
        usuario.usuarioo = usuarioo;
    }

    public static String getPsw() {
        return psw;
    }

    public static void setPsw(String psw) {
        usuario.psw = psw;
    }

    public static String getTipo() {
        return tipo;
    }

    public static void setTipo(String tipo) {
        usuario.tipo = tipo;
    }

    public static String getSemilla() {
        return semilla;
    }

    public static void setSemilla(String semilla) {
        usuario.semilla = semilla;
    }

    public static String getPregunta() {
        return pregunta;
    }

    public static void setPregunta(String pregunta) {
        usuario.pregunta = pregunta;
    }

    public static String getRespuesta() {
        return respuesta;
    }

    public static void setRespuesta(String respuesta) {
        usuario.respuesta = respuesta;
    }

    public static String[] login() {
        java.sql.Connection con = OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        String[] datos = new String[2];
        String usuario;
        String psw;

        try {
            query = con.prepareStatement("Select * from usuario WHERE user = '" + getUsuarioo() + "';");
            rs = query.executeQuery();
            while (rs.next()) {
                usuario = rs.getString("user");
                setUsuarioo(usuario);
                psw = rs.getString("psw");
                datos[0] = usuario;
                datos[1] = psw;
            }
        } catch (SQLException ex) {
            Logger.getLogger(usuario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            OpenBd.desconectar();
        }
        return datos;
    }

    public static void modificar(String nUsuario, String nPsw) {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;

        try {
            query = con.prepareStatement("UPDATE usuario set user = '" + nUsuario + "', psw = '" + nPsw + "' WHERE `usuario`.`user` = '" + getUsuarioo() + "';");
            query.execute();
            JOptionPane.showMessageDialog(null, "Su usuario y contraseña han sido modificados sin problemas");
        } catch (SQLException ex) {
            Logger.getLogger(usuario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            OpenBd.desconectar();
        }
    }

    public static void agregar() {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;

        try {
            query = con.prepareStatement("INSERT INTO `usuario` VALUES (?,?,?,?,?,?);");
            query.setString(1, getUsuarioo());
            query.setString(2, getPsw());
            query.setString(3, getTipo());
            query.setString(4, getSemilla());

            if (getPregunta().equals("")) {
                query.setString(5, null);
            } else {
                query.setString(5, getPregunta());
            }
            if (getRespuesta().equals("")) {
                query.setString(6, null);
            } else {
                query.setString(6, getRespuesta());
            }

            query.execute();
            JOptionPane.showMessageDialog(null, "Usuario creado correctamente");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conect.OpenBd.desconectar();
        }
    }

    public static ArrayList<String[]> rescueAll() {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        ArrayList<String[]> lista = new ArrayList<>();

        try {

            query = con.prepareStatement("SELECT * FROM usuario");
            rs = query.executeQuery();

            while (rs.next()) {
                String[] resutaldo = new String[6];
                resutaldo[0] = rs.getString("user");
                resutaldo[1] = rs.getString("psw");
                resutaldo[2] = rs.getString("tipo");
                resutaldo[3] = String.valueOf(rs.getInt("cemilla"));
                resutaldo[4] = rs.getString("pregunta");
                resutaldo[5] = rs.getString("respuesta");

                lista.add(resutaldo);
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conect.OpenBd.desconectar();
        }
        return lista;
    }

    public static boolean comprobarTipoUser(String tipo) {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        boolean resultado = false;
        System.out.println(tipo);

        try {

            query = con.prepareStatement("SELECT COUNT(*) FROM usuario u where u.user = '" + getUsuarioo() + "' AND u.tipo = '" + tipo + "'");
            rs = query.executeQuery();
            System.out.println(query.toString());
            while (rs.next()) {
                if (rs.getInt("COUNT(*)") != 0) {

                    resultado = true;
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conect.OpenBd.desconectar();
        }
        return resultado;
    }
    public static boolean esistUsuario(String user){
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        boolean resultado = true;
        
        try {
            query = con.prepareStatement("SELECT COUNT(*) FROM usuario u WHERE u.user = '"+user+"' ");  
            System.out.println(query.toString());
            rs = query.executeQuery();
            
            while(rs.next()){
                if(rs.getInt("COUNT(*)") != 0){
                    resultado = false;
                }
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            conect.OpenBd.desconectar();
        }
        System.out.println(resultado + " Este es el valor de resultado");
        return resultado;
    }

    public static String buscarPregunta(String user) {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        String pregunta = "";

        try {

            query = con.prepareStatement("SELECT us.pregunta FROM `usuario` us WHERE us.user = '" + user + "';");
            rs = query.executeQuery();

            while (rs.next()) {
                pregunta = rs.getString("pregunta");
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conect.OpenBd.desconectar();
        }
        return pregunta;
    }

    public static boolean validarCemilla(String usuario, String cemilla) {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        boolean resultado = false;

        try {

            query = con.prepareStatement("SELECT COUNT(*) FROM usuario u WHERE u.user = ?  AND cemilla = ?");
            query.setString(1, usuario);
            query.setString(2, cemilla);
            rs = query.executeQuery();

            while (rs.next()) {
                if (rs.getInt("COUNT(*)") != 0) {
                    resultado = true;
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conect.OpenBd.desconectar();
        }
        return resultado;
    }

    public static boolean validarPregunta(String respuesta, String usuario) {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        boolean resultado = false;

        try {

            query = con.prepareStatement("SELECT COUNT(*) FROM usuario u WHERE u.user = ?  AND respuesta = ?");
            query.setString(1, usuario);
            query.setString(2, respuesta);
            rs = query.executeQuery();

            while (rs.next()) {
                if (rs.getInt("COUNT(*)") != 0) {
                    resultado = true;
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conect.OpenBd.desconectar();
        }
        return resultado;
    }

    public static boolean whatPregunta(String user) {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        boolean resultado = false;
        try {
            query = con.prepareStatement("SELECT * FROM `usuario` us WHERE  us.user = '" + user + "'");
            rs = query.executeQuery();
            while (rs.next()) {
                if (rs.getString("pregunta").equals("") == false && rs.getString("respuesta").equals("") == false) {
                    resultado = true;
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conect.OpenBd.desconectar();
        }
        return resultado;
    }

    public static boolean stdControlDeLogin() {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        boolean resultado = false;

        try {

            query = con.prepareStatement("SELECT * FROM controldelogin;");
            rs = query.executeQuery();

            while (rs.next()) {
                if (rs.getInt("estado") != 0) {
                    resultado = true;
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conect.OpenBd.desconectar();
        }
        return resultado;
    }

    public static void controlDeLogin(int estado) {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;

        try {

            query = con.prepareStatement("UPDATE `controldelogin` SET `estado` = '" + estado + "';");
            query.execute();

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conect.OpenBd.desconectar();
        }
    }
}

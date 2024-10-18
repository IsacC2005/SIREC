/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Ezequiel Isacc
 */
public class demografia {

    static private Integer idStrike = 0;
    static private String Strike = " ";
    static private Integer idLider = 0;
    static private String nombreL = "";

    public static Integer getIdStrike() {
        return idStrike;
    }

    public static void setIdStrike(Integer idStrike) {
        demografia.idStrike = idStrike;
    }

    public static String getNombreL() {
        return nombreL;
    }

    public static void setNombreL(String nombreL) {
        demografia.nombreL = nombreL;
    }

    public static String getStrike() {
        return Strike;
    }

    public static void setStrike(String Strike) {
        demografia.Strike = Strike;
    }

    public static Integer getIdLider() {
        return idLider;
    }

    public static void setIdLider(Integer idLider) {
        demografia.idLider = idLider;
    }

    //
    //@Esta funcion sirve para agregar una nueva calle
    //
    public static void addStrike() {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;

        try {
            query = con.prepareStatement("INSERT INTO `demografia` VALUES (null, '" + getStrike() + "',null)");
            //query.setString(1, getStrike());
            query.execute();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conect.OpenBd.desconectar();
        }
    }

    //
    //@Esta función asigna un lider de calle a una calle ya existente
    //
    public static void addLiderStk() {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;

        try {
            query = con.prepareStatement("UPDATE `demografia` SET `id_lider` = ? WHERE `demografia`.`id` = ?;");
            query.setInt(1, getIdLider());
            query.setInt(2, idStrike());
            query.execute();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conect.OpenBd.desconectar();
        }
    }

    //
    //
    public static void modStrike(int id, String nombre) {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;

        try {
            if (estStrike(nombre) == false) {
                query = con.prepareStatement("UPDATE `demografia` SET `calle` = '" + nombre + "' WHERE `demografia`.`id` =  " + id + " ");
                query.execute();
                JOptionPane.showMessageDialog(null, "La Calle fue modificada correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Ya existe una calle con ese nombre");
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conect.OpenBd.desconectar();
        }
    }

    //
    //@Esta función devuelve el nombre de las calles y su id
    //
    public static ArrayList<String[]> rsStrike() {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ArrayList<String[]> lista = new ArrayList<>();
        ResultSet rs;

        try {
            query = con.prepareStatement("SELECT * FROM `demografia`");
            rs = query.executeQuery();

            while (rs.next()) {
                String[] registro = new String[2];
                registro[0] = String.valueOf(rs.getInt("id"));
                registro[1] = rs.getString("Calle");
                lista.add(registro);
            }
        } catch (Exception e) {
        } finally {
            conect.OpenBd.desconectar();
        }
        return lista;
    }
    //
    //@Esta función sirve para encontrar el id de una calle
    //

    public static int idStrike() {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        int resultado = 0;

        try {
            query = con.prepareStatement("SELECT * FROM demografia WHERE calle = '" + getStrike() + "'; ");
            rs = query.executeQuery();

            while (rs.next()) {
                resultado = rs.getInt("id");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conect.OpenBd.desconectar();
        }
        return resultado;
    }

    //
    //@Esta función devuelve la cantidad de calles que hay
    //
    public static int cantidadStrike() {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs;
        int i = 0;

        try {
            query = con.prepareStatement("SELECT COUNT(*) FROM demografia ");
            rs = query.executeQuery();

            while (rs.next()) {
                i = rs.getInt("COUNT(*)");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conect.OpenBd.desconectar();
        }
        return i;
    }

    //
    //@Esta función devuelve de lideres de calle que hay
    //
    public static int cantidadLiderSk() {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs;
        int i = 0;

        try {
            query = con.prepareStatement("SELECT COUNT(*) FROM `lider_calle` ");
            rs = query.executeQuery();

            while (rs.next()) {
                i = rs.getInt("COUNT(*)");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conect.OpenBd.desconectar();
        }
        return i;
    }

    //
    //@Esta función devuelve los lideres de calle en conjunto de el nombre de la calle
    //
    public static ArrayList<String[]> rescuAll() {
        ArrayList<String[]> lista = new ArrayList<>();
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;

        try {
            query = con.prepareStatement(" SELECT *"
                    + " FROM demografia d "
                    + " LEFT JOIN persona p "
                    + " ON p.id = d.id_lider ;");
            rs = query.executeQuery();

            while (rs.next()) {
                String[] aux = new String[7];

                aux[0] = rs.getString("calle");
                if (rs.getInt("id_lider") != 0) {
                    aux[1] = rs.getString("pNombre");
                    aux[2] = rs.getString("sNombre");
                    aux[3] = rs.getString("pApellido");
                    aux[4] = rs.getString("sApellido");
                    aux[5] = rs.getString("cedula");
                } else {
                    aux[1] = "S/N";
                    aux[2] = "S/N";
                    aux[3] = "S/N";
                    aux[4] = "S/N";
                    aux[5] = "S/N";
                }

                lista.add(aux);
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
        } finally {
            conect.OpenBd.desconectar();
        }
        return lista;
    }

    //
    //
    public static boolean estStrike(String nombre) {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        boolean resultado = false;

        try {

            query = con.prepareStatement("SELECT COUNT(*) FROM `demografia` dm WHERE dm.calle = '" + nombre + "';");

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

}

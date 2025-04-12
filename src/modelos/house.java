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

/**
 *
 * @author Ezequiel Isacc
 */
public class house {

    private static int idCasa;
    private static String numCasa;
    private static int id_estdCasa;
    private static String estadCasa;
    private static boolean rModuloCLP = false;
    private static int id_direccion;
    private static String direccion;
    private static boolean agua = false;
    private static boolean aguasN = false;
    private static boolean luz = false;

    public static int getIdCasa() {
        return idCasa;
    }

    public static void setIdCasa(int idCasa) {
        house.idCasa = idCasa;
    }

    public static String getNumCasa() {
        return numCasa;
    }

    public static void setNumCasa(String numCasa) {
        house.numCasa = numCasa;
    }

    public static int getId_estdCasa() {
        return id_estdCasa;
    }

    public static void setId_estdCasa(int id_estdCasa) {
        house.id_estdCasa = id_estdCasa;
    }

    public static String getEstadCasa() {
        return estadCasa;
    }

    public static void setEstadCasa(String estadCasa) {
        house.estadCasa = estadCasa;
    }

    public static boolean isrModuloCLP() {
        return rModuloCLP;
    }

    public static void setrModuloCLP(boolean rModuloCLP) {
        house.rModuloCLP = rModuloCLP;
    }

    public static int getId_direccion() {
        return id_direccion;
    }

    public static void setId_direccion(int id_direccion) {
        house.id_direccion = id_direccion;
    }

    public static String getDireccion() {
        return direccion;
    }

    public static void setDireccion(String direccion) {
        house.direccion = direccion;
    }

    public static boolean isAgua() {
        return agua;
    }

    public static void setAgua(boolean agua) {
        house.agua = agua;
    }

    public static boolean isAguasN() {
        return aguasN;
    }

    public static void setAguasN(boolean aguasN) {
        house.aguasN = aguasN;
    }

    public static boolean isLuz() {
        return luz;
    }

    public static void setLuz(boolean luz) {
        house.luz = luz;
    }

    //
    //@Esta función crea una nueva casa y devuelve el id de esa casa
    //
    public static void newHose() {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;

        try {
            query = con.prepareStatement("INSERT INTO casa VALUES(NULL,?,?,?,?,?,?,?)");

            query.setString(1, getNumCasa());
            query.setInt(2, getId_estdCasa());
            query.setInt(3, getId_direccion());
            query.setBoolean(4, isrModuloCLP());
            query.setBoolean(5, isAgua());
            query.setBoolean(6, isAguasN());
            query.setBoolean(7, isLuz());
            query.execute();

            query = con.prepareStatement("SELECT LAST_INSERT_ID() AS id");
            rs = query.executeQuery();

            if (rs.next()) {
                setIdCasa(rs.getInt("id"));
            }

        } catch (Exception e) {
            System.out.println("modelos.persona.newHose()");
        } finally {
            conect.OpenBd.desconectar();
        }
    }

    //
    //@Esta función devuelve una lista con todas las casas registradas
    //
    public static ArrayList<String[]> rescueAll() {
        ArrayList<String[]> lista = new ArrayList<>();
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;

        try {
            query = con.prepareStatement("SELECT * FROM `casa`");
            rs = query.executeQuery();

            while (rs.next()) {
                String[] registro = new String[8];
                registro[0] = String.valueOf(rs.getInt("id"));
                registro[1] = rs.getString("nCasa");
                registro[2] = String.valueOf(rs.getInt("id_estdCasa"));
                registro[3] = String.valueOf(rs.getInt("id_direccion"));
                registro[4] = String.valueOf(rs.getInt("rModuloCLP"));
                registro[5] = String.valueOf(rs.getInt("sAgua"));
                registro[6] = String.valueOf(rs.getInt("sAguasN"));
                registro[7] = String.valueOf(rs.getInt("sLuz"));

                lista.add(registro);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conect.OpenBd.desconectar();
        }
        return lista;
    }

    //
    //@Esta funcion devuelve todas las casas de una direccion especifica
    //
    public static ArrayList<String[]> buscarHausexDircc() {
        ArrayList<String[]> lista = new ArrayList<>();
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;

        try {
            query = con.prepareStatement("SELECT * FROM `casa` WHERE id_direccion = " + getId_direccion());
            rs = query.executeQuery();

            while (rs.next()) {
                String[] registro = new String[8];
                registro[0] = String.valueOf(rs.getInt("id"));
                registro[1] = rs.getString("nCasa");
                registro[2] = String.valueOf(rs.getInt("id_estdCasa"));
                registro[3] = String.valueOf(rs.getInt("id_direccion"));
                registro[4] = String.valueOf(rs.getInt("rModuloCLP"));
                registro[5] = String.valueOf(rs.getInt("sAgua"));
                registro[6] = String.valueOf(rs.getInt("sAguasN"));
                registro[7] = String.valueOf(rs.getInt("sLuz"));

                lista.add(registro);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conect.OpenBd.desconectar();
        }
        return lista;
    }

    //
    //@Esta realiza una busqueda de una casa de la cual se le paso por parametro el id
    //
    public static ArrayList<String[]> buscarHause() {
        ArrayList<String[]> lista = new ArrayList<>();
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;

        try {
            query = con.prepareStatement("SELECT * FROM `casa` WHERE id = " + getIdCasa());
            rs = query.executeQuery();
            System.out.println(query.toString());
            while (rs.next()) {
                String[] registro = new String[8];
                registro[0] = String.valueOf(rs.getInt("id"));
                registro[1] = String.valueOf(rs.getString("nCasa"));
                registro[2] = String.valueOf(rs.getInt("id_estdCasa"));
                registro[3] = String.valueOf(rs.getInt("id_direccion"));
                registro[4] = String.valueOf(rs.getInt("rModuloCLP"));
                registro[5] = String.valueOf(rs.getInt("sAgua"));
                registro[6] = String.valueOf(rs.getInt("sAguasN"));
                registro[7] = String.valueOf(rs.getInt("sLuz"));

                lista.add(registro);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conect.OpenBd.desconectar();
        }
        return lista;
    }
    public static void deleteHouse(int idCasa){
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        
        try {
            query = con.prepareStatement("DELETE FROM casa c WHERE c.id = "+idCasa+"");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void ClanAll() {
        idCasa = 0;
        numCasa = "";
        id_estdCasa = 0;
        estadCasa = "";
        rModuloCLP = false;
        id_direccion = 0;
        direccion = "";
        agua = false;
        aguasN = false;
        luz = false;
    }

}

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
public class discapacidades {

    public static int id;
    public static String discapaciad;
    public static String descripcion;
    //
    // estas listas son para el manejo de las enfermedades de las personas para registrarlas
    //
    public static ArrayList<String> lDiscapacidad = new ArrayList<>();
    static ArrayList<Integer[]> pDiscapacidad = new ArrayList<>();

    public static void setlDiscapacidad(String seleccion) {
        if (lDiscapacidad.contains(seleccion)) {
            lDiscapacidad.remove(seleccion);
        } else {
            lDiscapacidad.add(seleccion);
        }
    }

    public static ArrayList<String> getLDiscapacidad() {
        return lDiscapacidad;
    }

    public static void rmvLDiscapacidad(String aux) {
        lDiscapacidad.remove(aux);
    }

    public static boolean estLDiscapacidad(String aux) {
        boolean resultado = false;

        if (lDiscapacidad.contains(aux)) {
            resultado = true;
        }

        return resultado;
    }

    public static void volcarLDiscapacidad() {
        lDiscapacidad = new ArrayList<>();
    }

    public static ArrayList<Integer[]> getpDiscapacidad() {
        return pDiscapacidad;
    }

    //
    //
    public static void addDiscapacidad(Integer id_persona, Integer id_discapaciad) {
        pDiscapacidad.add(new Integer[]{id_persona, id_discapaciad});
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        discapacidades.id = id;
    }

    public static String getDiscapaciad() {
        return discapaciad;
    }

    public static void setDiscapaciad(String discapaciad) {
        discapacidades.discapaciad = discapaciad;
    }

    public static String getDescripcion() {
        return descripcion;
    }

    public static void setDescripcion(String descripcion) {
        discapacidades.descripcion = descripcion;
    }

    //
    // addDiscapacidadPers agrega las discapacidades de una persona
    // hay que pasar por parametro el id de la persona y el id de la discapacidad 
    //
    public static void addDisapacidadPers(int id, int id_discapacidad) {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;

        try {
            query = con.prepareStatement("INSERT INTO `p_discapacidad` VALUES (?,?);");
            query.setInt(1, id);
            query.setInt(2, id_discapacidad);
            query.execute();
        } catch (Exception e) {
            System.out.println("error en addDiscapacidadPers");
            System.out.println(e);
        } finally {
            conect.OpenBd.desconectar();
        }
        System.out.println("modelos.discapacidades.addDisapacidadPers()");
    }

    //
    //@Esta función devuelve toda la informacion respecto a las discapacidades
    // el id, el nombre de la discapacidad, el tipo de discapacidad, y la descripción
    //
    public static ArrayList<String[]> recuperarAll() {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        ArrayList<String[]> lista = new ArrayList<>();

        try {
            query = con.prepareStatement(""
                    + " SELECT * FROM `discapacidad` d"
                    + " JOIN tipo_discapacidad td ON d.id_tipo = td.id; ");
            rs = query.executeQuery();

            while (rs.next()) {
                String[] registro = new String[4];
                registro[0] = String.valueOf(rs.getInt("id"));
                registro[1] = rs.getString("discapacidad");
                registro[2] = rs.getString("descripcion");
                registro[3] = rs.getString("tipo");

                lista.add(registro);
            }
        } catch (Exception e) {
        } finally {
            conect.OpenBd.desconectar();
        }
        return lista;
    }
    public static ArrayList<String> rescuellTipoDd(){
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet  rs = null;
        ArrayList<String> lista = new ArrayList<>();
        
        try {
            
            query = con.prepareStatement("SELECT * FROM `tipo_discapacidad`");
            rs = query.executeQuery();
            
            while(rs.next()){
                String aux = rs.getString("tipo");
                lista.add(aux);
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            conect.OpenBd.desconectar();
        }
        
        return lista;
    }

    //
    // whatId devuelve el id de una discpacidad
    // la discapacidad se debe de cargar antes de ejecutar whatId
    //
    public static Integer whatId() {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        Integer resultado = null;

        try {
            query = con.prepareStatement("Select * FROM discapacidad WHERE discapacidad = ?");
            query.setString(1, getDiscapaciad());
            rs = query.executeQuery();

            while (rs.next()) {
                if (rs.getInt("id") != 0) {
                    resultado = rs.getInt("id");
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conect.OpenBd.desconectar();
        }
        System.out.println(resultado);
        return resultado;
    }
    //
    //Esta funcion va a devolver el id de un tipo de discapacidad 
    // el cual debe de ser cargado por parametro
    //
    public static Integer idTdd(String tipoDd){
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        Integer id = null;
        
        try {
            
            query = con.prepareStatement("SELECT * FROM tipo_discapacidad WHERE tipo = '"+tipoDd+"'");
            rs = query.executeQuery();
            
            while(rs.next()){
                id = rs.getInt("id");
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            conect.OpenBd.desconectar();
        }
        
        return id;
    }
    //
    //@Esta función busca las discapacidades de una persona
    //
    public static ArrayList<String> disDP(int id) {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        ArrayList<String> resultado = new ArrayList();

        try {
            query = con.prepareStatement("SELECT d.discapacidad "
                    + " FROM `p_discapacidad` pd "
                    + " JOIN discapacidad d ON d.id = pd.idDiscpacidad "
                    + " WHERE pd.idPersona = " + id + "");
            rs = query.executeQuery();

            while (rs.next()) {
                resultado.add(rs.getString("discapacidad"));
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conect.OpenBd.desconectar();
        }
        return resultado;
    }

    //
    //@Esta funcion resive una lista de discapacidades y el id de una persona, 
    // todas las enfermedades de la persona del id son eliminadas, y despues se 
    // agregan las enfermedades que estan en la lista
    //
    public static void modificarDDp(ArrayList<String> lista) {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;

        try {

            query = con.prepareStatement("DELETE FROM p_discapacidad WHERE idPersona = " + persona.getId() + " ;");
            query.execute();

            for (String aux : lista) {
                setDiscapaciad(aux);
                if (whatId() != null) {
                    query = con.prepareStatement("INSERT INTO `p_discapacidad` "
                            + "VALUES ('" + persona.getId() + "', '" + whatId() + "');");
                    query.execute();
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conect.OpenBd.desconectar();
        }
    }

    public static void cleanDD() {
        id = 0;
        discapaciad = "";
        descripcion = "";
        lDiscapacidad = new ArrayList<>();
        pDiscapacidad = new ArrayList<>();
    }
}

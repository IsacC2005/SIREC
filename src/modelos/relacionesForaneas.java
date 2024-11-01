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
public class relacionesForaneas {

    public static int id_sexo;
    public static String sexo;
    public static int id_rolFamiliar;
    public static String rolFamiliar;
    public static int id_nacionalidad;
    public static String nacinalidad;
    public static int id_mgAcademico;
    public static String mgAcademico;
    public static int id_stadoCasa;
    public static String stadoCasa;
    public static int id_demografia;
    public static String demografia;
    public static int id_discapacidad;
    public static String discapacidad;

    public static int getId_sexo() {
        return id_sexo;
    }

    public static void setId_sexo(int id_sexo) {
        relacionesForaneas.id_sexo = id_sexo;
    }

    public static String getSexo() {
        return sexo;
    }

    public static void setSexo(String sexo) {
        relacionesForaneas.sexo = sexo;
    }

    public static int getId_rolFamiliar() {
        return id_rolFamiliar;
    }

    public static void setId_rolFamiliar(int id_rolFamiliar) {
        relacionesForaneas.id_rolFamiliar = id_rolFamiliar;
    }

    public static String getRolFamiliar() {
        return rolFamiliar;
    }

    public static void setRolFamiliar(String rolFamiliar) {
        relacionesForaneas.rolFamiliar = rolFamiliar;
    }

    public static int getId_nacionalidad() {
        return id_nacionalidad;
    }

    public static void setId_nacionalidad(int id_nacionalidad) {
        relacionesForaneas.id_nacionalidad = id_nacionalidad;
    }

    public static String getNacinalidad() {
        return nacinalidad;
    }

    public static void setNacinalidad(String nacinalidad) {
        relacionesForaneas.nacinalidad = nacinalidad;
    }

    public static int getId_mgAcademico() {
        return id_mgAcademico;
    }

    public static void setId_mgAcademico(int id_mgAcademico) {
        relacionesForaneas.id_mgAcademico = id_mgAcademico;
    }

    public static String getMgAcademico() {
        return mgAcademico;
    }

    public static void setMgAcademico(String mgAcademico) {
        relacionesForaneas.mgAcademico = mgAcademico;
    }

    public static int getId_stadoCasa() {
        return id_stadoCasa;
    }

    public static void setId_stadoCasa(int id_stadoCasa) {
        relacionesForaneas.id_stadoCasa = id_stadoCasa;
    }

    public static String getStadoCasa() {
        return stadoCasa;
    }

    public static void setStadoCasa(String stadoCasa) {
        relacionesForaneas.stadoCasa = stadoCasa;
    }

    public static int getId_demografia() {
        return id_demografia;
    }

    public static void setId_demografia(int id_demografia) {
        relacionesForaneas.id_demografia = id_demografia;
    }

    public static String getDemografia() {
        return demografia;
    }

    public static void setDemografia(String demografia) {
        relacionesForaneas.demografia = demografia;
    }

    public static int getId_discapacidad() {
        return id_discapacidad;
    }

    public static void setId_discapacidad(int id_discapacidad) {
        relacionesForaneas.id_discapacidad = id_discapacidad;
    }

    public static String getDiscapacidad() {
        return discapacidad;
    }

    public static void setDiscapacidad(String discapacidad) {
        relacionesForaneas.discapacidad = discapacidad;
    }

    public static String nSexo() {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        String lista = "";
        try {
            query = con.prepareStatement("SELECT * FROM `sexo` WHERE id =" + getId_sexo() + ";");
            rs = query.executeQuery();

            while (rs.next()) {
                lista = rs.getString("sexo");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conect.OpenBd.desconectar();
        }
        return lista;
    }

    public static ArrayList<String[]> rsSexo() {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        ArrayList<String[]> lista = new ArrayList<>();
        try {
            query = con.prepareStatement("SELECT * FROM `sexo`");
            rs = query.executeQuery();

            while (rs.next()) {

                String[] aux = new String[2];
                aux[0] = String.valueOf(rs.getInt("id"));
                aux[1] = rs.getString("sexo");

                lista.add(aux);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conect.OpenBd.desconectar();
        }
        return lista;
    }

    public static int buscarSexo() {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        int resultado = 0;

        try {
            query = con.prepareStatement("SELECT * FROM sexo WHERE sexo = '" + getSexo() + "'");
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

    public static ArrayList<String[]> rsRolFamiar() {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        ArrayList<String[]> lista = new ArrayList<>();

        try {
            query = con.prepareStatement("SELECT * FROM `rolfamiliar`");
            rs = query.executeQuery();

            while (rs.next()) {
                String[] resultado = new String[2];
                resultado[0] = String.valueOf(rs.getInt("id"));
                resultado[1] = rs.getString("rolFamiliar");

                lista.add(resultado);
            }
        } catch (Exception e) {
        } finally {
            conect.OpenBd.desconectar();
        }

        return lista;
    }

    public static String nRolFamiliar() {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        String lista = "";

        try {
            query = con.prepareStatement("SELECT * FROM `rolfamiliar` WHERE id = " + getId_rolFamiliar() + ";");
            rs = query.executeQuery();

            while (rs.next()) {
                lista = rs.getString("rolFamiliar");
            }
        } catch (Exception e) {
        } finally {
            conect.OpenBd.desconectar();
        }
        return lista;
    }

    public static int buscarRolFamiliar() {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        int resultado = 0;

        try {
            query = con.prepareStatement("SELECT * FROM `rolfamiliar` WHERE rolfamiliar = '" + getRolFamiliar() + "'");
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

    public static String nNacionalidad() {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        String lista = "";

        try {
            query = con.prepareStatement("SELECT * FROM `nacionalidad` WHERE id = " + getId_nacionalidad() + ";");
            rs = query.executeQuery();

            while (rs.next()) {
                lista = rs.getString("nacionalidad");
            }
        } catch (Exception e) {
        } finally {
            conect.OpenBd.desconectar();
        }
        return lista;
    }

    public static ArrayList<String[]> rsNacionalidad() {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        ArrayList<String[]> lista = new ArrayList<>();

        try {
            query = con.prepareStatement("SELECT * FROM `nacionalidad`");
            rs = query.executeQuery();

            while (rs.next()) {
                String[] resultado = new String[2];
                resultado[0] = String.valueOf(rs.getInt("id"));
                resultado[1] = rs.getString("nacionalidad");

                lista.add(resultado);
            }
        } catch (Exception e) {
        } finally {
            conect.OpenBd.desconectar();
        }

        return lista;
    }

    public static int buscarNacionalidad() {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        int resultado = 0;

        try {
            query = con.prepareStatement("SELECT * FROM `nacionalidad` WHERE nacionalidad = '" + getNacinalidad() + "'");
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

    public static ArrayList<String[]> rsMgAcademico() {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        ArrayList<String[]> lista = new ArrayList<>();

        try {
            query = con.prepareStatement("SELECT * FROM `mgacademico`");
            rs = query.executeQuery();

            while (rs.next()) {
                String[] resultado = new String[2];
                resultado[0] = String.valueOf(rs.getInt("id"));
                resultado[1] = rs.getString("mgAcademico");

                lista.add(resultado);
            }
        } catch (Exception e) {
        } finally {
            conect.OpenBd.desconectar();
        }

        return lista;
    }

    public static String nMgAcademico() {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        String lista = "";

        try {
            query = con.prepareStatement("SELECT * FROM `mgacademico` WHERE id = " + getId_mgAcademico() + ";");
            rs = query.executeQuery();

            while (rs.next()) {
                lista = rs.getString("mgAcademico");
            }
        } catch (Exception e) {
        } finally {
            conect.OpenBd.desconectar();
        }

        return lista;
    }

    public static int buscarMgAcademico() {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        int resultado = 0;

        try {
            query = con.prepareStatement("SELECT * FROM `mgacademico` WHERE mgacademico = '" + getMgAcademico() + "'");
            rs = query.executeQuery();

            while (rs.next()) {
                resultado = rs.getInt("id");
            }
        } catch (Exception e) {
            System.out.println("buscar id maximo grado academico, esta dando error :((");
        } finally {
            conect.OpenBd.desconectar();
        }
        return resultado;
    }

    public static ArrayList<String[]> rsStadoCasa() {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        ArrayList<String[]> lista = new ArrayList<>();

        try {
            query = con.prepareStatement("SELECT * FROM `estdcasa`");
            rs = query.executeQuery();

            while (rs.next()) {
                String[] resultado = new String[2];
                resultado[0] = String.valueOf(rs.getInt("id"));
                resultado[1] = rs.getString("estdCasa");

                lista.add(resultado);
            }
        } catch (Exception e) {
        } finally {
            conect.OpenBd.desconectar();
        }

        return lista;
    }

    public static int buscarStadoCasa() {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        int resultado = 0;

        try {
            query = con.prepareStatement("SELECT * FROM `estdcasa` WHERE estdCasa = '" + getStadoCasa() + "'");
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

    public static String nDemografia() {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        String lista = "";

        try {
            query = con.prepareStatement("SELECT * FROM `demografia` WHERE id =" + getId_demografia() + "; ");
            rs = query.executeQuery();

            while (rs.next()) {
                lista = rs.getString("calle");
            }
        } catch (Exception e) {
        } finally {
            conect.OpenBd.desconectar();
        }
        return lista;
    }

    public static int buscarDemografia() {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        int resultado = 0;
        try {
            query = con.prepareStatement("SELECT * FROM `demografia` WHERE calle = '" + getDemografia() + "'; ");
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

    public static ArrayList<String[]> rsDiscapacidad() {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        ArrayList<String[]> lista = new ArrayList<>();

        try {
            query = con.prepareStatement("SELECT * FROM `discapacidad`");
            rs = query.executeQuery();

            while (rs.next()) {
                String[] resultado = new String[2];
                resultado[0] = String.valueOf(rs.getInt("id"));
                resultado[1] = rs.getString("mgAcademico");

                lista.add(resultado);
            }
        } catch (Exception e) {
        } finally {
            conect.OpenBd.desconectar();
        }

        return lista;
    }

    public static String nDiscapacidad() {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        String lista = "";

        try {
            query = con.prepareStatement("SELECT * FROM `discapacidad` WHERE id = " + getId_discapacidad() + ";");
            rs = query.executeQuery();

            while (rs.next()) {
                lista = rs.getString("discapacidad");
            }
        } catch (Exception e) {
            System.out.println("erroo ///////// auida");
        } finally {
            conect.OpenBd.desconectar();
        }

        return lista;
    }

    public static int buscarDiscapacidad() {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        int resultado = 0;

        try {
            query = con.prepareStatement("SELECT * FROM `discapacidad` WHERE discapacidad = '" + getDiscapacidad() + "'");
            rs = query.executeQuery();

            while (rs.next()) {
                resultado = rs.getInt("id");
            }
        } catch (Exception e) {
            System.out.println("buscar id discapacidad, esta dando error :((");
        } finally {
            conect.OpenBd.desconectar();
        }
        return resultado;
    }
}

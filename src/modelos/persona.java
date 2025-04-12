/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author isacc
 */
public class persona {

    private static int id = 0;
    private static int idd = 0;
    private static String pNombre = "";
    private static String sNombre = "";
    private static String pApellido = "";
    private static String sApellido = "";
    private static int cedula;
    private static int sexo;
    private static Date fechaN;
    private static boolean cDiscapacidad;
    private static String sLaboral = "";
    private static int mgAcademico;
    private static int nacionalidad;
    private static String correo = "";
    private static double telefono;

    private static int idDiscapacidad;
    private static String discapacidad = "";
    private static int idEnfermedad;
    private static String enfermedad = "";

    private static int idProfesion;
    private static String profesion = "";
    private static int idIntitucion;
    private static String intitucion = "";

    private static int rolFamiliar;

    private static ArrayList<String[]> familia = new ArrayList<>();

    public static ArrayList<String[]> getFamilia() {
        return familia;
    }

    //
    //@Esta función almacena la informacion de las personas que se estan agregando 
    // como una nueva familia, el valor "idd" se utiliza para identificar a cada uno
    //
    public static void addFamilia() {

        String[] familia = new String[15];

        familia[0] = getpNombre();
        familia[1] = getsNombre();
        familia[2] = getpApellido();
        familia[3] = getsApellido();
        familia[4] = String.valueOf(getCedula());
        familia[5] = String.valueOf(getSexo());

        SimpleDateFormat formato = new SimpleDateFormat("yy-MM-dd");
        String fecha = formato.format(getFechaN());

        familia[6] = fecha;
        familia[7] = String.valueOf(getcDiscapacidad());
        familia[8] = getsLaboral();
        familia[9] = String.valueOf(getMgAcademico());
        familia[10] = String.valueOf(getNacionalidad());
        familia[11] = getCorreo();
        familia[12] = String.valueOf(getTelefono());
        familia[13] = String.valueOf(getRolFamiliar());
        familia[14] = String.valueOf(getIdd());

        System.out.println(idd + " este es el idd");
        idd++;
        persona.familia.add(familia);
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        persona.id = id;
    }

    public static int getIdd() {
        return idd;
    }

    public static void setIdd(int idd) {
        persona.idd = idd;
    }

    public static String getpNombre() {
        return pNombre;
    }

    public static void setpNombre(String pNombre) {
        persona.pNombre = pNombre;
    }

    public static String getsNombre() {
        return sNombre;
    }

    public static void setsNombre(String sNombre) {
        persona.sNombre = sNombre;
    }

    public static String getpApellido() {
        return pApellido;
    }

    public static void setpApellido(String pApellido) {
        persona.pApellido = pApellido;
    }

    public static String getsApellido() {
        return sApellido;
    }

    public static void setsApellido(String sApellido) {
        persona.sApellido = sApellido;
    }

    public static int getCedula() {
        return cedula;
    }

    public static void setCedula(int cedula) {
        persona.cedula = cedula;
    }

    public static int getSexo() {
        return sexo;
    }

    public static void setSexo(int sexo) {
        persona.sexo = sexo;
    }

    public static Date getFechaN() {
        return fechaN;
    }

    public static void setFechaN(Date fechaN) {
        persona.fechaN = fechaN;
    }

    public static boolean getcDiscapacidad() {
        return cDiscapacidad;
    }

    public static void setcDiscapacidad(boolean cDiscapacidad) {
        persona.cDiscapacidad = cDiscapacidad;
    }

    public static String getsLaboral() {
        return sLaboral;
    }

    public static void setsLaboral(String sLaboral) {
        persona.sLaboral = sLaboral;
    }

    public static int getMgAcademico() {
        return mgAcademico;
    }

    public static void setMgAcademico(int mgAcademico) {
        persona.mgAcademico = mgAcademico;
    }

    public static int getNacionalidad() {
        return nacionalidad;
    }

    public static void setNacionalidad(int nacionalidad) {
        persona.nacionalidad = nacionalidad;
    }

    public static String getCorreo() {
        return correo;
    }

    public static void setCorreo(String correo) {
        persona.correo = correo;
    }

    public static double getTelefono() {
        return telefono;
    }

    public static void setTelefono(double telefono) {
        persona.telefono = telefono;
    }

    public static int getIdDiscapacidad() {
        return idDiscapacidad;
    }

    public static void setIdDiscapacidad(int idDiscapacidad) {
        persona.idDiscapacidad = idDiscapacidad;
    }

    public static String getDiscapacidad() {
        return discapacidad;
    }

    public static void setDiscapacidad(String discapacidad) {
        persona.discapacidad = discapacidad;
    }

    public static int getIdEnfermedad() {
        return idEnfermedad;
    }

    public static void setIdEnfermedad(int idEnfermedad) {
        persona.idEnfermedad = idEnfermedad;
    }

    public static String getEnfermedad() {
        return enfermedad;
    }

    public static void setEnfermedad(String enfermedad) {
        persona.enfermedad = enfermedad;
    }

    public static int getIdProfesion() {
        return idProfesion;
    }

    public static void setIdProfesion(int idProfesion) {
        persona.idProfesion = idProfesion;
    }

    public static String getProfesion() {
        return profesion;
    }

    public static void setProfesion(String profesion) {
        persona.profesion = profesion;
    }

    public static int getIdIntitucion() {
        return idIntitucion;
    }

    public static void setIdIntitucion(int idIntitucion) {
        persona.idIntitucion = idIntitucion;
    }

    public static String getIntitucion() {
        return intitucion;
    }

    public static void setIntitucion(String intitucion) {
        persona.intitucion = intitucion;
    }

    public static int getRolFamiliar() {
        return rolFamiliar;
    }

    public static void setRolFamiliar(int rolFamiliar) {
        persona.rolFamiliar = rolFamiliar;
    }

    //
    //@Esta funcion agrega a una nueva persona, y devuelve el id de esa persona 
    //¡¡Primero se deben de cargar todos los datos de la personas!!
    //    
    public static int agregar() {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;

        String s;
        System.out.println("modelos.persona.agregar()");
        try {

            query = con.prepareStatement("INSERT INTO persona VALUES(NULL,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            query.setString(1, getpNombre());
            query.setString(2, getsNombre());
            query.setString(3, getpApellido());
            query.setString(4, getsApellido());
            query.setInt(5, getCedula());
            query.setInt(6, getSexo());

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String ax = formato.format(getFechaN());

            query.setString(7, ax);
            query.setInt(8, getMgAcademico());
            query.setInt(9, getNacionalidad());
            query.setDouble(10, getTelefono());
            query.setString(11, getCorreo());
            query.setInt(12, getRolFamiliar());
            query.setInt(13, house.getIdCasa());

            System.out.println("\n\n" + query.toString() + "\n\n");
            query.execute();

            query = con.prepareStatement("SELECT LAST_INSERT_ID() AS id");

            rs = query.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id");
            }
            for (Integer[] aux : discapacidades.getpDiscapacidad()) {
                System.out.println(aux + " Esta es la discapacidad que se esta  agregando");
                System.out.println("Este es el idd " + getIdd() + "  y se esta comparando con este " + aux[0]);
                if (aux[0] == getIdd()) {
                    discapacidades.addDisapacidadPers(id, aux[1]);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        setId(id);
        return id;
    }

    //
    //@Esta función retorna a todas las personas ordenandolas por familia, 
    // ademas pide por parapetro un String que se utiliza para hacer un filtrado por similitud
    //
    public static ArrayList<String[]> RecuperarAll(String buscar) {
        ArrayList<String[]> lista = new ArrayList<>();
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;

        try {

            query = con.prepareStatement(""
                    + " SELECT * "
                    + " FROM `persona` p "
                    + " JOIN rolfamiliar r ON  r.id = p.id_rolFamiliar "
                    + " JOIN nacionalidad n  ON n.id = p.id_nacionalidad  "
                    + " JOIN sexo sx ON sx.id = p.id_sexo "
                    + " JOIN mgacademico mg ON mg.id = p.id_mgAcademico "
                    + " WHERE "
                    + " pNombre LIKE '%" + buscar + "%' OR"
                    + " sNombre LIKE '%" + buscar + "%'  OR "
                    + " pApellido LIKE '%" + buscar + "%'  OR "
                    + " sApellido LIKE '%" + buscar + "%'  OR "
                    + " cedula LIKE '%" + buscar + "%' "
                    + " ORDER BY idCasa ASC , id_rolFamiliar ASC ");
            System.out.println(query.toString());

            rs = query.executeQuery();
            while (rs.next()) {

                String[] registro = new String[14];

                registro[0] = String.valueOf(rs.getInt("id"));
                registro[1] = rs.getString("pNombre");
                registro[2] = rs.getString("sNombre");
                registro[3] = rs.getString("pApellido");
                registro[4] = rs.getString("sApellido");
                registro[5] = String.valueOf(rs.getInt("cedula"));
                registro[6] = rs.getString("sexo");
                registro[7] = rs.getString("fechaN");
                registro[8] = rs.getString("mgAcademico");
                registro[9] = rs.getString("nacionalidad");
                registro[10] = String.valueOf(rs.getDouble("telefono"));
                registro[11] = rs.getString("correo");
                registro[12] = rs.getString("rolFamiliar");
                registro[13] = String.valueOf(rs.getInt("idCasa"));

                lista.add(registro);
            }
        } catch (Exception e) {
            System.out.println("error en buscar persona :(((");
            System.out.println(e);
        } finally {
            conect.OpenBd.conectar();
        }
        return lista;
    }

    //
    //@Esta función busca a una persona especifica, por id el id debe de ser proporcionado por parametro
    //
    public static void buscar(int id) {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        String aux = "Hola mudno ";
        try {
            aux = "Inicio del try chat";
            query = con.prepareStatement("SELECT * FROM persona WHERE id = ?");
            query.setInt(1, id);
            aux = "Carga del query";
            rs = query.executeQuery();

            while (rs.next()) {
                aux = rs.getString("pNombre");
                setId(rs.getInt("id"));
                setpNombre(rs.getString("pNombre"));
                setsNombre(rs.getString("sNombre"));
                setpApellido(rs.getString("pApellido"));
                setsApellido(rs.getString("sApellido"));
                setCedula(rs.getInt("cedula"));
                setSexo(rs.getInt("id_sexo"));

                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                setFechaN(formato.parse(String.valueOf(rs.getDate("fechaN"))));

                setMgAcademico(rs.getInt("id_mgAcademico"));
                setNacionalidad(rs.getInt("id_nacionalidad"));
                setTelefono(rs.getDouble("telefono"));
                setCorreo(rs.getString("correo"));
                setRolFamiliar(rs.getInt("id_rolFamiliar"));
                house.setIdCasa(rs.getInt("idCasa"));
            }
        } catch (Exception e) {
            System.out.println(" ERROR !!" + e);
        } finally {
            conect.OpenBd.desconectar();
        }
        System.out.println(aux + " lee aqui que esta es la buena");
    }

    //
    //@Esta funcion modifica a una persona, primero se tiene que cargar todos los datos
    //    
    public static void modificarP() {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        String cons = "No se cargo nada :((";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(format.format(getFechaN()));

        try {
            query = con.prepareStatement("UPDATE `persona` SET `pNombre` = ?, `sNombre` = ?, `pApellido` = ?, `sApellido` = ?, `cedula` = ?, `id_sexo` = ?,`fechaN` = ?, `id_mgAcademico` = ?, `id_nacionalidad` = ?, `telefono` = ?, `correo` = ? WHERE `persona`.`id` = " + getId() + ";");
            query.setString(1, getpNombre());
            query.setString(2, getsNombre());
            query.setString(3, getpApellido());
            query.setString(4, getsApellido());
            query.setInt(5, getCedula());
            query.setInt(6, getSexo());

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String ax = formato.format(getFechaN());

            query.setString(7, ax);
            query.setInt(8, getMgAcademico());
            query.setInt(9, getNacionalidad());
            query.setDouble(10, getTelefono());
            query.setString(11, getCorreo());

            cons = query.toString();
            query.execute();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conect.OpenBd.desconectar();
        }
        System.out.println(cons);

    }

    //
    //@Esta funcion extrae todos los datos de la lista familia y los carga para usar la funcion agregar 
    // de esta manera agregando a cada uno de los integrantes de la familia
    //
    public static void crearGrupoFamiliar() {
        for (String[] aux : getFamilia()) {
            try {
                /*familia[0] = getpNombre();
        familia[1] = getsNombre();
        familia[2] = getpApellido();
        familia[3] = getsApellido();
        familia[4] = String.valueOf(getCedula());
        familia[5] = String.valueOf(getSexo());

        SimpleDateFormat formato = new SimpleDateFormat("yy-MM-dd");
        String fecha = formato.format(getFechaN());

        familia[6] = fecha;
        familia[7] = String.valueOf(getcDiscapacidad());
        familia[8] = getsLaboral();
        familia[9] = String.valueOf(getMgAcademico());
        familia[10] = String.valueOf(getNacionalidad());
        familia[11] = getCorreo();
        familia[12] = String.valueOf(getTelefono());
        familia[13] = String.valueOf(getRolFamiliar());
        familia[14] = String.valueOf(getIdd());*/
                System.out.println(persona.getpNombre() + " " + persona.getsNombre());
                persona.setpNombre(aux[0]);
                persona.setsNombre(aux[1]);
                persona.setpApellido(aux[2]);
                persona.setsApellido(aux[3]);
                persona.setCedula(Integer.parseInt(aux[4]));
                persona.setSexo(Integer.parseInt(aux[5]));

                SimpleDateFormat formato = new SimpleDateFormat("yy-MM-dd");
                Date ax = formato.parse(aux[6]);
                persona.setFechaN(ax);

                persona.setcDiscapacidad(Boolean.valueOf(aux[7]));
                persona.setsLaboral(aux[8]);
                persona.setMgAcademico(Integer.parseInt(aux[9]));
                persona.setNacionalidad(Integer.valueOf(aux[10]));
                persona.setCorreo(aux[11]);
                persona.setTelefono(Double.parseDouble(aux[12]));
                persona.setRolFamiliar(Integer.parseInt(aux[13]));
                persona.setIdd(Integer.parseInt(aux[14]));

                persona.agregar();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    //
    //@Esta funcion devuelve los datos de todas las personas que tienen como rol familia jefe de familia
    // ademas pide por parametro un String para filtrar la busqueda 
    //
    public static ArrayList<String[]> buscarFamilia(String buscar) {
        ArrayList<String[]> lista = new ArrayList<>();
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        try {
            query = con.prepareStatement("SELECT * "
                    + "FROM `persona` p"
                    + " JOIN rolfamiliar rf ON rf.id = p.id_rolFamiliar "
                    + " WHERE "
                    + " p.pNombre LIKE '%" + buscar + "%' AND rf.rolFamiliar = 'Jefe de Familia' OR"
                    + " p.sNombre LIKE '%" + buscar + "%' AND rf.rolFamiliar = 'Jefe de Familia' OR "
                    + " p.pApellido LIKE '%" + buscar + "%' AND rf.rolFamiliar = 'Jefe de Familia' OR "
                    + " p.sApellido LIKE '%" + buscar + "%' AND rf.rolFamiliar = 'Jefe de Familia' OR "
                    + " p.cedula LIKE '%" + buscar + "%' AND rf.rolFamiliar = 'Jefe de Familia' ");

            rs = query.executeQuery();
            while (rs.next()) {
                String[] registro = new String[6];

                registro[0] = rs.getString("pNombre");
                registro[1] = rs.getString("sNombre");
                registro[2] = rs.getString("pApellido");
                registro[3] = rs.getString("sApellido");
                registro[4] = String.valueOf(rs.getInt("cedula"));
                registro[5] = String.valueOf(rs.getInt("id"));

                lista.add(registro);
            }
        } catch (Exception e) {
            System.out.println("error en buscar familia :((");
            System.out.println(e);
        } finally {
            conect.OpenBd.conectar();
        }
        return lista;
    }

    /**
     *
     *
     *
     *
     *
     *
     */
    /**
     *
     *
     *
     *
     *
     */
    /**
     *
     *
     *
     *
     *
     *
     */
    /* public static Map rescuesD() {
        Map<Integer, String> lista = new HashMap<>();
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;

        try {
            query = con.prepareStatement("SELECT * FROM `discapacidad`;");
            rs = query.executeQuery();
            while (rs.next()) {
                lista.put(rs.getInt(id), rs.getString("discapacidad"));
            }
        } catch (Exception e) {
        } finally {
            conect.OpenBd.desconectar();
        }
        return lista;
    }*/
    //
    //@Esta funcion devuelve a las personas que habitan en una casa
    //
    public static ArrayList<String[]> buscarPersonaXhause(int i, String buscar) {
        ArrayList<String[]> filtrado = new ArrayList<>();
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;

        try {

            query = con.prepareStatement(""
                    + " SELECT * "
                    + " FROM `persona` p "
                    + " JOIN rolfamiliar r ON  r.id = p.id_rolFamiliar "
                    + " JOIN nacionalidad n ON n.id = P.id_nacionalidad "
                    + " JOIN sexo sx ON sx.id = p.id_sexo "
                    + " JOIN mgacademico mg ON mg.id = p.id_mgAcademico "
                    + "WHERE "
                    + " idCasa = " + i + " AND pNombre LIKE '%" + buscar + "%' OR"
                    + " idCasa = " + i + " AND sNombre LIKE '%" + buscar + "%' OR "
                    + " idCasa = " + i + " AND pApellido LIKE '%" + buscar + "%' OR "
                    + " idCasa = " + i + " AND sApellido LIKE '%" + buscar + "%' OR "
                    + " idCasa = " + i + " AND cedula LIKE '%" + buscar + "%' "
                    + " ORDER BY `p`.`id_rolFamiliar` ASC ");
            rs = query.executeQuery();

            while (rs.next()) {
                String[] registro = new String[14];

                registro[0] = String.valueOf(rs.getInt("id"));
                registro[1] = rs.getString("pNombre");
                registro[2] = rs.getString("sNombre");
                registro[3] = rs.getString("pApellido");
                registro[4] = rs.getString("sApellido");
                registro[5] = String.valueOf(rs.getInt("cedula"));
                registro[6] = rs.getString("sexo");
                registro[7] = rs.getString("fechaN");
                registro[8] = rs.getString("mgAcademico");
                registro[9] = rs.getString("nacionalidad");
                registro[10] = String.valueOf(rs.getDouble("telefono"));
                registro[11] = rs.getString("correo");
                registro[12] = rs.getString("rolFamiliar");
                registro[13] = String.valueOf(rs.getInt("idCasa"));

                filtrado.add(registro);
            }
        } catch (Exception e) {
            System.out.println("filtro x casa " + e);
        } finally {
            conect.OpenBd.desconectar();
        }
        return filtrado;
    }

    public static void modificarFamilia(ArrayList<Integer[]> n) {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;

        try {
            for (Integer[] aux : n) {
                System.out.println("que lo que wa wa wa salkjfsakljfdklajsfklsadf");
                query = con.prepareStatement("UPDATE `persona` SET `id_rolFamiliar` = ? WHERE `persona`.`id` = ?");
                query.setInt(1, aux[0]);
                query.setInt(2, aux[1]);

                query.execute();
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conect.OpenBd.desconectar();
        }
    }

    public static void cambiardFamilia(int idPersona, int idPersonaNF) {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;

        try {
            buscar(idPersonaNF);
            query = con.prepareStatement("UPDATE `persona` SET `idCasa` = " + house.getIdCasa() + " WHERE `persona`.`id` = " + idPersona + " ");
            query.execute();

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conect.OpenBd.desconectar();
        }
    }

    public static void deletePersona(int id) {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;

        try {
            query = con.prepareStatement("DELETE FROM p_discapacidad WHERE idPersona = " + id + " ;");
            query.execute();

            query = con.prepareStatement(" DELETE FROM `persona`  "
                    + " WHERE persona.id = " + id + " ;");
            query.execute();            
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conect.OpenBd.desconectar();
        }
    }

    public static void deleteFamilia(int idCasa) {
        ArrayList<String[]> lista = buscarPersonaXhause(idCasa, "");
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        PreparedStatement query2 = null;
        ResultSet rs = null;

        try {
            for (String[] aux : lista) {
                query = con.prepareStatement("UPDATE `demografia` SET `id_lider` = NULL WHERE `demografia`.`id_lider` = " + Integer.parseInt(aux[0]) + "");
                query.execute();
                deletePersona(Integer.parseInt(aux[0]));
                System.out.println(aux[0]);
            }
            house.deleteHouse(idCasa);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static int cantidadPersona() {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        int i = 0;

        try {
            query = con.prepareStatement("SELECT COUNT(*) FROM persona");
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

    public static int cantidadPersonaH() {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        int i = 0;

        try {
            query = con.prepareStatement("SELECT COUNT(*) "
                    + " FROM persona p "
                    + " JOIN sexo sx ON sx.id = p.id_sexo "
                    + " WHERE sx.sexo = 'Hombre' ");
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

    public static int cantidadPersonaM() {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        int i = 0;

        try {
            query = con.prepareStatement("SELECT COUNT(*) "
                    + " FROM persona p "
                    + " JOIN sexo sx ON sx.id = p.id_sexo "
                    + " WHERE sx.sexo = 'Mujer' ");
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

    public static int cantidadPersonaNN() {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        int i = 0;

        try {
            query = con.prepareStatement(" SELECT COUNT(*) "
                    + " FROM persona "
                    + " WHERE FLOOR(DATEDIFF(CURDATE(), fechaN) / 365.25) BETWEEN 0 AND 12; ");
            /*" SELECT pNombre, pApellido, fechaN, "
                                            +" FLOOR(DATEDIFF(CURDATE(), fechaN) / 365.25) AS edad " 
                                            +" FROM persona "
                                            +" WHERE FLOOR(DATEDIFF(CURDATE(), fechaN) / 365.25) BETWEEN 0 AND 18");*/
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

    public static boolean exisPer(int cedula) {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        boolean resultado = false;

        try {

            query = con.prepareStatement("SELECT COUNT(*) FROM persona WHERE cedula = ?");
            query.setInt(1, cedula);
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

    /* Cuando se va a modificar una persona hay que validar que no se valla a poner la cedula de una persona a otra
        para eso va a servir exisCedulaPer, se le pasa la cedula que se le quiere agregar y el id de la persona 
        para comprobar si hay alguna persona ademas de el mismo que tenga esa cedula
     */
    public static boolean exisCedulaPer(int cedula, int id) {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        boolean resultado = false;

        try {

            query = con.prepareStatement("SELECT COUNT(*) FROM persona WHERE cedula = ? AND id != ?");
            query.setInt(1, cedula);
            query.setInt(2, id);
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

    public static void ClanAll() {
        id = 0;
        idd = 0;
        pNombre = "";
        sNombre = "";
        pApellido = "";
        sApellido = "";
        cedula = 0;
        sexo = 0;
        sLaboral = "";
        mgAcademico = 0;
        nacionalidad = 0;
        correo = "";
        telefono = 0;
        idDiscapacidad = 0;
        discapacidad = "";
        idEnfermedad = 0;
        enfermedad = "";
        idProfesion = 0;
        profesion = "";
        idIntitucion = 0;
        intitucion = "";
        familia = new ArrayList<>();
        modelos.discapacidades.cleanDD();
    }
}

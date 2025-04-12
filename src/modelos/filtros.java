/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Ezequiel Isacc
 */
public class filtros {

    // La variable "buscar" se va a utilizar para complementar los filtro
    // añadiendole un parametro mas de busqueda inteligente
    private static String buscar = "";

    public static String getBuscar() {
        return buscar;
    }

    public static void setBuscar(String buscar) {
        filtros.buscar = buscar;
    }

    //
    //@Esta funcion devuelve las personas que esten ubicados en un rango de edad 
    //el rango de edad debe de ser propocionado por parametro, edad minima y edad maxima
    //
    public static ArrayList<String[]> edad(int minimo, int maximo) throws ParseException {
        ArrayList<String[]> lista = new ArrayList<>();
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;

        try {
            query = con.prepareStatement(" "
                    + "SELECT * "
                    + " FROM persona "
                    + " WHERE "
                    + " FLOOR(DATEDIFF(CURDATE(), fechaN) / 365.25) BETWEEN " + minimo + " AND " + maximo + " AND pNombre LIKE '%" + getBuscar() + "%' OR"
                    + " FLOOR(DATEDIFF(CURDATE(), fechaN) / 365.25) BETWEEN " + minimo + " AND " + maximo + "  AND sNombre LIKE '%" + getBuscar() + "%' OR "
                    + " FLOOR(DATEDIFF(CURDATE(), fechaN) / 365.25) BETWEEN " + minimo + " AND " + maximo + "  AND pApellido LIKE '%" + getBuscar() + "%' OR "
                    + " FLOOR(DATEDIFF(CURDATE(), fechaN) / 365.25) BETWEEN " + minimo + " AND " + maximo + "  AND sApellido LIKE '%" + getBuscar() + "%' OR "
                    + " FLOOR(DATEDIFF(CURDATE(), fechaN) / 365.25) BETWEEN " + minimo + " AND " + maximo + "  AND cedula LIKE '%" + getBuscar() + "%' ");

            rs = query.executeQuery();
            System.out.println(query.toString());

            while (rs.next()) {
                String[] reg = new String[7];

                reg[0] = String.valueOf(rs.getInt("id"));
                reg[1] = rs.getString("pNombre");
                reg[2] = rs.getString("sNombre");
                reg[3] = rs.getString("pApellido");
                reg[4] = rs.getString("sApellido");
                reg[5] = String.valueOf(rs.getInt("cedula"));

                SimpleDateFormat formato = new SimpleDateFormat("yy-MM-dd");
                Date dt = formato.parse(rs.getString("fechaN"));
                LocalDate fecha = dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate hoy = LocalDate.now();
                Period periodo = Period.between(fecha, hoy);

                reg[6] = String.valueOf(periodo.getYears());

                lista.add(reg);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conect.OpenBd.conectar();
        }
        return lista;
    }

    //
    //@Esta función devuelva a las personas que vivan en una casa ubicada en una 
    //direción, esta dirección debe de ser ingresada por parametro
    //
    public static ArrayList<String[]> ubicacion(String direccion) {
        ArrayList<String[]> lista = new ArrayList<>();
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;

        try {
            query = con.prepareStatement(""
                    + " SELECT * "
                    + " FROM persona p "
                    + " JOIN casa cs ON cs.id = p.idCasa "
                    + " JOIN demografia dm ON dm.id = cs.id_direccion "
                    + " WHERE "
                    + " dm.calle = '" + direccion + "'  AND pNombre LIKE '%" + getBuscar() + "%' OR "
                    + " dm.calle = '" + direccion + "'  AND sNombre LIKE '%" + getBuscar() + "%' OR "
                    + " dm.calle = '" + direccion + "'  AND pApellido LIKE '%" + getBuscar() + "%' OR "
                    + " dm.calle = '" + direccion + "'  AND sApellido LIKE '%" + getBuscar() + "%' OR "
                    + " dm.calle = '" + direccion + "'  AND cedula LIKE '%" + getBuscar() + "%' ");

            rs = query.executeQuery();

            while (rs.next()) {
                String[] reg = new String[7];

                reg[0] = rs.getString("pNombre");
                reg[1] = rs.getString("sNombre");
                reg[2] = rs.getString("pApellido");
                reg[3] = rs.getString("sApellido");
                reg[4] = String.valueOf(rs.getInt("cedula"));
                reg[5] = rs.getString("calle");
                reg[6] = String.valueOf(rs.getInt("id"));

                lista.add(reg);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conect.OpenBd.conectar();
        }

        return lista;
    }

    //
    //@Esta función devuelve a las personas que se encuentren dentro de un nivel educativo   
    //
    public static ArrayList<String[]> nivelEd(String nivel) {
        ArrayList<String[]> lista = new ArrayList<>();
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        StringBuilder sql = new StringBuilder(""
                + " SELECT * FROM `persona` p "
                + " JOIN mgacademico mg ON mg.id = p.id_mgAcademico "
                + " WHERE "
        );

        switch (nivel) {
            case "Sin Educacion":
                sql.append(" "
                        + " mg.mgAcademico = 'Sin Educacion' AND pNombre LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Sin Educacion' AND sNombre LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Sin Educacion' AND pApellido LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Sin Educacion' AND sApellido LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Sin Educacion' AND cedula LIKE '%" + getBuscar() + "%' ");
                break;
            case "Educacion Inicial":
                sql.append(" "
                        + " mg.mgAcademico = 'Educacion Inicial' AND pNombre LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Educacion Inicial' AND sNombre LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Educacion Inicial' AND pApellido LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Educacion Inicial' AND sApellido LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Educacion Inicial' AND cedula LIKE '%" + getBuscar() + "%' OR\n"
                        + " mg.mgAcademico = 'Educacion Basica' AND pNombre LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Educacion Basica' AND sNombre LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Educacion Basica' AND pApellido LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Educacion Basica' AND sApellido LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Educacion Basica' AND cedula LIKE '%" + getBuscar() + "%' OR\n"
                        + " mg.mgAcademico = 'Educacion Media' AND pNombre LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Educacion Media' AND sNombre LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Educacion Media' AND pApellido LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Educacion Media' AND sApellido LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Educacion Media' AND cedula LIKE '%" + getBuscar() + "%' OR\n"
                        + " mg.mgAcademico = 'Educacion Superior' AND pNombre LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Educacion Superior' AND sNombre LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Educacion Superior' AND pApellido LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Educacion Superior' AND sApellido LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Educacion Superior' AND cedula LIKE '%" + getBuscar() + "%'");
                break;
            case "Educacion Basica":
                sql.append(" " 
                        + " mg.mgAcademico = 'Educacion Basica' AND pNombre LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Educacion Basica' AND sNombre LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Educacion Basica' AND pApellido LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Educacion Basica' AND sApellido LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Educacion Basica' AND cedula LIKE '%" + getBuscar() + "%' OR\n"
                        + " mg.mgAcademico = 'Educacion Media' AND pNombre LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Educacion Media' AND sNombre LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Educacion Media' AND pApellido LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Educacion Media' AND sApellido LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Educacion Media' AND cedula LIKE '%" + getBuscar() + "%' OR\n"
                        + " mg.mgAcademico = 'Educacion Superior' AND pNombre LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Educacion Superior' AND sNombre LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Educacion Superior' AND pApellido LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Educacion Superior' AND sApellido LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Educacion Superior' AND cedula LIKE '%" + getBuscar() + "%'");
                break;
            case "Educacion Media":
                sql.append(" "
                        + " mg.mgAcademico = 'Educacion Media' AND pNombre LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Educacion Media' AND sNombre LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Educacion Media' AND pApellido LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Educacion Media' AND sApellido LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Educacion Media' AND cedula LIKE '%" + getBuscar() + "%' OR\n"
                        + " mg.mgAcademico = 'Educacion Superior' AND pNombre LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Educacion Superior' AND sNombre LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Educacion Superior' AND pApellido LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Educacion Superior' AND sApellido LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Educacion Superior' AND cedula LIKE '%" + getBuscar() + "%'");
                break;
            case "Educacion Superior":
                sql.append(""
                        + " mg.mgAcademico = 'Educacion Superior' AND pNombre LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Educacion Superior' AND sNombre LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Educacion Superior' AND pApellido LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Educacion Superior' AND sApellido LIKE '%" + getBuscar() + "%' OR \n"
                        + " mg.mgAcademico = 'Educacion Superior' AND cedula LIKE '%" + getBuscar() + "%'");
                break;
            default:
                return lista;
        }
        System.out.println(sql);
        try {
            query = con.prepareStatement(sql.toString());
            rs = query.executeQuery();
            while (rs.next()) {
                String[] reg = new String[7];

                reg[0] = rs.getString("pNombre");
                reg[1] = rs.getString("sNombre");
                reg[2] = rs.getString("pApellido");
                reg[3] = rs.getString("sApellido");
                reg[4] = String.valueOf(rs.getInt("cedula"));
                reg[5] = rs.getString("mgAcademico");
                reg[6] = String.valueOf(rs.getInt("id"));

                lista.add(reg);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conect.OpenBd.desconectar();
        }
        return lista;
    }

    /**
     * // // Esta funcion resive por parametro, distintos posibles filtros y
     * dependiendo de si // algun parametro es distinto de null se modifica la
     * orden sql para ajustarce a ese // requisito, filtra personas por rango de
     * edad, direccion, nivel educativo, // tipo de discapacidad, direcccion, y
     * tipo de habitante //
     *
     */
    public static ArrayList<String[]> personalizado(Integer rEdadmn, Integer rEdadmx, Integer direccion, Integer nivelEdc, Integer tpDiscapacidad, Integer sdtCasa, Integer tipoHabitante, boolean omitir_serv_casa, Integer agua, Integer aguaN, Integer luz) {
        Connection con = conect.OpenBd.conectar();
        PreparedStatement query = null;
        ResultSet rs = null;
        ArrayList<String[]> lista = new ArrayList<>();

        StringBuilder encabezado = new StringBuilder(" SELECT DISTINCT p.id, p.pNombre, p.sNombre, p.pApellido, p.sApellido, p.cedula, p.fechaN,p.telefono,p.correo,sx.sexo, rf.rolFamiliar,nc.nacionalidad, mg.mgAcademico "
                + " FROM `persona` p "
                + " JOIN sexo sx ON p.id_sexo = sx.id "
                + " JOIN mgacademico mg ON p.id_mgAcademico = mg.id "
                + " JOIN nacionalidad nc ON p.id_nacionalidad = nc.id "
                + " JOIN rolfamiliar rf ON p.id_rolFamiliar = rf.id  "
        // + " JOIN p_discapacidad pd ON pd.idPersona =  p.id "
        // + " JOIN discapacidad d ON d.id = pd.idDiscpacidad "
        // + " JOIN casa c ON c.id = p.idCasa "
        );
        StringBuilder cuerpo = new StringBuilder("");

        boolean addWhere = true;
        if (rEdadmn != null || rEdadmx != null) {
            if (rEdadmn == null) {
                rEdadmn = 0;
            }
            if (rEdadmx == null) {
                rEdadmx = 1000;
            }
            if (addWhere) {
                cuerpo.append("  "
                        + " FLOOR(DATEDIFF(CURDATE(), fechaN) / 365.25) BETWEEN " + rEdadmn + " AND " + rEdadmx + " "
                );
                addWhere = false;
            } else {
                cuerpo.append(" AND "
                        + " FLOOR(DATEDIFF(CURDATE(), fechaN) / 365.25) BETWEEN " + rEdadmn + " AND " + rEdadmx + " "
                );
            }

        }

        if (direccion != null) {
            encabezado.append(" JOIN casa c ON c.id = p.idCasa ");
            if (addWhere) {
                cuerpo.append("  c.id_direccion = " + direccion + " ");
                addWhere = false;
            } else {
                cuerpo.append(" AND c.id_direccion = " + direccion + " ");
            }

        }
        if (nivelEdc != null) {
            if (addWhere) {
                cuerpo.append("  p.id_mgAcademico = " + nivelEdc + " ");
                addWhere = false;
            } else {
                cuerpo.append(" AND  p.id_mgAcademico = " + nivelEdc + " ");
            }
        }
        if (tpDiscapacidad != null) {
            encabezado.append(" JOIN p_discapacidad pd ON pd.idPersona =  p.id ");
            encabezado.append(" JOIN discapacidad d ON d.id = pd.idDiscpacidad ");
            if (addWhere) {
                cuerpo.append("  d.id_tipo = " + tpDiscapacidad + " ");
                addWhere = false;
            } else {
                cuerpo.append(" AND d.id_tipo = " + tpDiscapacidad + " ");
            }
        }
        if (sdtCasa != null) {
            if (direccion == null) {
                encabezado.append(" JOIN casa c ON c.id = p.idCasa ");
            }
            if (addWhere) {
                cuerpo.append("  c.id_estdCasa = " + sdtCasa + " ");
                addWhere = false;
            } else {
                cuerpo.append(" AND c.id_estdCasa = " + sdtCasa + " ");
            }
        }
        if (tipoHabitante != null) {
            if (addWhere) {
                cuerpo.append("  p.id_rolFamiliar = " + tipoHabitante + " ");
                addWhere = false;
            } else {
                cuerpo.append(" AND p.id_rolFamiliar = " + tipoHabitante + " ");
            }
        }
        if (omitir_serv_casa == false) {
            if (direccion == null && sdtCasa == null) {
                encabezado.append(" JOIN casa c ON c.id = p.idCasa ");
            }
            if (addWhere) {
                cuerpo.append(" "
                        + " c.sAgua = " + agua + " AND "
                        + " c.sAguasN = " + aguaN + " AND "
                        + " c.sLuz = " + luz + " ");
                addWhere = false;
            } else {
                cuerpo.append(" AND "
                        + " c.sAgua = " + agua + " AND "
                        + " c.sAguasN = " + aguaN + " AND "
                        + " c.sLuz = " + luz + " ");
            }

        }
        if (rEdadmn != null || rEdadmx != null || direccion != null || nivelEdc != null || tpDiscapacidad != null || sdtCasa != null || tipoHabitante != null || omitir_serv_casa == false) {
            encabezado.append(" WHERE ");
            encabezado.append(" "
                    + cuerpo.toString() + " AND pNombre LIKE '%" + getBuscar() + "%' OR"
                    + cuerpo.toString() + " AND sNombre LIKE '%" + getBuscar() + "%' OR"
                    + cuerpo.toString() + " AND pApellido LIKE '%" + getBuscar() + "%' OR"
                    + cuerpo.toString() + " AND sApellido LIKE '%" + getBuscar() + "%' OR"
                    + cuerpo.toString() + " AND pNombre LIKE '%" + getBuscar() + "%' OR"
                    + cuerpo.toString() + " AND cedula LIKE '%" + getBuscar() + "%' "
            );
        } else {
            encabezado.append(" WHERE ");
            encabezado.append(" "
                    + "  pNombre LIKE '%" + getBuscar() + "%' OR"
                    + "  sNombre LIKE '%" + getBuscar() + "%' OR"
                    + "  pApellido LIKE '%" + getBuscar() + "%' OR"
                    + "  sApellido LIKE '%" + getBuscar() + "%' OR"
                    + "  pNombre LIKE '%" + getBuscar() + "%' OR"
                    + "  cedula LIKE '%" + getBuscar() + "%' "
            );
        }
        System.out.println(encabezado.toString());

        try {
            query = con.prepareStatement(encabezado.toString() + " ORDER BY `p`.`id` ASC ");
            rs = query.executeQuery();
            // System.out.println(query.toString());

            while (rs.next()) {
                String[] aux = new String[13];
                aux[0] = String.valueOf(rs.getInt("id"));
                aux[1] = rs.getString("pNombre");
                aux[2] = rs.getString("sNombre");
                aux[3] = rs.getString("pApellido");
                aux[4] = rs.getString("sApellido");
                aux[5] = String.valueOf(rs.getInt("cedula"));
                aux[6] = rs.getString("sexo");
                aux[7] = String.valueOf(rs.getDate("fechaN"));
                aux[8] = rs.getString("mgAcademico");
                aux[9] = rs.getString("nacionalidad");
                aux[10] = rs.getString("rolFamiliar");
                aux[11] = String.valueOf(rs.getDouble("telefono"));
                aux[12] = rs.getString("correo");

                lista.add(aux);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conect.OpenBd.desconectar();
        }
        return lista;
    }
}

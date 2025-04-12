/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import impresRiven.dynamicjasper.template.PageFormat;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javafx.scene.control.SelectionMode;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import modelos.demografia;
import modelos.discapacidades;
import modelos.filtros;
import modelos.persona;
import modelos.relacionesForaneas;
import modelos.usuario;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.exception.DRException;
import Clases.tabla.TableCustom;
import conect.restaurar;
import java.time.Period;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * @author isacc
 */
public class principal extends javax.swing.JFrame {

    String tipoDeRegistro;
    String seleccionDiscapacidad = "";

    int xMause, yMause, x, y;
    //int stadeRolf = 1;

    int condicion = 0;

    int stadeRegistro = 0;
    int stadeRegistroC = 0;

    Integer iDaux = null;

    Integer rowSelect = 0;

    final private Color nulo = new Color(153, 153, 153);
    final private Color seleccionado = new Color(51, 153, 255);
    final private Color disponible = new Color(0, 0, 0);

    login login;
    agregar agregar = new agregar();
    detallesP infoPersona = new detallesP();
    modificar modificar = new modificar();

    DefaultTableModel modelo = new DefaultTableModel();

    DefaultTableModel modeloFamilia = new DefaultTableModel();
    DefaultTableModel modeloTdiscapacidades = new DefaultTableModel();
    DefaultTableModel modeloDemografia = new DefaultTableModel();
    //DefaultTableModel modeloBucarP = new DefaultTableModel();        

    //  Map<Integer, String> tDis = new HashMap<>();
    private PageFormat pageFormat = new PageFormat(PageType.A4, 0, 0, PageOrientation.PORTRAIT);

    private ReportOption getReportOption() {
        return new ReportOption("", "No", "Customer", "Amount", new ReportOption("Product List", "No", "Item", "Total", new ReportOption("Detail", "No", "Type", "Qty")));
    }

    public static void openUser(String name) {
        bt_menu_usuario.setText(name);
    }

    public void buscarP(String busqueda) {
        ArrayList<String[]> lista = persona.RecuperarAll(busqueda);
        String[] colums = {"Cod", "Nobre y apellido", "Cedula", "Rol de familia"};
        modelo = new DefaultTableModel() {
            boolean[] canEdit = new boolean[]{false, false, false, false};

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        modelo.setColumnIdentifiers(colums);

        for (String[] aux : lista) {
            modelo.addRow(new String[]{
                aux[0],
                aux[1] + " " + aux[2] + " " + aux[3] + " " + aux[4],
                aux[5],
                aux[12]
            });
        }
        jTable1.setModel(modelo);
    }

    //
    public void filtroEdad() {
        try {
            modelos.filtros.setBuscar(jTextField3.getText());
            modelo = new DefaultTableModel() {
                boolean[] canEdit = new boolean[]{false, false, false, false};

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            };
            String[] colums = {"Cod", "Nombre y Apellido", "Cedula", "Edad"};
            modelo.setColumnIdentifiers(colums);
            int minimo = 0;
            int maximo = 0;

            if (jTextField9.getText().equals("")) {
                minimo = 0;
            } else {
                minimo = Integer.parseInt(jTextField9.getText());
            }
            if (jTextField11.getText().equals("") || jTextField11.getText().equals("0")) {
                maximo = 1000;
            } else {
                maximo = Integer.parseInt(jTextField11.getText());
            }
            ArrayList<String[]> lista = modelos.filtros.edad(minimo, maximo);

            for (String[] aux : lista) {
                String[] registro = {aux[0], aux[1] + " " + aux[2] + " " + aux[3] + " " + aux[4], aux[5], aux[6]};

                modelo.addRow(registro);
            }
            jTable6.setModel(modelo);
        } catch (ParseException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //
    public void filtroNvEdc(String nivel) {
        System.out.println("filtroNvEdc entrada principal");
        modelos.filtros.setBuscar(jTextField5.getText());
        ArrayList<String[]> lista = modelos.filtros.nivelEd(nivel);
        modelo = new DefaultTableModel() {
            boolean[] canEdit = new boolean[]{false, false, false, false};

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        String[] colums = {"COD", "Nombre y Apellido", "Cedula", "Nivel Educativo Mx"};
        modelo.setColumnIdentifiers(colums);

        for (String[] aux : lista) {
            modelo.addRow(new String[]{
                aux[6],
                aux[0] + " " + aux[1] + " " + aux[2] + " " + aux[3],
                aux[4],
                aux[5]
            });
        }
        jTable8.setModel(modelo);
    }

    //
    public void filtroUbicacion(String direccion) {
        ArrayList<String[]> lista = filtros.ubicacion(direccion);
        String[] colums = {"COD", "Nombre y Apellido", "Cedula", "Direccion"};
        modelo = new DefaultTableModel() {
            boolean[] canEdit = new boolean[]{false, false, false, false};

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        modelo.setColumnIdentifiers(colums);

        for (String[] aux : lista) {
            modelo.addRow(new String[]{
                aux[6],
                aux[0] + " " + aux[1] + " " + aux[2] + " " + aux[3],
                aux[4],
                aux[5]
            });
        }
        jTable7.setModel(modelo);
    }

    //
    public void cargarCalles() {
        ArrayList<String[]> lista = modelos.demografia.rsStrike();
        String[] colums = {"Calle"};
        modeloDemografia = new DefaultTableModel() {
            boolean[] canEdit = new boolean[]{
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        modeloDemografia.setColumnIdentifiers(colums);
        for (String[] aux : lista) {
            modeloDemografia.addRow(new String[]{aux[1]});
        }
        jTable3.setModel(modeloDemografia);
    }

    ArrayList<String> listaDd;

    static String[] rolesFm;
    ArrayList<String[]> ddD = discapacidades.recuperarAll();

    public void cargar_rolesfm() {
        ArrayList<String[]> lista = relacionesForaneas.rsRolFamiar();
        rolesFm = new String[lista.size()];
        int i = 0;
        //rolesFm[0] = "Roles Familiares";
        for (String[] aux : lista) {
            // System.out.println(aux[1]);
            rolesFm[i] = aux[1];
            i++;
        }
    }

    public String[] cargarDdCbbx() {
        String[] lista = new String[ddD.size()];
        listaDd = new ArrayList<>();
        int i = 0;
        for (String[] aux : ddD) {
            lista[i] = aux[1];
            listaDd.add(aux[1]);
            i++;
        }
        return lista;
    }

    public String[] cargarTDdCbbx() {
        ArrayList<String> lista = discapacidades.rescuellTipoDd();
        String[] tipo = new String[lista.size() + 1];
        int i = 1;

        tipo[0] = "Tipo de Discapacidad";

        for (String aux : lista) {
            tipo[i] = aux;
            i++;
        }
        return tipo;
    }

    /*Esta funcion cargar los roles familiares que van a estar disponible para
         la nueva persona*/
    public void cargarRfCbbx(String aux) {
        switch (aux) {
            case "Jefe de Familia":

                // rolesFm[0] = "Roles Familiares";
                rolesFm[0] = "Esposo(a)";
                rolesFm[1] = "Hermano(a)";
                rolesFm[2] = "Hijo(a)";
                rolesFm[3] = "Nieto(a)";
                rolesFm[4] = " ";
                break;
            case "Esposo(a)":

                //   rolesFm[0] = "Roles Familiares";                
                rolesFm[0] = "Hermano(a)";
                rolesFm[1] = "Hijo(a)";
                rolesFm[2] = "Nieto(a)";
                rolesFm[3] = " ";
                rolesFm[4] = " ";
                break;
            case "Hijo(a)":
                break;
            case "Hermano(a)":
                break;
            case "Nieto(a)":
                break;
            case "Otro":
                break;
        }
        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(rolesFm));
    }

    public String[] cargarSexoCbbx() {
        ArrayList<String[]> lista = relacionesForaneas.rsSexo();
        String[] resultado = new String[lista.size() + 1];
        resultado[0] = "Generos";
        int i = 1;

        for (String[] aux : lista) {
            resultado[i] = aux[1];
            i++;
        }
        return resultado;
    }

    public String[] cargarNacionalidadCbbx() {
        ArrayList<String[]> lista = relacionesForaneas.rsNacionalidad();
        String[] resultado = new String[lista.size()];
        int i = 0;

        for (String[] aux : lista) {
            resultado[i] = aux[1];
            i++;
        }
        return resultado;
    }

    public String[] cargarStrikeCbbx() {
        ArrayList<String[]> lista = modelos.demografia.rsStrike();
        String[] Strikes = new String[lista.size() + 1];

        int i = 1;

        Strikes[0] = "Direccion";

        for (String[] aux : lista) {
            Strikes[i] = aux[1];
            i++;
        }
        return Strikes;
    }

    public String[] cargarNivelesEdcCbbx() {
        ArrayList<String[]> lista = relacionesForaneas.rsMgAcademico();
        String[] resultado = new String[lista.size() + 1];
        int i = 1;

        resultado[0] = "Niveles Educativos";

        for (String[] aux : lista) {
            resultado[i] = aux[1];
            i++;
        }
        return resultado;
    }

    public void AgregarDisc(String discapacidad) {
        System.out.println("Interfaz.principal.AgregarDisc()");
        if (discapacidades.estLDiscapacidad(discapacidad) == false) {
            System.out.println("Dentro de la condicion");

            seleccionDiscapacidad = "";
            modeloTdiscapacidades = new DefaultTableModel();
            discapacidades.setlDiscapacidad(discapacidad);
            String[] colums = {"Enfermedades"};
            modeloTdiscapacidades.setColumnIdentifiers(colums);

            for (String aux : discapacidades.getLDiscapacidad()) {
                System.out.println("Dentro del for");
                System.out.println(aux + " hola mundos ");
                String[] ax = {aux};
                modeloTdiscapacidades.addRow(ax);
            }

            System.out.println("Final por cargar tabla");
            jTable4.setModel(modeloTdiscapacidades);
            int i = 0;

            System.out.println("removiendo discpacidad de la lista");
            listaDd.remove(discapacidad);
            String[] ax = new String[listaDd.size()];
            for (String aux : listaDd) {
                System.out.println("dentro del for para remover");
                ax[i] = aux;
                i++;
            }
            combo_entradaRdd_listaDiscapacidades.setModel(new javax.swing.DefaultComboBoxModel<>(ax));
            System.out.println("final");
        }
    }

    public void RemoveDisc(String discapacidad) {
        System.out.println(discapacidad);
        if (discapacidades.estLDiscapacidad(discapacidad)) {
            seleccionDiscapacidad = "";
            modeloTdiscapacidades = new DefaultTableModel();
            discapacidades.rmvLDiscapacidad(discapacidad);
            String[] colums = {"Enfermedades"};
            modeloTdiscapacidades.setColumnIdentifiers(colums);
            for (String aux : discapacidades.getLDiscapacidad()) {
                String[] ax = {aux};
                modeloTdiscapacidades.addRow(ax);
            }
            jTable4.setModel(modeloTdiscapacidades);

            int i = 0;
            listaDd.add(discapacidad);
            String[] ax = new String[listaDd.size()];

            for (String aux : listaDd) {
                ax[i] = aux;
                i++;
            }
            combo_entradaRdd_listaDiscapacidades.setModel(new javax.swing.DefaultComboBoxModel<>(ax));
        }
    }

    public String[] cargarStadoCasaCbbx() {
        ArrayList<String[]> lista = modelos.relacionesForaneas.rsStadoCasa();
        String[] resultado = new String[lista.size() + 1];

        int i = 1;

        resultado[0] = "Estado de la Casa";

        for (String[] aux : lista) {
            resultado[i] = aux[1];
            i++;
        }
        return resultado;
    }

    public void setLogin(login login) {
        this.login = login;
    }

    public principal() {
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/recursos/logoSinFondo110x110.png")).getImage());
        cargar_rolesfm();
        initComponents();
        axdinamicMenu();
        mostrarMenu(true);
        this.setResizable(false);
        TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        TableCustom.apply(jScrollPane2, TableCustom.TableType.MULTI_LINE);
        TableCustom.apply(jScrollPane3, TableCustom.TableType.MULTI_LINE);
        TableCustom.apply(jScrollPane4, TableCustom.TableType.MULTI_LINE);
        TableCustom.apply(jScrollPane5, TableCustom.TableType.MULTI_LINE);
        TableCustom.apply(jScrollPane6, TableCustom.TableType.MULTI_LINE);
        TableCustom.apply(jScrollPane7, TableCustom.TableType.MULTI_LINE);
        TableCustom.apply(jScrollPane8, TableCustom.TableType.MULTI_LINE);
        TableCustom.apply(jScrollPane9, TableCustom.TableType.MULTI_LINE);

        //jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new com.raven.datechooser.DateChooser();
        panel_base = new javax.swing.JPanel();
        btSalie = new javax.swing.JLabel();
        panel_filtro = new javax.swing.JPanel();
        menu_impress = new javax.swing.JLayeredPane();
        jLabel110 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        menu_impressD = new Clases.PanelRound();
        p_filtroGeneral = new javax.swing.JPanel();
        jLayeredPane8 = new javax.swing.JLayeredPane();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new Clases.ScrollPaneWin11();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel65 = new javax.swing.JLabel();
        p_filtroPro = new javax.swing.JPanel();
        jLayeredPane7 = new javax.swing.JLayeredPane();
        jLabel17 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jLabel82 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        combo_filtroPro_ubicacion = new Clases.combobox.ComboBoxSuggestion();
        jSeparator16 = new javax.swing.JSeparator();
        jSeparator17 = new javax.swing.JSeparator();
        combo_filtroPro_nvEdc = new Clases.combobox.ComboBoxSuggestion();
        combo_filtroPro_tipoDiscapacidad = new Clases.combobox.ComboBoxSuggestion();
        combo_filtroPro_estCasa = new Clases.combobox.ComboBoxSuggestion();
        combo_filtroPro_rolFamiliar = new Clases.combobox.ComboBoxSuggestion();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jPanel14 = new javax.swing.JPanel();
        jLabel81 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel84 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel85 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel86 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel87 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jLabel88 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jLabel89 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel90 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLayeredPane6 = new javax.swing.JLayeredPane();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable9 = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel125 = new javax.swing.JLabel();
        jLabel126 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        totalInfo_filtroPro = new Clases.checkbox.JCheckBoxCustom();
        totalInfo_filtroPro.setSelected(false);
        jPanel17 = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        p_filtroEdad = new javax.swing.JPanel();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jLabel10 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jScrollPane6 = new Clases.ScrollPaneWin11();
        jTable6 = new javax.swing.JTable();
        jLabel79 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel78 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jSeparator14 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel106 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        p_filtroDirecc = new javax.swing.JPanel();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        jLabel111 = new javax.swing.JLabel();
        jScrollPane7 = new Clases.ScrollPaneWin11();
        jTable7 = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        jTextField4 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel121 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel73 = new javax.swing.JLabel();
        p_filtroNEdc = new javax.swing.JPanel();
        jLayeredPane5 = new javax.swing.JLayeredPane();
        jLabel112 = new javax.swing.JLabel();
        jScrollPane8 = new Clases.ScrollPaneWin11();
        jTable8 = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel123 = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        panel_registrar = new javax.swing.JPanel();
        Registro3o4 = new javax.swing.JPanel();
        jLabel94 = new javax.swing.JLabel();
        buttonGradient1 = new Clases.botones.ButtonGradient();
        buttonGradient2 = new Clases.botones.ButtonGradient();
        barraLateralD = new javax.swing.JPanel();
        pr1 = new Clases.PanelRound();
        jLabel5 = new javax.swing.JLabel();
        pr2 = new Clases.PanelRound();
        jLabel29 = new javax.swing.JLabel();
        pr3 = new Clases.PanelRound();
        jLabel58 = new javax.swing.JLabel();
        pr4 = new Clases.PanelRound();
        jLabel59 = new javax.swing.JLabel();
        pr5 = new Clases.PanelRound();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        Registro1 = new javax.swing.JPanel();
        enCorreo = new javax.swing.JTextField();
        etick_fechaN = new javax.swing.JLabel();
        enpNombre = new javax.swing.JTextField();
        ensNombre = new javax.swing.JTextField();
        enpApellido = new javax.swing.JTextField();
        ensApellido = new javax.swing.JTextField();
        enTelefono = new javax.swing.JTextField();
        enCedula = new javax.swing.JTextField();
        dSeparador_pNombre = new javax.swing.JSeparator();
        dSeparador_sNombre = new javax.swing.JSeparator();
        dSeparador_pApellido = new javax.swing.JSeparator();
        dSeparador_sApellido = new javax.swing.JSeparator();
        dSeparador_cedula = new javax.swing.JSeparator();
        dSeparador_telefono = new javax.swing.JSeparator();
        dSeparador_correo = new javax.swing.JSeparator();
        etick_nacionalidad = new javax.swing.JLabel();
        etick_nacionalidad.setVisible(false);
        etick_pNombre = new javax.swing.JLabel();
        etick_pNombre.setVisible(false);
        etick_sNombre = new javax.swing.JLabel();
        etick_sNombre.setVisible(false);
        etick_pApellido = new javax.swing.JLabel();
        etick_pApellido.setVisible(false);
        etick_sApellido = new javax.swing.JLabel();
        etick_sApellido.setVisible(false);
        etick_cedula = new javax.swing.JLabel();
        etick_cedula.setVisible(false);
        etick_telefono = new javax.swing.JLabel();
        etick_telefono.setVisible(false);
        etick_correo = new javax.swing.JLabel();
        etick_correo.setVisible(false);
        etick_genero = new javax.swing.JLabel();
        etick_genero.setVisible(false);
        entrada_fechaN_registro = new javax.swing.JTextField();
        bt_fechaN_registro1 = new elaprendiz.gui.button.ButtonShadow();
        enNacionalidad = new Clases.combobox.ComboBoxSuggestion();
        enSexo = new Clases.combobox.ComboBoxSuggestion();
        bt_registro1_siguiente = new javax.swing.JLabel();
        Registro2 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        checkbox_entradaNVedc_eduacionBasica = new javax.swing.JCheckBox();
        checkbox_entradaNVedc_educacionInicial = new javax.swing.JCheckBox();
        checkbox_entradaNVedc_educacionMedia = new javax.swing.JCheckBox();
        checkbox_entradaNVedc_eduacionSuperior = new javax.swing.JCheckBox();
        label_entradaNVedc_educacionSuperior = new javax.swing.JLabel();
        label_entradaNVedc_educacionMedia = new javax.swing.JLabel();
        label_entradaNVedc_eduacionBasica = new javax.swing.JLabel();
        label_entradaNVedc_educacionInicial = new javax.swing.JLabel();
        label_entradaNVedc_sinEducacion = new javax.swing.JLabel();
        checkbox_entradaNVedc_sinEducacion = new javax.swing.JCheckBox();
        jCheckBox10 = new javax.swing.JCheckBox();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        Registro3 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane4 = new Clases.ScrollPaneWin11();
        jTable4 = new javax.swing.JTable();
        label_registroP_detallesSs_Descripcion = new javax.swing.JTextArea();
        jLabel103 = new javax.swing.JLabel();
        label_registroP_detallesSs_nombre = new javax.swing.JLabel();
        label_registroP_detallesSs_tipo = new javax.swing.JLabel();
        combo_entradaRdd_listaDiscapacidades = new Clases.combobox.ComboBoxSuggestion();
        boton_entradaRdd_agregar = new elaprendiz.gui.button.ButtonAction();
        buttonAction1 = new elaprendiz.gui.button.ButtonAction();
        jLabel51 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        Registro4 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<>();
        jScrollPane2 = new Clases.ScrollPaneWin11();
        jTable2 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jSeparator19 = new javax.swing.JSeparator();
        Registro5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        en_nCasa = new javax.swing.JTextField();
        en_not_nCasa = new javax.swing.JCheckBox();
        jLabel41 = new javax.swing.JLabel();
        en_combo_direccion = new javax.swing.JComboBox<>();
        jPanel12 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        en_serv_agua_s = new javax.swing.JCheckBox();
        en_serv_agua_s.setSelected(true);
        en_serv_agua_n = new javax.swing.JCheckBox();
        en_serv_aguaN_n = new javax.swing.JCheckBox();
        en_serv_aguaN_s = new javax.swing.JCheckBox();
        en_serv_elct_n = new javax.swing.JCheckBox();
        en_serv_elct_s = new javax.swing.JCheckBox();
        en_serv_cls_n = new javax.swing.JCheckBox();
        en_serv_cls_s = new javax.swing.JCheckBox();
        jLabel47 = new javax.swing.JLabel();
        en_combo_stdCasa = new javax.swing.JComboBox<>();
        jSeparator18 = new javax.swing.JSeparator();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        panel_demografia = new javax.swing.JPanel();
        panel_dmg_strike = new javax.swing.JLayeredPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        bt_dmg_estruc = new javax.swing.JLabel();
        bt_dmg_addLider = new javax.swing.JLabel();
        bt_dmg_addStrike = new javax.swing.JLabel();
        entradaTxt_demografia = new javax.swing.JTextField();
        bt_dmg_guardar = new javax.swing.JLabel();
        bt_dmg_descartar = new javax.swing.JLabel();
        jSeparator20 = new javax.swing.JSeparator();
        bt_dmg_stingStrike = new javax.swing.JLabel();
        panel_dmg_lider = new javax.swing.JLayeredPane();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel63 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jTextField10 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        combo_demografia_asigLider = new Clases.combobox.ComboBoxSuggestion();
        jPanel4 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        panelBarra = new javax.swing.JPanel();
        panelMenup = new javax.swing.JPanel();
        bt_menu_agregar = new Clases.botones.ButtonGradient();
        bt_menu_demografia = new Clases.botones.ButtonGradient();
        bt_mn_buscar = new Clases.botones.ButtonGradient();
        bt_menu_modificar = new Clases.botones.ButtonGradient();
        bt_menu_imprimir = new Clases.botones.ButtonGradient();
        bt_menu_gestionar = new Clases.botones.ButtonGradient();
        bt_menu_usuario = new Clases.botones.ButtonGradient();
        itemM_agregar_persona = new Clases.botones.ButtonGradient();
        itemM_agregar_cargaFamiliar = new Clases.botones.ButtonGradient();
        itemM_agregar_LiderCalle = new Clases.botones.ButtonGradient();
        itemM_demografia_addCalle = new Clases.botones.ButtonGradient();
        itemM_demografia_infComunal = new Clases.botones.ButtonGradient();
        itemM_filtro_buscar = new Clases.botones.ButtonGradient();
        itemM_filtro_ubicacion = new Clases.botones.ButtonGradient();
        itemM_filtro_edad = new Clases.botones.ButtonGradient();
        itemM_filtro_nvlEdc = new Clases.botones.ButtonGradient();
        itemM_filtro_otro = new Clases.botones.ButtonGradient();
        itemM_modificar_persona = new Clases.botones.ButtonGradient();
        itemM_modificar_familia = new Clases.botones.ButtonGradient();
        itemM_modificar_calle = new Clases.botones.ButtonGradient();
        itemM_modificar_liderCalle = new Clases.botones.ButtonGradient();
        itemM_imprimir_censo = new Clases.botones.ButtonGradient();
        itemM_imprimir_carta = new Clases.botones.ButtonGradient();
        itemM_pregunta_respaldarBD = new Clases.botones.ButtonGradient();
        itemM_pregunta_restaurarBD = new Clases.botones.ButtonGradient();
        itemM_pregunta_manual = new Clases.botones.ButtonGradient();
        itemM_usuario_add = new Clases.botones.ButtonGradient();
        itemM_usuario_modificar = new Clases.botones.ButtonGradient();
        itemM_usuario_close = new Clases.botones.ButtonGradient();
        itemM_usuario_exit = new Clases.botones.ButtonGradient();
        fondo_imagen = new javax.swing.JLabel();

        dateChooser1.setForeground(new java.awt.Color(102, 0, 204));
        dateChooser1.setDateFormat("dd-MMMM-yyyy");
        dateChooser1.setTextRefernce(entrada_fechaN_registro);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 500));
        setUndecorated(true);
        setSize(new java.awt.Dimension(800, 500));
        getContentPane().setLayout(new java.awt.CardLayout());

        this.setTitle("Registro Comunal");
        panel_base.setBackground(new java.awt.Color(225, 225, 225));
        panel_base.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(107, 109, 143)));
        panel_base.setMinimumSize(new java.awt.Dimension(800, 500));
        panel_base.setPreferredSize(new java.awt.Dimension(800, 500));
        panel_base.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panel_baseMouseDragged(evt);
            }
        });
        panel_base.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panel_baseMousePressed(evt);
            }
        });
        panel_base.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btSalie.setBackground(new java.awt.Color(0, 0, 0,0));
        btSalie.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        btSalie.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btSalie.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cerrar-sesion(2).png"))); // NOI18N
        btSalie.setText("Regresar");
        btSalie.setToolTipText("");
        btSalie.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btSalie.setVisible(false);
        btSalie.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SalirStm(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btSalieMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btSalieMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btSalieMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btSalieMouseReleased(evt);
            }
        });
        panel_base.add(btSalie, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 42));

        panel_filtro.setBackground(new java.awt.Color(245, 245, 245));
        panel_filtro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(107, 109, 143)));
        panel_filtro.setVisible(false);
        panel_filtro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menu_impress.setVisible(false);
        menu_impress.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel110.setBackground(new Color(0,0,255,0));
        jLabel110.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel110.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ImprimirPdf.png"))); // NOI18N
        jLabel110.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel110MouseClicked(evt);
            }
        });
        menu_impress.add(jLabel110, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 50, 40));

        jLabel107.setBackground(new Color(0,0,255,0));
        jLabel107.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel107.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ImprimirWor 24x24.png"))); // NOI18N
        jLabel107.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel107MouseClicked(evt);
            }
        });
        menu_impress.add(jLabel107, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 50, 40));

        jLabel108.setBackground(new Color(0,0,255,0));
        jLabel108.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel108.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ImprimirImagen 24 x 24.png"))); // NOI18N
        jLabel108.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel108MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel108MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel108MouseExited(evt);
            }
        });
        menu_impress.add(jLabel108, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 40));

        jLabel109.setBackground(new Color(0,0,255,0));
        jLabel109.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel109.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ImprimirExcel 24x24.png"))); // NOI18N
        jLabel109.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel109MouseClicked(evt);
            }
        });
        menu_impress.add(jLabel109, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 50, 40));

        panel_filtro.add(menu_impress, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 40, 200, 40));

        menu_impressD.setBackground(new Color(255,255,255,200));
        menu_impressD.setRoundBottomLeft(15);
        menu_impressD.setRoundBottomRight(15);
        menu_impressD.setRoundTopLeft(15);
        menu_impressD.setRoundTopRight(15);
        menu_impressD.setVisible(false);

        javax.swing.GroupLayout menu_impressDLayout = new javax.swing.GroupLayout(menu_impressD);
        menu_impressD.setLayout(menu_impressDLayout);
        menu_impressDLayout.setHorizontalGroup(
            menu_impressDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        menu_impressDLayout.setVerticalGroup(
            menu_impressDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panel_filtro.add(menu_impressD, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 40, 200, 40));

        p_filtroGeneral.setVisible(false);

        p_filtroGeneral.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLayeredPane8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLayeredPane8MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLayeredPane8MousePressed(evt);
            }
        });
        jLayeredPane8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/informacion.png"))); // NOI18N
        jLabel3.setText("Información");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jLayeredPane8.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, -1, 20));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Cod", "Nombre y apellido", "Cedula", "Jefe de familia", "Seleccionar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jTable1.setDefaultRenderer(Object.class, new Clases.table.TableGradientCell(new Color(153,204,255),new Color(255,153,255)));
        jScrollPane1.setViewportView(jTable1);

        jLayeredPane8.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 79, 760, 340));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/configuraciones.png"))); // NOI18N
        jLabel4.setText("Modificar");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jLayeredPane8.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 30, -1, 20));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imprimir 16x16.png"))); // NOI18N
        jLabel2.setText("Imprimir");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jLayeredPane8.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 30, 70, 20));

        jSeparator9.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator9.setOpaque(true);
        jLayeredPane8.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 330, -1));

        jTextField1.setBackground(new Color(255,255,255,100));
        jTextField1.setBorder(null);
        jTextField1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField1CaretUpdate(evt);
            }
        });
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTextField1MouseExited(evt);
            }
        });
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jLayeredPane8.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 330, 28));

        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/buscar.png"))); // NOI18N
        jLabel1.setText("Buscar");
        jLayeredPane8.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, -1, 20));

        jLabel117.setForeground(new java.awt.Color(255, 51, 51));
        jLabel117.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/3Puntos 20x20.png"))); // NOI18N
        jLabel117.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel117.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel117MouseClicked(evt);
            }
        });
        jLayeredPane8.add(jLabel117, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 30, -1, 20));

        jLabel118.setForeground(new java.awt.Color(255, 51, 51));
        jLabel118.setText("eliminar");
        jLabel118.setVisible(false);
        jLabel118.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel118MouseClicked(evt);
            }
        });
        jLayeredPane8.add(jLabel118, new org.netbeans.lib.awtextra.AbsoluteConstraints(699, 30, -1, 20));

        p_filtroGeneral.add(jLayeredPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 780, 430));

        jPanel5.setBackground(new Color(255,255,255,150));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        p_filtroGeneral.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 780, 430));

        jLabel65.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/fondoGeneral.png"))); // NOI18N
        p_filtroGeneral.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 798, 500));

        panel_filtro.add(p_filtroGeneral, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));

        p_filtroDirecc.setVisible(false);
        p_filtroPro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLayeredPane7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setText("Filtro avanzado");
        jLayeredPane7.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, -1, -1));

        jButton1.setText("Aplicar filtro");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jLayeredPane7.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 390, 120, 30));

        jCheckBox1.setText("Agua");
        jLayeredPane7.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, -1, -1));

        jCheckBox2.setText("Aguas negras");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });
        jLayeredPane7.add(jCheckBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 380, -1, -1));

        jCheckBox3.setText("Luz electrica");
        jCheckBox3.setBorder(null);
        jCheckBox3.setBorderPainted(true);
        jLayeredPane7.add(jCheckBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 380, -1, 20));

        jCheckBox4.setText("Modulo CLAP");
        jLayeredPane7.add(jCheckBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 380, -1, -1));

        jLabel82.setText("Minima");
        jLayeredPane7.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jTextField12.setBackground(new Color(255,255,255,100));
        jTextField12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jTextField12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField12MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTextField12MouseExited(evt);
            }
        });
        jLayeredPane7.add(jTextField12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 70, 30));

        jTextField13.setBackground(new Color(255,255,255,100));
        jTextField13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jTextField13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField13MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTextField13MouseExited(evt);
            }
        });
        jLayeredPane7.add(jTextField13, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 70, 30));

        jLabel83.setText("Maxima");
        jLayeredPane7.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, -1, -1));

        combo_filtroPro_ubicacion.setModel(new javax.swing.DefaultComboBoxModel<>(cargarStrikeCbbx()));
        jLayeredPane7.add(combo_filtroPro_ubicacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, 448, 29));

        jSeparator16.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator16.setOpaque(true);
        jLayeredPane7.add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 112, 70, -1));

        jSeparator17.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator17.setOpaque(true);
        jLayeredPane7.add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 112, 70, -1));

        combo_filtroPro_nvEdc.setModel(new javax.swing.DefaultComboBoxModel<>(cargarNivelesEdcCbbx()));
        jLayeredPane7.add(combo_filtroPro_nvEdc, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 279, 32));

        combo_filtroPro_tipoDiscapacidad.setModel(new javax.swing.DefaultComboBoxModel<>(cargarTDdCbbx()));
        jLayeredPane7.add(combo_filtroPro_tipoDiscapacidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 190, 370, 29));

        combo_filtroPro_estCasa.setModel(new javax.swing.DefaultComboBoxModel<>(cargarStadoCasaCbbx()));
        jLayeredPane7.add(combo_filtroPro_estCasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 290, 29));

        combo_filtroPro_rolFamiliar.setModel(new javax.swing.DefaultComboBoxModel<>(rolesFm));
        combo_filtroPro_rolFamiliar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_filtroPro_rolFamiliarActionPerformed(evt);
            }
        });
        jLayeredPane7.add(combo_filtroPro_rolFamiliar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 300, 218, 32));

        jCheckBox5.setSelected(true);
        jCheckBox5.setText("Omitir");
        jLayeredPane7.add(jCheckBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 350, -1, 20));

        jCheckBox6.setSelected(true);
        jCheckBox6.setText("Omitir");
        jCheckBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox6ActionPerformed(evt);
            }
        });
        jLayeredPane7.add(jCheckBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 350, -1, 20));

        jCheckBox9.setSelected(true);
        jCheckBox9.setText("Todos");
        jCheckBox9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox9ActionPerformed(evt);
            }
        });
        jLayeredPane7.add(jCheckBox9, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 260, -1, -1));

        p_filtroPro.add(jLayeredPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 780, 430));

        jPanel14.setBackground(new Color(255,255,255,50));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 255, 255)));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel81.setText("Rango de edad");
        jPanel14.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, -1));

        p_filtroPro.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 230, 90));

        jPanel16.setBackground(new Color(255,255,255,50));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 255, 255)));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel84.setText("Ubicacion");
        jPanel16.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 1, -1, -1));

        p_filtroPro.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 490, 90));

        jPanel19.setBackground(new Color(255,255,255,50));
        jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 255, 255)));
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel85.setText("Nivel educativo");
        jPanel19.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 1, -1, -1));

        p_filtroPro.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 320, 90));

        jPanel20.setBackground(new Color(255,255,255,50));
        jPanel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 255, 255)));
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel86.setText("Discapacidad");
        jPanel20.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 1, -1, -1));

        p_filtroPro.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 220, 400, 90));

        jPanel21.setBackground(new Color(255,255,255,50));
        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 255, 255)));
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel87.setText("Condicion de vivienda");
        jPanel21.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 1, -1, -1));

        p_filtroPro.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 320, 70));

        jPanel22.setBackground(new Color(255,255,255,50));
        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 255, 255)));
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel88.setText("Tipo de habitante");
        jPanel22.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 1, -1, -1));

        p_filtroPro.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 330, 260, 70));

        jPanel23.setBackground(new Color(255,255,255,50));
        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 255, 255)));
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel89.setText("Servicios que dispone la vivienda ");
        jPanel23.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 1, -1, -1));

        p_filtroPro.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 320, 70));

        jPanel24.setBackground(new Color(255,255,255,50));
        jPanel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 255, 255)));
        jPanel24.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel90.setText("Servicios CLAP");
        jPanel24.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 1, -1, -1));

        p_filtroPro.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 410, 150, 70));

        jPanel18.setBackground(new Color(255,255,255,150));
        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        p_filtroPro.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 780, 430));

        jLayeredPane6.setVisible(false);
        jLayeredPane6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable9.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));

        jTable9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable9MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable9MousePressed(evt);
            }
        });
        jScrollPane9.setViewportView(jTable9);

        jLayeredPane6.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 88, 760, 340));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imprimir 16x16.png"))); // NOI18N
        jLabel21.setText("Imprimir");
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });
        jLayeredPane6.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 30, 70, 20));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/configuraciones.png"))); // NOI18N
        jLabel23.setText("Modificar");
        jLabel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel23MouseClicked(evt);
            }
        });
        jLayeredPane6.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 30, -1, -1));

        jTextField8.setBackground(new Color(255,255,255,100));
        jTextField8.setBorder(null);
        jTextField8.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField8CaretUpdate(evt);
            }
        });
        jTextField8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTextField8MouseExited(evt);
            }
        });
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });
        jLayeredPane6.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 300, 28));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/informacion.png"))); // NOI18N
        jLabel22.setText("Información");
        jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel22MouseClicked(evt);
            }
        });
        jLayeredPane6.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 30, -1, -1));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/lupa-de-busqueda 16x.png"))); // NOI18N
        jLabel20.setText("Buscar");
        jLayeredPane6.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 26, -1, 20));

        jSeparator13.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator13.setOpaque(true);
        jLayeredPane6.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 48, 300, -1));

        jLabel125.setForeground(new java.awt.Color(255, 51, 51));
        jLabel125.setText("eliminar");
        jLabel125.setVisible(false);
        jLabel125.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel125MouseClicked(evt);
            }
        });
        jLayeredPane6.add(jLabel125, new org.netbeans.lib.awtextra.AbsoluteConstraints(699, 30, -1, 20));

        jLabel126.setForeground(new java.awt.Color(255, 51, 51));
        jLabel126.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/3Puntos 20x20.png"))); // NOI18N
        jLabel126.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel126.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel126MouseClicked(evt);
            }
        });
        jLayeredPane6.add(jLabel126, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 30, -1, 20));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/filtrar(2).png"))); // NOI18N
        jLabel7.setText("Ajustar filtro");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        jLayeredPane6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        totalInfo_filtroPro.setText("Mostrar mas");
        totalInfo_filtroPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalInfo_filtroProActionPerformed(evt);
            }
        });
        jLayeredPane6.add(totalInfo_filtroPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, -1, -1));

        p_filtroPro.add(jLayeredPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 780, 430));

        jPanel17.setBackground(new Color(255,255,255,150));
        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel17.setVisible(false);
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        p_filtroPro.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 780, 430));

        jLabel77.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/fondoGeneral.png"))); // NOI18N
        p_filtroPro.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 798, 500));

        panel_filtro.add(p_filtroPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));

        p_filtroDirecc.setVisible(false);
        p_filtroEdad.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLayeredPane3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/lupa-de-busqueda 16x.png"))); // NOI18N
        jLabel10.setText("Buscar");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        jLayeredPane3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 80, -1, -1));

        jLabel24.setText("Filtrado con rando de edad");
        jLayeredPane3.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, -1, -1));

        jTextField3.setBackground(new Color(255,255,255,100));
        jTextField3.setBorder(null);
        jTextField3.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField3CaretUpdate(evt);
            }
        });
        jTextField3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTextField3MouseExited(evt);
            }
        });
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jLayeredPane3.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, 330, 30));

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable6.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable6MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable6MousePressed(evt);
            }
        });
        //jTable6.setDefaultRenderer(Object.class, new Clases.table.TableGradientCell(new Color(153,204,255),new Color(255,153,255)));
        //jTable6.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(jTable6);

        jLayeredPane3.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 760, 310));

        jLabel79.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/configuraciones.png"))); // NOI18N
        jLabel79.setText("Modificar");
        jLabel79.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel79MouseClicked(evt);
            }
        });
        jLayeredPane3.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 30, -1, -1));

        jSeparator10.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator10.setOpaque(true);
        jLayeredPane3.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 100, 330, -1));

        jLabel78.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/informacion.png"))); // NOI18N
        jLabel78.setText("Información");
        jLabel78.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel78MouseClicked(evt);
            }
        });
        jLayeredPane3.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, -1, -1));

        jTextField11.setBackground(new Color(255,255,255,100));
        jTextField11.setText("0");
        jTextField11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jTextField11.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField11CaretUpdate(evt);
            }
        });
        jTextField11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTextField11MouseExited(evt);
            }
        });
        jTextField11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField11ActionPerformed(evt);
            }
        });
        jTextField11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField11KeyTyped(evt);
            }
        });
        jLayeredPane3.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 70, 30));

        jLabel8.setText("Edad minima");
        jLayeredPane3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        jLabel9.setText("Edad maxima");
        jLayeredPane3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, -1, -1));

        jTextField9.setBackground(new Color(255,255,255,100));
        jTextField9.setText("0");
        jTextField9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jTextField9.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField9CaretUpdate(evt);
            }
        });
        jTextField9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTextField9MouseExited(evt);
            }
        });
        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });
        jTextField9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField9KeyTyped(evt);
            }
        });
        jLayeredPane3.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 70, 30));

        jSeparator14.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator14.setForeground(new java.awt.Color(153, 153, 153));
        jSeparator14.setOpaque(true);
        jLayeredPane3.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 92, 70, -1));

        jSeparator15.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator15.setForeground(new java.awt.Color(153, 153, 153));
        jSeparator15.setOpaque(true);
        jLayeredPane3.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 92, 70, -1));

        jPanel1.setBackground(new Color(255,255,255,50));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 255, 255)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jLayeredPane3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 310, 90));

        jLabel106.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imprimir 16x16.png"))); // NOI18N
        jLabel106.setText("Imprimir");
        jLabel106.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel106MouseClicked(evt);
            }
        });
        jLayeredPane3.add(jLabel106, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 30, 70, -1));

        jLabel119.setForeground(new java.awt.Color(255, 51, 51));
        jLabel119.setText("eliminar");
        jLabel119.setVisible(false);
        jLabel119.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel119MouseClicked(evt);
            }
        });
        jLayeredPane3.add(jLabel119, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 30, -1, 20));

        jLabel120.setForeground(new java.awt.Color(255, 51, 51));
        jLabel120.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/3Puntos 20x20.png"))); // NOI18N
        jLabel120.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel120.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel120MouseClicked(evt);
            }
        });
        jLayeredPane3.add(jLabel120, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 30, -1, 20));

        p_filtroEdad.add(jLayeredPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 780, 430));

        jPanel15.setBackground(new Color(255,255,255,150));
        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        p_filtroEdad.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 780, 430));

        jLabel76.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/fondoGeneral.png"))); // NOI18N
        p_filtroEdad.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 798, 500));

        panel_filtro.add(p_filtroEdad, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));

        p_filtroDirecc.setVisible(false);
        p_filtroDirecc.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLayeredPane4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel111.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imprimir 16x16.png"))); // NOI18N
        jLabel111.setText("Imprimir");
        jLabel111.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel111MouseClicked(evt);
            }
        });
        jLayeredPane4.add(jLabel111, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 30, -1, -1));

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable7.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable7.setDefaultRenderer(Object.class, new Clases.table.TableGradientCell(new Color(153,204,255),new Color(255,153,255)));
        jTable7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable7MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable7MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable7MouseReleased(evt);
            }
        });
        jScrollPane7.setViewportView(jTable7);

        jLayeredPane4.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 760, 310));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/lupa-de-busqueda 16x.png"))); // NOI18N
        jLabel12.setText("Buscar");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        jLayeredPane4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 80, -1, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/informacion.png"))); // NOI18N
        jLabel14.setText("Información");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        jLayeredPane4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 30, -1, -1));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/configuraciones.png"))); // NOI18N
        jLabel15.setText("Modificar");
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });
        jLayeredPane4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 30, -1, -1));

        jSeparator11.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator11.setOpaque(true);
        jLayeredPane4.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 100, 290, -1));

        jTextField4.setBackground(new Color(255,255,255,100));
        jTextField4.setBorder(null);
        jTextField4.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField4CaretUpdate(evt);
            }
        });
        jTextField4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTextField4MouseExited(evt);
            }
        });
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jLayeredPane4.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 70, 290, 28));

        jLabel11.setText("Filtrado por dirección");
        jLayeredPane4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 120, -1));

        jComboBox1.setBackground(new Color(255,255,255,10));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(cargarStrikeCbbx()));
        jComboBox1.setBorder(null);
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jLayeredPane4.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 380, 30));

        jLabel121.setForeground(new java.awt.Color(255, 51, 51));
        jLabel121.setText("eliminar");
        jLabel121.setVisible(false);
        jLabel121.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel121MouseClicked(evt);
            }
        });
        jLayeredPane4.add(jLabel121, new org.netbeans.lib.awtextra.AbsoluteConstraints(699, 30, -1, 20));

        jLabel122.setForeground(new java.awt.Color(255, 51, 51));
        jLabel122.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/3Puntos 20x20.png"))); // NOI18N
        jLabel122.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel122.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel122MouseClicked(evt);
            }
        });
        jLayeredPane4.add(jLabel122, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 30, -1, 20));

        p_filtroDirecc.add(jLayeredPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 780, 430));

        jPanel7.setBackground(new Color(255,255,255,150));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        p_filtroDirecc.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 780, 430));

        jPanel6.setBackground(new Color(255,255,255,50));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 255, 255)));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        p_filtroDirecc.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 400, 90));

        jLabel73.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/fondoGeneral.png"))); // NOI18N
        p_filtroDirecc.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 798, 500));

        panel_filtro.add(p_filtroDirecc, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));

        p_filtroNEdc.setVisible(false);
        p_filtroNEdc.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLayeredPane5.setBackground(new Color(255,255,255,150));
        jLayeredPane5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jLayeredPane5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel112.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imprimir 16x16.png"))); // NOI18N
        jLabel112.setText("Imprimir");
        jLabel112.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel112MouseClicked(evt);
            }
        });
        jLayeredPane5.add(jLabel112, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 30, 70, -1));

        jTable8.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable8.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable8.setDefaultRenderer(Object.class, new Clases.table.TableGradientCell(new Color(153,204,255),new Color(255,153,255)));
        jTable8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable8MouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(jTable8);

        jLayeredPane5.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 760, 310));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/lupa-de-busqueda 16x.png"))); // NOI18N
        jLabel16.setText("Buscar");
        jLayeredPane5.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 80, -1, -1));

        jLabel13.setText("Filtrado por nivel educativo");
        jLayeredPane5.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, -1));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/configuraciones.png"))); // NOI18N
        jLabel19.setText("Modificar");
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });
        jLayeredPane5.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 30, -1, -1));

        jSeparator12.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator12.setOpaque(true);
        jLayeredPane5.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, 330, -1));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/informacion.png"))); // NOI18N
        jLabel18.setText("Información");
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
        });
        jLayeredPane5.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, -1, -1));

        jTextField5.setBackground(new Color(255,255,255,100));
        jTextField5.setBorder(null);
        jTextField5.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField5CaretUpdate(evt);
            }
        });
        jTextField5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTextField5MouseExited(evt);
            }
        });
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        jLayeredPane5.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, 330, 28));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(cargarNivelesEdcCbbx()));
        jComboBox2.setBorder(null);
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jLayeredPane5.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 310, 30));

        jLabel123.setForeground(new java.awt.Color(255, 51, 51));
        jLabel123.setText("eliminar");
        jLabel123.setVisible(false);
        jLabel123.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel123MouseClicked(evt);
            }
        });
        jLayeredPane5.add(jLabel123, new org.netbeans.lib.awtextra.AbsoluteConstraints(699, 30, -1, 20));

        jLabel124.setForeground(new java.awt.Color(255, 51, 51));
        jLabel124.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/3Puntos 20x20.png"))); // NOI18N
        jLabel124.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel124.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel124MouseClicked(evt);
            }
        });
        jLayeredPane5.add(jLabel124, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 30, -1, 20));

        p_filtroNEdc.add(jLayeredPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 780, 430));

        jPanel9.setBackground(new Color(255,255,255,50));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 255, 255)));

        jLabel80.setText("Seleccione el nivel educativo maximo que desea buscar");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel80)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        p_filtroNEdc.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 350, 70));

        jPanel11.setBackground(new Color(255,255,255,150));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        p_filtroNEdc.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 780, 430));

        jLabel74.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/fondoGeneral.png"))); // NOI18N
        p_filtroNEdc.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 798, 500));

        panel_filtro.add(p_filtroNEdc, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));

        panel_base.add(panel_filtro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));

        panel_registrar.setBackground(new java.awt.Color(245, 245, 245));
        panel_registrar.setVisible(false);
        panel_registrar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Registro3o4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel94.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        jLabel94.setText("Regresar");
        jLabel94.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel94MouseClicked(evt);
            }
        });
        Registro3o4.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 400, -1, -1));

        buttonGradient1.setText("Agregar como carga familiar");
        buttonGradient1.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        buttonGradient1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient1ActionPerformed(evt);
            }
        });
        Registro3o4.add(buttonGradient1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 330, 80));

        buttonGradient2.setText("Nuevo grupo familiar");
        buttonGradient2.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        buttonGradient2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient2ActionPerformed(evt);
            }
        });
        Registro3o4.add(buttonGradient2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 330, 80));

        panel_registrar.add(Registro3o4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 530, 430));

        barraLateralD.setBackground(new java.awt.Color(60, 133, 216));
        barraLateralD.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pr1.setBackground(new Color(255, 51, 71, 120));
        pr1.setRoundBottomLeft(15);
        pr1.setRoundTopLeft(15);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("General");

        javax.swing.GroupLayout pr1Layout = new javax.swing.GroupLayout(pr1);
        pr1.setLayout(pr1Layout);
        pr1Layout.setHorizontalGroup(
            pr1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pr1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pr1Layout.setVerticalGroup(
            pr1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pr1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        barraLateralD.add(pr1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 64, 170, 60));

        pr2.setBackground(new Color(51, 153, 255, 100));
        pr2.setRoundBottomLeft(15);
        pr2.setRoundTopLeft(15);

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/libro-abierto.png"))); // NOI18N
        jLabel29.setText("Educación");

        javax.swing.GroupLayout pr2Layout = new javax.swing.GroupLayout(pr2);
        pr2.setLayout(pr2Layout);
        pr2Layout.setHorizontalGroup(
            pr2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pr2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pr2Layout.setVerticalGroup(
            pr2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pr2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        barraLateralD.add(pr2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 157, 170, 60));

        pr3.setBackground(new Color(51, 153, 255, 100));
        pr3.setRoundBottomLeft(15);
        pr3.setRoundTopLeft(15);

        jLabel58.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/salud(1).png"))); // NOI18N
        jLabel58.setText("Salud");

        javax.swing.GroupLayout pr3Layout = new javax.swing.GroupLayout(pr3);
        pr3.setLayout(pr3Layout);
        pr3Layout.setHorizontalGroup(
            pr3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pr3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pr3Layout.setVerticalGroup(
            pr3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pr3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        barraLateralD.add(pr3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 170, 60));

        pr4.setBackground(new Color(51, 153, 255, 100));
        pr4.setRoundBottomLeft(15);
        pr4.setRoundTopLeft(15);

        jLabel59.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel59.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/control-parental.png"))); // NOI18N
        jLabel59.setText("Familia");

        javax.swing.GroupLayout pr4Layout = new javax.swing.GroupLayout(pr4);
        pr4.setLayout(pr4Layout);
        pr4Layout.setHorizontalGroup(
            pr4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pr4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pr4Layout.setVerticalGroup(
            pr4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pr4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        barraLateralD.add(pr4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 323, 170, 60));

        pr5.setBackground(new Color(51, 153, 255, 100));
        pr5.setRoundBottomLeft(15);
        pr5.setRoundTopLeft(15);

        jLabel60.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel60.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/hogar.png"))); // NOI18N
        jLabel60.setText("Casa");

        javax.swing.GroupLayout pr5Layout = new javax.swing.GroupLayout(pr5);
        pr5.setLayout(pr5Layout);
        pr5Layout.setHorizontalGroup(
            pr5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pr5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pr5Layout.setVerticalGroup(
            pr5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pr5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        barraLateralD.add(pr5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 406, 170, 60));

        jLabel61.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/barraDecoracion.png"))); // NOI18N
        barraLateralD.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        panel_registrar.add(barraLateralD, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 500));

        Registro1.setBackground(new java.awt.Color(245, 245, 245));
        Registro1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "¡Agrege los datos de la nueva persona!", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto Light", 2, 12))); // NOI18N
        Registro1.setVisible(false);
        Registro1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        enCorreo.setEditable(false);
        enCorreo.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        enCorreo.setForeground(new java.awt.Color(51, 51, 51));
        enCorreo.setText("Correo");
        enCorreo.setBorder(null);
        enCorreo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                enCorreoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                enCorreoMouseExited(evt);
            }
        });
        enCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                enCorreoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                enCorreoKeyTyped(evt);
            }
        });
        Registro1.add(enCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 340, 210, 30));

        etick_fechaN.setText("Fecha de nacimiendo");
        Registro1.add(etick_fechaN, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, 207, -1));

        enpNombre.setEditable(false);
        enpNombre.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        enpNombre.setForeground(new java.awt.Color(51, 51, 51));
        enpNombre.setText("Primer nombre");
        enpNombre.setBorder(null);
        enpNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                enpNombreMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                enpNombreMouseExited(evt);
            }
        });
        enpNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                enpNombreKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                enpNombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                enpNombreKeyTyped(evt);
            }
        });
        Registro1.add(enpNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 207, 30));

        ensNombre.setEditable(false);
        ensNombre.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        ensNombre.setForeground(new java.awt.Color(51, 51, 51));
        ensNombre.setText("Segundo nombre");
        ensNombre.setBorder(null);
        ensNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ensNombreMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ensNombreMouseExited(evt);
            }
        });
        ensNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ensNombreKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ensNombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ensNombreKeyTyped(evt);
            }
        });
        Registro1.add(ensNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 50, 207, 30));

        enpApellido.setEditable(false);
        enpApellido.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        enpApellido.setForeground(new java.awt.Color(51, 51, 51));
        enpApellido.setText("Primer apellido");
        enpApellido.setBorder(null);
        enpApellido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                enpApellidoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                enpApellidoMouseExited(evt);
            }
        });
        enpApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                enpApellidoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                enpApellidoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                enpApellidoKeyTyped(evt);
            }
        });
        Registro1.add(enpApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 207, 30));

        ensApellido.setEditable(false);
        ensApellido.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        ensApellido.setForeground(new java.awt.Color(51, 51, 51));
        ensApellido.setText("Segundo Apellido");
        ensApellido.setBorder(null);
        ensApellido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ensApellidoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ensApellidoMouseExited(evt);
            }
        });
        ensApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ensApellidoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ensApellidoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ensApellidoKeyTyped(evt);
            }
        });
        Registro1.add(ensApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 120, 207, 30));

        enTelefono.setEditable(false);
        enTelefono.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        enTelefono.setForeground(new java.awt.Color(51, 51, 51));
        enTelefono.setText("Telefono");
        enTelefono.setBorder(null);
        enTelefono.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                enTelefonoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                enTelefonoMouseExited(evt);
            }
        });
        enTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                enTelefonoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                enTelefonoKeyTyped(evt);
            }
        });
        Registro1.add(enTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 210, 30));

        enCedula.setEditable(false);
        enCedula.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        enCedula.setForeground(new java.awt.Color(51, 51, 51));
        enCedula.setText("Cedula de Identidad");
        enCedula.setBorder(null);
        enCedula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                enCedulaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                enCedulaMouseExited(evt);
            }
        });
        enCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                enCedulaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                enCedulaKeyTyped(evt);
            }
        });
        Registro1.add(enCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 207, 30));

        dSeparador_pNombre.setBackground(new java.awt.Color(51, 51, 51));
        dSeparador_pNombre.setForeground(new java.awt.Color(153, 153, 153));
        dSeparador_pNombre.setOpaque(true);
        Registro1.add(dSeparador_pNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 207, -1));

        dSeparador_sNombre.setBackground(new java.awt.Color(51, 51, 51));
        dSeparador_sNombre.setForeground(new java.awt.Color(153, 153, 153));
        dSeparador_sNombre.setOpaque(true);
        Registro1.add(dSeparador_sNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, 207, -1));

        dSeparador_pApellido.setBackground(new java.awt.Color(51, 51, 51));
        dSeparador_pApellido.setForeground(new java.awt.Color(153, 153, 153));
        dSeparador_pApellido.setOpaque(true);
        Registro1.add(dSeparador_pApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 207, -1));

        dSeparador_sApellido.setBackground(new java.awt.Color(51, 51, 51));
        dSeparador_sApellido.setForeground(new java.awt.Color(153, 153, 153));
        dSeparador_sApellido.setOpaque(true);
        Registro1.add(dSeparador_sApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 150, 207, -1));

        dSeparador_cedula.setBackground(new java.awt.Color(51, 51, 51));
        dSeparador_cedula.setForeground(new java.awt.Color(153, 153, 153));
        dSeparador_cedula.setOpaque(true);
        Registro1.add(dSeparador_cedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 207, -1));

        dSeparador_telefono.setBackground(new java.awt.Color(51, 51, 51));
        dSeparador_telefono.setForeground(new java.awt.Color(153, 153, 153));
        dSeparador_telefono.setOpaque(true);
        Registro1.add(dSeparador_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 210, -1));

        dSeparador_correo.setBackground(new java.awt.Color(51, 51, 51));
        dSeparador_correo.setForeground(new java.awt.Color(153, 153, 153));
        dSeparador_correo.setOpaque(true);
        Registro1.add(dSeparador_correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 370, 210, -1));

        etick_nacionalidad.setText("nacionalidad");
        Registro1.add(etick_nacionalidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 250, 120, -1));

        etick_pNombre.setText("Primer Nombre. ¡Necesario!");
        Registro1.add(etick_pNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        etick_sNombre.setText("Segundo Nombre. ¡Necesario!");
        Registro1.add(etick_sNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, -1, -1));

        etick_pApellido.setText("Primer Apellido. ¡Necesario!");
        Registro1.add(etick_pApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        etick_sApellido.setText("Segundo Apellido. ¡Necesario!");
        Registro1.add(etick_sApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, -1, -1));

        etick_cedula.setText("cedula de identidad");
        Registro1.add(etick_cedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        etick_telefono.setText("Telefono. ¡Opcional!");
        Registro1.add(etick_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 120, -1));

        etick_correo.setText("Correo ¡Opcional!");
        Registro1.add(etick_correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 320, 120, -1));

        etick_genero.setText("genero");
        Registro1.add(etick_genero, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 120, -1));

        entrada_fechaN_registro.setBorder(null);
        Registro1.add(entrada_fechaN_registro, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 200, 160, 30));

        bt_fechaN_registro1.setBackground(new java.awt.Color(102, 0, 255));
        bt_fechaN_registro1.setBorder(null);
        bt_fechaN_registro1.setText("buttonShadow1");
        bt_fechaN_registro1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_fechaN_registro1ActionPerformed(evt);
            }
        });
        Registro1.add(bt_fechaN_registro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 202, 15, 26));

        enNacionalidad.setModel(new javax.swing.DefaultComboBoxModel<>(cargarNacionalidadCbbx()));
        Registro1.add(enNacionalidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 270, 207, 30));

        enSexo.setModel(new javax.swing.DefaultComboBoxModel<>(cargarSexoCbbx()));
        enSexo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enSexoActionPerformed(evt);
            }
        });
        Registro1.add(enSexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 207, 30));

        bt_registro1_siguiente.setFont(new java.awt.Font("Corbel", 1, 16)); // NOI18N
        bt_registro1_siguiente.setText("Siguiente");
        bt_registro1_siguiente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bt_registro1_siguienteMousePressed(evt);
            }
        });
        Registro1.add(bt_registro1_siguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 400, -1, -1));

        panel_registrar.add(Registro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 530, 430));

        Registro2.setBackground(new java.awt.Color(245, 245, 245));
        Registro2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Nivel maximo de educación", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto", 2, 13))); // NOI18N
        Registro2.setPreferredSize(new java.awt.Dimension(508, 420));
        Registro2.setVisible(false);
        Registro2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel13.setBackground(new java.awt.Color(245, 245, 245));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Seleccione el nivel educativo mas alto alcanzado"));

        checkbox_entradaNVedc_eduacionBasica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_entradaNVedc_eduacionBasicaActionPerformed(evt);
            }
        });

        checkbox_entradaNVedc_educacionInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_entradaNVedc_educacionInicialActionPerformed(evt);
            }
        });

        checkbox_entradaNVedc_educacionMedia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_entradaNVedc_educacionMediaActionPerformed(evt);
            }
        });

        checkbox_entradaNVedc_eduacionSuperior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_entradaNVedc_eduacionSuperiorActionPerformed(evt);
            }
        });

        label_entradaNVedc_educacionSuperior.setText("Educación Superior");

        label_entradaNVedc_educacionMedia.setText("Educación Media");

        label_entradaNVedc_eduacionBasica.setText("Educación Basica");

        label_entradaNVedc_educacionInicial.setText("Educación Inicial");

        label_entradaNVedc_sinEducacion.setText("Sin Educacion");

        checkbox_entradaNVedc_sinEducacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_entradaNVedc_sinEducacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(label_entradaNVedc_educacionInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label_entradaNVedc_sinEducacion, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(label_entradaNVedc_eduacionBasica, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_entradaNVedc_educacionMedia, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_entradaNVedc_educacionSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkbox_entradaNVedc_educacionInicial)
                    .addComponent(checkbox_entradaNVedc_eduacionBasica)
                    .addComponent(checkbox_entradaNVedc_eduacionSuperior)
                    .addComponent(checkbox_entradaNVedc_educacionMedia)
                    .addComponent(checkbox_entradaNVedc_sinEducacion))
                .addGap(100, 100, 100))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_entradaNVedc_sinEducacion, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkbox_entradaNVedc_sinEducacion))
                .addGap(30, 30, 30)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_entradaNVedc_educacionInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkbox_entradaNVedc_educacionInicial))
                .addGap(30, 30, 30)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_entradaNVedc_eduacionBasica, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkbox_entradaNVedc_eduacionBasica))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_entradaNVedc_educacionMedia, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkbox_entradaNVedc_educacionMedia))
                .addGap(29, 29, 29)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(checkbox_entradaNVedc_eduacionSuperior)
                    .addComponent(label_entradaNVedc_educacionSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );

        Registro2.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 450, 300));

        jCheckBox10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox10ActionPerformed(evt);
            }
        });
        Registro2.add(jCheckBox10, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, -1, -1));

        jLabel49.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        jLabel49.setText("Regresar");
        jLabel49.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel49MouseClicked(evt);
            }
        });
        Registro2.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 400, -1, -1));

        jLabel50.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        jLabel50.setText("Siguiente");
        jLabel50.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel50MouseClicked(evt);
            }
        });
        Registro2.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 400, -1, -1));

        panel_registrar.add(Registro2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 530, 430));

        Registro3.setBackground(new java.awt.Color(245, 245, 245));
        Registro3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto", 2, 13))); // NOI18N
        Registro3.setMinimumSize(new java.awt.Dimension(510, 420));
        Registro3.setPreferredSize(new java.awt.Dimension(508, 420));
        Registro3.setVisible(false);
        Registro3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Registro3MousePressed(evt);
            }
        });
        Registro3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setBackground(new java.awt.Color(245, 245, 245));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "¿Tiene alguna enfermedad discapacidad?", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto", 2, 12))); // NOI18N
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane4.setBorder(null);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        jTable4.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable4.setDefaultRenderer(Object.class, new Clases.table.TableGradientCell(new Color(153,204,255),new Color(255,153,255)));
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable4MousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);

        jPanel10.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 470, 180));

        label_registroP_detallesSs_Descripcion.setEditable(false);
        label_registroP_detallesSs_Descripcion.setBackground(new java.awt.Color(245, 245, 245));
        label_registroP_detallesSs_Descripcion.setColumns(20);
        label_registroP_detallesSs_Descripcion.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        label_registroP_detallesSs_Descripcion.setRows(5);
        label_registroP_detallesSs_Descripcion.setText("Descripción: ");
        label_registroP_detallesSs_Descripcion.setBorder(null);
        jPanel10.add(label_registroP_detallesSs_Descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 470, 40));

        jLabel103.setText("Discapacidades");
        jPanel10.add(jLabel103, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 126, 90, -1));

        label_registroP_detallesSs_nombre.setText("Nombre de discapacidad: ");
        jPanel10.add(label_registroP_detallesSs_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        label_registroP_detallesSs_tipo.setText("Tipo:");
        jPanel10.add(label_registroP_detallesSs_tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        combo_entradaRdd_listaDiscapacidades.setModel(new javax.swing.DefaultComboBoxModel<>(cargarDdCbbx()));
        combo_entradaRdd_listaDiscapacidades.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combo_entradaRdd_listaDiscapacidadesItemStateChanged(evt);
            }
        });
        combo_entradaRdd_listaDiscapacidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_entradaRdd_listaDiscapacidadesActionPerformed(evt);
            }
        });
        jPanel10.add(combo_entradaRdd_listaDiscapacidades, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 140, 320, 30));

        boton_entradaRdd_agregar.setText("Agregar");
        boton_entradaRdd_agregar.setToolTipText("");
        boton_entradaRdd_agregar.setColorDeSombra(new java.awt.Color(51, 51, 51));
        boton_entradaRdd_agregar.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        boton_entradaRdd_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_entradaRdd_agregarActionPerformed(evt);
            }
        });
        jPanel10.add(boton_entradaRdd_agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 140, 70, 30));

        buttonAction1.setText("Quitar");
        buttonAction1.setColorDeSombra(new java.awt.Color(51, 51, 51));
        buttonAction1.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        buttonAction1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction1ActionPerformed(evt);
            }
        });
        jPanel10.add(buttonAction1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 140, 70, 30));

        Registro3.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 490, 370));

        jLabel51.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        jLabel51.setText("Regresar");
        jLabel51.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel51MouseClicked(evt);
            }
        });
        Registro3.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 400, -1, -1));

        jLabel53.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        jLabel53.setText("Siguiente");
        jLabel53.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel53MouseClicked(evt);
            }
        });
        Registro3.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 400, -1, -1));

        panel_registrar.add(Registro3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 530, 430));

        Registro4.setBackground(new java.awt.Color(245, 245, 245));
        Registro4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto", 2, 13))); // NOI18N
        Registro4.setMinimumSize(new java.awt.Dimension(508, 420));
        Registro4.setPreferredSize(new java.awt.Dimension(508, 420));
        Registro4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField2.setText("Buscar...");
        jTextField2.setBorder(null);
        jTextField2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField2CaretUpdate(evt);
            }
        });
        jTextField2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTextField2MouseExited(evt);
            }
        });
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        Registro4.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 338, 30));

        jLabel32.setText("Rol familiar");
        Registro4.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(rolesFm));
        jComboBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox7ActionPerformed(evt);
            }
        });
        Registro4.add(jComboBox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 133, -1));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Rol Familiar", "Nombre", "Cedula"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jTable2.setDefaultRenderer(Object.class, new Clases.table.TableGradientCell(new Color(153,204,255),new Color(255,153,255)));
        jScrollPane2.setViewportView(jTable2);

        Registro4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 158, 480, 230));

        jButton3.setText("Añadir+");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        Registro4.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 110, -1, 30));

        jLabel54.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        jLabel54.setText("Siguiente");
        jLabel54.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel54MouseClicked(evt);
            }
        });
        Registro4.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 400, -1, -1));

        jLabel55.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        jLabel55.setText("Regresar");
        jLabel55.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel55MouseClicked(evt);
            }
        });
        Registro4.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 400, -1, -1));

        jSeparator19.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator19.setOpaque(true);
        jSeparator19.setRequestFocusEnabled(false);
        Registro4.add(jSeparator19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 340, -1));

        panel_registrar.add(Registro4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 530, 430));
        Registro4.getAccessibleContext().setAccessibleDescription("");

        Registro5.setBackground(new java.awt.Color(245, 245, 245));
        Registro5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto", 2, 13))); // NOI18N
        Registro5.setVisible(false);
        Registro5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(245, 245, 245));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Informacion referente a la Vivienda "));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel40.setText("Numero de la casa");
        jPanel8.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 103, -1));

        en_nCasa.setBorder(null);
        en_nCasa.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                en_nCasaCaretUpdate(evt);
            }
        });
        en_nCasa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                en_nCasaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                en_nCasaMouseExited(evt);
            }
        });
        en_nCasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                en_nCasaActionPerformed(evt);
            }
        });
        jPanel8.add(en_nCasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 130, 20));

        en_not_nCasa.setText("no posee");
        en_not_nCasa.setSelected(true);
        en_not_nCasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                en_not_nCasaActionPerformed(evt);
            }
        });
        jPanel8.add(en_not_nCasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, -1, -1));

        jLabel41.setText("Direccion");
        jPanel8.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 55, -1));

        en_combo_direccion.setModel(new javax.swing.DefaultComboBoxModel<>(cargarStrikeCbbx()));
        en_combo_direccion.setBorder(null);
        jPanel8.add(en_combo_direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 420, -1));

        jPanel12.setBackground(new java.awt.Color(245, 245, 245));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Servicios disponibles"));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel42.setText("Agua");
        jPanel12.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 94, -1));

        jLabel43.setText("Aguas negras");
        jPanel12.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 94, -1));

        jLabel44.setText("Electricidad");
        jPanel12.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 94, -1));

        jLabel45.setText("Modulo CLP");
        jPanel12.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 94, -1));

        en_serv_agua_s.setText("Si");
        en_serv_agua_s.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                en_serv_agua_sActionPerformed(evt);
            }
        });
        jPanel12.add(en_serv_agua_s, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, -1, -1));

        en_serv_agua_n.setText("No");
        en_serv_agua_n.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                en_serv_agua_nActionPerformed(evt);
            }
        });
        jPanel12.add(en_serv_agua_n, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, -1, -1));

        en_serv_aguaN_n.setText("No");
        en_serv_aguaN_n.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                en_serv_aguaN_nActionPerformed(evt);
            }
        });
        jPanel12.add(en_serv_aguaN_n, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, -1, -1));

        en_serv_aguaN_s.setText("Si");
        en_serv_aguaN_s.setSelected(true);
        en_serv_aguaN_s.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                en_serv_aguaN_sActionPerformed(evt);
            }
        });
        jPanel12.add(en_serv_aguaN_s, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, -1, -1));

        en_serv_elct_n.setText("No");
        en_serv_elct_n.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                en_serv_elct_nActionPerformed(evt);
            }
        });
        jPanel12.add(en_serv_elct_n, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, -1, -1));

        en_serv_elct_s.setText("Si");
        en_serv_elct_s.setSelected(true);
        en_serv_elct_s.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                en_serv_elct_sActionPerformed(evt);
            }
        });
        jPanel12.add(en_serv_elct_s, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, -1, -1));

        en_serv_cls_n.setText("No");
        en_serv_cls_n.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                en_serv_cls_nActionPerformed(evt);
            }
        });
        jPanel12.add(en_serv_cls_n, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 150, -1, -1));

        en_serv_cls_s.setText("Si");
        en_serv_cls_s.setSelected(true);
        en_serv_cls_s.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                en_serv_cls_sActionPerformed(evt);
            }
        });
        jPanel12.add(en_serv_cls_s, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, -1, -1));

        jPanel8.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 163, 422, 190));

        jLabel47.setText("Condicion de la casa");
        jPanel8.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, 20));

        en_combo_stdCasa.setModel(new javax.swing.DefaultComboBoxModel<>(cargarStadoCasaCbbx()));
        en_combo_stdCasa.setBorder(null);
        jPanel8.add(en_combo_stdCasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 270, -1));

        jSeparator18.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator18.setOpaque(true);
        jPanel8.add(jSeparator18, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 130, -1));

        Registro5.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 500, 360));

        jLabel56.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        jLabel56.setText("Regresar");
        jLabel56.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel56MouseClicked(evt);
            }
        });
        Registro5.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 400, -1, -1));

        jLabel57.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        jLabel57.setText("Completar");
        jLabel57.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel57MouseClicked(evt);
            }
        });
        Registro5.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 400, -1, 20));

        panel_registrar.add(Registro5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 530, 430));

        panel_base.add(panel_registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));

        panel_demografia.setBackground(new java.awt.Color(245, 245, 245));
        panel_demografia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        panel_demografia.setMinimumSize(new java.awt.Dimension(740, 480));
        panel_demografia.setPreferredSize(new java.awt.Dimension(740, 870));
        panel_demografia.setVisible(false);
        panel_demografia.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_dmg_strike.setVisible(false);
        panel_dmg_strike.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable3.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        cargarCalles();
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable3MousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        panel_dmg_strike.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 680, 290));

        bt_dmg_estruc.setBackground(new Color(153, 153, 153));
        bt_dmg_estruc.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        bt_dmg_estruc.setForeground(disponible);
        bt_dmg_estruc.setText("Ver estructura");
        bt_dmg_estruc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_dmg_estrucMouseClicked(evt);
            }
        });
        panel_dmg_strike.add(bt_dmg_estruc, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, -1, 20));

        bt_dmg_addLider.setBackground(new java.awt.Color(153, 153, 153));
        bt_dmg_addLider.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        bt_dmg_addLider.setForeground(nulo);
        bt_dmg_addLider.setText("Asignar lider de calle");
        bt_dmg_addLider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_dmg_addLiderMouseClicked(evt);
            }
        });
        panel_dmg_strike.add(bt_dmg_addLider, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, -1, 20));

        bt_dmg_addStrike.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        bt_dmg_addStrike.setForeground(disponible);
        bt_dmg_addStrike.setText("Agregar Calle");
        bt_dmg_addStrike.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_dmg_addStrikeMouseClicked(evt);
            }
        });
        panel_dmg_strike.add(bt_dmg_addStrike, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, 20));

        entradaTxt_demografia.setEditable(false);
        entradaTxt_demografia.setBackground(new java.awt.Color(255, 255, 255));
        entradaTxt_demografia.setBorder(null);
        entradaTxt_demografia.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                entradaTxt_demografiaCaretUpdate(evt);
            }
        });
        entradaTxt_demografia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                entradaTxt_demografiaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                entradaTxt_demografiaMouseExited(evt);
            }
        });
        entradaTxt_demografia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entradaTxt_demografiaActionPerformed(evt);
            }
        });
        entradaTxt_demografia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                entradaTxt_demografiaKeyReleased(evt);
            }
        });
        entradaTxt_demografia.setText("");
        panel_dmg_strike.add(entradaTxt_demografia, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 330, 30));

        bt_dmg_guardar.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        bt_dmg_guardar.setForeground(new java.awt.Color(0, 153, 0));
        bt_dmg_guardar.setText("Guardar");
        bt_dmg_guardar.setVisible(false);
        bt_dmg_guardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_dmg_guardarMouseClicked(evt);
            }
        });
        panel_dmg_strike.add(bt_dmg_guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, -1, 20));

        bt_dmg_descartar.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        bt_dmg_descartar.setForeground(new java.awt.Color(204, 0, 0));
        bt_dmg_descartar.setText("Descartar");
        bt_dmg_descartar.setVisible(false);
        bt_dmg_descartar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_dmg_descartarMouseClicked(evt);
            }
        });
        panel_dmg_strike.add(bt_dmg_descartar, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 70, -1, 20));

        jSeparator20.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator20.setOpaque(true);
        panel_dmg_strike.add(jSeparator20, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 330, 1));

        bt_dmg_stingStrike.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        bt_dmg_stingStrike.setForeground(disponible);
        bt_dmg_stingStrike.setText("Modificar Calle");
        bt_dmg_stingStrike.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_dmg_stingStrikeMouseClicked(evt);
            }
        });
        panel_dmg_strike.add(bt_dmg_stingStrike, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, 20));

        panel_demografia.add(panel_dmg_strike, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 720, 410));

        panel_dmg_lider.setBackground(new java.awt.Color(0, 0, 0));
        panel_dmg_lider.setPreferredSize(new java.awt.Dimension(710, 400));
        panel_dmg_lider.setVisible(false);
        panel_dmg_lider.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator2.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator2.setForeground(new java.awt.Color(153, 153, 153));
        jSeparator2.setOpaque(true);
        panel_dmg_lider.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 490, -1));

        jLabel63.setText("Añadir lider de calle");
        panel_dmg_lider.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 240, -1));

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTable5);

        panel_dmg_lider.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 680, 250));

        jTextField10.setBackground(new Color(255,255,255,150));
        jTextField10.setText("Buscar...");
        jTextField10.setBorder(null);
        jTextField10.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField10CaretUpdate(evt);
            }
        });
        jTextField10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTextField10MouseExited(evt);
            }
        });
        panel_dmg_lider.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 490, 30));

        jButton6.setText("Agregar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        panel_dmg_lider.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 100, -1, -1));

        combo_demografia_asigLider.setModel(new javax.swing.DefaultComboBoxModel<>(cargarStrikeCbbx()));
        panel_dmg_lider.add(combo_demografia_asigLider, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 490, -1));

        panel_demografia.add(panel_dmg_lider, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 750, 410));

        jPanel4.setBackground(new Color(255,255,255,150));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panel_demografia.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 750, 410));

        jLabel62.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/fondoGeneral.png"))); // NOI18N
        panel_demografia.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));

        panel_base.add(panel_demografia, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));

        panelBarra.setBackground(new java.awt.Color(0,0,0,0));
        panelBarra.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panelBarraMouseDragged(evt);
            }
        });
        panelBarra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelBarraMousePressed(evt);
            }
        });
        panelBarra.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panel_base.add(panelBarra, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 30));

        panelMenup.setBackground(new java.awt.Color(0, 233, 233));
        panelMenup.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bt_menu_agregar.setForeground(new java.awt.Color(0, 0, 0));
        bt_menu_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/residente.png"))); // NOI18N
        bt_menu_agregar.setText("Agregar");
        bt_menu_agregar.setColor1(new java.awt.Color(51, 102, 255));
        bt_menu_agregar.setColor2(new java.awt.Color(200, 94, 160));
        bt_menu_agregar.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        bt_menu_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_menu_agregarActionPerformed(evt);
            }
        });
        panelMenup.add(bt_menu_agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 230, 60));

        bt_menu_demografia.setForeground(new java.awt.Color(0, 0, 0));
        bt_menu_demografia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/agregar32x32.png"))); // NOI18N
        bt_menu_demografia.setText("Demografia");
        bt_menu_demografia.setColor1(new java.awt.Color(51, 102, 255));
        bt_menu_demografia.setColor2(new java.awt.Color(200, 94, 160));
        bt_menu_demografia.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        bt_menu_demografia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_menu_demografiaActionPerformed(evt);
            }
        });
        panelMenup.add(bt_menu_demografia, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 95, 230, 60));

        bt_mn_buscar.setForeground(new java.awt.Color(0, 0, 0));
        bt_mn_buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/lupa-de-busqueda 32x.png"))); // NOI18N
        bt_mn_buscar.setText("Buscar");
        bt_mn_buscar.setColor1(new java.awt.Color(51, 102, 255));
        bt_mn_buscar.setColor2(new java.awt.Color(200, 94, 160));
        bt_mn_buscar.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        bt_mn_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_mn_buscarActionPerformed(evt);
            }
        });
        panelMenup.add(bt_mn_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 230, 60));

        bt_menu_modificar.setForeground(new java.awt.Color(0, 0, 0));
        bt_menu_modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/editar 32x32.png"))); // NOI18N
        bt_menu_modificar.setText("Modificar");
        bt_menu_modificar.setColor1(new java.awt.Color(51, 102, 255));
        bt_menu_modificar.setColor2(new java.awt.Color(200, 94, 160));
        bt_menu_modificar.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        bt_menu_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_menu_modificarActionPerformed(evt);
            }
        });
        panelMenup.add(bt_menu_modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 225, 230, 60));

        bt_menu_imprimir.setForeground(new java.awt.Color(0, 0, 0));
        bt_menu_imprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imprimir.png"))); // NOI18N
        bt_menu_imprimir.setText("Imprimir");
        bt_menu_imprimir.setColor1(new java.awt.Color(51, 102, 255));
        bt_menu_imprimir.setColor2(new java.awt.Color(200, 94, 160));
        bt_menu_imprimir.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        bt_menu_imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_menu_imprimirActionPerformed(evt);
            }
        });
        panelMenup.add(bt_menu_imprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 230, 60));

        bt_menu_gestionar.setForeground(new java.awt.Color(0, 0, 0));
        bt_menu_gestionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/pregunta.png"))); // NOI18N
        bt_menu_gestionar.setText("Gestionar");
        bt_menu_gestionar.setColor1(new java.awt.Color(51, 102, 255));
        bt_menu_gestionar.setColor2(new java.awt.Color(200, 94, 160));
        bt_menu_gestionar.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        bt_menu_gestionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_menu_gestionarActionPerformed(evt);
            }
        });
        panelMenup.add(bt_menu_gestionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 355, 230, 60));

        bt_menu_usuario.setForeground(new java.awt.Color(0, 0, 0));
        bt_menu_usuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/usuario 32x32.png"))); // NOI18N
        bt_menu_usuario.setText("Usuario");
        bt_menu_usuario.setColor1(new java.awt.Color(51, 102, 255));
        bt_menu_usuario.setColor2(new java.awt.Color(200, 94, 160));
        bt_menu_usuario.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        bt_menu_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_menu_usuarioActionPerformed(evt);
            }
        });
        panelMenup.add(bt_menu_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 429, 230, 60));

        itemM_agregar_persona.setForeground(new java.awt.Color(0, 0, 0));
        itemM_agregar_persona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/persona 24x24.png"))); // NOI18N
        itemM_agregar_persona.setText("Persona");
        itemM_agregar_persona.setColor1(new java.awt.Color(102, 204, 255));
        itemM_agregar_persona.setColor2(new java.awt.Color(153, 130, 157));
        itemM_agregar_persona.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        itemM_agregar_persona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemM_agregar_personaActionPerformed(evt);
            }
        });
        //itemM_agregar_persona.setAnimacionAct(false);
        panelMenup.add(itemM_agregar_persona, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, -1, 60));

        itemM_agregar_cargaFamiliar.setForeground(new java.awt.Color(0, 0, 0));
        itemM_agregar_cargaFamiliar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/familia 24x24.png"))); // NOI18N
        itemM_agregar_cargaFamiliar.setText("Carga Familiar");
        itemM_agregar_cargaFamiliar.setColor1(new java.awt.Color(102, 204, 255));
        itemM_agregar_cargaFamiliar.setColor2(new java.awt.Color(153, 130, 157));
        itemM_agregar_cargaFamiliar.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        itemM_agregar_cargaFamiliar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemM_agregar_cargaFamiliarActionPerformed(evt);
            }
        });
        panelMenup.add(itemM_agregar_cargaFamiliar, new org.netbeans.lib.awtextra.AbsoluteConstraints(385, 30, -1, 60));

        itemM_agregar_LiderCalle.setForeground(new java.awt.Color(0, 0, 0));
        itemM_agregar_LiderCalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/lider 24x24.png"))); // NOI18N
        itemM_agregar_LiderCalle.setText("Lider de Calle");
        itemM_agregar_LiderCalle.setColor1(new java.awt.Color(102, 204, 255));
        itemM_agregar_LiderCalle.setColor2(new java.awt.Color(153, 130, 157));
        itemM_agregar_LiderCalle.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        itemM_agregar_LiderCalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemM_agregar_LiderCalleActionPerformed(evt);
            }
        });
        panelMenup.add(itemM_agregar_LiderCalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 30, -1, 60));

        itemM_demografia_addCalle.setForeground(new java.awt.Color(0, 0, 0));
        itemM_demografia_addCalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/mapa.png"))); // NOI18N
        itemM_demografia_addCalle.setText("Agregar Calle");
        itemM_demografia_addCalle.setColor1(new java.awt.Color(102, 204, 255));
        itemM_demografia_addCalle.setColor2(new java.awt.Color(153, 130, 157));
        itemM_demografia_addCalle.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        itemM_demografia_addCalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemM_demografia_addCalleActionPerformed(evt);
            }
        });
        panelMenup.add(itemM_demografia_addCalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 95, -1, 60));

        itemM_demografia_infComunal.setForeground(new java.awt.Color(0, 0, 0));
        itemM_demografia_infComunal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/informacion 24x24.png"))); // NOI18N
        itemM_demografia_infComunal.setText("Información comunal");
        itemM_demografia_infComunal.setToolTipText("");
        itemM_demografia_infComunal.setColor1(new java.awt.Color(102, 204, 255));
        itemM_demografia_infComunal.setColor2(new java.awt.Color(153, 130, 157));
        itemM_demografia_infComunal.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        itemM_demografia_infComunal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemM_demografia_infComunalActionPerformed(evt);
            }
        });
        panelMenup.add(itemM_demografia_infComunal, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 95, -1, 60));

        itemM_filtro_buscar.setForeground(new java.awt.Color(0, 0, 0));
        itemM_filtro_buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/buscar lupa 24x24.png"))); // NOI18N
        itemM_filtro_buscar.setText("Buscar");
        itemM_filtro_buscar.setColor1(new java.awt.Color(102, 204, 255));
        itemM_filtro_buscar.setColor2(new java.awt.Color(153, 130, 157));
        itemM_filtro_buscar.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        itemM_filtro_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemM_filtro_buscarActionPerformed(evt);
            }
        });
        panelMenup.add(itemM_filtro_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 160, -1, 60));

        itemM_filtro_ubicacion.setForeground(new java.awt.Color(0, 0, 0));
        itemM_filtro_ubicacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ubicacion 24x24.png"))); // NOI18N
        itemM_filtro_ubicacion.setText("Ubicacion");
        itemM_filtro_ubicacion.setColor1(new java.awt.Color(102, 204, 255));
        itemM_filtro_ubicacion.setColor2(new java.awt.Color(153, 130, 157));
        itemM_filtro_ubicacion.setFont(new java.awt.Font("Roboto Light", 1, 13)); // NOI18N
        itemM_filtro_ubicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemM_filtro_ubicacionActionPerformed(evt);
            }
        });
        panelMenup.add(itemM_filtro_ubicacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(368, 160, -1, 60));

        itemM_filtro_edad.setForeground(new java.awt.Color(0, 0, 0));
        itemM_filtro_edad.setText("Edad");
        itemM_filtro_edad.setColor1(new java.awt.Color(102, 204, 255));
        itemM_filtro_edad.setColor2(new java.awt.Color(153, 130, 157));
        itemM_filtro_edad.setFont(new java.awt.Font("Roboto Light", 1, 13)); // NOI18N
        itemM_filtro_edad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemM_filtro_edadActionPerformed(evt);
            }
        });
        panelMenup.add(itemM_filtro_edad, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 160, -1, 60));

        itemM_filtro_nvlEdc.setForeground(new java.awt.Color(0, 0, 0));
        itemM_filtro_nvlEdc.setText("Nv Educativo");
        itemM_filtro_nvlEdc.setColor1(new java.awt.Color(102, 204, 255));
        itemM_filtro_nvlEdc.setColor2(new java.awt.Color(153, 130, 157));
        itemM_filtro_nvlEdc.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        itemM_filtro_nvlEdc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemM_filtro_nvlEdcActionPerformed(evt);
            }
        });
        panelMenup.add(itemM_filtro_nvlEdc, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 160, -1, 60));

        itemM_filtro_otro.setForeground(new java.awt.Color(0, 0, 0));
        itemM_filtro_otro.setText("Otro");
        itemM_filtro_otro.setColor1(new java.awt.Color(102, 204, 255));
        itemM_filtro_otro.setColor2(new java.awt.Color(153, 130, 157));
        itemM_filtro_otro.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        itemM_filtro_otro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemM_filtro_otroActionPerformed(evt);
            }
        });
        panelMenup.add(itemM_filtro_otro, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 160, -1, 60));

        itemM_modificar_persona.setForeground(new java.awt.Color(0, 0, 0));
        itemM_modificar_persona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/persona 24x24.png"))); // NOI18N
        itemM_modificar_persona.setText("Persona");
        itemM_modificar_persona.setColor1(new java.awt.Color(102, 204, 255));
        itemM_modificar_persona.setColor2(new java.awt.Color(153, 130, 157));
        itemM_modificar_persona.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        itemM_modificar_persona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemM_modificar_personaActionPerformed(evt);
            }
        });
        panelMenup.add(itemM_modificar_persona, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 225, -1, 60));

        itemM_modificar_familia.setForeground(new java.awt.Color(0, 0, 0));
        itemM_modificar_familia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/familia 24x24.png"))); // NOI18N
        itemM_modificar_familia.setText("Familia");
        itemM_modificar_familia.setColor1(new java.awt.Color(102, 204, 255));
        itemM_modificar_familia.setColor2(new java.awt.Color(153, 130, 157));
        itemM_modificar_familia.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        itemM_modificar_familia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemM_modificar_familiaActionPerformed(evt);
            }
        });
        panelMenup.add(itemM_modificar_familia, new org.netbeans.lib.awtextra.AbsoluteConstraints(385, 225, -1, 60));

        itemM_modificar_calle.setForeground(new java.awt.Color(0, 0, 0));
        itemM_modificar_calle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/carretera 24x24.png"))); // NOI18N
        itemM_modificar_calle.setText("Calle");
        itemM_modificar_calle.setColor1(new java.awt.Color(102, 204, 255));
        itemM_modificar_calle.setColor2(new java.awt.Color(153, 130, 157));
        itemM_modificar_calle.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        itemM_modificar_calle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemM_modificar_calleActionPerformed(evt);
            }
        });
        panelMenup.add(itemM_modificar_calle, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 225, -1, 60));

        itemM_modificar_liderCalle.setForeground(new java.awt.Color(0, 0, 0));
        itemM_modificar_liderCalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/lider 24x24.png"))); // NOI18N
        itemM_modificar_liderCalle.setText("Lider de calle");
        itemM_modificar_liderCalle.setColor1(new java.awt.Color(102, 204, 255));
        itemM_modificar_liderCalle.setColor2(new java.awt.Color(153, 130, 157));
        itemM_modificar_liderCalle.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        itemM_modificar_liderCalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemM_modificar_liderCalleActionPerformed(evt);
            }
        });
        panelMenup.add(itemM_modificar_liderCalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(615, 225, -1, 60));

        itemM_imprimir_censo.setForeground(new java.awt.Color(0, 0, 0));
        itemM_imprimir_censo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/censo 24x24.png"))); // NOI18N
        itemM_imprimir_censo.setText("Censo");
        itemM_imprimir_censo.setColor1(new java.awt.Color(102, 204, 255));
        itemM_imprimir_censo.setColor2(new java.awt.Color(153, 130, 157));
        itemM_imprimir_censo.setFont(new java.awt.Font("Corbel", 1, 16)); // NOI18N
        itemM_imprimir_censo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemM_imprimir_censoActionPerformed(evt);
            }
        });
        panelMenup.add(itemM_imprimir_censo, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 290, -1, 60));

        itemM_imprimir_carta.setForeground(new java.awt.Color(0, 0, 0));
        itemM_imprimir_carta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/carta 24x24.png"))); // NOI18N
        itemM_imprimir_carta.setText("Carta de residencia");
        itemM_imprimir_carta.setToolTipText("");
        itemM_imprimir_carta.setColor1(new java.awt.Color(102, 204, 255));
        itemM_imprimir_carta.setColor2(new java.awt.Color(153, 130, 157));
        itemM_imprimir_carta.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        itemM_imprimir_carta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemM_imprimir_cartaActionPerformed(evt);
            }
        });
        panelMenup.add(itemM_imprimir_carta, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 290, -1, 60));

        itemM_pregunta_respaldarBD.setForeground(new java.awt.Color(0, 0, 0));
        itemM_pregunta_respaldarBD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/export-archive_icon-icons.com_53998.png"))); // NOI18N
        itemM_pregunta_respaldarBD.setText("Respaldar BD");
        itemM_pregunta_respaldarBD.setColor1(new java.awt.Color(102, 204, 255));
        itemM_pregunta_respaldarBD.setColor2(new java.awt.Color(153, 130, 157));
        itemM_pregunta_respaldarBD.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        itemM_pregunta_respaldarBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemM_pregunta_respaldarBDActionPerformed(evt);
            }
        });
        panelMenup.add(itemM_pregunta_respaldarBD, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 355, -1, 60));

        itemM_pregunta_restaurarBD.setForeground(new java.awt.Color(0, 0, 0));
        itemM_pregunta_restaurarBD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/database_import_icon_135719.png"))); // NOI18N
        itemM_pregunta_restaurarBD.setText("Restaurar BD");
        itemM_pregunta_restaurarBD.setColor1(new java.awt.Color(102, 204, 255));
        itemM_pregunta_restaurarBD.setColor2(new java.awt.Color(153, 130, 157));
        itemM_pregunta_restaurarBD.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        itemM_pregunta_restaurarBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemM_pregunta_restaurarBDActionPerformed(evt);
            }
        });
        panelMenup.add(itemM_pregunta_restaurarBD, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 355, -1, 60));

        itemM_pregunta_manual.setForeground(new java.awt.Color(0, 0, 0));
        itemM_pregunta_manual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/manual 24x24.png"))); // NOI18N
        itemM_pregunta_manual.setText("Manual de Uso");
        itemM_pregunta_manual.setColor1(new java.awt.Color(102, 204, 255));
        itemM_pregunta_manual.setColor2(new java.awt.Color(153, 130, 157));
        itemM_pregunta_manual.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        itemM_pregunta_manual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemM_pregunta_manualActionPerformed(evt);
            }
        });
        panelMenup.add(itemM_pregunta_manual, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 355, -1, 60));

        itemM_usuario_add.setForeground(new java.awt.Color(0, 0, 0));
        itemM_usuario_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/agregar-usuario.png"))); // NOI18N
        itemM_usuario_add.setText("Agregar");
        itemM_usuario_add.setColor1(new java.awt.Color(102, 204, 255));
        itemM_usuario_add.setColor2(new java.awt.Color(153, 130, 157));
        itemM_usuario_add.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        itemM_usuario_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemM_usuario_addActionPerformed(evt);
            }
        });
        panelMenup.add(itemM_usuario_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 430, -1, 60));

        itemM_usuario_modificar.setForeground(new java.awt.Color(0, 0, 0));
        itemM_usuario_modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/configuraciones-de-la-cuenta.png"))); // NOI18N
        itemM_usuario_modificar.setText("Modificar");
        itemM_usuario_modificar.setColor1(new java.awt.Color(102, 204, 255));
        itemM_usuario_modificar.setColor2(new java.awt.Color(153, 130, 157));
        itemM_usuario_modificar.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        itemM_usuario_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemM_usuario_modificarActionPerformed(evt);
            }
        });
        panelMenup.add(itemM_usuario_modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 430, -1, 60));

        itemM_usuario_close.setForeground(new java.awt.Color(0, 0, 0));
        itemM_usuario_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cerrar-sesionUsuario32x32.png"))); // NOI18N
        itemM_usuario_close.setText("Cerrar Sesión");
        itemM_usuario_close.setColor1(new java.awt.Color(255, 102, 102));
        itemM_usuario_close.setColor2(new java.awt.Color(153, 130, 157));
        itemM_usuario_close.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        itemM_usuario_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemM_usuario_closeActionPerformed(evt);
            }
        });
        panelMenup.add(itemM_usuario_close, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 430, -1, 60));

        itemM_usuario_exit.setForeground(new java.awt.Color(0, 0, 0));
        itemM_usuario_exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cerrar-sesion(1).png"))); // NOI18N
        itemM_usuario_exit.setText("Salir");
        itemM_usuario_exit.setColor1(new java.awt.Color(255, 102, 102));
        itemM_usuario_exit.setColor2(new java.awt.Color(153, 130, 157));
        itemM_usuario_exit.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        itemM_usuario_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemM_usuario_exitActionPerformed(evt);
            }
        });
        panelMenup.add(itemM_usuario_exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 430, -1, 60));

        fondo_imagen.setBackground(new java.awt.Color(204, 204, 204,0));
        fondo_imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/fondoMenuPrincipal.1.2.png"))); // NOI18N
        panelMenup.add(fondo_imagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 510));

        panel_base.add(panelMenup, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));

        getContentPane().add(panel_base, "card2");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void panel_baseMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_baseMousePressed
        /*xMause = evt.getX();
        yMause = evt.getY();*/
    }//GEN-LAST:event_panel_baseMousePressed

    private void panel_baseMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_baseMouseDragged
        /* x = evt.getXOnScreen();
        y = evt.getYOnScreen();

        setLocation(x - xMause, y - yMause);*/
    }//GEN-LAST:event_panel_baseMouseDragged

    private void SalirStm(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalirStm
        ocultar(panelMenup);
        btSalie.setVisible(false);
        Cldemografia();
        Clregistro();
        CLfiltros();

        cargar_rolesfm();
        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(rolesFm));
        jComboBox7.setSelectedIndex(0);

        persona.ClanAll();
        axdinamicMenu();
        mostrarMenu(true);
        this.setTitle("Registro Comunal");
    }//GEN-LAST:event_SalirStm

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        DprimPanel();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        informacionPersona();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void btSalieMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btSalieMouseEntered
        btSalie.setForeground(new Color(255, 0, 0));
        btSalie.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cerrar-sesion(2).png")));
    }//GEN-LAST:event_btSalieMouseEntered

    private void btSalieMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btSalieMouseExited
        btSalie.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cerrar-sesion(1).png")));
        btSalie.setFont(new java.awt.Font("Roboto", 1, 16));
        btSalie.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_btSalieMouseExited

    private void btSalieMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btSalieMousePressed
        btSalie.setFont(new java.awt.Font("Roboto", 1, 20));
    }//GEN-LAST:event_btSalieMousePressed

    private void btSalieMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btSalieMouseReleased
        btSalie.setFont(new java.awt.Font("Roboto", 1, 16));
    }//GEN-LAST:event_btSalieMouseReleased

    private void jTextField1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField1CaretUpdate
        buscarP(jTextField1.getText());
        jTable1.setModel(modelo);

        for (int i = 0; i < 10; i++) {
            System.out.println(jTable1.getModel().getColumnName(i));
        }
        jTable1.setSelectionMode(1);
        persona.setId(0);

    }//GEN-LAST:event_jTextField1CaretUpdate

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jComboBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox7ActionPerformed

    }//GEN-LAST:event_jComboBox7ActionPerformed

    private void enpNombreMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enpNombreMouseEntered
        DenpNombre(1);
    }//GEN-LAST:event_enpNombreMouseEntered

    private void enpNombreMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enpNombreMouseExited
        DenpNombre(0);
    }//GEN-LAST:event_enpNombreMouseExited

    private void ensNombreMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ensNombreMouseEntered
        DensNombre(1);
    }//GEN-LAST:event_ensNombreMouseEntered

    private void ensNombreMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ensNombreMouseExited
        DensNombre(0);
    }//GEN-LAST:event_ensNombreMouseExited

    private void enpApellidoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enpApellidoMouseEntered
        DenpApellido(1);
    }//GEN-LAST:event_enpApellidoMouseEntered

    private void enpApellidoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enpApellidoMouseExited
        DenpApellido(0);
    }//GEN-LAST:event_enpApellidoMouseExited

    private void ensApellidoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ensApellidoMouseEntered
        DensApellido(1);
    }//GEN-LAST:event_ensApellidoMouseEntered

    private void ensApellidoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ensApellidoMouseExited
        DensApellido(0);
    }//GEN-LAST:event_ensApellidoMouseExited

    private void enCedulaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enCedulaMouseEntered
        DenCedula(1);
    }//GEN-LAST:event_enCedulaMouseEntered

    private void enCedulaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enCedulaMouseExited
        DenCedula(0);
    }//GEN-LAST:event_enCedulaMouseExited

    private void enTelefonoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enTelefonoMouseEntered
        DenTelefono(1);
    }//GEN-LAST:event_enTelefonoMouseEntered

    private void enTelefonoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enTelefonoMouseExited
        DenTelefono(0);
    }//GEN-LAST:event_enTelefonoMouseExited

    private void enCorreoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enCorreoMouseEntered
        DenCorreo(1);
    }//GEN-LAST:event_enCorreoMouseEntered

    private void enCorreoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enCorreoMouseExited
        DenCorreo(0);
    }//GEN-LAST:event_enCorreoMouseExited

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        if (jTextField2.isVisible() == false) {
            relacionesForaneas.setRolFamiliar((String) jComboBox7.getSelectedItem());
            System.out.println(jComboBox7.getSelectedItem());
            cargarRfCbbx((String) jComboBox7.getSelectedItem());
            Clregistro();
            dinamicRegistro(Registro1);
            dinamicDregisro(pr1);
            jLabel55.setVisible(false);
            stadeRegistro++;

        } else {
            int seleccion = JOptionPane.showConfirmDialog(null, "Agregar a " + enpNombre.getText() + " " + enpApellido.getText() + "\nal grupo familiar de " + persona.getpNombre());
            System.out.println(seleccion);
            if (seleccion == 0) {
                relacionesForaneas.setRolFamiliar((String) jComboBox7.getSelectedItem());
                int id = persona.getId();
                persona.buscar(id);
                try {
                    cargarPersona();
                } catch (ParseException ex) {
                    Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
                }
                persona.crearGrupoFamiliar();
                Clregistro();
                stadeRegistro = 0;
                stadeRegistroC = 0;
                ocultar(panelMenup);
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField2CaretUpdate
        cargarTfamilia("Agregar a grupo");
    }//GEN-LAST:event_jTextField2CaretUpdate

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        if (jTextField2.isVisible() == true) {
            int fila = jTable2.getSelectedRow();
            String ax = (String) jTable2.getValueAt(fila, 3);
            persona.setId(Integer.parseInt(ax));
            persona.setpNombre((String) jTable2.getValueAt(jTable2.getSelectedRow(), 1));
            System.out.println(ax);
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void en_nCasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_en_nCasaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_en_nCasaActionPerformed

    private void en_not_nCasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_en_not_nCasaActionPerformed
        if (en_not_nCasa.isSelected() == true) {
            en_nCasa.setText("");
        }
    }//GEN-LAST:event_en_not_nCasaActionPerformed

    private void checkbox_entradaNVedc_eduacionBasicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_entradaNVedc_eduacionBasicaActionPerformed
        grupCheckboxdc(checkbox_entradaNVedc_eduacionBasica);
        // relacionesForaneas.setMgAcademico(label_entradaNVedc_eduacionBasica.getText());
    }//GEN-LAST:event_checkbox_entradaNVedc_eduacionBasicaActionPerformed

    private void jCheckBox10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox10ActionPerformed

    private void checkbox_entradaNVedc_educacionInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_entradaNVedc_educacionInicialActionPerformed
        grupCheckboxdc(checkbox_entradaNVedc_educacionInicial);
        //relacionesForaneas.setMgAcademico(label_entradaNVedc_educacionInicial.getText());
    }//GEN-LAST:event_checkbox_entradaNVedc_educacionInicialActionPerformed

    private void checkbox_entradaNVedc_educacionMediaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_entradaNVedc_educacionMediaActionPerformed
        grupCheckboxdc(checkbox_entradaNVedc_educacionMedia);
        //relacionesForaneas.setMgAcademico(label_entradaNVedc_educacionMedia.getText());
    }//GEN-LAST:event_checkbox_entradaNVedc_educacionMediaActionPerformed

    private void checkbox_entradaNVedc_eduacionSuperiorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_entradaNVedc_eduacionSuperiorActionPerformed
        grupCheckboxdc(checkbox_entradaNVedc_eduacionSuperior);
        // relacionesForaneas.setMgAcademico(label_entradaNVedc_educacionSuperior.getText());
    }//GEN-LAST:event_checkbox_entradaNVedc_eduacionSuperiorActionPerformed

    private void jTextField2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField2MouseEntered
        if (jTextField2.getText().equals("Buscar...")) {
            jTextField2.setText("");
        }
        jSeparator19.setVisible(true);
    }//GEN-LAST:event_jTextField2MouseEntered

    private void jTextField2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField2MouseExited
        if (jTextField2.getText().equals("")) {
            jTextField2.setText("Buscar...");
            jSeparator19.setVisible(false);
        }
    }//GEN-LAST:event_jTextField2MouseExited

    private void jLabel49MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel49MouseClicked
        dinamicRegistro(Registro1);
        dinamicDregisro(pr1);
    }//GEN-LAST:event_jLabel49MouseClicked

    private void jLabel50MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel50MouseClicked
        if (checkbox_entradaNVedc_sinEducacion.isSelected() == true || checkbox_entradaNVedc_educacionInicial.isSelected() == true || checkbox_entradaNVedc_eduacionBasica.isSelected() == true || checkbox_entradaNVedc_educacionMedia.isSelected() == true || checkbox_entradaNVedc_eduacionSuperior.isSelected() == true) {
            dinamicRegistro(Registro3);
            dinamicDregisro(pr3);
        } else {
            JOptionPane.showMessageDialog(null, "Primero debe de seleccionar una opcion");
        }
    }//GEN-LAST:event_jLabel50MouseClicked

    private void jLabel51MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel51MouseClicked
        dinamicRegistro(Registro2);
        dinamicDregisro(pr2);
    }//GEN-LAST:event_jLabel51MouseClicked

    private void jLabel53MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel53MouseClicked
        try {
            switch (tipoDeRegistro) {
                case "nuevo":
                    if (stadeRegistro > 0) {
                        cargarPersona();
                        cargarTfamilia("Nuevo grupo");
                        dinamicRegistro(Registro4);
                        dinamicDregisro(pr4);
                    } else {
                        dinamicRegistro(Registro3o4);
                        //dinamicDregisro(pr2);
                    }

                    break;
                case "modificar":
                    cargarPersona();
                    persona.modificarP();
                    Clregistro();
                    ocultar(panelBarra);
                    break;
            }
        } catch (ParseException ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_jLabel53MouseClicked

    private void jLabel54MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel54MouseClicked
        //try {
        dinamicRegistro(Registro5);
        dinamicDregisro(pr5);
        //cargarPersona();
        //persona.addFamilia();
        /* } catch (ParseException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_jLabel54MouseClicked

    private void jLabel55MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel55MouseClicked
        dinamicRegistro(Registro3o4);
        poliFamly("CL");
        //dinamicDregisro(pr3);
    }//GEN-LAST:event_jLabel55MouseClicked

    private void jLabel56MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel56MouseClicked
        dinamicRegistro(Registro4);
        dinamicDregisro(pr4);
    }//GEN-LAST:event_jLabel56MouseClicked

    private void jLabel57MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel57MouseClicked

        try {
            if (en_combo_direccion.getSelectedItem().equals("Direccion")) {
                throw new IllegalAccessException("Selecciona la direccion de la casa");
            }
            if (en_combo_stdCasa.getSelectedItem().equals("Estado de la Casa")) {
                throw new IllegalAccessException("Debes de seleccionar el estado de la casa");
            }
            cargarHouse();
            modelos.house.newHose();
            persona.crearGrupoFamiliar();
            modelos.house.ClanAll();
            modelos.persona.ClanAll();
            Clregistro();
            cargarRfCbbx("");
            jComboBox7.setSelectedIndex(0);
            ocultar(panelMenup);
            stadeRegistro = 0;
        } catch (IllegalAccessException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }


    }//GEN-LAST:event_jLabel57MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        modelos.demografia.setStrike((String) combo_demografia_asigLider.getSelectedItem());
        modelos.demografia.setIdStrike(modelos.demografia.idStrike());
        int aux = JOptionPane.showConfirmDialog(null, "Añadir a " + modelos.demografia.getNombreL() + "\n como lider de calle da la calle: " + modelos.demografia.getStrike());
        if (aux == 0) {
            modelos.demografia.addLiderStk();
            ocultar(panelMenup);
        }

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextField10CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField10CaretUpdate
        ArrayList<String[]> lista = persona.RecuperarAll(jTextField10.getText());
        DefaultTableModel modelo = new DefaultTableModel();
        String[] colums = {"Nombre y apellido               ", "Cedula", "cod"};
        modelo.setColumnIdentifiers(colums);

        for (String[] aux : lista) {
            String[] registro = new String[3];
            registro[0] = aux[1] + " " + aux[2] + " " + aux[3] + " " + aux[4];
            registro[1] = aux[5];
            registro[2] = aux[0];
            modelo.addRow(registro);
        }
        jTable5.setModel(modelo);
    }//GEN-LAST:event_jTextField10CaretUpdate

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
        modelos.demografia.setIdLider(Integer.parseInt((String) jTable5.getValueAt(jTable5.getSelectedRow(), 2)));
        modelos.demografia.setNombreL((String) jTable5.getValueAt(jTable5.getSelectedRow(), 0));
        System.out.println(modelos.demografia.getIdLider());
    }//GEN-LAST:event_jTable5MouseClicked

    private void panelBarraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBarraMousePressed
        xMause = evt.getX();
        yMause = evt.getY();
    }//GEN-LAST:event_panelBarraMousePressed

    private void panelBarraMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBarraMouseDragged
        x = evt.getXOnScreen();
        y = evt.getYOnScreen();

        setLocation(x - xMause, y - yMause);
    }//GEN-LAST:event_panelBarraMouseDragged

    private void jTextField10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField10MouseEntered
        jSeparator2.setBackground(new Color(0, 0, 250));
    }//GEN-LAST:event_jTextField10MouseEntered

    private void jTextField10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField10MouseExited
        jSeparator2.setBackground(new Color(153, 153, 153));
    }//GEN-LAST:event_jTextField10MouseExited

    private void jTextField1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseEntered
        jSeparator9.setBackground(new java.awt.Color(0, 0, 250));
    }//GEN-LAST:event_jTextField1MouseEntered

    private void jTextField1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseExited
        jSeparator9.setBackground(new java.awt.Color(153, 153, 153));
    }//GEN-LAST:event_jTextField1MouseExited

    private void jTextField3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField3CaretUpdate
        filtroEdad();
    }//GEN-LAST:event_jTextField3CaretUpdate

    private void jTextField3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField3MouseEntered
        jSeparator10.setBackground(new Color(0, 0, 250));
    }//GEN-LAST:event_jTextField3MouseEntered

    private void jTextField3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField3MouseExited
        jSeparator10.setBackground(new Color(153, 153, 153));
    }//GEN-LAST:event_jTextField3MouseExited

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        informacionPersona();
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jTextField4CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField4CaretUpdate
        filtroUbicacion((String) jComboBox1.getSelectedItem());
    }//GEN-LAST:event_jTextField4CaretUpdate

    private void jTextField4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4MouseEntered

    private void jTextField4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4MouseExited

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        informacionPersona();
    }//GEN-LAST:event_jLabel18MouseClicked

    private void jTextField5CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField5CaretUpdate
        filtroNvEdc((String) jComboBox2.getSelectedItem());
    }//GEN-LAST:event_jTextField5CaretUpdate

    private void jTextField5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField5MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5MouseEntered

    private void jTextField5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField5MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5MouseExited

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        /*jLayeredPane7.setVisible(true);
        jLayeredPane6.setVisible(false);
        
        jPanel18.setVisible(true);
        jPanel14.setVisible(true);
        jPanel16.setVisible(true);
        jPanel19.setVisible(true);
        jPanel20.setVisible(true);
        jPanel21.setVisible(true);
        jPanel22.setVisible(true);
        jPanel23.setVisible(true);
        jPanel24.setVisible(true);     
        
        jPanel17.setVisible(false);*/
        DprimPanel();
    }//GEN-LAST:event_jLabel21MouseClicked

    private void jLabel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseClicked
        informacionPersona();
    }//GEN-LAST:event_jLabel22MouseClicked

    private void jTextField8CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField8CaretUpdate
        modelos.filtros.setBuscar(jTextField8.getText());
        filtroPro();
    }//GEN-LAST:event_jTextField8CaretUpdate

    private void jTextField8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField8MouseEntered
        jSeparator13.setBackground(new Color(0, 0, 250));
    }//GEN-LAST:event_jTextField8MouseEntered

    private void jTextField8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField8MouseExited
        if (jTextField8.getText().trim().equals("") == true) {
            jSeparator13.setBackground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_jTextField8MouseExited

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField11ActionPerformed

    private void jLabel78MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel78MouseClicked
        informacionPersona();
    }//GEN-LAST:event_jLabel78MouseClicked

    private void jTextField9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField9MouseEntered
        jSeparator15.setBackground(new Color(0, 0, 250));
    }//GEN-LAST:event_jTextField9MouseEntered

    private void jTextField9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField9MouseExited
        jSeparator15.setBackground(new Color(153, 153, 153));
    }//GEN-LAST:event_jTextField9MouseExited

    private void jTextField11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField11MouseEntered
        jSeparator14.setBackground(new Color(0, 0, 250));
    }//GEN-LAST:event_jTextField11MouseEntered

    private void jTextField11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField11MouseExited
        jSeparator14.setBackground(new Color(153, 153, 153));
    }//GEN-LAST:event_jTextField11MouseExited

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dinamic_filtroPro(jLayeredPane6);
        filtroPro();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField12MouseEntered
        jSeparator16.setBackground(new Color(0, 0, 250));
    }//GEN-LAST:event_jTextField12MouseEntered

    private void jTextField12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField12MouseExited
        jSeparator16.setBackground(new Color(153, 153, 153));
    }//GEN-LAST:event_jTextField12MouseExited

    private void jTextField13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField13MouseEntered
        jSeparator17.setBackground(new Color(0, 0, 250));
    }//GEN-LAST:event_jTextField13MouseEntered

    private void jTextField13MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField13MouseExited
        jSeparator17.setBackground(new Color(153, 153, 153));
    }//GEN-LAST:event_jTextField13MouseExited

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        try {
            modelos.filtros.setBuscar(jTextField3.getText());
            modelo = new DefaultTableModel();
            String[] colums = {"Nombre y Apellido", "Cedula", "Edad"};
            modelo.setColumnIdentifiers(colums);
            ArrayList<String[]> lista = modelos.filtros.edad(Integer.parseInt(jTextField9.getText()), Integer.parseInt(jTextField11.getText()));

            for (String[] aux : lista) {
                String[] registro = {aux[1] + " " + aux[2] + " " + aux[3] + " " + aux[4], aux[5], aux[7]};

                modelo.addRow(registro);
            }
            jTable6.setModel(modelo);
        } catch (ParseException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jTextField9CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField9CaretUpdate
        filtroEdad();
    }//GEN-LAST:event_jTextField9CaretUpdate

    private void jTextField11CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField11CaretUpdate
        filtroEdad();
    }//GEN-LAST:event_jTextField11CaretUpdate

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        filtroUbicacion((String) jComboBox1.getSelectedItem());
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        /*persona.setDireccion((String)jComboBox1.getSelectedItem());
        ArrayList<String[]> lista = filtros.ubicacion();
        String[] colums = {"Nombre y Apellido","Cedula","adfasd"};
        modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(colums);
        
        for(String[] aux : lista){
            
            modelo.addRow(aux);
        }
        jTable7.setModel(modelo);*/
    }//GEN-LAST:event_jLabel12MouseClicked

    private void en_nCasaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_en_nCasaCaretUpdate
        if (en_nCasa.getText().trim().isEmpty()) {
            en_not_nCasa.setSelected(true);
        } else {
            en_not_nCasa.setSelected(false);
        }

    }//GEN-LAST:event_en_nCasaCaretUpdate

    private void en_nCasaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_en_nCasaMouseEntered
        jSeparator18.setBackground(new Color(0, 0, 250));
    }//GEN-LAST:event_en_nCasaMouseEntered

    private void en_nCasaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_en_nCasaMouseExited
        if (en_nCasa.getText().trim().equals("")) {
            jSeparator18.setBackground(new Color(153, 153, 153));
            en_not_nCasa.setSelected(true);
        }
    }//GEN-LAST:event_en_nCasaMouseExited

    private void jLabel94MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel94MouseClicked
        dinamicRegistro(Registro3);
        dinamicDregisro(pr3);
    }//GEN-LAST:event_jLabel94MouseClicked

    private void enpNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_enpNombreKeyTyped
        char a = evt.getKeyChar();
        if (a == ' ') {
            evt.consume();
        }
        if (!Character.isAlphabetic(a)) {
            evt.consume();
        }
        if (enpNombre.getText().length() >= 15) {
            evt.consume();
        }
    }//GEN-LAST:event_enpNombreKeyTyped

    private void ensNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ensNombreKeyTyped
        char a = evt.getKeyChar();
        if (a == ' ') {
            evt.consume();
        }
        if (!Character.isAlphabetic(a)) {
            evt.consume();
        }
        if (ensNombre.getText().length() >= 15) {
            evt.consume();
        }
    }//GEN-LAST:event_ensNombreKeyTyped

    private void enpApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_enpApellidoKeyTyped
        char a = evt.getKeyChar();
        if (a == ' ') {
            evt.consume();
        }
        if (!Character.isAlphabetic(a)) {
            evt.consume();
        }
        if (enpApellido.getText().length() >= 15) {
            evt.consume();
        }

    }//GEN-LAST:event_enpApellidoKeyTyped

    private void ensApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ensApellidoKeyTyped
        char a = evt.getKeyChar();
        if (a == ' ') {
            evt.consume();
        }
        if (!Character.isAlphabetic(a)) {
            evt.consume();
        }
        if (ensApellido.getText().length() >= 15) {
            evt.consume();
        }
    }//GEN-LAST:event_ensApellidoKeyTyped

    private void enCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_enCedulaKeyTyped
        char a = evt.getKeyChar();
        if (a == ' ') {
            evt.consume();
        }
        if (!Character.isDigit(a)) {
            evt.consume();
        }
        if (enCedula.getText().length() >= 8) {
            evt.consume();
        }
    }//GEN-LAST:event_enCedulaKeyTyped

    private void enTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_enTelefonoKeyTyped
        char a = evt.getKeyChar();
        if (a == ' ') {
            evt.consume();
        }
        if (!Character.isDigit(a)) {
            evt.consume();
        }
        if (enTelefono.getText().length() > 11) {
            evt.consume();
        }
    }//GEN-LAST:event_enTelefonoKeyTyped

    private void enCorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_enCorreoKeyTyped
        char a = evt.getKeyChar();
        if (a == ' ') {
            evt.consume();
        }
    }//GEN-LAST:event_enCorreoKeyTyped

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        filtroNvEdc((String) jComboBox2.getSelectedItem());
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void enpNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_enpNombreKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            DensNombre(1);
        }
    }//GEN-LAST:event_enpNombreKeyPressed

    private void ensNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ensNombreKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            DenpApellido(1);
            enpApellido.requestFocusInWindow();
        }
    }//GEN-LAST:event_ensNombreKeyPressed

    private void enpApellidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_enpApellidoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            DensApellido(1);
            ensApellido.requestFocusInWindow();
        }
    }//GEN-LAST:event_enpApellidoKeyPressed

    private void ensApellidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ensApellidoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            DenCedula(1);
            enCedula.requestFocusInWindow();
        }
    }//GEN-LAST:event_ensApellidoKeyPressed

    private void enTelefonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_enTelefonoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            DenCorreo(1);
            enCorreo.requestFocusInWindow();
        }
    }//GEN-LAST:event_enTelefonoKeyPressed

    private void enCorreoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_enCorreoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            dinamicRegistro(Registro2);
            dinamicDregisro(pr2);
        }
    }//GEN-LAST:event_enCorreoKeyPressed

    private void jTable6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable6MouseClicked

    }//GEN-LAST:event_jTable6MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        if (persona.getId() != 0) {
            montarPersona();
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un persona primero");
        }
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

    }//GEN-LAST:event_jTable1MouseClicked

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        if (persona.getId() != 0) {
            montarPersona();
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un persona primero");
        }
    }//GEN-LAST:event_jLabel19MouseClicked

    private void jLabel79MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel79MouseClicked
        if (persona.getId() != 0) {
            montarPersona();
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un persona primero");
        }
    }//GEN-LAST:event_jLabel79MouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        if (persona.getId() != 0) {
            montarPersona();
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un persona primero");
        }
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jLabel106MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel106MouseClicked
        DprimPanel();
    }//GEN-LAST:event_jLabel106MouseClicked

    private void jLabel109MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel109MouseClicked
        menu_impressD.setVisible(false);
        menu_impress.setVisible(false);
        File file = showFileChooser();
        if (file != null) {
            try {
                File f = new PrintTable().printTable(modelo, "Reporte Consejo Comunal 'La Union'", pageFormat).exportToExcel(file);
                Desktop.getDesktop().open(f);
            } catch (IOException | DRException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jLabel109MouseClicked

    private void jLabel107MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel107MouseClicked
        menu_impressD.setVisible(false);
        menu_impress.setVisible(false);
        File file = showFileChooser();
        if (file != null) {
            try {
                File f = new PrintTable().printTable(modelo, "Test Reportddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd", pageFormat).exportToWord(file);
                Desktop.getDesktop().open(f);
            } catch (IOException | DRException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jLabel107MouseClicked

    private void jLabel111MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel111MouseClicked
        DprimPanel();
    }//GEN-LAST:event_jLabel111MouseClicked

    private void jLabel112MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel112MouseClicked
        DprimPanel();
    }//GEN-LAST:event_jLabel112MouseClicked

    private void jLabel110MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel110MouseClicked
        menu_impressD.setVisible(false);
        menu_impress.setVisible(false);
        File file = showFileChooser();
        if (file != null) {
            try {
                File f = new PrintTable().printTable(modelo, "Test Report", pageFormat).exportToPdf(file);
                Desktop.getDesktop().open(f);
            } catch (IOException | DRException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jLabel110MouseClicked

    private void jLabel108MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel108MouseClicked
        menu_impressD.setVisible(false);
        menu_impress.setVisible(false);
        File file = showFileChooser();
        if (file != null) {
            try {
                File f = new PrintTable().printTable(modelo, "Hola mundo", pageFormat).exportToImage(file);
                Desktop.getDesktop().open(f);
            } catch (IOException | DRException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jLabel108MouseClicked

    private void jLabel108MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel108MouseEntered
        jLabel108.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ImprimirPdf.png")));
    }//GEN-LAST:event_jLabel108MouseEntered

    private void jLabel108MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel108MouseExited
        jLabel108.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ImprimirImagen 24 x 24.png")));
    }//GEN-LAST:event_jLabel108MouseExited

    private void jLabel117MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel117MouseClicked
        anim.animate.animarL(jLabel118, jLabel118.isVisible(), jLabel118.getForeground(), 255);
    }//GEN-LAST:event_jLabel117MouseClicked

    private void jLabel120MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel120MouseClicked
        anim.animate.animarL(jLabel119, jLabel119.isVisible(), jLabel119.getForeground(), 255);
    }//GEN-LAST:event_jLabel120MouseClicked

    private void jLabel122MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel122MouseClicked
        anim.animate.animarL(jLabel121, jLabel121.isVisible(), jLabel121.getForeground(), 255);
    }//GEN-LAST:event_jLabel122MouseClicked

    private void jLabel124MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel124MouseClicked
        anim.animate.animarL(jLabel123, jLabel123.isVisible(), new Color(255, 51, 51), 255);
    }//GEN-LAST:event_jLabel124MouseClicked

    private void jLabel126MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel126MouseClicked
        anim.animate.animarL(jLabel119, jLabel119.isVisible(), jLabel119.getForeground(), 255);
        if (jLabel125.isVisible()) {
            jLabel125.setVisible(false);
        } else {
            jLabel125.setVisible(true);
        }
    }//GEN-LAST:event_jLabel126MouseClicked

    private void jTable8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable8MouseClicked
        persona.setId(Integer.valueOf((String) jTable8.getValueAt(jTable8.getSelectedRow(), 0)));
    }//GEN-LAST:event_jTable8MouseClicked

    private void jLabel119MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel119MouseClicked
        deleteP();
    }//GEN-LAST:event_jLabel119MouseClicked

    private void bt_dmg_estrucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_dmg_estrucMouseClicked
        if (bt_dmg_addStrike.getForeground().equals(disponible) == true && bt_dmg_estruc.getForeground().equals(disponible) == true && bt_dmg_addLider.getForeground().equals(nulo) == true && bt_dmg_stingStrike.getForeground().equals(disponible) == true) {

            dinamic_bt_demografia(bt_dmg_estruc);
        }
    }//GEN-LAST:event_bt_dmg_estrucMouseClicked

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked


    }//GEN-LAST:event_jTable3MouseClicked

    private void bt_dmg_addLiderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_dmg_addLiderMouseClicked
        if (bt_dmg_addLider.getForeground().equals(disponible) == true) {
            Integer resultado = demografia.idStrike();
            if (resultado == null) {
                resultado = 0;
            }
            if (resultado != 0) {
                dinamic_bt_demografia(null);
                combo_demografia_asigLider.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{demografia.getStrike()}));
                dinamicDemografia(panel_dmg_lider);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione una calle");
            }
        }
    }//GEN-LAST:event_bt_dmg_addLiderMouseClicked

    private void entradaTxt_demografiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entradaTxt_demografiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_entradaTxt_demografiaActionPerformed

    private void bt_dmg_descartarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_dmg_descartarMouseClicked
        cargarCalles();
        demografia.clean();
        dinamic_bt_demografia(null);
    }//GEN-LAST:event_bt_dmg_descartarMouseClicked

    private void bt_dmg_addStrikeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_dmg_addStrikeMouseClicked
        if (bt_dmg_addStrike.getForeground().equals(disponible) == true && bt_dmg_estruc.getForeground().equals(disponible) == true && bt_dmg_addLider.getForeground().equals(nulo) == true && bt_dmg_stingStrike.getForeground().equals(disponible) == true) {
            cargarCalles();
            dinamic_bt_demografia(bt_dmg_addStrike);
            System.out.println("se esta ejecutando");
        }
    }//GEN-LAST:event_bt_dmg_addStrikeMouseClicked

    private void bt_dmg_guardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_dmg_guardarMouseClicked
        if (bt_dmg_addStrike.getForeground().equals(seleccionado)) {
            demografia.setStrike(entradaTxt_demografia.getText().trim());
            if (entradaTxt_demografia.getText().trim().isEmpty() == false) {
                if (demografia.estStrike(demografia.getStrike()) == false) {
                    demografia.addStrike();
                    cargarCalles();
                    JOptionPane.showMessageDialog(null, "Calle agregada correctamente");
                    dinamic_bt_demografia(null);
                } else {
                    JOptionPane.showMessageDialog(null, "Ya hay una calle con ese mismo nombre");
                }
            } else {
                JOptionPane.showMessageDialog(null, "El nombre esta vacio debes de poner el nombre de la calle");
            }
        }
        if (bt_dmg_stingStrike.getForeground().equals(seleccionado) == true) {
            if (demografia.idStrike() != null && demografia.idStrike() != 0) {
                demografia.modStrike(demografia.idStrike(), entradaTxt_demografia.getText());
                cargarCalles();
                dinamic_bt_demografia(null);
            } else {
                JOptionPane.showMessageDialog(null, "No se puede modificar calle!! \nRecuerda que primero tienes que seleccionar una calle");
            }

        }
    }//GEN-LAST:event_bt_dmg_guardarMouseClicked

    private void jLabel118MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel118MouseClicked
        deleteP();
    }//GEN-LAST:event_jLabel118MouseClicked

    private void jLabel125MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel125MouseClicked
        deleteP();
    }//GEN-LAST:event_jLabel125MouseClicked

    private void jLabel121MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel121MouseClicked
        deleteP();
    }//GEN-LAST:event_jLabel121MouseClicked

    private void jLabel123MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel123MouseClicked
        deleteP();
    }//GEN-LAST:event_jLabel123MouseClicked

    private void jTable9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable9MouseClicked

    }//GEN-LAST:event_jTable9MouseClicked

    private void jTable7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable7MouseClicked

    }//GEN-LAST:event_jTable7MouseClicked

    private void entradaTxt_demografiaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_entradaTxt_demografiaMouseEntered
        if (entradaTxt_demografia.isEditable()) {
            jSeparator20.setBackground(new Color(0, 0, 250));
        }
    }//GEN-LAST:event_entradaTxt_demografiaMouseEntered

    private void entradaTxt_demografiaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_entradaTxt_demografiaMouseExited
        if (entradaTxt_demografia.isEditable() && entradaTxt_demografia.getText().equals("") == false) {
            jSeparator20.setBackground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_entradaTxt_demografiaMouseExited

    private void enpNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_enpNombreKeyReleased
        if (enpNombre.getText().isEmpty() == false) {
            enpNombre.setText(enpNombre.getText().substring(0, 1).toUpperCase() + enpNombre.getText().substring(1));
        }
    }//GEN-LAST:event_enpNombreKeyReleased

    private void ensNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ensNombreKeyReleased
        if (ensNombre.getText().isEmpty() == false) {
            ensNombre.setText(ensNombre.getText().substring(0, 1).toUpperCase() + ensNombre.getText().substring(1));
        }
    }//GEN-LAST:event_ensNombreKeyReleased

    private void enpApellidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_enpApellidoKeyReleased
        if (enpApellido.getText().isEmpty() == false) {
            enpApellido.setText(enpApellido.getText().substring(0, 1).toUpperCase() + enpApellido.getText().substring(1));
        }
    }//GEN-LAST:event_enpApellidoKeyReleased

    private void ensApellidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ensApellidoKeyReleased
        if (ensApellido.getText().isEmpty() == false) {
            ensApellido.setText(ensApellido.getText().substring(0, 1).toUpperCase() + ensApellido.getText().substring(1));
        }
    }//GEN-LAST:event_ensApellidoKeyReleased

    private void enCedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_enCedulaKeyReleased
        if (enCedula.getText().isEmpty() == false) {
            int cedula;
            if (enCedula.getText().equals(null) || enCedula.getText().equals("Cedula de Identidad") || enCedula.getText().equals("")) {
                cedula = 0;
            } else {
                cedula = Integer.parseInt(enCedula.getText());
            }
            switch (tipoDeRegistro) {
                case "nuevo":
                    if (persona.exisPer(cedula) && cedula != 0) {
                        etick_cedula.setText("cedula ya registrada!");
                        etick_cedula.setForeground(new Color(250, 20, 20));
                    } else {
                        etick_cedula.setText("cedula de identidad");
                        etick_cedula.setForeground(new Color(0, 0, 0));
                    }
                    break;
                case "modificar":
                    if (persona.exisPer(cedula) && cedula != 0 && persona.getCedula() != cedula) {
                        etick_cedula.setText("cedula ya registrada!");
                        etick_cedula.setForeground(new Color(250, 20, 20));
                    } else {
                        etick_cedula.setText("cedula de identidad");
                        etick_cedula.setForeground(new Color(0, 0, 0));
                    }

                    break;
            }
//            if (condicion == 0) {
//                if (persona.exisPer(cedula)) {
//
//                    if (cedula != 0) {
//                        jLabel99.setText("cedula ya registrada!");
//                        jLabel99.setForeground(new Color(250, 20, 20));
//                    } else {
//                        jLabel99.setText("cedula de identidad");
//                        jLabel99.setForeground(new Color(0, 0, 0));
//                    }
//                } else {
//                    jLabel99.setText("cedula de identidad");
//                    jLabel99.setForeground(new Color(0, 0, 0));
//                }
//
//            } else {
//                if (persona.exisCedulaPer(cedula, persona.getId())) {
//
//                    if (cedula != 0) {
//                        jLabel99.setText("cedula ya registrada!");
//                        jLabel99.setForeground(new Color(250, 20, 20));
//                    } else {
//                        jLabel99.setText("cedula de identidad");
//                        jLabel99.setForeground(new Color(0, 0, 0));
//                    }
//                } else {
//                    jLabel99.setText("cedula de identidad");
//                    jLabel99.setForeground(new Color(0, 0, 0));
//                }
//            }
        }
    }//GEN-LAST:event_enCedulaKeyReleased

    private void bt_dmg_stingStrikeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_dmg_stingStrikeMouseClicked
        if (bt_dmg_addStrike.getForeground().equals(disponible) == true && bt_dmg_estruc.getForeground().equals(disponible) == true && bt_dmg_addLider.getForeground().equals(nulo) == true && bt_dmg_stingStrike.getForeground().equals(disponible) == true) {
            cargarCalles();
            dinamic_bt_demografia(bt_dmg_stingStrike);
            // entradaTxt_demografia.setEditable(true);

        }
    }//GEN-LAST:event_bt_dmg_stingStrikeMouseClicked

    private void jLabel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MouseClicked
        if (persona.getId() != 0) {
            montarPersona();
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un persona primero");
        }
    }//GEN-LAST:event_jLabel23MouseClicked

    private void bt_fechaN_registro1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_fechaN_registro1ActionPerformed
        dateChooser1.showPopup();
    }//GEN-LAST:event_bt_fechaN_registro1ActionPerformed

    private void jTextField9KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyTyped
        Character a = evt.getKeyChar();

        if (!Character.isDigit(a)) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField9KeyTyped

    private void jTextField11KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField11KeyTyped
        Character a = evt.getKeyChar();

        if (!Character.isDigit(a)) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField11KeyTyped

    private void jLayeredPane8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLayeredPane8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLayeredPane8MouseClicked

    private void jLayeredPane8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLayeredPane8MousePressed
        jTable1.setSelectionMode(1);
        persona.setId(0);
    }//GEN-LAST:event_jLayeredPane8MousePressed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        persona.setId(Integer.valueOf((String) jTable1.getValueAt(jTable1.getSelectedRow(), 0)));
    }//GEN-LAST:event_jTable1MousePressed

    private void bt_menu_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_menu_agregarActionPerformed
        usuario.setUsuarioo(bt_menu_usuario.getText());
        if (usuario.comprobarTipoUser("Administrador")) {
            itemsMenuDesplegar("Agregar");
        } else {
            JOptionPane.showMessageDialog(null, "Esta opcion es solo para los usuarios,\"Administradores\"");
        }
    }//GEN-LAST:event_bt_menu_agregarActionPerformed

    private void bt_menu_demografiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_menu_demografiaActionPerformed
        itemsMenuDesplegar("Demografia");
    }//GEN-LAST:event_bt_menu_demografiaActionPerformed

    private void bt_mn_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_mn_buscarActionPerformed
        itemsMenuDesplegar("Filtrar");
    }//GEN-LAST:event_bt_mn_buscarActionPerformed

    private void bt_menu_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_menu_modificarActionPerformed
        usuario.setUsuarioo(bt_menu_usuario.getText());
        if (usuario.comprobarTipoUser("Administrador")) {
            itemsMenuDesplegar("Modificar");
        } else {
            JOptionPane.showMessageDialog(null, "Esta opcion es solo para los usuarios,\"Administradores\"");
        }
    }//GEN-LAST:event_bt_menu_modificarActionPerformed

    private void bt_menu_imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_menu_imprimirActionPerformed
        itemsMenuDesplegar("Imprimir");
    }//GEN-LAST:event_bt_menu_imprimirActionPerformed

    private void bt_menu_gestionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_menu_gestionarActionPerformed
        itemsMenuDesplegar("Gestionar");
    }//GEN-LAST:event_bt_menu_gestionarActionPerformed

    private void bt_menu_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_menu_usuarioActionPerformed
        itemsMenuDesplegar("Usuario");
    }//GEN-LAST:event_bt_menu_usuarioActionPerformed

    private void itemM_agregar_personaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemM_agregar_personaActionPerformed
        axdinamicMenu();
        tipoDeRegistro = "nuevo";
        condicion = 0;
        ocultar(panel_registrar);
        dinamicRegistro(Registro1);
        this.setTitle("Registrar Persona");
    }//GEN-LAST:event_itemM_agregar_personaActionPerformed

    private void itemM_agregar_cargaFamiliarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemM_agregar_cargaFamiliarActionPerformed
        axdinamicMenu();
        tipoDeRegistro = "carga";
        ocultar(panel_registrar);
        dinamicRegistro(Registro1);
        //tadeRegistro++
        this.setTitle("Carga Familiar");
    }//GEN-LAST:event_itemM_agregar_cargaFamiliarActionPerformed

    private void itemM_agregar_LiderCalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemM_agregar_LiderCalleActionPerformed
        axdinamicMenu();
        ocultar(panel_demografia);
        dinamicDemografia(panel_dmg_strike);
        dinamic_bt_demografia(bt_dmg_estruc);
        this.setTitle("Estructura comunal");
    }//GEN-LAST:event_itemM_agregar_LiderCalleActionPerformed

    private void itemM_demografia_addCalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemM_demografia_addCalleActionPerformed
        axdinamicMenu();
        usuario.setUsuarioo(bt_menu_usuario.getText());
        if (usuario.comprobarTipoUser("Administrador")) {
            ocultar(panel_demografia);
            dinamicDemografia(panel_dmg_strike);
            this.setTitle("Modificar Calle");
        } else {
            JOptionPane.showMessageDialog(null, "Esta opcion es solo para los usuarios,\"Administradores\"");
        }
    }//GEN-LAST:event_itemM_demografia_addCalleActionPerformed

    private void itemM_demografia_infComunalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemM_demografia_infComunalActionPerformed
        infoPersona.setVisible(true);
        infoPersona.cDetalles();
        axdinamicMenu();
    }//GEN-LAST:event_itemM_demografia_infComunalActionPerformed

    private void itemM_filtro_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemM_filtro_buscarActionPerformed
        axdinamicMenu();
        ocultar(panel_filtro);
        buscarP("");
        dinamicFiltro(p_filtroGeneral);
        this.setTitle("Buscar persona");
    }//GEN-LAST:event_itemM_filtro_buscarActionPerformed

    private void itemM_filtro_ubicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemM_filtro_ubicacionActionPerformed
        axdinamicMenu();
        ocultar(panel_filtro);
        buscarP("");
        jTable7.setModel(modelo);
        dinamicFiltro(p_filtroDirecc);
        this.setTitle("Buscar persona");
    }//GEN-LAST:event_itemM_filtro_ubicacionActionPerformed

    private void itemM_filtro_edadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemM_filtro_edadActionPerformed
        axdinamicMenu();
        ocultar(panel_filtro);
        buscarP("");
        jTable6.setModel(modelo);
        dinamicFiltro(p_filtroEdad);
        this.setTitle("Buscar persona");
    }//GEN-LAST:event_itemM_filtro_edadActionPerformed

    private void itemM_filtro_nvlEdcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemM_filtro_nvlEdcActionPerformed
        axdinamicMenu();
        ocultar(panel_filtro);
        buscarP("");
        jTable8.setModel(modelo);
        dinamicFiltro(p_filtroNEdc);
        this.setTitle("Buscar persona");
    }//GEN-LAST:event_itemM_filtro_nvlEdcActionPerformed

    private void itemM_filtro_otroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemM_filtro_otroActionPerformed
        axdinamicMenu();
        ocultar(panel_filtro);
        dinamicFiltro(p_filtroPro);

        jLayeredPane7.setVisible(true);
        jLayeredPane6.setVisible(false);

        jPanel18.setVisible(true);
        jPanel14.setVisible(true);
        jPanel16.setVisible(true);
        jPanel19.setVisible(true);
        jPanel20.setVisible(true);
        jPanel21.setVisible(true);
        jPanel22.setVisible(true);
        jPanel23.setVisible(true);
        jPanel24.setVisible(true);

        jPanel17.setVisible(false);
        this.setTitle("Buscar persona");
    }//GEN-LAST:event_itemM_filtro_otroActionPerformed

    private void itemM_modificar_personaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemM_modificar_personaActionPerformed
        axdinamicMenu();
        ocultar(panel_filtro);
        buscarP("");
        dinamicFiltro(p_filtroGeneral);
        this.setTitle("Buscar persona");
    }//GEN-LAST:event_itemM_modificar_personaActionPerformed

    private void itemM_modificar_familiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemM_modificar_familiaActionPerformed
        modificar.setVisible(true);
        modificar.setState(JFrame.NORMAL);
        modificar.setTitle("Modificar familia");
    }//GEN-LAST:event_itemM_modificar_familiaActionPerformed

    private void itemM_modificar_calleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemM_modificar_calleActionPerformed
        ocultar(panel_demografia);
        dinamicDemografia(panel_dmg_strike);
        dinamic_bt_demografia(bt_dmg_stingStrike);
        this.setTitle("Modificar calle");
    }//GEN-LAST:event_itemM_modificar_calleActionPerformed

    private void itemM_modificar_liderCalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemM_modificar_liderCalleActionPerformed
        ocultar(panel_demografia);
        dinamicDemografia(panel_dmg_strike);
        dinamic_bt_demografia(bt_dmg_estruc);
        this.setTitle("Estructura comunal");
    }//GEN-LAST:event_itemM_modificar_liderCalleActionPerformed

    private void itemM_imprimir_censoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemM_imprimir_censoActionPerformed
        axdinamicMenu();
        ArrayList<String[]> lista = persona.RecuperarAll("");
        modelo = new DefaultTableModel();
        String[] colums = new String[]{"Nombre y Apellido", "Genero", "Cedula", "fecha de nacimiento", "Telefono", "Correo", "Nacionalidad"};
        modelo.setColumnIdentifiers(colums);

        for (String[] aux : lista) {
            modelo.addRow(new String[]{
                aux[1] + " " + aux[2] + " " + aux[3] + " " + aux[4],
                aux[6],
                aux[5],
                aux[7],
                aux[10],
                aux[11],
                aux[9]
            });
        }
        File file = showFileChooser();
        if (file != null) {
            try {
                File f = new PrintTable().printTable(modelo, "Reporte Consejo Comunal 'La Union'", new PageFormat(null, 850, 1100, PageOrientation.LANDSCAPE)).exportToExcel(file);
                Desktop.getDesktop().open(f);
            } catch (IOException ex) {
                Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DRException ex) {
                Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_itemM_imprimir_censoActionPerformed

    private void itemM_imprimir_cartaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemM_imprimir_cartaActionPerformed
        ImprimirCarta imprimirCarta = new ImprimirCarta();
        imprimirCarta.setVisible(true);
        axdinamicMenu();
    }//GEN-LAST:event_itemM_imprimir_cartaActionPerformed

    private void itemM_pregunta_respaldarBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemM_pregunta_respaldarBDActionPerformed
        axdinamicMenu();
        usuario.setUsuarioo(bt_menu_usuario.getText());
        if (usuario.comprobarTipoUser("Administrador")) {
            File file = showFileChooser();
            conect.respaldar.respaldar(file.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Esta opcion es solo para los usuarios,\"Administradores\"");
        }
    }//GEN-LAST:event_itemM_pregunta_respaldarBDActionPerformed

    private void itemM_pregunta_manualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemM_pregunta_manualActionPerformed
        try {
            File file = new File(System.getProperty("user.dir")+ "/recursos/Manual de usuario.pdf");
            Desktop.getDesktop().open(file);
            axdinamicMenu();
        } catch (IOException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_itemM_pregunta_manualActionPerformed

    private void itemM_usuario_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemM_usuario_addActionPerformed
        axdinamicMenu();
        usuario.setUsuarioo(bt_menu_usuario.getText());
        if (usuario.comprobarTipoUser("Administrador")) {
            login.setPrincipal(this);
            login.setVisible(true);
            login.dinami(2);
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Esta opcion es solo para los usuarios,\"Administradores\"");
        }
    }//GEN-LAST:event_itemM_usuario_addActionPerformed

    private void itemM_usuario_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemM_usuario_modificarActionPerformed
        axdinamicMenu();
        login.setPrincipal(this);
        login.setVisible(true);
        this.setVisible(false);
        login.dinami(1);
    }//GEN-LAST:event_itemM_usuario_modificarActionPerformed

    private void itemM_usuario_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemM_usuario_closeActionPerformed
        axdinamicMenu();
        login.setPrincipal(this);
        login.setVisible(true);
        this.setVisible(false);
        login.dinami(0);
    }//GEN-LAST:event_itemM_usuario_closeActionPerformed

    private void entradaTxt_demografiaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_entradaTxt_demografiaCaretUpdate

    }//GEN-LAST:event_entradaTxt_demografiaCaretUpdate

    private void entradaTxt_demografiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_entradaTxt_demografiaKeyReleased
        if (entradaTxt_demografia.getText().trim().isEmpty() == false) {
            entradaTxt_demografia.setText(entradaTxt_demografia.getText().substring(0, 1).toUpperCase() + entradaTxt_demografia.getText().substring(1));
        }
    }//GEN-LAST:event_entradaTxt_demografiaKeyReleased

    private void en_serv_agua_sActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_en_serv_agua_sActionPerformed
        en_serv_agua_n.setSelected(false);
    }//GEN-LAST:event_en_serv_agua_sActionPerformed

    private void en_serv_agua_nActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_en_serv_agua_nActionPerformed
        en_serv_agua_s.setSelected(false);
    }//GEN-LAST:event_en_serv_agua_nActionPerformed

    private void en_serv_aguaN_sActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_en_serv_aguaN_sActionPerformed
        en_serv_aguaN_n.setSelected(false);
    }//GEN-LAST:event_en_serv_aguaN_sActionPerformed

    private void en_serv_aguaN_nActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_en_serv_aguaN_nActionPerformed
        en_serv_aguaN_s.setSelected(false);
    }//GEN-LAST:event_en_serv_aguaN_nActionPerformed

    private void en_serv_elct_sActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_en_serv_elct_sActionPerformed
        en_serv_elct_n.setSelected(false);
    }//GEN-LAST:event_en_serv_elct_sActionPerformed

    private void en_serv_elct_nActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_en_serv_elct_nActionPerformed
        en_serv_elct_s.setSelected(false);
    }//GEN-LAST:event_en_serv_elct_nActionPerformed

    private void en_serv_cls_sActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_en_serv_cls_sActionPerformed
        en_serv_cls_n.setSelected(false);
    }//GEN-LAST:event_en_serv_cls_sActionPerformed

    private void en_serv_cls_nActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_en_serv_cls_nActionPerformed
        en_serv_cls_s.setSelected(false);
    }//GEN-LAST:event_en_serv_cls_nActionPerformed

    private void jTable4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MousePressed
        if ((String) jTable4.getValueAt(jTable4.getSelectedRow(), 0) != null) {
            seleccionDiscapacidad = (String) jTable4.getValueAt(jTable4.getSelectedRow(), 0);
            System.out.println(seleccionDiscapacidad);
        }
    }//GEN-LAST:event_jTable4MousePressed

    private void Registro3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Registro3MousePressed
        seleccionDiscapacidad = "";
        Registro3.requestFocusInWindow();
        jTable4.setSelectionMode(0);
    }//GEN-LAST:event_Registro3MousePressed

    private void jTable7MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable7MouseReleased

    }//GEN-LAST:event_jTable7MouseReleased

    private void jTable7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable7MousePressed
        persona.setId(Integer.valueOf((String) jTable7.getValueAt(jTable7.getSelectedRow(), 0)));
    }//GEN-LAST:event_jTable7MousePressed

    private void jTable6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable6MousePressed
        persona.setId(Integer.valueOf((String) jTable6.getValueAt(jTable6.getSelectedRow(), 0)));
    }//GEN-LAST:event_jTable6MousePressed

    private void jTable9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable9MousePressed
        persona.setId(Integer.valueOf((String) jTable9.getValueAt(jTable9.getSelectedRow(), 0)));
    }//GEN-LAST:event_jTable9MousePressed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        dinamic_filtroPro(jLayeredPane7);
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jTable3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MousePressed
        demografia.setStrike((String) jTable3.getValueAt(jTable3.getSelectedRow(), 0));
        if (bt_dmg_stingStrike.getForeground().equals(seleccionado) == true) {

            entradaTxt_demografia.setText((String) jTable3.getValueAt(jTable3.getSelectedRow(), 0));
        }
    }//GEN-LAST:event_jTable3MousePressed

    private void jCheckBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox6ActionPerformed
        if (jCheckBox6.isSelected() == true) {
            jCheckBox1.setEnabled(false);
            jCheckBox2.setEnabled(false);
            jCheckBox3.setEnabled(false);
        } else {
            jCheckBox1.setEnabled(true);
            jCheckBox2.setEnabled(true);
            jCheckBox3.setEnabled(true);
        }
    }//GEN-LAST:event_jCheckBox6ActionPerformed

    private void jCheckBox9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox9ActionPerformed
        if (jCheckBox9.isSelected() == true) {
            combo_filtroPro_rolFamiliar.setSelectedIndex(0);
        }
    }//GEN-LAST:event_jCheckBox9ActionPerformed

    private void combo_filtroPro_rolFamiliarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_filtroPro_rolFamiliarActionPerformed
        if (combo_filtroPro_rolFamiliar.getSelectedIndex() == 0) {
            jCheckBox9.setSelected(true);
        } else {
            jCheckBox9.setSelected(false);
        }
    }//GEN-LAST:event_combo_filtroPro_rolFamiliarActionPerformed

    private void buttonGradient1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient1ActionPerformed
        poliFamly("añadir a grupo");
        dinamicRegistro(Registro4);
    }//GEN-LAST:event_buttonGradient1ActionPerformed

    private void buttonGradient2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient2ActionPerformed
        poliFamly("nuevo grupo");
        dinamicRegistro(Registro4);
        cargarRfCbbx("Jefe de Familia");
    }//GEN-LAST:event_buttonGradient2ActionPerformed

    private void checkbox_entradaNVedc_sinEducacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_entradaNVedc_sinEducacionActionPerformed
        grupCheckboxdc(checkbox_entradaNVedc_sinEducacion);
    }//GEN-LAST:event_checkbox_entradaNVedc_sinEducacionActionPerformed

    private void combo_entradaRdd_listaDiscapacidadesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combo_entradaRdd_listaDiscapacidadesItemStateChanged
        mostrarDestallesDd((String) combo_entradaRdd_listaDiscapacidades.getSelectedItem());
    }//GEN-LAST:event_combo_entradaRdd_listaDiscapacidadesItemStateChanged

    private void combo_entradaRdd_listaDiscapacidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_entradaRdd_listaDiscapacidadesActionPerformed
        mostrarDestallesDd((String) combo_entradaRdd_listaDiscapacidades.getSelectedItem());
    }//GEN-LAST:event_combo_entradaRdd_listaDiscapacidadesActionPerformed

    private void boton_entradaRdd_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_entradaRdd_agregarActionPerformed
        if (combo_entradaRdd_listaDiscapacidades.getSelectedItem().equals("") == false) {
            AgregarDisc((String) combo_entradaRdd_listaDiscapacidades.getSelectedItem());
        }
    }//GEN-LAST:event_boton_entradaRdd_agregarActionPerformed

    private void buttonAction1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAction1ActionPerformed
        if (seleccionDiscapacidad.isEmpty() == false) {
            RemoveDisc((String) jTable4.getValueAt(jTable4.getSelectedRow(), 0));
        } else {
            JOptionPane.showMessageDialog(null, "Primero tienes que seleccionar la discapacidad");
        }
    }//GEN-LAST:event_buttonAction1ActionPerformed

    private void enSexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enSexoActionPerformed

    }//GEN-LAST:event_enSexoActionPerformed

    private void bt_registro1_siguienteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_registro1_siguienteMousePressed
        DenpNombre(0);
        DensNombre(0);
        DenpApellido(0);
        DensApellido(0);
        DenTelefono(0);
        DenCorreo(0);
        DenCedula(0);

        Integer cedula;
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MMMM-yyyy");
        LocalDate fecha = LocalDate.parse(entrada_fechaN_registro.getText(), formato);
        LocalDate hoy = LocalDate.now();
        Period periodo = Period.between(fecha, hoy);

        if (enCedula.getText().equals("Cedula de Identidad") == true) {
            cedula = 0;
        } else {
            cedula = Integer.valueOf(enCedula.getText());
        }
        /*
        
        
         */
        try {

            if (!(enpNombre.getText().equals("Primer nombre") == false && ensNombre.getText().equals("Segundo nombre") == false && enpApellido.getText().equals("Primer apellido") == false && ensApellido.getText().equals("Segundo Apellido") == false)) {
                throw new IllegalAccessException("Debes de poner los dos nombre y los dos apellidos");
            }
            if (!(cedula == 0 || cedula > 1000000)) {
                throw new IllegalAccessException("El formato de la cedula es invalido");
            }
            if (fecha.isAfter(hoy)) {
                throw new IllegalAccessException("La fecha que has ingresado no es valida");
            }
            if (periodo.getYears() > 120) {
                throw new IllegalAccessException("Fecha invalida!!\nDeverdad existe una persona con " + periodo.getYears() + " añon viva?");
            }
            if (enSexo.getSelectedItem().equals("Generos") == true) {
                throw new IllegalAccessException("Debes de seleccionar un genero");
            }
            switch (tipoDeRegistro) {
                case "nuevo":
                    if (persona.exisPer(cedula) && cedula != 0 && persona.getCedula() != cedula) {
                        etick_cedula.setText("cedula ya registrada!");
                        etick_cedula.setForeground(new Color(250, 20, 20));
                        throw new IllegalAccessException("La cedula que esta intentando agregar ya esta registrada,\n "
                                + "por favor verifique la cedula o asegurese de que esta persona ya este registrada");
                    }
                    dinamicRegistro(Registro2);
                    dinamicDregisro(pr2);
                    etick_cedula.setText("cedula de identidad");
                    etick_cedula.setForeground(new Color(0, 0, 0));

                    break;
                case "modificar":
                    if (persona.exisCedulaPer(cedula, persona.getId()) && cedula != 0) {
                        etick_cedula.setText("cedula ya registrada!");
                        etick_cedula.setForeground(new Color(250, 20, 20));
                        throw new IllegalAccessException("La cedula que esta intentando agregar ya esta registrada,\n "
                                + "por favor verifique la cedula o asegurese de que esta persona ya este registrada");
                    }
                    dinamicRegistro(Registro2);
                    dinamicDregisro(pr2);
                    etick_cedula.setText("cedula de identidad");
                    etick_cedula.setForeground(new Color(0, 0, 0));
                    break;
            }
        } catch (IllegalAccessException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
//        if (enCedula.getText().equals("Cedula de Identidad") == true) {
//            cedula = 0;
//        } else {
//            cedula = Integer.valueOf(enCedula.getText());
//        }
//
//        if (enpNombre.getText().equals("Primer nombre") == false && ensNombre.getText().equals("Segundo nombre") == false && enpApellido.getText().equals("Primer apellido") == false && ensApellido.getText().equals("Segundo Apellido") == false) {
//            if (cedula == 0 || cedula > 1000000) {
//
//                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MMMM-yyyy");
//                LocalDate fecha = LocalDate.parse(jTextField14.getText(), formato);
//                LocalDate hoy = LocalDate.now();
//
//                if (fecha.isAfter(hoy)) {
//                    JOptionPane.showMessageDialog(pr1, "La fecha que has ingresado no es valida");
//                } else {
//                        if (condicion == 0) {
//                            if (persona.exisPer(cedula)) {
//                                if (cedula != 0) {
//                                    JOptionPane.showMessageDialog(null, "La cedula que esta intentando agregar ya esta registrada,\n por favor verifique la cedula o asegurese de que esta persona ya este registrada");
//                                    jLabel99.setText("cedula ya registrada!");
//                                    jLabel99.setForeground(new Color(250, 20, 20));
//                                } else {
//                                    dinamicRegistro(Registro2);
//                                    dinamicDregisro(pr2);
//                                    jLabel99.setText("cedula de identidad");
//                                    jLabel99.setForeground(new Color(0, 0, 0));
//                                }
//                            } else {
//                                dinamicRegistro(Registro2);
//                                dinamicDregisro(pr2);
//                                jLabel99.setText("cedula de identidad");
//                                jLabel99.setForeground(new Color(0, 0, 0));
//                            }
//
//                        } else {
//                            if (persona.exisCedulaPer(cedula, persona.getId())) {
//
//                                if (cedula != 0) {
//                                    JOptionPane.showMessageDialog(null, "La cedula que esta intentando agregar ya esta registrada,\n por favor verifique la cedula o asegurese de que esta persona ya este registrada");
//
//                                    jLabel99.setText("cedula ya registrada!");
//                                    jLabel99.setForeground(new Color(250, 20, 20));
//                                } else {
//                                    dinamicRegistro(Registro2);
//                                    dinamicDregisro(pr2);
//                                    jLabel99.setText("cedula de identidad");
//                                    jLabel99.setForeground(new Color(0, 0, 0));
//                                }
//                            } else {
//                                dinamicRegistro(Registro2);
//                                dinamicDregisro(pr2);
//                                jLabel99.setText("cedula de identidad");
//                                jLabel99.setForeground(new Color(0, 0, 0));
//                            }
//                        }
//                    
//                }
//
//            } else {
//                JOptionPane.showMessageDialog(null, "El formato de la cedula es invalido");
//            }
//        } else {
//            JOptionPane.showMessageDialog(null, "Debes de poner los dos nombre y los dos apellidos");
//        }

    }//GEN-LAST:event_bt_registro1_siguienteMousePressed

    private void itemM_pregunta_restaurarBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemM_pregunta_restaurarBDActionPerformed
        axdinamicMenu();
        usuario.setUsuarioo(bt_menu_usuario.getText());
        if (usuario.comprobarTipoUser("Administrador")) {
            JFileChooser file = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo de respaldo", "sql");
            file.setFileFilter(filter);

            int i = file.showOpenDialog(null);

            if (i == JFileChooser.APPROVE_OPTION) {
                File fille = file.getSelectedFile();
                System.out.println(fille.getAbsolutePath());
                restaurar.restaurar(fille.getAbsolutePath());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Esta opcion es solo para los usuarios,\"Administradores\"");
        }


    }//GEN-LAST:event_itemM_pregunta_restaurarBDActionPerformed

    private void totalInfo_filtroProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalInfo_filtroProActionPerformed
        if (totalInfo_filtroPro.isSelected() == true) {
            jTable9.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        } else {
            jTable9.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        }
        filtroPro();
    }//GEN-LAST:event_totalInfo_filtroProActionPerformed

    private void itemM_usuario_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemM_usuario_exitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_itemM_usuario_exitActionPerformed

    /**
     * user
     *
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        FlatRobotoFont.install();
//        FlatLaf.registerCustomDefaultsSource("raven.combobox");
//        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
//        FlatMacDarkLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new principal().setVisible(true);
            }
        });
    }

    public void ocultar(JPanel aux) {
        panel_filtro.setVisible(false);
        panelMenup.setVisible(false);
        panel_registrar.setVisible(false);
        panel_demografia.setVisible(false);

        panelBarra.setVisible(true);
        btSalie.setVisible(true);
        aux.setVisible(true);

        if (aux.equals(panelMenup)) {
            btSalie.setVisible(false);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Registro1;
    private javax.swing.JPanel Registro2;
    private javax.swing.JPanel Registro3;
    private javax.swing.JPanel Registro3o4;
    private javax.swing.JPanel Registro4;
    private javax.swing.JPanel Registro5;
    private javax.swing.JPanel barraLateralD;
    private elaprendiz.gui.button.ButtonAction boton_entradaRdd_agregar;
    private javax.swing.JLabel btSalie;
    private javax.swing.JLabel bt_dmg_addLider;
    private javax.swing.JLabel bt_dmg_addStrike;
    private javax.swing.JLabel bt_dmg_descartar;
    private javax.swing.JLabel bt_dmg_estruc;
    private javax.swing.JLabel bt_dmg_guardar;
    private javax.swing.JLabel bt_dmg_stingStrike;
    private elaprendiz.gui.button.ButtonShadow bt_fechaN_registro1;
    static Clases.botones.ButtonGradient bt_menu_agregar;
    static Clases.botones.ButtonGradient bt_menu_demografia;
    static Clases.botones.ButtonGradient bt_menu_gestionar;
    static Clases.botones.ButtonGradient bt_menu_imprimir;
    static Clases.botones.ButtonGradient bt_menu_modificar;
    static Clases.botones.ButtonGradient bt_menu_usuario;
    static Clases.botones.ButtonGradient bt_mn_buscar;
    private javax.swing.JLabel bt_registro1_siguiente;
    private elaprendiz.gui.button.ButtonAction buttonAction1;
    private Clases.botones.ButtonGradient buttonGradient1;
    private Clases.botones.ButtonGradient buttonGradient2;
    private static javax.swing.JCheckBox checkbox_entradaNVedc_eduacionBasica;
    private static javax.swing.JCheckBox checkbox_entradaNVedc_eduacionSuperior;
    private static javax.swing.JCheckBox checkbox_entradaNVedc_educacionInicial;
    private static javax.swing.JCheckBox checkbox_entradaNVedc_educacionMedia;
    private static javax.swing.JCheckBox checkbox_entradaNVedc_sinEducacion;
    private Clases.combobox.ComboBoxSuggestion combo_demografia_asigLider;
    private Clases.combobox.ComboBoxSuggestion combo_entradaRdd_listaDiscapacidades;
    private Clases.combobox.ComboBoxSuggestion combo_filtroPro_estCasa;
    private Clases.combobox.ComboBoxSuggestion combo_filtroPro_nvEdc;
    private Clases.combobox.ComboBoxSuggestion combo_filtroPro_rolFamiliar;
    private Clases.combobox.ComboBoxSuggestion combo_filtroPro_tipoDiscapacidad;
    private Clases.combobox.ComboBoxSuggestion combo_filtroPro_ubicacion;
    private javax.swing.JSeparator dSeparador_cedula;
    private javax.swing.JSeparator dSeparador_correo;
    private javax.swing.JSeparator dSeparador_pApellido;
    private javax.swing.JSeparator dSeparador_pNombre;
    private javax.swing.JSeparator dSeparador_sApellido;
    private javax.swing.JSeparator dSeparador_sNombre;
    private javax.swing.JSeparator dSeparador_telefono;
    private com.raven.datechooser.DateChooser dateChooser1;
    private javax.swing.JTextField enCedula;
    private javax.swing.JTextField enCorreo;
    private Clases.combobox.ComboBoxSuggestion enNacionalidad;
    private Clases.combobox.ComboBoxSuggestion enSexo;
    private javax.swing.JTextField enTelefono;
    private javax.swing.JComboBox<String> en_combo_direccion;
    private javax.swing.JComboBox<String> en_combo_stdCasa;
    private javax.swing.JTextField en_nCasa;
    private javax.swing.JCheckBox en_not_nCasa;
    private javax.swing.JCheckBox en_serv_aguaN_n;
    private javax.swing.JCheckBox en_serv_aguaN_s;
    private javax.swing.JCheckBox en_serv_agua_n;
    private javax.swing.JCheckBox en_serv_agua_s;
    private javax.swing.JCheckBox en_serv_cls_n;
    private javax.swing.JCheckBox en_serv_cls_s;
    private javax.swing.JCheckBox en_serv_elct_n;
    private javax.swing.JCheckBox en_serv_elct_s;
    private javax.swing.JTextField enpApellido;
    private javax.swing.JTextField enpNombre;
    private javax.swing.JTextField ensApellido;
    private javax.swing.JTextField ensNombre;
    private javax.swing.JTextField entradaTxt_demografia;
    private javax.swing.JTextField entrada_fechaN_registro;
    private javax.swing.JLabel etick_cedula;
    private javax.swing.JLabel etick_correo;
    private javax.swing.JLabel etick_fechaN;
    private javax.swing.JLabel etick_genero;
    private javax.swing.JLabel etick_nacionalidad;
    private javax.swing.JLabel etick_pApellido;
    private javax.swing.JLabel etick_pNombre;
    private javax.swing.JLabel etick_sApellido;
    private javax.swing.JLabel etick_sNombre;
    private javax.swing.JLabel etick_telefono;
    private javax.swing.JLabel fondo_imagen;
    private Clases.botones.ButtonGradient itemM_agregar_LiderCalle;
    private Clases.botones.ButtonGradient itemM_agregar_cargaFamiliar;
    private Clases.botones.ButtonGradient itemM_agregar_persona;
    private Clases.botones.ButtonGradient itemM_demografia_addCalle;
    private Clases.botones.ButtonGradient itemM_demografia_infComunal;
    private Clases.botones.ButtonGradient itemM_filtro_buscar;
    private Clases.botones.ButtonGradient itemM_filtro_edad;
    private Clases.botones.ButtonGradient itemM_filtro_nvlEdc;
    private Clases.botones.ButtonGradient itemM_filtro_otro;
    private Clases.botones.ButtonGradient itemM_filtro_ubicacion;
    private Clases.botones.ButtonGradient itemM_imprimir_carta;
    private Clases.botones.ButtonGradient itemM_imprimir_censo;
    private Clases.botones.ButtonGradient itemM_modificar_calle;
    private Clases.botones.ButtonGradient itemM_modificar_familia;
    private Clases.botones.ButtonGradient itemM_modificar_liderCalle;
    private Clases.botones.ButtonGradient itemM_modificar_persona;
    private Clases.botones.ButtonGradient itemM_pregunta_manual;
    private Clases.botones.ButtonGradient itemM_pregunta_respaldarBD;
    private Clases.botones.ButtonGradient itemM_pregunta_restaurarBD;
    private Clases.botones.ButtonGradient itemM_usuario_add;
    private Clases.botones.ButtonGradient itemM_usuario_close;
    private Clases.botones.ButtonGradient itemM_usuario_exit;
    private Clases.botones.ButtonGradient itemM_usuario_modificar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private static javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JLayeredPane jLayeredPane5;
    private javax.swing.JLayeredPane jLayeredPane6;
    private javax.swing.JLayeredPane jLayeredPane7;
    private javax.swing.JLayeredPane jLayeredPane8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable8;
    private javax.swing.JTable jTable9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel label_entradaNVedc_eduacionBasica;
    private javax.swing.JLabel label_entradaNVedc_educacionInicial;
    private javax.swing.JLabel label_entradaNVedc_educacionMedia;
    private javax.swing.JLabel label_entradaNVedc_educacionSuperior;
    private javax.swing.JLabel label_entradaNVedc_sinEducacion;
    private javax.swing.JTextArea label_registroP_detallesSs_Descripcion;
    private javax.swing.JLabel label_registroP_detallesSs_nombre;
    private javax.swing.JLabel label_registroP_detallesSs_tipo;
    private javax.swing.JLayeredPane menu_impress;
    private Clases.PanelRound menu_impressD;
    private javax.swing.JPanel p_filtroDirecc;
    private javax.swing.JPanel p_filtroEdad;
    private javax.swing.JPanel p_filtroGeneral;
    private javax.swing.JPanel p_filtroNEdc;
    private javax.swing.JPanel p_filtroPro;
    private javax.swing.JPanel panelBarra;
    private javax.swing.JPanel panelMenup;
    private javax.swing.JPanel panel_base;
    private javax.swing.JPanel panel_demografia;
    private javax.swing.JLayeredPane panel_dmg_lider;
    private javax.swing.JLayeredPane panel_dmg_strike;
    private javax.swing.JPanel panel_filtro;
    private javax.swing.JPanel panel_registrar;
    private Clases.PanelRound pr1;
    private Clases.PanelRound pr2;
    private Clases.PanelRound pr3;
    private Clases.PanelRound pr4;
    private Clases.PanelRound pr5;
    private Clases.checkbox.JCheckBoxCustom totalInfo_filtroPro;
    // End of variables declaration//GEN-END:variables

    public void dinamicRegistro(JPanel panel) {
        Registro1.setVisible(false);
        Registro3.setVisible(false);
        Registro2.setVisible(false);
        Registro4.setVisible(false);
        Registro5.setVisible(false);
        Registro3o4.setVisible(false);

        panel.setVisible(true);
    }

    public void cargar_tabla(ArrayList<String[]> lista) {
        DefaultTableModel modelo = new DefaultTableModel();
        String[] colums = {"cedula", "Primer Nombre", "Segundo Nombre", "Primer Apellidos", "Segundo Apellido"};
        modelo.setColumnIdentifiers(colums);

        for (String[] aux : lista) {
            modelo.addRow(aux);
        }
        jTable1.setModel(modelo);
    }

    public void Cldemografia() {
        dinamic_bt_demografia(null);

        combo_demografia_asigLider.setModel(new javax.swing.DefaultComboBoxModel<>(cargarStrikeCbbx()));
        jTextField10.setText(null);
    }

    public void Clregistro() {
        jLabel55.setVisible(true);

        etick_pNombre.setVisible(false);
        etick_sNombre.setVisible(false);
        etick_pApellido.setVisible(false);
        etick_sApellido.setVisible(false);
        etick_cedula.setVisible(false);
        etick_telefono.setVisible(false);
        etick_correo.setVisible(false);

        etick_cedula.setText("cedula de identidad");
        etick_cedula.setForeground(new Color(0, 0, 0));

        enpNombre.setText("Primer nombre");
        enpNombre.setForeground(new java.awt.Color(51, 51, 51));
        ensNombre.setText("Segundo nombre");
        ensNombre.setForeground(new java.awt.Color(51, 51, 51));
        enpApellido.setText("Primer apellido");
        enpApellido.setForeground(new java.awt.Color(51, 51, 51));
        ensApellido.setText("Segundo Apellido");
        ensApellido.setForeground(new java.awt.Color(51, 51, 51));
        enCedula.setText("Cedula de Identidad");
        enCedula.setForeground(new java.awt.Color(51, 51, 51));
        enCorreo.setText("Correo");
        enCorreo.setForeground(new java.awt.Color(51, 51, 51));
        enTelefono.setText("Telefono");
        enTelefono.setForeground(new java.awt.Color(51, 51, 51));

        dateChooser1.toDay();
        // enFechaD.setText("Dia");
        //  enFechaD.setForeground(new java.awt.Color(51, 51, 51));
        //   enFechaYear.setText("Año");
        //   enFechaYear.setForeground(new java.awt.Color(51, 51, 51));
        dSeparador_correo.setBackground(new java.awt.Color(51, 51, 51));
        dSeparador_sNombre.setBackground(new java.awt.Color(51, 51, 51));
        dSeparador_pApellido.setBackground(new java.awt.Color(51, 51, 51));
        dSeparador_sApellido.setBackground(new java.awt.Color(51, 51, 51));
        dSeparador_cedula.setBackground(new java.awt.Color(51, 51, 51));
        dSeparador_telefono.setBackground(new java.awt.Color(51, 51, 51));
        dSeparador_pNombre.setBackground(new java.awt.Color(51, 51, 51));

        grupCheckboxdc(checkbox_entradaNVedc_educacionInicial);
        checkbox_entradaNVedc_educacionInicial.setSelected(false);
        cargarDdCbbx();
        modeloTdiscapacidades = new DefaultTableModel();
        String[] colums = {"Enfermedades"};
        modeloTdiscapacidades.setColumnIdentifiers(colums);
        jTable4.setModel(modeloTdiscapacidades);
        combo_entradaRdd_listaDiscapacidades.setModel(new javax.swing.DefaultComboBoxModel<>(cargarDdCbbx()));

        // enFechaM.setSelectedIndex(0);
        enNacionalidad.setSelectedIndex(0);
        enSexo.setSelectedIndex(0);
        dinamicDregisro(pr1);
        //rolesFm = new String[]{"Jefe de Familia", "Esposo(a)", "Hijo(a)"};
        //jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(rolesFm));
        stadeRegistro = 0;
        stadeRegistroC = 0;

        en_serv_agua_s.setSelected(false);
        en_serv_aguaN_s.setSelected(false);
        en_serv_elct_s.setSelected(false);
        en_serv_cls_s.setSelected(false);

        en_serv_agua_n.setSelected(false);
        en_serv_aguaN_n.setSelected(false);
        en_serv_elct_n.setSelected(false);
        en_serv_cls_n.setSelected(false);

        en_combo_stdCasa.setSelectedIndex(0);
        en_combo_direccion.setSelectedIndex(0);

        en_nCasa.setText("");
        en_not_nCasa.setSelected(false);

        mostrarDestallesDd("");
        cargar_rolesfm();
    }

    public void CLfiltros() {
        menu_impressD.setVisible(false);
        menu_impress.setVisible(false);
        /*limpiar filtro pro*/
        jTextField12.setText("");
        jTextField13.setText("");
        jTextField8.setText("");

        combo_filtroPro_ubicacion.setSelectedIndex(0);
        combo_filtroPro_nvEdc.setSelectedIndex(0);
        combo_filtroPro_estCasa.setSelectedIndex(0);
        combo_filtroPro_rolFamiliar.setSelectedIndex(0);
        combo_filtroPro_tipoDiscapacidad.setSelectedIndex(0);

        jCheckBox1.setSelected(false);
        jCheckBox2.setSelected(false);
        jCheckBox3.setSelected(false);
        jCheckBox4.setSelected(false);
        /**/
    }

    /*Esta funcion se va a encargar de llenar la tabla del nucleo familia*/
    public void cargarPersona() throws ParseException {
        persona.setpNombre(enpNombre.getText());
        persona.setsNombre(ensNombre.getText());
        persona.setpApellido(enpApellido.getText());
        persona.setsApellido(ensApellido.getText());
        if (enCedula.getText().equals("Cedula de Identidad") == true || enCedula.getText().equals("") == true) {
            persona.setCedula(0);
        } else {
            persona.setCedula(Integer.parseInt(enCedula.getText()));
        }
        relacionesForaneas.setSexo((String) enSexo.getSelectedItem());
        persona.setSexo(relacionesForaneas.buscarSexo());
        int i = 0;
        if (relacionesForaneas.buscarMgAcademico() != null) {
            i = relacionesForaneas.buscarMgAcademico();
        }
        persona.setMgAcademico(i);

        SimpleDateFormat formato = new SimpleDateFormat("dd-MMMM-yyyy");
        persona.setFechaN(formato.parse(entrada_fechaN_registro.getText()));
        System.out.println(entrada_fechaN_registro.getText());
//   persona.setFechaN(formato.parse(enFechaYear.getText() + "-" + (String) enFechaM.getSelectedItem() + "-" + enFechaD.getText()));

        relacionesForaneas.setNacinalidad((String) enNacionalidad.getSelectedItem());
        persona.setNacionalidad(relacionesForaneas.buscarNacionalidad());
        if (enTelefono.getText().equals("Telefono") == true || enTelefono.getText().equals("") == true) {
            persona.setTelefono(0);
        } else {
            persona.setTelefono(Double.parseDouble(enTelefono.getText()));
        }
        persona.setCorreo(enCorreo.getText());
        persona.setRolFamiliar(relacionesForaneas.buscarRolFamiliar());

        System.out.println("Interfaz.principal.cargarPersona()");
        for (String ax : discapacidades.getLDiscapacidad()) {
            relacionesForaneas.setDiscapacidad(ax);
            int id_discapacidad = relacionesForaneas.buscarDiscapacidad();
            discapacidades.addDiscapacidad(persona.getIdd(), id_discapacidad);
        }
        persona.addFamilia();
        discapacidades.volcarLDiscapacidad();

    }

    public void montarPersona() {
        if (usuario.comprobarTipoUser("Administrador")) {
            condicion = 1;
            persona.buscar(persona.getId());
            DenpNombre(1);
            DensNombre(1);
            DenpApellido(1);
            DensApellido(1);
            DenCedula(1);
            DenTelefono(1);
            DenCorreo(1);
            enpNombre.setText(persona.getpNombre());
            ensNombre.setText(persona.getsNombre());
            enpApellido.setText(persona.getpApellido());
            ensApellido.setText(persona.getsApellido());
            enCedula.setText(String.valueOf(persona.getCedula()));

            relacionesForaneas.setId_sexo(persona.getSexo());
            int i = 0;
            for (String ax : cargarSexoCbbx()) {
                if (ax.equals(relacionesForaneas.nSexo())) {
                    enSexo.setSelectedIndex(i);
                    System.out.println(ax);
                }
                i++;
            }

            i = 0;
            relacionesForaneas.setId_nacionalidad(persona.getNacionalidad());
            for (String ax : cargarNacionalidadCbbx()) {
                if (ax.equals(relacionesForaneas.nNacionalidad())) {
                    enNacionalidad.setSelectedIndex(i);
                    System.out.println(ax);
                }
                i++;
            }

            i = 0;
            System.out.println(persona.getFechaN() + " esta es la fecha");

            SimpleDateFormat formato = new SimpleDateFormat("dd-MMMM-yyyy");
            System.out.println(formato.format(persona.getFechaN()));
            //String[] fecha = formato.format(persona.getFechaN()).split("-");
            dateChooser1.setSelectedDate(persona.getFechaN());

            if (persona.getTelefono() != 0) {
                DecimalFormat fm = new DecimalFormat("#");
                enTelefono.setText(fm.format(persona.getTelefono()));
            }
            if (persona.getCorreo().equals("") == false) {
                enCorreo.setText(persona.getCorreo());
            }
            relacionesForaneas.setId_mgAcademico(persona.getMgAcademico());
            switch (relacionesForaneas.nMgAcademico()) {
                case "Educacion Inicial":
                    grupCheckboxdc(checkbox_entradaNVedc_educacionInicial);
                    break;
                case "Educacion Basica":
                    grupCheckboxdc(checkbox_entradaNVedc_eduacionBasica);
                    break;
                case "Educacion Media":
                    grupCheckboxdc(checkbox_entradaNVedc_educacionMedia);
                    break;
                case "Educacion Superior":
                    grupCheckboxdc(checkbox_entradaNVedc_eduacionSuperior);
                    break;
                case "Sin Educacion":
                    grupCheckboxdc(checkbox_entradaNVedc_sinEducacion);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Algo anda mal -__-");
                    break;
            }

            ArrayList<String> lista = discapacidades.disDP(persona.getId());

            cargarDdCbbx();
            System.out.println("Se deberian de estar agregando las discapacidades");
            for (String ax : lista) {
                System.out.println("se esta agregando " + ax);
                AgregarDisc(ax);
            }
            tipoDeRegistro = "modificar";
            ocultar(panel_registrar);
            dinamicRegistro(Registro1);
        } else {
            JOptionPane.showMessageDialog(null, "Esta opcion es solo para los usuarios,\"Administradores\"");
        }
    }

    /**
     *
     *
     *
     *
     *
     */
    public void cargarHouse() {
        modelos.house.setNumCasa(en_nCasa.getText());

        relacionesForaneas.setStadoCasa((String) en_combo_stdCasa.getSelectedItem());
        modelos.house.setId_estdCasa(relacionesForaneas.buscarStadoCasa());
        modelos.house.setrModuloCLP(en_serv_cls_s.isSelected());

        modelos.demografia.setStrike((String) en_combo_direccion.getSelectedItem());
        modelos.house.setId_direccion(modelos.demografia.idStrike());

        modelos.house.setAgua(en_serv_agua_s.isSelected());
        modelos.house.setAguasN(en_serv_aguaN_s.isSelected());
        modelos.house.setLuz(en_serv_elct_s.isSelected());
        modelos.house.setrModuloCLP(en_serv_cls_s.isSelected());
    }

    public void cargarTfamilia(String opcion) {

        switch (opcion) {
            case "Nuevo grupo":
                String[] registro = {relacionesForaneas.getRolFamiliar(), persona.getpNombre() + " " + persona.getsNombre() + " " + persona.getpApellido() + " " + persona.getsApellido(), String.valueOf(persona.getCedula())};
                String[] colums = {"RolFamilar", "Nombre", "Cedula"};

                System.out.println("se esta creando un nuevo grupo en la tabla <<<<<<");

                modeloFamilia.setColumnIdentifiers(colums);
                modeloFamilia.addRow(registro);

                jTable2.setModel(modeloFamilia);
                break;
            case "Agregar a grupo":

                String buscar;
                if (jTextField2.getText().equals("Buscar...")) {
                    buscar = "";
                } else {
                    buscar = jTextField2.getText();
                }
                ArrayList<String[]> lista = persona.buscarFamilia(buscar);
                modeloFamilia = new DefaultTableModel();
                ;
                String[] colums2 = {"Familia", "Nombre ", "Cedula", "COD"};
                modeloFamilia.setColumnIdentifiers(colums2);

                for (String[] aux : lista) {

                    String[] registro2 = {"Familia de ", aux[0] + " " + aux[1] + " " + aux[2] + " " + aux[3], "V-" + aux[4], aux[5]};
                    modeloFamilia.addRow(registro2);
                }
                jTable2.setModel(modeloFamilia);
                break;

        }
    }

    /**
     *
     *
     *
     *
     *
     */
    public void pDiscapacidades(String aux) {

        for (String[] ax : discapacidades.recuperarAll()) {
            if (ax[1].equals(aux)) {
                label_registroP_detallesSs_nombre.setText("Nombre de discapacidad: " + ax[1]);
                label_registroP_detallesSs_tipo.setText("Tipo de discapacidad: " + ax[3]);
                label_registroP_detallesSs_Descripcion.setText("Descripción: " + ax[2]);
            }
        }
    }

    /**
     *
     *
     *
     *
     *
     */
    public void poliFamly(String aux) {
        switch (aux) {
            case "nuevo grupo":
                jTextField2.setVisible(false);
                jSeparator19.setVisible(false);
                modeloFamilia = new DefaultTableModel();
                relacionesForaneas.setRolFamiliar((String) jComboBox7.getSelectedItem());
                persona.setRolFamiliar(relacionesForaneas.buscarRolFamiliar());
                try {
                    System.out.println("Se esta cargando otra ves la persona");
                    cargarPersona();
                } catch (ParseException ex) {
                    Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
                }
                cargarTfamilia("Nuevo grupo");
                break;
            case "añadir a grupo":
                condicion = 0;
                jTextField2.setVisible(true);
                jTextField2.setEditable(true);
                jSeparator19.setVisible(true);
                cargarRfCbbx("Jefe de Familia");

                cargarTfamilia("Agregar a grupo");
                System.out.println("Interfaz.principal.poliFamly()");
                break;
            case "CL":
                cargar_rolesfm();
                jComboBox7.setModel(new DefaultComboBoxModel<>(rolesFm));
                persona.ClanAll();
                break;
        }
    }

    /**
     *
     *
     *
     *
     *
     */
    public void grupCheckboxdc(JCheckBox aux) {
        checkbox_entradaNVedc_sinEducacion.setSelected(false);
        checkbox_entradaNVedc_educacionInicial.setSelected(false);
        checkbox_entradaNVedc_eduacionBasica.setSelected(false);
        checkbox_entradaNVedc_eduacionSuperior.setSelected(false);
        checkbox_entradaNVedc_educacionMedia.setSelected(false);

        if (aux == checkbox_entradaNVedc_sinEducacion) {
            relacionesForaneas.setMgAcademico("Sin Educacion");
        }
        if (aux == checkbox_entradaNVedc_educacionInicial) {
            relacionesForaneas.setMgAcademico("Educacion Inicial");
        }
        if (aux == checkbox_entradaNVedc_eduacionBasica) {
            relacionesForaneas.setMgAcademico("Educacion Basica");
        }
        if (aux == checkbox_entradaNVedc_educacionMedia) {
            relacionesForaneas.setMgAcademico("Educacion Media");
        }
        if (aux == checkbox_entradaNVedc_eduacionSuperior) {
            relacionesForaneas.setMgAcademico("Educacion Superior");
        }
        aux.setSelected(true);
        System.out.println(persona.getMgAcademico());
    }

    /**
     *
     *
     *
     *
     */
    public void itemsMenuDesplegar(String opcion) {
        switch (opcion) {
            case "Agregar":
                if (itemM_agregar_persona.isVisible() == false) {
                    axdinamicMenu();
                    itemM_agregar_persona.setVisible(true);
                    itemM_agregar_cargaFamiliar.setVisible(true);
                    itemM_agregar_LiderCalle.setVisible(true);
                } else {
                    axdinamicMenu();
                }
                break;

            case "Demografia":
                if (itemM_demografia_addCalle.isVisible() == false) {
                    axdinamicMenu();
                    itemM_demografia_addCalle.setVisible(true);
                    itemM_demografia_infComunal.setVisible(true);
                } else {
                    axdinamicMenu();
                }
                break;
            case "Filtrar":
                if (itemM_filtro_buscar.isVisible() == false) {
                    axdinamicMenu();
                    itemM_filtro_buscar.setVisible(true);
                    itemM_filtro_edad.setVisible(true);
                    itemM_filtro_nvlEdc.setVisible(true);
                    itemM_filtro_otro.setVisible(true);
                    itemM_filtro_ubicacion.setVisible(true);
                } else {
                    axdinamicMenu();
                }
                break;
            case "Usuario":
                if (itemM_usuario_add.isVisible() == false) {
                    axdinamicMenu();
                    itemM_usuario_add.setVisible(true);
                    itemM_usuario_close.setVisible(true);
                    itemM_usuario_exit.setVisible(true);
                    itemM_usuario_modificar.setVisible(true);
                } else {
                    axdinamicMenu();
                }
                break;
            case "Imprimir":
                if (itemM_imprimir_carta.isVisible() == false) {
                    axdinamicMenu();
                    itemM_imprimir_carta.setVisible(true);
                    itemM_imprimir_censo.setVisible(true);
                } else {
                    axdinamicMenu();
                }
                break;
            case "Gestionar":
                if (itemM_pregunta_respaldarBD.isVisible() == false) {
                    axdinamicMenu();
                    itemM_pregunta_respaldarBD.setVisible(true);
                    itemM_pregunta_restaurarBD.setVisible(true);
                    itemM_pregunta_manual.setVisible(true);
                } else {
                    axdinamicMenu();
                }
                break;
            case "Modificar":
                if (itemM_modificar_familia.isVisible() == false) {
                    axdinamicMenu();
                    itemM_modificar_calle.setVisible(true);
                    itemM_modificar_familia.setVisible(true);
                    itemM_modificar_liderCalle.setVisible(true);
                    itemM_modificar_persona.setVisible(true);
                } else {
                    axdinamicMenu();
                }
                break;
        }
    }

    /**
     *
     *
     *
     */
    public void axdinamicMenu() {
        //mostrarMenu(false);
        itemM_agregar_persona.setVisible(false);
        itemM_agregar_cargaFamiliar.setVisible(false);
        itemM_agregar_LiderCalle.setVisible(false);

        itemM_demografia_addCalle.setVisible(false);
        itemM_demografia_infComunal.setVisible(false);

        itemM_filtro_buscar.setVisible(false);
        itemM_filtro_edad.setVisible(false);
        itemM_filtro_nvlEdc.setVisible(false);
        itemM_filtro_otro.setVisible(false);
        itemM_filtro_ubicacion.setVisible(false);

        itemM_modificar_calle.setVisible(false);
        itemM_modificar_familia.setVisible(false);
        itemM_modificar_liderCalle.setVisible(false);
        itemM_modificar_persona.setVisible(false);

        itemM_imprimir_carta.setVisible(false);
        itemM_imprimir_censo.setVisible(false);

        itemM_usuario_add.setVisible(false);
        itemM_usuario_close.setVisible(false);
        itemM_usuario_exit.setVisible(false);
        itemM_usuario_modificar.setVisible(false);

        itemM_pregunta_respaldarBD.setVisible(false);
        itemM_pregunta_restaurarBD.setVisible(false);
        itemM_pregunta_manual.setVisible(false);

    }

    /**
     *
     *
     *
     *
     */
    public void dinamicDemografia(JLayeredPane aux) {
        panel_dmg_lider.setVisible(false);
        panel_dmg_strike.setVisible(false);

        aux.setVisible(true);
    }

    /**
     *
     *
     *
     *
     */
    public void dinamicDregisro(JPanel aux) {
        pr1.setBackground(new Color(51, 153, 255, 100));
        pr2.setBackground(new Color(51, 153, 255, 100));
        pr3.setBackground(new Color(51, 153, 255, 100));
        pr4.setBackground(new Color(51, 153, 255, 100));
        pr5.setBackground(new Color(51, 153, 255, 100));

        aux.setBackground(new Color(255, 51, 71, 120));
    }

    /**
     *
     *
     *
     *
     */
    public void dinamicFiltro(JPanel aux) {
        p_filtroDirecc.setVisible(false);
        p_filtroGeneral.setVisible(false);
        p_filtroNEdc.setVisible(false);
        p_filtroPro.setVisible(false);
        p_filtroEdad.setVisible(false);

        aux.setVisible(true);
    }

    /**
     * funciones para dinamismo en la primera pantall de registro faciado de
     * datos e interaccion entro objetos
     *
     *
     */
    public void DenpNombre(int i) {
        switch (i) {
            case 1:
                if (enpNombre.getText().equals("Primer nombre")) {
                    enpNombre.requestFocusInWindow();
                    etick_pNombre.setVisible(true);
                    enpNombre.setEditable(true);
                    dSeparador_pNombre.setBackground(new Color(0, 0, 250));
                    enpNombre.setForeground(new Color(0, 0, 0));
                    enpNombre.setText("");
                }
                break;
            case 0:
                if (enpNombre.getText().equals("")) {
                    Registro1.requestFocusInWindow();
                    etick_pNombre.setVisible(false);
                    enpNombre.setEditable(false);
                    enpNombre.setText("Primer nombre");
                    dSeparador_pNombre.setBackground(new Color(51, 51, 51));
                    enpNombre.setForeground(new Color(51, 51, 51));
                }

                break;
        }
    }

    public void DensNombre(int i) {
        switch (i) {
            case 1:
                if (ensNombre.getText().equals("Segundo nombre")) {
                    ensNombre.requestFocusInWindow();
                    etick_sNombre.setVisible(true);
                    ensNombre.setEditable(true);
                    dSeparador_sNombre.setBackground(new Color(0, 0, 250));
                    ensNombre.setForeground(new Color(0, 0, 0));
                    ensNombre.setText("");
                }

                break;
            case 0:
                if (ensNombre.getText().equals("")) {
                    Registro1.requestFocusInWindow();
                    etick_sNombre.setVisible(false);
                    ensNombre.setEditable(false);
                    ensNombre.setText("Segundo nombre");
                    dSeparador_sNombre.setBackground(new Color(51, 51, 51));
                    ensNombre.setForeground(new Color(51, 51, 51));
                }
                break;
        }

    }

    public void DenpApellido(int i) {
        switch (i) {
            case 1:
                if (enpApellido.getText().equals("Primer apellido")) {
                    enpApellido.requestFocusInWindow();
                    etick_pApellido.setVisible(true);
                    enpApellido.setEditable(true);
                    enpApellido.setForeground(new Color(0, 0, 0));
                    enpApellido.setText("");
                    dSeparador_pApellido.setBackground(new Color(0, 0, 250));
                }
                break;
            case 0:
                if (enpApellido.getText().equals("")) {
                    Registro1.requestFocusInWindow();
                    etick_pApellido.setVisible(false);
                    enpApellido.setEditable(false);
                    enpApellido.setForeground(new Color(51, 51, 51));
                    enpApellido.setText("Primer apellido");
                    dSeparador_pApellido.setBackground(new Color(51, 51, 51));
                }

                break;
        }

    }

    public void DensApellido(int i) {
        switch (i) {
            case 1:
                if (ensApellido.getText().equals("Segundo Apellido")) {
                    ensApellido.requestFocusInWindow();
                    etick_sApellido.setVisible(true);
                    ensApellido.setEditable(true);
                    ensApellido.setText("");
                    ensApellido.setForeground(new Color(0, 0, 0));
                    dSeparador_sApellido.setBackground(new Color(0, 0, 250));
                }

                break;
            case 0:
                if (ensApellido.getText().equals("")) {
                    Registro1.requestFocusInWindow();
                    etick_sApellido.setVisible(false);
                    ensApellido.setEditable(false);
                    ensApellido.setText("Segundo Apellido");
                    ensApellido.setForeground(new Color(51, 51, 51));
                    dSeparador_sApellido.setBackground(new Color(51, 51, 51));
                }
                break;
        }

    }

    public void DenCedula(int i) {
        switch (i) {
            case 1:
                if (enCedula.getText().equals("Cedula de Identidad")) {
                    enCedula.requestFocusInWindow();
                    etick_cedula.setVisible(true);
                    enCedula.setEditable(true);
                    enCedula.setText("");
                    enCedula.setForeground(new Color(0, 0, 0));
                    dSeparador_cedula.setBackground(new Color(0, 0, 250));
                }
                break;
            case 0:
                if (enCedula.getText().equals("")) {
                    Registro1.requestFocusInWindow();
                    etick_cedula.setVisible(false);
                    enCedula.setEditable(false);
                    enCedula.setForeground(new Color(51, 51, 51));
                    enCedula.setText("Cedula de Identidad");
                    dSeparador_cedula.setBackground(new Color(51, 51, 51));
                }
                break;
        }

    }

    public void DenSexo() {

    }

    public void DenNacionalidad() {

    }

    public void DenTelefono(int i) {
        switch (i) {
            case 1:
                if (enTelefono.getText().equals("Telefono")) {
                    enTelefono.requestFocusInWindow();
                    etick_telefono.setVisible(true);
                    enTelefono.setEditable(true);
                    enTelefono.setText("");
                    enTelefono.setForeground(new Color(0, 0, 0));
                    dSeparador_telefono.setBackground(new Color(0, 0, 250));
                }

                break;
            case 0:
                if (enTelefono.getText().equals("")) {
                    Registro1.requestFocusInWindow();
                    etick_telefono.setVisible(false);
                    enTelefono.setEditable(false);
                    enTelefono.setText("Telefono");
                    enTelefono.setForeground(new Color(51, 51, 51));
                    dSeparador_telefono.setBackground(new Color(51, 51, 51));
                }
                break;

        }

    }

    public void DenCorreo(int i) {
        switch (i) {
            case 1:
                if (enCorreo.getText().equals("Correo")) {
                    enCorreo.requestFocusInWindow();
                    etick_correo.setVisible(true);
                    enCorreo.setEditable(true);
                    enCorreo.setText("");
                    enCorreo.setForeground(new Color(0, 0, 0));
                    dSeparador_correo.setBackground(new Color(0, 0, 250));
                }
                break;
            case 0:
                if (enCorreo.getText().equals("")) {
                    Registro1.requestFocusInWindow();
                    etick_correo.setVisible(false);
                    enCorreo.setEditable(false);
                    enCorreo.setText("Correo");
                    enCorreo.setForeground(new Color(51, 51, 51));
                    dSeparador_correo.setBackground(new Color(51, 51, 51));
                }
                break;
        }

    }

    public void DprimPanel() {
        anim.animate.animar(menu_impressD, menu_impress, menu_impressD.isVisible(), Color.WHITE, 200);
    }

    //Codigo reutilizado autor, River
    private File showFileChooser() {
        JFileChooser ch = new JFileChooser();
        int opt = ch.showSaveDialog(this);
        if (opt == JFileChooser.APPROVE_OPTION) {
            return ch.getSelectedFile();
        } else {
            return null;
        }
    }

    private void deleteP() {
        if (usuario.comprobarTipoUser("Administrador")) {
            int id = persona.getId();

            if (id != 0) {
                persona.buscar(id);
                int i = JOptionPane.showConfirmDialog(null, "Realmente estas seguro que deseas eliminar a \n" + persona.getpNombre() + " " + persona.getpApellido());
                if (i == 0) {
                    relacionesForaneas.setRolFamiliar("Jefe de Familia");
                    if (persona.getRolFamiliar() != relacionesForaneas.buscarRolFamiliar()) {
                        persona.deletePersona(persona.getId());
                        JOptionPane.showMessageDialog(null, "Persona eliminada correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se puede eliminar a esta persona por que es jefe de familia,\n primero debe de cambiar el rol familiar");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Primero tienes que seleccionar a una persona!!!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Esta opcion es solo para los usuarios,\"Administradores\"");
        }

    }

    public void mostrarMenu(boolean aux) {
        bt_menu_agregar.setVisible(aux);
        bt_menu_demografia.setVisible(aux);
        bt_menu_imprimir.setVisible(aux);
        bt_menu_modificar.setVisible(aux);
        bt_menu_gestionar.setVisible(aux);
        bt_menu_usuario.setVisible(aux);
        bt_mn_buscar.setVisible(aux);
    }

    private void informacionPersona() {
        if (persona.getId() != 0) {
            try {
                infoPersona.setVisible(true);
                infoPersona.pDetalles();
            } catch (ParseException ex) {
                Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona una persona");
        }
    }

    private void dinamic_filtroPro(JLayeredPane layer) {
        if (layer.equals(jLayeredPane6)) {
            jLayeredPane7.setVisible(false);
            jLayeredPane6.setVisible(true);

            jPanel18.setVisible(false);
            jPanel14.setVisible(false);
            jPanel16.setVisible(false);
            jPanel19.setVisible(false);
            jPanel20.setVisible(false);
            jPanel21.setVisible(false);
            jPanel22.setVisible(false);
            jPanel23.setVisible(false);
            jPanel24.setVisible(false);

            jPanel17.setVisible(true);
        } else {
            jLayeredPane7.setVisible(true);
            jLayeredPane6.setVisible(false);

            jPanel18.setVisible(true);
            jPanel14.setVisible(true);
            jPanel16.setVisible(true);
            jPanel19.setVisible(true);
            jPanel20.setVisible(true);
            jPanel21.setVisible(true);
            jPanel22.setVisible(true);
            jPanel23.setVisible(true);
            jPanel24.setVisible(true);

            jPanel17.setVisible(true);
        }

    }

    private void filtroPro() {
        Integer direccion = null;
        Integer NivelEducativo = null;
        Integer discapacidad = null;
        Integer estadoCasa = null;
        Integer tipoHabitante = null;
        Integer EdadMn = null;
        Integer EdadMx = null;

        boolean omitir_servicios = true;
        Integer agua = null;
        Integer aguaN = null;
        Integer luz = null;
        Integer moduloCLP = null;

        if (jTextField12.getText().trim().equals("") == false) {
            EdadMn = Integer.parseInt(jTextField12.getText());
        }
        if (jTextField13.getText().trim().equals("") == false) {
            EdadMx = Integer.parseInt(jTextField13.getText());
        }

        if (combo_filtroPro_ubicacion.getSelectedItem().equals("") == false) {
            modelos.demografia.setStrike((String) combo_filtroPro_ubicacion.getSelectedItem());
            direccion = modelos.demografia.idStrike();
        }
        if (combo_filtroPro_nvEdc.getSelectedItem().equals("") == false) {
            modelos.relacionesForaneas.setMgAcademico((String) combo_filtroPro_nvEdc.getSelectedItem());
            NivelEducativo = modelos.relacionesForaneas.buscarMgAcademico();
        }
        if (combo_filtroPro_tipoDiscapacidad.getSelectedItem().equals("") == false) {
            discapacidad = modelos.discapacidades.idTdd((String) combo_filtroPro_tipoDiscapacidad.getSelectedItem());
        }
        if (combo_filtroPro_estCasa.getSelectedItem().equals("") == false) {
            modelos.relacionesForaneas.setStadoCasa((String) combo_filtroPro_estCasa.getSelectedItem());
            estadoCasa = modelos.relacionesForaneas.buscarStadoCasa();
        }
        if (combo_filtroPro_rolFamiliar.getSelectedItem().equals("") == false) {
            if (jCheckBox9.isSelected() == false) {
                modelos.relacionesForaneas.setRolFamiliar((String) combo_filtroPro_rolFamiliar.getSelectedItem());
                tipoHabitante = modelos.relacionesForaneas.buscarRolFamiliar();
            }
        }

        if (jCheckBox6.isSelected() == false) {
            omitir_servicios = false;
            if (jCheckBox1.isSelected() == true) {
                agua = 1;
            } else {
                agua = 0;
            }
            if (jCheckBox2.isSelected() == true) {
                aguaN = 1;
            } else {
                aguaN = 0;
            }
            if (jCheckBox3.isSelected() == true) {
                luz = 1;
            } else {
                luz = 0;
            }
        }

        ArrayList<String[]> lista = filtros.personalizado(EdadMn, EdadMx, direccion, NivelEducativo, discapacidad, estadoCasa, tipoHabitante, omitir_servicios, agua, aguaN, luz);
        String[] colums;

        if (totalInfo_filtroPro.isSelected() == true) {
            colums = new String[]{"Cod", "Primer Nombre", "Segundo Nombre", "Primer Apellido", "Segundo Apellido", "Genero", "Fecha de Nacimiento", "Cedula", "Nivel Academico", "Nacionalidad", "Rol de familia", "Telefono", "Correo"};
            modelo = new DefaultTableModel() {
                boolean[] canEdit = new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false};

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            };
            modelo.setColumnIdentifiers(colums);

            for (String[] aux : lista) {
                modelo.addRow(new String[]{
                    aux[0],
                    aux[1],
                    aux[2],
                    aux[3],
                    aux[4],
                    aux[6],
                    aux[7],
                    aux[5],
                    aux[8],
                    aux[9],
                    aux[10],
                    aux[11],
                    aux[12]
                });
            }
        } else {
            colums = new String[]{"Cod", "Nobre y apellido", "Cedula", "Rol de familia"};
            modelo = new DefaultTableModel() {
                boolean[] canEdit = new boolean[]{false, false, false, false};

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            };
            modelo.setColumnIdentifiers(colums);

            for (String[] aux : lista) {
                modelo.addRow(new String[]{
                    aux[0],
                    aux[1] + " " + aux[2] + " " + aux[3] + " " + aux[4],
                    aux[5],
                    aux[10]
                });
            }
        }
        jTable9.setModel(modelo);
    }

    private void mostrarDestallesDd(String entrada) {
        ArrayList<String[]> lista = discapacidades.recuperarAll();
        boolean limpiar = true;
        for (String[] aux : lista) {
            System.out.println(aux[1]);
            if (aux[1].equals(entrada) == true) {
                label_registroP_detallesSs_nombre.setText("Nombre de discapacidad: " + aux[1]);
                label_registroP_detallesSs_tipo.setText("Tipo: " + aux[3]);
                label_registroP_detallesSs_Descripcion.setText("Descripción: " + aux[2]);
                limpiar = false;
            }
        }
        if (limpiar) {
            label_registroP_detallesSs_nombre.setText("Nombre de discapacidad: ");
            label_registroP_detallesSs_tipo.setText("Tipo: ");
            label_registroP_detallesSs_Descripcion.setText("Descripción: ");
        }
    }

    private void dinamic_bt_demografia(JLabel aux) {
        if (aux != null) {
            bt_dmg_addLider.setForeground(nulo);
            bt_dmg_estruc.setForeground(nulo);
            bt_dmg_addStrike.setForeground(nulo);
            bt_dmg_stingStrike.setForeground(nulo);

            if (aux.equals(bt_dmg_estruc)) {
                bt_dmg_addLider.setForeground(disponible);
                bt_dmg_guardar.setVisible(false);
                bt_dmg_descartar.setVisible(true);

                ArrayList<String[]> lista = demografia.rescuAll();
                modelo = new DefaultTableModel() {
                    boolean[] canEdit = new boolean[]{
                        false
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit[columnIndex];
                    }
                };
                String[] colums = {""};
                modelo.setColumnIdentifiers(colums);
                for (String[] ax : lista) {
                    modelo.addRow(new String[]{ax[0]});
                    modelo.addRow(new String[]{ax[1] + " " + ax[2] + " " + ax[3] + " " + ax[4] + ", CI " + ax[5]});
                    modelo.addRow(new String[]{" "});
                }
                jTable3.setModel(modelo);

            }

            if (aux.equals(bt_dmg_stingStrike) == true || aux.equals(bt_dmg_addStrike)) {
                entradaTxt_demografia.setEditable(true);
                entradaTxt_demografia.setText("");

                bt_dmg_guardar.setVisible(true);
                bt_dmg_descartar.setVisible(true);

            } else {
                entradaTxt_demografia.setEditable(false);
                entradaTxt_demografia.setText("");
            }

            aux.setForeground(seleccionado);
        } else {
            bt_dmg_addLider.setForeground(nulo);
            bt_dmg_estruc.setForeground(disponible);
            bt_dmg_addStrike.setForeground(disponible);
            bt_dmg_stingStrike.setForeground(disponible);

            bt_dmg_guardar.setVisible(false);
            bt_dmg_descartar.setVisible(false);
            entradaTxt_demografia.setEditable(false);
            entradaTxt_demografia.setText("");
        }
    }
}

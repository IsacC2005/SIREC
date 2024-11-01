/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import modelos.usuario;

/**
 *
 * @author isacc
 */
public class login extends javax.swing.JFrame {

    /**
     * Creates new form login
     */
    int contador = 0;
    int cronometro = 0;
    Timer timer;
    boolean estadoContador = true;

    principal principal = new principal();

    int xMause, yMause, x, y;
    int aux = 0;
    int aux1 = 0;
    int pocicionCemilla = 0;
    ImageIcon imFormas;
    ImageIcon imFrutas;
    ImageIcon imDispositivos;
    ImageIcon imVehiculos;

    int forma, vehicul, fruta, dispocitivo;

    static ArrayList<String> formas = new ArrayList<>();
    static ArrayList<String> frutas = new ArrayList<>();
    static ArrayList<String> vehiculo = new ArrayList<>();
    static ArrayList<String> dispositivos = new ArrayList<>();

    static int tRecuperacion = 0;

    public static void Cemilla() {
        formas.add("/recursos/seguridad/formas/fm1.png");
        formas.add("/recursos/seguridad/formas/fm2.png");
        formas.add("/recursos/seguridad/formas/fm3.png");
        formas.add("/recursos/seguridad/formas/fm4.png");
        formas.add("/recursos/seguridad/formas/fm5.png");
        formas.add("/recursos/seguridad/formas/fm6.png");
        formas.add("/recursos/seguridad/formas/fm7.png");
        formas.add("/recursos/seguridad/formas/fm8.png");
        formas.add("/recursos/seguridad/formas/fm9.png");
        formas.add("/recursos/seguridad/formas/fm10.png");
        formas.add("/recursos/seguridad/formas/fm11.png");

        frutas.add("/recursos/seguridad/frutas/fm1.png");
        frutas.add("/recursos/seguridad/frutas/fm2.png");
        frutas.add("/recursos/seguridad/frutas/fm3.png");
        frutas.add("/recursos/seguridad/frutas/fm4.png");
        frutas.add("/recursos/seguridad/frutas/fm5.png");
        frutas.add("/recursos/seguridad/frutas/fm6.png");
        frutas.add("/recursos/seguridad/frutas/fm7.png");
        frutas.add("/recursos/seguridad/frutas/fm8.png");
        frutas.add("/recursos/seguridad/frutas/fm9.png");
        frutas.add("/recursos/seguridad/frutas/fm10.png");
        frutas.add("/recursos/seguridad/frutas/fm11.png");

        vehiculo.add("/recursos/seguridad/vehiculo/fm1.png");
        vehiculo.add("/recursos/seguridad/vehiculo/fm2.png");
        vehiculo.add("/recursos/seguridad/vehiculo/fm3.png");
        vehiculo.add("/recursos/seguridad/vehiculo/fm4.png");
        vehiculo.add("/recursos/seguridad/vehiculo/fm5.png");
        vehiculo.add("/recursos/seguridad/vehiculo/fm6.png");
        vehiculo.add("/recursos/seguridad/vehiculo/fm7.png");
        vehiculo.add("/recursos/seguridad/vehiculo/fm8.png");
        vehiculo.add("/recursos/seguridad/vehiculo/fm9.png");
        vehiculo.add("/recursos/seguridad/vehiculo/fm10.png");
        vehiculo.add("/recursos/seguridad/vehiculo/fm11.png");

        dispositivos.add("/recursos/seguridad/dispositivos/fm1.png");
        dispositivos.add("/recursos/seguridad/dispositivos/fm2.png");
        dispositivos.add("/recursos/seguridad/dispositivos/fm3.png");
        dispositivos.add("/recursos/seguridad/dispositivos/fm4.png");
        dispositivos.add("/recursos/seguridad/dispositivos/fm5.png");
        dispositivos.add("/recursos/seguridad/dispositivos/fm6.png");
        dispositivos.add("/recursos/seguridad/dispositivos/fm7.png");
        dispositivos.add("/recursos/seguridad/dispositivos/fm8.png");
        dispositivos.add("/recursos/seguridad/dispositivos/fm9.png");
        dispositivos.add("/recursos/seguridad/dispositivos/fm10.png");
        dispositivos.add("/recursos/seguridad/dispositivos/fm11.png");
        /*("/recursos/seguridad/formas/fm1.png", "/recursos/seguridad/formas/fm1s.png");
        formas.put("/recursos/seguridad/formas/fm2.png", "/recursos/seguridad/formas/fm2s.png");
        formas.put("/recursos/seguridad/formas/fm3.png", "/recursos/seguridad/formas/fm3s.png");
        formas.put("/recursos/seguridad/formas/fm4.png", "/recursos/seguridad/formas/fm4s.png");
        formas.put("/recursos/seguridad/formas/fm5.png", "/recursos/seguridad/formas/fm5s.png");
        formas.put("/recursos/seguridad/formas/fm6.png", "/recursos/seguridad/formas/fm6s.png");
        formas.put("/recursos/seguridad/formas/fm7.png", "/recursos/seguridad/formas/fm7s.png");
        formas.put("/recursos/seguridad/formas/fm8.png", "/recursos/seguridad/formas/fm8s.png");
        formas.put("/recursos/seguridad/formas/fm9.png", "/recursos/seguridad/formas/fm9s.png");
        formas.put("/recursos/seguridad/formas/fm10.png", "/recursos/seguridad/formas/fm10s.png");
        formas.put("/recursos/seguridad/formas/fm11.png", "/recursos/seguridad/formas/fm11s.png");

        frutas.put("/recursos/seguridad/frutas/fm1.png", "/recursos/seguridad/frutas/fm1s.png");
        frutas.put("/recursos/seguridad/frutas/fm2.png", "/recursos/seguridad/frutas/fm2s.png");
        frutas.put("/recursos/seguridad/frutas/fm3.png", "/recursos/seguridad/frutas/fm3s.png");
        frutas.put("/recursos/seguridad/frutas/fm4.png", "/recursos/seguridad/frutas/fm4s.png");
        frutas.put("/recursos/seguridad/frutas/fm5.png", "/recursos/seguridad/frutas/fm5s.png");
        frutas.put("/recursos/seguridad/frutas/fm6.png", "/recursos/seguridad/frutas/fm6s.png");
        frutas.put("/recursos/seguridad/frutas/fm7.png", "/recursos/seguridad/frutas/fm7s.png");
        frutas.put("/recursos/seguridad/frutas/fm8.png", "/recursos/seguridad/frutas/fm8s.png");
        frutas.put("/recursos/seguridad/frutas/fm9.png", "/recursos/seguridad/frutas/fm9s.png");
        frutas.put("/recursos/seguridad/frutas/fm10.png", "/recursos/seguridad/frutas/fm10s.png");
        frutas.put("/recursos/seguridad/frutas/fm11.png", "/recursos/seguridad/frutas/fm11s.png");

        vehiculo.put("/recursos/seguridad/vehiculo/fm1.png", "/recursos/seguridad/vehiculo/fm1s.png");
        vehiculo.put("/recursos/seguridad/vehiculo/fm2.png", "/recursos/seguridad/vehiculo/fm2s.png");
        vehiculo.put("/recursos/seguridad/vehiculo/fm3.png", "/recursos/seguridad/vehiculo/fm3s.png");
        vehiculo.put("/recursos/seguridad/vehiculo/fm4.png", "/recursos/seguridad/vehiculo/fm4s.png");
        vehiculo.put("/recursos/seguridad/vehiculo/fm5.png", "/recursos/seguridad/vehiculo/fm5s.png");
        vehiculo.put("/recursos/seguridad/vehiculo/fm6.png", "/recursos/seguridad/vehiculo/fm6s.png");
        vehiculo.put("/recursos/seguridad/vehiculo/fm7.png", "/recursos/seguridad/vehiculo/fm7s.png");
        vehiculo.put("/recursos/seguridad/vehiculo/fm8.png", "/recursos/seguridad/vehiculo/fm8s.png");
        vehiculo.put("/recursos/seguridad/vehiculo/fm9.png", "/recursos/seguridad/vehiculo/fm9s.png");
        vehiculo.put("/recursos/seguridad/vehiculo/fm10.png", "/recursos/seguridad/vehiculo/fm10s.png");
        vehiculo.put("/recursos/seguridad/vehiculo/fm11.png", "/recursos/seguridad/vehiculo/fm11s.png");

        dispositivos.put("/recursos/seguridad/dispositivos/fm1.png", "/recursos/seguridad/dispositivos/fm1s.png");
        dispositivos.put("/recursos/seguridad/dispositivos/fm2.png", "/recursos/seguridad/dispositivos/fm2s.png");
        dispositivos.put("/recursos/seguridad/dispositivos/fm3.png", "/recursos/seguridad/dispositivos/fm3s.png");
        dispositivos.put("/recursos/seguridad/dispositivos/fm4.png", "/recursos/seguridad/dispositivos/fm4s.png");
        dispositivos.put("/recursos/seguridad/dispositivos/fm5.png", "/recursos/seguridad/dispositivos/fm5s.png");
        dispositivos.put("/recursos/seguridad/dispositivos/fm6.png", "/recursos/seguridad/dispositivos/fm6s.png");
        dispositivos.put("/recursos/seguridad/dispositivos/fm7.png", "/recursos/seguridad/dispositivos/fm7s.png");
        dispositivos.put("/recursos/seguridad/dispositivos/fm8.png", "/recursos/seguridad/dispositivos/fm8s.png");
        dispositivos.put("/recursos/seguridad/dispositivos/fm9.png", "/recursos/seguridad/dispositivos/fm9s.png");
        dispositivos.put("/recursos/seguridad/dispositivos/fm10.png", "/recursos/seguridad/dispositivos/fm10s.png");
        dispositivos.put("/recursos/seguridad/dispositivos/fm11.png", "/recursos/seguridad/dispositivos/fm11s.png");*/

    }

    public void setPrincipal(principal principal) {
        this.principal = principal;
    }

    public login() {
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/recursos/logoSinFondo110x110.png")).getImage());
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_base = new javax.swing.JPanel();
        panelLogin = new javax.swing.JPanel();
        panelDegradadoAnimado1 = new Interfaz.PanelDegradadoAnimado();
        jLabel33 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        enUsuarioLogin = new javax.swing.JTextField();
        enPasworLogin = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        mensajePasworErr = new javax.swing.JLabel();
        mensajeUsuarioErr = new javax.swing.JLabel();
        sPsw = new javax.swing.JSeparator();
        sUsuario = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        clockDigital1 = new elaprendiz.gui.varios.ClockDigital();
        panelAgregar = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        panelCreacion = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jPasswordField2 = new javax.swing.JPasswordField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel22 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        panelDegradadoAnimado4 = new Interfaz.PanelDegradadoAnimado();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        panelCrearSeguridad = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jTextField1 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        panelDegradadoAnimado5 = new Interfaz.PanelDegradadoAnimado();
        jLabel42 = new javax.swing.JLabel();
        jTextArea4 = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();
        panleModificar = new javax.swing.JPanel();
        panelDegradadoAnimado3 = new Interfaz.PanelDegradadoAnimado();
        cAnl = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        uAnl = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        enPasworLoginM = new javax.swing.JPasswordField();
        jLabel23 = new javax.swing.JLabel();
        mensajePasworErr1 = new javax.swing.JLabel();
        mensajeUsuarioErr1 = new javax.swing.JLabel();
        enUsuarioLoginM = new javax.swing.JTextField();
        dEnUsuario = new javax.swing.JSeparator();
        sPsw1 = new javax.swing.JSeparator();
        jLabel32 = new javax.swing.JLabel();
        panelRecuperar = new javax.swing.JPanel();
        panelDegradadoAnimado2 = new Interfaz.PanelDegradadoAnimado();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel37 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        preguntaSeguridad = new javax.swing.JLayeredPane();
        jLabel44 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jTextArea2 = new javax.swing.JTextArea();
        jPanel8 = new javax.swing.JPanel();
        bUsuario = new javax.swing.JLayeredPane();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jTextArea3 = new javax.swing.JTextArea();
        jPanel7 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        cemilla = new javax.swing.JLayeredPane();
        fCemilla = new javax.swing.JLayeredPane();
        fm10 = new javax.swing.JLabel();
        fm9 = new javax.swing.JLabel();
        fm2 = new javax.swing.JLabel();
        fm8 = new javax.swing.JLabel();
        fm4 = new javax.swing.JLabel();
        fm3 = new javax.swing.JLabel();
        fm1 = new javax.swing.JLabel();
        fm5 = new javax.swing.JLabel();
        fm7 = new javax.swing.JLabel();
        fm6 = new javax.swing.JLabel();
        fm11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        mCemilla = new javax.swing.JLayeredPane();
        fm12 = new javax.swing.JLabel();
        fm13 = new javax.swing.JLabel();
        fm14 = new javax.swing.JLabel();
        fm15 = new javax.swing.JLabel();
        panelCurves1 = new elaprendiz.gui.panel.PanelCurves();
        jLabel45 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        getContentPane().setLayout(new java.awt.CardLayout());

        this.setTitle("Inicio ;))");
        panel_base.setLayout(new java.awt.CardLayout());

        panelLogin.setBackground(new java.awt.Color(245, 245, 245));
        panelLogin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(107, 109, 143)));
        panelLogin.setForeground(new java.awt.Color(153, 153, 153));
        panelLogin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelDegradadoAnimado1.setColorInicial(new java.awt.Color(47,135,203));
        panelDegradadoAnimado1.setColorFinal(new java.awt.Color(153,0, 204));
        panelDegradadoAnimado1.setMncFinal(new java.awt.Color(204, 0, 153));
        panelDegradadoAnimado1.setMncInicial(new java.awt.Color(47, 203, 193));
        //panelDegradadoAnimado1.stopAnimation();
        panelDegradadoAnimado1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/configuraciones-de-la-cuenta.png"))); // NOI18N
        jLabel33.setText("Modificar");
        jLabel33.setToolTipText("");
        jLabel33.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel33.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel33MouseClicked(evt);
            }
        });
        panelDegradadoAnimado1.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 60, 50));

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Login");
        panelDegradadoAnimado1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 93, 140, -1));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/usuarioA.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel6MouseExited(evt);
            }
        });
        panelDegradadoAnimado1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 12, 140, -1));

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/usuario(1).png"))); // NOI18N
        jLabel1.setText("Usuario");
        panelDegradadoAnimado1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 161, 140, 30));

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cerrar.png"))); // NOI18N
        jLabel2.setText("Contraseña");
        panelDegradadoAnimado1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 228, 140, 30));

        panelLogin.add(panelDegradadoAnimado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 360));

        enUsuarioLogin.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        enUsuarioLogin.setForeground(new java.awt.Color(102, 102, 102));
        enUsuarioLogin.setText("Ingrese su usuario");
        enUsuarioLogin.setBorder(null);
        enUsuarioLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        enUsuarioLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                enUsuarioLoginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                enUsuarioLoginMouseExited(evt);
            }
        });
        enUsuarioLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enUsuarioLoginActionPerformed(evt);
            }
        });
        enUsuarioLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                enUsuarioLoginKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                enUsuarioLoginKeyTyped(evt);
            }
        });
        enUsuarioLogin.setEditable(false);
        panelLogin.add(enUsuarioLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 270, 30));

        enPasworLogin.setForeground(new java.awt.Color(102, 102, 102));
        enPasworLogin.setText("Ingrese su contraseña");
        enPasworLogin.setBorder(null);
        enPasworLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                enPasworLoginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                enPasworLoginMouseExited(evt);
            }
        });
        enPasworLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enPasworLoginActionPerformed(evt);
            }
        });
        enPasworLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                enPasworLoginKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                enPasworLoginKeyTyped(evt);
            }
        });
        panelLogin.add(enPasworLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 270, 30));

        jLabel5.setBackground(new java.awt.Color(204, 204, 204));
        jLabel5.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel5.setText(" Iniciar Seción");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel5MouseExited(evt);
            }
        });
        panelLogin.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 310, 130, 50));

        jLabel7.setFont(new java.awt.Font("Roboto Medium", 2, 12)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/mostrar.png"))); // NOI18N
        jLabel7.setText("Mostra");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel7MouseExited(evt);
            }
        });
        panelLogin.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, -1, 20));

        mensajePasworErr.setFont(new java.awt.Font("Roboto Light", 2, 12)); // NOI18N
        mensajePasworErr.setForeground(new java.awt.Color(200, 23, 45));
        mensajePasworErr.setText("contraseña incorrecta");
        mensajePasworErr.setVisible(false);
        panelLogin.add(mensajePasworErr, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, -1, 20));

        mensajeUsuarioErr.setFont(new java.awt.Font("Roboto Light", 2, 12)); // NOI18N
        mensajeUsuarioErr.setForeground(new java.awt.Color(200, 23, 45));
        mensajeUsuarioErr.setText("usuario incorrecto");
        mensajeUsuarioErr.setVisible(false);
        panelLogin.add(mensajeUsuarioErr, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, -1, 20));

        sPsw.setBackground(new java.awt.Color(153, 153, 153));
        sPsw.setOpaque(true);
        panelLogin.add(sPsw, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, 270, -1));

        sUsuario.setBackground(new java.awt.Color(153, 153, 153));
        sUsuario.setOpaque(true);
        panelLogin.add(sUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 270, -1));

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 51, 51));
        jLabel8.setText("Salir");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel8MouseExited(evt);
            }
        });
        panelLogin.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, -1, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/logoSinFondo110x110.png"))); // NOI18N
        jLabel9.setText("jLabel9");
        panelLogin.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 110, 110));

        jLabel4.setText("recuperar inicio");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        panelLogin.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 270, -1, -1));

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 51, 51));
        jLabel50.setText("00");
        jLabel50.setVisible(false);
        panelLogin.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, -1, -1));

        clockDigital1.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 10)); // NOI18N
        panelLogin.add(clockDigital1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 130, -1, -1));

        panel_base.add(panelLogin, "card4");

        panelAgregar.setBackground(new java.awt.Color(245, 245, 245));
        panelAgregar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(107, 109, 143)));
        panelAgregar.setVisible(false);
        panelAgregar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/logoSinFondo110x110.png"))); // NOI18N
        jLabel16.setText("jLabel9");
        panelAgregar.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 110, 110));

        panelCreacion.setBackground(new java.awt.Color(245, 245, 245));
        panelCreacion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField2.setText("Nuevo usuario");
        jTextField2.setBorder(null);
        jTextField2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTextField2MouseExited(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });
        panelCreacion.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 270, 30));

        jPasswordField2.setText("Ingrese su contraseña");
        jPasswordField2.setBorder(null);
        jPasswordField2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPasswordField2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPasswordField2MouseExited(evt);
            }
        });
        jPasswordField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jPasswordField2KeyTyped(evt);
            }
        });
        panelCreacion.add(jPasswordField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 270, 30));

        jSeparator2.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator2.setOpaque(true);
        panelCreacion.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 192, 270, -1));

        jLabel20.setText("Siguiente");
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });
        panelCreacion.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 320, 80, 30));

        jSeparator1.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator1.setOpaque(true);
        panelCreacion.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 252, 270, -1));

        jLabel22.setFont(new java.awt.Font("Dialog", 2, 10)); // NOI18N
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/mostrar.png"))); // NOI18N
        jLabel22.setText("Mostrar");
        jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel22MouseClicked(evt);
            }
        });
        panelCreacion.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 270, -1, -1));

        jLabel21.setText("volver al inicio");
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });
        panelCreacion.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 324, 70, 30));

        jLabel35.setText("Tipo de Usuario");
        panelCreacion.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, -1, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccion", "_______________________________________________________", "Administrador", "Basico" }));
        jComboBox2.setBorder(null);
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        panelCreacion.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 200, -1));

        panelDegradadoAnimado4.setColorInicial(new java.awt.Color(47,135,203));
        panelDegradadoAnimado4.setColorFinal(new java.awt.Color(153,0, 204));
        panelDegradadoAnimado4.setMncFinal(new java.awt.Color(204, 0, 153));
        panelDegradadoAnimado4.setMncInicial(new java.awt.Color(47, 203, 193));
        panelDegradadoAnimado4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cerrar.png"))); // NOI18N
        jLabel12.setText("Contraseña");
        panelDegradadoAnimado4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 226, 140, -1));

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/agregar-usuario(1).png"))); // NOI18N
        panelDegradadoAnimado4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 140, -1));

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Agregar");
        panelDegradadoAnimado4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 87, 140, -1));

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/usuario(1).png"))); // NOI18N
        jLabel13.setText("Usuario ");
        panelDegradadoAnimado4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(-3, 161, 140, -1));

        panelCreacion.add(panelDegradadoAnimado4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 360));

        panelAgregar.add(panelCreacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 360));

        panelCrearSeguridad.setBackground(new java.awt.Color(245, 245, 245));
        panelCrearSeguridad.setVisible(false);
        panelCrearSeguridad.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel38.setText("Crear Cemilla de recuperacion");
        panelCrearSeguridad.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, -1, 20));

        jLabel39.setBackground(new java.awt.Color(153, 153, 153));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/seguridad/atras-en-el-tiempo.png"))); // NOI18N
        jLabel39.setOpaque(true);
        jLabel39.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel39MouseClicked(evt);
            }
        });
        panelCrearSeguridad.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 50, 40));

        jCheckBox1.setText("Crear pregunta de segurida");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        panelCrearSeguridad.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, -1, -1));

        jTextField1.setBorder(null);
        jTextField1.setVisible(false);
        panelCrearSeguridad.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, 210, 20));

        jTextField3.setBorder(null);
        jTextField3.setVisible(false);
        panelCrearSeguridad.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, 210, 20));

        jButton3.setText("Guardar");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        panelCrearSeguridad.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 320, -1, -1));

        jLabel40.setText("Pregunta");
        jLabel40.setVisible(false);
        panelCrearSeguridad.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, -1, 20));

        jLabel41.setText("Respuesta");
        jLabel41.setVisible(false);
        panelCrearSeguridad.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, -1, 20));

        panelDegradadoAnimado5.setColorInicial(new java.awt.Color(47,135,203));
        panelDegradadoAnimado5.setColorFinal(new java.awt.Color(153,0, 204));
        panelDegradadoAnimado5.setMncFinal(new java.awt.Color(204, 0, 153));
        panelDegradadoAnimado5.setMncInicial(new java.awt.Color(47, 203, 193));
        panelDegradadoAnimado5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/agregar-usuario(1).png"))); // NOI18N
        panelDegradadoAnimado5.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 140, -1));

        jTextArea4.setEditable(false);
        jTextArea4.setBackground(new Color(0,0,0,0));
        jTextArea4.setColumns(20);
        jTextArea4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextArea4.setRows(5);
        jTextArea4.setText("    Estas son las \nopciones disponibles \npararecuperar el \nusuario en caso de \nnecesitar restablecer \nla contraseña.\n   La semilla de usuario \nes obligatoria para \npoder terminar de \ncrear al usuario y las \npreguntas de \nseguridad son \nopcionales\n\n");
        jTextArea4.setBorder(null);
        panelDegradadoAnimado5.add(jTextArea4, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 110, 130, 230));

        panelCrearSeguridad.add(panelDegradadoAnimado5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 360));

        jButton4.setText("Atras");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        panelCrearSeguridad.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, -1, -1));

        panelAgregar.add(panelCrearSeguridad, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 360));

        panel_base.add(panelAgregar, "card3");

        panleModificar.setBackground(new java.awt.Color(245, 245, 245));
        panleModificar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(107, 109, 143)));
        panleModificar.setPreferredSize(new java.awt.Dimension(490, 360));
        panleModificar.setVisible(false);
        panleModificar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelDegradadoAnimado3.setColorInicial(new java.awt.Color(47,135,203));
        panelDegradadoAnimado3.setColorFinal(new java.awt.Color(153,0, 204));
        panelDegradadoAnimado3.setMncFinal(new java.awt.Color(204, 0, 153));
        panelDegradadoAnimado3.setMncInicial(new java.awt.Color(47, 203, 193));
        panelDegradadoAnimado3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cAnl.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        cAnl.setText("anterior");
        panelDegradadoAnimado3.add(cAnl, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 271, -1, -1));

        jLabel25.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cerrar.png"))); // NOI18N
        jLabel25.setText("Contraseña");
        panelDegradadoAnimado3.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 244, 140, -1));

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Modificar");
        panelDegradadoAnimado3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 140, -1));

        uAnl.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        uAnl.setText("anterior");
        panelDegradadoAnimado3.add(uAnl, new org.netbeans.lib.awtextra.AbsoluteConstraints(57, 191, -1, -1));

        jLabel24.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/usuario(1).png"))); // NOI18N
        jLabel24.setText("Usuario");
        panelDegradadoAnimado3.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 164, 140, -1));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/modificarUsuario 64x64.png"))); // NOI18N
        panelDegradadoAnimado3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 6, 140, 80));

        panleModificar.add(panelDegradadoAnimado3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 360));

        jLabel18.setFont(new java.awt.Font("Roboto", 1, 16));
        jLabel18.setForeground(new java.awt.Color(255, 51, 51));
        jLabel18.setText("Regresar");
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
        });
        panleModificar.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 314, 90, 30));

        jLabel19.setFont(new java.awt.Font("Roboto", 1, 16));
        jLabel19.setText("Continuar");
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });
        panleModificar.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 320, 110, 30));

        enPasworLoginM.setForeground(new java.awt.Color(102, 102, 102));
        enPasworLoginM.setText("Ingresar contraseña");
        enPasworLoginM.setBorder(null);
        enPasworLoginM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                enPasworLoginMMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                enPasworLoginMMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                enPasworLoginMMousePressed(evt);
            }
        });
        enPasworLoginM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enPasworLoginMActionPerformed(evt);
            }
        });
        enPasworLoginM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                enPasworLoginMKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                enPasworLoginMKeyTyped(evt);
            }
        });
        panleModificar.add(enPasworLoginM, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, 270, 30));

        jLabel23.setFont(new java.awt.Font("Roboto Medium", 2, 12)); // NOI18N
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/mostrar.png"))); // NOI18N
        jLabel23.setText("Mostra");
        jLabel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel23MouseClicked(evt);
            }
        });
        panleModificar.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, -1, 20));

        mensajePasworErr1.setFont(new java.awt.Font("Roboto Light", 2, 12)); // NOI18N
        mensajePasworErr1.setForeground(new java.awt.Color(200, 23, 45));
        mensajePasworErr1.setText("contraseña incorrecta");
        mensajePasworErr1.setVisible(false);
        panleModificar.add(mensajePasworErr1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, -1, 20));

        mensajeUsuarioErr1.setFont(new java.awt.Font("Roboto Light", 2, 12)); // NOI18N
        mensajeUsuarioErr1.setForeground(new java.awt.Color(200, 23, 45));
        mensajeUsuarioErr1.setText("usuario incorrecto");
        mensajeUsuarioErr1.setVisible(false);
        panleModificar.add(mensajeUsuarioErr1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, -1, 20));

        enUsuarioLoginM.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        enUsuarioLoginM.setForeground(new java.awt.Color(102, 102, 102));
        enUsuarioLoginM.setText("Ingresar usuario");
        enUsuarioLoginM.setBorder(null);
        enUsuarioLoginM.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        enUsuarioLoginM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                enUsuarioLoginMMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                enUsuarioLoginMMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                enUsuarioLoginMMousePressed(evt);
            }
        });
        enUsuarioLoginM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                enUsuarioLoginMKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                enUsuarioLoginMKeyTyped(evt);
            }
        });
        panleModificar.add(enUsuarioLoginM, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, 270, 30));

        dEnUsuario.setBackground(new java.awt.Color(153, 153, 153));
        dEnUsuario.setOpaque(true);
        panleModificar.add(dEnUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 190, 270, -1));

        sPsw1.setBackground(new java.awt.Color(153, 153, 153));
        sPsw1.setOpaque(true);
        panleModificar.add(sPsw1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, 270, -1));

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/logoSinFondo110x110.png"))); // NOI18N
        jLabel32.setText("jLabel9");
        panleModificar.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 110, 110));

        panel_base.add(panleModificar, "card5");

        panelRecuperar.setBackground(new java.awt.Color(245, 245, 245));
        panelRecuperar.setPreferredSize(new java.awt.Dimension(493, 360));
        panelRecuperar.setVisible(false);
        panelRecuperar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelDegradadoAnimado2.setColorInicial(new java.awt.Color(47,135,203));
        panelDegradadoAnimado2.setColorFinal(new java.awt.Color(153,0, 204));
        panelDegradadoAnimado2.setMncFinal(new java.awt.Color(204, 0, 153));
        panelDegradadoAnimado2.setMncInicial(new java.awt.Color(47, 203, 193));
        panelDegradadoAnimado2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new Color(0,0,0,0));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText("Para recuperar el \nusuario puede \nrecuperarlo usando\nla pregunta de \nseguridad sí creo \nuna, o utilizar la\nsemilla de usuario\npara restablecer la\ncontraseña del \nusuario\n");
        jTextArea1.setBorder(null);
        panelDegradadoAnimado2.add(jTextArea1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 120, 160));

        jLabel37.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("de");
        panelDegradadoAnimado2.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 118, 20));

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel49.setText("Volver");
        jLabel49.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel49MouseClicked(evt);
            }
        });
        panelDegradadoAnimado2.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, -1, -1));

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/usuario.png"))); // NOI18N
        panelDegradadoAnimado2.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 12, 138, -1));

        jLabel36.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("Semilla");
        panelDegradadoAnimado2.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 118, 20));

        jLabel28.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Recuperación ");
        panelDegradadoAnimado2.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 140, 20));

        panelRecuperar.add(panelDegradadoAnimado2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 360));

        preguntaSeguridad.setVisible(false);
        preguntaSeguridad.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel44.setText("Respuesta");
        preguntaSeguridad.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 150, 20));

        jTextField4.setBorder(null);
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
        preguntaSeguridad.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 190, 20));

        jSeparator3.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator3.setOpaque(true);
        preguntaSeguridad.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 209, 186, 2));
        jSeparator3.getAccessibleContext().setAccessibleName("");

        jLabel47.setText("Pregunta de seguridad ");
        preguntaSeguridad.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 150, 20));

        jLabel48.setText("Aqui va la pregunta???");
        preguntaSeguridad.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, 20));

        jButton2.setText("Verificar");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        preguntaSeguridad.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 300, -1, -1));

        jTextArea2.setBackground(new Color (0,0,0,0));
        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextArea2.setRows(5);
        jTextArea2.setText("Debes de responder correctamente la pregunta \nde seguridad para poder restablecer la contraseña\n");
        jTextArea2.setBorder(null);
        preguntaSeguridad.add(jTextArea2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 280, 40));

        jPanel8.setBackground(new Color(255,255,255,150));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 255, 255)));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 248, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 148, Short.MAX_VALUE)
        );

        preguntaSeguridad.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 250, 150));

        panelRecuperar.add(preguntaSeguridad, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 350, 360));

        bUsuario.setVisible(false);
        bUsuario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(listaUsuarios()));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        bUsuario.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 240, -1));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setText("Pregunta de seguridad");
        jLabel26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel26MouseClicked(evt);
            }
        });
        bUsuario.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, -1, -1));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setText("Cemilla de usuario");
        jLabel27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel27MouseClicked(evt);
            }
        });
        bUsuario.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 255, -1, -1));

        jTextArea3.setEditable(false);
        jTextArea3.setBackground(new Color(0,0,0,0));
        jTextArea3.setColumns(20);
        jTextArea3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextArea3.setRows(5);
        jTextArea3.setText("Seleccione el usuario que necesita recuperar\ny después seleccione una de las opciones de\nrecuperation\n");
        jTextArea3.setBorder(null);
        bUsuario.add(jTextArea3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 260, 50));

        jPanel7.setBackground(new Color (255,255,255,100));

        jLabel17.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        jLabel17.setText("Opciones de Recuperacion");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addContainerGap(130, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel17)
                .addGap(0, 87, Short.MAX_VALUE))
        );

        bUsuario.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 260, 100));

        panelRecuperar.add(bUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 350, 360));

        cemilla.setVisible(false);
        cemilla.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fCemilla.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fm10.setBackground(new java.awt.Color(153, 255, 255));
        fm10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fm10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/seguridad/formas/fm10.png"))); // NOI18N
        fm10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fm10.setOpaque(true);
        fm10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fm10MouseClicked(evt);
            }
        });
        fCemilla.add(fm10, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 40, 40));

        fm9.setBackground(new java.awt.Color(153, 255, 255));
        fm9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fm9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/seguridad/formas/fm9.png"))); // NOI18N
        fm9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fm9.setOpaque(true);
        fm9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fm9MouseClicked(evt);
            }
        });
        fCemilla.add(fm9, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 40, 40));

        fm2.setBackground(new java.awt.Color(153, 255, 255));
        fm2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fm2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/seguridad/formas/fm2.png"))); // NOI18N
        fm2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fm2.setOpaque(true);
        fm2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fm2MouseClicked(evt);
            }
        });
        fCemilla.add(fm2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 40, 40));

        fm8.setBackground(new java.awt.Color(153, 255, 255));
        fm8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fm8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/seguridad/formas/fm8.png"))); // NOI18N
        fm8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fm8.setOpaque(true);
        fm8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fm8MouseClicked(evt);
            }
        });
        fCemilla.add(fm8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 40, 40));

        fm4.setBackground(new java.awt.Color(153, 255, 255));
        fm4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fm4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/seguridad/formas/fm4.png"))); // NOI18N
        fm4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fm4.setOpaque(true);
        fm4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fm4MouseClicked(evt);
            }
        });
        fCemilla.add(fm4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 40, 40));

        fm3.setBackground(new java.awt.Color(153, 255, 255));
        fm3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fm3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/seguridad/formas/fm3.png"))); // NOI18N
        fm3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fm3.setOpaque(true);
        fm3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fm3MouseClicked(evt);
            }
        });
        fCemilla.add(fm3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 40, 40));

        fm1.setBackground(new java.awt.Color(153, 255, 255));
        fm1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fm1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/seguridad/formas/fm1.png"))); // NOI18N
        fm1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fm1.setOpaque(true);
        fm1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fm1MouseClicked(evt);
            }
        });
        fCemilla.add(fm1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 40, 40));

        fm5.setBackground(new java.awt.Color(153, 255, 255));
        fm5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fm5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/seguridad/formas/fm5.png"))); // NOI18N
        fm5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fm5.setOpaque(true);
        fm5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fm5MouseClicked(evt);
            }
        });
        fCemilla.add(fm5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 40, 40));

        fm7.setBackground(new java.awt.Color(153, 255, 255));
        fm7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fm7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/seguridad/formas/fm7.png"))); // NOI18N
        fm7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fm7.setOpaque(true);
        fm7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fm7MouseClicked(evt);
            }
        });
        fCemilla.add(fm7, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 40, 40));

        fm6.setBackground(new java.awt.Color(153, 255, 255));
        fm6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fm6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/seguridad/formas/fm6.png"))); // NOI18N
        fm6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fm6.setOpaque(true);
        fm6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fm6MouseClicked(evt);
            }
        });
        fCemilla.add(fm6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 40, 40));

        fm11.setBackground(new java.awt.Color(153, 255, 255));
        fm11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fm11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/seguridad/formas/fm11.png"))); // NOI18N
        fm11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fm11.setOpaque(true);
        fm11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fm11MouseClicked(evt);
            }
        });
        fCemilla.add(fm11, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, 40, 40));

        jButton1.setText("Siguiente");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        fCemilla.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, -1, -1));

        cemilla.add(fCemilla, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 60, 330, 290));

        jPanel1.setBackground(new Color(255,255,255,50));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 320, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );

        cemilla.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 60, 320, 230));

        mCemilla.setVisible(false);
        mCemilla.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fm12.setBackground(new java.awt.Color(153, 255, 255));
        fm12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fm12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/seguridad/formas/fm2.png"))); // NOI18N
        fm12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fm12.setOpaque(true);
        fm12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fm12MouseClicked(evt);
            }
        });
        mCemilla.add(fm12, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 40, 40));

        fm13.setBackground(new java.awt.Color(153, 255, 255));
        fm13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fm13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/seguridad/formas/fm3.png"))); // NOI18N
        fm13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fm13.setOpaque(true);
        fm13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fm13MouseClicked(evt);
            }
        });
        mCemilla.add(fm13, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, 40, 40));

        fm14.setBackground(new java.awt.Color(153, 255, 255));
        fm14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fm14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/seguridad/formas/fm1.png"))); // NOI18N
        fm14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fm14.setOpaque(true);
        fm14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fm14MouseClicked(evt);
            }
        });
        mCemilla.add(fm14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 40, 40));

        fm15.setBackground(new java.awt.Color(153, 255, 255));
        fm15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fm15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/seguridad/formas/fm1.png"))); // NOI18N
        fm15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fm15.setOpaque(true);
        fm15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fm15MouseClicked(evt);
            }
        });
        mCemilla.add(fm15, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 40, 40));

        cemilla.add(mCemilla, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 60, 320, 230));

        panelRecuperar.add(cemilla, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 350, 360));
        panelRecuperar.add(panelCurves1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 370, 360));

        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/fondoGeneral.png"))); // NOI18N
        panelRecuperar.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 360));

        panel_base.add(panelRecuperar, "card2");

        getContentPane().add(panel_base, "card6");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        xMause = evt.getX();
        yMause = evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        x = evt.getXOnScreen();
        y = evt.getYOnScreen();

        setLocation(x - xMause, y - yMause);
    }//GEN-LAST:event_formMouseDragged

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        logear();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        accionModificar();
    }//GEN-LAST:event_jLabel19MouseClicked

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        dinami(0);
    }//GEN-LAST:event_jLabel18MouseClicked

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        dinami(0);
    }//GEN-LAST:event_jLabel21MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked

        char i = '*';
        switch (aux) {
            case 0:
                aux = 1;
                jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/esconder.png")));
                jLabel7.setText("esconder");
                enPasworLogin.setEchoChar((char) 0);
                // enPasworLogin.setEchoChar((char)1);
                break;
            case 1:
                enPasworLogin.setEchoChar(i);
                jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/mostrar.png")));
                jLabel7.setText("mostrar");
                aux = 0;
                break;
        }
    }//GEN-LAST:event_jLabel7MouseClicked

    private void enUsuarioLoginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enUsuarioLoginMouseExited
        dEntradaUser(0);
    }//GEN-LAST:event_enUsuarioLoginMouseExited

    private void enPasworLoginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enPasworLoginMouseExited
        dEntradaPsw(0);
    }//GEN-LAST:event_enPasworLoginMouseExited

    private void enUsuarioLoginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enUsuarioLoginMouseEntered
        dEntradaUser(1);
        dEntradaPsw(0);
    }//GEN-LAST:event_enUsuarioLoginMouseEntered

    private void enPasworLoginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enPasworLoginMouseEntered
        dEntradaPsw(1);
    }//GEN-LAST:event_enPasworLoginMouseEntered

    private void enPasworLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enPasworLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_enPasworLoginActionPerformed

    private void enUsuarioLoginKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_enUsuarioLoginKeyTyped

        if (enUsuarioLogin.getText().length() >= 20) {
            evt.consume();
        }
        char a = evt.getKeyChar();
        if (a == ' ') {
            evt.consume();
        }

    }//GEN-LAST:event_enUsuarioLoginKeyTyped

    private void enPasworLoginKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_enPasworLoginKeyTyped
        if (enPasworLogin.getText().length() >= 20) {
            evt.consume();
        }
        char a = evt.getKeyChar();
        if (a == ' ') {
            evt.consume();
        }
    }//GEN-LAST:event_enPasworLoginKeyTyped

    private void enPasworLoginMMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enPasworLoginMMouseEntered
        dmPsw(1);
    }//GEN-LAST:event_enPasworLoginMMouseEntered

    private void enPasworLoginMMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enPasworLoginMMouseExited
        dmPsw(0);
    }//GEN-LAST:event_enPasworLoginMMouseExited

    private void enPasworLoginMMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enPasworLoginMMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_enPasworLoginMMousePressed

    private void enPasworLoginMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enPasworLoginMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_enPasworLoginMActionPerformed

    private void enPasworLoginMKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_enPasworLoginMKeyTyped
        if (enPasworLoginM.getText().length() >= 20) {
            evt.consume();
        }
    }//GEN-LAST:event_enPasworLoginMKeyTyped

    private void jLabel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MouseClicked
        char i = '*';
        switch (aux1) {
            case 0:
                aux1 = 1;
                jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/esconder.png")));
                jLabel23.setText("esconder");
                enPasworLoginM.setEchoChar((char) 0);
                break;
            case 1:
                enPasworLoginM.setEchoChar(i);
                jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/mostrar.png")));
                jLabel23.setText("mostrar");
                aux1 = 0;
                break;
        }
    }//GEN-LAST:event_jLabel23MouseClicked

    private void enUsuarioLoginMMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enUsuarioLoginMMouseEntered
        dmUsuario(1);
    }//GEN-LAST:event_enUsuarioLoginMMouseEntered

    private void enUsuarioLoginMMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enUsuarioLoginMMouseExited
        dmUsuario(0);
    }//GEN-LAST:event_enUsuarioLoginMMouseExited

    private void enUsuarioLoginMMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enUsuarioLoginMMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_enUsuarioLoginMMousePressed

    private void enUsuarioLoginMKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_enUsuarioLoginMKeyTyped
        if (enUsuarioLoginM.getText().length() >= 20) {
            evt.consume();
        }
    }//GEN-LAST:event_enUsuarioLoginMKeyTyped

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseEntered
        jLabel5.setForeground(new java.awt.Color(0, 0, 250));
    }//GEN-LAST:event_jLabel5MouseEntered

    private void jLabel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseExited
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_jLabel5MouseExited

    private void jLabel8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseEntered
        jLabel8.setForeground(new Color(255, 0, 0));
    }//GEN-LAST:event_jLabel8MouseEntered

    private void jLabel8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseExited
        jLabel8.setForeground(new Color(255, 51, 51));
    }//GEN-LAST:event_jLabel8MouseExited

    private void jLabel7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseEntered
        jLabel7.setForeground(new Color(0, 0, 100));
    }//GEN-LAST:event_jLabel7MouseEntered

    private void jLabel7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseExited
        jLabel7.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_jLabel7MouseExited

    private void jLabel6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseExited
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/usuarioA.png")));
    }//GEN-LAST:event_jLabel6MouseExited

    private void jLabel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseEntered
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/usuarioB.png")));
    }//GEN-LAST:event_jLabel6MouseEntered

    private void enUsuarioLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_enUsuarioLoginKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            dEntradaUser(0);
            dEntradaPsw(1);
        }
    }//GEN-LAST:event_enUsuarioLoginKeyPressed

    private void enPasworLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_enPasworLoginKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            logear();
        }
    }//GEN-LAST:event_enPasworLoginKeyPressed

    private void jLabel33MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel33MouseClicked
        dinami(1);
    }//GEN-LAST:event_jLabel33MouseClicked

    private void jLabel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseClicked
        char a = '*';

        switch (jLabel22.getText()) {
            case "Mostrar":
                jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/folder1/esconder.png")));
                jLabel22.setText("Ocultar");
                jPasswordField2.setEchoChar((char) 0);
                System.out.println("Interfaz.login.jLabel22MouseClicked()");
                break;
            case "Ocultar":
                jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/mostrar.png")));
                jLabel22.setText("Mostrar");
                jPasswordField2.setEchoChar(a);
                break;

        }
    }//GEN-LAST:event_jLabel22MouseClicked

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        if (jComboBox2.getSelectedIndex() > 1) {
            usuario.setUsuarioo(jTextField2.getText());
            usuario.setPsw(jPasswordField2.getText());
            tRecuperacion = 0;
            dinamicCreacion(panelCrearSeguridad);
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un tipo de usuario");
        }
    }//GEN-LAST:event_jLabel20MouseClicked

    private void jTextField2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField2MouseEntered
        if (jTextField2.getText().equals("Nuevo usuario")) {
            jTextField2.setText("");
        }
        jSeparator2.setBackground(new Color(0, 0, 150));
    }//GEN-LAST:event_jTextField2MouseEntered

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        dinami(3);
        dinamicRecuperacion(bUsuario);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        pocicionCemilla++;
        if (pocicionCemilla >= 4) {
            switch (tRecuperacion) {
                case 0:
                    pocicionCemilla = 0;
                    mCemilla.setVisible(true);
                    fCemilla.setVisible(false);
                    fm12.setIcon(imDispositivos);
                    fm13.setIcon(imVehiculos);
                    fm14.setIcon(imFormas);
                    fm15.setIcon(imFrutas);
                    usuario.setSemilla(String.valueOf(forma + " " + fruta + " " + dispocitivo + " " + vehicul));

                    dinami(2);
                    jLabel39.setBackground(new Color(102, 255, 102));

                    break;
                case 1:
                    //JOptionPane.showMessageDialog(null, usuario.validarCemilla((String) jComboBox1.getSelectedItem(), String.valueOf(forma + " " + fruta + " " + dispocitivo + " " + vehicul)));
                    pocicionCemilla = 0;
                    usuario.setSemilla(String.valueOf(forma + " " + fruta + " " + dispocitivo + " " + vehicul));
                    if (usuario.validarCemilla((String) jComboBox1.getSelectedItem(), usuario.getSemilla())) {
                        JOptionPane.showMessageDialog(null, "La combinación es correcta ahora puedes cambiar \nlos datos de inicio de este usuario");
                        dinami(1);
                        uAnl.setText("nuevo");
                        cAnl.setText("nuevo");
                    } else {
                        JOptionPane.showMessageDialog(null, "La combinación de imágenes no es la correcta");
                        moverCemilla(dnmCemilla());
                    }
                    break;
            }
            //JOptionPane.showMessageDialog(null, "La cemilla generada es, " + forma + " " + fruta + " " + dispocitivo + " " + vehicul);

        } else {
            moverCemilla(dnmCemilla());
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void fm1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fm1MouseClicked
        clSeleccionCemilla();
        fm1.setBackground(new Color(153, 153, 153));
        switch (pocicionCemilla) {
            case 0:
                forma = 10;
                imFormas = (ImageIcon) fm1.getIcon();
                break;
            case 1:
                fruta = 10;
                imFrutas = (ImageIcon) fm1.getIcon();
                break;
            case 2:
                dispocitivo = 10;
                imDispositivos = (ImageIcon) fm1.getIcon();
                break;
            case 3:
                vehicul = 10;
                imVehiculos = (ImageIcon) fm1.getIcon();
                break;
        }
    }//GEN-LAST:event_fm1MouseClicked

    private void fm2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fm2MouseClicked
        clSeleccionCemilla();
        fm2.setBackground(new Color(153, 153, 153));
        switch (pocicionCemilla) {
            case 0:
                forma = 20;
                imFormas = (ImageIcon) fm2.getIcon();
                break;
            case 1:
                fruta = 20;
                imFrutas = (ImageIcon) fm2.getIcon();
                break;
            case 2:
                dispocitivo = 20;
                imDispositivos = (ImageIcon) fm2.getIcon();
                break;
            case 3:
                vehicul = 20;
                imVehiculos = (ImageIcon) fm2.getIcon();
                break;
        }
    }//GEN-LAST:event_fm2MouseClicked

    private void fm3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fm3MouseClicked
        clSeleccionCemilla();
        fm3.setBackground(new Color(153, 153, 153));
        switch (pocicionCemilla) {
            case 0:
                forma = 30;
                imFormas = (ImageIcon) fm3.getIcon();
                break;
            case 1:
                fruta = 30;
                imFrutas = (ImageIcon) fm3.getIcon();
                break;
            case 2:
                dispocitivo = 30;
                imDispositivos = (ImageIcon) fm3.getIcon();
                break;
            case 3:
                vehicul = 30;
                imVehiculos = (ImageIcon) fm3.getIcon();
                break;
        }
    }//GEN-LAST:event_fm3MouseClicked

    private void fm4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fm4MouseClicked
        clSeleccionCemilla();
        fm4.setBackground(new Color(153, 153, 153));
        switch (pocicionCemilla) {
            case 0:
                forma = 40;
                imFormas = (ImageIcon) fm4.getIcon();
                break;
            case 1:
                fruta = 40;
                imFrutas = (ImageIcon) fm4.getIcon();
                break;
            case 2:
                dispocitivo = 40;
                imDispositivos = (ImageIcon) fm4.getIcon();
                break;
            case 3:
                vehicul = 40;
                imVehiculos = (ImageIcon) fm4.getIcon();
                break;
        }
    }//GEN-LAST:event_fm4MouseClicked

    private void fm5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fm5MouseClicked
        clSeleccionCemilla();
        fm5.setBackground(new Color(153, 153, 153));
        switch (pocicionCemilla) {
            case 0:
                forma = 50;
                imFormas = (ImageIcon) fm5.getIcon();
                break;
            case 1:
                fruta = 50;
                imFrutas = (ImageIcon) fm5.getIcon();
                break;
            case 2:
                dispocitivo = 50;
                imDispositivos = (ImageIcon) fm5.getIcon();
                break;
            case 3:
                vehicul = 50;
                imVehiculos = (ImageIcon) fm5.getIcon();
                break;
        }
    }//GEN-LAST:event_fm5MouseClicked

    private void fm6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fm6MouseClicked
        clSeleccionCemilla();
        fm6.setBackground(new Color(153, 153, 153));
        switch (pocicionCemilla) {
            case 0:
                forma = 60;
                imFormas = (ImageIcon) fm6.getIcon();
                break;
            case 1:
                fruta = 60;
                imFrutas = (ImageIcon) fm6.getIcon();
                break;
            case 2:
                dispocitivo = 60;
                imDispositivos = (ImageIcon) fm6.getIcon();
                break;
            case 3:
                vehicul = 60;
                imVehiculos = (ImageIcon) fm6.getIcon();
                break;
        }
    }//GEN-LAST:event_fm6MouseClicked

    private void fm7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fm7MouseClicked
        clSeleccionCemilla();
        fm7.setBackground(new Color(153, 153, 153));
        switch (pocicionCemilla) {
            case 0:
                forma = 70;
                imFormas = (ImageIcon) fm7.getIcon();
                break;
            case 1:
                fruta = 70;
                imFrutas = (ImageIcon) fm7.getIcon();
                break;
            case 2:
                dispocitivo = 70;
                imDispositivos = (ImageIcon) fm7.getIcon();
                break;
            case 3:
                vehicul = 70;
                imVehiculos = (ImageIcon) fm7.getIcon();
                break;
        }
    }//GEN-LAST:event_fm7MouseClicked

    private void fm8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fm8MouseClicked
        clSeleccionCemilla();
        fm8.setBackground(new Color(153, 153, 153));
        switch (pocicionCemilla) {
            case 0:
                forma = 80;
                imFormas = (ImageIcon) fm8.getIcon();
                break;
            case 1:
                fruta = 80;
                imFrutas = (ImageIcon) fm8.getIcon();
                break;
            case 2:
                dispocitivo = 80;
                imDispositivos = (ImageIcon) fm8.getIcon();
                break;
            case 3:
                vehicul = 80;
                imVehiculos = (ImageIcon) fm8.getIcon();
                break;
        }
    }//GEN-LAST:event_fm8MouseClicked

    private void fm9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fm9MouseClicked
        clSeleccionCemilla();
        fm9.setBackground(new Color(153, 153, 153));
        switch (pocicionCemilla) {
            case 0:
                forma = 90;
                imFormas = (ImageIcon) fm9.getIcon();
                break;
            case 1:
                fruta = 90;
                imFrutas = (ImageIcon) fm9.getIcon();
                break;
            case 2:
                dispocitivo = 90;
                imDispositivos = (ImageIcon) fm9.getIcon();
                break;
            case 3:
                vehicul = 90;
                imVehiculos = (ImageIcon) fm9.getIcon();
                break;
        }
    }//GEN-LAST:event_fm9MouseClicked

    private void fm10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fm10MouseClicked
        clSeleccionCemilla();
        fm10.setBackground(new Color(153, 153, 153));
        switch (pocicionCemilla) {
            case 0:
                forma = 95;
                imFormas = (ImageIcon) fm10.getIcon();
                break;
            case 1:
                fruta = 95;
                imFrutas = (ImageIcon) fm10.getIcon();
                break;
            case 2:
                dispocitivo = 95;
                imDispositivos = (ImageIcon) fm10.getIcon();
                break;
            case 3:
                vehicul = 95;
                imVehiculos = (ImageIcon) fm10.getIcon();
                break;
        }
    }//GEN-LAST:event_fm10MouseClicked

    private void fm11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fm11MouseClicked
        clSeleccionCemilla();
        fm11.setBackground(new Color(153, 153, 153));
        switch (pocicionCemilla) {
            case 0:
                forma = 99;
                imFormas = (ImageIcon) fm11.getIcon();
                break;
            case 1:
                fruta = 99;
                imFrutas = (ImageIcon) fm11.getIcon();
                break;
            case 2:
                dispocitivo = 99;
                imDispositivos = (ImageIcon) fm11.getIcon();
                break;
            case 3:
                vehicul = 99;
                imVehiculos = (ImageIcon) fm11.getIcon();
                break;
        }
    }//GEN-LAST:event_fm11MouseClicked

    private void fm12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fm12MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_fm12MouseClicked

    private void fm13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fm13MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_fm13MouseClicked

    private void fm14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fm14MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_fm14MouseClicked

    private void fm15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fm15MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_fm15MouseClicked

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if (jCheckBox1.isSelected() == false) {
            jLabel40.setVisible(false);
            jLabel41.setVisible(false);
            jTextField1.setVisible(false);
            jTextField3.setVisible(false);
        } else {
            jLabel40.setVisible(true);
            jLabel41.setVisible(true);
            jTextField1.setVisible(true);
            jTextField3.setVisible(true);
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        usuario.setTipo((String) jComboBox2.getSelectedItem());
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        if (jCheckBox1.isSelected()) {
            if (usuario.getSemilla().equals("") == true) {
                JOptionPane.showMessageDialog(null, "Es necesario crear una semilla de recuperación para el usuario");
            } else {
                if (jCheckBox1.isSelected()) {
                    if (jTextField1.getText().equals("") == false && jTextField3.getText().equals("") == false) {
                        usuario.agregar();
                        //JOptionPane.showMessageDialog(null, "Usuaio registrado correctamente");
                        dinami(0);
                    } else {
                        JOptionPane.showMessageDialog(null, "Debes de poner una ‘pregunta’ y una ‘respuesta’");
                    }
                } else {
                    usuario.agregar();
                    dinami(0);
                }

            }
        } else {
            usuario.agregar();
            dinami(0);
        }

    }//GEN-LAST:event_jButton3MouseClicked

    private void jLabel39MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel39MouseClicked
        dinami(3);
        dinamicRecuperacion(cemilla);
    }//GEN-LAST:event_jLabel39MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        dinamicCreacion(panelCreacion);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jLabel27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseClicked
        tRecuperacion = 1;
        moverCemilla(dnmCemilla());
        dinamicRecuperacion(cemilla);
    }//GEN-LAST:event_jLabel27MouseClicked

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        char a = evt.getKeyChar();

        if (jTextField2.getText().length() >= 20) {
            evt.consume();
        }
        if (a == ' ') {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField2KeyTyped

    private void enUsuarioLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enUsuarioLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_enUsuarioLoginActionPerformed

    private void jPasswordField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField2KeyTyped
        char a = evt.getKeyChar();

        if (jPasswordField2.getText().length() >= 20) {
            evt.consume();
        }
        if (a == ' ') {
            evt.consume();
        }
    }//GEN-LAST:event_jPasswordField2KeyTyped

    private void jTextField2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField2MouseExited
        if (jTextField2.getText().equals("")) {
            jTextField2.setText("Nuevo usuario");
            jSeparator2.setBackground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_jTextField2MouseExited

    private void jPasswordField2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPasswordField2MouseEntered
        if (jPasswordField2.getText().equals("Ingrese su contraseña")) {
            jPasswordField2.setText("");
        }
        jSeparator1.setBackground(new Color(0, 0, 150));
    }//GEN-LAST:event_jPasswordField2MouseEntered

    private void jPasswordField2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPasswordField2MouseExited
        if (jPasswordField2.getText().equals("")) {
            jPasswordField2.setText("Ingrese su contraseña");
            jSeparator1.setBackground(new Color(153, 153, 153));
        }

    }//GEN-LAST:event_jPasswordField2MouseExited

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        usuario.setUsuarioo((String) jComboBox1.getSelectedItem());
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        //  JOptionPane.showMessageDialog(null, usuario.validarPregunta(jTextField4.getText(), (String) jComboBox1.getSelectedItem()));
        if (usuario.validarPregunta(jTextField4.getText(), (String) jComboBox1.getSelectedItem())) {
            uAnl.setText("nuevo");
            cAnl.setText("nuevo");
            dinami(1);
        } else {
            JOptionPane.showMessageDialog(null, "La respuesta no es la correcta");
        }
    }//GEN-LAST:event_jButton2MouseClicked

    private void jLabel26MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MouseClicked
        if (usuario.whatPregunta((String) jComboBox1.getSelectedItem())) {
            jLabel48.setText(usuario.buscarPregunta((String) jComboBox1.getSelectedItem()));
            modelos.usuario.setUsuarioo((String) jComboBox1.getSelectedItem());
            dinamicRecuperacion(preguntaSeguridad);
        } else {
            JOptionPane.showMessageDialog(null, "Este usuario no tiene establecida ninguna pregunta de seguridad");
        }


    }//GEN-LAST:event_jLabel26MouseClicked

    private void jLabel49MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel49MouseClicked
        dinami(0);
    }//GEN-LAST:event_jLabel49MouseClicked

    private void enUsuarioLoginMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_enUsuarioLoginMKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            dmPsw(1);
        }
    }//GEN-LAST:event_enUsuarioLoginMKeyPressed

    private void enPasworLoginMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_enPasworLoginMKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            accionModificar();
        }
    }//GEN-LAST:event_enPasworLoginMKeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4MouseEntered
        jSeparator3.setBackground(new Color(0, 0, 250));
    }//GEN-LAST:event_jTextField4MouseEntered

    private void jTextField4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4MouseExited
        if (jTextField4.getText().equals("")) {
            jSeparator3.setBackground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_jTextField4MouseExited

    /**
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
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JLayeredPane bUsuario;
    private javax.swing.JLabel cAnl;
    private static javax.swing.JLayeredPane cemilla;
    private elaprendiz.gui.varios.ClockDigital clockDigital1;
    private javax.swing.JSeparator dEnUsuario;
    private javax.swing.JPasswordField enPasworLogin;
    private javax.swing.JPasswordField enPasworLoginM;
    private javax.swing.JTextField enUsuarioLogin;
    private javax.swing.JTextField enUsuarioLoginM;
    private javax.swing.JLayeredPane fCemilla;
    private static javax.swing.JLabel fm1;
    private static javax.swing.JLabel fm10;
    private static javax.swing.JLabel fm11;
    private static javax.swing.JLabel fm12;
    private static javax.swing.JLabel fm13;
    private static javax.swing.JLabel fm14;
    private static javax.swing.JLabel fm15;
    private static javax.swing.JLabel fm2;
    private static javax.swing.JLabel fm3;
    private static javax.swing.JLabel fm4;
    private static javax.swing.JLabel fm5;
    private static javax.swing.JLabel fm6;
    private static javax.swing.JLabel fm7;
    private static javax.swing.JLabel fm8;
    private static javax.swing.JLabel fm9;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JLayeredPane mCemilla;
    private javax.swing.JLabel mensajePasworErr;
    private javax.swing.JLabel mensajePasworErr1;
    private javax.swing.JLabel mensajeUsuarioErr;
    private javax.swing.JLabel mensajeUsuarioErr1;
    private javax.swing.JPanel panelAgregar;
    private static javax.swing.JPanel panelCreacion;
    private static javax.swing.JPanel panelCrearSeguridad;
    private elaprendiz.gui.panel.PanelCurves panelCurves1;
    private Interfaz.PanelDegradadoAnimado panelDegradadoAnimado1;
    private Interfaz.PanelDegradadoAnimado panelDegradadoAnimado2;
    private Interfaz.PanelDegradadoAnimado panelDegradadoAnimado3;
    private Interfaz.PanelDegradadoAnimado panelDegradadoAnimado4;
    private Interfaz.PanelDegradadoAnimado panelDegradadoAnimado5;
    private javax.swing.JPanel panelLogin;
    private javax.swing.JPanel panelRecuperar;
    private javax.swing.JPanel panel_base;
    private javax.swing.JPanel panleModificar;
    private static javax.swing.JLayeredPane preguntaSeguridad;
    private javax.swing.JSeparator sPsw;
    private javax.swing.JSeparator sPsw1;
    private javax.swing.JSeparator sUsuario;
    private javax.swing.JLabel uAnl;
    // End of variables declaration//GEN-END:variables
public void dinami(int aux) {
        switch (aux) {
            case 0:
                panelLogin.setVisible(true);
                panleModificar.setVisible(false);
                panelAgregar.setVisible(false);
                panelRecuperar.setVisible(false);
                break;
            case 1:
                panelLogin.setVisible(false);
                panleModificar.setVisible(true);
                panelAgregar.setVisible(false);
                panelRecuperar.setVisible(false);
                break;
            case 2:
                panelLogin.setVisible(false);
                panleModificar.setVisible(false);
                panelAgregar.setVisible(true);
                panelRecuperar.setVisible(false);
                break;
            case 3:
                panelLogin.setVisible(false);
                panleModificar.setVisible(false);
                panelAgregar.setVisible(false);
                panelRecuperar.setVisible(true);
                break;
        }
    }

    public void dEntradaPsw(int i) {

        switch (i) {
            case 1:
                if (enPasworLogin.getText().equals("Ingrese su contraseña")) {
                    enPasworLogin.requestFocusInWindow();
                    sPsw.setBackground(new Color(0, 0, 250));
                    enPasworLogin.setText("");
                    //  enPasworLogin.setEnabled(true);
                    enPasworLogin.setForeground(new Color(0, 0, 0));
                }
                break;

            case 0:
                if (enPasworLogin.getText().equals("")) {
                    panelLogin.requestFocusInWindow();
                    //  enPasworLogin.setEnabled(false);
                    enPasworLogin.setText("Ingrese su contraseña");

                    sPsw.setBackground(new Color(153, 153, 153));
                    enPasworLogin.setForeground(new Color(102, 102, 102));
                }

                break;
        }
    }

    public void dEntradaUser(int i) {
        switch (i) {
            case 1:
                if (enUsuarioLogin.getText().equals("Ingrese su usuario")) {
                    enUsuarioLogin.requestFocusInWindow();
                    sUsuario.setBackground(new Color(0, 0, 250));
                    enUsuarioLogin.setText("");
                    enUsuarioLogin.setEditable(true);
                    enUsuarioLogin.setForeground(new Color(0, 0, 0));
                }
                break;
            case 0:
                if (enUsuarioLogin.getText().equals("")) {
                    panelLogin.requestFocusInWindow();
                    enUsuarioLogin.setText("Ingrese su usuario");
                    enUsuarioLogin.setEditable(false);
                    enUsuarioLogin.setForeground(new Color(102, 102, 102));
                    sUsuario.setBackground(new Color(153, 153, 153));
                }
                break;
        }
    }

    public void dmUsuario(int i) {
        switch (i) {
            case 1:
                dEnUsuario.setBackground(new Color(0, 0, 250));
                if (enUsuarioLoginM.getText().equals("Ingresar usuario")) {
                    enUsuarioLoginM.setText("");
                    enUsuarioLoginM.requestFocusInWindow();
                    enUsuarioLoginM.setForeground(new Color(0, 0, 0));
                }
                break;
            case 0:
                if (enUsuarioLoginM.getText().equals("")) {
                    enUsuarioLoginM.setText("Ingresar usuario");
                    panleModificar.requestFocusInWindow();
                    enUsuarioLoginM.setForeground(new Color(102, 102, 102));
                    dEnUsuario.setBackground(new Color(160, 160, 160));
                }
                break;
        }
    }

    public void dmPsw(int i) {
        switch (i) {
            case 1:
                sPsw1.setBackground(new Color(0, 0, 250));
                if (enPasworLoginM.getText().equals("Ingresar contraseña")) {
                    enPasworLoginM.requestFocusInWindow();
                    enPasworLoginM.setText("");
                    enPasworLoginM.setForeground(new Color(0, 0, 0));
                }
                break;
            case 0:
                if (enPasworLoginM.getText().equals("")) {
                    panleModificar.requestFocusInWindow();
                    enPasworLoginM.setText("Ingresar contraseña");
                    enPasworLoginM.setForeground(new Color(102, 102, 102));
                    sPsw1.setBackground(new Color(160, 160, 160));
                }
                break;
        }
    }

    public void logear() {
        if (usuario.stdControlDeLogin()) {
            contador = 5;
        }
        if (((enUsuarioLogin.getText().equals("Ingrese su usuario") == false) && (enPasworLogin.getText().equals("Ingrese su contraseña") == false))) {
            contador++;
            System.out.println("se esta haciendo");
        } else {
            JOptionPane.showMessageDialog(null, "Los campos de datos estan vacios por favor ingrese los datos");
        }

        if (contador >= 5) {
            JOptionPane.showMessageDialog(null, "Has realizado demaciados intentos por favor espere 60s");
            usuario.controlDeLogin(1);

            if (estadoContador) {
                timer = new Timer(1000, (ae) -> {
                    jLabel50.setVisible(true);

                    cronometro++;
                    jLabel50.setText("0" + String.valueOf(cronometro) + "s");
                    if (cronometro >= 60) {
                        contador = 0;
                        usuario.controlDeLogin(0);
                        JOptionPane.showMessageDialog(null, "ya puedes volver a intentar");
                        jLabel50.setVisible(false);
                        cronometro = 0;
                        timer.stop();
                        estadoContador = true;
                    }

                });
                estadoContador = false;
            }
            timer.start();
        } else {
            String usr = enUsuarioLogin.getText();
            usuario.setUsuarioo(usr);
            System.out.println(usuario.getUsuarioo());
            String psw = enPasworLogin.getText();
            mensajePasworErr.setVisible(false);
            String[] datos = modelos.usuario.login();

            if (usr.equals(datos[0])) {

                mensajeUsuarioErr.setVisible(false);
                if (psw.equals(datos[1])) {
                    mensajePasworErr.setVisible(false);
                    principal.setVisible(true);
                    principal.setLogin(this);
                    principal.openUser(usr);
                    this.setVisible(false);

                    enUsuarioLogin.setText("");
                    dEntradaUser(0);
                    jLabel9.requestFocusInWindow();
                    enPasworLogin.setText("");
                    dEntradaPsw(0);
                } else {
                    mensajePasworErr.setVisible(true);
                }
            } else {
                mensajeUsuarioErr.setVisible(true);
            }
        }
    }

    public ArrayList<String> dnmCemilla() {
        switch (pocicionCemilla) {
            case 0:
                return formas;
            case 1:
                return frutas;
            case 2:
                return dispositivos;
            case 3:
                return vehiculo;
            default:
                return null;
        }
    }

    public void moverCemilla(ArrayList<String> dispositivos) {
        Cemilla();
        clSeleccionCemilla();
        fm1.setIcon((new javax.swing.ImageIcon(getClass().getResource(dispositivos.get(0)))));
        fm2.setIcon((new javax.swing.ImageIcon(getClass().getResource(dispositivos.get(1)))));
        fm3.setIcon((new javax.swing.ImageIcon(getClass().getResource(dispositivos.get(2)))));
        fm4.setIcon((new javax.swing.ImageIcon(getClass().getResource(dispositivos.get(3)))));
        fm5.setIcon((new javax.swing.ImageIcon(getClass().getResource(dispositivos.get(4)))));
        fm6.setIcon((new javax.swing.ImageIcon(getClass().getResource(dispositivos.get(5)))));
        fm7.setIcon((new javax.swing.ImageIcon(getClass().getResource(dispositivos.get(6)))));
        fm8.setIcon((new javax.swing.ImageIcon(getClass().getResource(dispositivos.get(7)))));
        fm9.setIcon((new javax.swing.ImageIcon(getClass().getResource(dispositivos.get(8)))));
        fm10.setIcon((new javax.swing.ImageIcon(getClass().getResource(dispositivos.get(9)))));
        fm11.setIcon((new javax.swing.ImageIcon(getClass().getResource(dispositivos.get(10)))));

    }

    public void clSeleccionCemilla() {
        fm1.setBackground(new Color(153, 255, 255));
        fm2.setBackground(new Color(153, 255, 255));
        fm3.setBackground(new Color(153, 255, 255));
        fm4.setBackground(new Color(153, 255, 255));
        fm5.setBackground(new Color(153, 255, 255));
        fm6.setBackground(new Color(153, 255, 255));
        fm7.setBackground(new Color(153, 255, 255));
        fm8.setBackground(new Color(153, 255, 255));
        fm9.setBackground(new Color(153, 255, 255));
        fm10.setBackground(new Color(153, 255, 255));
        fm11.setBackground(new Color(153, 255, 255));
    }

    public String[] listaUsuarios() {
        String[] usuarios;
        ArrayList<String[]> lista = modelos.usuario.rescueAll();
        usuarios = new String[lista.size()];
        int i = 0;
        for (String[] aux : lista) {
            usuarios[i] = aux[0];
            i++;
        }
        return usuarios;
    }

    public static void dinamicRecuperacion(JLayeredPane aux) {
        bUsuario.setVisible(false);
        cemilla.setVisible(false);
        preguntaSeguridad.setVisible(false);

        aux.setVisible(true);
    }

    public static void dinamicCreacion(JPanel aux) {
        panelCreacion.setVisible(false);
        panelCrearSeguridad.setVisible(false);

        aux.setVisible(true);
    }

    private void accionModificar() {
        String usr = enUsuarioLoginM.getText();
        String psw = enPasworLoginM.getText();

        switch (uAnl.getText()) {

            case "anterior":
                modelos.usuario.setUsuarioo(usr);
                String[] datos = modelos.usuario.login();
                if (usr.equals(datos[0])) {
                    mensajeUsuarioErr1.setVisible(false);
                    if (psw.equals(datos[1])) {
                        usuario.setPsw(psw);
                        mensajePasworErr1.setVisible(false);
                        uAnl.setText("nuevo");
                        cAnl.setText("nuevo");
                        enUsuarioLoginM.setText("");
                        dmUsuario(0);
                        enPasworLoginM.setText("");
                        dmPsw(0);

                    } else {
                        mensajePasworErr1.setVisible(true);
                    }
                } else {
                    mensajeUsuarioErr1.setVisible(true);
                }
                break;

            case "nuevo":
                System.out.println(usuario.esistUsuario(usr));
                if (usuario.esistUsuario(usr)) {
                    usuario.modificar(usr, psw);
                    uAnl.setText("anterior");
                    cAnl.setText("anterior");
                    enUsuarioLoginM.setText("");
                    dmUsuario(0);
                    enPasworLoginM.setText("");
                    dmPsw(0);

                    dinami(0);
                } else {
                    if (usuario.getUsuarioo().equals(usr) == true) {
                        usuario.modificar(usr, psw);
                        uAnl.setText("anterior");
                        cAnl.setText("anterior");
                        enUsuarioLoginM.setText("");
                        dmUsuario(0);
                        enPasworLoginM.setText("");
                        dmPsw(0);

                        dinami(0);
                    } else {
                        JOptionPane.showMessageDialog(null, "Ya existe un usuario con ese nombre");
                    }
                }

                break;
        }
    }
}

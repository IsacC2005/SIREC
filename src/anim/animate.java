package anim;

import Clases.PanelRound;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class animate extends Thread {

    private static Timer timer;
    private static int i = 0;
    private static boolean animationInProgress = false;
    private static boolean animationL = false;
    // static Color cl;

    public static void animar(JComponent componente, boolean opcion, Color cl, int opacidad) {

        //cl = color;   
        if (animationInProgress) {
            return;
        } else {

            animationInProgress = true;
            if (opcion) {
                i = opacidad;
                System.out.println("ocultar");
                timer = new Timer(1, (ae) -> {
                    if (i > 0) {
                        i = i - 10;
                        if (i < 0) {
                            System.out.println("Opacidad menor");
                            i = 0;
                        }
                        componente.setBackground(new Color(cl.getRed(), cl.getGreen(), cl.getBlue(), i));
                        componente.repaint();
                    } else {
                        componente.setVisible(false);
                        timer.stop();
                        animationInProgress = false;
                    }
                });
                timer.start();
            } else {
                i = 0;
                componente.setBackground(new Color(componente.getBackground().getRed(), componente.getBackground().getGreen(), componente.getBackground().getBlue(), 0));
                componente.repaint();
                componente.setVisible(true);
                System.out.println("mostrar");
                timer = new Timer(1, (ae) -> {
                    if (i < opacidad) {
                        componente.setBackground(new Color(cl.getRed(), cl.getGreen(), cl.getBlue(), i));
                        componente.repaint();
                        i = i + 10;
                        if (i > opacidad) {
                            System.out.println("Opacidad mayor");
                            i = opacidad;
                        }
                    } else {
                        timer.stop();
                        animationInProgress = false;
                    }
                });

            }
        }
        timer.restart(); // Reiniciar el timer en lugar de crear uno nuevo

        // Al finalizar la animación:
    }

    public static void animar(JComponent componente, JComponent accesorio, boolean opcion, Color cl, int opacidad) {

        //cl = color;   
        if (animationInProgress) {
            return;
        } else {

            animationInProgress = true;
            if (opcion) {
                i = opacidad;
                System.out.println("ocultar");
                accesorio.setVisible(false);
                timer = new Timer(1, (ae) -> {
                    if (i > 0) {
                        i = i - 10;
                        if (i < 0) {
                            System.out.println("Opacidad menor");
                            i = 0;
                        }
                        componente.setBackground(new Color(cl.getRed(), cl.getGreen(), cl.getBlue(), i));
                        componente.repaint();
                    } else {
                        componente.setVisible(false);
                        timer.stop();
                        animationInProgress = false;
                    }
                });
                timer.start();
            } else {
                i = 0;
                componente.setBackground(new Color(componente.getBackground().getRed(), componente.getBackground().getGreen(), componente.getBackground().getBlue(), 0));
                componente.repaint();
                accesorio.setVisible(true);
                componente.setVisible(true);
                System.out.println("mostrar");
                timer = new Timer(1, (ae) -> {
                    if (i < opacidad) {
                        componente.setBackground(new Color(cl.getRed(), cl.getGreen(), cl.getBlue(), i));
                        componente.repaint();
                        i = i + 10;
                        if (i > opacidad) {
                            System.out.println("Opacidad mayor");
                            i = opacidad;
                        }
                    } else {
                        timer.stop();
                        animationInProgress = false;
                    }
                });

            }
        }
        timer.restart(); // Reiniciar el timer en lugar de crear uno nuevo

        // Al finalizar la animación:
    }

    public static void animar(ArrayList<JComponent> lista, boolean opcion, Color cl, int opacidad) {

        for (JComponent componente : lista) {
            an(componente, opcion, cl, opacidad);
            System.out.println("esta encendido kjasdjkfasjfdk");
        }

    }

    public static void main(String[] args) {

    }

    public static void an(JComponent componente, boolean estado, Color cl, int opacidad) {
        System.out.println(cl.getRed() + " " + cl.getGreen() + " " + cl.getBlue());
        if (estado) {
            i = opacidad;
            System.out.println("ocultar");
            timer = new Timer(1, (ae) -> {
                if (i > 0) {
                    i = i - 10;
                    if (i < 0) {
                        System.out.println("Opacidad menor");
                        i = 0;
                    }
                    componente.setBackground(new Color(cl.getRed(), cl.getGreen(), cl.getBlue(), i));
                    componente.repaint();
                } else {
                    componente.setVisible(false);
                    timer.stop();
                    animationInProgress = false;
                }
            });
            timer.start();
        } else {
            i = 0;
            componente.setBackground(new Color(componente.getBackground().getRed(), componente.getBackground().getGreen(), componente.getBackground().getBlue(), 0));
            componente.repaint();
            componente.setVisible(true);
            System.out.println("mostrar " + opacidad);
            timer = new Timer(10, (ae) -> {
                if (i < opacidad) {
                    System.out.println("hola mundo asjdl jfasdlfjlksajfdlkjasjdf");
                    componente.setBackground(new Color(cl.getRed(), cl.getGreen(), cl.getBlue(), i));
                    componente.repaint();
                    i = i + 1;
                    if (i > opacidad) {
                        System.out.println("Opacidad mayor");
                        i = opacidad;
                    }
                } else {
                    timer.stop();
                    animationInProgress = false;
                }
            });
            timer.start();
        }
    }

    public static void animarL(JLabel label, boolean opcion, Color color, int opacidad) {
        if (animationL == false) {
            animationL = true;
            if (opcion) {

                i = opacidad;
                timer = new Timer(1, (ae) -> {
                    if (i > 0) {
                        i = i - 10;
                        if(i < 0){
                            i = 0;
                        }
                        label.setForeground(new Color(color.getRed(), color.getGreen(), color.getBlue(), i));
                    } else {
                        label.setVisible(false);
                        timer.stop();
                        animationL = false;
                    }
                });
                timer.start();
            } else {

                i = 0;
                label.setForeground(new Color(color.getRed(), color.getGreen(), color.getBlue(), i));
                label.setVisible(true);
                timer = new Timer(1, (ae) -> {
                    if (i < opacidad) {
                        i = i + 10;
                        if(i > 255){
                            i = 255;
                        }
                        label.setForeground(new Color(color.getRed(), color.getGreen(), color.getBlue(), i));
                    } else {
                        animationL = false;                        
                        timer.stop();
                    }
                });
                timer.start();
            }

        } else {
            return;
        }

    }
}

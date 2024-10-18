/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Clases.*;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelDegradadoAnimado extends JPanel {

    private Color colorInicial = Color.BLACK;
    private Color colorFinal = Color.WHITE;

    private Color mncInicial = Color.WHITE;
    private Color mncInicialInvertido = Color.BLACK;
    private Color mncFinal = Color.PINK;
    private Color mncFinalInvertido = Color.WHITE;

    private Timer timer;

    boolean invertirSuperior = false;
    boolean invertirInferior = false;
    int r = 0;
    int g = 0;
    int b = 0;
    int ri = 0;
    int gi = 0;
    int bi = 0;

    public Color getColorInicial() {
        return colorInicial;
    }

    public void setColorInicial(Color colorInicial) {
        this.colorInicial = colorInicial;
        this.mncInicialInvertido = colorInicial;
    }

    public Color getColorFinal() {
        return colorFinal;
    }

    public void setColorFinal(Color colorFinal) {
        this.colorFinal = colorFinal;
        this.mncFinalInvertido = colorFinal;
    }

    public Color getMncInicial() {
        return mncInicial;
    }

    public void setMncInicial(Color mncInicial) {
        this.mncInicial = mncInicial;
    }

    public Color getMncFinal() {
        return mncFinal;
    }

    public void setMncFinal(Color mncFinal) {
        this.mncFinal = mncFinal;
    }

    public PanelDegradadoAnimado() {
        startAnimation();
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Obtener las dimensiones del panel
        int width = getWidth();
        int height = getHeight();

        // Calcular el color intermedio basado en el tiempo transcurrido
        // Crear el degradado vertical
        GradientPaint gp = new GradientPaint(0, 0, colorInicial, 0, height, colorFinal);

        // Dibujar el degradado
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);
    }

    public void calculoSuperior() {
        if (invertirSuperior) {
            if (colorInicial.getRed() >= mncInicial.getRed()) {
                if (colorInicial.getRed() == mncInicial.getRed()) {
                    r = colorInicial.getRed();
                } else {
                    if (r < 3) {
                        r = 0;
                    } else {
                        r = colorInicial.getRed() - 3;
                    }

                }
            } else {
                r = colorInicial.getRed() + 3;
                if (r > 255) {
                    r = 255;
                }
            }

            if (colorInicial.getGreen() >= mncInicial.getGreen()) {
                if (colorInicial.getGreen() == mncInicial.getGreen()) {
                    this.g = colorInicial.getGreen();
                } else {
                    if (this.g < 3) {
                        this.g = 0;
                    } else {
                        this.g = colorInicial.getGreen() - 3;
                    }
                }
            } else {
                this.g = colorInicial.getGreen() + 3;
                if (this.g > 255) {
                    this.g = 255;
                }
            }

            if (colorInicial.getBlue() >= mncInicial.getBlue()) {
                if (colorInicial.getBlue() == mncInicial.getBlue()) {
                    b = colorInicial.getBlue();
                } else {
                    if (b < 3) {
                        b = 0;
                    } else {
                        b = colorInicial.getBlue() - 3;
                    }
                }
            } else {
                b = colorInicial.getBlue() + 3;
                if (b > 255) {
                    b = 255;
                }
            }
            if (colorInicial.getRed() == mncInicial.getRed() && colorInicial.getGreen() == mncInicial.getGreen() && colorInicial.getBlue() == mncInicial.getBlue()) {
                invertirSuperior = false;
            }
        } else {
            if (colorInicial.getRed() >= mncInicialInvertido.getRed()) {
                if (colorInicial.getRed() == mncInicialInvertido.getRed()) {
                    r = colorInicial.getRed();
                } else {
                    if (r < 3) {
                        r = 0;
                    } else {
                        r = colorInicial.getRed() - 3;
                    }
                }
            } else {
                r = colorInicial.getRed() + 3;
                if (r > 255) {
                    r = 255;
                }
            }

            if (colorInicial.getGreen() >= mncInicialInvertido.getGreen()) {
                if (colorInicial.getGreen() == mncInicialInvertido.getGreen()) {
                    this.g = colorInicial.getGreen();
                } else {
                    if (this.g < 3) {
                        this.g = 0;
                    } else {
                        this.g = colorInicial.getGreen() - 3;
                    }
                }
            } else {
                this.g = colorInicial.getGreen() + 3;
                if (this.g > 255) {
                    this.g = 255;
                }
            }

            if (colorInicial.getBlue() >= mncInicialInvertido.getBlue()) {
                if (colorInicial.getBlue() == mncInicialInvertido.getBlue()) {
                    b = colorInicial.getBlue();
                } else {
                    if (b < 3) {
                        b = 0;
                    } else {
                        b = colorInicial.getBlue() - 3;
                    }
                }
            } else {
                b = colorInicial.getBlue() + 3;
                if (b > 255) {
                    b = 255;
                }
            }
            if (colorInicial.getRed() == mncInicialInvertido.getRed() && colorInicial.getGreen() == mncInicialInvertido.getGreen() && colorInicial.getBlue() == mncInicialInvertido.getBlue()) {
                invertirSuperior = true;
            }
        }
        colorInicial = new Color(r, this.g, b);

    }

    public void calculoInferior() {

        if (invertirInferior) {
            if (colorFinal.getRed() >= mncFinal.getRed()) {
                if (colorFinal.getRed() == mncFinal.getRed()) {
                    ri = colorFinal.getRed();
                } else {
                    if (ri < 3) {
                        ri = 0;
                    } else {
                        ri = colorFinal.getRed() - 3;
                    }
                }
            } else {
                ri = colorFinal.getRed() + 3;
                if (ri > 255) {
                    ri = 255;
                }
            }

            if (colorFinal.getGreen() >= mncFinal.getGreen()) {
                if (colorFinal.getGreen() == mncFinal.getGreen()) {
                    this.gi = colorFinal.getGreen();
                } else {
                    if (this.gi < 3) {
                        this.gi = 0;
                    } else {
                        this.gi = colorFinal.getGreen() - 3;
                    }
                }
            } else {
                this.gi = colorFinal.getGreen() + 3;
                if (this.gi > 255) {
                    this.gi = 255;
                }
            }

            if (colorFinal.getBlue() >= mncFinal.getBlue()) {
                if (colorFinal.getBlue() == mncFinal.getBlue()) {
                    bi = colorFinal.getBlue();
                } else {
                    if (bi < 3) {
                        bi = 0;
                    } else {
                        bi = colorFinal.getBlue() - 3;
                    }
                }
            } else {
                bi = colorFinal.getBlue() + 3;
                if (bi > 255) {
                    bi = 255;
                }
            }
            if (colorFinal.getRed() == mncFinal.getRed() && colorFinal.getGreen() == mncFinal.getGreen() && colorFinal.getBlue() == mncFinal.getBlue()) {
                invertirInferior = false;
            }
        } else {
            if (colorFinal.getRed() >= mncFinalInvertido.getRed()) {

                if (colorFinal.getRed() == mncFinalInvertido.getRed()) {
                    ri = colorFinal.getRed();
                } else {
                    if (ri < 3) {
                        ri = 0;
                    } else {
                        ri = colorFinal.getRed() - 3;
                    }
                }
            } else {
                ri = colorFinal.getRed() + 3;
                if (ri > 255) {
                    ri = 255;
                }
            }

            if (colorFinal.getGreen() >= mncFinalInvertido.getGreen()) {
                if (colorFinal.getGreen() == mncFinalInvertido.getGreen()) {
                    this.gi = colorFinal.getGreen();
                } else {
                    if (this.gi < 3) {
                        this.gi = 0;
                    } else {
                        this.gi = colorFinal.getGreen() - 3;
                    }
                }
            } else {
                this.gi = colorFinal.getGreen() + 3;
                if (this.gi > 255) {
                    this.gi = 255;
                }
            }

            if (colorFinal.getBlue() >= mncFinalInvertido.getBlue()) {
                if (colorFinal.getBlue() == mncFinalInvertido.getBlue()) {
                    bi = colorFinal.getBlue();
                } else {
                    if (bi < 3) {
                        bi = 0;
                    } else {
                        bi = colorFinal.getBlue() - 3;
                    }
                }
            } else {
                bi = colorFinal.getBlue() + 3;
                if (bi > 255) {
                    bi = 255;
                }
            }
            if (colorFinal.getRed() == mncFinalInvertido.getRed() && colorFinal.getGreen() == mncFinalInvertido.getGreen() && colorFinal.getBlue() == mncFinalInvertido.getBlue()) {
                invertirInferior = true;
            }
        }

        colorFinal = new Color(ri, gi, bi);
    }

    // Método para iniciar y detener la animación
    public void startAnimation() {
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculoSuperior();
                calculoInferior();
                repaint();
            }
        });
        timer.start();
    }

    public void stopAnimation() {
        timer.stop();
    }
}

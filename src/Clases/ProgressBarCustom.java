package Clases;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class ProgressBarCustom extends JProgressBar {

    public Color getColorString() {
        return colorString;
    }

    public void setColorString(Color colorString) {
        this.colorString = colorString;
    }

    private Color colorString = new Color(200, 200, 200);

    public ProgressBarCustom() {
        setStringPainted(true);
        setPreferredSize(new Dimension(100, 15));
        setBackground(new Color(255, 255, 255));
        setForeground(new Color(69, 124, 235));
        setUI(new BasicProgressBarUI() {
            @Override
            protected void paintString(Graphics grphcs, int i, int i1, int i2, int i3, int i4, Insets insets) {
                grphcs.setColor(getColorString());
                super.paintString(grphcs, i, i1, i2, i3, i4, insets);
            }
        });
    }

    /**
     *
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Obtener dimensiones y valor de progreso
        int width = getWidth();
        int height = getHeight();
        int progress = getValue();

        // Crear un degradado lineal
        GradientPaint gradient = new GradientPaint(
                0, 0, getForeground().brighter(), // Color inicial en la esquina superior izquierda
                width, 0, getForeground().darker().darker() // Color final en la esquina superior derecha
        );


        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setPaint(gradient);

        // Dibujar el rectángulo de progreso con el degradado
        g2d.fillRect(0, 0, (int) (width * getPercentComplete()), height);

        // Dibujar el borde (opcional)
        //g2d.setColor(Color.DARK_GRAY);
        //g2d.drawRect(0, 0, width - 1, height - 1);

        g2d.dispose();
    }

    // Método para obtener el porcentaje de progreso
    @Override
    public double getPercentComplete() {
        return (double) getValue() / getMaximum();
    }
}

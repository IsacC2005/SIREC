package com.raven.datechooser;

public final class Months extends javax.swing.JPanel {

    private Event event;
    private int m;

    public Months() {
        initComponents();
    }

    private void addEvent() {
        for (int i = 0; i < getComponentCount(); i++) {
            ((Button) getComponent(i)).setEvent(event);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmd1 = new com.raven.datechooser.Button();
        cmd2 = new com.raven.datechooser.Button();
        cmd3 = new com.raven.datechooser.Button();
        cmd4 = new com.raven.datechooser.Button();
        cmd5 = new com.raven.datechooser.Button();
        cmd6 = new com.raven.datechooser.Button();
        cmd7 = new com.raven.datechooser.Button();
        cmd8 = new com.raven.datechooser.Button();
        cmd9 = new com.raven.datechooser.Button();
        cmd10 = new com.raven.datechooser.Button();
        cmd11 = new com.raven.datechooser.Button();
        cmd12 = new com.raven.datechooser.Button();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.GridLayout(4, 4));

        cmd1.setForeground(new java.awt.Color(75, 75, 75));
        cmd1.setText("Enero");
        cmd1.setName("1"); // NOI18N
        cmd1.setOpaque(true);
        add(cmd1);

        cmd2.setForeground(new java.awt.Color(75, 75, 75));
        cmd2.setText("Febrero");
        cmd2.setName("2"); // NOI18N
        cmd2.setOpaque(true);
        add(cmd2);

        cmd3.setForeground(new java.awt.Color(75, 75, 75));
        cmd3.setText("Marzo");
        cmd3.setName("3"); // NOI18N
        cmd3.setOpaque(true);
        add(cmd3);

        cmd4.setForeground(new java.awt.Color(75, 75, 75));
        cmd4.setText("Abril");
        cmd4.setName("4"); // NOI18N
        cmd4.setOpaque(true);
        cmd4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd4ActionPerformed(evt);
            }
        });
        add(cmd4);

        cmd5.setForeground(new java.awt.Color(75, 75, 75));
        cmd5.setText("Mayo");
        cmd5.setName("5"); // NOI18N
        cmd5.setOpaque(true);
        add(cmd5);

        cmd6.setForeground(new java.awt.Color(75, 75, 75));
        cmd6.setText("Junio");
        cmd6.setName("6"); // NOI18N
        cmd6.setOpaque(true);
        add(cmd6);

        cmd7.setForeground(new java.awt.Color(75, 75, 75));
        cmd7.setText("Julio");
        cmd7.setName("7"); // NOI18N
        cmd7.setOpaque(true);
        add(cmd7);

        cmd8.setForeground(new java.awt.Color(75, 75, 75));
        cmd8.setText("Agosto");
        cmd8.setName("8"); // NOI18N
        cmd8.setOpaque(true);
        add(cmd8);

        cmd9.setForeground(new java.awt.Color(75, 75, 75));
        cmd9.setText("Septiember");
        cmd9.setName("9"); // NOI18N
        cmd9.setOpaque(true);
        add(cmd9);

        cmd10.setForeground(new java.awt.Color(75, 75, 75));
        cmd10.setText("Octubre");
        cmd10.setName("10"); // NOI18N
        cmd10.setOpaque(true);
        add(cmd10);

        cmd11.setForeground(new java.awt.Color(75, 75, 75));
        cmd11.setText("Noviembre");
        cmd11.setName("11"); // NOI18N
        cmd11.setOpaque(true);
        add(cmd11);

        cmd12.setForeground(new java.awt.Color(75, 75, 75));
        cmd12.setText("Diciembre");
        cmd12.setName("12"); // NOI18N
        cmd12.setOpaque(true);
        add(cmd12);
    }// </editor-fold>//GEN-END:initComponents

    private void cmd4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmd4ActionPerformed

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
        addEvent();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.datechooser.Button cmd1;
    private com.raven.datechooser.Button cmd10;
    private com.raven.datechooser.Button cmd11;
    private com.raven.datechooser.Button cmd12;
    private com.raven.datechooser.Button cmd2;
    private com.raven.datechooser.Button cmd3;
    private com.raven.datechooser.Button cmd4;
    private com.raven.datechooser.Button cmd5;
    private com.raven.datechooser.Button cmd6;
    private com.raven.datechooser.Button cmd7;
    private com.raven.datechooser.Button cmd8;
    private com.raven.datechooser.Button cmd9;
    // End of variables declaration//GEN-END:variables

}

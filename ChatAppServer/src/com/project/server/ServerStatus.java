/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.server;

import com.lloseng.ocsf.server.AbstractServer;

import java.awt.CardLayout;
import java.io.IOException;

/**
 *
 * @author Barracuda
 */
public class ServerStatus extends javax.swing.JPanel{

    private static final String CARD_NAME = "SERVER_STATUS";

    private javax.swing.JButton stopServerButton;

    private final AbstractServer server;

    /**
     * Creates new form ServerStatus
     */
    public ServerStatus(AbstractServer server) {
        initComponents();
        this.server = server;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        stopServerButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(33, 33, 33));
        setForeground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.GridBagLayout());

        stopServerButton.setBackground(new java.awt.Color(204, 0, 0));
        stopServerButton.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        stopServerButton.setForeground(new java.awt.Color(255, 255, 255));
        stopServerButton.setText("Stop Server");
        stopServerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopServerEvent(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(stopServerButton, gridBagConstraints);
    }

    private void stopServerEvent(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CardLayout cardLayout = (CardLayout) this.getParent().getLayout();
        cardLayout.show(this.getParent(), StartServer.getCardName());
    }//GEN-LAST:event_jButton1ActionPerformed

    public static String getCardName() {
        return CARD_NAME;
    }

}

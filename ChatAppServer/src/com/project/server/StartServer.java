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
public class StartServer extends javax.swing.JPanel {

    private static final String CARD_NAME = "START_SERVER";

    private final AbstractServer server;

    private javax.swing.JButton startServerButton;

    private javax.swing.JLabel portLabel;

    private javax.swing.JTextField portField;

    public StartServer(AbstractServer server) {
        this.server = server;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        portLabel = new javax.swing.JLabel();
        portField = new javax.swing.JTextField();
        startServerButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(33, 33, 33));
        setLayout(new java.awt.GridBagLayout());

        portLabel.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        portLabel.setForeground(new java.awt.Color(255, 255, 255));
        portLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        portLabel.setText("Port:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(11, 10, 11, 10);
        add(portLabel, gridBagConstraints);

        portField.setColumns(15);
        portField.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(11, 10, 11, 10);
        add(portField, gridBagConstraints);

        startServerButton.setBackground(new java.awt.Color(13, 71, 161));
        startServerButton.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        startServerButton.setForeground(new java.awt.Color(255, 255, 255));
        startServerButton.setText("Start Server");
        startServerButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        startServerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startServerEvent(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        add(startServerButton, gridBagConstraints);
    }

    private void startServerEvent(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            int port = Integer.valueOf(portField.getText());
            if (port < 1024 || port > 65535) {
                portField.setText("The port number must be between 1024 and 65354.");
            }else{
                server.setPort(port);
                server.listen();
                CardLayout cardLayout = (CardLayout) this.getParent().getLayout();
                cardLayout.show(this.getParent(), ServerStatus.getCardName());
            }
        } catch (NumberFormatException | IOException e) {
            portField.setText("The port number is invalid.");
        }
    }

    public static String getCardName() {
        return CARD_NAME;
    }

}

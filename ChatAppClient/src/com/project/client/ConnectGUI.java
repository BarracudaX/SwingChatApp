/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.client;

import com.lloseng.ocsf.client.AbstractClient;
import com.project.User;

import java.awt.CardLayout;
import java.io.IOException;

/**
 *
 * @author Barracuda
 */
public class ConnectGUI extends javax.swing.JPanel {

    private static final String CARD_NAME = "CONNECTION_GUI";

    private javax.swing.JButton connectButton;

    private javax.swing.JLabel hostLabel;

    private javax.swing.JLabel portLabel;

    private javax.swing.JLabel usernameLabel;

    private javax.swing.JTextField host;

    private javax.swing.JTextField port;

    private javax.swing.JTextField username;

    private final AbstractClient client;

    private static volatile User user;

    /**
     * Creates new form ConnectGUI
     *
     * @param client
     */
    public ConnectGUI(AbstractClient client) {
        this.client = client;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        hostLabel = new javax.swing.JLabel();
        portLabel = new javax.swing.JLabel();
        host = new javax.swing.JTextField();
        port = new javax.swing.JTextField();
        connectButton = new javax.swing.JButton();
        usernameLabel = new javax.swing.JLabel();
        username = new javax.swing.JTextField();

        setBackground(new java.awt.Color(33, 33, 33));
        setLayout(new java.awt.GridBagLayout());

        hostLabel.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        hostLabel.setForeground(new java.awt.Color(255, 255, 255));
        hostLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        hostLabel.setText("IP Host:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(8, 9, 8, 9);
        add(hostLabel, gridBagConstraints);

        portLabel.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        portLabel.setForeground(new java.awt.Color(255, 255, 255));
        portLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        portLabel.setText("Port:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(8, 9, 8, 9);
        add(portLabel, gridBagConstraints);

        host.setColumns(15);
        host.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(8, 9, 8, 9);
        add(host, gridBagConstraints);

        port.setColumns(15);
        port.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(8, 9, 8, 9);
        add(port, gridBagConstraints);

        connectButton.setBackground(new java.awt.Color(0, 126, 51));
        connectButton.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        connectButton.setForeground(new java.awt.Color(255, 255, 255));
        connectButton.setText("Connect");
        connectButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectEvent(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(8, 9, 8, 9);
        add(connectButton, gridBagConstraints);


        usernameLabel.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        usernameLabel.setForeground(new java.awt.Color(255, 255, 255));
        usernameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        usernameLabel.setText("Username:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(8, 9, 8, 9);
        add(usernameLabel, gridBagConstraints);

        username.setColumns(15);
        username.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(8, 9, 8, 9);
        add(username, gridBagConstraints);
    }

    private void connectEvent(java.awt.event.ActionEvent evt) {
        String host = this.host.getText().trim();

        try {
            int port = Integer.parseInt(this.port.getText().trim());
            if (port < 1024 || port > 65535) {
                this.port.setText("The port number must be between 1024 and 65354.");
            } else {
                if (username.getText().length() <= 0) {
                    username.setText("Provide a username.");
                }else {
                    user = new User(username.getText().trim());
                    client.setHost(host);
                    client.setPort(port);
                    client.openConnection();
                    CardLayout cardLayout = (CardLayout) this.getParent().getLayout();
                    cardLayout.show(this.getParent(), ChatGUI.getCardName());
                }
            }
        } catch (NumberFormatException | IOException e) {
            port.setText("The port is invalid or the server is unreachable.");
        }

    }

    public static String getCardName(){
        return CARD_NAME;
    }

    public static User getUser(){
        return user;
    }

}

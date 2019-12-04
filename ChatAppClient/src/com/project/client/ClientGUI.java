/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.client;

import com.lloseng.ocsf.client.AbstractClient;
import com.lloseng.ocsf.client.MyClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author Barracuda
 */
public class ClientGUI extends javax.swing.JFrame {

    private final AbstractClient client;

    private com.project.client.ChatGUI chatGui;

    private com.project.client.ConnectGUI connectGui;

    private javax.swing.JPanel mainPanel;

    /**
     * Creates new form ClientGUI
     */
    public ClientGUI() throws UnknownHostException {
        client = new MyClient(null, 0, null);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        connectGui = new com.project.client.ConnectGUI(client);
        chatGui = new com.project.client.ChatGUI(client);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(33, 33, 33));

        mainPanel.setBackground(new java.awt.Color(33, 33, 33));
        mainPanel.setLayout(new java.awt.CardLayout());
        mainPanel.add(connectGui, "CONNECTION_GUI");
        mainPanel.add(chatGui, "CHAT_GUI");

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        pack();
    }

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
            java.util.logging.Logger.getLogger(ClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ClientGUI().setVisible(true);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}

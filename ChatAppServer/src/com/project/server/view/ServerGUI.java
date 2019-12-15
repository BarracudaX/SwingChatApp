/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.server.view;

import com.lloseng.ocsf.server.AbstractServer;
import com.lloseng.ocsf.server.MyServer;
import com.project.server.controller.ServerStatusController;
import com.project.server.controller.StartServerController;
import com.project.server.controller.ViewController;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Barracuda
 */
public class ServerGUI extends JFrame {

    private static final String START_SERVER = "START_SERVER";
    private static final String SERVER_STATUS = "SERVER_STATUS";

    private final AbstractServer server;

    private JPanel mainPanel;

    private final ViewController viewController;

    private final CardLayout cardLayout = new CardLayout();

    /**
     * Creates new form ServerGUI
     */
    public ServerGUI() {
        this.viewController = new ViewController(this);
        server = new MyServer(0);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {
        ServerStatusController serverStatusController = new ServerStatusController(viewController,server);
        StartServerController startServerController = new StartServerController(viewController, server);

        mainPanel = new javax.swing.JPanel();
        StartServer startServerGui = new StartServer(startServerController);
        ServerStatus serverStatusGui = new ServerStatus(serverStatusController);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setBackground(new java.awt.Color(33, 33, 33));
        mainPanel.setLayout(cardLayout);
        mainPanel.add(startServerGui, START_SERVER);
        mainPanel.add(serverStatusGui, SERVER_STATUS);

        setContentPane(mainPanel);

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
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerGUI().setVisible(true);
            }
        });
    }

    public final void switchToStartServer() {
        cardLayout.show(mainPanel,START_SERVER);
    }

    public final void switchToServerStatus(){
        cardLayout.show(mainPanel,SERVER_STATUS);
    }

    public final void showError(String error) {
        JOptionPane.showMessageDialog(this,error,"Error",JOptionPane.ERROR_MESSAGE);
    }

    public final void showMessage(String message) {
        JOptionPane.showMessageDialog(this,message,"Message",JOptionPane.INFORMATION_MESSAGE);
    }

}

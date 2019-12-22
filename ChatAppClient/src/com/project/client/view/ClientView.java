/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.client.view;

import com.lloseng.ocsf.client.MyClient;
import com.project.client.GeneralStyle;
import com.project.client.controller.ConnectController;
import com.project.client.controller.ViewController;
import com.project.client.controller.ChatController;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Barracuda
 */
public class ClientView extends JFrame {

    private static final String CONNECTION_GUI = "CONNECTION_GUI";
    private static final String CHAT_GUI = "CHAT_GUI";

    private final MyClient client;

    private final ViewController viewController;

    private final ConnectController connectController;

    private final CardLayout cardLayout = new CardLayout();

    private final JPanel mainPanel = new JPanel();

    private final GeneralStyle.GeneralStyleBuilder styleBuilder = new GeneralStyle.GeneralStyleBuilder();

    /**
     * Creates new form ClientGUI
     */
    public ClientView() {

        viewController = new ViewController(this);

        client = new MyClient(null, 0);

        connectController = new ConnectController(viewController, client);

        initComponents();
    }

    private void initComponents() {
        styleBuilder.setBackgroundAsDark(mainPanel);

        ConnectionView connectionView = new ConnectionView(connectController);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setLayout(cardLayout);
        mainPanel.add(connectionView, CONNECTION_GUI);

        setContentPane(mainPanel);

        pack();
    }

    public void switchToChatGUI() {

        ChatController controller = new ChatController(viewController, connectController, client);

        ChatView view = new ChatView(controller);

        mainPanel.add(view, CHAT_GUI);
        cardLayout.show(mainPanel, CHAT_GUI);
    }

    public void switchToConnectView() {
        cardLayout.show(mainPanel,CONNECTION_GUI);
    }

    public void showError(String error) {
        JOptionPane.showMessageDialog(this,error,"Error",JOptionPane.ERROR_MESSAGE);
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
            java.util.logging.Logger.getLogger(ClientView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientView().setVisible(true);
            }
        });
    }

}

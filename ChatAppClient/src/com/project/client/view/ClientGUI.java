/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.client.view;

import com.lloseng.ocsf.client.AbstractClient;
import com.lloseng.ocsf.client.MyClient;
import com.project.client.controller.ConnectController;
import com.project.client.controller.ViewController;
import com.project.client.controller.ChatController;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Barracuda
 */
public class ClientGUI extends JFrame {

    private static final String CONNECTION_GUI = "CONNECTION_GUI";
    private static final String CHAT_GUI = "CHAT_GUI";

    private final AbstractClient client;

    private final ViewController viewController;

    private final ConnectController connectController;

    private final CardLayout cardLayout = new CardLayout();

    private ChatController chatController;

    private ChatGUI chatGui;

    private ConnectGUI connectGui;

    private JPanel mainPanel;

    /**
     * Creates new form ClientGUI
     */
    public ClientGUI() {


        viewController = new ViewController(this);

        connectController = new ConnectController(viewController);

        chatController = new ChatController(viewController, connectController);

        client = new MyClient(null, 0,chatController);

        viewController.setClient(client);
        connectController.setClient(client);
        chatController.setClient(client);

        initComponents();
    }

    private void initComponents() {
        connectGui = new ConnectGUI(connectController);
        chatGui = new ChatGUI(chatController);

        chatController.setChatGUI(chatGui);

        mainPanel = new JPanel();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new Color(33, 33, 33));



        mainPanel.setBackground(new Color(33, 33, 33));
        mainPanel.setLayout(cardLayout);
        mainPanel.add(connectGui, CONNECTION_GUI);
        mainPanel.add(chatGui, CHAT_GUI);

        setContentPane(mainPanel);

        pack();
    }

    public void switchToChatGUI(){
        cardLayout.show(mainPanel,CHAT_GUI);
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
                new ClientGUI().setVisible(true);
            }
        });
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.client;

import com.lloseng.ocsf.client.AbstractClient;
import com.lloseng.ocsf.client.MyClient;
import com.project.Message;

import java.awt.CardLayout;
import java.io.IOException;

/**
 * @author Barracuda
 */
public class ChatGUI extends javax.swing.JPanel {

    private static final String CARD_NAME = "CHAT_GUI";

    private javax.swing.JButton disconnectButton;

    private javax.swing.JButton sendButton;

    private javax.swing.JLabel chatLabel;

    private javax.swing.JLabel messageLabel;

    private javax.swing.JScrollPane chatScrollPane;

    private javax.swing.JScrollPane messageScrollPane;

    private javax.swing.JTextArea chatArea;

    private javax.swing.JTextArea messageArea;


    private final AbstractClient client;

    /**
     * Creates new form ChatGUI
     * @param client
     */
    public ChatGUI(AbstractClient client) {
        initComponents();
        this.client = client;
        ((MyClient) client).setChat(chatArea);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        chatLabel = new javax.swing.JLabel();
        chatScrollPane = new javax.swing.JScrollPane();
        chatArea = new javax.swing.JTextArea();
        disconnectButton = new javax.swing.JButton();
        messageLabel = new javax.swing.JLabel();
        messageScrollPane = new javax.swing.JScrollPane();
        messageArea = new javax.swing.JTextArea();
        sendButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(33, 33, 33));
        setLayout(new java.awt.GridBagLayout());

        chatLabel.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        chatLabel.setForeground(new java.awt.Color(255, 255, 255));
        chatLabel.setText("Chat:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(6, 4, 6, 4);
        add(chatLabel, gridBagConstraints);

        chatScrollPane.setBackground(new java.awt.Color(13, 71, 161));

        chatArea.setEditable(false);
        chatArea.setBackground(new java.awt.Color(46, 46, 46));
        chatArea.setColumns(30);
        chatArea.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        chatArea.setForeground(new java.awt.Color(255, 255, 255));
        chatArea.setLineWrap(true);
        chatArea.setRows(10);
        chatArea.setTabSize(5);
        chatArea.setWrapStyleWord(true);
        chatScrollPane.setViewportView(chatArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.6;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 0);
        add(chatScrollPane, gridBagConstraints);

        disconnectButton.setBackground(new java.awt.Color(204, 0, 0));
        disconnectButton.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        disconnectButton.setForeground(new java.awt.Color(255, 255, 255));
        disconnectButton.setText("Disconnect");
        disconnectButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        disconnectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disconnectEvent(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 6);
        add(disconnectButton, gridBagConstraints);

        messageLabel.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        messageLabel.setForeground(new java.awt.Color(255, 255, 255));
        messageLabel.setText("Message:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(6, 4, 6, 4);
        add(messageLabel, gridBagConstraints);

        messageScrollPane.setBackground(new java.awt.Color(13, 71, 161));
        messageScrollPane.setForeground(new java.awt.Color(13, 71, 161));
        messageScrollPane.setAutoscrolls(true);

        messageArea.setBackground(new java.awt.Color(46, 46, 46));
        messageArea.setColumns(30);
        messageArea.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        messageArea.setForeground(new java.awt.Color(255, 255, 255));
        messageArea.setLineWrap(true);
        messageArea.setRows(3);
        messageArea.setWrapStyleWord(true);
        messageScrollPane.setViewportView(messageArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.6;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 0);
        add(messageScrollPane, gridBagConstraints);

        sendButton.setBackground(new java.awt.Color(0, 200, 81));
        sendButton.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        sendButton.setForeground(new java.awt.Color(255, 255, 255));
        sendButton.setText("Send");
        sendButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sendButton.addActionListener(e -> sendMessageEvent(e));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 6);
        add(sendButton, gridBagConstraints);
    }

    private void disconnectEvent(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            client.closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CardLayout cardLayout = (CardLayout) this.getParent().getLayout();
            cardLayout.show(this.getParent(), ConnectGUI.getCardName());
        }
    }

    private void sendMessageEvent(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Message message = new Message(messageArea.getText(), ConnectGUI.getUser());
            client.sendToServer(message);
            messageArea.setText("");
        } catch (IOException e) {
            messageArea.setText("Error while sending to the server...Please try again later.");
        }
    }

    public static String getCardName(){
        return CARD_NAME;
    }

}

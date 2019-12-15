/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.client.view;

import com.project.client.GeneralStyle;
import com.project.client.Utils;
import com.project.client.controller.ChatController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author Barracuda
 */
public class ChatGUI extends JPanel {

    private final ChatController controller;

    private final GeneralStyle.GeneralStyleBuilder styleBuilder = new GeneralStyle.GeneralStyleBuilder();

    private final JButton disconnectButton = new JButton("Disconnect");

    private final JButton sendButton = new JButton("Send Message");

    private final JLabel chatLabel = new JLabel("Chat:");

    private final JLabel messageLabel = new JLabel("Message:");

    private final JTextArea chatArea = new JTextArea();

    private final JTextArea messageArea = new JTextArea();

    public ChatGUI(ChatController controller) {
        this.controller = controller;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());

        JScrollPane chatScrollPane = new JScrollPane(chatArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JScrollPane messageScrollPane = new JScrollPane(messageArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        styleBuilder.setBackgroundAsDark(this)
                .setFont(chatArea, chatLabel, messageArea, messageLabel, sendButton, disconnectButton)
                .setNotEditable(chatArea).setBackgroundAsBlue(sendButton).setBackgroundAsRed(disconnectButton)
                .setForegroundAsWhite(chatArea,messageArea,sendButton,disconnectButton,chatLabel,messageLabel)
                .setTextAreaSize(chatArea,messageArea)
                .setCursorAsHand(disconnectButton,sendButton)
                .setBackgroundAsGrey(chatArea,messageArea);

        Utils.GridBagConstraintBuilder builder = new Utils.GridBagConstraintBuilder();
        builder.reset().setRow(0).setColumn(0).setRowWeight(1).setColumnWeight(1).setFill(Utils.Fill.BOTH)
                .setAnchor(Utils.Anchor.CENTER).setInsets(new Insets(5, 5, 20, 5))
                .setColumnWidth(2);
        add(disconnectButton, builder.build());

        builder.reset().setRow(1).setColumn(0).setRowWeight(1).setColumnWeight(1).setFill(Utils.Fill.BOTH)
                .setAnchor(Utils.Anchor.CENTER).setInsets(new Insets(5, 5, 5, 10));
        add(chatLabel, builder.build());

        builder.reset().setRow(1).setColumn(1).setRowWeight(25).setColumnWeight(25).setFill(Utils.Fill.BOTH)
                .setAnchor(Utils.Anchor.CENTER).setInsets(new Insets(5, 5, 5, 5));
        add(chatScrollPane, builder.build());

        builder.reset().setRow(2).setColumn(0).setRowWeight(1).setColumnWeight(1).setFill(Utils.Fill.BOTH)
                .setAnchor(Utils.Anchor.CENTER).setInsets(new Insets(5, 5, 5, 10));
        add(messageLabel, builder.build());

        builder.reset().setRow(2).setColumn(1).setRowWeight(5).setColumnWeight(25).setFill(Utils.Fill.BOTH)
                .setAnchor(Utils.Anchor.CENTER).setInsets(new Insets(5, 5, 5, 5));
        add(messageScrollPane, builder.build());

        builder.reset().setRow(3).setColumn(0).setRowWeight(1).setColumnWeight(1).setFill(Utils.Fill.BOTH)
                .setAnchor(Utils.Anchor.CENTER).setInsets(new Insets(5, 5, 20, 5)).setColumnWidth(2);
        add(sendButton, builder.build());

        disconnectButton.addActionListener(this::disconnectButtonClick);
        sendButton.addActionListener(this::sendMessageButtonClick);
    }

    private void disconnectButtonClick(ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        controller.disconnect();
    }

    private void sendMessageButtonClick(ActionEvent evt) {
        String message = messageArea.getText();
        messageArea.setText("");
        controller.sendMessage(message);
    }

    public void appendMessage(String message) {
        chatArea.append(message);
    }
}

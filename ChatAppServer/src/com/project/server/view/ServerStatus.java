/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.server.view;

import com.project.server.GeneralStyle;
import com.project.server.Utils;
import com.project.server.controller.ServerStatusController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 *
 * @author Barracuda
 */
public class ServerStatus extends JPanel {

    private final GeneralStyle.GeneralStyleBuilder styleBuilder = new GeneralStyle.GeneralStyleBuilder();

    private final ServerStatusController controller;

    private final JButton stopServerButton = new JButton("Stop Server");

    /**
     * Creates new form ServerStatus
     * @param controller
     */
    public ServerStatus(ServerStatusController controller) {
        this.controller = controller;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {
        setLayout(new GridBagLayout());
        styleBuilder.setBackgroundAsDark(this)
                .setCursorAsHand(stopServerButton)
                .setBackgroundAsRed(stopServerButton)
                .setFont(stopServerButton)
                .setForegroundAsWhite(stopServerButton);


        Utils.GridBagConstraintBuilder builder = new Utils.GridBagConstraintBuilder();
        builder.setColumn(0).setRow(0).setFill(Utils.Fill.HORIZONTAL).setAnchor(Utils.Anchor.CENTER)
                .setColumnWeight(1).setInsets(new Insets(10, 10, 10, 10));
        add(stopServerButton, builder.build());

        stopServerButton.addActionListener(this::stopServerEvent);
    }

    private void stopServerEvent(ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        controller.stopServer();
    }

}

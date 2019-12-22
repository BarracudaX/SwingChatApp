/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.server.view;

import com.project.GeneralStyle;
import com.project.Utils;
import com.project.server.controller.ServerStatusController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Barracuda
 */
public class StatusView extends JPanel {

    private final GeneralStyle.GeneralStyleBuilder styleBuilder = new GeneralStyle.GeneralStyleBuilder();

    private final ServerStatusController controller;

    private final JButton stopServerButton = new JButton("Stop Server");

    private final JLabel portLabel = new JLabel();

    private final JLabel ipLabel = new JLabel();

    private final JLabel clientsLabel = new JLabel();

    private final ExecutorService clientLabelUpdater = Executors.newSingleThreadExecutor();

    /**
     * Creates new form ServerStatus
     *
     * @param controller
     */
    public StatusView(ServerStatusController controller) {
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
                .setFont(stopServerButton, ipLabel, clientsLabel, portLabel)
                .setForegroundAsWhite(stopServerButton, ipLabel, clientsLabel, portLabel);


        Utils.GridBagConstraintBuilder builder = new Utils.GridBagConstraintBuilder();

        builder.setColumn(0).setRow(0).setFill(Utils.Fill.HORIZONTAL).setAnchor(Utils.Anchor.CENTER)
                .setColumnWeight(1).setInsets(new Insets(10, 10, 10, 10));
        add(ipLabel, builder.build());

        builder.setColumn(0).setRow(1).setFill(Utils.Fill.HORIZONTAL).setAnchor(Utils.Anchor.CENTER)
                .setColumnWeight(1).setInsets(new Insets(10, 10, 10, 10));
        add(portLabel, builder.build());

        builder.setColumn(0).setRow(2).setFill(Utils.Fill.HORIZONTAL).setAnchor(Utils.Anchor.CENTER)
                .setColumnWeight(1).setInsets(new Insets(10, 10, 10, 10));
        add(clientsLabel, builder.build());

        builder.setColumn(0).setRow(3).setFill(Utils.Fill.HORIZONTAL).setAnchor(Utils.Anchor.CENTER)
                .setColumnWeight(1).setInsets(new Insets(10, 10, 10, 10));
        add(stopServerButton, builder.build());

        portLabel.setText(String.valueOf("Listening on port " + controller.getPort()));

        ipLabel.setText("Your local IP4 address : " + controller.getIp());

        stopServerButton.addActionListener(this::stopServerEvent);

        startUpdater();
    }

    private void startUpdater() {
        clientLabelUpdater.execute(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
                SwingUtilities.invokeLater(() -> {
                    clientsLabel.setText("Number of clients connected : "
                            + String.valueOf(controller.getNumberOfConnectedUsers()));
                });

            }
        });
    }

    private void stopServerEvent(ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        controller.stopServer();
        clientLabelUpdater.shutdownNow();
    }


}

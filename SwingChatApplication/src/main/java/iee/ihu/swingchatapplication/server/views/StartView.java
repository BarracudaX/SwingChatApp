/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iee.ihu.swingchatapplication.server.views;

import iee.ihu.swingchatapplication.server.controllers.StartServerController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.text.NumberFormat;
import java.util.concurrent.ThreadLocalRandom;
import utils.*;

/**
 * @author Barracuda
 */
public class StartView extends JPanel {

    private final GeneralStyle.GeneralStyleBuilder styleBuilder = new GeneralStyle.GeneralStyleBuilder();

    private final StartServerController controller;

    private final JButton startServerButton = new JButton("Start Server");

    private final JLabel portLabel = new JLabel("Port:");

    private final JButton checkPortButton = new JButton("Check Port");

    private JFormattedTextField portField;


    public StartView(StartServerController controller) {
        this.controller = controller;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {
        setLayout(new GridBagLayout());

        NumberFormat format = NumberFormat.getIntegerInstance();
        format.setMinimumIntegerDigits(4);
        format.setMaximumIntegerDigits(5);
        format.setMaximumFractionDigits(0);

        portField = new JFormattedTextField(format);
        portField.setValue(ThreadLocalRandom.current().nextInt(1024,65536));

        styleBuilder.setBackgroundAsDark(this).setFont(portField, portLabel, startServerButton, checkPortButton)
                .setForegroundAsWhite(portLabel, portField, startServerButton, checkPortButton)
                .setBackgroundAsBlue(startServerButton, checkPortButton)
                .setCursorAsHand(startServerButton, checkPortButton)
                .setHorizontalAlignmentToRight(portLabel)
                .setTextFieldSize(portField)
                .setBackgroundAsGrey(portField);

        Utils.GridBagConstraintBuilder builder = new Utils.GridBagConstraintBuilder();
        builder.setRow(0).setColumn(0).setFill(Utils.Fill.HORIZONTAL).setAnchor(Utils.Anchor.CENTER)
                .setInsets(new Insets(5, 5, 5, 20))
                .setColumnWeight(1);
        add(portLabel, builder.build());

        builder.reset().setRow(0).setColumn(1).setFill(Utils.Fill.HORIZONTAL).setAnchor(Utils.Anchor.CENTER)
                .setInsets(new Insets(5, 5, 5, 5))
                .setColumnWeight(20);
        add(portField, builder.build());

        builder.reset().setRow(1).setColumn(0).setColumnWidth(2).setFill(Utils.Fill.HORIZONTAL).setAnchor(Utils.Anchor.CENTER)
                .setInsets(new Insets(5, 5, 20, 5))
                .setColumnWeight(1);
        add(startServerButton, builder.build());

        builder.reset().setRow(2).setColumn(0).setColumnWidth(2).setFill(Utils.Fill.HORIZONTAL).setAnchor(Utils.Anchor.CENTER)
                .setInsets(new Insets(5, 5, 20, 5))
                .setColumnWeight(1);
        add(checkPortButton, builder.build());

        checkPortButton.addActionListener(this::checkPortClick);
        startServerButton.addActionListener(this::startServerEvent);
        portField.addPropertyChangeListener("value", this::portChanged);
    }

    private void portChanged(PropertyChangeEvent propertyChangeEvent) {
        int port = ((Number) portField.getValue()).intValue();

        if (!controller.validPort(port)) {
            styleBuilder.setBackgroundAsLightRed(portField);
        } else {
            styleBuilder.setBackgroundAsGrey(portField);
        }
    }

    private void checkPortClick(ActionEvent actionEvent) {
        int port = ((Number) portField.getValue()).intValue();

        controller.checkIfPortNumberOpen(port);
    }

    private void startServerEvent(ActionEvent evt) {
        int port = ((Number) portField.getValue()).intValue();

        controller.startServer(port);
    }

}

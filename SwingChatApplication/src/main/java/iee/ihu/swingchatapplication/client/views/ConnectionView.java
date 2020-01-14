/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iee.ihu.swingchatapplication.client.views;

import iee.ihu.swingchatapplication.client.controllers.ConnectController;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.text.NumberFormat;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;
import utils.*;

/**
 *
 * @author Barracuda
 */
public class ConnectionView extends JPanel {

    private final Pattern expression = Pattern.compile("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}");

    private final ConnectController controller;

    private final GeneralStyle.GeneralStyleBuilder styleBuilder = new GeneralStyle.GeneralStyleBuilder();

    private final JButton connectButton = new JButton("Connect");

    private final JLabel hostLabel = new JLabel("IP Host:");

    private final JLabel portLabel = new JLabel("Port:");

    private final JLabel usernameLabel = new JLabel("Username:");

    private final JTextField username = new JTextField();

    private final JTextField host = new JTextField();

    private JFormattedTextField port;


    public ConnectionView(ConnectController controller) {
        this.controller = controller;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());

        NumberFormat portFormat = NumberFormat.getIntegerInstance();
        portFormat.setMinimumIntegerDigits(4);
        portFormat.setMaximumIntegerDigits(5);
        portFormat.setMinimumFractionDigits(0);
        portFormat.setMaximumFractionDigits(0);

        port = new JFormattedTextField(portFormat);

        host.setText("192.168.1.1");
        port.setValue(ThreadLocalRandom.current().nextInt(1024,65535));

        styleBuilder.setBackgroundAsDark(this)
                .setFont(host, port, username, usernameLabel, portLabel, hostLabel, connectButton)
                .setForegroundAsWhite(host, port, username, usernameLabel, portLabel, hostLabel, connectButton)
                .setBackgroundAsBlue(connectButton).setCursorAsHand(connectButton)
                .setHorizontalAlignmentToRight(usernameLabel, portLabel, hostLabel)
                .setTextFieldSize(username, host, port)
                .setBackgroundAsGrey(username, host, port);

        Utils.GridBagConstraintBuilder builder = new Utils.GridBagConstraintBuilder();
        builder.reset().setColumn(0).setRow(0).setFill(Utils.Fill.HORIZONTAL).setAnchor(Utils.Anchor.CENTER)
                .setInsets(new Insets(5, 5, 5, 10)).setColumnWeight(1);
        add(hostLabel, builder.build());

        builder.reset().setColumn(1).setRow(0).setFill(Utils.Fill.HORIZONTAL).setAnchor(Utils.Anchor.CENTER)
                .setInsets(new Insets(5, 5, 5, 5)).setColumnWeight(20);
        add(host, builder.build());

        builder.reset().setColumn(0).setRow(1).setFill(Utils.Fill.HORIZONTAL).setAnchor(Utils.Anchor.CENTER)
                .setInsets(new Insets(5, 5, 5, 10)).setColumnWeight(1);
        add(portLabel, builder.build());

        builder.reset().setColumn(1).setRow(1).setFill(Utils.Fill.HORIZONTAL).setAnchor(Utils.Anchor.CENTER)
                .setInsets(new Insets(5, 5, 5, 5)).setColumnWeight(20);
        add(port, builder.build());


        builder.reset().setColumn(0).setRow(2).setFill(Utils.Fill.HORIZONTAL).setAnchor(Utils.Anchor.CENTER)
                .setInsets(new Insets(5, 5, 5, 10)).setColumnWeight(1);
        add(usernameLabel, builder.build());

        builder.reset().setColumn(1).setRow(2).setFill(Utils.Fill.HORIZONTAL).setAnchor(Utils.Anchor.CENTER)
                .setInsets(new Insets(5, 5, 5, 5)).setColumnWeight(20);
        add(username, builder.build());

        builder.reset().setColumn(1).setRow(3).setFill(Utils.Fill.HORIZONTAL).setAnchor(Utils.Anchor.CENTER)
                .setInsets(new Insets(20, 5, 5, 5)).setColumnWeight(1).setColumnWidth(2);
        add(connectButton, builder.build());

        connectButton.addActionListener(this::connectButtonClick);
        port.addPropertyChangeListener("value", this::portChanged);
        host.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                ConnectionView.this.hostChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                ConnectionView.this.hostChanged();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                ConnectionView.this.hostChanged();
            }

        });
    }

    private void portChanged(PropertyChangeEvent propertyChangeEvent) {
        int portNumber = ((Number) port.getValue()).intValue();

        if (portNumber < 1024 || portNumber > 65_535) {
            styleBuilder.setBackgroundAsLightRed(port);
        } else {
            styleBuilder.setBackgroundAsGrey(port);
        }

    }

    private boolean hostChanged(){
        if (!expression.matcher(host.getText()).matches()) {
            styleBuilder.setBackgroundAsLightRed(host);
            return false;
        }else{
            styleBuilder.setBackgroundAsGrey(host);
            return true;
        }
    }

    private void connectButtonClick(ActionEvent evt) {
        if (!hostChanged()) {
            controller.wrongHost();
            return;
        }

        int portValue = ((Number)port.getValue()).intValue();
        String usernameValue = username.getText().trim();
        String hostValue = host.getText().trim();

        controller.connect(portValue, hostValue, usernameValue);

    }


}

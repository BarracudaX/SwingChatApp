/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apps;

import client.controllers.ClientViewController;
import server.MyServer;
import server.controllers.ServerStatusController;
import server.controllers.ServerViewController;
import server.controllers.StartServerController;
import server.views.StartView;
import server.views.StatusView;
import javax.swing.*;
import java.awt.*;
import java.net.UnknownHostException;
import utils.GeneralStyle;

/**
 *
 * @author Barracuda
 */
public class ServerView extends JFrame {

    private static final String START_SERVER = "START_SERVER";
    private static final String SERVER_STATUS = "SERVER_STATUS";

    private final GeneralStyle.GeneralStyleBuilder styleBuilder = new GeneralStyle.GeneralStyleBuilder();

    private final MyServer server;

    private JPanel mainPanel;

    private final ServerViewController viewController;

    private final CardLayout cardLayout = new CardLayout();

    private final ServerStatusController serverStatusController ;

    private final StartServerController startServerController ;


    /**
     * Creates new form ServerGUI
     */
    public ServerView() throws UnknownHostException {
        this.viewController = new ServerViewController(this);
        server = new MyServer(0);
        serverStatusController = new ServerStatusController(viewController, server);
        startServerController = new StartServerController(viewController, server);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {
        mainPanel = new javax.swing.JPanel();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setLayout(cardLayout);
        styleBuilder.setBackgroundAsDark(mainPanel);

        StartView view = new StartView(startServerController);
        mainPanel.add(view, START_SERVER);

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
            java.util.logging.Logger.getLogger(ServerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ServerView().setVisible(true);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public final void switchToStartServer() {
        cardLayout.show(mainPanel, START_SERVER);
    }

    public final void switchToServerStatus(int port) {
        StatusView view = new StatusView(serverStatusController);

        mainPanel.add(view, SERVER_STATUS);
        cardLayout.show(mainPanel, SERVER_STATUS);
    }

    public final void showError(String error) {
        JOptionPane.showMessageDialog(this,error,"Error",JOptionPane.ERROR_MESSAGE);
    }

    public final void showMessage(String message) {
        JOptionPane.showMessageDialog(this,message,"Message",JOptionPane.INFORMATION_MESSAGE);
    }

}

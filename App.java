package vn.viettuts.qlsv;

import java.awt.EventQueue;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

import vn.viettuts.qlsv.controller.LoginController;
import vn.viettuts.qlsv.view.LoginView;

public class App {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
            SwingUtilities.invokeLater(() -> {
            	LoginView loginView = new LoginView();
            	LoginController loginController = new LoginController(loginView);
            	loginView.setVisible(true);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
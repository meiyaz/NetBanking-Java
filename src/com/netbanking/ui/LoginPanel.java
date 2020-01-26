package com.netbanking.ui;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends BasePanel {

    public LoginPanel(NetBankingFrame controller) {
        super(controller);
        initUI();
    }

    private void initUI() {
        JLabel lTitle = new JLabel("MK BANK OF INDIA");
        lTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lTitle.setBounds(280, 50, 300, 30);
        add(lTitle);

        JLabel lSub = new JLabel("WELCOME TO NET BANKING SERVICE");
        lSub.setBounds(290, 90, 300, 20);
        add(lSub);

        JLabel lUser = new JLabel("USER ID :");
        lUser.setBounds(250, 150, 100, 30);
        add(lUser);

        JTextField txtUser = new JTextField();
        txtUser.setBounds(350, 150, 150, 30);
        add(txtUser);

        JLabel lPass = new JLabel("PASSWORD :");
        lPass.setBounds(250, 200, 100, 30);
        add(lPass);

        JPasswordField txtPass = new JPasswordField();
        txtPass.setBounds(350, 200, 150, 30);
        add(txtPass);

        JButton btnLogin = new JButton("LOGIN");
        btnLogin.setBounds(350, 260, 100, 30);
        btnLogin.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Login Successful");
            controller.showCard("Dashboard");
        });
        add(btnLogin);
    }
}

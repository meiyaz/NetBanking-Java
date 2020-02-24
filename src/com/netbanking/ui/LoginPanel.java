package com.netbanking.ui;

import javax.swing.*;
import java.awt.*;
import com.netbanking.model.DataManager;
import com.netbanking.model.User;

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
        lSub.setBounds(270, 90, 400, 20);
        add(lSub);

        JLabel lUser = new JLabel("CIF NUMBER :");
        lUser.setBounds(220, 150, 130, 30);
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
            String accNo = txtUser.getText();
            String pass = new String(txtPass.getPassword());
            
            User dbUser = DataManager.loadUser(accNo, pass);
            
            if (dbUser != null) {
                txtUser.setText("");
                txtPass.setText("");
                JOptionPane.showMessageDialog(this, "Login Successful");
                controller.login(dbUser);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid CIF Number or Password!");
            }
        });
        add(btnLogin);
        
        JButton btnRegister = new JButton("CREATE ACCOUNT");
        btnRegister.setBounds(330, 320, 150, 30);
        btnRegister.addActionListener(e -> controller.showCard("Register"));
        add(btnRegister);
    }
}

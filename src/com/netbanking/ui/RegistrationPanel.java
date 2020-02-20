package com.netbanking.ui;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import com.netbanking.model.DataManager;
import com.netbanking.model.User;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistrationPanel extends BasePanel {

    public RegistrationPanel(NetBankingFrame controller) {
        super(controller);
        initUI();
    }

    private void initUI() {
        JLabel lTitle = new JLabel("NEW ACCOUNT REGISTRATION");
        lTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lTitle.setBounds(250, 30, 350, 30);
        add(lTitle);

        JLabel lName = new JLabel("FULL NAME :");
        lName.setBounds(200, 100, 130, 30);
        add(lName);

        JTextField tName = new JTextField();
        tName.setBounds(350, 100, 200, 30);
        add(tName);

        JLabel lPhone = new JLabel("PHONE NUMBER :");
        lPhone.setBounds(200, 150, 130, 30);
        add(lPhone);

        JTextField tPhone = new JTextField();
        tPhone.setBounds(350, 150, 200, 30);
        add(tPhone);

        JLabel lAddress = new JLabel("ADDRESS :");
        lAddress.setBounds(200, 200, 130, 30);
        add(lAddress);

        JTextField tAddress = new JTextField();
        tAddress.setBounds(350, 200, 200, 30);
        add(tAddress);
        
        JLabel lPass = new JLabel("SET PASSWORD :");
        lPass.setBounds(200, 250, 130, 30);
        add(lPass);

        JPasswordField tPass = new JPasswordField();
        tPass.setBounds(350, 250, 200, 30);
        add(tPass);

        JButton btnRegister = new JButton("REGISTER");
        btnRegister.setBounds(350, 320, 150, 30);
        btnRegister.addActionListener(e -> {
            String name = tName.getText();
            String phone = tPhone.getText();
            String address = tAddress.getText();
            String pass = new String(tPass.getPassword());

            if (name.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name and Password are required!");
                return;
            }

            // Generate 5-digit CIF
            String cif = String.format("%05d", new Random().nextInt(100000));
            // Generate 7-digit Account Number
            String accNo = String.format("%07d", new Random().nextInt(10000000));
            
            String date = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());

            User newUser = new User(name, cif, accNo, address, phone, "SELF", date, "**** **** **** 1234", "12/28", 0.0);
            newUser.setPassword(pass);
            
            DataManager.saveUser(newUser);

            JOptionPane.showMessageDialog(this, "Registration Successful!\n\nYour CIF Number: " + cif + "\nYour Account Number: " + accNo + "\n\nPlease login using your CIF Number.");
            controller.showCard("Login");
        });
        add(btnRegister);

        JButton btnBack = new JButton("BACK TO LOGIN");
        btnBack.setBounds(350, 370, 150, 30);
        btnBack.addActionListener(e -> controller.showCard("Login"));
        add(btnBack);
    }
}

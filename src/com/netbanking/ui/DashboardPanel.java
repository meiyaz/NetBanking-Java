package com.netbanking.ui;

import javax.swing.*;
import java.awt.*;
import com.netbanking.model.User;

public class DashboardPanel extends BasePanel {
    private User user;

    public DashboardPanel(NetBankingFrame controller, User user) {
        super(controller);
        this.user = user;
        initUI();
    }

    private void initUI() {
        JLabel lblWelcome = new JLabel("Welcome, " + user.getName());
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 18));
        lblWelcome.setBounds(50, 30, 400, 30);
        add(lblWelcome);

        JButton btnProfile = new JButton("USER PROFILE");
        btnProfile.setBounds(100, 100, 200, 40);
        btnProfile.addActionListener(e -> controller.showCard("Profile"));
        add(btnProfile);

        JButton btnBalance = new JButton("CHECK BALANCE");
        btnBalance.setBounds(400, 100, 200, 40);
        btnBalance.addActionListener(e -> controller.showCard("Balance"));
        add(btnBalance);

        JButton btnTransfer = new JButton("TRANSFER CASH");
        btnTransfer.setBounds(100, 200, 200, 40);
        btnTransfer.addActionListener(e -> controller.showCard("Transfer"));
        add(btnTransfer);

        JButton btnComplaint = new JButton("COMPLAINTS");
        btnComplaint.setBounds(400, 200, 200, 40);
        btnComplaint.addActionListener(e -> controller.showCard("Complaints"));
        add(btnComplaint);
        
        JButton btnLogout = new JButton("LOGOUT");
        btnLogout.setBounds(250, 350, 200, 40);
        btnLogout.addActionListener(e -> controller.showCard("Login"));
        add(btnLogout);
    }
}

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
        lblWelcome.setBounds(50, 20, 400, 30);
        add(lblWelcome);

        // Container for buttons
        JPanel gridPanel = new JPanel(new GridLayout(3, 3, 15, 15));
        gridPanel.setBounds(50, 70, 700, 400);

        // Add Buttons in logical order
        gridPanel.add(createTile("PROFILE", e -> controller.showCard("Profile")));
        gridPanel.add(createTile("BALANCE", e -> controller.showCard("Balance")));
        gridPanel.add(createTile("TRANSFER", e -> controller.showCard("Transfer")));
        
        gridPanel.add(createTile("HISTORY", e -> controller.showCard("Transactions")));
        gridPanel.add(createTile("BENEFICIARIES", e -> controller.showCard("Beneficiaries")));
        gridPanel.add(createTile("BILLS", e -> controller.showCard("Bills")));
        
        gridPanel.add(createTile("DEPOSITS (FD)", e -> controller.showCard("FixedDeposit")));
        gridPanel.add(createTile("COMPLAINTS", e -> controller.showCard("Complaints")));
        gridPanel.add(createTile("SETTINGS", e -> controller.showCard("Settings")));

        add(gridPanel);
        
        JButton btnLogout = new JButton("LOGOUT");
        btnLogout.setBounds(50, 500, 100, 30);
        btnLogout.setForeground(Color.RED);
        btnLogout.addActionListener(e -> controller.showCard("Login"));
        add(btnLogout);
    }
    
    private JButton createTile(String title, java.awt.event.ActionListener action) {
        JButton btn = new JButton(title);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.addActionListener(action);
        return btn;
    }
}

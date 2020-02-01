package com.netbanking.ui;

import javax.swing.*;
import java.awt.*;
import com.netbanking.model.User;

public class SettingsPanel extends BasePanel {
    private User user;

    public SettingsPanel(NetBankingFrame controller, User user) {
        super(controller);
        this.user = user;
        initUI();
    }

    private void initUI() {
        JLabel lTitle = new JLabel("SETTINGS & SECURITY");
        lTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lTitle.setBounds(300, 30, 300, 30);
        add(lTitle);

        JLabel lPass = new JLabel("CHANGE PASSWORD");
        lPass.setFont(new Font("Arial", Font.BOLD, 14));
        lPass.setBounds(50, 100, 200, 20);
        add(lPass);

        addInput("Current Password:", 100, 150, 150);
        JPasswordField tOld = new JPasswordField(); tOld.setBounds(300, 150, 200, 30); add(tOld);

        addInput("New Password:", 100, 200, 150);
        JPasswordField tNew = new JPasswordField(); tNew.setBounds(300, 200, 200, 30); add(tNew);

        addInput("Confirm Password:", 100, 250, 150);
        JPasswordField tConfirm = new JPasswordField(); tConfirm.setBounds(300, 250, 200, 30); add(tConfirm);

        JButton btnChange = new JButton("UPDATE PASSWORD");
        btnChange.setBounds(300, 300, 200, 30);
        add(btnChange);

        btnChange.addActionListener(e -> {
            String oldP = new String(tOld.getPassword());
            String newP = new String(tNew.getPassword());
            String confP = new String(tConfirm.getPassword());

            if (!oldP.equals(user.getPassword())) {
                JOptionPane.showMessageDialog(this, "Incorrect Current Password");
            } else if (newP.isEmpty()) {
                 JOptionPane.showMessageDialog(this, "New Password cannot be empty");
            } else if (!newP.equals(confP)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match");
            } else {
                user.setPassword(newP);
                JOptionPane.showMessageDialog(this, "Password Changed Successfully!");
                tOld.setText(""); tNew.setText(""); tConfirm.setText("");
            }
        });
        
        // App Info
        JLabel lInfo = new JLabel("NetBanking App v1.2 (Simulated)");
        lInfo.setBounds(300, 450, 300, 20);
        add(lInfo);

        addBackButton();
    }

    private void addInput(String label, int x, int y, int w) {
        JLabel l = new JLabel(label);
        l.setBounds(x, y, w, 30);
        add(l);
    }
}

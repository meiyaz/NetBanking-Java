package com.netbanking.ui;

import javax.swing.*;
import java.awt.*;
import com.netbanking.model.User;

public class ProfilePanel extends BasePanel {
    private User user;

    public ProfilePanel(NetBankingFrame controller, User user) {
        super(controller);
        this.user = user;
        initUI();
    }

    private void initUI() {
        JLabel lTitle = new JLabel("USER PROFILE");
        lTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lTitle.setBounds(300, 30, 200, 30);
        add(lTitle);

        int y = 100;
        addProfileField("USER NAME :", user.getName(), y); y += 50;
        addProfileField("ACCOUNT NO :", user.getAccountNumber(), y); y += 50;
        addProfileField("ADDRESS :", user.getAddress(), y); y += 50;
        addProfileField("PHONE NO :", user.getPhoneNumber(), y); y += 50;
        addProfileField("MODE :", user.getMode(), y); y += 50;
        addProfileField("OPENING DATE :", user.getDateOfOpening(), y); y += 50;
        addProfileField("ATM NO :", user.getAtmNumber(), y); y += 50;
        addProfileField("VALIDITY :", user.getCardValidity(), y);

        addBackButton();
    }

    private void addProfileField(String label, String value, int y) {
        JLabel l = new JLabel(label);
        l.setBounds(100, y, 150, 30);
        add(l);
        
        JTextField t = new JTextField(value);
        t.setBounds(300, y, 250, 30);
        t.setEditable(false);
        add(t);
    }
}

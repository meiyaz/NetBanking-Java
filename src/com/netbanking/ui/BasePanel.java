package com.netbanking.ui;

import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class BasePanel extends JPanel {
    protected NetBankingFrame controller;

    public BasePanel(NetBankingFrame controller) {
        this.controller = controller;
        setLayout(null);
    }

    protected void addBackButton() {
        JButton b = new JButton("BACK");
        b.setBounds(50, 500, 100, 30);
        b.addActionListener(e -> controller.showCard("Dashboard"));
        add(b);
    }
}

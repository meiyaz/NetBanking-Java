package com.netbanking.ui;

import javax.swing.*;
import java.awt.*;
import com.netbanking.model.User;

public class BalancePanel extends BasePanel {
    private User user;

    public BalancePanel(NetBankingFrame controller, User user) {
        super(controller);
        this.user = user;
        initUI();
    }

    private void initUI() {
        JLabel lTitle = new JLabel("VERIFY BALANCE");
        lTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lTitle.setBounds(300, 50, 300, 30);
        add(lTitle);

        JLabel lBal = new JLabel("CURRENT BALANCE :");
        lBal.setBounds(150, 200, 150, 30);
        add(lBal);
        
        JTextField tBal = new JTextField("Rs. " + user.getBalance());
        tBal.setBounds(350, 200, 200, 30);
        tBal.setEditable(false);
        add(tBal);

        addBackButton();
    }
}

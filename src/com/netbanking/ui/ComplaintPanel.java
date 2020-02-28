package com.netbanking.ui;

import javax.swing.*;
import java.awt.*;

public class ComplaintPanel extends BasePanel {

    public ComplaintPanel(NetBankingFrame controller) {
        super(controller);
        initUI();
    }

    private void initUI() {
        JLabel lTitle = new JLabel("COMPLAINT BOX");
        lTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lTitle.setBounds(300, 30, 250, 30);
        add(lTitle);

        JLabel lDesc = new JLabel("DESCRIBE YOUR ISSUE:");
        lDesc.setBounds(50, 80, 200, 30);
        add(lDesc);

        JTextArea txtArea = new JTextArea();
        txtArea.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(txtArea);
        scroll.setBounds(50, 120, 700, 200);
        add(scroll);

        JButton btnSubmit = new JButton("SUBMIT COMPLAINT");
        btnSubmit.setBounds(250, 340, 200, 40);
        add(btnSubmit);
        
        btnSubmit.addActionListener(e -> {
            if (txtArea.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complaint cannot be empty!");
            } else {
                JOptionPane.showMessageDialog(this, "Complaint Registered Successfully! Reference ID: " + (int)(Math.random()*10000));
                txtArea.setText("");
            }
        });

        JLabel lContact = new JLabel("CUSTOMER CARE : 95249 14630");
        lContact.setBounds(450, 400, 300, 30);
        add(lContact);

        JLabel lEmail = new JLabel("EMAIL: mknetbanking@gmail.com");
        lEmail.setBounds(450, 440, 300, 30);
        add(lEmail);

        addBackButton();
    }
}

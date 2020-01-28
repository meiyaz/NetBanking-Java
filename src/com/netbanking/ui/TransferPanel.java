package com.netbanking.ui;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.netbanking.model.User;
import com.netbanking.model.Transaction;

public class TransferPanel extends BasePanel {
    private User user;

    public TransferPanel(NetBankingFrame controller, User user) {
        super(controller);
        this.user = user;
        initUI();
    }

    private void initUI() {
        JLabel lTitle = new JLabel("BALANCE TRANSFER");
        lTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lTitle.setBounds(300, 30, 250, 30);
        add(lTitle);

        JLabel lBal = new JLabel("CURRENT BALANCE :");
        lBal.setBounds(100, 100, 150, 30);
        add(lBal);
        
        JLabel lCurrentBal = new JLabel("Rs. " + user.getBalance());
        lCurrentBal.setBounds(300, 100, 200, 30);
        add(lCurrentBal);

        JLabel lAcc = new JLabel("PARTNER'S ACC NO:");
        lAcc.setBounds(100, 150, 150, 30);
        add(lAcc);
        
        JTextField tAcc = new JTextField();
        tAcc.setBounds(300, 150, 200, 30);
        add(tAcc);

        JLabel lAmt = new JLabel("AMOUNT TO TRANSFER :");
        lAmt.setBounds(100, 200, 150, 30);
        add(lAmt);
        
        JTextField tAmt = new JTextField();
        tAmt.setBounds(300, 200, 200, 30);
        add(tAmt);
        
        JButton btnSubmit = new JButton("TRANSFER");
        btnSubmit.setBounds(250, 300, 150, 30);
        add(btnSubmit);

        btnSubmit.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(tAmt.getText());
                if (amount <= 0) {
                     JOptionPane.showMessageDialog(this, "Invalid Amount");
                } else if (amount > user.getBalance()) {
                    JOptionPane.showMessageDialog(this, "Insufficient Balance");
                } else {
                    user.setBalance(user.getBalance() - amount);
                    
                    String date = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
                    Transaction t = new Transaction(date, "DEBIT", "Transfer to " + tAcc.getText(), amount, user.getBalance());
                    user.addTransaction(t);
                    
                    lCurrentBal.setText("Rs. " + user.getBalance());
                    JOptionPane.showMessageDialog(this, "Transfer Successful! New Balance: " + user.getBalance());
                    tAmt.setText("");
                    tAcc.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number");
            }
        });

        addBackButton();
    }
}

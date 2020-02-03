package com.netbanking.ui;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.netbanking.model.User;
import com.netbanking.model.Transaction;

public class BillPaymentPanel extends BasePanel {
    private User user;

    public BillPaymentPanel(NetBankingFrame controller, User user) {
        super(controller);
        this.user = user;
        initUI();
    }

    private void initUI() {
        JLabel lTitle = new JLabel("BILL PAYMENTS");
        lTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lTitle.setBounds(300, 30, 250, 30);
        add(lTitle);

        JLabel lType = new JLabel("SELECT BILL TYPE:");
        lType.setBounds(100, 100, 150, 30);
        add(lType);

        String[] billTypes = {"Electricity Bill", "Mobile Recharge", "DTH Recharge", "Broadband Bill"};
        JComboBox<String> cbType = new JComboBox<>(billTypes);
        cbType.setBounds(300, 100, 200, 30);
        add(cbType);

        JLabel lConsumer = new JLabel("CONSUMER / MOBILE NO:");
        lConsumer.setBounds(100, 150, 200, 30);
        add(lConsumer);
        
        JTextField tConsumer = new JTextField();
        tConsumer.setBounds(300, 150, 200, 30);
        add(tConsumer);

        JLabel lAmt = new JLabel("AMOUNT:");
        lAmt.setBounds(100, 200, 150, 30);
        add(lAmt);
        
        JTextField tAmt = new JTextField();
        tAmt.setBounds(300, 200, 200, 30);
        add(tAmt);
        
        JButton btnPay = new JButton("PAY NOW");
        btnPay.setBounds(250, 280, 150, 30);
        add(btnPay);

        btnPay.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(tAmt.getText());
                String type = (String) cbType.getSelectedItem();
                
                if (tConsumer.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter Consumer/Mobile Number");
                } else if (amount <= 0) {
                     JOptionPane.showMessageDialog(this, "Invalid Amount");
                } else if (amount > user.getBalance()) {
                    JOptionPane.showMessageDialog(this, "Insufficient Balance");
                } else {
                    user.setBalance(user.getBalance() - amount);
                    
                    String date = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
                    String desc = type + " - " + tConsumer.getText();
                    Transaction t = new Transaction(date, "DEBIT", desc, amount, user.getBalance());
                    user.addTransaction(t);
                    
                    JOptionPane.showMessageDialog(this, "Payment Successful! New Balance: " + user.getBalance());
                    tAmt.setText("");
                    tConsumer.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid amount");
            }
        });

        addBackButton();
    }
}

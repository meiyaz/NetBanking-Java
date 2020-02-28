package com.netbanking.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.netbanking.model.User;
import com.netbanking.model.FixedDeposit;
import com.netbanking.model.Transaction;

public class FixedDepositPanel extends BasePanel {
    private User user;
    private JTable table;
    private DefaultTableModel tableModel;

    public FixedDepositPanel(NetBankingFrame controller, User user) {
        super(controller);
        this.user = user;
        initUI();
    }

    private void initUI() {
        JLabel lTitle = new JLabel("FIXED DEPOSIT (FD)");
        lTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lTitle.setBounds(300, 20, 300, 30);
        add(lTitle);

        JLabel lNew = new JLabel("OPEN NEW FD");
        lNew.setFont(new Font("Arial", Font.BOLD, 14));
        lNew.setBounds(50, 70, 200, 20);
        add(lNew);

        addInput("Principal:", 50, 100, 100);
        JTextField tPrincipal = new JTextField(); tPrincipal.setBounds(150, 100, 150, 25); add(tPrincipal);

        addInput("Duration (Months):", 350, 100, 120);
        String[] durations = {"6", "12", "24", "36", "60"};
        JComboBox<String> cbDuration = new JComboBox<>(durations);
        cbDuration.setBounds(500, 100, 100, 25); add(cbDuration);

        addInput("Interest Rate:", 50, 140, 100);
        JTextField tRate = new JTextField("6.5"); tRate.setEditable(false); tRate.setBounds(150, 140, 150, 25); add(tRate);

        addInput("Maturity Amount:", 350, 140, 120);
        JTextField tMaturity = new JTextField(); tMaturity.setEditable(false); tMaturity.setBounds(500, 140, 150, 25); add(tMaturity);

        JButton btnCalc = new JButton("CALCULATE");
        btnCalc.setBounds(150, 180, 120, 30);
        add(btnCalc);

        JButton btnBook = new JButton("BOOK FD");
        btnBook.setBounds(300, 180, 120, 30);
        add(btnBook);
        
        btnCalc.addActionListener(e -> {
            calculateMaturity(tPrincipal, cbDuration, tRate, tMaturity);
        });

        btnBook.addActionListener(e -> {
            bookFD(tPrincipal, cbDuration, tRate, tMaturity);
        });
        
        JLabel lActive = new JLabel("YOUR ACTIVE DEPOSITS");
        lActive.setFont(new Font("Arial", Font.BOLD, 14));
        lActive.setBounds(50, 240, 300, 20);
        add(lActive);
        
        String[] columns = {"ID", "Principal", "Tenure", "Rate", "Maturity", "Date"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(50, 270, 700, 200);
        add(scroll);

        addBackButton();
        refreshData();
    }
    
    private void calculateMaturity(JTextField tP, JComboBox<String> cbD, JTextField tR, JTextField tM) {
        try {
            double p = Double.parseDouble(tP.getText());
            int m = Integer.parseInt((String)cbD.getSelectedItem());
            double r = Double.parseDouble(tR.getText());
            
            double tVar = m / 12.0;
            double maturity = p * (1 + (r * tVar / 100.0));
            tM.setText(String.format("%.2f", maturity));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Principal");
        }
    }

    private void bookFD(JTextField tP, JComboBox<String> cbD, JTextField tR, JTextField tM) {
        try {
             if (tM.getText().isEmpty()) calculateMaturity(tP, cbD, tR, tM);
             
             double p = Double.parseDouble(tP.getText());
             double amt = Double.parseDouble(tM.getText());
             int moons = Integer.parseInt((String)cbD.getSelectedItem());
             double rate = Double.parseDouble(tR.getText());

             if (p > user.getBalance()) {
                 JOptionPane.showMessageDialog(this, "Insufficient Balance");
                 return;
             }

             user.setBalance(user.getBalance() - p);
             
             String date = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
             FixedDeposit fd = new FixedDeposit("FD" + (int)(Math.random()*10000), p, moons, rate, amt, date);
             user.addFixedDeposit(fd);
             
             Transaction t = new Transaction(date, "DEBIT", "FD Booking: " + fd.getId(), p, user.getBalance());
             user.addTransaction(t);
             
             JOptionPane.showMessageDialog(this, "FD Booked Successfully! ID: " + fd.getId());
             refreshData();
             tP.setText(""); tM.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error in booking");
        }
    }

    private void addInput(String label, int x, int y, int w) {
        JLabel l = new JLabel(label);
        l.setBounds(x, y, w, 25);
        add(l);
    }
    
    public void refreshData() {
        tableModel.setRowCount(0);
        List<FixedDeposit> list = user.getFixedDeposits();
        for (FixedDeposit fd : list) {
            tableModel.addRow(fd.toRow());
        }
    }
}

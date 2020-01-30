package com.netbanking.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import com.netbanking.model.User;
import com.netbanking.model.Beneficiary;

public class BeneficiaryPanel extends BasePanel {
    private User user;
    private JTable table;
    private DefaultTableModel tableModel;

    public BeneficiaryPanel(NetBankingFrame controller, User user) {
        super(controller);
        this.user = user;
        initUI();
    }

    private void initUI() {
        JLabel lTitle = new JLabel("MANAGE BENEFICIARIES");
        lTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lTitle.setBounds(300, 30, 300, 30);
        add(lTitle);

        // --- List Section ---
        String[] columns = {"Name", "Account No", "Bank", "IFSC", "Limit"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(50, 80, 700, 200);
        add(scroll);

        // --- Add Section ---
        JLabel lAdd = new JLabel("ADD NEW BENEFICIARY");
        lAdd.setFont(new Font("Arial", Font.BOLD, 14));
        lAdd.setBounds(50, 300, 200, 20);
        add(lAdd);

        addInput("Name:", 50, 330, 100);
        JTextField tName = new JTextField(); tName.setBounds(120, 330, 150, 25); add(tName);

        addInput("Acc No:", 300, 330, 100);
        JTextField tAcc = new JTextField(); tAcc.setBounds(370, 330, 150, 25); add(tAcc);

        addInput("Bank:", 550, 330, 100);
        JTextField tBank = new JTextField(); tBank.setBounds(600, 330, 150, 25); add(tBank);

        addInput("IFSC:", 50, 370, 100);
        JTextField tIfsc = new JTextField(); tIfsc.setBounds(120, 370, 150, 25); add(tIfsc);

        addInput("Limit:", 300, 370, 100);
        JTextField tLimit = new JTextField(); tLimit.setBounds(370, 370, 150, 25); add(tLimit);

        JButton btnAdd = new JButton("ADD BENEFICIARY");
        btnAdd.setBounds(550, 370, 200, 30);
        add(btnAdd);

        btnAdd.addActionListener(e -> {
            try {
                String name = tName.getText();
                String acc = tAcc.getText();
                String bank = tBank.getText();
                String ifsc = tIfsc.getText();
                double limit = Double.parseDouble(tLimit.getText());

                if (name.isEmpty() || acc.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all fields");
                } else {
                    Beneficiary b = new Beneficiary(name, acc, bank, ifsc, limit);
                    user.addBeneficiary(b);
                    refreshData();
                    JOptionPane.showMessageDialog(this, "Beneficiary Added Successfully");
                    tName.setText(""); tAcc.setText(""); tBank.setText(""); tIfsc.setText(""); tLimit.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid Limit");
            }
        });

        addBackButton();
        refreshData();
    }
    
    private void addInput(String label, int x, int y, int w) {
        JLabel l = new JLabel(label);
        l.setBounds(x, y, w, 25);
        add(l);
    }

    public void refreshData() {
        tableModel.setRowCount(0);
        List<Beneficiary> list = user.getBeneficiaries();
        for (Beneficiary b : list) {
            tableModel.addRow(b.toRow());
        }
    }
}

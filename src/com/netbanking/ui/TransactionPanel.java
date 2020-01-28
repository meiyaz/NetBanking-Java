package com.netbanking.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import com.netbanking.model.User;
import com.netbanking.model.Transaction;

public class TransactionPanel extends BasePanel {
    private User user;
    private JTable table;
    private DefaultTableModel tableModel;

    public TransactionPanel(NetBankingFrame controller, User user) {
        super(controller);
        this.user = user;
        initUI();
    }

    private void initUI() {
        JLabel lTitle = new JLabel("TRANSACTION HISTORY");
        lTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lTitle.setBounds(300, 30, 300, 30);
        add(lTitle);

        String[] columns = {"Date", "Type", "Description", "Amount", "Balance"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(50, 80, 700, 350);
        add(scroll);
        
        JButton btnRefresh = new JButton("REFRESH");
        btnRefresh.setBounds(350, 450, 100, 30);
        btnRefresh.addActionListener(e -> refreshData());
        add(btnRefresh);

        addBackButton();
        refreshData();
    }

    public void refreshData() {
        tableModel.setRowCount(0);
        List<Transaction> list = user.getTransactions();
        for (Transaction t : list) {
            tableModel.addRow(t.toRow());
        }
    }
}

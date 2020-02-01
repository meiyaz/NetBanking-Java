package com.netbanking.ui;

import javax.swing.*;
import java.awt.*;
import com.netbanking.model.User;

public class NetBankingFrame extends JFrame {
    
    CardLayout cardLayout;
    JPanel mainPanel;
    User currentUser;

    public NetBankingFrame() {
        super("MK BANK OF INDIA");
        
        // Initialize User Data
        currentUser = new User("MEIYAZHAGAN KULANDAIVEL", "6078 5184 1274 3157", "NAMAKKAL", 
                               "90803 35279", "SELF", "11 FEB 2011", 
                               "**** **** **** *852", "24 - AUG - 2020", 2500000);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        // Add Panels
        // Pass 'this' as controller to panels
        mainPanel.add(new LoginPanel(this), "Login");
        mainPanel.add(new DashboardPanel(this, currentUser), "Dashboard");
        mainPanel.add(new ProfilePanel(this, currentUser), "Profile");
        mainPanel.add(new BalancePanel(this, currentUser), "Balance");
        mainPanel.add(new TransferPanel(this, currentUser), "Transfer");
        mainPanel.add(new ComplaintPanel(this), "Complaints");
        mainPanel.add(new TransactionPanel(this, currentUser), "Transactions");
        mainPanel.add(new BeneficiaryPanel(this, currentUser), "Beneficiaries");
        mainPanel.add(new SettingsPanel(this, currentUser), "Settings");

        add(mainPanel);
        
        cardLayout.show(mainPanel, "Login");
        setVisible(true);
    }

    public void showCard(String cardName) {
        cardLayout.show(mainPanel, cardName);
    }
}

package com.netbanking.ui;

import javax.swing.*;
import java.awt.*;
import com.netbanking.model.User;
import com.netbanking.model.DataManager;

public class NetBankingFrame extends JFrame {
    
    CardLayout cardLayout;
    JPanel mainPanel;
    User currentUser;

    public NetBankingFrame() {
        super("MK BANK OF INDIA");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        // Add only Login natively at startup
        mainPanel.add(new LoginPanel(this), "Login");
        mainPanel.add(new RegistrationPanel(this), "Register");
        
        add(mainPanel);
        
        // Save on exit
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (currentUser != null) {
                DataManager.saveUser(currentUser);
            }
        }));
        
        cardLayout.show(mainPanel, "Login");
        setVisible(true);
    }

    public void login(User user) {
        this.currentUser = user;
        
        // Ensure old components are removed if logging in again
        mainPanel.removeAll();
        mainPanel.add(new LoginPanel(this), "Login");
        mainPanel.add(new RegistrationPanel(this), "Register");
        
        mainPanel.add(new DashboardPanel(this, currentUser), "Dashboard");
        mainPanel.add(new ProfilePanel(this, currentUser), "Profile");
        mainPanel.add(new BalancePanel(this, currentUser), "Balance");
        mainPanel.add(new TransferPanel(this, currentUser), "Transfer");
        mainPanel.add(new ComplaintPanel(this), "Complaints");
        mainPanel.add(new TransactionPanel(this, currentUser), "Transactions");
        mainPanel.add(new BeneficiaryPanel(this, currentUser), "Beneficiaries");
        mainPanel.add(new SettingsPanel(this, currentUser), "Settings");
        mainPanel.add(new BillPaymentPanel(this, currentUser), "Bills");
        mainPanel.add(new FixedDepositPanel(this, currentUser), "FixedDeposit");
        
        mainPanel.revalidate();
        mainPanel.repaint();
        
        showCard("Dashboard");
    }

    public void logout() {
        if(currentUser != null) {
            DataManager.saveUser(currentUser);
        }
        this.currentUser = null;
        showCard("Login");
    }

    public void showCard(String cardName) {
        cardLayout.show(mainPanel, cardName);
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
}

package com.netbanking.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import com.netbanking.model.User;

public class DashboardPanel extends BasePanel {
    private User user;

    public DashboardPanel(NetBankingFrame controller, User user) {
        super(controller);
        this.user = user;
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 244, 248)); // Cool Gray/Blue background for modern feel

        // --- 1. Top Navigation Bar ---
        JPanel navbar = new JPanel(new BorderLayout());
        navbar.setBackground(new Color(255, 255, 255));
        navbar.setBorder(new EmptyBorder(15, 40, 15, 40));
        
        JLabel brand = new JLabel("MK BANK");
        brand.setFont(new Font("Segoe UI", Font.BOLD, 26));
        brand.setForeground(new Color(30, 60, 114));
        
        JPanel userCorner = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        userCorner.setOpaque(false);
        
        JLabel userName = new JLabel(user.getName());
        userName.setFont(new Font("Segoe UI", Font.BOLD, 14));
        userName.setForeground(Color.DARK_GRAY);
        
        JButton btnLogout = createButton("Logout", new Color(255, 75, 75));
        btnLogout.addActionListener(e -> controller.logout());

        userCorner.add(userName);
        userCorner.add(btnLogout);
        
        navbar.add(brand, BorderLayout.WEST);
        navbar.add(userCorner, BorderLayout.EAST);
        
        add(navbar, BorderLayout.NORTH);

        JPanel mainContent = new JPanel();
        mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.Y_AXIS));
        mainContent.setBorder(new EmptyBorder(30, 40, 30, 40));
        mainContent.setOpaque(false);

        JPanel summaryCard = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, new Color(42, 82, 152), getWidth(), 0, new Color(30, 60, 114));
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            }
        };
        summaryCard.setPreferredSize(new Dimension(800, 120));
        summaryCard.setMaximumSize(new Dimension(2000, 120));
        summaryCard.setLayout(null);
        summaryCard.setOpaque(false);
        
        JLabel lblTotalBal = new JLabel("Total Balance");
        lblTotalBal.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblTotalBal.setForeground(new Color(200, 220, 255));
        lblTotalBal.setBounds(40, 25, 200, 20);
        summaryCard.add(lblTotalBal);
        
        JLabel lblAmount = new JLabel("Rs. " + String.format("%.2f", user.getBalance()));
        lblAmount.setFont(new Font("Segoe UI", Font.BOLD, 36));
        lblAmount.setForeground(Color.WHITE);
        lblAmount.setBounds(40, 50, 400, 50);
        summaryCard.add(lblAmount);
        
        mainContent.add(summaryCard);
        mainContent.add(Box.createRigidArea(new Dimension(0, 30)));
        
        JLabel lblQuick = new JLabel("Quick Actions");
        lblQuick.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblQuick.setForeground(new Color(60, 70, 80));
        lblQuick.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainContent.add(lblQuick);
        mainContent.add(Box.createRigidArea(new Dimension(0, 15)));

        JPanel grid = new JPanel(new GridLayout(2, 4, 20, 20));
        grid.setOpaque(false);
        grid.setMaximumSize(new Dimension(2000, 300));

        grid.add(new DashboardTile("Transfer", "💸", new Color(76, 175, 80), e -> controller.showCard("Transfer")));
        grid.add(new DashboardTile("Bills", "💡", new Color(33, 150, 243), e -> controller.showCard("Bills")));
        grid.add(new DashboardTile("History", "📜", new Color(156, 39, 176), e -> controller.showCard("Transactions")));
        grid.add(new DashboardTile("Deposits", "🏦", new Color(255, 152, 0), e -> controller.showCard("FixedDeposit")));
        
        grid.add(new DashboardTile("Beneficiaries", "👥", new Color(0, 188, 212), e -> controller.showCard("Beneficiaries")));
        grid.add(new DashboardTile("Profile", "👤", new Color(63, 81, 181), e -> controller.showCard("Profile")));
        grid.add(new DashboardTile("Support", "🎧", new Color(233, 30, 99), e -> controller.showCard("Complaints")));
        grid.add(new DashboardTile("Settings", "⚙️", new Color(96, 125, 139), e -> controller.showCard("Settings")));

        mainContent.add(grid);

        add(mainContent, BorderLayout.CENTER);
    }
    
    private JButton createButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(bg);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    class DashboardTile extends JPanel {
        private Color accent;
        
        public DashboardTile(String title, String icon, Color accent, java.awt.event.ActionListener action) {
            this.accent = accent;
            setLayout(new BorderLayout());
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setOpaque(false);
            
            JLabel lIcon = new JLabel(icon, SwingConstants.CENTER);
            lIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40));
            
            JLabel lTitle = new JLabel(title, SwingConstants.CENTER);
            lTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
            lTitle.setForeground(new Color(80, 80, 80));
            lTitle.setBorder(new EmptyBorder(0, 0, 15, 0));
            
            add(lIcon, BorderLayout.CENTER);
            add(lTitle, BorderLayout.SOUTH);
            
            addMouseListener(new MouseAdapter() {
                public void mouseReleased(MouseEvent e) {
                   action.actionPerformed(null);
                }
            });
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            g2.setColor(Color.WHITE);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
            
            g2.setColor(accent);
            g2.fillRoundRect(0, 0, getWidth(), 6, 15, 15);
            g2.fillRect(0, 3, getWidth(), 3);
            
            g2.setColor(new Color(230, 230, 230));
            g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        }
    }
}

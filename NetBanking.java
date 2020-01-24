import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * NetBanking Application
 * A comprehensive simulation of a NetBanking interface.
 * Features: Login, Dashboard, Profile View, Balance Check, Transfer Money, and Complaints.
 * 
 * @version 1.0
 * @since 2020-01-24
 */
public class NetBanking extends JFrame implements ActionListener {
    
    // Core Components
    JPanel mainPanel;
    CardLayout cardLayout;
    User currentUser;

    // Login Components
    JTextField txtUser;
    JPasswordField txtPass;
    JButton btnLogin;

    // Dashboard Components
    JLabel lblWelcome;

    public NetBanking() {
        super("MK BANK OF INDIA");
        
        // Initialize User Data
        currentUser = new User("MEIYAZHAGAN KULANDAIVEL", "6078 5184 1274 3157", "NAMAKKAL", 
                               "90803 35279", "SELF", "11 FEB 2011", 
                               "**** **** **** *852", "24 - AUG - 2020", 2500000);

        // Frame Setup
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center on screen

        // Main Layout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        // Add Panels
        mainPanel.add(createLoginPanel(), "Login");
        mainPanel.add(createDashboardPanel(), "Dashboard");
        mainPanel.add(createProfilePanel(), "Profile");
        mainPanel.add(createBalancePanel(), "Balance");
        mainPanel.add(createTransferPanel(), "Transfer");
        mainPanel.add(createComplaintPanel(), "Complaints");

        add(mainPanel);
        
        // Show Login initially
        cardLayout.show(mainPanel, "Login");
        
        setVisible(true);
    }

    // --- Inner Class: User Model ---
    private static class User {
        String name;
        String accountNumber;
        String address;
        String phoneNumber;
        String mode;
        String dateOfOpening;
        String atmNumber;
        String cardValidity;
        double balance;

        public User(String name, String an, String ad, String ph, String mo, String dob, String atm, String val, double bal) {
            this.name = name; this.accountNumber = an; this.address = ad; this.phoneNumber = ph;
            this.mode = mo; this.dateOfOpening = dob; this.atmNumber = atm; this.cardValidity = val; this.balance = bal;
        }
    }

    // --- Panel Creators ---

    private JPanel createLoginPanel() {
        JPanel p = new JPanel(null);
        
        JLabel lTitle = new JLabel("MK BANK OF INDIA");
        lTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lTitle.setBounds(280, 50, 300, 30);
        p.add(lTitle);

        JLabel lSub = new JLabel("WELCOME TO NET BANKING SERVICE");
        lSub.setBounds(290, 90, 300, 20);
        p.add(lSub);

        JLabel lUser = new JLabel("USER ID :");
        lUser.setBounds(250, 150, 100, 30);
        p.add(lUser);

        txtUser = new JTextField();
        txtUser.setBounds(350, 150, 150, 30);
        p.add(txtUser);

        JLabel lPass = new JLabel("PASSWORD :");
        lPass.setBounds(250, 200, 100, 30);
        p.add(lPass);

        txtPass = new JPasswordField();
        txtPass.setBounds(350, 200, 150, 30);
        p.add(txtPass);

        btnLogin = new JButton("LOGIN");
        btnLogin.setBounds(350, 260, 100, 30);
        btnLogin.addActionListener(this);
        p.add(btnLogin);
        
        return p;
    }

    private JPanel createDashboardPanel() {
        JPanel p = new JPanel(null);
        
        lblWelcome = new JLabel("Welcome, User");
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 18));
        lblWelcome.setBounds(50, 30, 400, 30);
        p.add(lblWelcome);

        JButton btnProfile = new JButton("USER PROFILE");
        btnProfile.setBounds(100, 100, 200, 40);
        btnProfile.addActionListener(e -> cardLayout.show(mainPanel, "Profile"));
        p.add(btnProfile);

        JButton btnBalance = new JButton("CHECK BALANCE");
        btnBalance.setBounds(400, 100, 200, 40);
        btnBalance.addActionListener(e -> cardLayout.show(mainPanel, "Balance"));
        p.add(btnBalance);

        JButton btnTransfer = new JButton("TRANSFER CASH");
        btnTransfer.setBounds(100, 200, 200, 40);
        btnTransfer.addActionListener(e -> cardLayout.show(mainPanel, "Transfer"));
        p.add(btnTransfer);

        JButton btnComplaint = new JButton("COMPLAINTS");
        btnComplaint.setBounds(400, 200, 200, 40);
        btnComplaint.addActionListener(e -> cardLayout.show(mainPanel, "Complaints"));
        p.add(btnComplaint);
        
        JButton btnLogout = new JButton("LOGOUT");
        btnLogout.setBounds(250, 350, 200, 40);
        btnLogout.addActionListener(e -> cardLayout.show(mainPanel, "Login"));
        p.add(btnLogout);

        return p;
    }

    private JPanel createProfilePanel() {
        JPanel p = new JPanel(null);
        
        JLabel lTitle = new JLabel("USER PROFILE");
        lTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lTitle.setBounds(300, 30, 200, 30);
        p.add(lTitle);

        int y = 100;
        addProfileField(p, "USER NAME :", currentUser.name, y); y += 50;
        addProfileField(p, "ACCOUNT NO :", currentUser.accountNumber, y); y += 50;
        addProfileField(p, "ADDRESS :", currentUser.address, y); y += 50;
        addProfileField(p, "PHONE NO :", currentUser.phoneNumber, y); y += 50;
        addProfileField(p, "MODE :", currentUser.mode, y); y += 50;
        addProfileField(p, "OPENING DATE :", currentUser.dateOfOpening, y); y += 50;
        addProfileField(p, "ATM NO :", currentUser.atmNumber, y); y += 50;
        addProfileField(p, "VALIDITY :", currentUser.cardValidity, y);

        addBackButton(p);
        return p;
    }

    private void addProfileField(JPanel p, String label, String value, int y) {
        JLabel l = new JLabel(label);
        l.setBounds(100, y, 150, 30);
        p.add(l);
        
        JTextField t = new JTextField(value);
        t.setBounds(300, y, 250, 30);
        t.setEditable(false);
        p.add(t);
    }

    private JPanel createBalancePanel() {
        JPanel p = new JPanel(null);
        
        JLabel lTitle = new JLabel("VERIFY BALANCE");
        lTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lTitle.setBounds(300, 50, 300, 30);
        p.add(lTitle);

        JLabel lBal = new JLabel("CURRENT BALANCE :");
        lBal.setBounds(150, 200, 150, 30);
        p.add(lBal);
        
        JTextField tBal = new JTextField("Rs. " + currentUser.balance);
        tBal.setBounds(350, 200, 200, 30);
        tBal.setEditable(false);
        p.add(tBal);

        addBackButton(p);
        return p;
    }

    private JPanel createTransferPanel() {
        JPanel p = new JPanel(null);
        
        JLabel lTitle = new JLabel("BALANCE TRANSFER");
        lTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lTitle.setBounds(300, 30, 250, 30);
        p.add(lTitle);

        JLabel lBal = new JLabel("CURRENT BALANCE :");
        lBal.setBounds(100, 100, 150, 30);
        p.add(lBal);
        
        JLabel lCurrentBal = new JLabel("Rs. " + currentUser.balance);
        lCurrentBal.setBounds(300, 100, 200, 30);
        p.add(lCurrentBal);

        JLabel lAcc = new JLabel("PARTNER'S ACC NO:");
        lAcc.setBounds(100, 150, 150, 30);
        p.add(lAcc);
        
        JTextField tAcc = new JTextField();
        tAcc.setBounds(300, 150, 200, 30);
        p.add(tAcc);

        JLabel lAmt = new JLabel("AMOUNT TO TRANSFER :");
        lAmt.setBounds(100, 200, 150, 30);
        p.add(lAmt);
        
        JTextField tAmt = new JTextField();
        tAmt.setBounds(300, 200, 200, 30);
        p.add(tAmt);
        
        JButton btnSubmit = new JButton("TRANSFER");
        btnSubmit.setBounds(250, 300, 150, 30);
        p.add(btnSubmit);

        btnSubmit.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(tAmt.getText());
                if (amount <= 0) {
                     JOptionPane.showMessageDialog(this, "Invalid Amount");
                } else if (amount > currentUser.balance) {
                    JOptionPane.showMessageDialog(this, "Insufficient Balance");
                } else {
                    currentUser.balance -= amount;
                    lCurrentBal.setText("Rs. " + currentUser.balance);
                    JOptionPane.showMessageDialog(this, "Transfer Successful! New Balance: " + currentUser.balance);
                    tAmt.setText("");
                    tAcc.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number");
            }
        });

        addBackButton(p);
        return p;
    }

    private JPanel createComplaintPanel() {
        JPanel p = new JPanel(null);
        
        JLabel lTitle = new JLabel("COMPLAINT BOX");
        lTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lTitle.setBounds(300, 30, 250, 30);
        p.add(lTitle);

        JLabel lDesc = new JLabel("DESCRIBE YOUR ISSUE:");
        lDesc.setBounds(50, 80, 200, 30);
        p.add(lDesc);

        JTextArea txtArea = new JTextArea();
        txtArea.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(txtArea);
        scroll.setBounds(50, 120, 700, 200);
        p.add(scroll);

        JButton btnSubmit = new JButton("SUBMIT COMPLAINT");
        btnSubmit.setBounds(250, 340, 200, 40);
        p.add(btnSubmit);
        
        btnSubmit.addActionListener(e -> {
            if (txtArea.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complaint cannot be empty!");
            } else {
                JOptionPane.showMessageDialog(this, "Complaint Registered Successfully! Reference ID: " + (int)(Math.random()*10000));
                txtArea.setText("");
            }
        });

        // Contact Info
        JLabel lContact = new JLabel("CUSTOMER CARE : 95249 14630");
        lContact.setBounds(450, 400, 300, 30);
        p.add(lContact);

        JLabel lEmail = new JLabel("EMAIL: mknetbanking@gmail.com");
        lEmail.setBounds(450, 440, 300, 30);
        p.add(lEmail);

        addBackButton(p);
        return p;
    }
    
    private void addBackButton(JPanel p) {
        JButton b = new JButton("BACK");
        b.setBounds(50, 500, 100, 30);
        b.addActionListener(e -> cardLayout.show(mainPanel, "Dashboard"));
        p.add(b);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            lblWelcome.setText("Welcome, " + currentUser.name);
            JOptionPane.showMessageDialog(this, "Login Successful");
            cardLayout.show(mainPanel, "Dashboard");
        }
    }

    public static void main(String[] args) {
        // Run on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new NetBanking());
    }
}



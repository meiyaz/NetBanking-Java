package com.netbanking.model;

import java.sql.*;
import java.util.ArrayList;

public class DataManager {
    private static final String URL = "jdbc:sqlite:netbanking.db";

    public static void saveUser(User user) {
        String insertUser = "INSERT OR REPLACE INTO users " +
                "(cif_number, account_number, name, password, balance, address, phone_number, mode_of_operation, date_of_opening, atm_number, card_validity) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(insertUser)) {
            
            pstmt.setString(1, user.getCifNumber());
            pstmt.setString(2, user.getAccountNumber());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getPassword());
            pstmt.setDouble(5, user.getBalance());
            pstmt.setString(6, user.getAddress());
            pstmt.setString(7, user.getPhoneNumber());
            pstmt.setString(8, user.getMode());
            pstmt.setString(9, user.getDateOfOpening());
            pstmt.setString(10, user.getAtmNumber());
            pstmt.setString(11, user.getCardValidity());
            pstmt.executeUpdate();
            
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("DELETE FROM transactions WHERE account_number = '" + user.getAccountNumber() + "'");
                stmt.execute("DELETE FROM beneficiaries WHERE account_number = '" + user.getAccountNumber() + "'");
                stmt.execute("DELETE FROM fixed_deposits WHERE account_number = '" + user.getAccountNumber() + "'");
            }
            
            String insertTx = "INSERT INTO transactions (account_number, date, type, particulars, amount, balance) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmtTx = conn.prepareStatement(insertTx)) {
                for (Transaction t : user.getTransactions()) {
                    pstmtTx.setString(1, user.getAccountNumber());
                    pstmtTx.setString(2, t.getDate());
                    pstmtTx.setString(3, t.getType());
                    pstmtTx.setString(4, t.getDescription());
                    pstmtTx.setDouble(5, t.getAmount());
                    pstmtTx.setDouble(6, t.getBalanceAfter());
                    pstmtTx.execute();
                }
            }
            
            String insertBen = "INSERT INTO beneficiaries (account_number, bene_name, bene_acc_no, bank_name, ifsc, limit_amount) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmtBen = conn.prepareStatement(insertBen)) {
                for (Beneficiary b : user.getBeneficiaries()) {
                    pstmtBen.setString(1, user.getAccountNumber());
                    pstmtBen.setString(2, b.getName());
                    pstmtBen.setString(3, b.getAccountNumber());
                    pstmtBen.setString(4, b.getBankName());
                    pstmtBen.setString(5, b.getIfscCode());
                    pstmtBen.setDouble(6, b.getTransferLimit());
                    pstmtBen.execute();
                }
            }

            String insertFd = "INSERT INTO fixed_deposits (account_number, fd_number, principal, tenure_months, interest_rate, maturity_amount, maturity_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmtFd = conn.prepareStatement(insertFd)) {
                for (FixedDeposit fd : user.getFixedDeposits()) {
                    pstmtFd.setString(1, user.getAccountNumber());
                    pstmtFd.setString(2, fd.getId());
                    pstmtFd.setDouble(3, fd.getPrincipal());
                    pstmtFd.setInt(4, fd.getDurationMonths());
                    pstmtFd.setDouble(5, fd.getInterestRate());
                    pstmtFd.setDouble(6, fd.getMaturityAmount());
                    pstmtFd.setString(7, fd.getStartDate());
                    pstmtFd.execute();
                }
            }

            System.out.println("Data saved successfully to SQLite.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static User loadUser(String cifId, String password) {
        String query = "SELECT * FROM users WHERE cif_number = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, cifId);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                String userId = rs.getString("account_number");
                User user = new User(
                        rs.getString("name"),
                        rs.getString("cif_number"),
                        userId,
                        rs.getString("address"),
                        rs.getString("phone_number"),
                        rs.getString("mode_of_operation"),
                        rs.getString("date_of_opening"),
                        rs.getString("atm_number"),
                        rs.getString("card_validity"),
                        rs.getDouble("balance")
                );
                user.setPassword(rs.getString("password"));
                user.getTransactions().clear();
                user.getBeneficiaries().clear();
                user.getFixedDeposits().clear();

                try (PreparedStatement txStmt = conn.prepareStatement("SELECT * FROM transactions WHERE account_number = ?")) {
                    txStmt.setString(1, userId);
                    ResultSet txRs = txStmt.executeQuery();
                    while (txRs.next()) {
                        user.addTransaction(new Transaction(
                                txRs.getString("date"),
                                txRs.getString("type"),
                                txRs.getString("particulars"),
                                txRs.getDouble("amount"),
                                txRs.getDouble("balance")
                        ));
                    }
                }

                try (PreparedStatement benStmt = conn.prepareStatement("SELECT * FROM beneficiaries WHERE account_number = ?")) {
                    benStmt.setString(1, userId);
                    ResultSet benRs = benStmt.executeQuery();
                    while (benRs.next()) {
                        user.addBeneficiary(new Beneficiary(
                                benRs.getString("bene_name"),
                                benRs.getString("bene_acc_no"),
                                benRs.getString("bank_name"),
                                benRs.getString("ifsc"),
                                benRs.getDouble("limit_amount")
                        ));
                    }
                }

                try (PreparedStatement fdStmt = conn.prepareStatement("SELECT * FROM fixed_deposits WHERE account_number = ?")) {
                    fdStmt.setString(1, userId);
                    ResultSet fdRs = fdStmt.executeQuery();
                    while (fdRs.next()) {
                        user.addFixedDeposit(new FixedDeposit(
                                fdRs.getString("fd_number"),
                                fdRs.getDouble("principal"),
                                fdRs.getInt("tenure_months"),
                                fdRs.getDouble("interest_rate"),
                                fdRs.getDouble("maturity_amount"),
                                fdRs.getString("maturity_date")
                        ));
                    }
                }
                
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User loadLegacyUser() {
        java.io.File file = new java.io.File("user_data.dat");
        if (!file.exists()) {
            return null;
        }
        try (java.io.ObjectInputStream ois = new java.io.ObjectInputStream(new java.io.FileInputStream(file))) {
            return (User) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

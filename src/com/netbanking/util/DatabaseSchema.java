package com.netbanking.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseSchema {
    private static final String URL = "jdbc:sqlite:netbanking.db";

    public static void initialize() {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

            stmt.execute("PRAGMA foreign_keys = ON;");

            String createUsersTable = "CREATE TABLE IF NOT EXISTS users (" +
                    "cif_number TEXT UNIQUE," +
                    "account_number TEXT PRIMARY KEY," +
                    "name TEXT NOT NULL," +
                    "password TEXT NOT NULL," +
                    "balance REAL DEFAULT 0.0," +
                    "address TEXT," +
                    "phone_number TEXT," +
                    "mode_of_operation TEXT," +
                    "date_of_opening TEXT," +
                    "atm_number TEXT," +
                    "card_validity TEXT" +
                    ");";
            stmt.execute(createUsersTable);

            String createTransactionsTable = "CREATE TABLE IF NOT EXISTS transactions (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "account_number TEXT NOT NULL," +
                    "date TEXT NOT NULL," +
                    "type TEXT NOT NULL," +
                    "particulars TEXT NOT NULL," +
                    "amount REAL NOT NULL," +
                    "balance REAL NOT NULL," +
                    "FOREIGN KEY (account_number) REFERENCES users(account_number)" +
                    ");";
            stmt.execute(createTransactionsTable);

            String createBeneficiariesTable = "CREATE TABLE IF NOT EXISTS beneficiaries (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "account_number TEXT NOT NULL," +
                    "bene_name TEXT NOT NULL," +
                    "bene_acc_no TEXT NOT NULL," +
                    "bank_name TEXT NOT NULL," +
                    "ifsc TEXT NOT NULL," +
                    "limit_amount REAL NOT NULL," +
                    "FOREIGN KEY (account_number) REFERENCES users(account_number)" +
                    ");";
            stmt.execute(createBeneficiariesTable);

            String createFDTable = "CREATE TABLE IF NOT EXISTS fixed_deposits (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "account_number TEXT NOT NULL," +
                    "fd_number TEXT NOT NULL," +
                    "principal REAL NOT NULL," +
                    "tenure_months INTEGER NOT NULL," +
                    "interest_rate REAL NOT NULL," +
                    "maturity_amount REAL NOT NULL," +
                    "maturity_date TEXT NOT NULL," +
                    "FOREIGN KEY (account_number) REFERENCES users(account_number)" +
                    ");";
            stmt.execute(createFDTable);

            System.out.println("Database schema initialized successfully.");

        } catch (Exception e) {
            System.err.println("Error initializing database schema: " + e.getMessage());
        }
    }
}

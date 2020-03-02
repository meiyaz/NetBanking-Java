package com.netbanking.main;

import javax.swing.SwingUtilities;
import com.netbanking.ui.NetBankingFrame;
import com.netbanking.model.DataManager;
import com.netbanking.model.User;
import com.netbanking.util.DatabaseSchema;

public class NetBankingApp {
    public static void main(String[] args) {
        DatabaseSchema.initialize();
        
        User legacyUser = DataManager.loadLegacyUser();
        if (legacyUser != null) {
            DataManager.saveUser(legacyUser);
            try { new java.io.File("user_data.dat").delete(); } catch(Exception e) {}
        } else {
             User userOne = DataManager.loadUser("meiy", "meiy123");
             if (userOne == null) {
                 User u1 = new User("MEIYAZHAGAN", "meiy", "1000001", "CHENNAI", 
                                   "90803 35279", "SELF", "15 JAN 2021", 
                                   "**** **** **** 1111", "12/26", 50000.0);
                 u1.setPassword("meiy123");
                 u1.addBeneficiary(new com.netbanking.model.Beneficiary("Hari", "1000002", "MK BANK", "MKB0001", 10000));
                 DataManager.saveUser(u1);

                 User u2 = new User("SRIHARIHARAN", "hari", "1000002", "COIMBATORE", 
                                   "98765 43210", "SU JOINT", "22 MAR 2019", 
                                   "**** **** **** 2222", "10/25", 25000.0);
                 u2.setPassword("hari123");
                 DataManager.saveUser(u2);
                 
                 User u3 = new User("SUGANRAJ", "sugan", "1000003", "TRICHY", 
                                   "87654 32109", "E-OR-S", "10 JUN 2022", 
                                   "**** **** **** 3333", "05/27", 75000.0);
                 u3.setPassword("sugan123");
                 u3.addBeneficiary(new com.netbanking.model.Beneficiary("Meiyaz", "1000001", "MK BANK", "MKB0001", 50000));
                 DataManager.saveUser(u3);
             }
        }

        SwingUtilities.invokeLater(() -> {
            new NetBankingFrame();
        });
    }
}

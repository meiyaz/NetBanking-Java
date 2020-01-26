package com.netbanking.main;

import javax.swing.SwingUtilities;
import com.netbanking.ui.NetBankingFrame;

public class NetBankingApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NetBankingFrame());
    }
}

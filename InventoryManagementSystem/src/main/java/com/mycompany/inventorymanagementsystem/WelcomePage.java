package com.mycompany.inventorymanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class WelcomePage extends JFrame {
    
    public WelcomePage() {
        
        setTitle("Inventory Management System");
        setSize(700, 700);
        setDefaultCloseOperation(3);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(255, 255, 255));
        
        
        JLabel welcomeLabel = new JLabel("Continue As...");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 50));
        JButton adminButton = new JButton("Admin");
        JButton customerButton = new JButton("Customer");
        
        
        GridBagConstraints grids = new GridBagConstraints();
        grids.insets = new Insets(10, 10, 10, 10);        
        grids.gridwidth = 2;
        add(welcomeLabel, grids);
        
        grids.gridwidth = 2;
        grids.gridy = 1;
        add(adminButton, grids);
        
        grids.gridy = 2;
        add(customerButton, grids);
        
        adminButton.setPreferredSize(new Dimension(500,70));
        customerButton.setPreferredSize(new Dimension(500,70));

        adminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                dispose();
                LoginAndRegister loginandregister = new LoginAndRegister();
                loginandregister.setVisible(true);
                
            }
        });
        
        customerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                dispose();
                Customer customer = new Customer();
                customer.setVisible(true);
                
            }
        });
    }
}
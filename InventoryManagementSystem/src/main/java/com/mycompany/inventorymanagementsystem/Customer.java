
package com.mycompany.inventorymanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Customer extends JFrame {

    public Customer() {
        setTitle("Customer");
        setSize(700, 700);
        setDefaultCloseOperation(3); 
        setLocationRelativeTo(null);     
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(255, 255, 255));
        
        JLabel selectOption = new JLabel("Please select any option");
        selectOption.setFont(new Font("Arial", Font.BOLD, 25));
        JButton buyProductButton = new JButton("Buy Product");
        JButton seeProductsButton = new JButton("See Products");
        JButton backButton = new JButton("Back");
                
        GridBagConstraints grids = new GridBagConstraints();
        grids.insets = new Insets(10, 10, 10, 10);        
        grids.gridwidth = 3;
        add(selectOption, grids);
           
        grids.gridwidth = 3;
        grids.gridy = 1;
        add(buyProductButton, grids);
        
        grids.gridwidth = 3;        
        grids.gridy = 2;
        add(seeProductsButton, grids);
        
        grids.gridy = 3;
        add(backButton, grids);
        
        
        buyProductButton.setPreferredSize(new Dimension(500,70));
        seeProductsButton.setPreferredSize(new Dimension(500,70));
        backButton.setPreferredSize(new Dimension(500,70));
        
        buyProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                new BuyProduct().setVisible(true);
                dispose();
            }
        });
        
        seeProductsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewProducts();
            }
        });
        
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new WelcomePage().setVisible(true);
                dispose();
            }
        });
    }

    public void viewProducts() {
        Admin admin = new Admin();
        admin.viewAdmin();
    }

    public static void main(String[] args) {
       
                new Customer().setVisible(true);
           
    }
}




package com.mycompany.inventorymanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginAndRegister extends JFrame{
    public LoginAndRegister() {
        setTitle("Login and Register");
        setSize(700, 700);
        setDefaultCloseOperation(3); 
        setLocationRelativeTo(null);     
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(255, 255, 255));
        
        JLabel loginOrRegister = new JLabel("Please login or register");
        loginOrRegister.setFont(new Font("Arial", Font.BOLD, 25));
        
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");
        JButton backButton = new JButton("Back");
                
        GridBagConstraints grids = new GridBagConstraints();
        grids.insets = new Insets(10, 10, 10, 10);        
        grids.gridwidth = 3;
        add(loginOrRegister, grids); 
           
        grids.gridwidth = 3;
        grids.gridy = 1;
        add(loginButton, grids);
        
        grids.gridy = 2;
        add(registerButton, grids);
        
        grids.gridy = 3;
        add(backButton, grids);
        
        
        registerButton.setPreferredSize(new Dimension(500,70));
        loginButton.setPreferredSize(new Dimension(500,70));
        backButton.setPreferredSize(new Dimension(500,70));
        
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                dispose();
                Login login = new Login();
                login.setVisible(true);
                
            }
        });
        
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Register register = new Register();
                register.setVisible(true);
            }
        });
        
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                new WelcomePage().setVisible(true);
                dispose();
            }
        });
    
    
    }
}

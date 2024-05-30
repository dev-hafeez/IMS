package com.mycompany.inventorymanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Register extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public Register() {
        setTitle("Register");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10)); 
        getContentPane().setBackground(new Color(255, 255, 255));

        JLabel titleLabel = new JLabel("Create an Account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        JButton registerButton = new JButton("Register");
        JButton loginButton = new JButton("Login");
        add(titleLabel);
        add(new JLabel()); 
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(registerButton);
        add(loginButton);
        add(new JLabel()); 

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();
                
                if (validateInput(username, password)) {
                    if (registerUser(username, password)) {
                        JOptionPane.showMessageDialog(null, "Registration successful");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to register. Please try again later.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please check your details and try again.");
                }
            }
        });

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login login = new Login();
                login.setVisible(true);
            }
        });
    }

    private boolean validateInput(String username, char[] password) {
        if (username.isEmpty() || password.length == 0) {
            return false;
        }   
        return true;
    }
    
    private boolean registerUser(String username, char[] password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("userInfo.txt", true))) {
            writer.write(username + " " + String.valueOf(password));
            writer.newLine(); 
            return true; 
        } catch (IOException e) {
            e.printStackTrace(); 
            return false; 
        }
    }

    public static void main(String[] args) {        
        new Register().setVisible(true);           
    }
}

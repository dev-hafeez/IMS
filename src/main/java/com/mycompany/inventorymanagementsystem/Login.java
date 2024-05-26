
package com.mycompany.inventorymanagementsystem;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Login extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public Login() {
        setTitle("Login");
        setSize(400, 200);
        setDefaultCloseOperation(3);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10)); 
        setResizable(false);
        
        JLabel usernameLabel = new JLabel("Email:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(registerButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();
                
                if (validateLogin(username, password)) {
                    JOptionPane.showMessageDialog(null, "Login successful");
                    Admin admin = new Admin();
                    admin.setVisible(true);
                    dispose();
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Register register = new Register();
                register.setVisible(true);
            }
        });
    }

    private boolean validateLogin(String username, char[] password) {
        

        try (BufferedReader reader = new BufferedReader(new FileReader("userInfo.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    String email = parts[0];
                    String storedPassword = parts[1];
                    
                    if (email.equals(username) && storedPassword.equals(String.valueOf(password))) {
                        return true; 
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No databse found");
            e.printStackTrace(); 
        }
        return false; 
    }

    public static void main(String[] args) {
        
                new Login().setVisible(true);
          
    }
}


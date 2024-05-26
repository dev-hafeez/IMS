
package com.mycompany.inventorymanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class Admin extends JFrame {
    private JTextField productNameField;
    private JTextField priceField;
    private JTextField quantityField;

    public Admin() {
        setTitle("Admin Management");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 3, 10, 10)); 

        JLabel productNameLabel = new JLabel("Product Name:");
        JLabel priceLabel = new JLabel("Price:");
        JLabel quantityLabel = new JLabel("Quantity:");
        productNameField = new JTextField(20);
        priceField = new JTextField(20);
        quantityField = new JTextField(20);
        JButton addButton = new JButton("Add Product");
        JButton deleteButton = new JButton("Delete Product");
        JButton viewButton = new JButton("View Products");
        JButton backButton = new JButton("Back");

        add(productNameLabel);
        add(productNameField);
        add(priceLabel);
        add(priceField);
        add(quantityLabel);
        add(quantityField);
        add(addButton);
        add(deleteButton);
        add(viewButton);
        add(new JLabel()); 
        add(backButton);
        
        backButton.setPreferredSize(new Dimension(500,70));
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String productName = productNameField.getText();
                double price = Double.parseDouble(priceField.getText());
                int quantity = Integer.parseInt(quantityField.getText());

                if (addProduct(productName, price, quantity)) {
                    JOptionPane.showMessageDialog(null, "Product added successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add product");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String productName = productNameField.getText();

                if (deleteProduct(productName)) {
                    JOptionPane.showMessageDialog(null, "Product deleted successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Product not found or failed to delete");
                }
            }
        });

        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewAdmin();
            }
        });
        
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new WelcomePage().setVisible(true);
                dispose();
            }
        });
    }

    private boolean addProduct(String productName, double price, int quantity) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Admin.txt", true))) {
            writer.write(productName + "," + price + "," + quantity);
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean deleteProduct(String productName) {
        try {
            File inputFile = new File("Admin.txt");
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToRemove = productName;
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String[] parts = currentLine.split(",");
                String existingProductName = parts[0].trim();

                if (!existingProductName.equals(lineToRemove)) {
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
            }
            writer.close();
            reader.close();
            boolean successful = tempFile.renameTo(inputFile);
            return successful;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void viewAdmin() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Admin.txt"))) {
            String line;
            StringBuilder AdminInfo = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                AdminInfo.append(line).append("\n");
            }
            JOptionPane.showMessageDialog(null, "Admin Information:\n" + AdminInfo.toString());
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to retrieve Admin information");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Admin().setVisible(true);
            }
        });
    }
}
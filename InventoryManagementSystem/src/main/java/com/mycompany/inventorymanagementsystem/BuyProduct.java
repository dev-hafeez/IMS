package com.mycompany.inventorymanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class BuyProduct extends JFrame {
    private JTextField productNameField;
    private JTextField quantityField;

    public BuyProduct() {
        setTitle("Buy Product");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));
        getContentPane().setBackground(new Color(255, 255, 255));

        JLabel productNameLabel = new JLabel("Product Name:");
        JLabel quantityLabel = new JLabel("Quantity:");
        productNameField = new JTextField(20);
        quantityField = new JTextField(20);
        JButton buyButton = new JButton("Buy");
        JButton backButton = new JButton("Back");

        add(productNameLabel);
        add(productNameField);
        add(quantityLabel);
        add(quantityField);
        add(buyButton);
        add(backButton);

        buyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                String productName = productNameField.getText();
                int quantity = Integer.parseInt(quantityField.getText());
                
                if (buyProduct(productName, quantity)) {                
                    JOptionPane.showMessageDialog(null, "Product bought successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Product not found or failed to buy");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Customer().setVisible(true);
            }
        });
    }

    private boolean buyProduct(String productName, int quantity) {
    File inputFile = new File("Admin.txt");
    File tempFile = new File("temp.txt");

    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
         BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

        String currentLine;
        boolean productFound = false;

        while ((currentLine = reader.readLine()) != null) {
            String[] parts = currentLine.split(",");
            String existingProductName = parts[0].trim();
            int existingQuantity;

            try {
                existingQuantity = Integer.parseInt(parts[2].trim());
            } catch (NumberFormatException ex) {
                System.err.println("Error parsing quantity for product: " + existingProductName);
                continue; 
            }

            if (existingProductName.equals(productName)) {
                existingQuantity -= quantity;
                if (existingQuantity < 0) {
                    existingQuantity = 0;
                }
                currentLine = existingProductName + "," + parts[1].trim() + "," + existingQuantity;
                productFound = true;
            }

            writer.write(currentLine);
            writer.newLine();
        }

        if (!productFound) {
            JOptionPane.showMessageDialog(null, "Product not found");
            return false;
        }
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }

    
    if (inputFile.delete()) {
        boolean successful = tempFile.renameTo(inputFile);
        if (!successful) {
            JOptionPane.showMessageDialog(null, "Failed to update inventory");
            return false;
        }
    } else {
        JOptionPane.showMessageDialog(null, "Failed to update inventory");
        return false;
    }

    
    return true;
}


}

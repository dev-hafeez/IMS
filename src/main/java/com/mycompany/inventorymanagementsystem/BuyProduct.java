
package com.mycompany.inventorymanagementsystem;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


class BuyProduct extends JFrame {
    private JTextField productNameField;
    private JTextField quantityField;

    public BuyProduct() {
        setTitle("Buy Product");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10)); 

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
                    JOptionPane.showMessageDialog(null, "Purchase successful");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to purchase product");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Customer().setVisible(true);
                dispose();
            }
        });
    }

    private boolean buyProduct(String productName, int quantity) {
        File inputFile = new File("products.txt");
        File tempFile = new File("tempProducts.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String currentLine;
            boolean productFound = false;

            while ((currentLine = reader.readLine()) != null) {
                String[] parts = currentLine.split(",");
                String existingProductName = parts[0].trim();
                double price = Double.parseDouble(parts[1].trim());
                int existingQuantity = Integer.parseInt(parts[2].trim());

                if (existingProductName.equalsIgnoreCase(productName)) {
                    if (existingQuantity >= quantity) {
                        int newQuantity = existingQuantity - quantity;
                        writer.write(existingProductName + "," + price + "," + newQuantity);
                        productFound = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Not enough stock available");
                        writer.write(currentLine + System.lineSeparator());
                    }
                } else {
                    writer.write(currentLine + System.lineSeparator());
                }
            }

            if (productFound) {
                inputFile.delete();
                tempFile.renameTo(inputFile);
                return true;
            } else {
                tempFile.delete();
                JOptionPane.showMessageDialog(null, "Product not found");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
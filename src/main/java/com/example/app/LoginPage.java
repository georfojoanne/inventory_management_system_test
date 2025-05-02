package com.example.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

public class LoginPage {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Intermed Pharmaceutical Inventory Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);

        // Apply rounded corners
        frame.setShape(new RoundRectangle2D.Double(0, 0, 800, 500, 30, 30));
        frame.setBackground(new Color(0, 0, 0, 0)); // Transparent background for smooth edges

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Gradient background (black to navy blue)
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, Color.BLACK, 0, getHeight(), new Color(0, 0, 58));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth() / 2, getHeight()); // Left half
            }
        };

        panel.setLayout(null);
        frame.add(panel);

        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        // ==== IMAGE SECTION (right side) ====
        try {
            ImageIcon icon = new ImageIcon(LoginPage.class.getResource("/intermed_pharma_photo.jpg"));
            Image scaledImage = icon.getImage().getScaledInstance(400, 500, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
            imageLabel.setBounds(400, 0, 400, 500); // Right half
            panel.add(imageLabel);
        } catch (Exception e) {
            System.out.println("Image not found. Make sure it's in 'src/resources/intermed_pharma_photo.jpg'");
        }

        // ==== CENTERING VARIABLES ====
        int panelHeight = 500;
        int verticalCenter = (panelHeight - 250) / 2; // Adjusted for new height
        int fieldWidth = 260;
        int fieldX = (400 - fieldWidth) / 2;

        // ==== HEADINGS ====
        JLabel heading1 = new JLabel("Hello!", SwingConstants.CENTER);
        heading1.setFont(new Font("SansSerif", Font.BOLD, 32));
        heading1.setForeground(Color.WHITE);
        heading1.setBounds(0, verticalCenter, 400, 40);
        panel.add(heading1);

        JLabel heading2 = new JLabel("Ready to manage your inventory?", SwingConstants.CENTER);
        heading2.setFont(new Font("SansSerif", Font.PLAIN, 18));
        heading2.setForeground(Color.WHITE);
        heading2.setBounds(0, verticalCenter + 50, 400, 25);
        panel.add(heading2);

        // ==== LOGIN FORM ====
        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.WHITE);
        userLabel.setBounds(fieldX, verticalCenter + 90, fieldWidth, 20);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(fieldX, verticalCenter + 110, fieldWidth, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(fieldX, verticalCenter + 145, fieldWidth, 20);
        panel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setBounds(fieldX, verticalCenter + 165, fieldWidth, 25);
        panel.add(passwordField);

        // ==== CREDITS LABEL ====
        JLabel creditLabel = new JLabel("By Devilias");
        creditLabel.setForeground(new Color(180, 180, 180, 70)); // Soft gray text
        creditLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        creditLabel.setBounds(330, 470, 200, 20); // Bottom-left position
        panel.add(creditLabel);

        JButton loginButton = new JButton("Log In") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Button background
                g2.setColor(new Color(0, 120, 215)); // Blue
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

                // Text
                g2.setColor(Color.WHITE);
                FontMetrics fm = g2.getFontMetrics();
                int textWidth = fm.stringWidth(getText());
                int textHeight = fm.getAscent();
                g2.drawString(getText(), (getWidth() - textWidth) / 2, (getHeight() + textHeight) / 2 - 2);

                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                // No border
            }
        };

        loginButton.setContentAreaFilled(false);
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setBounds((400 - 100) / 2, verticalCenter + 210, 100, 30);
        panel.add(loginButton);

        loginButton.addActionListener(e -> {
            String username = userText.getText();
            String password = new String(passwordField.getPassword());

            if (username.equals("admin") && password.equals("password123")) {
                JOptionPane.showMessageDialog(panel, "Login Successful!");
            } else {
                JOptionPane.showMessageDialog(panel, "Invalid credentials.");
            }
        });
    }
}

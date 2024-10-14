package com.example.Tamagochi;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class NameScreen extends JPanel {
    private WindowsTamagochi windowsTamagochi;
    private JTextField nameField;
    private JLabel nameLabel;
    private Image nameBackgroundImage;

    public NameScreen(WindowsTamagochi windowsTamagochi) {
        this.windowsTamagochi = windowsTamagochi;
        nameBackgroundImage = new ImageIcon("C:\\Users\\lilit\\OneDrive\\Desktop\\Tamagochi\\Start.Background2.png").getImage();
        setLayout(null);

        nameLabel = new JLabel("Tamagochi's Name:");
        nameLabel.setBounds(100, 63, 200, 50);
        nameLabel.setFont(new Font("Ink Free", Font.BOLD, 22));
        nameLabel.setForeground(Color.decode("#8b008b"));
        add(nameLabel);

        if (windowsTamagochi.isContinuing()) {
            JLabel nameDisplayLabel = new JLabel(windowsTamagochi.tamagochi.getName());
            nameDisplayLabel.setBounds(95, 105, 200, 30);
            nameDisplayLabel.setFont(new Font("Ink Free", Font.BOLD, 15));
            nameDisplayLabel.setForeground(Color.decode("#d85baa"));
            add(nameDisplayLabel);

        } else {
            nameField = new JTextField(20);
            nameField.setBounds(95, 105, 200, 30);
            nameField.setFont(new Font("Ink Free", Font.BOLD, 15));
            nameField.setBorder(new LineBorder(Color.decode("#d85baa")));
            nameField.setBackground(Color.decode("#f8d6e7"));
            nameField.setForeground(Color.decode("#d85baa"));
            add(nameField);

            JButton submitButton = new JButton("Enter");
            submitButton.setBounds(145, 150, 100, 30);
            submitButton.setFont(new Font("Ink Free", Font.BOLD, 18));
            submitButton.setBorder(new LineBorder(Color.cyan.darker()));
            submitButton.setBackground(Color.decode("#e8ffff"));
            submitButton.setForeground(Color.cyan.darker());
            add(submitButton);

            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = nameField.getText();
                    if (!name.isEmpty()) {
                        windowsTamagochi.tamagochi.setName(name);
                        windowsTamagochi.updateWelcomeLabel(name);
                        windowsTamagochi.showGameScreen();
                    } else {
                        JOptionPane.showMessageDialog(NameScreen.this, "Name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        }

        JButton returnButton = new JButton("↩");
        returnButton.setBounds(300, 5, 50, 15);
        returnButton.setBorder(new LineBorder(Color.decode("#d85baa")));
        returnButton.setBackground(Color.decode("#f8d6e7"));
        returnButton.setForeground(Color.decode("#d85baa"));

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                windowsTamagochi.showStartScreen();
            }
        });
        add(returnButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(nameBackgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}


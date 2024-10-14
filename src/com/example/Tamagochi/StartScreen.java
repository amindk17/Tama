package com.example.Tamagochi;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class StartScreen extends JPanel {
    private Image startBackgroundImage;
    private JLabel welcomeLabel;

    public StartScreen(WindowsTamagochi mainWindow, String tamagochiName) {
        startBackgroundImage = new ImageIcon("C:\\Users\\lilit\\OneDrive\\Desktop\\Tamagochi\\Start.Background2.png").getImage();
        setLayout(null);

        welcomeLabel = new JLabel("Willkommen " + tamagochiName);
        welcomeLabel.setBounds(100, 65, 200, 50);
        welcomeLabel.setFont(new Font("Ink Free", Font.BOLD, 22));
        welcomeLabel.setForeground(Color.decode("#8b008b"));
        add(welcomeLabel);

        JButton startButton = new JButton("Continue Game");
        startButton.setBounds(110, 120, 150, 50);
        startButton.setFont(new Font("Ink Free", Font.BOLD, 18));
        startButton.setBorder(new LineBorder(Color.decode("#d85baa")));
        startButton.setBackground(Color.decode("#f8d6e7"));
        startButton.setForeground(Color.decode("#d85baa"));
        add(startButton);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.setContinuing(true);
                mainWindow.showGameScreen();
            }
        });

        JButton restartButton = new JButton("Restart Game");
        restartButton.setBounds(110, 175, 150, 50);
        restartButton.setFont(new Font("Ink Free", Font.BOLD, 18));
        restartButton.setBorder(new LineBorder(Color.CYAN.darker()));
        restartButton.setBackground(Color.decode("#e8ffff"));
        restartButton.setForeground(Color.cyan.darker());
        add(restartButton);

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.setContinuing(false);
                mainWindow.resetGame();
            }
        });
    }


    public void updateWelcomeLabel(String name) {
        welcomeLabel.setText("Willkommen " + name);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(startBackgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}


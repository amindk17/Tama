package com.example.Tamagochi;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class WindowsTamagochi {
    JFrame frame;
    JPanel mainPanel;
    Tamagochi tamagochi;
    CardLayout cardLayout;
    GameScreen gameScreen;
    NameScreen nameScreen;
    private boolean isContinuing;

    public WindowsTamagochi() {
        tamagochi = GameSaveManager.loadGame();
        if (tamagochi == null) {
            tamagochi = new Tamagochi();
        }

        frame = new JFrame("Tamagochi");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        showStartScreen();

        nameScreen = new NameScreen(this);
        mainPanel.add(nameScreen, "NameScreen");
        gameScreen = new GameScreen(this);
        mainPanel.add(gameScreen, "GameScreen");

        frame.add(mainPanel);
        frame.setVisible(true);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                GameSaveManager.saveGame(tamagochi);
            }
        });
    }

    public void showStartScreen() {
        StartScreen startScreen = new StartScreen(this, tamagochi.getName());
        mainPanel.add(startScreen, "StartScreen");
        cardLayout.show(mainPanel, "StartScreen");
    }

    public void setContinuing(boolean isContinuing) {
        this.isContinuing = isContinuing;
    }

    public boolean isContinuing() {
        return isContinuing;
    }

    public void showNameScreen() {
        cardLayout.show(mainPanel, "NameScreen");
    }

    public void showGameScreen() {
        if (gameScreen != null && tamagochi != null && mainPanel != null) {
            gameScreen.updateLabels();
            cardLayout.show(mainPanel, "GameScreen");
        } else {
            System.out.println("One of the objects is null");
        }
    }

    public void resetGame() {
        JDialog dialog = new JDialog(frame, "Restart Game", true);
        dialog.setFont(new Font("Courier New", Font.BOLD,10));
        dialog.setLayout(new BorderLayout());

        JLabel message = new JLabel("<html>Möchtest du wirklich ein neues Spiel starten?<br>Der aktuelle Spielstand wird überschrieben.</html>");
        message.setHorizontalAlignment(SwingConstants.CENTER);
        message.setFont(new Font("Times New Roman", Font.BOLD,13));
        message.setForeground(Color.decode("#d85baa"));
        dialog.add(message, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton yesButton = new JButton(" Ja ");
        yesButton.setForeground(Color.cyan.darker());
        yesButton.setBackground(Color.decode("#e8ffff"));
        yesButton.setBorder(new LineBorder(Color.cyan.darker()));
        yesButton.setFont(new Font("Courier New", Font.BOLD, 16));

        buttonPanel.add(yesButton);

        JButton noButton = new JButton(" Nein ");
        noButton.setForeground(Color.cyan.darker());
        noButton.setBackground(Color.decode("#e8ffff"));
        noButton.setBorder(new LineBorder(Color.cyan.darker()));
        noButton.setFont(new Font("Courier New", Font.BOLD, 18));

        buttonPanel.add(noButton);

        dialog.add(buttonPanel, BorderLayout.SOUTH);

        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tamagochi = new Tamagochi();
                nameScreen = new NameScreen(WindowsTamagochi.this);
                mainPanel.add(nameScreen, "NameScreen");
                gameScreen = new GameScreen(WindowsTamagochi.this);
                mainPanel.add(gameScreen, "GameScreen");
                showNameScreen();
                dialog.dispose();
            }
        });

        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }

    public void updateWelcomeLabel(String name) {
    }

    public static void main(String[] args) {
        new WindowsTamagochi();
    }
}

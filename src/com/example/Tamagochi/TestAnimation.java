package com.example.Tamagochi;
import javax.swing.*;
import java.awt.*;

public class TestAnimation {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(null);

        JLabel animationLabel = new JLabel();
        animationLabel.setBounds(15, 90, 100, 100);
        frame.add(animationLabel);

        ImageIcon[] tamagotchiFrames = {
                new ImageIcon("C:\\Users\\lilit\\OneDrive\\Desktop\\Tamagochi\\Dreckig 1.png"),
                new ImageIcon("C:\\Users\\lilit\\OneDrive\\Desktop\\Tamagochi\\Dreckig 2.png"),
                new ImageIcon("C:\\Users\\lilit\\OneDrive\\Desktop\\Tamagochi\\Langeweile 1.png"),
                new ImageIcon("C:\\Users\\lilit\\OneDrive\\Desktop\\Tamagochi\\Langeweile 2.png"),
                new ImageIcon("C:\\Users\\lilit\\OneDrive\\Desktop\\Tamagochi\\Main NEW.png"),
                new ImageIcon("C:\\Users\\lilit\\OneDrive\\Desktop\\Tamagochi\\Schlafen 1.png"),
                new ImageIcon("C:\\Users\\lilit\\OneDrive\\Desktop\\Tamagochi\\Schlafen 2.png"),
                new ImageIcon("C:\\Users\\lilit\\OneDrive\\Desktop\\Tamagochi\\Spielen 1.png"),
                new ImageIcon("C:\\Users\\lilit\\OneDrive\\Desktop\\Tamagochi\\Spielen 2.png"),
                new ImageIcon("C:\\Users\\lilit\\OneDrive\\Desktop\\Tamagochi\\Spielen 3.png"),
                new ImageIcon("C:\\Users\\lilit\\OneDrive\\Desktop\\Tamagochi\\Trinken 1.png"),
                new ImageIcon("C:\\Users\\lilit\\OneDrive\\Desktop\\Tamagochi\\Trinken 2.png"),
                new ImageIcon("C:\\Users\\lilit\\OneDrive\\Desktop\\Tamagochi\\Trinken 3.png")
        };

        Timer timer = new Timer(1000, e -> {
            int currentFrame = (int) ((System.currentTimeMillis() / 1000) % tamagotchiFrames.length);
            animationLabel.setIcon(tamagotchiFrames[currentFrame]);
        });
        timer.start();

        frame.setVisible(true);
    }
}

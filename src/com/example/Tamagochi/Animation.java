package com.example.Tamagochi;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Animation {
    private ImageIcon[] frames;
    private JLabel label;
    private Timer timer;
    private int currentFrame = 0;

    public Animation(ImageIcon[] frames, int delay, int width, int height) {
        this.frames = new ImageIcon[frames.length];
        for (int i = 0; i < frames.length; i++) {
            this.frames[i] = new ImageIcon(frames[i].getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        }
        this.label = new JLabel(this.frames[0]);
        this.timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFrame();
            }
        });
    }

    public void startAnimation() {
        timer.start();
    }

    public void stopAnimation() {
        timer.stop();
    }

    private void updateFrame() {
        currentFrame = (currentFrame + 1) % frames.length;
        label.setIcon(frames[currentFrame]);
    }

    public JLabel getLabel() {
        return label;
    }
}

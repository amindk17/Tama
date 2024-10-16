package com.example.Tamagochi;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;
import javax.swing.border.Border;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class GameScreen extends JPanel {
    private WindowsTamagochi mainWindow;
    private Image backgroundImage;
    private JProgressBar caffeineBar;
    private JProgressBar hygieneBar;
    private JProgressBar motivationBar;
    private JProgressBar experienceBar;
    private JProgressBar moodBar;
    private Tamagochi tamagochi;
    private JLabel timeLabel;
    private JLabel generationLabel;
    private JLabel warningLabel;
    private JLabel animationLabel;
    private JLabel wakeLabel;
    private JLabel sleepLabel;
    private JLabel nameLabel;
    private JLabel noSleepLabel;
    private JLabel plsSleepLabel;
    private int gameHour = 11;
    private Timer workTimer;
    private Timer decreaseStatsTimer;
    private Timer dailyDecreaseTimer;
    private Timer checkStatsTimer;
    private Animation sleepAnimation;
    private Animation tiredAnimation;
    private Animation playAnimation;
    private Animation dirtyAnimation;
    private Animation boringAnimation;
    private Animation drinkAnimation;
    private Animation mainAnimation;



    int screenWidth = 400;
    int screenHeight = 400;
    int centerX = screenWidth / 2;
    int centerY = screenHeight / 2;


    public GameScreen(WindowsTamagochi mainWindow) {
        this.mainWindow = mainWindow;
        this.tamagochi = mainWindow.tamagochi;
        setLayout(null);

        int buttonWidth = 80;
        int buttonHeight = 30;
        int buttonSpacing = 16;
        int progressBarWidth = 100;
        int progressBarHeight = 10;
        int progressBarSpacing = 10;
        int progressBarX = (300 - progressBarWidth) / 2;
        int progressBarXLeft = (300 - 2 * progressBarWidth - progressBarSpacing) / 2;
        int progressBarXRight = progressBarXLeft + progressBarWidth + progressBarSpacing;


        backgroundImage = new ImageIcon("C:\\Users\\lilit\\OneDrive\\Desktop\\Tamagochi\\Background.png").getImage();

        JButton backButton = createButton("↩", 300, 5, buttonWidth, buttonHeight, e -> mainWindow.showStartScreen());
        backButton.setBounds(300, 5, 50, 15);
        backButton.setBackground(Color.decode("#ff8164"));
        add(backButton);

        JButton drinkButton = createButton("Trinken", 10, 300, buttonWidth, buttonHeight, e -> {
            tamagochi.trinken();
            startDrinkAnimation();
            updateProgressBars();
        });

        drinkButton.setBackground(Color.decode("#FFC1C1"));
        drinkButton.setForeground(Color.RED.darker());
        drinkButton.setBorder(new LineBorder(Color.RED.darker()));
        drinkButton.setFont(new Font("Courier New", Font.BOLD,13));

        add(drinkButton);

        JButton sleepButton = createButton("Schlafen", 10 + 2 * (buttonWidth + buttonSpacing), 300, buttonWidth, buttonHeight, e -> {
            tamagochi.schlafen();
            startSleepAnimation();
            stopDecreaseStatsTimer();
            sleepLabel.setVisible(true);
            updateProgressBars();
        });

        sleepButton.setBackground(Color.decode("#f9ddb1"));
        sleepButton.setForeground(Color.decode("#8B4513"));
        sleepButton.setBorder(new LineBorder(Color.decode("#8B4513")));
        sleepButton.setFont(new Font("Courier New", Font.BOLD,13));

        add(sleepButton);

        JButton cleanButton = createButton("Aufräumen", 10 + (buttonWidth + buttonSpacing), 300, buttonWidth, buttonHeight, e -> {
            tamagochi.aufraeumen();
            updateProgressBars();
        });

        cleanButton.setBackground(Color.decode("#ADD8E6"));
        cleanButton.setForeground(Color.BLUE.darker());
        cleanButton.setBorder(new LineBorder(Color.BLUE.darker()));
        cleanButton.setFont(new Font("Courier New", Font.BOLD,13));

        add(cleanButton);

        JButton playButton = createButton("Spielen", 10 + 3 * (buttonWidth + buttonSpacing), 300, buttonWidth, buttonHeight, e -> {
            tamagochi.spielen();
            startPlayAnimation();
            updateProgressBars();
        });

        playButton.setBackground(Color.decode("#CCFFCC"));
        playButton.setForeground(Color.decode("#2331f"));
        playButton.setBorder(new LineBorder(Color.decode("#2331f")));
        playButton.setFont(new Font("Courier New", Font.BOLD,13));

        add(playButton);

        JButton wakeButton = createButton("⏰", 300, 40, 50, 25, e -> {
            if (tamagochi.isSleeping()) {
                tamagochi.aufwachen(gameHour);
                stopDecreaseStatsTimer();
                startWakeUpAnimation();
                wakeLabel.setVisible(true);
                noSleepLabel.setVisible(false);
                repaint();
                revalidate();
                updateProgressBars();

            } else {
                changeBackgroundImage("C:\\Users\\lilit\\OneDrive\\Desktop\\Tamagochi\\Background Bubble.png");
                noSleepLabel.setVisible(true);
            }
        });

        wakeButton.setBackground(Color.decode("#f9ddb1"));
        wakeButton.setFont(new Font("Ariel", Font.BOLD, 15));
        add(wakeButton);

        caffeineBar = createProgressBar("Koffein", progressBarXLeft, 5, progressBarWidth, progressBarHeight);
        caffeineBar.setBackground(Color.decode("#f9ddb1"));
        caffeineBar.setForeground(Color.YELLOW.darker());
        caffeineBar.setBorder(new LineBorder(Color.yellow.darker()));
        caffeineBar.setFont(new Font("Bahnschrift SemiCondensed", Font.BOLD, 10));
        add(caffeineBar);

        hygieneBar = createProgressBar("Hygiene", progressBarXLeft, 5 + progressBarHeight + progressBarSpacing, progressBarWidth, progressBarHeight);
        hygieneBar.setBackground(Color.decode("#ADD8E6"));
        hygieneBar.setForeground(Color.BLUE.darker());
        hygieneBar.setBorder(new LineBorder(Color.BLUE.darker()));
        hygieneBar.setFont(new Font("Bahnschrift SemiCondensed", Font.BOLD, 10));
        add(hygieneBar);

        motivationBar = createProgressBar("Motivation", progressBarXRight, 5, progressBarWidth, progressBarHeight);
        motivationBar.setBackground(Color.decode("#CCFFCC"));
        motivationBar.setForeground(Color.GREEN.darker());
        motivationBar.setBorder(new LineBorder(Color.green.darker()));
        motivationBar.setFont(new Font("Bahnschrift SemiCondensed", Font.BOLD, 10));
        add(motivationBar);

        experienceBar = createProgressBar("XP", progressBarXRight, 5 + progressBarHeight + progressBarSpacing, progressBarWidth, progressBarHeight);
        experienceBar.setBackground(Color.decode("#E8deff"));
        experienceBar.setForeground(Color.decode("#8B008B"));
        experienceBar.setBorder(new LineBorder(Color.decode("#8B008B")));
        experienceBar.setFont(new Font("Bahnschrift SemiCondensed", Font.BOLD, 10));
        add(experienceBar);

        moodBar = createProgressBar("Gesundheit", 40, 280, 300, progressBarHeight);
        moodBar.setBackground(Color.decode("#FFC1C1"));
        moodBar.setForeground(Color.RED.darker());
        moodBar.setFont(new Font("Bahnschrift SemiCondensed", Font.BOLD, 10));
        moodBar.setBorder(new LineBorder(Color.decode("#8B2323")));
        add(moodBar);

        timeLabel = new JLabel("11:00");
        timeLabel.setBounds(307, 21, 100, 20);
        timeLabel.setFont(new Font("Courier New", Font.BOLD, 12));
        add(timeLabel);

        nameLabel = new JLabel(tamagochi.getName());
        nameLabel.setBounds(130, 40, 50, 30);
        nameLabel.setFont(new Font("Ink Free", Font.BOLD, 20));
        nameLabel.setForeground(Color.black);
        add(nameLabel);
        revalidate();
        repaint();

        animationLabel = new JLabel();
        animationLabel.setBounds(100, 60, 150, 220);
        add(animationLabel);

        generationLabel = new JLabel(tamagochi.getGeneration());
        generationLabel.setBounds(223, 336, 100, 20);
        generationLabel.setForeground(Color.BLACK);
        generationLabel.setFont(new Font("Arial", Font.BOLD,12));
        add(generationLabel);


        warningLabel = new JLabel("<html>Ich muss so dringend <br>ins Bett, ansonsten <br>sterbe ich... :(</html>");
        warningLabel.setBounds(250, 70, 200, 80);
        warningLabel.setForeground(Color.BLACK);
        warningLabel.setFont(new Font("Arial",Font.BOLD,8));

        warningLabel.setVisible(false);
        add(warningLabel);


        wakeLabel = new JLabel("Guten Morgen!");
        wakeLabel.setBounds(250,70,220,80);
        wakeLabel.setForeground(Color.BLACK);
        wakeLabel.setFont(new Font("Bookman Old Style",Font.BOLD,11));

        wakeLabel.setVisible(false);
        add(wakeLabel);


        sleepLabel = new JLabel("Gute Nacht!");
        sleepLabel.setBounds(250,70,220,80);
        sleepLabel.setForeground(Color.BLACK);
        sleepLabel.setFont(new Font("Bookman Old Style",Font.BOLD,13));

        sleepLabel.setVisible(false);
        add(sleepLabel);


        noSleepLabel = new JLabel("<html>Ich bin schon <br>lange wach <br>du Knalltüte!!</html>");
        noSleepLabel.setBounds(250,70,200,80);
        noSleepLabel.setForeground(Color.BLACK);
        noSleepLabel.setFont(new Font("Bookman Old Style",Font.BOLD,10));

        noSleepLabel.setVisible(false);
        add(noSleepLabel);
        


        ImageIcon[] mainFrames = {
                new ImageIcon("C:\\Users\\lilit\\OneDrive\\Desktop\\Tamagochi\\Main NEW.png"),

        };
        mainAnimation = new Animation(mainFrames, 500, 150, 220);


        ImageIcon[] sleepFrames = {
                new ImageIcon("C:\\Users\\lilit\\OneDrive\\Desktop\\Tamagochi\\Schlafen 1.png"),
                new ImageIcon("C:\\Users\\lilit\\OneDrive\\Desktop\\Tamagochi\\Schlafen 2.png")
        };
        sleepAnimation = new Animation(sleepFrames, 500, 150, 220);


        ImageIcon[] playFrames = {
                new ImageIcon("C:\\Users\\lilit\\OneDrive\\Desktop\\Tamagochi\\Spielen 1.png"),
                new ImageIcon("C:\\Users\\lilit\\OneDrive\\Desktop\\Tamagochi\\Spielen 2.png"),
                new ImageIcon("C:\\Users\\lilit\\OneDrive\\Desktop\\Tamagochi\\Spielen 3.png")
        };
        playAnimation = new Animation(playFrames, 500, 150, 220);


        ImageIcon[] dirtyFrames = {
                new ImageIcon("C:\\Users\\lilit\\OneDrive\\Desktop\\Tamagochi\\Dreckig 1.png"),
                new ImageIcon("C:\\Users\\lilit\\OneDrive\\Desktop\\Tamagochi\\Dreckig 2.png")
        };
        dirtyAnimation = new Animation(dirtyFrames, 500, 150, 220);


        ImageIcon[] boringFrames = {
                new ImageIcon("C:\\Users\\lilit\\OneDrive\\Desktop\\Tamagochi\\Langeweile 1.png"),
                new ImageIcon("C:\\Users\\lilit\\OneDrive\\Desktop\\Tamagochi\\Langeweile 2.png")
        };
        boringAnimation = new Animation(boringFrames, 500, 150, 220);


        ImageIcon[] drinkFrames = {
                new ImageIcon("C:\\Users\\lilit\\OneDrive\\Desktop\\Tamagochi\\Trinken 1.png"),
                new ImageIcon("C:\\Users\\lilit\\OneDrive\\Desktop\\Tamagochi\\Trinken 2.png"),
                new ImageIcon("C:\\Users\\lilit\\OneDrive\\Desktop\\Tamagochi\\Trinken 3.png"),
        };
        drinkAnimation = new Animation(drinkFrames, 500, 150, 220);

        startWorkTimer();
        startTimer();
        startDailyDecreaseTimer();
        startDailyResetTimer();

        updateProgressBars();
        startCheckStatsTimer();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
    }

    public void startMainAnimation() {
        stopAllAnimations();
        animationLabel.setIcon(mainAnimation.getLabel().getIcon());
        mainAnimation.startAnimation();

    }

    public void startSleepAnimation() {
        stopAllAnimations();
        animationLabel.setIcon(sleepAnimation.getLabel().getIcon());
        sleepAnimation.startAnimation();
        tamagochi.setSleeping(true);
        warningLabel.setVisible(false);
        wakeLabel.setVisible(false);
        noSleepLabel.setVisible(false);
        pauseAllTimersAndAnimations();
        repaint();

        changeBackgroundImage("C:\\Users\\lilit\\OneDrive\\Desktop\\Tamagochi\\Background Bubble.png");
        if (!tamagochi.getHasWokenUpToday()) {
            sleepLabel.setVisible(true);
            noSleepLabel.setVisible(false);
        }

        Timer shortTimer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeBackgroundImage("C:\\Users\\lilit\\OneDrive\\Desktop\\Tamagochi\\Background.png");
                sleepLabel.setVisible(false);
                noSleepLabel.setVisible(false);
            }
        });

        shortTimer.setRepeats(false);
        shortTimer.start();
    }

    void pauseAllTimersAndAnimations() {
        if (workTimer != null) {
            workTimer.stop();
        }
        if (decreaseStatsTimer != null) {
            decreaseStatsTimer.stop();
        }
        if (dailyDecreaseTimer != null) {
            dailyDecreaseTimer.stop();
        }
    }

    void resumeAllTimersAndAnimations() {
        if (workTimer != null) {
            workTimer.start();
        }
        if (decreaseStatsTimer != null) {
            decreaseStatsTimer.start();
        }
        if (dailyDecreaseTimer != null) {
            dailyDecreaseTimer.start();
        }
    }

    private void startWakeUpAnimation() {

        stopAllAnimations();
        animationLabel.setIcon(mainAnimation.getLabel().getIcon());
        mainAnimation.startAnimation();
        tamagochi.setSleeping(false);
        resumeAllTimersAndAnimations();

        changeBackgroundImage("C:\\Users\\lilit\\OneDrive\\Desktop\\Tamagochi\\Background Bubble.png");

        if (!tamagochi.getHasWokenUpToday()) {
            wakeLabel.setVisible(true);
            warningLabel.setVisible(false);
            repaint();
            tamagochi.setHasWokenUpToday(true);
        }

        tamagochi.auffuellen();


        Timer timer = new Timer(12000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeBackgroundImage("C:\\Users\\lilit\\OneDrive\\Desktop\\Tamagochi\\Background.png");
                wakeLabel.setVisible(false);
                warningLabel.setVisible(false);
                startDecreaseStatsTimer();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    public void setNameLabel(String name) {
        nameLabel.setText(name);
    }

    private void startPlayAnimation() {
        stopAllAnimations();
        animationLabel.setIcon(playAnimation.getLabel().getIcon());
        playAnimation.startAnimation();
    }

    private void startDrinkAnimation() {
        stopAllAnimations();
        animationLabel.setIcon(drinkAnimation.getLabel().getIcon());
        drinkAnimation.startAnimation();

    }

    private void startBoringAnimation() {
        stopAllAnimations();
        animationLabel.setIcon(boringAnimation.getLabel().getIcon());
        boringAnimation.startAnimation();

    }

    private void startDirtyAnimation() {
        stopAllAnimations();
        animationLabel.setIcon(dirtyAnimation.getLabel().getIcon());
        dirtyAnimation.startAnimation();

    }

    private void stopAllAnimations() {
        drinkAnimation.stopAnimation();
        sleepAnimation.stopAnimation();
        playAnimation.stopAnimation();
        boringAnimation.stopAnimation();
        dirtyAnimation.stopAnimation();
        sleepLabel.setVisible(false);
        noSleepLabel.setVisible(false);

    }

    private void startCheckStatsTimer() {
        checkStatsTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkStats();
            }
        });
        checkStatsTimer.start();

    }

    private void checkStats() {
        if (motivationBar.getValue() < 50) {
            startBoringAnimation();
        } else if (hygieneBar.getValue() < 50) {
            startDirtyAnimation();
        }
    }

    private void startTimer() {
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameHour = (gameHour + 1) % 24;
                updateTimeLabel();
                checkTime(gameHour);
                if (!tamagochi.isSleeping()) {
                    startMainAnimation();
                }
                if (gameHour == 0) {
                    tamagochi.resetDailyStats();
                }
            }
        });
        timer.start();
    }


    private JButton createButton(String text, int x, int y, int width, int height, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.addActionListener(actionListener);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setVerticalAlignment(SwingConstants.CENTER);

        return button;
    }

    private JProgressBar createProgressBar(String name, int x, int y, int width, int height) {
        JProgressBar progressBar = new JProgressBar();
        progressBar.setString(name);
        progressBar.setStringPainted(true);
        progressBar.setBounds(x, y, width, height);

        return progressBar;
    }

    private void updateProgressBars() {
        if (!tamagochi.isSleeping()) {
            caffeineBar.setValue(tamagochi.getCaffeine());
            hygieneBar.setValue(tamagochi.getHygiene());
            motivationBar.setValue(tamagochi.getMotivation());
            experienceBar.setValue(tamagochi.getExperience());
            moodBar.setValue(tamagochi.getMood());

            if (tamagochi.getMood() == 0) {
                endGame();
            }
        }
    }

    public GameScreen() {
        tamagochi = new Tamagochi();
    }

    private void updateTimeLabel() {
        String time = String.format("%02d:00", gameHour);
        timeLabel.setText(time);
    }

    public void updateLabels() {
        nameLabel.setText(tamagochi.getName());
        generationLabel.setText(tamagochi.getGeneration());
    }

    private void checkSleepWake() {
        if (gameHour == 23) {
            startDecreaseStatsTimer();
        }
    }

    public void checkTime(int gameHour) {
        if (tamagochi.isSleeping()) {
            warningLabel.setVisible(false);
            revalidate();
            repaint();
            return;
        }

        if (tamagochi.isTired(gameHour)) {
            warningLabel.setVisible(true);
            noSleepLabel.setVisible(false);
            wakeLabel.setVisible(false);
            repaint();
            setComponentZOrder(warningLabel, 0);
            changeBackgroundImage("C:\\Users\\lilit\\OneDrive\\Desktop\\Tamagochi\\Background Bubble.png");

        } else {
            warningLabel.setVisible(false);
            wakeLabel.setVisible(false);
            changeBackgroundImage("C:\\Users\\lilit\\OneDrive\\Desktop\\Tamagochi\\Background.png");
        }

        revalidate();
        repaint();
    }

    private void changeBackgroundImage(String imagePath) {
        backgroundImage = new ImageIcon(imagePath).getImage();
        repaint();
    }

    private void startWorkTimer() {
        workTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tamagochi.startArbeiten();
                tamagochi.updateGeneration();
                startMainAnimation();
                updateProgressBars();
                checkTime(gameHour);
                startDecreaseStatsTimer();
                updateLabels();
            }
        });
        workTimer.start();
    }

    private void stopWorkTimer() {
        if (workTimer != null) {
            workTimer.stop();
        }
    }

    private void startDecreaseStatsTimer() {
        decreaseStatsTimer = new Timer(12000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tamagochi.isSleeping()) {
                    tamagochi.decreaseStats();
                    updateProgressBars();
                    startMainAnimation();
                }
            }
        });
        decreaseStatsTimer.start();
    }


    private void stopDecreaseStatsTimer() {
        if (decreaseStatsTimer != null) {
            decreaseStatsTimer.stop();
        }
    }

    private void startDailyDecreaseTimer() {
        dailyDecreaseTimer = new Timer(60000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tamagochi.decreaseStats();
                updateProgressBars();
            }
        });
        dailyDecreaseTimer.start();
    }

    private void startDailyResetTimer() {
        Timer dailyResetTimer = new Timer(24 * 60 * 60 * 1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameHour == 0) {
                    tamagochi.setHasWokenUpToday(false);
                }
            }
        });
        dailyResetTimer.start();
    }

    private void stopDailyDecreaseTimer() {
        if (dailyDecreaseTimer != null) {
            dailyDecreaseTimer.stop();
        }
    }

    private void endGame() {
        JOptionPane.showMessageDialog(this, "Game Over", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        int response = JOptionPane.showConfirmDialog(this, "Möchtest du neu starten?", "Neustart", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            restartGame();
        } else {
            System.exit(0);
        }
    }

    private void restartGame() {
        stopWorkTimer();
        stopDecreaseStatsTimer();
        stopDailyDecreaseTimer();

        tamagochi.restart();

        updateProgressBars();
        startMainAnimation();
        startWorkTimer();
        startDecreaseStatsTimer();
        startDailyDecreaseTimer();
    }
}



package com.example.Tamagochi;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class Tamagochi implements Serializable {
    private static final long serialVersionUID = 1L;
    private int generation;
    private int experience;
    private int motivation;
    private int caffeine;
    private int mood;
    private int hygiene;
    private boolean hasWokenUpToday;
    private boolean experienceUpdatedToday;
    private boolean sleeping;
    private boolean isAwake;
    private String name;
    private GameScreen gameScreen;

    public Tamagochi() {
        this.generation = 1;
        this.experience = 0;
        this.motivation = 100;
        this.caffeine = 100;
        this.mood = 100;
        this.hygiene = 100;
        this.sleeping = false;
        this.hasWokenUpToday = false;
        this.experienceUpdatedToday = false;
    }

    public void saveGameState() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("savefile.dat"))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Tamagochi loadGameState() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("savefile.dat"))) {
            return (Tamagochi) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new Tamagochi();
        }
    }

    public boolean isAwake() {
        return isAwake;
    }

    public void setAwake(boolean isAwake) {
        this.isAwake = isAwake;
    }

    public void startArbeiten() {
        arbeiten();
    }

    public void arbeiten() {
        gameScreen = new GameScreen();
        gameScreen.resumeAllTimersAndAnimations();
        if (!sleeping) {
            decreaseStats();

            if (generation < 5) {
                while (experience >= 100) {
                    experience -= 100;
                    generation++;
                }
            }

            if (hygiene < 25) {
                mood -= 20;
            }
        }
    }

    public void spielen() {
        if (!sleeping) {
            motivation += 10;
            mood += 10;
            hygiene -= 5;
            caffeine -= 5;
        }
    }

    public void trinken() {
        if (!sleeping) {
            caffeine += 10;
            hygiene -= 5;
        }
    }

    public void aufraeumen() {
        if (!sleeping) {
            hygiene += 10;
            mood += 10;
            motivation -= 5;
            caffeine -= 5;
        }
    }

    public void schlafen() {
        gameScreen = new GameScreen();
        sleeping = true;
        System.out.println("Tamagochi schläft");
        gameScreen.pauseAllTimersAndAnimations();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getHasWokenUpToday() {
        return hasWokenUpToday;
    }

    public void setHasWokenUpToday(boolean hasWokenUpToday) {
        this.hasWokenUpToday = hasWokenUpToday;
    }

    public void aufwachen(int gameHour) {
        if (!hasWokenUpToday && gameHour >= 11 && gameHour < 23) {
            sleeping = false;
            auffuellen();
            hasWokenUpToday = true;
            experienceUpdatedToday = false;
            System.out.println("Tamagochi ist aufgewacht");
        } else {
            System.out.println("Tamagochi kann jetzt nicht aufwachen");
        }
    }

    public void auffuellen() {
        if (!sleeping) {
            this.motivation = 100;
            this.caffeine = 100;
            this.mood = 100;
            this.hygiene = 50;
            if (!experienceUpdatedToday) {
                this.experience += 20;
                experienceUpdatedToday = true;
            }
        }
    }

    public void resetDailyStats() {
        this.hasWokenUpToday = false;
        this.experienceUpdatedToday = false;
    }

    public void decreaseStats() {
        if (!isSleeping()) {
            caffeine -= 6;
            motivation -= 2;
            mood -= 1;
            hygiene -= 4;
        }
    }

    public boolean isTired(int gameHour) {
        return (gameHour >= 23 || gameHour < 11) && !isSleeping();
    }

    public int getMotivation() {
        return motivation;
    }

    public int getCaffeine() {
        return caffeine;
    }

    public int getMood() {
        return mood;
    }

    public int getHygiene() {
        return hygiene;
    }

    public int getExperience() {
        return experience;
    }

    public boolean isSleeping() {
        return sleeping;
    }

    public void setSleeping(boolean sleeping) {
        this.sleeping = sleeping;
    }

    public String getGeneration() {
        switch (generation) {
            case 1:
                return "Baby";
            case 2:
                return "Kind";
            case 3:
                return "Teenager";
            case 4:
                return "Erwachsener";
            case 5:
                return "Senior";
            default:
                return "Unbekannt";
        }
    }


    public void updateGeneration() {
        if (generation < 5) {
            while (experience >= 100) {
                experience -= 100;
                generation++;
            }
        }
    }

    public void setMotivation(int motivation) {
        this.motivation = motivation;
    }

    public void setCaffeine(int caffeine) {
        this.caffeine = caffeine;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public void setHygiene(int hygiene) {
        this.hygiene = hygiene;
    }
}


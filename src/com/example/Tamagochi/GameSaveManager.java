package com.example.Tamagochi;

import java.io.*;


public class GameSaveManager {
    private static final String SAVE_FILE = "tamagochi.ser";

    public static void saveGame(Tamagochi tamagochi) {
        try (FileOutputStream fileOut = new FileOutputStream(SAVE_FILE);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(tamagochi);
            System.out.println("Spielstand gespeichert.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Tamagochi loadGame() {
        Tamagochi tamagochi = null;
        try (FileInputStream fileIn = new FileInputStream(SAVE_FILE);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            tamagochi = (Tamagochi) in.readObject();
            System.out.println("Spielstand geladen.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tamagochi;
    }
}

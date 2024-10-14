package com.example.Tamagochi;

import java.io.Serializable;

public class TamagochiState implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int generation;
    private boolean isSleeping;

    public TamagochiState(String name, int generation, boolean isSleeping) {
        this.name = name;
        this.generation = generation;
        this.isSleeping = isSleeping;
    }

    public String getName() {
        return name;
    }

    public int getGeneration() {
        return generation;
    }

    public boolean isSleeping() {
        return isSleeping;
    }
}

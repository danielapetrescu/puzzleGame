package org.danskebank.puzzlegame.entities;

import java.util.List;

public class Player {
    private String name;

    private List<Integer> game;
    private boolean gameSolved = false;

    public Player(String name, List<Integer> game){
        this.name = name;
        this.game = game;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getGame() {
        return game;
    }

    public void setGame(List<Integer> game) {
        this.game = game;
    }

    public boolean isGameSolved() {
        return gameSolved;
    }

    public void setGameSolved(boolean isSolved) {
        this.gameSolved = isSolved;
    }
}

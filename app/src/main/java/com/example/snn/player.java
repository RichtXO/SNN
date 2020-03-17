package com.example.snn;

public class Player {

    private String name;
    private int score;
    private int death;

    public Player(String playerName){
        name = playerName;
        score = 0;
        death = 0;
    }

    public String getName(){ return name; }
    public int getScore(){ return score; }
    public int getDeath(){ return death; }

    public void setName(String playerName){ name = playerName; }
    public void setScore(int numScore){ score = numScore; }
    public void setDeath(int numDeath){ death = numDeath; }

    public void targetKilled(){ score++; }
    public void killed(){ death++; }
}

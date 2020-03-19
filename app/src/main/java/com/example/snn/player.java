package com.example.snn;

public class Player implements Comparable<Player>{

    private int _id;
    private String _playername;
    private int _score;
    private int _death;

    public Player(String playerName){
        this._playername = playerName;
        this._score = 0;
        this._death = 0;
    }

    public Player(String name, int score, int death){
        this._playername = name;
        this._score = score;
        this._death = death;
    }

    public String getName(){ return this._playername; }
    public int getScore(){ return this._score; }
    public int getDeath(){ return this._death; }

    public void setName(String playerName){ this._playername = playerName; }
    public void setScore(int numScore){ this._score = numScore; }
    public void setDeath(int numDeath){ this._death = numDeath; }

    public void targetKilled(){ this._score++; }
    public void killed(){ this._death++; }

    public int compareTo(Player a){ return getScore() - a.getScore(); }
}

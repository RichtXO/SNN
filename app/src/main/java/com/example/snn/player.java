package com.example.snn;

public class Player implements Comparable<Player>{

    private int _id;
    private String _playerName;
    private int _score;
    private int _death;

    public Player(String name){
        this._playerName = name;
        this._score = 0;
        this._death = 0;
    }

    public Player(String name, int score, int death){
        this._playerName = name;
        this._score = score;
        this._death = death;
    }

    public String getName(){ return this._playerName; }
    public int getScore(){ return this._score; }
    public int getDeath(){ return this._death; }

    public void setName(String playerName){ this._playerName = playerName; }
    public void setScore(int numScore){ this._score = numScore; }
    public void setDeath(int numDeath){ this._death = numDeath; }

    public void targetKilled(){ this._score++; }
    public void killed(){ this._death++; }

    public int compareTo(Player a){ return a.getScore() - getScore(); }
}

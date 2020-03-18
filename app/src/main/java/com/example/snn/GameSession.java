package com.example.snn;
import java.util.ArrayList;

public class GameSession {

    private ArrayList<Player> in_game;
    private ArrayList<Player> dead;
    private ArrayList<Pair> targets;


    public GameSession() {
        in_game = new ArrayList<Player>();
        dead = new ArrayList<Player>();
        targets = new ArrayList<Pair>();
    }

    public ArrayList<Player> getIn_game(){ return in_game; }
    public ArrayList<Player> getDead(){ return dead; }
    public ArrayList<Pair> getTargets(){ return targets; }

    public void addPlayer(Player player){ in_game.add(player); }
    public void removePlayer(String name){
        for (int i = 0; i < in_game.size(); i++){
            if (in_game.get(i).getName().equals(name)) {
                in_game.remove(in_game.get(i));
                break;
            }
        }
        for (int i = 0; i < dead.size(); i++) {
            if (dead.get(i).getName().equals(name)) {
                dead.remove(dead.get(i));
                break;
            }
        }
    }
    public void removeAllPlayers(){ in_game.clear(); dead.clear(); targets.clear(); }

    public void killedPlayer(Player killed){
        dead.add(killed);
        killed.killed();

        // Have to do something with target list
    }
}

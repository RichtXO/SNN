package com.example.snn;
import java.util.ArrayList;
import java.util.Random;

public class GameSession {

    private ArrayList<Player> in_game;
    private ArrayList<Player[]>pairs;
    private ArrayList<Player> dead;


    public GameSession() {
        in_game = new ArrayList<Player>();
        dead = new ArrayList<Player>();
        pairs = new ArrayList<Player[]>();
    }

    public ArrayList<Player> getIn_game(){ return in_game; }
    public ArrayList<Player> getDead(){ return dead; }
    public Player getPlayer(String name){
        for (int i = 0; i < in_game.size(); i++){
            if (in_game.get(i).getName().equals(name))
                return in_game.get(i);
        }
        return null;
    }


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
    public void removeAllPlayers(){ in_game.clear(); dead.clear(); pairs.clear(); }



    public void setPlayers(ArrayList<Player> list){
        this.in_game = (ArrayList<Player>) list.clone();
        randomize();
    }



    public Player whoTarget(String name){
        for (int i = 0; i < pairs.size(); i++){
            if (pairs.get(i)[0].getName().equals(name)){
                return pairs.get(i)[1];
            }
        }
        return null;
    }

    public void killedPlayer(Player killed){
        dead.add(killed);
        killed.killed();
        boolean ifBreak = false;
        for (Player[] x : pairs){
            if (x[1] == killed){
                Player killer = x[0];
                for (Player[] y: pairs){
                    if (y[0] == killed){
                        Player[] tempPair = {killer, y[0]};
                        pairs.add(tempPair);
                        pairs.remove(x);
                        pairs.remove(y);
                        ifBreak = true;
                        break;
                    }
                }
            }
            if (ifBreak)
                break;
        }
    }




    public void randomize(){
        ArrayList<Player> temp = (ArrayList<Player>) in_game.clone();

        for (int i = 0; i < in_game.size(); i++){
            Player randomSelected = getRandomPlayer(temp);
            if (randomSelected == in_game.get(i))
                randomSelected = getRandomPlayer(temp);
            temp.remove(randomSelected);
            Player[] tempPair = {in_game.get(i), getRandomPlayer(temp)};
            pairs.add(tempPair);
        }
    }


    public Player getRandomPlayer(ArrayList<Player> tempList){
        Random random = new Random();
        return tempList.get(random.nextInt(tempList.size()));
    }
}

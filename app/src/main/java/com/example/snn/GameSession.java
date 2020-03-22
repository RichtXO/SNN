package com.example.snn;
import java.util.ArrayList;
import java.util.Random;

class GameSession {

    private ArrayList<Player> in_game;
    private final ArrayList<Player[]>pairs;
    private final ArrayList<Player> dead;


    public GameSession() {
        in_game = new ArrayList<>();
        dead = new ArrayList<>();
        pairs = new ArrayList<>();
    }

    public ArrayList<Player> getIn_game(){ return in_game; }
    public ArrayList<Player> getDead(){ return dead; }
    public Player getPlayer(String name){
        for (Player i : in_game){
            if (i.getName().equals(name)){
                return i;
            }
        }
        return null;
    }

    public Player getDeadPlayer(String name){
        for (Player i : dead){
            if (i.getName().equals(name))
                return i;
        }
        return null;
    }


    public Player[] getPair(String target){
        for (Player[] i : pairs){
            if (i[1].getName().equals(target)){
                return i;
            }
        }
        return null;
    }


    public void addPlayer(Player player){ in_game.add(player); randomize(); }
    public void removePlayer(String name){
        killedPlayer(name);
        for (int i = 0; i < dead.size(); i++) {
            if (dead.get(i).getName().equals(name)) {
                dead.remove(dead.get(i));
                break;
            }
        }
    }
    public void removeAllPlayers(){ in_game.clear(); dead.clear(); pairs.clear(); }



    public void setPlayers(ArrayList<Player> list){
        this.in_game = new ArrayList<>(list);
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

    public void killedPlayer(String killed){
        Player player = new Player(killed);
        for (Player i : in_game){
            if (i.getName().equals(killed))
                player = i;
        }
        player.killed();
        in_game.remove(player);
        dead.add(player);
        boolean ifBreak = false;
        for (Player[] x : pairs){
            if (x[1] == player){
                Player killer = x[0];
                killer.targetKilled();
                for (Player[] y: pairs){
                    if (y[0] == player){
                        Player[] tempPair = {killer, y[1]};
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

    public void reset(){
        in_game.addAll(dead);
        pairs.clear();
        dead.clear();

    }

    public void resetScore(){
        for (Player i : in_game){
            i.setScore(0);
            i.setDeath(0);
        }
    }

    public void randomize(){
        reset();
        ArrayList<Player> temp = new ArrayList<>(in_game);

        if (temp.size() == 1){
            Player[] tempPair = {temp.get(0), temp.get(0)};
            pairs.add(tempPair);
        }

        if (temp.size() > 1){
            for (Player i : in_game){
                Player randomSelected = getRandomPlayer(temp);
                while(randomSelected.getName().equals(i.getName()))
                    randomSelected = getRandomPlayer(temp);
                temp.remove(randomSelected);

                Player[] tempPair = {i, randomSelected};
                pairs.add(tempPair);
            }
        }
    }


    private Player getRandomPlayer(ArrayList<Player> tempList){
        Random random = new Random();
        return tempList.get(random.nextInt(tempList.size()));
    }
}

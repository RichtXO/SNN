package com.example.snn;


public class Pair{

    private Player[] assignedTarget;


    public Pair(Player assignedPlayer, Player target){
        assignedTarget = new Player[] {assignedPlayer, target};
    }


    public void changeAssignedPlayer(Player player){ assignedTarget[0] = player; }
    public void changeTarget(Player target){ assignedTarget[1] = target; }

    public String getAssignedPlayerName(){return assignedTarget[0].getName();}
    public int getAssignedPlayerScore(){return assignedTarget[0].getScore();}
    public int getAssignedPlayerDeath(){return assignedTarget[0].getDeath();}
}

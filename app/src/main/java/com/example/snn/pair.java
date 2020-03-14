package com.example.snn;


public class pair{

    private player[] assignedTarget;


    public pair(player assignedPlayer, player target){
        assignedTarget = new player[]{ assignedPlayer, target}; }


    public void changeAssignedPlayer(player newPlayer){ assignedTarget[0] = newPlayer; }
    public void changeTarget(player target){ assignedTarget[1] = target; }

    public String getAssignedPlayerName(){return assignedTarget[0].getName();}
    public int getAssignedPlayerScore(){return assignedTarget[0].getScore();}
    public int getAssignedPlayerDeath(){return assignedTarget[0].getDeath();}
}

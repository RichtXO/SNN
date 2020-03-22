package com.example.snn;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.snn.ui.AddFragment;
import com.example.snn.ui.HomeFragment;
import com.example.snn.ui.KilledFragment;
import com.example.snn.ui.RemoveFragment;
import com.example.snn.ui.ScoreFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AddFragment.addListener,
        RemoveFragment.removeListener, KilledFragment.killedListener, HomeFragment.homeListener,
        ScoreFragment.scoreListener {

    private final GameSession game = new GameSession();
    private DBHandler dbAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_add, R.id.navigation_remove)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


        dbAccess = new DBHandler(this, null, null, 1);
        dbStartUp();

    }


    private void dbStartUp(){
        game.setPlayers(dbAccess.getTablePlayers());
    }


    @Override
    public boolean ifExist(String name) {
        name = name.toLowerCase();
        for (Player i : game.getIn_game()){
            if (i.getName().equals(name))
                return true;
        }
        for (Player i : game.getDead()){
            if (i.getName().equals(name))
                return true;
        }
        return false;
    }

    // Gets called by AddFragment when user is adding player to game
    @Override
    public void addPlayer(String name) {
        Player player = new Player(name.toLowerCase());
        game.addPlayer(player);
        dbAccess.addPlayer(player);
    }



    // Gets called by RemoveFragment when user is removing player from game
    @Override
    public void removePlayer(String name){
        game.removePlayer(name.toLowerCase());
        dbAccess.deletePlayer(name.toLowerCase());
    }

    // Gets called by RemoveFragment when user wants to delete player list
    @Override
    public void removeAllPlayers(){
        game.removeAllPlayers();
        dbAccess.deleteGame();
    }



    // Gets called by KilledFragment when a player got shot in game
    @Override
    public void killed(String name){
        Player[] temp = game.getPair(name.toLowerCase());
        game.killedPlayer(name.toLowerCase());
        dbAccess.updateKilled(temp[1]);
        dbAccess.updateScored(temp[0]);

    }



    // Gets called by HomeFragment to get the names sorted
    @Override
    public String[] getPlayerNames(){
        String[] result = new String[game.getIn_game().size()];
        for (int i = 0; i < game.getIn_game().size(); i++){
            String playerName = (game.getIn_game()).get(i).getName();
            result[i] = playerName.substring(0,1).toUpperCase() + playerName.substring(1).toLowerCase();
        }
        Arrays.sort(result);
        return result;
    }

    // Gets called by HomeFragment to randomized targets for each players
    @Override
    public void randomized() {
        Runnable run = new Runnable() {
            @Override
            public void run() {
                game.randomize();
            }
        };
        Thread thread = new Thread(run);
        thread.start();
    }


    @Override
    public void resetGame() {
        Runnable run = new Runnable() {
            @Override
            public void run() {
                game.reset();
                game.resetScore();
                dbAccess.resetGame();
                game.randomize();
            }
        };
        Thread thread = new Thread(run);
        thread.start();
    }

    // Gets called by HomeFragment to get the target of that player
    @Override
    public String getTarget(String name) {
        String result = game.whoTarget(name.toLowerCase()).getName();
        result = result.substring(0,1).toUpperCase() + result.substring(1).toLowerCase();
        return result;
    }


    // Gets called by ScoreFragment to have the highest scored player go on top
    @Override
    public Player[] getPlayers(){
        Player[] result = new Player[game.getIn_game().size() + game.getDead().size()];
        int tempSize;
        for (tempSize = 0; tempSize < game.getIn_game().size(); tempSize++)
            result[tempSize] = (game.getIn_game()).get(tempSize);
        for (int i = 0; i < game.getDead().size(); i++)
            result[tempSize + i] = (game.getDead()).get(i);
        Arrays.sort(result);
        return result;
    }
}

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

    private static final String TAG = "Message";
    GameSession game = new GameSession();

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
    }


    // Gets called by AddFragment when user is adding player to game
    @Override
    public void addPlayer(String name) { game.addPlayer(new Player(name)); }


    // Gets called by RemoveFragment when user is removing player from game
    @Override
    public void removePlayer(String name){ game.removePlayer(name); }

    // Gets called by RemoveFragment when user wants to delete player list
    @Override
    public void removeAllPlayers(){
        game.removeAllPlayers();
    }


    // Gets called by KilledFragment when a player got shot in game
    @Override
    public void killed(String name){ game.killedPlayer(new Player(name)); }


    // Gets called by HomeFragment to get the names sorted
    @Override
    public String[] getPlayerNames(){
        String[] result = new String[game.getIn_game().size()];
        for (int i = 0; i < game.getIn_game().size(); i++){
            result[i] = (game.getIn_game()).get(i).getName();
        }
        Arrays.sort(result);
        return result;
    }


    // Gets called by ScoreFragment to have the highest scored player go on top
    @Override
    public Player[] getPlayers(){
        Player[] result = new Player[game.getIn_game().size()];
        for (int i = 0; i < game.getIn_game().size(); i++)
            result[i] = (game.getIn_game()).get(i);
        Arrays.sort(result);
        return result;
    }

}

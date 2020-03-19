package com.example.snn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TableLayout;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME= "game_session.db";

    public static final String TABLE_PLAYERS = "players";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "_name";
    public static final String COLUMN_SCORE = "_score";
    public static final String COLUMN_DEATH = "_death";



    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_PLAYERS + " (" + COLUMN_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT ,"
                + COLUMN_SCORE + " INTEGER ," + COLUMN_DEATH + " INTEGER );";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYERS);
        onCreate(db);
    }

    // Adding new players into database
    public void addPlayer(Player player){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, player.getName());
        values.put(COLUMN_SCORE, player.getScore());
        values.put(COLUMN_DEATH, player.getDeath());
        db.insert(TABLE_PLAYERS, null, values);
        db.close();
    }


    // Deleting players from database
    public void deletePlayer(String playerName){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PLAYERS + " WHERE " + COLUMN_NAME + " =\""
                + playerName + "\";");
        db.close();
    }


    public void deleteGame(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYERS + ";");
        onCreate(db);
        db.close();
    }


    public void updateKilled(Player player){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_DEATH, player.getDeath());
        db.update(TABLE_PLAYERS, cv, "_name="+player.getName(), null);
        db.close();
    }

    public void updateScored(Player player){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_SCORE, player.getScore());
        db.update(TABLE_PLAYERS, cv, "_name="+player.getName(), null);
        db.close();
    }

    // Return a string with player names as string
    public ArrayList<String> getTablePlayerNames(){
        ArrayList<String> result = new ArrayList<String>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT " + COLUMN_NAME + " FROM " + TABLE_PLAYERS;

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            if (c.getString(c.getColumnIndex("playername")) != null){
                result.add(c.getString(c.getColumnIndex("playername")));
            }
        }
        db.close();
        return result;
    }

    // Return a string with all players' data
    public ArrayList<Player> getTablePlayers(){
        ArrayList<Player> result = new ArrayList<Player>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PLAYERS + ";";


        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            if (c.getString(c.getColumnIndex("playername")) != null){
                Player temp = new Player(c.getString(c.getColumnIndex("playername")),
                        c.getInt(c.getColumnIndex("score")),
                        c.getInt(c.getColumnIndex("death")));
                result.add(temp);
            }
        }

        db.close();
        return result;
    }
}
package com.example.snn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME= "game_session.db";

    private static final String TABLE_PLAYERS = "players";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "_name";
    private static final String COLUMN_SCORE = "_score";
    private static final String COLUMN_DEATH = "_death";


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
        values.put(COLUMN_NAME, player.getName());
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
        db.update(TABLE_PLAYERS, cv, "_name='"+player.getName() + "'", null);
        db.close();
    }

    public void updateScored(Player player){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_SCORE, player.getScore());
        db.update(TABLE_PLAYERS, cv, "_name='"+player.getName() + "'", null);
        db.close();
    }

    // Return a string with player names as string
    public ArrayList<String> getTablePlayerNames(){
        ArrayList<String> result = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();

        String[] projection = {COLUMN_NAME};
        String sortOrder = COLUMN_NAME + " DESC";
        Cursor c = db.query(TABLE_PLAYERS, projection,null,null,null,null, sortOrder);

        c.moveToFirst();
        while(c.moveToNext()){
            if (c.getString(c.getColumnIndex(COLUMN_NAME)) != null){
                result.add(c.getString(c.getColumnIndex(COLUMN_NAME)));
            }
        }
        c.close();
        db.close();
        return result;
    }

    // Return a string with all players' data
    public ArrayList<Player> getTablePlayers(){
        ArrayList<Player> result = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();

        String[] projection = {COLUMN_NAME, COLUMN_SCORE, COLUMN_DEATH};
        String sortOrder = COLUMN_NAME + " DESC";
        Cursor c = db.query(TABLE_PLAYERS, projection,null,null,null,null, sortOrder);

        c.moveToFirst();
        while(c.moveToNext()){
            if (c.getString(c.getColumnIndex(COLUMN_NAME)) != null){
                Player temp = new Player(c.getString(c.getColumnIndex(COLUMN_NAME)),
                        c.getInt(c.getColumnIndex(COLUMN_SCORE)),
                        c.getInt(c.getColumnIndex(COLUMN_DEATH)));
                result.add(temp);
            }
        }
        c.close();
        db.close();
        return result;
    }
}
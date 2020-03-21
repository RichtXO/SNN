package com.example.snn.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.snn.Player;
import com.example.snn.R;


class scoreAdapter extends ArrayAdapter<Player> {

    scoreAdapter(Context context, Player[] players){ super(context, R.layout.score_board, players); }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        @SuppressLint("ViewHolder") View scoreView = inflater.inflate(R.layout.score_board, parent, false);

        TextView name = scoreView.findViewById(R.id.displayName);
        TextView score = scoreView.findViewById(R.id.displayScore);
        TextView death = scoreView.findViewById(R.id.displayDeath);

        String playerName = getItem(position).getName();
        name.setText(playerName.substring(0,1).toUpperCase() + playerName.substring(1).toLowerCase());
        score.setText("Score: " + getItem(position).getScore());
        death.setText("Death:" + getItem(position).getDeath());


        return scoreView;
    }
}

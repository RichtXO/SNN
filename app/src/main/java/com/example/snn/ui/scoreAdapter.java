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

import java.util.Objects;

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

        name.setText(getItem(position).getName());
        score.setText("Score: " + Integer.toString(getItem(position).getScore()));
        death.setText("Death:" + Integer.toString(getItem(position).getDeath()));


        return scoreView;
    }
}

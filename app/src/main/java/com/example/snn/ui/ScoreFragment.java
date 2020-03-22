package com.example.snn.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.snn.Player;
import com.example.snn.R;


public class ScoreFragment extends Fragment {


    private TextView message;

    private scoreListener activityCommander;
    public interface scoreListener {
        Player[] getPlayers();
    }

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        try{
            activityCommander = (scoreListener) getActivity();
        }catch (ClassCastException e){
            message.setText(R.string.scoreEmpty);
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_score, container, false);
        message = view.findViewById(R.id.scoreMessage);


        Player[] names = activityCommander.getPlayers();
        if (names.length != 0) {
            ListAdapter listAdapter = new scoreAdapter(getContext(), names);
            ListView board = view.findViewById(R.id.scoreListView);
            board.setAdapter(listAdapter);
        }
        else{
            message.setText(R.string.scoreEmpty);
        }

        return view;
    }

}

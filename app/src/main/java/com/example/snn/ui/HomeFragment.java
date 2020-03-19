package com.example.snn.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.snn.MainActivity;
import com.example.snn.Player;
import com.example.snn.R;

import java.util.Arrays;
import java.util.Objects;

public class HomeFragment extends Fragment implements targetDisplay.targetDisplayListener {


    private String killer;

    homeListener activityCommander;
    public interface homeListener {
        public String[] getPlayerNames();
        public String getTarget(String name);
        public void randomized();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            activityCommander = (homeListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(Objects.requireNonNull(getActivity()).toString());
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        String[] names = activityCommander.getPlayerNames();
        Arrays.sort(names);
        ListAdapter listAdapter = new rowAdapter(getContext(), names);
        ListView home = view.findViewById(R.id.playerListView);
        home.setAdapter(listAdapter);


        home.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        killer = String.valueOf(adapterView.getItemAtPosition(i));
                        startActivity(new Intent(getActivity(), targetDisplay.class));
                    }
                }
        );




        Button randomBtn = view.findViewById(R.id.randomButton);

        randomBtn.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        randomBtnClicked();
                    }
                }
        );

        return view;
    }



    private void randomBtnClicked(){
        activityCommander.randomized();
    }


    @Override
    public String getTargetName(){
        return activityCommander.getTarget(killer);
    }

}

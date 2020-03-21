package com.example.snn.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.snn.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;
import java.util.Objects;

public class HomeFragment extends Fragment {


    private String killer;

    private homeListener activityCommander;
    public interface homeListener {
        String[] getPlayerNames();
        void randomized();
        String getTarget(String name);
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
        //ListAdapter listAdapter = new rowAdapter(getContext(), names);
        ListAdapter listAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), android.R.layout.simple_list_item_1, names);
        ListView home = view.findViewById(R.id.playerListView);
        home.setAdapter(listAdapter);


        home.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        killer = activityCommander.getTarget(String.valueOf(adapterView.getItemAtPosition(i)));
                        killer = killer.substring(0,1).toUpperCase() + killer.substring(1).toLowerCase();
                        Intent intent = new Intent(getActivity(), targetDisplay.class);
                        intent.putExtra("killerName", killer);
                        startActivity(intent);
                    }
                }
        );




        FloatingActionButton randomBtn = view.findViewById(R.id.randomButton);

        randomBtn.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        randomBtnClicked();
                        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                        ft.detach(HomeFragment.this).attach(HomeFragment.this).commit();
                    }
                }
        );

        return view;
    }



    private void randomBtnClicked(){
        activityCommander.randomized();
    }
}

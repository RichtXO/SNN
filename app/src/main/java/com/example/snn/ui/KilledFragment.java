package com.example.snn.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.snn.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class KilledFragment extends Fragment {

    private EditText nameInput;

    private killedListener activityCommander;
    public interface killedListener{
        void killed(String name);
        boolean ifExist(String name);
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            activityCommander = (killedListener) getActivity();
        }catch (ClassCastException e){
            throw new ClassCastException(Objects.requireNonNull(getActivity()).toString());
        }
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_killed, container, false);

        nameInput = view.findViewById(R.id.editKilled);
        final FloatingActionButton killedBtn = view.findViewById(R.id.killedButton);

        killedBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        btnClicked(view);
                    }
                }
        );

        return view;
    }

    private void btnClicked(View view){

        if (nameInput.getText().toString().equals("")){
            Toast.makeText(getContext(), "Enter in name.", Toast.LENGTH_SHORT).show();
        }
        else if (activityCommander.ifExist(nameInput.getText().toString())){
            activityCommander.killed(nameInput.getText().toString());
            Toast.makeText(getContext(), nameInput.getText().toString().substring(0,1).toUpperCase() +
                    nameInput.getText().toString().substring(1).toLowerCase() + " has been marked KILLED", Toast.LENGTH_SHORT).show();
            nameInput.getText().clear();
        }
        else{
            Toast.makeText(getContext(), nameInput.getText().toString().substring(0, 1).toUpperCase() +
                            nameInput.getText().toString().substring(1).toLowerCase() + " doesn't exist in game",
                    Toast.LENGTH_SHORT).show();
            nameInput.getText().clear();
        }

    }
}

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

public class RemoveFragment extends Fragment {

    private EditText nameInput;

    private removeListener activityCommander;
    public interface removeListener{
        void removePlayer(String name);
        void removeAllPlayers();
        boolean ifExist(String name);
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            activityCommander = (removeListener) getActivity();
        }catch (ClassCastException e){
            throw new ClassCastException(Objects.requireNonNull(getActivity()).toString());
        }
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_remove, container, false);

        nameInput = view.findViewById(R.id.editRemove);
        final FloatingActionButton removeBtn = view.findViewById(R.id.removeBtn);
        final FloatingActionButton removeAllBtn = view.findViewById(R.id.removeAllButton);

        removeBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        removeBtnClicked(view);
                    }
                }
        );

        removeAllBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        removeAllBtnClicked(view);
                    }
                }
        );


        return view;
    }

    private void removeBtnClicked(View view){
        if (nameInput.getText().toString().equals("")){
            Toast.makeText(getContext(), "Enter a name", Toast.LENGTH_SHORT).show();
        }
        else if (!activityCommander.ifExist(nameInput.getText().toString())) {
            Toast.makeText(getContext(), nameInput.getText().toString().substring(0, 1).toUpperCase() +
                            nameInput.getText().toString().substring(1).toLowerCase() + " is already removed",
                    Toast.LENGTH_SHORT).show();
            nameInput.getText().clear();
        }
        else{
                activityCommander.removePlayer(nameInput.getText().toString());
                Toast.makeText(getContext(), nameInput.getText().toString().substring(0,1).toUpperCase() +
                        nameInput.getText().toString().substring(1).toLowerCase() + " has been removed from game", Toast.LENGTH_SHORT).show();
                nameInput.getText().clear();
        }
    }


    private void removeAllBtnClicked(View view){
        activityCommander.removeAllPlayers();
        Toast.makeText(getContext(), "Cleared Player List", Toast.LENGTH_SHORT).show();
    }

}

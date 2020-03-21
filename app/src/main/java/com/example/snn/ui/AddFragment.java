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

public class AddFragment extends Fragment {

    private EditText nameInput;

    private addListener activityCommander;
    public interface addListener{
        void addPlayer(String name);
        boolean ifExist(String name);
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            activityCommander = (addListener)getActivity();
        }catch (ClassCastException e){
            throw new ClassCastException(Objects.requireNonNull(getActivity()).toString());
        }
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        nameInput = view.findViewById(R.id.editAdd);
        final FloatingActionButton addBtn = view.findViewById(R.id.addButton);

        addBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        buttonClicked(view);
                    }
                }
        );

        return view;
    }

    private void buttonClicked(View view){
        if (nameInput.getText().toString().equals("")){
            Toast.makeText(getContext(), "Enter a name", Toast.LENGTH_SHORT).show();
        }
        else if (activityCommander.ifExist(nameInput.getText().toString())) {
            Toast.makeText(getContext(), nameInput.getText().toString().substring(0, 1).toUpperCase() +
                            nameInput.getText().toString().substring(1).toLowerCase() + " is already added",
                    Toast.LENGTH_SHORT).show();
            nameInput.getText().clear();
        }
        else {
            activityCommander.addPlayer(nameInput.getText().toString());
            Toast.makeText(getContext(), nameInput.getText().toString().substring(0,1).toUpperCase() +
                    nameInput.getText().toString().substring(1).toLowerCase() + " is added", Toast.LENGTH_SHORT).show();
            nameInput.getText().clear();
        }
    }
}

package com.example.snn.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.snn.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class KilledFragment extends Fragment {

    private static EditText nameInput;

    killedListener activityCommander;
    public interface killedListener{
        public void killed(String name);
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
        activityCommander.killed(nameInput.getText().toString());
        Toast.makeText(getContext(), nameInput.getText().toString() + " has been marked KILLED", Toast.LENGTH_LONG).show();
        nameInput.getText().clear();
    }
}

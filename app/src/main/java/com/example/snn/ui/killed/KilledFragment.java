package com.example.snn.ui.killed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.snn.R;

public class KilledFragment extends Fragment {

    private KilledViewModel killedViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        killedViewModel =
                ViewModelProviders.of(this).get(KilledViewModel.class);
        View root = inflater.inflate(R.layout.fragment_killed, container, false);
        final TextView textView = root.findViewById(R.id.text_killed);
        killedViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}

package com.example.snn.ui.remove;

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

public class RemoveFragment extends Fragment {

    private RemoveViewModel removeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        removeViewModel =
                ViewModelProviders.of(this).get(RemoveViewModel.class);
        View root = inflater.inflate(R.layout.fragment_remove, container, false);
        final TextView textView = root.findViewById(R.id.text_remove);
        removeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}

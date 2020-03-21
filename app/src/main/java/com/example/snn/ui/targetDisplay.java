package com.example.snn.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.snn.MainActivity;
import com.example.snn.R;

import java.util.Objects;

public class targetDisplay extends Activity {


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.target_display);

        Bundle data = getIntent().getExtras();
        if (data == null){
            return;
        }
        String killerName = data.getString("killerName");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width* .8), (int) (height * .1));

        TextView message = findViewById(R.id.targetDisplayText);
        message.setText(killerName + " is the target");
    }
}

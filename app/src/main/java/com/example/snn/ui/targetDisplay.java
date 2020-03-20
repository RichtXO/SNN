package com.example.snn.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.snn.R;

public class targetDisplay extends Activity {

    targetDisplayListener activityCommander;
    public interface targetDisplayListener{
        String getTargetName();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.target_display);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width* .4), (int) (height * .4));


        TextView message = findViewById(R.id.targetDisplayText);
        message.setText(activityCommander.getTargetName()+ " is the target");
    }
}

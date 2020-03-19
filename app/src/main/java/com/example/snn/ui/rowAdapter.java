package com.example.snn.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.snn.R;

class rowAdapter extends ArrayAdapter<String> {

    rowAdapter(Context context, String[] names){
        super(context, R.layout.button_row, names);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        @SuppressLint("ViewHolder") View rowView = inflater.inflate(R.layout.button_row, parent, false);


        String firstName = getItem(position);
        Button btn = rowView.findViewById(R.id.Btn);


        btn.setText(firstName);
        return rowView;
    }
}

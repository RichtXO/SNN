package com.example.snn.ui.killed;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class KilledViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public KilledViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is killed fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
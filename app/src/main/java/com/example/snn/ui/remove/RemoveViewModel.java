package com.example.snn.ui.remove;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RemoveViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RemoveViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is remove fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
package com.ranajeetbarik2205.icds.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    List<PieEntry> entries = new ArrayList<>();
    public HomeViewModel(@NonNull Application application) {
        super(application);
    }
}

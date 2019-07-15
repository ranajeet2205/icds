package com.ranajeetbarik2205.icds.viewmodels;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ranajeetbarik2205.icds.database.ThrRepository;
import com.ranajeetbarik2205.icds.models.THR;

import java.util.List;

public class ThrViewModel extends AndroidViewModel {

    private ThrRepository thrRepository;
    private LiveData<List<THR>> thrLiveDataList;

    public ThrViewModel(@NonNull Application application) {
        super(application);
        thrRepository = new ThrRepository(application);
        thrLiveDataList = thrRepository.getThrLiveDataList();
    }

    public LiveData<List<THR>> getThrLiveDataList(){
        return thrLiveDataList;
    }

    public void insertThrData(THR thr){
        thrRepository.insertThrData(thr);
    }

    public int getNumberOfThrEntries(String centre,String month){
        int numberOfEntries = thrRepository.numberOfEntries(centre, month);
        return numberOfEntries;
    }
    public boolean isValid(THR thr){
        String totalBnf = thr.getNumber_total_beneficiary();
        String thrPhotoPath = thr.getUri_thr_photo();
        return !TextUtils.isEmpty(totalBnf) && !TextUtils.equals(totalBnf,"0") && !TextUtils.isEmpty(thrPhotoPath);
    }
}

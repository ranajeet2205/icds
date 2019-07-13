package com.ranajeetbarik2205.icds.viewmodels;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ranajeetbarik2205.icds.database.ImmunizationRepository;
import com.ranajeetbarik2205.icds.models.Immunization;

import java.util.List;

public class ImmunizationViewModel extends AndroidViewModel {

    private ImmunizationRepository immunizationRepository;
    private LiveData<List<Immunization>> immunizationListLiveData;

    public ImmunizationViewModel(@NonNull Application application) {
        super(application);
        immunizationRepository = new ImmunizationRepository(application);
        immunizationListLiveData = immunizationRepository.getImmunizationList();
    }

    public LiveData<List<Immunization>> getImmunizationListLiveData(){
        return  immunizationListLiveData;
    }

    public void insertImmunizationData(Immunization immunization){
        immunizationRepository.insertImmunizationList(immunization);
    }

    public boolean isValid(Immunization immunization){
        String totalDue = immunization.getNumber_total_due();
        String totalReceived = immunization.getTotal_number_received();
        String thrPhotoPath  = immunization.getUri_photo_immunization();

        return !TextUtils.isEmpty(totalDue) && !TextUtils.isEmpty(totalReceived) && !TextUtils.isEmpty(thrPhotoPath);
    }

}

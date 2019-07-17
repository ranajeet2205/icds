package com.ranajeetbarik2205.icds.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.github.mikephil.charting.utils.Utils;
import com.ranajeetbarik2205.icds.database.MprRepository;
import com.ranajeetbarik2205.icds.database.WeightRepository;
import com.ranajeetbarik2205.icds.models.MPR;
import com.ranajeetbarik2205.icds.models.Weight;

import java.util.List;

public class MprViewModel extends AndroidViewModel {

    private MprRepository mprRepository;
    private WeightRepository weightRepository;
    private LiveData<List<MPR>> mprList;
    private LiveData<List<Weight>> weightList;

    public MprViewModel(@NonNull Application application) {
        super(application);
        mprRepository = new MprRepository(application);
        weightRepository = new WeightRepository(application);
        mprList = mprRepository.getMprList();
        weightList = weightRepository.getWeightList();
    }

    public LiveData<List<MPR>> getMprList(){
        return mprList;
    }

    public void insertMprData(MPR mpr){
        mprRepository.insertMprData(mpr);
    }

    public LiveData<List<Weight>> getWeightList(){
        return weightList;
    }

    public void insertWeightData(Weight weight){
        weightRepository.insertWeight(weight);
    }

    public void getStatusUpdate(String centre){
        mprRepository.getApproved(centre);
    }
    public int numberOfMprEntry(String centre,String month){
       int numberEntries =  mprRepository.numberMprEntry(centre,month);
       return numberEntries;
    }

    public boolean isValid(MPR mpr,Weight weight){
        try{
            int totalBabiesInWeight,totalPreschoolInWeight,numberNormalBabies,numberModerateBabies,numberSumBabies,
                    numberNormalPreschool,numberModeratePreschool,numberSumPreschool,totalBabies,totalPreschool;
            String nameAWW,nameAWH,month,pmNumber,nmNumber;

            totalBabiesInWeight = Integer.parseInt(mpr.getNumber_babies());
            totalPreschoolInWeight = Integer.parseInt(mpr.getNumber_preschool_children());
            nameAWH = mpr.getAwh_name();
            nameAWW = mpr.getAww_name();
            month=mpr.getReporting_month();
            pmNumber = mpr.getNumber_pm();
            nmNumber = mpr.getNumber_nm();
            numberNormalBabies = Integer.parseInt(weight.getNumber_normal_babies());
            numberModerateBabies = Integer.parseInt(weight.getNumber_moderate_babies());
            numberSumBabies = Integer.parseInt(weight.getNumber_smn_babies());
            numberNormalPreschool = Integer.parseInt(weight.getNumber_preschool_children());
            numberModeratePreschool = Integer.parseInt(weight.getNumber_moderate_children());
            numberSumPreschool = Integer.parseInt(weight.getNumber_snm_children());

            totalBabies = numberNormalBabies+numberModerateBabies+numberSumBabies;
            totalPreschool = numberNormalPreschool+numberModeratePreschool+numberSumPreschool;

            return totalBabies == totalBabiesInWeight && totalPreschool == totalPreschoolInWeight && !nameAWH.isEmpty()
                    && !nameAWW.isEmpty() && !month.isEmpty() && !pmNumber.isEmpty() && !nmNumber.isEmpty();
        }catch (Exception e){
           return false;
        }

    }
}

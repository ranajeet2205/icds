package com.ranajeetbarik2205.icds.viewmodels;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ranajeetbarik2205.icds.database.BnfRepository;
import com.ranajeetbarik2205.icds.models.BNF;

import java.util.List;

public class BnfViewModel extends AndroidViewModel {

    private BnfRepository bnfRepository;
    private LiveData<List<BNF>> bnfLiveDataList;


    public BnfViewModel(@NonNull Application application) {
        super(application);
        bnfRepository = new BnfRepository(application);
        bnfLiveDataList = bnfRepository.getBnfLiveDataList();
    }

    public LiveData<List<BNF>> getBnfLiveDataList(){
        return bnfLiveDataList;
    }

    public void insertBnfData(BNF bnf){
        bnfRepository.insertBnfData(bnf);
    }

    public int numberOfEntries(String centre,String month){
        int numberEntries = bnfRepository.numberOfBnfEntries(centre,month);
        return numberEntries;
    }

    public void gotStatusUpdate(String centre){
        bnfRepository.gotStatusUpdate(centre);
    }

    public boolean isValid(BNF bnf){
        String numberPM = bnf.getNumber_pm();
        String numberNM = bnf.getNumber_nm();
        String numberBabies = bnf.getNumber_babies();
        String numberPreschool = bnf.getNumber_preschool();
        String totalBnf = bnf.getNumber_total_beneficiary();
        int totalNumbers = Integer.parseInt(numberPM) + Integer.parseInt(numberNM) + Integer.parseInt(numberBabies) + Integer.parseInt(numberPreschool);

        return !TextUtils.isEmpty(numberPM) && !TextUtils.isEmpty(numberNM) && !TextUtils.isEmpty(numberBabies) &&
                !TextUtils.isEmpty(numberPreschool) && !TextUtils.isEmpty(totalBnf) && !totalBnf.equals("0") && TextUtils.equals(totalBnf,String.valueOf(totalNumbers));
    }
}

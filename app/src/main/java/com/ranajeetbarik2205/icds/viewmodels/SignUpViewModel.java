package com.ranajeetbarik2205.icds.viewmodels;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ranajeetbarik2205.icds.database.UserRepository;
import com.ranajeetbarik2205.icds.models.User;

import java.util.List;

public class SignUpViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private LiveData<List<User>> userLiveDataList;
    public SignUpViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        userLiveDataList = userRepository.getUserLiveDataList();
    }

    public void insertUser(User user){
        userRepository.insertUser(user);
    }

    public LiveData<List<User>> getUserLiveDataList(){
        return userLiveDataList;
    }

    public boolean isValid(User user){
        String fullName,designation,phoneNumber,email,aadharNumber,votorId,
                dateOfJoining,joiningDistrict,joiningProject,address;

        fullName = user.getFull_name();
        designation = user.getDesignation();
        phoneNumber = user.getPhone_number();
        email = user.getEmail();
        aadharNumber = user.getAadhar();
        votorId = user.getVotor_id();
        dateOfJoining = user.getDate_of_joining();
        joiningDistrict = user.getJoining_district();
        joiningProject = user.getJoining_project();
        address = user.getAddress();

        return !TextUtils.isEmpty(fullName)  && !TextUtils.isEmpty(designation) && !TextUtils.isEmpty(phoneNumber)
                && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(aadharNumber) && !TextUtils.isEmpty(votorId)
                && !TextUtils.isEmpty(dateOfJoining) && !TextUtils.isEmpty(joiningDistrict) && !TextUtils.isEmpty(joiningProject)
                && !TextUtils.isEmpty(address);
    }
}

package com.ranajeetbarik2205.icds.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ranajeetbarik2205.icds.database.UserRepository;
import com.ranajeetbarik2205.icds.models.User;

import java.util.List;

public class LoginViewModel extends AndroidViewModel {
    UserRepository userRepository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }


}

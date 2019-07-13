package com.ranajeetbarik2205.icds.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.ranajeetbarik2205.icds.models.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertAll(User User);

    @Query("SELECT * FROM user")
    LiveData<List<User>> getAllUserDetails();
}

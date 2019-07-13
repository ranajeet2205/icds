package com.ranajeetbarik2205.icds.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.ranajeetbarik2205.icds.models.THR;

import java.util.List;

@Dao
public interface THRDao {

    @Insert
    void insertTHR(THR thr);

    @Query("SELECT * FROM take_home_ration")
    LiveData<List<THR>> thrList();

    @Query("SELECT COUNT(*) FROM take_home_ration WHERE centre=:centre and reporting_month=:reporting_month")
    int numberOfEntriesThr(String centre , String reporting_month);
}

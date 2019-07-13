package com.ranajeetbarik2205.icds.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.ranajeetbarik2205.icds.models.MPR;

import java.util.List;

@Dao
public interface MPRDao {

    @Insert
    void insertMPRAll(MPR mpr);

    @Query("SELECT * FROM monthly_progress")
    LiveData<List<MPR>> mprList();

    @Query("SELECT COUNT(*) FROM monthly_progress WHERE centre=:centre and reporting_month=:reporting_month")
    int numberOfEntriesMpr(String centre , String reporting_month);

    @Query("SELECT COUNT(*) FROM monthly_progress")
    int totalNumberOfEntries();

    @Query("SELECT centre FROM monthly_progress")
    LiveData<List<String>> centres();
}

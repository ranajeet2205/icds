package com.ranajeetbarik2205.icds.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.ranajeetbarik2205.icds.models.Immunization;

import java.util.List;

@Dao
public interface ImmunizationDao {

    @Insert
    void insertAllImmunization(Immunization immunization);

    @Query("SELECT * FROM immunization")
    LiveData<List<Immunization>> immunizationList();

    @Query("SELECT COUNT(*) FROM immunization WHERE centre=:centre and reporting_month=:reporting_month")
    int numberOfEntriesImmunization(String centre , String reporting_month);

    @Query("UPDATE immunization SET status=1 WHERE centre=:centre")
    void gotUpdate(String centre);

}

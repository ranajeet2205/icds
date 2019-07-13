package com.ranajeetbarik2205.icds.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.ranajeetbarik2205.icds.models.Weight;

import java.util.List;

@Dao
public interface WeightDao {
    @Insert
    void insertWeightData(Weight weight);

    @Query("SELECT * FROM children_weight")
    LiveData<List<Weight>> weightDataList();

    @Query("SELECT COUNT(*) FROM children_weight WHERE centre=:centre and reporting_month=:reporting_month")
    int numberOfEntriesWeight(String centre , String reporting_month);
}

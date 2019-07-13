package com.ranajeetbarik2205.icds.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.ranajeetbarik2205.icds.models.BNF;

import java.util.List;

@Dao
public interface BNFDao {

    @Insert
    void insertBNFDao(BNF bnf);

    @Query("SELECT * FROM beneficiary")
    LiveData<List<BNF>> bnfList();

    @Query("SELECT COUNT(*) FROM beneficiary WHERE centre=:centre and reporting_month=:reporting_month")
    int numberOfEntriesBnf(String centre , String reporting_month);

}

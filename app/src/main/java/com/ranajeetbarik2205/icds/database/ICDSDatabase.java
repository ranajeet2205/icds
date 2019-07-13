package com.ranajeetbarik2205.icds.database;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.Entity;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.ranajeetbarik2205.icds.dao.BNFDao;
import com.ranajeetbarik2205.icds.dao.ImmunizationDao;
import com.ranajeetbarik2205.icds.dao.MPRDao;
import com.ranajeetbarik2205.icds.dao.THRDao;
import com.ranajeetbarik2205.icds.dao.UserDao;
import com.ranajeetbarik2205.icds.dao.WeightDao;
import com.ranajeetbarik2205.icds.models.BNF;
import com.ranajeetbarik2205.icds.models.Immunization;
import com.ranajeetbarik2205.icds.models.MPR;
import com.ranajeetbarik2205.icds.models.THR;
import com.ranajeetbarik2205.icds.models.User;
import com.ranajeetbarik2205.icds.models.Weight;

@Database(entities = {MPR.class, Immunization.class, THR.class, BNF.class, Weight.class, User.class}, version = 1)
public abstract class ICDSDatabase extends RoomDatabase {

    public abstract MPRDao mprDao();

    public abstract ImmunizationDao immunizationDao();

    public  abstract BNFDao bnfDao();

    public abstract THRDao thrDao();

    public abstract WeightDao weightDao();

    public abstract UserDao userDao();
}

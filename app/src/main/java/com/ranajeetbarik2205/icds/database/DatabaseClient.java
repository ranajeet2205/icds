package com.ranajeetbarik2205.icds.database;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {

    private Context context;
    private static DatabaseClient databaseClient;
    private ICDSDatabase icdsDatabase;

    public DatabaseClient(Context context){
        this.context = context;
        icdsDatabase = Room.databaseBuilder(context,ICDSDatabase.class,"ICDSDatabase").build();
    }

    public static synchronized DatabaseClient getDatabaseClient(Context context){
        if (databaseClient==null){
            databaseClient = new DatabaseClient(context);
        }
        return databaseClient;
    }

    public ICDSDatabase getICDSDatabase(){
        return icdsDatabase;
    }
}

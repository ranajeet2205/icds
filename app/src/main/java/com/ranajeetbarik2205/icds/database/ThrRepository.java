package com.ranajeetbarik2205.icds.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ranajeetbarik2205.icds.dao.THRDao;
import com.ranajeetbarik2205.icds.models.THR;

import java.util.List;

public class ThrRepository {
    private THRDao thrDao;
    private LiveData<List<THR>> thrLiveDataList;

    public ThrRepository(Application application){
        thrDao = DatabaseClient.getDatabaseClient(application).getICDSDatabase().thrDao();
        thrLiveDataList = thrDao.thrList();
    }

    public LiveData<List<THR>> getThrLiveDataList(){
        return thrLiveDataList;
    }

    public void insertThrData(THR thr){
        new InsertThrDataTask(thrDao).execute(thr);
    }

    public class InsertThrDataTask extends AsyncTask<THR,Void,Void>{
        private THRDao thrDao;
        public InsertThrDataTask(THRDao thrDao){
            this.thrDao = thrDao;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(THR... thrs) {
            thrDao.insertTHR(thrs[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
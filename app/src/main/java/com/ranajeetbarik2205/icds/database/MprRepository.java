package com.ranajeetbarik2205.icds.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ranajeetbarik2205.icds.dao.MPRDao;
import com.ranajeetbarik2205.icds.models.MPR;

import java.util.List;

public class MprRepository {
    private MPRDao mprDao;
    private LiveData<List<MPR>> mprList;

    public MprRepository(Application application){
        mprDao = DatabaseClient.getDatabaseClient(application).getICDSDatabase().mprDao();
        mprList = mprDao.mprList();
    }

    public LiveData<List<MPR>> getMprList(){
        return mprList;
    }

    public void insertMprData(MPR mpr){
        new InsertMprDataTask(mprDao).execute(mpr);
    }

    public class InsertMprDataTask extends AsyncTask<MPR,Void,Void>{
        private MPRDao mprDao;

        public InsertMprDataTask(MPRDao mprDao){
            this.mprDao = mprDao;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(MPR... mprs) {
            mprDao.insertMPRAll(mprs[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }


}

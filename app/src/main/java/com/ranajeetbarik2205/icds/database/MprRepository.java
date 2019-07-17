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
    private int numberMprEntry;

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

    public int numberMprEntry(String centre,String month){
        new GetNumberOfEntries().execute(centre,month);
        return numberMprEntry;
    }

    public void getApproved(String centre){
        new GetApproved().execute(centre);
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

    public class GetNumberOfEntries extends AsyncTask<String,Void,Integer>{

        @Override
        protected Integer doInBackground(String... strings) {
            numberMprEntry = mprDao.numberOfEntriesMpr(strings[0],strings[1]);
            return null;
        }
    }

    public class GetApproved extends AsyncTask<String,Void,Void>{

        @Override
        protected Void doInBackground(String... strings) {
            mprDao.gotApproved(strings[0]);
            return null;
        }
    }

}

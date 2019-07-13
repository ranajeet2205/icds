package com.ranajeetbarik2205.icds.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ranajeetbarik2205.icds.dao.BNFDao;
import com.ranajeetbarik2205.icds.models.BNF;

import java.util.List;

public class BnfRepository {
    private BNFDao bnfDao;
    private LiveData<List<BNF>> bnfLiveDataList;

    public BnfRepository(Application application){
        bnfDao = DatabaseClient.getDatabaseClient(application).getICDSDatabase().bnfDao();
        bnfLiveDataList = bnfDao.bnfList();
    }

    public LiveData<List<BNF>> getBnfLiveDataList(){
        return bnfLiveDataList;
    }

    public void insertBnfData(BNF bnf){
        new InsertBnfDataTask(bnfDao).execute(bnf);
    }

    public class InsertBnfDataTask extends AsyncTask<BNF,Void,Void>{
        private BNFDao bnfDao;

        public InsertBnfDataTask(BNFDao bnfDao){
            this.bnfDao = bnfDao;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(BNF... bnfs) {
            bnfDao.insertBNFDao(bnfs[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
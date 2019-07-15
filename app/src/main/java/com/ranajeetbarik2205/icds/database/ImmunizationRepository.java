package com.ranajeetbarik2205.icds.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ranajeetbarik2205.icds.dao.ImmunizationDao;
import com.ranajeetbarik2205.icds.models.Immunization;

import java.util.List;

public class ImmunizationRepository {

    private ImmunizationDao immunizationDao;
    private LiveData<List<Immunization>> immunizationList;
    private int numberOfImmunEntry;

    public ImmunizationRepository(Application application){
        immunizationDao = DatabaseClient.getDatabaseClient(application).getICDSDatabase().immunizationDao();
        immunizationList = immunizationDao.immunizationList();
    }

    public LiveData<List<Immunization>> getImmunizationList(){
        return immunizationList;
    }

    public void insertImmunizationList(Immunization immunization){
        new InsertImmunizationData(immunizationDao).execute(immunization);
    }

    public int numberOfImmunEntry(String centre,String month){
        new GetNumberOfImmunEntry(immunizationDao).execute(centre,month);
        return numberOfImmunEntry;
    }

    public class InsertImmunizationData extends AsyncTask<Immunization,Void,Void>{

        private ImmunizationDao immunizationDao;

        public InsertImmunizationData(ImmunizationDao immunizationDao){
            this.immunizationDao = immunizationDao;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Immunization... immunizations) {
            immunizationDao.insertAllImmunization(immunizations[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    public class GetNumberOfImmunEntry extends AsyncTask<String,Void,Integer>{

        private ImmunizationDao immunizationDao;

        public GetNumberOfImmunEntry(ImmunizationDao immunizationDao){
            this.immunizationDao = immunizationDao;
        }
        @Override
        protected Integer doInBackground(String... strings) {
           numberOfImmunEntry =  immunizationDao.numberOfEntriesImmunization(strings[0],strings[1]);
            return null;
        }
    }
}

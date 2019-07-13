package com.ranajeetbarik2205.icds.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ranajeetbarik2205.icds.dao.WeightDao;
import com.ranajeetbarik2205.icds.models.Weight;

import java.util.List;

public class WeightRepository {
    private WeightDao weightDao;
    private LiveData<List<Weight>> weightList;

    public WeightRepository(Application application){
        weightDao = DatabaseClient.getDatabaseClient(application).getICDSDatabase().weightDao();
        weightList = weightDao.weightDataList();
    }

    public LiveData<List<Weight>> getWeightList(){
        return weightList;
    }

    public void insertWeight(Weight weight){
        new InsertWeightTask(weightDao).execute(weight);
    }

    public class InsertWeightTask extends AsyncTask<Weight,Void,Void>{

        private WeightDao weightDao;

        public InsertWeightTask(WeightDao weightDao){
            this.weightDao = weightDao;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Weight... weights) {
            weightDao.insertWeightData(weights[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}

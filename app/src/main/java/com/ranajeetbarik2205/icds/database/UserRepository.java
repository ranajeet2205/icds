package com.ranajeetbarik2205.icds.database;

import android.app.Application;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.ranajeetbarik2205.icds.dao.UserDao;
import com.ranajeetbarik2205.icds.models.User;
import com.ranajeetbarik2205.icds.util.AppConstants;
import com.ranajeetbarik2205.icds.util.SharedPrefManager;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserRepository {

    private UserDao userDao;
    private LiveData<List<User>> userLiveDataList;
    List<String> designations ;
    private String designation ;
    SharedPrefManager sharedPrefManager;

    public UserRepository(Application application){
        userDao = DatabaseClient.getDatabaseClient(application).getICDSDatabase().userDao();
        userLiveDataList = userDao.getAllUserDetails();
        sharedPrefManager = new SharedPrefManager(application);
    }

    public LiveData<List<User>> getUserLiveDataList(){
        return userLiveDataList;
    }

    public void insertUser(User user){
        new InsertUserAsyncTask(userDao).execute(user);
    }

    public String getDesignation(String email){
        new GetDesignation(userDao).execute(email);
        return designation;
    }


    public class InsertUserAsyncTask  extends AsyncTask<User,Void,Void>{
        private UserDao userdao;

        public InsertUserAsyncTask(UserDao userdao){
            this.userdao = userdao;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(User... users) {
            userdao.insertAll(users[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    public class GetDesignation extends AsyncTask<String,Void,String>{

        private UserDao userdao;


        public GetDesignation(UserDao userdao){
            this.userdao = userdao;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            designation = userdao.getDesignation(strings[0]);
           // designation = designations.get(0);
            return designation;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            sharedPrefManager.setStr(AppConstants.WHO_IS_USER,designation);

        }
    }
}

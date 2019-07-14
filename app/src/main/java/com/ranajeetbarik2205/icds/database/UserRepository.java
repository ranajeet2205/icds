package com.ranajeetbarik2205.icds.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ranajeetbarik2205.icds.dao.UserDao;
import com.ranajeetbarik2205.icds.models.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserRepository {

    private UserDao userDao;
    private LiveData<List<User>> userLiveDataList;

    public UserRepository(Application application){
        userDao = DatabaseClient.getDatabaseClient(application).getICDSDatabase().userDao();
        userLiveDataList = userDao.getAllUserDetails();
    }

    public LiveData<List<User>> getUserLiveDataList(){
        return userLiveDataList;
    }

    public void insertUser(User user){
        new InsertUserAsyncTask(userDao).execute(user);
    }

    public String getDesignation(String email){
        String designation = null;
        try {
            designation = new GetDesignation(userDao).execute(email).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        String designation;

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
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

        }
    }
}

package com.ranajeetbarik2205.icds.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ranajeetbarik2205.icds.dao.UserDao;
import com.ranajeetbarik2205.icds.models.User;

import java.util.List;

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
}

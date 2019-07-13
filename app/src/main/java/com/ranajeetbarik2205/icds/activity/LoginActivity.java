package com.ranajeetbarik2205.icds.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.ranajeetbarik2205.icds.MainActivity;
import com.ranajeetbarik2205.icds.R;
import com.ranajeetbarik2205.icds.databinding.ActivityLoginBinding;
import com.ranajeetbarik2205.icds.util.AppConstants;
import com.ranajeetbarik2205.icds.util.SharedPrefManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding activityLoginBinding;
    ArrayAdapter<String> designationSpinnerAdapter;
    private String whoIsUser,userMailId,userPassword;
    FirebaseAuth mAuth;
    SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Initialise Activity Login Binding
        activityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        //first we intialized the FirebaseAuth object
        mAuth = FirebaseAuth.getInstance();
        //Initialise new Shared Preference Object
        sharedPrefManager = new SharedPrefManager(this);

        activityLoginBinding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityLoginBinding.progress.setVisibility(View.VISIBLE);
                userMailId  = activityLoginBinding.usernameEdTxt.getText().toString().trim();
                userPassword = activityLoginBinding.passwordEdTxt.getText().toString().trim();
                if (isEmailValid(userMailId) && isPasswordValid(userPassword) && !userMailId.isEmpty() && !userPassword.isEmpty() && isNetworkConnected()){
                    sharedPrefManager.setBool(AppConstants.IS_FIRST_TIME_LOGIN,true);
                    loginWithEmail();

                }
                else if (!isNetworkConnected()){
                    Toasty.info(LoginActivity.this,"Please Connect To Internet", Toast.LENGTH_SHORT,true).show();
                    activityLoginBinding.progress.setVisibility(View.GONE);
                }
                else if (TextUtils.isEmpty(userMailId) || TextUtils.isEmpty(userPassword) ){
                    Toasty.info(LoginActivity.this,"Email Or Password Can't Be Blank", Toast.LENGTH_SHORT,true).show();
                    activityLoginBinding.progress.setVisibility(View.GONE);

                }else if (!isEmailValid(userMailId) || !isPasswordValid(userPassword)){
                    Toasty.error(LoginActivity.this,"Please Enter Valid Credaintials", Toast.LENGTH_SHORT,true).show();
                    activityLoginBinding.passwordEdTxt.setError("Must Be 8 character With one Upper,Lower,Special Character and number");
                    activityLoginBinding.progress.setVisibility(View.GONE);
                }

            }
        });

        activityLoginBinding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
                finish();
            }
        });


        designationSpinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Designation));
        designationSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        activityLoginBinding.designationSpinner.setAdapter(designationSpinnerAdapter);


    }


    public boolean isEmailValid(String email) {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if(matcher.matches())
            return true;
        else
            return false;
    }

    public boolean isPasswordValid(String password) {


        String regExpn =
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

        CharSequence inputStr = password;

        Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if(matcher.matches())
            return true;
        else
            return false;
    }

    private void loginWithEmail() {

        mAuth.signInWithEmailAndPassword(userMailId,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    //preferenceManager.setIsFirstTimeLogin(false);
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
                else {
                    Toast.makeText(LoginActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}

package com.ranajeetbarik2205.icds.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.ranajeetbarik2205.icds.MainActivity;
import com.ranajeetbarik2205.icds.R;
import com.ranajeetbarik2205.icds.databinding.ActivitySignUpBinding;
import com.ranajeetbarik2205.icds.models.User;
import com.ranajeetbarik2205.icds.util.SharedPrefManager;
import com.ranajeetbarik2205.icds.viewmodels.SignUpViewModel;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding activitySignUpBinding;
    ArrayAdapter<String> designationSpinnerAdapter;
    SharedPrefManager sharedPrefManager;
    SignUpViewModel signUpViewModel;
    private FirebaseAuth mAuth;
    User user;
    private String fullName, designation, phoneNumber, email, password, confirmPassword, aadharNumber, votorId,
            dateOfJoining, joiningDistrict, joiningProject, address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySignUpBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        sharedPrefManager = new SharedPrefManager(this);
        signUpViewModel = ViewModelProviders.of(this).get(SignUpViewModel.class);

        //first we intialized the FirebaseAuth object
        mAuth = FirebaseAuth.getInstance();

        activitySignUpBinding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activitySignUpBinding.progressbar.setVisibility(View.VISIBLE);

                fullName = activitySignUpBinding.usernameEdTxt.getText().toString().trim();
                phoneNumber = activitySignUpBinding.phoneNumberEdTxt.getText().toString().trim();
                email = activitySignUpBinding.emailEdTxt.getText().toString().trim();
                password = activitySignUpBinding.passwordEdTxt.getText().toString().trim();
                confirmPassword = activitySignUpBinding.cnfmPasswordEdTxt.getText().toString().trim();
                aadharNumber = activitySignUpBinding.aadharEdTxt.getText().toString().trim();
                votorId = activitySignUpBinding.votoridEdTxt.getText().toString().trim();
                dateOfJoining = activitySignUpBinding.dateOfJoiningEdTxt.getText().toString().trim();
                joiningDistrict = activitySignUpBinding.nameOfJoiningDistrictEdTxt.getText().toString().trim();
                joiningProject = activitySignUpBinding.nameOfJoiningProjectEdTxt.getText().toString().trim();
                address = activitySignUpBinding.addressEdTxt.getText().toString().trim();
                user = new User(fullName, designation, phoneNumber, email, aadharNumber,
                        votorId, dateOfJoining, joiningDistrict, joiningProject, address);

                if (!isPasswordValid(password) && !TextUtils.isEmpty(password)) {
                    activitySignUpBinding.passwordEdTxt.setError("Must Be 8 character With one Upper,Lower,Special Character and number");
                    activitySignUpBinding.progressbar.setVisibility(View.GONE);
                } else if (!TextUtils.equals(password, confirmPassword)) {
                    Toasty.warning(SignUpActivity.this, "Password And ConfirmPassword Must be Same", Toast.LENGTH_SHORT, true).show();
                    activitySignUpBinding.progressbar.setVisibility(View.GONE);
                } else if (!isEmailValid(email)) {
                    Toasty.warning(SignUpActivity.this, "Enter Valid Email", Toast.LENGTH_SHORT, true).show();
                    activitySignUpBinding.progressbar.setVisibility(View.GONE);
                } else if (!signUpViewModel.isValid(user)) {
                    Toasty.error(SignUpActivity.this, "Please Provide Valid Data", Toast.LENGTH_SHORT, true).show();
                    activitySignUpBinding.progressbar.setVisibility(View.GONE);
                } else {
                    signUpViewModel.insertUser(user);
                    signUpWithEmail(email, password);
                    //activitySignUpBinding.progressbar.setVisibility(View.GONE);
                }

            }
        });

        designationSpinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Designation));
        designationSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activitySignUpBinding.designationSpinner.setAdapter(designationSpinnerAdapter);

        activitySignUpBinding.designationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                designation = activitySignUpBinding.designationSpinner.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        final Calendar calendar = Calendar.getInstance();

        int dt_day = calendar.get(Calendar.DAY_OF_MONTH);
        int dt_month = calendar.get(Calendar.MONTH);
        int dt_year = calendar.get(Calendar.YEAR);

        final DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                activitySignUpBinding.dateOfJoiningEdTxt.setText(dayOfMonth + "/" + month + "/" + year);
            }
        }, dt_year, dt_month, dt_day);

        activitySignUpBinding.dateOfJoiningEdTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // Show your calender here
                    datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                    datePickerDialog.show();

                } else {
                    // Hide your calender here
                }
            }
        });
    }

    public boolean isEmailValid(String email) {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches())
            return true;
        else
            return false;
    }


    public boolean isPasswordValid(String password) {
        /**
         * Regular Expression for pasword in java
         * ^                 # start-of-string
         * (?=.*[0-9])       # a digit must occur at least once
         * (?=.*[a-z])       # a lower case letter must occur at least once
         * (?=.*[A-Z])       # an upper case letter must occur at least once
         * (?=.*[@#$%^&+=])  # a special character must occur at least once
         * (?=\S+$)          # no whitespace allowed in the entire string
         * .{8,}             # anything, at least eight places though
         * $                 # end-of-string
         */

        String regExpn =
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

        CharSequence inputStr = password;

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches())
            return true;
        else
            return false;
    }

    public void signUpWithEmail(String userID, String passwordUser) {

        mAuth.createUserWithEmailAndPassword(userID, passwordUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                    Toasty.success(SignUpActivity.this, "Sign Up Successful", Toast.LENGTH_SHORT, true).show();
                    finish();
                }
                else {
                    Toasty.info(SignUpActivity.this, "Please Make Sure You Entered Valid Details", Toast.LENGTH_SHORT, true).show();
                    task.addOnFailureListener(SignUpActivity.this, new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            e.printStackTrace();
                            Toast.makeText(SignUpActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }

        });
    }
}

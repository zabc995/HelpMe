package com.gethelp.huyngh.helpmedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.firebase.database.DatabaseReference;

public class RegistrationActivity extends AppCompatActivity {

    EditText edtFirstName, edtLastName, edtAddress, edtEmail;
    EditText edtPhoneNumber, edtPassword;
    RadioButton rdbGender;
    Button btnSignUp;

    DatabaseReference databaseAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }
}

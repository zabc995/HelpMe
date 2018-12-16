package com.gethelp.huyngh.helpmedemo;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.gethelp.huyngh.model.Account;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class RegistrationActivity extends AppCompatActivity {

    EditText edtFirstName, edtLastName, edtAddress, edtEmail;
    EditText edtPhoneNumber, edtPassword, edtComfirmPassword;
    RadioButton rdbGenderMale, rdbGenderFemale;
    Button btnSignUp;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseAccount;

    List<Account> accountList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Registration");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_registration);

        //Thiết lâp firebase
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseAccount = firebaseDatabase.getReference("Account");

        //Tạo một nút account mới, trả về giá trị khóa duy nhất
        String accountId = databaseAccount.push().getKey();

        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        rdbGenderMale = (RadioButton) findViewById(R.id.rdbMale);
        rdbGenderFemale = (RadioButton) findViewById(R.id.rdbFemale);
        edtFirstName = (EditText) findViewById(R.id.edtFirstName);
        edtLastName = (EditText) findViewById(R.id.edtLastName);
        edtAddress = (EditText) findViewById(R.id.edtAddress);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPhoneNumber = (EditText) findViewById(R.id.edtPhoneNumber);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtComfirmPassword = (EditText) findViewById(R.id.edtConfirmPassword);
    }
}

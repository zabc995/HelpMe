package com.gethelp.huyngh.helpmedemo;

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
    EditText edtPhoneNumber, edtPassword;
    RadioButton rdbGender;
    Button btnSignUp;

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseAccount;

    List<Account> accountList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        databaseAccount = FirebaseDatabase.getInstance().getReference("account");

        //Tạo một nút account mới, trả về giá trị khóa duy nhất
        //String accountId = databaseAccount.push().getKey();

        //btnSignUp = (Button) findViewById();
        //rdbGender = (RadioButton) findViewById();
        //edtFirstName = (EditText) findViewById(R.id.edtFirstName);
        //edtLastName = (EditText) findViewById();
        //edtAddress = (EditText) findViewById();
        //edtEmail = (EditText) findViewById();
        //edtPhoneNumber = (EditText) findViewById();
        //edtPassword = (EditText) findViewById();
    }
}

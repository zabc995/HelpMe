package com.gethelp.huyngh.helpmedemo;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.gethelp.huyngh.model.Account;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class RegistrationActivity extends AppCompatActivity {

    EditText edtFirstName, edtLastName, edtAddress, edtEmail;
    EditText edtPhoneNumber, edtPassword, edtComfirmPassword;
    RadioButton rdbGenderMale, rdbGenderFemale;
    Button btnSignUp;

    LinearLayout regisLayout;

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

        //Tạo một nút account mới, trả về giá trị khóa duy nhất
        String accountId = databaseAccount.push().getKey();



        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void handleSignUp(View view) {
        //Thiết lập dialog
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("S I G N U P");
        dialog.setMessage("Please use email to register");

        //Thiết lập các view control
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
        regisLayout = (LinearLayout) findViewById(R.id.regisLayout);

        //Lấy Gender
        final Boolean Gender;
        if(rdbGenderFemale.isChecked() == true & rdbGenderMale.isChecked() == false)
            Gender = false;
        else
            Gender = true;


        //Thiết lập Button
        dialog.setPositiveButton("+", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();

                //Thiết lâp firebase
                firebaseAuth = FirebaseAuth.getInstance();
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseAccount = firebaseDatabase.getReference("Account");

                // Kiểm tra ràng buộc
                if(TextUtils.isEmpty(edtEmail.getText().toString())){

                    Snackbar.make(regisLayout, "Please enter email address", Snackbar.LENGTH_SHORT)
                            .show();
                    return;
                }
                if(TextUtils.isEmpty(edtPhoneNumber.getText().toString())){

                    Snackbar.make(regisLayout, "Please enter phone number", Snackbar.LENGTH_SHORT)
                            .show();
                    return;
                }
                if(TextUtils.isEmpty(edtPassword.getText().toString())){

                    Snackbar.make(regisLayout, "Please enter password", Snackbar.LENGTH_SHORT)
                            .show();
                    return;
                }
                if(edtPassword.getText().toString().length() < 6){

                    Snackbar.make(regisLayout, "Password too short !!!", Snackbar.LENGTH_SHORT)
                            .show();
                    return;
                }

                //Đăng ký tài khoản mới
                firebaseAuth.createUserWithEmailAndPassword(edtEmail.getText().toString(),edtPassword.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                //Tạo dữ liệu để lưu vào db
                                Account account = new Account();
                                account.setMail(edtEmail.getText().toString());
                                account.setPassword(edtPassword.getText().toString());
                                account.setFirstName(edtFirstName.getText().toString());
                                account.setLastName(edtLastName.getText().toString());
                                account.setAddress(edtAddress.getText().toString());
                                account.setPhoneNumber(edtPhoneNumber.getText().toString());
                                account.setGender(Gender);

                                //Dùng email làm key
                                databaseAccount.child(firebaseAuth.getCurrentUser().getUid())
                                        .setValue(account)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Snackbar.make(regisLayout,"Register Succesfully !!!",Snackbar.LENGTH_SHORT)
                                                        .show();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Snackbar.make(regisLayout,"Failed !!!"+ e.getMessage(),Snackbar.LENGTH_SHORT)
                                                        .show();
                                            }
                                        });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Snackbar.make(regisLayout, "Failed !!!"+e.getMessage(), Snackbar.LENGTH_SHORT)
                                        .show();
                            }
                        });
            }
        });
    }
}

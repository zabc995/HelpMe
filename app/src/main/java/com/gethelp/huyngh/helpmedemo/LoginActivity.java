package com.gethelp.huyngh.helpmedemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends Activity {

    EditText edtUserName, edtPassword;
    TextView txvRegistration;
    Button btnSignIn;
    LinearLayout loginLayout;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        //Thiết lập Layout
        loginLayout = findViewById(R.id.loginLayout);
        txvRegistration = findViewById(R.id.txvRegistration);
        //Thiết lập Button Sign
        btnSignIn = findViewById(R.id.btnSignIn);
        //Thiết lập sự kiện
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSignIn();
            }
        });
        //
        txvRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }

    public void handleSignIn() {
        //Thiết lập các view control
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);

        //Thiết lâp firebase
        firebaseAuth = FirebaseAuth.getInstance();

        // Kiểm tra ràng buộc
        if(TextUtils.isEmpty(edtUserName.getText().toString())){

            Snackbar.make(loginLayout, "Please enter email address", Snackbar.LENGTH_SHORT)
                    .show();
            return;
        }
        if(TextUtils.isEmpty(edtPassword.getText().toString())){

            Snackbar.make(loginLayout, "Please enter password", Snackbar.LENGTH_SHORT)
                    .show();
            return;
        }
        if(edtPassword.getText().toString().length() < 6){

            Snackbar.make(loginLayout, "Password too short !!!", Snackbar.LENGTH_SHORT)
                    .show();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(edtUserName.getText().toString(),edtPassword.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(loginLayout,"Failed !!!"+ e.getMessage(),Snackbar.LENGTH_SHORT)
                                .show();
                    }
                });
    }
}

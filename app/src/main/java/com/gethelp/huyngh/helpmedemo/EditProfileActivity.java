package com.gethelp.huyngh.helpmedemo;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gethelp.huyngh.model.Account;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfileActivity extends AppCompatActivity {

    private static final String TAG = "EDIT";
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener authStateListener;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseAccount;
    String userUid;

    Button btnSave;

    EditText edtFirstName, edtLastName, edtAddress, edtPassword, edtPhoneNumber, edtNewPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Edit Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setContentView(R.layout.activity_edit_profile);

        //Thiết lập controls
        edtPassword = findViewById(R.id.edtPassword);
        edtFirstName = findViewById(R.id.edtFirstName);
        edtLastName = findViewById(R.id.edtLastName);
        edtAddress = findViewById(R.id.edtAddress);
        edtPassword = findViewById(R.id.edtPassword);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        btnSave = findViewById(R.id.btnSave);
        //
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseAccount = firebaseDatabase.getReference("Account");
        FirebaseUser user = firebaseAuth.getCurrentUser();
        userUid = user.getUid();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if( user != null){
                    //User đã đăng nhập
                    Log.d(TAG,"Singed In:"+ user.getUid());
                    toastMessage("Success with"+ user.getEmail());
                }
                else {

                }
            }
        };
        databaseAccount.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseAccount.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        checkData(dataSnapshot);
                        //saveData();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void checkData(DataSnapshot dataSnapshot) {
        Account account = new Account();
        account.setFirstName(dataSnapshot.child(userUid).getValue(Account.class).getFirstName());
        account.setLastName(dataSnapshot.child(userUid).getValue(Account.class).getLastName());
        account.setAddress(dataSnapshot.child(userUid).getValue(Account.class).getAddress());
        account.setPhoneNumber(dataSnapshot.child(userUid).getValue(Account.class).getPhoneNumber());
        account.setPassword(dataSnapshot.child(userUid).getValue(Account.class).getPassword());

        /*
        if(account.getFirstName()!= edtFirstName.getText().toString() && account.getLastName() != edtLastName.getText().toString()
                && account.getAddress() != edtAddress.getText().toString() && account.getPhoneNumber() != edtPhoneNumber.getText().toString()
                && ){

        }*/
    }

    private void toastMessage(String s) {
        Toast.makeText(this,s, Toast.LENGTH_SHORT).show();
    }

    private void showData(DataSnapshot dataSnapshot) {
        Account account = new Account();
        account.setFirstName(dataSnapshot.child(userUid).getValue(Account.class).getFirstName());
        account.setLastName(dataSnapshot.child(userUid).getValue(Account.class).getLastName());
        account.setAddress(dataSnapshot.child(userUid).getValue(Account.class).getAddress());
        account.setPhoneNumber(dataSnapshot.child(userUid).getValue(Account.class).getPhoneNumber());
        account.setPassword(dataSnapshot.child(userUid).getValue(Account.class).getPassword());

        if(account != null) {
            String FirstName = account.getFirstName();
            edtFirstName.setText(FirstName);
            edtLastName.setText(account.getLastName());
            edtAddress.setText(account.getAddress());
            edtPhoneNumber.setText(account.getPhoneNumber());
            edtPassword.setText(account.getPassword());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}

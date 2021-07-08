package com.example.firebaseexample;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);
    }



    private Boolean validateUser(){
        String val = username.getText().toString();

        if(val.isEmpty())
        {
            username.setError("Field cannot be empty");
            return false;
        }
        else
        {
            username.setError(null);
            return true;
        }

    }

    private Boolean validatePassword(){
        String val = password.getText().toString();

        if(val.isEmpty())
        {
            password.setError("Field cannot be empty");
            return false;
        }
        else
        {
            password.setError(null);
            return true;
        }

    }

    public void loginUser(View view)
    {
        if(!validateUser() | !validatePassword())
        {
            return;
        }
        else
        {
            isUser();
        }
    }

    private void isUser() {
        String userEnteredName = username.getText().toString();
        String userEnteredPass = password.getText().toString();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        Query checkUser = reference.orderByChild("username").equalTo(userEnteredName);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists())
                {
                    String passwordFromDB = snapshot.child(userEnteredName).child("password").getValue(String.class);

                    if(passwordFromDB.equals(userEnteredPass))
                    {
                        String namefromDB = snapshot.child(userEnteredName).child("name").getValue(String.class);
                        String usernameformDB = snapshot.child(userEnteredName).child("username").getValue(String.class);
                        String phoneNoFromDB = snapshot.child(userEnteredName).child("phonenumber").getValue(String.class);
                        String emalFromDB = snapshot.child(userEnteredName).child("email").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(), UserProfile.class);

                        intent.putExtra("name", namefromDB);
                        intent.putExtra("username", usernameformDB);
                        intent.putExtra("email", emalFromDB);
                        intent.putExtra("phoneNo", phoneNoFromDB);
                        intent.putExtra("password", passwordFromDB);

                        startActivity(intent);

                    }

                    else
                    {
                        password.setError("Wrong Password");
                        password.requestFocus();
                    }

                }

                else
                {
                    username.setError("No such user Exists");
                    username.requestFocus();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
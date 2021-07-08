package com.example.firebaseexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    EditText fullname, username, email, phone, password;
    Button signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         fullname = findViewById(R.id.fullName);
         username = findViewById(R.id.userName);
         email = findViewById(R.id.userName);
         phone = findViewById(R.id.phoneNumber);
         password = findViewById(R.id.password);
         signup = findViewById(R.id.btn_signup);




        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validateName() || !validateEmail() || !validatePassword() || !validatePhone() || !validateuserName())
                {
                    return;
                }

               rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                

                String name = fullname.getText().toString();
                String user = username.getText().toString();
                String usermail = email.getText().toString();
                String phoneno = phone.getText().toString();
                String pass = password.getText().toString();

                UserHelperClass userHelperClass = new UserHelperClass(name, user, usermail, phoneno, pass);

                reference.child(user).setValue(userHelperClass);

            }
        });
    }


    private Boolean validateName(){
        String val = fullname.getText().toString();

        if(val.isEmpty())
        {
            fullname.setError("Field cannot be empty");
            return false;
        }
        else
        {
            fullname.setError(null);
            return true;
        }

    }

    private Boolean validateuserName(){
        String val = username.getText().toString();

        if(val.isEmpty())
        {
            fullname.setError("Field cannot be empty");
            return false;
        }
        else
        {
            fullname.setError(null);
            return true;
        }

    }

    private Boolean validateEmail(){
        String val = email.getText().toString();

        if(val.isEmpty())
        {
            fullname.setError("Field cannot be empty");
            return false;
        }
        else
        {
            fullname.setError(null);
            return true;
        }

    }

    private Boolean validatePhone(){
        String val = phone.getText().toString();

        if(val.isEmpty())
        {
            fullname.setError("Field cannot be empty");
            return false;
        }
        else
        {
            fullname.setError(null);
            return true;
        }

    }

    private Boolean validatePassword(){
        String val = password.getText().toString();

        if(val.isEmpty())
        {
            fullname.setError("Field cannot be empty");
            return false;
        }
        else
        {
            fullname.setError(null);
            return true;
        }

    }

}
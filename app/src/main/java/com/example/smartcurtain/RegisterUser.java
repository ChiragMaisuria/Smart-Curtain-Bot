package com.example.smartcurtain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterUser extends AppCompatActivity {

    Button signupBtn;
    EditText registerEmail;
    EditText registerPassword;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        signupBtn = findViewById(R.id.registerButton);
        registerEmail = findViewById(R.id.registerEmail);
        registerPassword = findViewById(R.id.registerPassword);
        auth = FirebaseAuth.getInstance();

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String regmail = registerEmail.getText().toString();
                String regpass = registerPassword.getText().toString();

                if(regmail.length() != 0 && regpass.length() != 0){
                    auth.createUserWithEmailAndPassword(regmail, regpass).addOnCompleteListener(RegisterUser.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            startActivity(new Intent(RegisterUser.this, mainpage1.class));
                            finish();
                        }
                    });

                }
            }
        });

    }
}
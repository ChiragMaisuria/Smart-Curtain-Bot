package com.example.smartcurtain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    ImageButton loginBtn, signUpBtn;
    EditText emailText;
    EditText passText;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginBtn = findViewById(R.id.loginBtn);
        signUpBtn = findViewById(R.id.signupBtn);

        emailText = findViewById(R.id.mailidInput);
        passText = findViewById(R.id.passwordInput);

        auth = FirebaseAuth.getInstance();

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterUser.class));
                finish();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String mailId = emailText.getText().toString();
                String password = passText.getText().toString();
                if(mailId.length() > 0 && password.length() > 0){
                    auth.signInWithEmailAndPassword(mailId, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            startActivity(new Intent(MainActivity.this, mainpage1.class));
                        }
                    });
                }
            }
        });



        /*if(emailText.length() > 0 && passText.length() > 0){

        }*/

    }
}
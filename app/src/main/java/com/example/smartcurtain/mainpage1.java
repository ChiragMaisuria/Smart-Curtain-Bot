package com.example.smartcurtain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//import com.google.firebase.database.DatabaseReference;

public class mainpage1 extends AppCompatActivity {

    ImageButton imageBtnOpen, imageBtnClose, imageBtnPause;
    TextView displayTemp, displayLight;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage1);

        imageBtnClose = findViewById(R.id.imageButtonClose);
        imageBtnOpen = findViewById(R.id.imageButtonOpen);
        imageBtnPause = findViewById(R.id.imageButtonPause);
        displayTemp = findViewById(R.id.textTemperature);
        displayLight = findViewById(R.id.textLight);

        DatabaseReference dbreference = FirebaseDatabase.getInstance().getReference();
        dbreference.child("Room").child("Temperature").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    String temperature = snapshot.getValue().toString();
//                    statusDisplay.setText(value_status.substring(8, value_status.indexOf("}")));
                    displayTemp.setText(temperature);
                }catch(Exception e){
                    displayTemp.setText("null");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        dbreference.child("Room").child("Sunlight").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    String light = snapshot.getValue().toString();
                    displayLight.setText(light);
                }catch(Exception e){
                    displayLight.setText("null");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        imageBtnClose.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dbreference.child("Curtain").child("Status").setValue("close");
            }
        });
        imageBtnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbreference.child("Curtain").child("Status").setValue("open");
            }
        });
        imageBtnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbreference.child("Curtain").child("Status").setValue("OFF");
            }
        });

//        addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String value_status = snapshot.getValue().toString();
//                statusDisplay.setText(value_status);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }
}
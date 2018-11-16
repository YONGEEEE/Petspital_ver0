package com.camping.seoul.seoulcamp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Pet_information extends AppCompatActivity {

    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinformation);
        button = findViewById(R.id.btn_revise);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Pet_information.this, Revise_pet_information.class);
                startActivity(i);
            }
        });

    }
}

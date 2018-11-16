package com.camping.seoul.seoulcamp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Sign_in_activity extends AppCompatActivity {
    Button button1,button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button1 = findViewById(R.id.btn_sign_in);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Sign_in_activity.this, Main_activity.class);
                startActivity(i);
            }
        });
        button2 = findViewById(R.id.btn_sign_up);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Sign_in_activity.this, Sign_up_activity.class);
                startActivity(i);
            }
        });
    }
}

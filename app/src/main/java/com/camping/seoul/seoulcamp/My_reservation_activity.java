package com.camping.seoul.seoulcamp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;

public class My_reservation_activity extends AppCompatActivity {
    ListView listView;
    Button showAllButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myreservation);

        listView = findViewById(R.id.listView);
        showAllButton = findViewById(R.id.btnShowAll);
    }
}

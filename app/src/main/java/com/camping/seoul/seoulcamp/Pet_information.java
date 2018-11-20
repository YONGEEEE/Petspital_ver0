package com.camping.seoul.seoulcamp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import connectDB.NetworkTask_GetPet;
import object.NowUser;
import object.PetData;

public class Pet_information extends AppCompatActivity {

    Button btn_check, btn_revise;
    TextView txt_name, txt_birth, txt_weight, txt_kind, txt_age, txt_sex, txt_inform;
    ImageView imageView;
    PetData index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinformation);
        btn_revise = findViewById(R.id.btn_revise);
        btn_check = findViewById(R.id.btn_check);
        Intent i = getIntent();
        index = (PetData) i.getSerializableExtra("index");//parsing test

        Log.d("aaaaa", index.toString());
        txt_name = findViewById(R.id.txt_name);
        txt_birth = findViewById(R.id.txt_birth);
        txt_weight = findViewById(R.id.txt_weight);
        txt_kind = findViewById(R.id.txt_kind);
        txt_age = findViewById(R.id.txt_age);
        txt_sex = findViewById(R.id.txt_sex);
        txt_inform = findViewById(R.id.txt_inform);
        imageView = findViewById(R.id.imageView);


        txt_name.setText(index.getName());
        txt_birth.setText(index.getBirth());
        txt_weight.setText(Float.toString(index.getWeight()));
        txt_kind.setText(index.getKind());
        txt_age.setText(Integer.toString(index.getAge()));
        if (index.getSex() == 1) {
            txt_sex.setText("수컷");
        } else {
            txt_sex.setText("암컷");
        }
        txt_inform.setText(index.getInform());
        if (index.getFlag() == 1) {
            imageView.setImageResource(R.drawable.dog);
        } else {
            imageView.setImageResource(R.drawable.cat);
        }
        btn_revise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Pet_information.this, Revise_pet_information.class);
                i.putExtra("index", (PetData) index);
                startActivityForResult(i, 1);
            }
        });
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            setResult(RESULT_OK);
            finish();
        }
        if (resultCode == RESULT_FIRST_USER) {
            try {
                PetData tmp = new NetworkTask_GetPet().execute(new PetData(NowUser.id, index.getName(), index.getBirth(), index.getKind(), index.getFlag(), index.getSex())).get();
                txt_age.setText(Integer.toString(tmp.getAge()));
                txt_weight.setText(Float.toString(tmp.getWeight()));
                txt_inform.setText(tmp.getInform());
                index.setInform(tmp.getInform());
                index.setWeight(tmp.getWeight());
                index.setAge(tmp.getAge());
                setResult(RESULT_OK);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}

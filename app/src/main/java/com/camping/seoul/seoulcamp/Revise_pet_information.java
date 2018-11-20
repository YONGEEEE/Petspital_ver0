package com.camping.seoul.seoulcamp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import connectDB.NetworkTask_DeletePet;
import connectDB.NetworkTask_UpdatePet;
import object.NowUser;
import object.PetData;

public class Revise_pet_information extends AppCompatActivity {

    TextView txt_name;
    EditText edit_age, edit_weight, edit_inform;
    ImageView imageView;
    Button btn_finish, btn_cancel, btn_delete;

    PetData index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revise_information);

        txt_name = findViewById(R.id.txt_name);
        edit_age = findViewById(R.id.edit_age);
        edit_weight = findViewById(R.id.edit_weight);
        edit_inform = findViewById(R.id.edit_inform);

        btn_finish = findViewById(R.id.btn_finish);
        btn_cancel = findViewById(R.id.btn_cancel);
        btn_delete = findViewById(R.id.btn_delete);

        imageView = findViewById(R.id.imageView);

        Intent i = getIntent();
        index = (PetData) i.getSerializableExtra("index");//parsing test

        Log.d("Aaaaaa", index.toString());

        txt_name.setText(index.getName());
        edit_age.setText(Integer.toString(index.getAge()));
        edit_weight.setText(Float.toString(index.getWeight()));
        edit_inform.setText(index.getInform());
        if (index.getFlag() == 1) {
            imageView.setImageResource(R.drawable.dog);
        } else {
            imageView.setImageResource(R.drawable.cat);
        }

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = index.getName();
                int age = Integer.parseInt(edit_age.getText().toString());
                float weight = Float.parseFloat(edit_weight.getText().toString());
                String inform = edit_inform.getText().toString();
                if (edit_age.getText().length() == 0) {

                } else if (edit_weight.getText().length() == 0) {

                } else {
                    try {
                        Integer result = new NetworkTask_UpdatePet().execute(new PetData(NowUser.id, name, age, weight, inform)).get();

                        if (result == 1) {
                            Toast.makeText(Revise_pet_information.this, "수정완료", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(Revise_pet_information.this, "수정실패", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Integer result = new NetworkTask_DeletePet().execute(index).get();
                    if (result == 1) {
                        Toast.makeText(Revise_pet_information.this, "삭제완료", Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK);
                        finish();
                    }
                    else{
                        Toast.makeText(Revise_pet_information.this, "삭제실패", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

}

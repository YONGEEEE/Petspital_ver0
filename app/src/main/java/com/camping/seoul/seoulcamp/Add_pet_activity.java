package com.camping.seoul.seoulcamp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import connectDB.NetworkTask_AddPet;
import connectDB.NetworkTask_signUp;
import object.Member;
import object.NowUser;
import object.PetData;

public class Add_pet_activity extends AppCompatActivity {

    EditText edit_name , edit_age , edit_weight , edit_birth , edit_inform , edit_kind;
//    CheckBox check_dog , check_cat , check_male , check_female;
    RadioButton rbtn_dog,rbtn_cat,rbtn_male,rbtn_female;

    Button btn_create, btn_cancel;

    int flag , sex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_add_pet);

        edit_name = findViewById(R.id.edit_name);
        edit_age = findViewById(R.id.edit_age);
        edit_weight = findViewById(R.id.edit_weight);
        edit_birth = findViewById(R.id.edit_birth);
        edit_inform = findViewById(R.id.edit_inform);
        edit_kind = findViewById(R.id.edit_kind);

//        check_dog = findViewById(R.id.check_dog);
//        check_cat = findViewById(R.id.check_cat);
//
//        check_male = findViewById(R.id.check_male);
//        check_female = findViewById(R.id.check_female);

        rbtn_dog = findViewById(R.id.rbtn_dog);
        rbtn_cat = findViewById(R.id.rbtn_cat);
        rbtn_male = findViewById(R.id.rbtn_male);
        rbtn_female = findViewById(R.id.rbtn_female);


        btn_create = findViewById(R.id.btn_create);
        btn_cancel = findViewById(R.id.btn_cancel);


        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edit_name.getText().toString();
                int age = Integer.parseInt(edit_age.getText().toString());
                float weight = Float.parseFloat(edit_weight.getText().toString());
                String birth = edit_birth.getText().toString();
                String inform = edit_inform.getText().toString();
                String kind = edit_kind.getText().toString();

//                if(check_dog.isChecked()&&!check_cat.isChecked())
//                {
//                    flag = 1;
//                }
//                else if(check_cat.isChecked()&&!check_dog.isChecked())
//                {
//                    flag = 0;
//                }
//                else{
//                    Toast.makeText(Add_pet_activity.this, "개냐 고양이냐", Toast.LENGTH_SHORT).show();
//                }
//
//                if(check_male.isChecked()&&!check_female.isChecked())
//                {
//                    sex = 1;
//                }
//                else if(check_female.isChecked()&&!check_male.isChecked())
//                {
//                    sex = 0;
//                }
//                else{
//                    Toast.makeText(Add_pet_activity.this, "남자냐 여자냐", Toast.LENGTH_SHORT).show();
//                }

                if (name.length() == 0) {
                    Toast.makeText(Add_pet_activity.this, "이름을 입력하지 않았습니다.", Toast.LENGTH_SHORT).show();
                } else if (age== 0) {
                    Toast.makeText(Add_pet_activity.this, "나이를 입력하시지 않으셨습니다.", Toast.LENGTH_SHORT).show();
                }else if(weight==0)
                {
                    Toast.makeText(Add_pet_activity.this, "몸무게을 입력하시지 않으셨습니다.", Toast.LENGTH_SHORT).show();
                }else if(birth.length() ==0)
                {
                    Toast.makeText(Add_pet_activity.this, "생일을 입력하시지 않으셨습니다.", Toast.LENGTH_SHORT).show();
                }
                else if(kind.length() ==0)
                {
                    Toast.makeText(Add_pet_activity.this, "종을 입력하시지 않으셨습니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        Check_flag_sex();
                        Integer result = new NetworkTask_AddPet().execute(new PetData(NowUser.id, name, age, weight, birth, inform, kind, flag, sex)).get();
                        if(result==1)
                        {
                            Toast.makeText(Add_pet_activity.this, "등록", Toast.LENGTH_SHORT).show();
                            setResult(RESULT_OK);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(Add_pet_activity.this, "등록실패", Toast.LENGTH_SHORT).show();
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
                setResult(RESULT_OK);
                finish();
            }
        });

    }

    public void Check_flag_sex()
    {
        if(rbtn_dog.isChecked())
        {
            this.flag = 1;
        }
        if(rbtn_cat.isChecked())
        {
            this.flag = 0;
        }
        if(rbtn_male.isChecked())
        {
            this.sex = 1;
        }
        if(rbtn_female.isChecked())
        {
            this.sex = 0;
        }
        Log.d("flag,sex",Integer.toString(flag)+"\t"+Integer.toString(sex));
    }
}

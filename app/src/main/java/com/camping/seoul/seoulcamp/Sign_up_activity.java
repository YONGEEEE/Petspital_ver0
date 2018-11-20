package com.camping.seoul.seoulcamp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import connectDB.NetworkTask_signUp;
import object.Member;

public class Sign_up_activity extends AppCompatActivity {
    Button btn_create, btn_cancel;
    EditText edit_id, edit_name, edit_nickname, edit_password, edit_tel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btn_create = findViewById(R.id.btn_create);
        btn_cancel = findViewById(R.id.btn_cancel);

        edit_id = findViewById(R.id.edit_id);
        edit_name = findViewById(R.id.edit_name);
        edit_nickname = findViewById(R.id.edit_nickname);
        edit_password = findViewById(R.id.edit_password);
        edit_tel = findViewById(R.id.edit_tel);

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edit_id.getText().toString();
                String password = edit_password.getText().toString();
                String name = edit_name.getText().toString();
                String nickname = edit_nickname.getText().toString();
                String tel = edit_tel.getText().toString();
                if (id.length() == 0) {
                    Toast.makeText(Sign_up_activity.this, "아이디를 입력하지 않았습니다.", Toast.LENGTH_SHORT).show();
                } else if (password.length() == 0) {
                    Toast.makeText(Sign_up_activity.this, "비밀번호를 입력하시지 않으셨습니다.", Toast.LENGTH_SHORT).show();
                } else if (nickname.length() == 0) {
                    Toast.makeText(Sign_up_activity.this, "닉네임을 입력하시지 않으셨습니다.", Toast.LENGTH_SHORT).show();
                } else if (name.length() == 0) {
                    Toast.makeText(Sign_up_activity.this, "이름을 입력하시지 않으셨습니다.", Toast.LENGTH_SHORT).show();
                } else if (tel.length() == 0) {
                    Toast.makeText(Sign_up_activity.this, "전화번호를 입력하시지 않으셨습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        Integer result = new NetworkTask_signUp().execute(new Member(name, id, password, nickname, tel)).get();
                        Log.d("Member : ", name + "\t" + id + "\t" + password + "\t" + nickname + "\t" + tel);
                        Log.d("result : ", Integer.toString(result));
                        if (result == 1) {
                            Toast.makeText(Sign_up_activity.this, "가입완료", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(Sign_up_activity.this, "가입실패", Toast.LENGTH_SHORT).show();
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
    }
}
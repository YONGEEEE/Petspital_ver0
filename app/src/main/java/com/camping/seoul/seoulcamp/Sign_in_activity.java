package com.camping.seoul.seoulcamp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import connectDB.NetworkTask_signIn;
import object.Member;
import object.NowUser;

public class Sign_in_activity extends AppCompatActivity {
    Button button1, button2;
    EditText edit_id, edit_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent i = new Intent(this, Intro_Activity.class);
        startActivity(i);

        edit_id = findViewById(R.id.edit_id);
        edit_password = findViewById(R.id.edit_password);
        button1 = findViewById(R.id.btn_sign_in);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edit_id.getText().toString();
                String pw = edit_password.getText().toString();
                if (edit_id.getText().toString().length() == 0) {
                    Toast.makeText(Sign_in_activity.this, "아이디를 입력하지 않았습니다.", Toast.LENGTH_SHORT).show();
                } else if (edit_password.getText().toString().length() == 0) {
                    Toast.makeText(Sign_in_activity.this, "비밀번호를 입력하시지 않으셨습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        Member tmp = new NetworkTask_signIn().execute(new Member(id, pw)).get();
                        if (tmp != null) {
                            if (tmp.getFlag() == 1) {
                                Intent i = new Intent(Sign_in_activity.this, Petspital_reservation_activity.class);
                                i.putExtra("name", tmp.getName());
                                startActivity(i);
                            } else if (tmp.getFlag() != 1 && !tmp.getId().equals(null) && !tmp.getName().equals(null) && !tmp.getNickname().equals(null) && !tmp.getPassword().equals(null) && !tmp.getTel().equals(null)) {
                                Intent i = new Intent(Sign_in_activity.this, Main_activity.class);
                                //회원정보 객체 같이 보내기
                                NowUser.id = tmp.getId();
                                NowUser.name = tmp.getName();
                                NowUser.nickname = tmp.getNickname();
                                NowUser.password = tmp.getPassword();
                                NowUser.tel = tmp.getTel();
//                            i.putExtra("index",  tmp);//parsing test
                                startActivity(i);
                            }
                        }
                        else
                        {
                            Toast.makeText(Sign_in_activity.this, "정보가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                        }
                        }catch(Exception e){
                        e.printStackTrace();
                    }
                }
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

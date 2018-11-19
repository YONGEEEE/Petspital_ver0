package com.camping.seoul.seoulcamp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import connectDB.NetworkTask_signIn;
import object.Member;
import object.NowUser;

public class Sign_in_activity extends AppCompatActivity {
    Button button1,button2;
    EditText edit_id,edit_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
                }else {
                    try {
                        Member tmp = new NetworkTask_signIn().execute(new Member(id,pw)).get();
                        if(!tmp.getId().equals(null)&&!tmp.getName().equals(null)&&!tmp.getNickname().equals(null)&&!tmp.getPassword().equals(null)&&!tmp.getTel().equals(null))
                        {
                            Intent i = new Intent(Sign_in_activity.this, Main_activity.class);
                            //회원정보 객체 같이 보내기
                            NowUser.id=tmp.getId();
                            NowUser.name=tmp.getName();
                            NowUser.nickname=tmp.getNickname();
                            NowUser.password=tmp.getPassword();
                            NowUser.tel=tmp.getTel();
//                            i.putExtra("index",  tmp);//parsing test
                            startActivity(i);
                        }
                    } catch (Exception e) {
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

        //        btnWriteComment.setOnClickListener(new View.OnClickListener() { // 작성하기 버튼 클릭시 이벤트
//            @Override
//            public void onClick(View view) {
//                String id;
//                String text;
//                float star;
//                String type;
//                String pw;
//                if (editComment.getText().toString().length() == 0) {
//                    Toast.makeText(Camp_detail.this, "내용을 입력하지 않았습니다.", Toast.LENGTH_SHORT).show();
//                } else if (password.getText().toString().length() == 0) {
//                    Toast.makeText(Camp_detail.this, "비밀번호를 입력하시지 않으셨습니다.", Toast.LENGTH_SHORT).show();
//                } else if (password.getText().toString().length() < 4) {
//                    Toast.makeText(Camp_detail.this, "4자리 비밀번호를 입력하지 않았습니다.", Toast.LENGTH_SHORT).show();
//                } else {
//                    WifiManager mwifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//                    WifiInfo info = mwifi.getConnectionInfo();
//                    id = info.getMacAddress(); // 핸드폰 맥주소 가져오기
//                    text = editComment.getText().toString();
//                    star = ratingBar.getRating();
//                    type = index.getId();
//                    pw = password.getText().toString();
//                    password.setText(null);
//                    editComment.setText(null);
//                    try {
//                        new NetworkTask_AddList().execute(new CommentItem(type, id, text, star, pw));
//                        setList();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
    }
}

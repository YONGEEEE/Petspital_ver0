package com.camping.seoul.seoulcamp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import fragment.NearByFragment;
import fragment.NearByFragment2;
import weather.WeatherInfo;
import weather.WeatherInfo2;

import java.util.Calendar;

import java.util.GregorianCalendar;


public class Reservation_activity extends AppCompatActivity {
    FrameLayout frameLayout;
    Button button_select_day;
    LinearLayout layout;

    int mYear, mMonth, mDay, mHour, mMinute;

    TextView txt_select_day;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_reservation);

        //현재 날짜와 시간을 가져오기위한 Calendar 인스턴스 선언

        Calendar cal = new GregorianCalendar();

        mYear = cal.get(Calendar.YEAR);

        mMonth = cal.get(Calendar.MONTH);

        mDay = cal.get(Calendar.DAY_OF_MONTH);

        mHour = cal.get(Calendar.HOUR_OF_DAY);


        frameLayout = (FrameLayout) findViewById(R.id.framelayout);

        replace_fragment(new NearByFragment());

        layout = findViewById(R.id.layout_search);
        txt_select_day = findViewById(R.id.txt_select_day);
        button_select_day = findViewById(R.id.btn_select_day);
        button_select_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Reservation_activity.this, mDateSetListener, mYear,

                        mMonth, mDay).show();
            }
            //날짜 대화상자 리스너 부분

            DatePickerDialog.OnDateSetListener mDateSetListener =

                    new DatePickerDialog.OnDateSetListener() {



                        @Override

                        public void onDateSet(DatePicker view, int year, int monthOfYear,

                                              int dayOfMonth) {

                            // TODO Auto-generated method stub

                            //사용자가 입력한 값을 가져온뒤

                            mYear = year;

                            mMonth = monthOfYear;

                            mDay = dayOfMonth;

                            //텍스트뷰의 값을 업데이트함

                            UpdateNow();

                        }

                    };
        });

    }

    public void replace_fragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framelayout, fragment);
        transaction.commit();
    }

    void UpdateNow(){

        txt_select_day.setText(String.format("%d/%d/%d", mYear,

                mMonth + 1, mDay));




    }

}
package com.camping.seoul.seoulcamp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import connectDB.NetworkTask_GetPet;
import connectDB.NetworkTask_getReservationList;
import fragment.NearByFragment;
import fragment.NearByFragment2;
import object.NowUser;
import object.Reservation;
import weather.WeatherInfo;
import weather.WeatherInfo2;

public class Main_activity extends AppCompatActivity {
    FrameLayout frameLayout;
    Button button_main, button_petspital, button_add, button_reservation;
    LinearLayout layout;
    TextView txt_nickname;

    List<Reservation> reservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_mainpage);

        txt_nickname = findViewById(R.id.txt_nickname);
        final ViewPager pager = (ViewPager) findViewById(R.id.weatherPager);
        pager.setOffscreenPageLimit(3); //프래그먼트를 미리 담아두기

        Main_activity.CampingPagerAdapter fragment = new Main_activity.CampingPagerAdapter(getSupportFragmentManager());

        /* Fragment 추가 */
        WeatherInfo fragment1 = new WeatherInfo();
        fragment.addItem(fragment1);

        WeatherInfo2 fragment2 = new WeatherInfo2();
        fragment.addItem(fragment2);
        pager.setAdapter(fragment);

        frameLayout = (FrameLayout) findViewById(R.id.framelayout);

        replace_fragment(new NearByFragment());

        button_main = findViewById(R.id.btn_main);
        button_petspital = findViewById(R.id.btn_petspital);
        button_add = findViewById(R.id.btn_add);
        button_reservation = findViewById(R.id.btn_reservation);
        layout = findViewById(R.id.layout_search);

        txt_nickname.setText(NowUser.nickname + "님");

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main_activity.this, Add_pet_activity.class);
                startActivityForResult(i, 1);
            }
        });
        button_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMain();
            }
        });
        button_petspital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPetspital();
            }
        });

        getReservation();
        if (!reservation.isEmpty()) {
            button_reservation.setText(reservation.get(0).getPetspital() + " " + reservation.get(0).getRegdate() + "진료예정");
        }
        else{
            button_reservation.setText("예정 없음");
        }

        button_reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main_activity.this, My_reservation_activity.class);
                startActivityForResult(i, 2);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 1) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    setMain();
                }
            }, 0);
//            setMain();
        }
        if (resultCode == RESULT_OK && requestCode == 2) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    getReservation();
                    if (!reservation.isEmpty()) {
                        button_reservation.setText(reservation.get(0).getPetspital() + " " + reservation.get(0).getRegdate() + "진료예정");
                    }
                    else{
                        button_reservation.setText("예정 없음");
                    }
                }
            }, 0);
        }
    }

    public void replace_fragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framelayout, fragment);
        transaction.commit();
    }

    class CampingPagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> items = new ArrayList<Fragment>();

        public CampingPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addItem(Fragment item) {
            items.add(item);
        }

        @Override
        public Fragment getItem(int i) {
            return items.get(i);
        }

        @Override
        public int getCount() {
            return items.size();
        }
    }

    public void setMain() {
        replace_fragment(new NearByFragment());
        if (layout.getVisibility() == View.VISIBLE) {
            layout.setVisibility(View.GONE); // 검색란
        }
        button_add.setVisibility(View.VISIBLE); // 추가
    }

    public void setPetspital() {
        replace_fragment(new NearByFragment2());
        if (layout.getVisibility() == View.GONE) {
            layout.setVisibility(View.VISIBLE); // 검색란
        }
        button_add.setVisibility(View.GONE); // 추가
    }

    public void getReservation() {
        try {
            reservation = new NetworkTask_getReservationList().execute(NowUser.id).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
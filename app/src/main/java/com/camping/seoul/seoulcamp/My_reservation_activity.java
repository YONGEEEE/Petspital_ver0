package com.camping.seoul.seoulcamp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import connectDB.NetworkTask_DelList;
import connectDB.NetworkTask_DeleteReservation;
import connectDB.NetworkTask_checkAlreadyReservation;
import connectDB.NetworkTask_checkReservationAble;
import connectDB.NetworkTask_getReservationList;
import connectDB.NetworkTask_insertReservation;
import listview.ReservationView;
import object.NowUser;
import object.Reservation;

public class My_reservation_activity extends AppCompatActivity {
    ListView listView;
    Button showAllButton;
    List<Reservation> list;
    TextView txt_nickname, txt_user_id;

    ReservationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myreservation);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        listView = findViewById(R.id.listView);
        showAllButton = findViewById(R.id.btnShowAll);
        txt_nickname = findViewById(R.id.txt_nickname);

        txt_nickname.setText(NowUser.nickname);
        adapter = new ReservationAdapter();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) { // 토스트 메세지 ( 리스트 뷰 클릭시 )
                show(list.get(position));
            }
        });
        setList();
    }

    public void setList() {//리스트뷰 구현
        list = null;
        try {
            if (list != null) {
                list.clear();
            }
            list = new NetworkTask_getReservationList().execute(NowUser.id).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        adapter.setInit();
        for (int j = 0; j < list.size(); j++) {       // db연동 전 listview 확인을 위한 for문
            adapter.addItem(list.get(j));
        }// 리스트 추가
        listView.setAdapter(adapter);
        showReservation();
    }

    public void show(final Reservation reservation) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogStyle);
        builder.setTitle("예약취소");
        builder.setMessage("예약취소 하시겠습니까?");

        builder.setPositiveButton("예약취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {//수정필요
                int result;
                Reservation tmp = new Reservation(reservation.getPetspital(),reservation.getRegdate(), reservation.getUserid(), reservation.getName(), reservation.getAge(), reservation.getWeight(), reservation.getBirth(), reservation.getInform(), reservation.getKind(), reservation.getFlag(), reservation.getSex());
                try {
                    result = new NetworkTask_DeleteReservation().execute(tmp).get();
                    if (result == 1) {
                        Toast.makeText(getApplicationContext(), "예약취소완료", Toast.LENGTH_SHORT).show();
                        setList();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "예약취소실패", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            //Toast.makeText(getApplicationContext(), "계정 불일치", Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton("확인", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(getApplicationContext(), "확인", Toast.LENGTH_SHORT).show();
            }
        });

        builder.show();
    }

    /* inner 클래스 */
    class ReservationAdapter extends BaseAdapter {

        ArrayList<Reservation> items = new ArrayList();
        private int size = 0;

        public void initSize() {
            size = 0;
        }

        @Override
        public int getCount() {
            return size;
        }
        //리스트 개수 2개로 제한

        public void addItem(Reservation item) {
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void plusSize() {
            if (showAllButton.getText() == "더보기") {
                for(int i = 1 ; i <= 10 ; i++)
                {
                    if(size == items.size())
                    {
                        showAllButton.setText("가리기");
                        break;
                    }
                    else
                    {
                        size++;
                    }
                }
//                if (size + 2 <= items.size()) {
//                    size += 2;
//                } else if (size + 1 == items.size()) {
//                    size++;
//                } else {
//
//                }
//                if (size == items.size()) {
//                    showAllButton.setText("가리기");
//
//                }
            } else {
                showAllButton.setText("더보기");
                showReservation();
            }

        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ReservationView view = new ReservationView(getApplicationContext());

            Reservation item = items.get(position);
            Log.d("Reservation : ", item.toString());
            view.setTxt_name(item.getName());
            view.setTxt_petspital_name(item.getPetspital());
            view.setTxt_regdate(item.getRegdate());
            view.setTxt_user_id("");
            return view;
        }

        public void setInit() {
            items.clear();
        }
    }

    public void showReservation() { // 초기화
        adapter.initSize();
        showAllReservation();
    }


    public void showAllReservation() { // 더보기
        adapter.plusSize();
        getTotalHeightofListView(listView);
        adapter.notifyDataSetChanged();
    }


    public void getTotalHeightofListView(ListView listView) // 개수 제한 및 높이 수정
    {
        ListAdapter listAdapter = listView.getAdapter();
        int totalHeight = 0;

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            listItem.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

            totalHeight += listItem.getMeasuredHeight();
            Log.w("Height" + i, String.valueOf(totalHeight));
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }


    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        super.onBackPressed();
    }
}

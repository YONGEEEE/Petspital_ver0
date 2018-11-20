package com.camping.seoul.seoulcamp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import connectDB.NetworkTask_AddList;
import connectDB.NetworkTask_DelList;
import connectDB.NetworkTask_GetList;
import listview.CommentItem;
import listview.CommentItemView;
import object.NowUser;
import object.PetspitalData;


public class Camp_detail extends AppCompatActivity implements OnMapReadyCallback {

    private SupportMapFragment mapFragment;
    private GoogleMap googleMap;
    private ArrayList<LatLng> listLatLng;
    private GoogleApiClient gclient;
    private TextView name, address, value1, value2, value3;
    private Button reservation, calling;
    private ImageView image;
    private String strreserv, strsite, telNum;
    private double x, y;

    /* 새로 추가된 부분 ( listView 관련 변수들 )*/
    ListView listView;
    EditText comment;
    Button btnWriteComment;
    CommentAdapter adapter;
    List<CommentItem> list;
    Button showAllButton;
    RatingBar ratingBar;
    /* 끝 */

    EditText editComment;
    PetspitalData index;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_campdetail);


        Intent i = getIntent();
        index = (PetspitalData) i.getSerializableExtra("index");//parsing test

        name = (TextView) findViewById(R.id.showname);
        address = (TextView) findViewById(R.id.showaddress);
        value1 = (TextView) findViewById(R.id.value1);
        value2 = (TextView) findViewById(R.id.value2);
        value3 = (TextView) findViewById(R.id.value3);
        reservation = (Button) findViewById(R.id.reservation);
        calling = (Button) findViewById(R.id.calling);
//        image = (ImageView) findViewById(R.id.image);


        name.setText(index.getNM());
        Log.d("address", index.getADDR());
        address.setText(index.getADDR());

        /*strsite = index.get();
        strreserv = index.getResurl();*/
        telNum = index.getTEL();
        x = index.getXCODE();
        y = index.getYCODE();
//        image.setImageResource(index.getImage());


        editComment = (EditText) findViewById(R.id.editComment);



        /* 추가 부분 */
        showAllButton = findViewById(R.id.btnShowAll);
        btnWriteComment = findViewById(R.id.btnWriteComment);
        comment = findViewById(R.id.editComment);
        ratingBar = findViewById(R.id.ratingBar);
        adapter = new CommentAdapter();
        listView = findViewById(R.id.listView);


        setList();

        showAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAllComment();
            }
        }); // 더보기 클릭시 이벤트


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) { // 토스트 메세지 ( 리스트 뷰 클릭시 )
                Log.d("getpw", list.get(position).toString());
                show(list.get(position));
            }
        });

        btnWriteComment.setOnClickListener(new View.OnClickListener() { // 작성하기 버튼 클릭시 이벤트
            @Override
            public void onClick(View view) {
                String id;
                String text;
                float star;
                String type;
                String pw;
                if (editComment.getText().toString().length() == 0) {
                    Toast.makeText(Camp_detail.this, "내용을 입력하지 않았습니다.", Toast.LENGTH_SHORT).show();
                }else {
                    text = editComment.getText().toString();
                    star = ratingBar.getRating();
                    Log.d("COMMENT : " , index.getNM()+"\t"+NowUser.id+"\t"+text+"\t"+Float.toString(star)+"\t");
                    editComment.setText(null);
                    try {
                        new NetworkTask_AddList().execute(new CommentItem(index.getNM(), NowUser.id, text, star));
                        setList();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        /*추가 부분 끝 */


        gclient = new GoogleApiClient
                .Builder(this)
                .addApi(AppIndex.API)
                .build();

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(Camp_detail.this);

        reservation.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(strreserv)));
                Intent i = new Intent(Camp_detail.this, Reservation_activity.class);
                startActivity(i);
            }
        });
        calling.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + telNum));
                try {
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setList() {//리스트뷰 구현
        list = null;
        try {
            if (list != null) {
                list.clear();
            }
            list = new NetworkTask_GetList().execute(index.getNM()).get();
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
        showComment();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        Geocoder geocoder = new Geocoder(this);
        String str = index.getADDR();
        List<Address> addressList = null;
        try {
            // editText에 입력한 텍스트(주소, 지역, 장소 등)을 지오 코딩을 이용해 변환
            addressList = geocoder.getFromLocationName(
                    str, // 주소
                    1); // 최대 검색 결과 개수
        } catch (IOException e) {
            e.printStackTrace();
        }

        double latitude = addressList.get(0).getLatitude();
        double longitude = addressList.get(0).getLongitude();

        CameraPosition cameraPosition = new CameraPosition.Builder().target(
                new LatLng(latitude, longitude)).zoom(16).build();

        LatLng point = new LatLng(latitude, longitude);
        MarkerOptions mOptions2 = new MarkerOptions();
        mOptions2.title("search result");
        mOptions2.position(point);
        // 마커 추가
        googleMap.addMarker(mOptions2);


        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        //show current location
        googleMap.setMyLocationEnabled(true); // false to disable
        // Zooming Buttons
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        //Zooming Functionality
        googleMap.getUiSettings().setZoomGesturesEnabled(true);
        //Compass Functionality
        googleMap.getUiSettings().setCompassEnabled(true);
        //My Location Button
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        //Map Rotate Gesture
        googleMap.getUiSettings().setRotateGesturesEnabled(true);

    }


    /* inner 클래스 */
    class CommentAdapter extends BaseAdapter {

        ArrayList<CommentItem> items = new ArrayList();
        private int size = 0;

        public void initSize() {
            size = 0;
        }

        @Override
        public int getCount() {
            return size;
        }
        //리스트 개수 2개로 제한

        public void addItem(CommentItem item) {
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
                if (size + 2 <= items.size()) {
                    size += 2;
                } else if (size + 1 == items.size()) {
                    size++;
                } else {

                }
                if (size == items.size()) {
                    showAllButton.setText("가리기");

                }
            } else {
                showAllButton.setText("더보기");
                showComment();
            }

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            CommentItemView view = new CommentItemView(getApplicationContext());

            CommentItem item = items.get(position);

            view.setID(item.getId());
            view.setComment(item.getText());
            view.setRating(item.getStar());
            view.setTime(item.getRegdate());
            return view;
        }

        public void setInit() {
            items.clear();
        }
    }

    public void showComment() { // 초기화
        adapter.initSize();
        showAllComment();
    }


    public void showAllComment() { // 더보기
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

    public void show(final CommentItem cm) {
        final int num = cm.getNum();
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogStyle);
        builder.setTitle("삭제");
        builder.setMessage("정말로 삭제하시겠습니까?");


        builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {//수정필요
                    try {
                        if(NowUser.id.equals(cm.getId())) {
                            Integer n = new NetworkTask_DelList().execute(cm).get();
                            if (n == 1) {
                                Toast.makeText(getApplicationContext(), "삭제 성공", Toast.LENGTH_SHORT).show();
                                setList();
                            }
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "계정 불일치", Toast.LENGTH_SHORT).show();
                        }
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                    //Toast.makeText(getApplicationContext(), "계정 불일치", Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(getApplicationContext(), "취소", Toast.LENGTH_SHORT).show();
            }
        });

        builder.show();
    }
}

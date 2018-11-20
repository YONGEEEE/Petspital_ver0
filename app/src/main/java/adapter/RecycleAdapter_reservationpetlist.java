package adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.camping.seoul.seoulcamp.Pet_information;
import com.camping.seoul.seoulcamp.R;
import com.camping.seoul.seoulcamp.Reservation_activity;

import java.util.List;
import java.util.concurrent.ExecutionException;

import connectDB.NetworkTask_GetPet;
import connectDB.NetworkTask_checkReservationAble;
import connectDB.NetworkTask_insertReservation;
import object.NowUser;
import object.PetData;
import object.Reservation;


public class RecycleAdapter_reservationpetlist extends RecyclerView.Adapter<RecycleAdapter_reservationpetlist.MyViewHolder> {

    Context context;
    List<PetData> CampdataList;
    PetData temp;
    List<PetData> tmp;

    public class MyViewHolder extends RecyclerView.ViewHolder {


        ImageView image;
        TextView name;


        public MyViewHolder(View view) {
            super(view);

            image = (ImageView) view.findViewById(R.id.image);
            name = (TextView) view.findViewById(R.id.name);
        }

    }

    public RecycleAdapter_reservationpetlist(Context mainActivityContacts, List<PetData> moviesList) {
        this.CampdataList = moviesList;
        this.context = mainActivityContacts;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_camp, parent, false);

        return new MyViewHolder(itemView);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final PetData movie = CampdataList.get(position);

        holder.name.setText(movie.getName());
        if (movie.getFlag() == 1) {
            holder.image.setImageResource(R.drawable.dog);
        } else {
            holder.image.setImageResource(R.drawable.cat);
        }
        //수정필요
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show(movie);
            }
        });


    }

    @Override
    public int getItemCount() {
        return CampdataList.size();
    }

    public void show(final PetData petData) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.MyDialogStyle);
        builder.setTitle("예약선택");
        builder.setMessage("예약하시겠습니까?");

        builder.setPositiveButton("예약", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {//수정필요
                int result;
                try {
                    if (NowUser.regdate.equals("")) {
                        Toast.makeText(context.getApplicationContext(), "예약 날짜를 선택해 주세요", Toast.LENGTH_SHORT).show();
                    } else {
                        result = new NetworkTask_checkReservationAble().execute(new Reservation(NowUser.petspital, NowUser.regdate)).get();
                        if (result < 5) {
                            Reservation tmp = new Reservation(NowUser.petspital, NowUser.regdate, petData.getUserid(), petData.getName(), petData.getAge(), petData.getWeight(), petData.getBirth(), petData.getInform(), petData.getKind(), petData.getFlag(), petData.getSex());
                            int insert_result = new NetworkTask_insertReservation().execute(tmp).get();
                            if (insert_result == 1) {
                                Toast.makeText(context.getApplicationContext(), "예약 완료", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context.getApplicationContext(), "DB삽입 실패", Toast.LENGTH_SHORT).show();
                            }


                        } else {
                            Toast.makeText(context.getApplicationContext(), "예약 불가", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            //Toast.makeText(getApplicationContext(), "계정 불일치", Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(context.getApplicationContext(), "취소", Toast.LENGTH_SHORT).show();
            }
        });

        builder.show();
    }

}



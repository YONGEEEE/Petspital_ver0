package adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.camping.seoul.seoulcamp.Camp_detail;
import com.camping.seoul.seoulcamp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import apiParser.JsonParser;
import object.CampData;
import object.PetspitalData;


public class RecycleAdapter_camplist extends RecyclerView.Adapter<RecycleAdapter_camplist.MyViewHolder> {

    Context context;
    private List<PetspitalData> CampdataList;
    private ArrayList<PetspitalData> tmp;
    private int image = R.drawable.hallym_hospital;

    public ArrayList<PetspitalData> getTmp() {
        return tmp;
    }

    public void setTmp(ArrayList<PetspitalData> tmp) {
        this.tmp = tmp;
    }

    public List<PetspitalData> getCampdataList() {
        return CampdataList;
    }

    public void setCampdataList(List<PetspitalData> campdataList) {
        CampdataList = campdataList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        ImageView image;
        TextView name;


        public MyViewHolder(View view) {
            super(view);

            image = (ImageView) view.findViewById(R.id.image);
            name = (TextView) view.findViewById(R.id.name);
        }

    }

    public RecycleAdapter_camplist(Context mainActivityContacts, List<PetspitalData> moviesList) {
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
        final PetspitalData movie = CampdataList.get(position);
        CampdataList.get(position).getNM();
        holder.name.setText(movie.getNM());
        holder.image.setImageResource(image);

        //수정필요
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, Camp_detail.class);
                i.putExtra("index", (PetspitalData) movie);
                context.startActivity(i);

            }
        });


    }

    @Override
    public int getItemCount() {
        return CampdataList.size();
    }


}



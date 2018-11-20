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
import com.camping.seoul.seoulcamp.Pet_information;
import com.camping.seoul.seoulcamp.R;

import java.util.List;
import java.util.concurrent.ExecutionException;


import connectDB.NetworkTask_GetPetList;

import object.NowUser;
import object.PetData;


public class RecycleAdapter_petlist extends RecyclerView.Adapter<RecycleAdapter_petlist.MyViewHolder> {

    Context context;
    List<PetData> CampdataList;
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

    public RecycleAdapter_petlist(Context mainActivityContacts, List<PetData> moviesList) {
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
                Intent i = new Intent(context, Pet_information.class);
                i.putExtra("index", (PetData) movie);
                context.startActivity(i);

            }
        });


    }

    @Override
    public int getItemCount() {
        return CampdataList.size();
    }


}



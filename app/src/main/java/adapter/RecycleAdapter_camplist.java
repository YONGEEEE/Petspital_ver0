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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import apiParser.JsonParser;
import object.CampData;
import object.PetspitalData;


public class RecycleAdapter_camplist extends RecyclerView.Adapter<RecycleAdapter_camplist.MyViewHolder> {

    Context context;
    List<PetspitalData> CampdataList;
    Map<String, PetspitalData> tmp;
    private int image = R.drawable.yeouido_detail;

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
        try {
            tmp = new JsonParser().execute().get();
            Log.d("Size", "" + tmp.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
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

        holder.name.setText(movie.getNM());

        //수정필요
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, Camp_detail.class);
                /*switch (movie.getName()) {
                    case "동물병원1":
                        tmp.get("camp2018_1").setImage(image[0]);
                        i.putExtra("index", (CampData) tmp.get("camp2018_1"));//parsing test
                        break;
                    case "동물병원2":
                        tmp.get("camp2018_2").setImage(image[1]);
                        i.putExtra("index", (CampData) tmp.get("camp2018_2"));//parsing test
                        break;
                    case "동물병원3":
                        tmp.get("camp2018_3").setImage(image[2]);
                        i.putExtra("index", (CampData) tmp.get("camp2018_3"));//parsing test
                        break;
                    case "동물병원4":
                        tmp.get("camp2018_4").setImage(image[3]);
                        i.putExtra("index", (CampData) tmp.get("camp2018_4"));//parsing test
                        break;
                    case "동물병원5":
                        tmp.get("camp2018_5").setImage(image[4]);
                        i.putExtra("index", (CampData) tmp.get("camp2018_5"));//parsing test
                        break;
                    case "동물병원6":
                        tmp.get("camp2018_6").setImage(image[5]);
                        i.putExtra("index", (CampData) tmp.get("camp2018_6"));//parsing test
                        break;
                    case "동물병원7":
                        tmp.get("camp2018_7").setImage(image[6]);
                        i.putExtra("index", (CampData) tmp.get("camp2018_7"));//parsing test
                        break;
                    case "동물병원8":
                        tmp.get("camp2018_8").setImage(image[7]);
                        i.putExtra("index", (CampData) tmp.get("camp2018_8"));//parsing test
                        break;
                    case "동물병원9":
                        tmp.get("camp2018_9").setImage(image[8]);
                        i.putExtra("index", (CampData) tmp.get("camp2018_9"));//parsing test
                        break;
                }
*/
                context.startActivity(i);

            }
        });


    }

    @Override
    public int getItemCount() {
        return CampdataList.size();
    }


}



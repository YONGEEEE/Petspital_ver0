package fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.camping.seoul.seoulcamp.Pet_information;
import com.camping.seoul.seoulcamp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import connectDB.NetworkTask_GetPet;
import connectDB.NetworkTask_GetPetList;
import object.NowUser;
import object.PetData;

import static android.app.Activity.RESULT_OK;


public class NearByFragment extends Fragment {

    View view;

    private List<PetData> tmp;
    private ArrayList<PetData> CampdataArrayList;
    private RecyclerView recyclerView;
    private RecycleAdapter_petlist mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_campmain, container, false);

        try {
            tmp = new NetworkTask_GetPetList().execute(NowUser.id).get();
            Log.d("Size", "" + tmp.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < tmp.size(); i++) {
            Log.d("tmp : ", tmp.get(i).toString());
        }


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_property);

        startFragment();


        return view;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
    public class RecycleAdapter_petlist extends RecyclerView.Adapter<RecycleAdapter_petlist.MyViewHolder> {

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
                    try{
                        temp = new NetworkTask_GetPet().execute(movie).get();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    i.putExtra("index", (PetData) temp);
                    //context.startActivity(i);
                    getActivity().startActivityForResult(i,1);

                }
            });


        }

        @Override
        public int getItemCount() {
            return CampdataList.size();
        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 1) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startFragment();
                }
            }, 500);

        }

    }

    public void startFragment()
    {
        CampdataArrayList = new ArrayList<>();


        for (int i = 0; i < tmp.size(); i++) {
            PetData beanClassForRecyclerView_contacts = tmp.get(i);
            CampdataArrayList.add(beanClassForRecyclerView_contacts);
        }


        mAdapter = new RecycleAdapter_petlist(getActivity(), CampdataArrayList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

}

package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.camping.seoul.seoulcamp.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import adapter.RecycleAdapter_camplist;
import apiParser.JsonParser;
import object.PetspitalData;


public class NearByFragment2 extends Fragment {

    View view;

    private ArrayList<PetspitalData> tmp;
    private ArrayList<PetspitalData> CampdataArrayList;
    private RecyclerView recyclerView;
    private RecycleAdapter_camplist mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_campmain, container, false);

        try {
            tmp = new JsonParser().execute().get();
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
        CampdataArrayList = new ArrayList<>();


        for (int i = 0; i < tmp.size(); i++) {
            PetspitalData beanClassForRecyclerView_contacts = tmp.get(i);
            CampdataArrayList.add(beanClassForRecyclerView_contacts);
        }


        mAdapter = new RecycleAdapter_camplist(getActivity(), CampdataArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }


}

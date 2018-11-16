package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.camping.seoul.seoulcamp.R;

import java.util.ArrayList;

import adapter.RecycleAdapter_camplist;
import object.CampData;
import object.PetspitalData;


public class NearByFragment2 extends Fragment {

    View view;
    private String name[] = {"동물병원1", "동물병원2", "동물병원3", "동물병원4", "동물병원5", "동물병원6", "동물병원7", "동물병원8", "동물병원9"};
    private int image[] = {R.drawable.yeouido_main, R.drawable.dducksome_main, R.drawable.sungdong_main, R.drawable.guro_main, R.drawable.nanji_main, R.drawable.noeul_main, R.drawable.seouldae_main, R.drawable.junglang_main, R.drawable.gangdong_main};


    private ArrayList<PetspitalData> CampdataArrayList;
    private RecyclerView recyclerView;
    private RecycleAdapter_camplist mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_campmain, container, false);


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_property);
        CampdataArrayList = new ArrayList<>();


        for (int i = 0; i < name.length; i++) {
            PetspitalData beanClassForRecyclerView_contacts = new PetspitalData(name[i]);
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

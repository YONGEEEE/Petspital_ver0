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
import java.util.List;
import java.util.concurrent.ExecutionException;

import adapter.RecycleAdapter_petlist;
import connectDB.NetworkTask_GetPetList;
import object.NowUser;
import object.PetData;


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


        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }


}

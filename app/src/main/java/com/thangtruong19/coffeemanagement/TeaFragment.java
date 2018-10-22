package com.thangtruong19.coffeemanagement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeaFragment extends Fragment {
    public TeaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.activity_common,container,false);

        final ArrayList<Dish> dishes=new ArrayList<>();

        dishes.add(new Dish("Lipton đá","20000",R.drawable.lipton_da));
        dishes.add(new Dish("Lipton sữa đá","20000",R.drawable.lipton_sua_da));
        dishes.add(new Dish("Lipton mật ong","20000",R.drawable.lipton_mat_ong));
        dishes.add(new Dish("Trà gừng","20000",R.drawable.tra_gung));
        dishes.add(new Dish("Trà việt quất","20000",R.drawable.tra_viet_quat));
        dishes.add(new Dish("Trà dâu tây","20000",R.drawable.tra_dau_tay));

        ListView listView=(ListView)rootView.findViewById(R.id.listView);

        DishAdapter adapter=new DishAdapter(getActivity(),dishes);
        listView.setAdapter(adapter);

        return rootView;
    }

}

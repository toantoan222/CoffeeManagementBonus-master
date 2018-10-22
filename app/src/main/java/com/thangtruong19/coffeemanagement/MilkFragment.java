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
public class MilkFragment extends Fragment {
    public MilkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.activity_common,container,false);

        final ArrayList<Dish> dishes=new ArrayList<>();

        dishes.add(new Dish("Sữa tươi","30000",R.drawable.sua_tuoi));
        dishes.add(new Dish("Sữa tươi cafe","30000",R.drawable.sua_tuoi_cafe));
        dishes.add(new Dish("Sữa đá chanh","30000",R.drawable.sua_da_chanh));
        dishes.add(new Dish("Sữa chanh dây","30000",R.drawable.sua_chanh_day));
        dishes.add(new Dish("Sữa việt quất","30000",R.drawable.sua_viet_quat));
        dishes.add(new Dish("Sữa dâu tây","30000",R.drawable.sua_dau_tay));

        ListView listView=(ListView)rootView.findViewById(R.id.listView);

        DishAdapter adapter=new DishAdapter(getActivity(),dishes);
        listView.setAdapter(adapter);

        return rootView;
    }
}

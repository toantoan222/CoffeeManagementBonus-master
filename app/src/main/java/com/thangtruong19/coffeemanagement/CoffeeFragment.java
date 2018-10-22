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
public class CoffeeFragment extends Fragment {
    public CoffeeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.activity_common,container,false);

        final ArrayList<Dish> dishes=new ArrayList<>();

        dishes.add(new Dish("Cafe đen","25000",R.drawable.caffee_den));
        dishes.add(new Dish("Cafe sữa","25000",R.drawable.caffee_sua));
        dishes.add(new Dish("Latte","25000",R.drawable.latte));
        dishes.add(new Dish("Expresso","25000",R.drawable.expresso));
        dishes.add(new Dish("Cappuchino","25000",R.drawable.cappuchino));
        dishes.add(new Dish("Cacao","25000",R.drawable.cacao));

        ListView listView=(ListView)rootView.findViewById(R.id.listView);

        DishAdapter adapter=new DishAdapter(getActivity(),dishes);
        listView.setAdapter(adapter);

        return rootView;
    }
}

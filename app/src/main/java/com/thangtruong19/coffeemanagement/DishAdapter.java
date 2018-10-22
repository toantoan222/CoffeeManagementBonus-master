package com.thangtruong19.coffeemanagement;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 20/10/2018.
 */

public class DishAdapter extends ArrayAdapter<Dish>{
    private List<Dish> dishes=new ArrayList<>();

    public DishAdapter(@NonNull Context context, @NonNull List<Dish> objects) {
        super(context, 0, objects);
        dishes=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem=convertView;
        if(listItem==null){
            listItem= LayoutInflater.from(getContext()).inflate(R.layout.list_item_dish,parent,false);
        }

        Dish currentDish=dishes.get(position);

        TextView dishNameTextView=listItem.findViewById(R.id.list_item_dish_name);
        dishNameTextView.setText(currentDish.getName());

        TextView dishPriceTextView=listItem.findViewById(R.id.list_item_dish_price);
        dishPriceTextView.setText(currentDish.getPrice());

        ImageView imageView=listItem.findViewById(R.id.list_item_dish_img);
        imageView.setImageResource(currentDish.getImgResourceId());

        return listItem;
    }
}

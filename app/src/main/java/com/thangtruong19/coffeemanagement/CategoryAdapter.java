package com.thangtruong19.coffeemanagement;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by User on 20/10/2018.
 */

public class CategoryAdapter extends FragmentPagerAdapter{
    private Context mContext;
    public CategoryAdapter(FragmentManager fm, Context context) {

        super(fm);
        mContext=context;
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new CoffeeFragment();
        }else if(position==1){
            return new TeaFragment();
        }else {
            return new MilkFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){
            return mContext.getString(R.string.category_coffee);
        }else if(position==1){
            return mContext.getString(R.string.category_tea);
        }else{
            return mContext.getString(R.string.category_milk);
        }
    }
}

package com.thangtruong19.coffeemanagement;

/**
 * Created by User on 20/10/2018.
 */

public class Dish {
    private String name;
    private String price;
    private int imgResourceId;

    public Dish(String name,String price,int imgResourceId){
        this.name=name;
        this.price=price;
        this.imgResourceId=imgResourceId;
    }


    public String getName(){
        return this.name;
    }

    public String getPrice(){
        return this.price;
    }

    public int getImgResourceId(){
        return this.imgResourceId;
    }
}

package com.thangtruong19.coffeemanagement.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by User on 19/10/2018.
 */

public class OrderContract {
    private OrderContract(){}

    public static final String CONTENT_AUTHORITY="com.thangtruong19.coffeemanagement" ;
    public static final Uri BASE_CONTENT_URI= Uri.parse("content://"+CONTENT_AUTHORITY);
    public static final String PATH_ORDERS="orders";

    public static class OrderEntry implements BaseColumns {
        /** The content URI to access the pet data in the provider */
        public static final Uri CONTENT_URI=Uri.withAppendedPath(BASE_CONTENT_URI,PATH_ORDERS);
        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of pets.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ORDERS;
        /**
         * The MIME type of the {@link #CONTENT_URI} for a single pet.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ORDERS;
        public static final String TABLE_NAME="orders";
        public static final String _ID=BaseColumns._ID;
        public static final String COLUMN_COFFEE_NAME="coffeeName";
        public static final String COLUMN_COFFEE_NUMBER="coffeeNumber";
        public static final String COLUMN_TEA_NAME="teaName";
        public static final String COLUMN_TEA_NUMBER="teaNumber";
        public static final String COLUMN_MILK_NAME="milkName";
        public static final String COLUMN_MILK_NUMBER="milkNumber";
        public static final String COLUMN_TABLE_ORDER="tableOrder";
        public static final String COLUMN_TOTAL_MONEY="totalMoney";


        public static final int TABLE_ONE=0;
        public static final int TABLE_TWO=1;
        public static final int TABLE_THREE=2;
        public static final int TABLE_FOUR=3;
        public static final int TABLE_FIVE=4;
        public static final int TABLE_SIX=5;
        public static final int TABLE_SEVEN=6;
        public static final int TABLE_EIGHT=7;
        public static final int TABLE_NINE=8;
        public static final int TABLE_TEN=9;
        public static final int TABLE_ELEVEN=10;
        public static final int TABLE_TWELVE=11;
        public static final int TABLE_THIRTTEEN=12;
        public static final int TABLE_FOURTEEN=13;
        public static final int TABLE_FIFTHTEEN=14;
        public static final int TABLE_SIXTEEN=15;
        public static final int TABLE_SEVENTEEN=16;
        public static final int TABLE_EIGHTTEEN=17;
        public static final int TABLE_NINETEEN=18;
        public static final int TABLE_TWENTY=19;
        public static final int TABLE_TWENTY_ONE=20;
        public static final int TABLE_TWENTY_TWO=21;
        public static final int TABLE_TWENTY_THREE=22;
        public static final int TABLE_TWENTY_FOUR=23;

    }
}

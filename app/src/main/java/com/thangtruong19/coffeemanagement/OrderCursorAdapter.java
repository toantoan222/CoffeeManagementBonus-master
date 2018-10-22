package com.thangtruong19.coffeemanagement;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.thangtruong19.coffeemanagement.data.OrderContract;

/**
 * Created by User on 19/10/2018.
 */

public class OrderCursorAdapter extends CursorAdapter{
    public OrderCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.list_item,viewGroup,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
// Find fields to populate in inflated template
        TextView nameView = (TextView) view.findViewById(R.id.name);
        TextView summaryView = (TextView) view.findViewById(R.id.summary);
        // Extract properties from cursor
        int name = cursor.getInt(cursor.getColumnIndexOrThrow(OrderContract.OrderEntry.COLUMN_TABLE_ORDER));
        name+=1;
        String summary = cursor.getString(cursor.getColumnIndexOrThrow(OrderContract.OrderEntry.COLUMN_TOTAL_MONEY));
        // Populate fields with extracted properties
        nameView.setText("Bàn "+name);
        summaryView.setText(summary+" VND");
    }
}

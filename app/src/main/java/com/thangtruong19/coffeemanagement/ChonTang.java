package com.thangtruong19.coffeemanagement;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class ChonTang extends AppCompatActivity {

    TextView txtchontang;
    ImageView imgtang1;
    ImageView imgtang2;
    ImageView imgtang3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.chontang);
        imgtang1=(ImageView)findViewById(R.id.imgtang1);
        imgtang2=(ImageView)findViewById(R.id.imgtang2);
        imgtang3=(ImageView)findViewById(R.id.imgtang3);

        imgtang1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent tang11=new Intent(ChonTang.this,OrderActivity.class);
                startActivity(tang11);
            }
        });

        imgtang2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent tang22=new Intent(ChonTang.this,OrderActivity.class);
                startActivity(tang22);

            }
        });
        imgtang3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent tang22=new Intent(ChonTang.this,OrderActivity.class);
                startActivity(tang22);

            }
        });




    }
}

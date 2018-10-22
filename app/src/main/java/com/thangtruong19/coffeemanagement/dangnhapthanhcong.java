package com.thangtruong19.coffeemanagement;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.thangtruong19.coffeemanagement.DangNhap;
public class dangnhapthanhcong extends AppCompatActivity {
    String null1;
    String null2;
    Button btnchontang;
    String kq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.loginthanhcong);
        Intent info = getIntent();
        Bundle nhan_goi_data_login = info.getExtras();
        // Gói nhận data gồm tên đăng nhập và password nhận từ activity DangNhap thông qua intent info

        String nhanuser = nhan_goi_data_login.getString("user");
        String nhanpass = nhan_goi_data_login.getString("pass");

        nhanuser.trim(); // loại bỏ các ký tự thừa
        nhanpass.trim();
        if(!nhanuser.isEmpty()&&!nhanpass.isEmpty()){
            if (nhanuser.equals("Admin") && nhanpass.equals("Admin123")) {

                Toast.makeText(getApplicationContext(), "                 CHÚC MỪNG BẠN " +"\n"+ "                            "+nhanuser + "\n" + "        ĐÃ ĐĂNG NHẬP THÀNH CÔNG!",
                    Toast.LENGTH_SHORT).show();

            } else {

                 // trả về kết quả nếu ko đúng tên đăng nhập và mật khẩu
                null1 = "";
                null2 = "";
                nhan_goi_data_login.putString("back1", null1);
                nhan_goi_data_login.putString("back2", null2);
                info.putExtras(nhan_goi_data_login);
                setResult(Activity.RESULT_OK, info);
                Toast.makeText(getApplicationContext(),"SAI TÊN TÀI KHOẢN HOẶC MẬT KHẨU",Toast.LENGTH_LONG).show();
                finish();

            }
        }else if(nhanuser.isEmpty()&&nhanpass.isEmpty()){

            Toast.makeText(getApplicationContext(),"VUI LÒNG ĐIỀN TÊN TÀI KHOẢN",Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(),"VUI LÒNG ĐIỀN MẬT KHẨU",Toast.LENGTH_LONG).show();
            finish();
        }else if(nhanuser.isEmpty()){
            Toast.makeText(getApplicationContext(),"VUI LÒNG ĐIỀN TÊN TÀI KHOẢN",Toast.LENGTH_LONG).show();
            finish();
        }else{
            Toast.makeText(getApplicationContext(),"VUI LÒNG ĐIỀN MẬT KHẨU",Toast.LENGTH_LONG).show();
            finish();
        }


        btnchontang = (Button) findViewById(R.id.btnchontang);
        btnchontang.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent chontang = new Intent(dangnhapthanhcong.this, ChonTang.class);
                startActivity(chontang);

            }
        });

    }
}

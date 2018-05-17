package com.example.tieu_nt.mokidemo.View;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tieu_nt.mokidemo.R;

/**
 * Created by tieu_nt on 5/17/2018.
 */

public class MainActivity extends AppCompatActivity{
    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view1 = LayoutInflater.from(this).inflate(R.layout.dialog_thongbao_xacnhan, null);
        Button btnHuy = (Button) view1.findViewById(R.id.btnHuy);
        Button btnDongY = (Button) view1.findViewById(R.id.btnDongY);
        btnDongY.setText("Thoát");
        TextView tvNoiDung = (TextView) view1.findViewById(R.id.tvNoiDung);
        tvNoiDung.setText("Bạn có chắc muốn thoát ứng dụng?");

        builder.setView(view1);
        final AlertDialog dialogCloseApp = builder.create();
        dialogCloseApp.show();

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogCloseApp.dismiss();
            }
        });

        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogCloseApp.dismiss();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}

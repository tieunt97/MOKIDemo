package com.example.tieu_nt.mokidemo.View.TrangChu;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.tieu_nt.mokidemo.Model.DanhMuc;
import com.example.tieu_nt.mokidemo.Presenter.TrangChuSanPham.PresenterLogicDanhMuc;
import com.example.tieu_nt.mokidemo.R;

import java.util.List;

/**
 * Created by tieu_nt on 5/28/2018.
 */

public class DialogLoc extends AlertDialog.Builder{
    private Context context;
    private LocSanPham locSanPham;
    private AlertDialog alertDialog;
    private int idLoaiSP, giaThap= 0, giaCao = 0;
    private ArrayAdapter arrayAdapter;
    private PresenterLogicDanhMuc presenterLogicDanhMuc;

    public DialogLoc(@NonNull Context context, LocSanPham locSanPham, int idLoaiSP) {
        super(context);
        this.context = context;
        this.locSanPham = locSanPham;
        this.idLoaiSP = idLoaiSP;
        presenterLogicDanhMuc = new PresenterLogicDanhMuc();
    }

    @Override
    public AlertDialog show() {
        View view = ((Activity) context).getLayoutInflater().inflate(R.layout.dialog_loc, null, false);
        final Spinner spinner = view.findViewById(R.id.spinerDanhMuc);
        final EditText edtToiThieu = view.findViewById(R.id.edtToiThieu);
        final EditText edtToiDa = view.findViewById(R.id.edtToiDa);
        Button btnHuy = view.findViewById(R.id.btnHuy);
        Button btnApDung = view.findViewById(R.id.btnApDung);
        setView(view);

        final List<DanhMuc> dsDanhMuc = presenterLogicDanhMuc.layDSDanhMucCon(idLoaiSP);
        int size = dsDanhMuc.size();
        String danhMuc[] = new String[size];
        for(int i = 0; i < size; i++){
            danhMuc[i] = dsDanhMuc.get(i).getTenDanhMuc();
        }
        arrayAdapter = new ArrayAdapter(context, R.layout.custom_item_spiner, R.id.tvDanhMuc, danhMuc);
        spinner.setAdapter(arrayAdapter);

        alertDialog = super.show();

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });


        btnApDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    giaThap = Integer.parseInt(edtToiThieu.getText().toString());
                    giaCao = Integer.parseInt(edtToiDa.getText().toString());
                }catch (NumberFormatException exc){
                    exc.printStackTrace();
                }
                alertDialog.dismiss();
                locSanPham.locSanPham(dsDanhMuc.get(spinner.getSelectedItemPosition()).getIdDanhMuc(), giaThap, giaCao);
            }
        });

        return alertDialog;
    }
}

package com.example.tieu_nt.mokidemo.Model.TrangChu;

import android.util.Log;

import com.example.tieu_nt.mokidemo.ConnectInternet.DownloadJSON;
import com.example.tieu_nt.mokidemo.Model.TaiKhoan;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.ManHinhTrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by tieu_nt on 4/18/2018.
 */

public class ModelDangNhap_DangKy {
    public TaiKhoan layTaiKhoan(String ham, String soDT, String matKhau){
        TaiKhoan taiKhoan = new TaiKhoan();
        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = ManHinhTrangChuActivity.SERVER_NAME_DANGNHAP_DANGKY;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham", ham);

        HashMap<String,String> hsSoDT = new HashMap<>();
        hsSoDT.put("soDT", soDT);

        HashMap<String,String> hsMatKhau = new HashMap<>();
        hsMatKhau.put("matKhau", matKhau);

        attrs.add(hsHam);
        attrs.add(hsSoDT);
        attrs.add(hsMatKhau);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        //end phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArray = jsonObject.getJSONArray("taikhoan");
            JSONObject objectTaiKhoan = jsonArray.getJSONObject(0);
            taiKhoan.setSoDT(objectTaiKhoan.getString("soDT"));
            taiKhoan.setMatKhau(objectTaiKhoan.getString("matKhau"));
            taiKhoan.setTrangThai(objectTaiKhoan.getInt("trangThai"));
            taiKhoan.setNgayKichHoat(objectTaiKhoan.getString("ngayKichHoat"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return taiKhoan;
    }

    public TaiKhoan dangKyTaiKhoan(String ham, String soDT, String matKhau){
        TaiKhoan taiKhoan = new TaiKhoan();
        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = ManHinhTrangChuActivity.SERVER_NAME_DANGNHAP_DANGKY;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham", ham);

        HashMap<String,String> hsSoDT = new HashMap<>();
        hsSoDT.put("soDT", soDT);

        HashMap<String,String> hsMatKhau = new HashMap<>();
        hsMatKhau.put("matKhau", matKhau);

        attrs.add(hsHam);
        attrs.add(hsSoDT);
        attrs.add(hsMatKhau);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        //end phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArray = jsonObject.getJSONArray("maxacnhan");
            JSONObject object = jsonArray.getJSONObject(0);
            taiKhoan.setSoDT(soDT);
            taiKhoan.setMatKhau(matKhau);
            taiKhoan.setMaXacNhan(object.getInt("maxacnhan"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return taiKhoan;
    }
}

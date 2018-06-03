package com.example.tieu_nt.mokidemo.Model.Data;

import com.example.tieu_nt.mokidemo.ConnectInternet.DownloadJSON;
import com.example.tieu_nt.mokidemo.Model.Constants;
import com.example.tieu_nt.mokidemo.Model.TaiKhoan;
import com.example.tieu_nt.mokidemo.View.TrangChu.TrangChuActivity;

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

public class ModelDangNhapDangKy {
    private static ModelDangNhapDangKy modelDangNhap_dangKy;

    private ModelDangNhapDangKy(){

    }

    public static synchronized ModelDangNhapDangKy getInstance(){
        if(modelDangNhap_dangKy == null) modelDangNhap_dangKy = new ModelDangNhapDangKy();
        return modelDangNhap_dangKy;
    }

    public TaiKhoan layTaiKhoan(String ham, String soDT, String matKhau){
        TaiKhoan taiKhoan = new TaiKhoan();
        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = Constants.SERVER_NAME_DANGNHAP_DANGKY;

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

        String duongdan = Constants.SERVER_NAME_DANGNHAP_DANGKY;

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

    public boolean xacNhanDangKy(String ham, String soDT){
        boolean b = false;
        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = Constants.SERVER_NAME_DANGNHAP_DANGKY;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham", ham);

        HashMap<String,String> hsSoDT = new HashMap<>();
        hsSoDT.put("soDT", soDT);

        attrs.add(hsHam);
        attrs.add(hsSoDT);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        //end phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArray = jsonObject.getJSONArray("xacNhan");
            JSONObject object = jsonArray.getJSONObject(0);
            int xacNhan = object.getInt("xacNhan");
            if(xacNhan == 1) b = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return b;
    }
}

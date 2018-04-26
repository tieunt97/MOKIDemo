package com.example.tieu_nt.mokidemo.Model.TrangChu;

import android.util.Log;

import com.example.tieu_nt.mokidemo.ConnectInternet.DownloadJSON;
import com.example.tieu_nt.mokidemo.Model.ChiTietSanPham;
import com.example.tieu_nt.mokidemo.Model.DanhMuc;
import com.example.tieu_nt.mokidemo.Model.DiaChi;
import com.example.tieu_nt.mokidemo.Model.KhachHang;
import com.example.tieu_nt.mokidemo.Model.SanPham;
import com.example.tieu_nt.mokidemo.Model.TaiKhoan;
import com.example.tieu_nt.mokidemo.Model.TinTuc;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.ManHinhTrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by tieu_nt on 4/21/2018.
 */

public class ModelKhachHang {
    private static ModelKhachHang modelKhachHang;

    private ModelKhachHang(){

    }

    public static synchronized ModelKhachHang getInstance(){
        if(modelKhachHang == null) modelKhachHang = new ModelKhachHang();

        return modelKhachHang;
    }

    public List<TinTuc> layDanhSachTinTuc(int limit){
        List<TinTuc> dsTinTuc = new ArrayList<>();
        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = ManHinhTrangChuActivity.SERVER_NAME_KHACHHANG;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham", "layDSTinTuc");

        HashMap<String,String> hsLimit = new HashMap<>();
        hsLimit.put("limit", String.valueOf(limit));

        attrs.add(hsHam);
        attrs.add(hsLimit);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        //end phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            JSONObject object = new JSONObject(dataJSON);
            JSONArray jsonArray = object.getJSONArray("danhsachtintuc");
            int length = jsonArray.length();
            for(int i = 0; i < length; i++){
                JSONObject object1 = jsonArray.getJSONObject(i);
                TinTuc tinTuc = new TinTuc();
                tinTuc.setIdTinTuc(object1.getInt("idTinTuc"));
                tinTuc.setTieuDe(object1.getString("tieuDe"));
                tinTuc.setNoiDung(object1.getString("noiDung"));
                String dateTime[] = object1.getString("ngayDang").split(" ");
                tinTuc.setNgayDang(dateTime[0].split("-"));
                tinTuc.setGioDang(dateTime[1].split(":"));

                dsTinTuc.add(tinTuc);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return dsTinTuc;
    }

    public boolean capNhatSanPhamYeuThich(String ham, int idKhachHang, int idSanPham){
        boolean b = false;
        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = ManHinhTrangChuActivity.SERVER_NAME_KHACHHANG;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham", ham);

        HashMap<String,String> hsIdKhachHang = new HashMap<>();
        hsIdKhachHang.put("idKhachHang", String.valueOf(idKhachHang));

        HashMap<String,String> hsIdSanPham = new HashMap<>();
        hsIdSanPham.put("idSanPham", String.valueOf(idSanPham));

        attrs.add(hsHam);
        attrs.add(hsIdKhachHang);
        attrs.add(hsIdSanPham);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        //end phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            JSONObject object = new JSONObject(dataJSON);
            int response = object.getInt("response");
            if (response == 1) b = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return b;
    }

    public boolean capNhatDiaChi(String ham, int idKhachHang, String diaChiCu, String diaChi, int macDinh){
        boolean b = false;
        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = ManHinhTrangChuActivity.SERVER_NAME_KHACHHANG;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham", ham);

        HashMap<String,String> hsIdKhachHang = new HashMap<>();
        hsIdKhachHang.put("idKhachHang", String.valueOf(idKhachHang));

        HashMap<String,String> hsDiaChiCu = new HashMap<>();
        hsDiaChiCu.put("diaChiCu", diaChiCu);

        HashMap<String,String> hsDiaChi = new HashMap<>();
        hsDiaChi.put("diaChi", diaChi);

        HashMap<String,String> hsMacDinh = new HashMap<>();
        hsMacDinh.put("macDinh", String.valueOf(macDinh));

        attrs.add(hsHam);
        attrs.add(hsIdKhachHang);
        attrs.add(hsDiaChiCu);
        attrs.add(hsDiaChi);
        attrs.add(hsMacDinh);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        //end phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            JSONObject object = new JSONObject(dataJSON);
            int response = object.getInt("response");
            if (response == 1) b = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return b;
    }

    public boolean doiMatKhau(String ham, int idKhachHang, String matKhauMoi){
        boolean b = false;
        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = ManHinhTrangChuActivity.SERVER_NAME_KHACHHANG;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham", ham);

        HashMap<String,String> hsIdKhachHang = new HashMap<>();
        hsIdKhachHang.put("idKhachHang", String.valueOf(idKhachHang));

        HashMap<String,String> hsMatKhauMoi = new HashMap<>();
        hsMatKhauMoi.put("matKhau", matKhauMoi);

        attrs.add(hsHam);
        attrs.add(hsIdKhachHang);
        attrs.add(hsMatKhauMoi);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        //end phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            JSONObject object = new JSONObject(dataJSON);
            int response = object.getInt("response");
            if (response == 1) b = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return b;
    }

    public void capNhatThongTinKhachHang(String ham, int idKhachHang, String name, String image,
                                         String nameAnhBia, String imageAnhBia, String moTa){
        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = ManHinhTrangChuActivity.SERVER_NAME_KHACHHANG;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham", ham);
        attrs.add(hsHam);

        HashMap<String,String> hsIdKhachHang = new HashMap<>();
        hsIdKhachHang.put("idKhachHang", String.valueOf(idKhachHang));
        attrs.add(hsIdKhachHang);

        if(!name.equals("")){
            HashMap<String,String> hsName = new HashMap<>();
            hsName.put("name", name);
            attrs.add(hsName);

            HashMap<String,String> hsImage = new HashMap<>();
            hsImage.put("image", image);
            attrs.add(hsImage);
        }

        if(!nameAnhBia.equals("")){
            HashMap<String,String> hsNameAnhBia = new HashMap<>();
            hsNameAnhBia.put("nameAnhBia", nameAnhBia);
            attrs.add(hsNameAnhBia);

            HashMap<String,String> hsImageAnhBia = new HashMap<>();
            hsImageAnhBia.put("imageAnhBia", imageAnhBia);
            attrs.add(hsImageAnhBia);
        }

        if(!moTa.equals("")){
            HashMap<String,String> hsMota = new HashMap<>();
            hsMota.put("moTa", moTa);
            attrs.add(hsMota);
        }

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        //end phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            Log.d("dataJSON", dataJSON);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public  KhachHang layThongTinKhachHang(String ham, TaiKhoan taiKhoan){
        KhachHang khachHang = new KhachHang();
        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = ManHinhTrangChuActivity.SERVER_NAME_KHACHHANG;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham", ham);

        HashMap<String,String> hsSoDT = new HashMap<>();
        hsSoDT.put("soDT", taiKhoan.getSoDT());
        Log.d("soDT", taiKhoan.getSoDT());

        attrs.add(hsHam);
        attrs.add(hsSoDT);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        //end phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArray = jsonObject.getJSONArray("khachhang");
            JSONObject objectKhachHang = jsonArray.getJSONObject(0);
            khachHang.setIdKhachHang(objectKhachHang.getInt("idKhachHang"));
            khachHang.setTenKhachHang(objectKhachHang.getString("tenKhachHang"));
            khachHang.setAnhInfoKH(objectKhachHang.getString("anhInfoKH"));
            khachHang.setAnhBia(objectKhachHang.getString("anhBia"));
            khachHang.setDiemTinCay(objectKhachHang.getInt("diemTinCay"));
            khachHang.setMoTa(objectKhachHang.getString("moTa"));
            khachHang.setThoiGianOnline(objectKhachHang.getString("thoiGianOnline"));
            khachHang.setThoiGianOffline(objectKhachHang.getString("thoiGianOffline"));
            khachHang.setTaiKhoan(taiKhoan);

            List<DiaChi> dsDiaChi = new ArrayList<>();
            JSONArray arrayDiaChi = objectKhachHang.getJSONArray("diaChi");
            for (int i = 0; i < arrayDiaChi.length(); i++){
                JSONObject objectDiaChi = arrayDiaChi.getJSONObject(i);
                DiaChi diaChi = new DiaChi();
                diaChi.setIdDiaChi(objectDiaChi.getInt("idDiaChi"));
                diaChi.setDiaChi(objectDiaChi.getString("diaChi"));
                diaChi.setTrangThai(objectDiaChi.getInt("macDinh"));

                dsDiaChi.add(diaChi);
            }

            khachHang.setDiaChi(dsDiaChi.get(0).getDiaChi());
            khachHang.setDsDiaChi(dsDiaChi);

            List<SanPham> dsSanPhamBan = layDSSanPhamBan("layDSSanPhamBan", khachHang);
            khachHang.setDsSanPham(dsSanPhamBan);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return khachHang;
    }

    public List<SanPham> layDSSanPhamBan(String ham, KhachHang khachHang){
        List<SanPham> dsSanPham = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = ManHinhTrangChuActivity.SERVER_NAME_KHACHHANG;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham", ham);

        HashMap<String,String> hsIdKhachHang = new HashMap<>();
        hsIdKhachHang.put("idKhachHang", String.valueOf(khachHang.getIdKhachHang()));

        HashMap<String,String> hsNoiBan = new HashMap<>();
        hsNoiBan.put("noiBan", khachHang.getDiaChi());

        attrs.add(hsHam);
        attrs.add(hsIdKhachHang);
        attrs.add(hsNoiBan);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        //end phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachSanPham = jsonObject.getJSONArray("danhsachsanpham");
            int count = jsonArrayDanhSachSanPham.length();
            for(int i = 0; i < count; i++){
                SanPham sanpham = new SanPham();
                JSONObject object = jsonArrayDanhSachSanPham.getJSONObject(i);
                sanpham.setIdSanPham(object.getInt("idSanPham"));

                sanpham.setGia(object.getInt("giaChuan"));
                sanpham.setSoLuotThich(object.getInt("soLuotThich"));
                sanpham.setSoBinhLuan(object.getInt("soBinhLuan"));
                sanpham.setTenSanPham(object.getString("tenSanPham"));
                sanpham.setMoTa(object.getString("moTa"));
                sanpham.setHinhLon(object.getString("hinhLon"));
                sanpham.setHinhNho(object.getString("hinhNho"));
                sanpham.setNoiBan(object.getString("noiBan"));
                sanpham.setKhachHang(khachHang);
                ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                List<DanhMuc> danhMucList = new ArrayList<>();
                chiTietSanPham.setKhoiLuong(object.getString("khoiLuong"));
                chiTietSanPham.setKichThuoc(object.getString("kichThuoc"));
                chiTietSanPham.setTrangThai(object.getString("trangThai"));

                JSONArray jsonArrayLoaiSP = object.getJSONArray("loaiSP");
                for (int j = 0; j < jsonArrayLoaiSP.length(); j++){
                    JSONObject jsonObject1 = jsonArrayLoaiSP.getJSONObject(j);
                    DanhMuc danhMuc = new DanhMuc();
                    danhMuc.setIdDanhMuc(jsonObject1.getInt("idLoaiSP"));
                    danhMuc.setTenDanhMuc(jsonObject1.getString("tenLoaiSP"));
                    danhMuc.setIdDanhMucCha(jsonObject1.getInt("idLoaiSPCha"));

                    danhMucList.add(danhMuc);
                }

                chiTietSanPham.setDanhMucList(danhMucList);
                sanpham.setChiTietSanPham(chiTietSanPham);
                dsSanPham.add(sanpham);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dsSanPham;
    }

    public List<SanPham> layDSSanPhamYeuThich(String ham , int idKhachHang, int limit){
        List<SanPham> dsSanPham = new ArrayList<>();
        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = ManHinhTrangChuActivity.SERVER_NAME_KHACHHANG;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham", ham);

        HashMap<String,String> hsIdKhachHang = new HashMap<>();
        hsIdKhachHang.put("idKhachHang", String.valueOf(idKhachHang));

        HashMap<String,String> hsLimit = new HashMap<>();
        hsLimit.put("limit", String.valueOf(limit));

        attrs.add(hsHam);
        attrs.add(hsIdKhachHang);
        attrs.add(hsLimit);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        //end phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArray = jsonObject.getJSONArray("danhsachsanpham");
            int count = jsonArray.length();
            for(int i = 0; i < count; i++){
                SanPham sanpham = new SanPham();
                JSONObject object = jsonArray.getJSONObject(i);
                sanpham.setIdSanPham(object.getInt("idSanPham"));

                KhachHang khachHang = new KhachHang();
                JSONArray arrayKhachHang = object.getJSONArray("thongTinNguoiBan");
                JSONObject objectKhachHang = arrayKhachHang.getJSONObject(0);
                khachHang.setIdKhachHang(objectKhachHang.getInt("idKhachHang"));
                khachHang.setTenKhachHang(objectKhachHang.getString("tenKhachHang"));
                khachHang.setAnhInfoKH(objectKhachHang.getString("anhInfoKH"));
                khachHang.setDiemTinCay(objectKhachHang.getInt("diemTinCay"));
                khachHang.setSoSanPham(objectKhachHang.getInt("soSanPham"));
                sanpham.setKhachHang(khachHang);

                sanpham.setGia(object.getInt("giaChuan"));
                sanpham.setSoLuotThich(object.getInt("soLuotThich"));
                sanpham.setSoBinhLuan(object.getInt("soBinhLuan"));
                sanpham.setTenSanPham(object.getString("tenSanPham"));
                sanpham.setMoTa(object.getString("moTa"));
                sanpham.setHinhLon(object.getString("hinhLon"));
                sanpham.setHinhNho(object.getString("hinhNho"));
                sanpham.setNoiBan(object.getString("noiBan"));
                sanpham.setYeuThich(true);
                ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                List<DanhMuc> danhMucList = new ArrayList<>();
                chiTietSanPham.setKhoiLuong(object.getString("khoiLuong"));
                chiTietSanPham.setKichThuoc(object.getString("kichThuoc"));
                chiTietSanPham.setTrangThai(object.getString("trangThai"));

                JSONArray jsonArrayLoaiSP = object.getJSONArray("loaiSP");
                for (int j = 0; j < jsonArrayLoaiSP.length(); j++){
                    JSONObject jsonObject1 = jsonArrayLoaiSP.getJSONObject(j);
                    DanhMuc danhMuc = new DanhMuc();
                    danhMuc.setIdDanhMuc(jsonObject1.getInt("idLoaiSP"));
                    danhMuc.setTenDanhMuc(jsonObject1.getString("tenLoaiSP"));
                    danhMuc.setIdDanhMucCha(jsonObject1.getInt("idLoaiSPCha"));

                    danhMucList.add(danhMuc);
                }

                chiTietSanPham.setDanhMucList(danhMucList);
                sanpham.setChiTietSanPham(chiTietSanPham);
                dsSanPham.add(sanpham);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return dsSanPham;
    }
}

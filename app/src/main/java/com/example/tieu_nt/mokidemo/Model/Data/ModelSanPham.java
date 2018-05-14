package com.example.tieu_nt.mokidemo.Model.Data;

import com.example.tieu_nt.mokidemo.ConnectInternet.DownloadJSON;
import com.example.tieu_nt.mokidemo.Model.BinhLuan;
import com.example.tieu_nt.mokidemo.Model.ChiTietSanPham;
import com.example.tieu_nt.mokidemo.Model.DanhMuc;
import com.example.tieu_nt.mokidemo.Model.KhachHang;
import com.example.tieu_nt.mokidemo.Model.SanPham;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.ManHinhTrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by tieu_nt on 4/3/2018.
 */

public class ModelSanPham {
    private static ModelSanPham modelSanPham;

    private ModelSanPham(){

    }

    public static synchronized ModelSanPham getInstance(){
        if(modelSanPham == null) modelSanPham = new ModelSanPham();
        return modelSanPham;
    }

    public List<SanPham> layDanhSachSanPhamTimKiem(String tenSP, int idLoaiSP, int giaThap, int giaCao,
                                                   String trangThai, int idKhachHang, int limit){
        List<SanPham> dsSanPham = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = ManHinhTrangChuActivity.SERVER_NAME_SANPHAM;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham", "layDSSanPhamTimKiem");
        attrs.add(hsHam);

        if(!tenSP.equals("")){
            HashMap<String,String> hsTenSP = new HashMap<>();
            hsTenSP.put("tenSP", tenSP);
            attrs.add(hsTenSP);
        }

        if(idLoaiSP > 0){
            HashMap<String,String> hsIdDanhMuc = new HashMap<>();
            hsIdDanhMuc.put("idLoaiSP", String.valueOf(idLoaiSP));
            attrs.add(hsIdDanhMuc);
        }

        if(giaThap > 0 && giaCao > 0){
            HashMap<String,String> hsGiaThap = new HashMap<>();
            hsGiaThap.put("giaThap", String.valueOf(giaThap));
            attrs.add(hsGiaThap);

            HashMap<String,String> hsGiaCao = new HashMap<>();
            hsGiaCao.put("giaCao", String.valueOf(giaCao));
            attrs.add(hsGiaCao);
        }

        if(!trangThai.equals("")){
            HashMap<String,String> hsTrangThai = new HashMap<>();
            hsTrangThai.put("trangThai", trangThai);
            attrs.add(hsTrangThai);
        }

        if (idKhachHang != 0){
            HashMap<String,String> hsIdKhachHang = new HashMap<>();
            hsIdKhachHang.put("idKhachHang", String.valueOf(idKhachHang));
            attrs.add(hsIdKhachHang);
        }

        HashMap<String,String> hsLimit = new HashMap<>();
        hsLimit.put("limit", String.valueOf(limit));
        attrs.add(hsLimit);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
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
                if(idKhachHang != 0){
                    sanpham.setYeuThich(object.getBoolean("yeuThich"));
                }
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

    public List<DanhMuc> layDSDanhMucCon(int idDanhMuc){
        List<DanhMuc> danhMucs = new ArrayList<>();
        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = ManHinhTrangChuActivity.SERVER_NAME_SANPHAM;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham", "layDSDanhMucCon");

        HashMap<String,String> hsIdDanhMuc = new HashMap<>();
        hsIdDanhMuc.put("idLoaiSPCha", String.valueOf(idDanhMuc));

        attrs.add(hsHam);
        attrs.add(hsIdDanhMuc);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArray = jsonObject.getJSONArray("danhmuc");
            int length = jsonArray.length();
            for (int i = 0; i < length; i++){
                JSONObject object = jsonArray.getJSONObject(i);
                DanhMuc danhMuc = new DanhMuc();
                danhMuc.setIdDanhMucCha(idDanhMuc);
                danhMuc.setIdDanhMuc(object.getInt("idLoaiSP"));
                danhMuc.setTenDanhMuc(object.getString("tenLoaiSP"));
                danhMuc.setSoDanhMucCon(object.getInt("soLoaiCon"));

                danhMucs.add(danhMuc);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return danhMucs;
    }

    public List<BinhLuan> layDanhSachBinhLuan(int idSanPham){
        List<BinhLuan> dsBinhLuan = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = ManHinhTrangChuActivity.SERVER_NAME_SANPHAM;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham", "layDanhSachBinhLuan");

        HashMap<String,String> hsIdSanPham = new HashMap<>();
        hsIdSanPham.put("idSanPham", String.valueOf(idSanPham));

        attrs.add(hsHam);
        attrs.add(hsIdSanPham);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArray = jsonObject.getJSONArray("binhluan");
            int length = jsonArray.length();
            for (int i = 0; i < length; i++){
                JSONObject object = jsonArray.getJSONObject(i);
                BinhLuan binhLuan = new BinhLuan();
                binhLuan.setIdKhachHang(object.getInt("idKhachHang"));
                binhLuan.setTenKhachHang(object.getString("tenKhachHang"));
                binhLuan.setHinhKhachHang(object.getString("anhInfoKH"));
                binhLuan.setNoiDungBL(object.getString("noiDung"));
                binhLuan.setThoiGianBL(object.getString("thoiGian"));

                dsBinhLuan.add(binhLuan);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return dsBinhLuan;
    }

    public List<SanPham> layDanhSachSanPham(String ham, int idLoaiSP, int limit, int idKhachHang, String giaTri, String sapXep){
        List<SanPham> dsSanPham = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = ManHinhTrangChuActivity.SERVER_NAME_SANPHAM;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham", ham);

        HashMap<String,String> hsIdLoaiSP = new HashMap<>();
        hsIdLoaiSP.put("idLoaiSP", String.valueOf(idLoaiSP));

        HashMap<String,String> hsLimit = new HashMap<>();
        hsLimit.put("limit", String.valueOf(limit));

        attrs.add(hsHam);
        attrs.add(hsIdLoaiSP);
        attrs.add(hsLimit);

        if (!giaTri.equals("")){
            HashMap<String,String> hsGiaTri = new HashMap<>();
            hsGiaTri.put("giaTri", giaTri);

            HashMap<String,String> hsSapXep = new HashMap<>();
            hsSapXep.put("sapXep", sapXep);

            attrs.add(hsGiaTri);
            attrs.add(hsSapXep);
        }

        if (idKhachHang != 0){
            HashMap<String,String> hsIdKhachHang = new HashMap<>();
            hsIdKhachHang.put("idKhachHang", String.valueOf(idKhachHang));
            attrs.add(hsIdKhachHang);
        }

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
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
                if(idKhachHang != 0){
                    sanpham.setYeuThich(object.getBoolean("yeuThich"));
                }
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

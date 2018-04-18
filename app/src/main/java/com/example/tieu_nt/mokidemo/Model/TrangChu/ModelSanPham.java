package com.example.tieu_nt.mokidemo.Model.TrangChu;

import com.example.tieu_nt.mokidemo.ConnectInternet.DownloadJSON;
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
    public List<SanPham> layDanhSachSanPham(String ham, int idLoaiSP){
        List<SanPham> dsSanPham = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = ManHinhTrangChuActivity.SERVER_NAME_SANPHAM;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham", ham);

        HashMap<String,String> hsIdLoaiSP = new HashMap<>();
        hsIdLoaiSP.put("idLoaiSP", String.valueOf(idLoaiSP));

        attrs.add(hsHam);
        attrs.add(hsIdLoaiSP);

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
//                sanpham.setIdNguoiBan(object.getInt("idNguoiBan"));

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

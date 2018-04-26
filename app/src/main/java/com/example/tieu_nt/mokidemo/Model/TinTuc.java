package com.example.tieu_nt.mokidemo.Model;

import java.io.Serializable;

/**
 * Created by tieu_nt on 4/11/2018.
 */

public class TinTuc implements Serializable{
    private int idTinTuc;
    private String tieuDe, noiDung;
    String[] ngayDang, gioDang;


    public int getIdTinTuc() {
        return idTinTuc;
    }

    public void setIdTinTuc(int idTinTuc) {
        this.idTinTuc = idTinTuc;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String[] getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(String[] ngayDang) {
        this.ngayDang = ngayDang;
    }

    public String[] getGioDang() {
        return gioDang;
    }

    public void setGioDang(String[] gioDang) {
        this.gioDang = gioDang;
    }
}

package com.example.tieu_nt.mokidemo.Model;

import java.io.Serializable;

/**
 * Created by tieu_nt on 4/14/2018.
 */

public class DanhMuc implements Serializable{
    int idDanhMuc, idDanhMucCha;
    String tenDanhMuc;

    public int getIdDanhMuc() {
        return idDanhMuc;
    }

    public void setIdDanhMuc(int idDanhMuc) {
        this.idDanhMuc = idDanhMuc;
    }

    public int getIdDanhMucCha() {
        return idDanhMucCha;
    }

    public void setIdDanhMucCha(int idDanhMucCha) {
        this.idDanhMucCha = idDanhMucCha;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }
}

package com.example.tieu_nt.mokidemo.View.ManHinhTrangChu;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.tieu_nt.mokidemo.Adapter.AdapterViewPagerSlider;
import com.example.tieu_nt.mokidemo.Model.ChiTietSanPham;
import com.example.tieu_nt.mokidemo.Model.DanhMuc;
import com.example.tieu_nt.mokidemo.Model.KhachHang;
import com.example.tieu_nt.mokidemo.Model.SanPham;
import com.example.tieu_nt.mokidemo.Model.Data.ModelKhachHang;
import com.example.tieu_nt.mokidemo.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.example.tieu_nt.mokidemo.R;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.Fragment.FragmentSliderChiTietSanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tieu_nt on 4/7/2018.
 */

public class HienThiChiTietSanPhamActivity extends AppCompatActivity implements View.OnClickListener,
        ViewChiTietSanPham, ViewPager.OnPageChangeListener, CompoundButton.OnCheckedChangeListener{
    private SanPham sanPham;
    private CircleImageView imgKhachHang;
    private TextView tvTenSP, tvMoTaSP, tvXemThem, tvGiaSP, tvNoiBan, tvTrangThai, tvKichThuoc,
            tvKhoiLuong, tvSoLuotThich, tvSoBinhLuan, tvDiem, tvSoSP, tvTenKhachHang;
    private Button btnMua, btnBinhLuan;
    private ImageButton imgBack;
    private ToggleButton tgLike;
    private ViewPager viewPagerSlider;
    private LinearLayout layoutDots, linearNhanHieu, linearDanhMuc, linearMua;
    private RelativeLayout relaXemThem, relaTrangThai, relaNhanHieu, relaKichThuoc, relaKhoiLuong;
    private List<Fragment> fragmentList = new ArrayList<>();
    private TextView[] tvDots;
    private PresenterLogicChiTietSanPham presenterLogicChiTietSanPham;
    private boolean xemThem = true;
    private Button[] danhMuc;
    private ModelKhachHang modelKhachHang;
    private int idSanPham;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitietsanpham);
        anhXa();
        Intent intent = getIntent();
        sanPham = (SanPham) intent.getSerializableExtra("sanPham");
        modelKhachHang = ModelKhachHang.getInstance();
        presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham(this);
        presenterLogicChiTietSanPham.layDanhSachHinhSP(sanPham);
        if(ManHinhTrangChuActivity.khachHang != null && ManHinhTrangChuActivity.khachHang.getIdKhachHang() == sanPham.getKhachHang().getIdKhachHang()){
            linearMua.setVisibility(View.GONE);
        }
        setActions();
    }

    private void anhXa(){
        imgKhachHang = (CircleImageView) findViewById(R.id.imgKhachHang);
        tvDiem = (TextView) findViewById(R.id.tvDiem);
        tvSoSP = (TextView) findViewById(R.id.tvTongSP);
        tvTenKhachHang = (TextView) findViewById(R.id.tvTenKhachHang);
        tvTenSP = (TextView) findViewById(R.id.tvTenSP);
        tvMoTaSP = (TextView) findViewById(R.id.tvMoTaSanPham);
        tvXemThem = (TextView) findViewById(R.id.tvXemThem);
        tvGiaSP = (TextView) findViewById(R.id.tvGiaSP);
        tvNoiBan = (TextView) findViewById(R.id.tvNoiBan);
        tvTrangThai = (TextView) findViewById(R.id.tvTrangThai);
        tvKichThuoc = (TextView) findViewById(R.id.tvKichThuoc);
        tvKhoiLuong = (TextView) findViewById(R.id.tvKhoiLuong);
        tvSoLuotThich = (TextView) findViewById(R.id.tvSoLuotThich);
        tvSoBinhLuan = (TextView) findViewById(R.id.tvSoBinhLuan);
        tgLike = (ToggleButton) findViewById(R.id.tgLike);
        btnMua = (Button) findViewById(R.id.btnMua);
        btnBinhLuan = (Button) findViewById(R.id.btnBinhLuan);
        imgBack = (ImageButton) findViewById(R.id.imgBack);
        viewPagerSlider = (ViewPager) findViewById(R.id.viewPagerSlider);
        layoutDots = (LinearLayout) findViewById(R.id.layoutDots);
        linearDanhMuc = (LinearLayout) findViewById(R.id.linearDanhMuc);
        linearNhanHieu = (LinearLayout) findViewById(R.id.linearNhanHieu);
        linearMua = (LinearLayout) findViewById(R.id.linearMua);
        relaXemThem = (RelativeLayout) findViewById(R.id.relaXemThem);
        relaTrangThai = (RelativeLayout) findViewById(R.id.relaTrangThai);
        relaNhanHieu = (RelativeLayout) findViewById(R.id.relaNhanHieu);
        relaKichThuoc = (RelativeLayout) findViewById(R.id.relaKichThuoc);
        relaKhoiLuong = (RelativeLayout) findViewById(R.id.relaKhoiLuong);
    }

    private void setActions(){
        btnMua.setOnClickListener(this);
        btnBinhLuan.setOnClickListener(this);
        viewPagerSlider.setOnPageChangeListener(this);
        imgBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.imgBack:
                finish();
                break;
            case R.id.btnBinhLuan:
                Intent iBinhLuan = new Intent(HienThiChiTietSanPhamActivity.this, BinhLuanActivity.class);
                iBinhLuan.putExtra("idSanPham", idSanPham);
                startActivity(iBinhLuan);
                break;
            case R.id.btnMua:
                if(ManHinhTrangChuActivity.idKhachHang == 0){
                    Toast.makeText(this, "Bạn phải đăng nhập để sử dụng tính năng này", Toast.LENGTH_SHORT).show();
                }else{
                    if (presenterLogicChiTietSanPham.muaSanPham(ManHinhTrangChuActivity.idKhachHang, sanPham.getIdSanPham())){
                        Toast.makeText(this, "Sản phẩm đã được đặt, vui lòng đợi thông báo từ hệ thống", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this, "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    @Override
    public void hienThiSliderSP(String[] linkHinhSP) {
        for (int i = 0; i < linkHinhSP.length; i++){
            FragmentSliderChiTietSanPham fragmentSliderChiTietSanPham = new FragmentSliderChiTietSanPham();
            Bundle bundle = new Bundle();
            bundle.putString("linkHinh", linkHinhSP[i]);
            fragmentSliderChiTietSanPham.setArguments(bundle);

            fragmentList.add(fragmentSliderChiTietSanPham);
        }
        AdapterViewPagerSlider adapterViewPagerSlider = new AdapterViewPagerSlider(getSupportFragmentManager(), fragmentList);
        viewPagerSlider.setAdapter(adapterViewPagerSlider);
        adapterViewPagerSlider.notifyDataSetChanged();

        themDotSlider(0);
        viewPagerSlider.setOnPageChangeListener(this);
    }

    @Override
    public void hienThiChiTietSanPham(SanPham sanPham) {
        idSanPham = sanPham.getIdSanPham();
        ChiTietSanPham  chiTietSanPham = sanPham.getChiTietSanPham();

        tvSoLuotThich.setText(String.valueOf(sanPham.getSoLuotThich()));
        tvSoBinhLuan.setText(String.valueOf(sanPham.getSoBinhLuan()));
        tgLike.setChecked(sanPham.isYeuThich());
        tgLike.setOnCheckedChangeListener(this);
        tvTenSP.setText(sanPham.getTenSanPham());
        tvMoTaSP.setText(sanPham.getMoTa());
        tvMoTaSP.post(new Runnable() {
            @Override
            public void run() {
                int line = tvMoTaSP.getLineCount();
                if(line > 2){
                    tvMoTaSP.setMaxLines(2);
                    tvXemThem.setVisibility(View.VISIBLE);
                    tvXemThem.setText("Xem thêm");
                    relaXemThem.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (xemThem){
                                tvMoTaSP.setMaxLines(Integer.MAX_VALUE);
                                xemThem = !xemThem;
                                tvXemThem.setText("Thu lại");
                            }else{
                                tvMoTaSP.setMaxLines(2);
                                xemThem = !xemThem;
                                tvXemThem.setText("Xem thêm");
                            }
                        }
                    });
                }
            }
        });

        KhachHang khachHang = sanPham.getKhachHang();
        Log.d("InfoKH", khachHang.getAnhInfoKH());
        if(!khachHang.getAnhInfoKH().equalsIgnoreCase("null"))
            Picasso.get().load(ManHinhTrangChuActivity.SERVER + khachHang.getAnhInfoKH()).into(imgKhachHang);
        tvTenKhachHang.setText(khachHang.getTenKhachHang());
        tvDiem.setText(String.valueOf(khachHang.getDiemTinCay()));
        tvSoSP.setText(String.valueOf(khachHang.getSoSanPham()));

        final List<DanhMuc> danhMucList = chiTietSanPham.getDanhMucList();
        int size = danhMucList.size();
        danhMuc = new Button[size];
        for(int i = 0; i < size; i++){
            danhMuc[i] = new Button(this);
            danhMuc[i].setTextColor(getIdColor(R.color.colorRed));
            danhMuc[i].setBackgroundResource(R.drawable.custom_button_bottom_login);
            danhMuc[i].setAllCaps(false);
            danhMuc[i].setTextSize(12);
            danhMuc[i].setText(danhMucList.get(i).getTenDanhMuc());

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 50);
            layoutParams.setMargins(0, 0, 0, 5);
            danhMuc[i].setLayoutParams(layoutParams);
            danhMuc[i].setPadding(10, 0, 10, 0);
            final int j = i;
            danhMuc[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("TNT", " aadsj ");
                    Intent iDSSPTheoLoai = new Intent(HienThiChiTietSanPhamActivity.this, HienThiSanPhamTheoLoaiActivity.class);
                    iDSSPTheoLoai.putExtra("danhMuc", danhMucList.get(j));
                    startActivity(iDSSPTheoLoai);
                }
            });
            linearDanhMuc.addView(danhMuc[i]);
        }

        if(chiTietSanPham.getKhoiLuong().equals("null")) relaKhoiLuong.setVisibility(View.GONE);
        else tvKhoiLuong.setText(chiTietSanPham.getKhoiLuong());

        if (chiTietSanPham.getKichThuoc().equals("null")) relaKichThuoc.setVisibility(View.GONE);
        else tvKichThuoc.setText(chiTietSanPham.getKichThuoc());

        if(chiTietSanPham.getTrangThai().equals("null")) relaTrangThai.setVisibility(View.GONE);
        else tvTrangThai.setText(chiTietSanPham.getTrangThai());

        tvNoiBan.setText(sanPham.getNoiBan());

        if(sanPham.getSoBinhLuan() > 0){
            btnBinhLuan.setText("Xem và viết bình luận");
        }

        if(sanPham.getGia() == 0){
            tvGiaSP.setText("Miễn phí");
            btnMua.setText("Nhận");
            btnMua.setBackgroundColor(getIdColor(R.color.colorLightGreen));
        }else {
            NumberFormat numberFormat = new DecimalFormat("###,###");
            String gia = numberFormat.format(sanPham.getGia()).toString();
            tvGiaSP.setText(gia + " VNĐ");
        }

    }

    private void themDotSlider(int viTriHienTai){
        tvDots = new TextView[fragmentList.size()];

        layoutDots.removeAllViews();
        for (int i = 0; i < tvDots.length; i++){
            tvDots[i] = new TextView(this);
            tvDots[i].setText(Html.fromHtml("&#8226"));
            tvDots[i].setTextSize(30);
            tvDots[i].setTextColor(getIdColor(R.color.colorWhite));

            layoutDots.addView(tvDots[i]);
        }

        tvDots[viTriHienTai].setTextColor(getIdColor(R.color.colorBlack));
    }

    private int getIdColor(int idcolor){
        int color = 0;
        if(Build.VERSION.SDK_INT > 21){
            color = ContextCompat.getColor(this, idcolor);
        }else{
            color = getResources().getColor(idcolor);
        }

        return color;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        for (int i = 0; i < tvDots.length; i++){
            if (i == position) continue;
            tvDots[i].setTextColor(getIdColor(R.color.colorWhite));
        }
        tvDots[position].setTextColor(getIdColor(R.color.colorBlack));
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(ManHinhTrangChuActivity.idKhachHang == 0){
            Toast.makeText(this, "Bạn phải đăng nhập để sử dụng tính năng này", Toast.LENGTH_SHORT).show();
            return;
        }
        if(b) {
            if (presenterLogicChiTietSanPham.themSanPhamYeuThich(ManHinhTrangChuActivity.idKhachHang, sanPham.getIdSanPham())) {
                tvSoLuotThich.setText(String.valueOf(Integer.parseInt(tvSoLuotThich.getText().toString()) + 1));
            }
        }
        else {
            if (presenterLogicChiTietSanPham.xoaSanPhamYeuThich(ManHinhTrangChuActivity.idKhachHang, sanPham.getIdSanPham())) {
                tvSoLuotThich.setText(String.valueOf(Integer.parseInt(tvSoLuotThich.getText().toString()) - 1));
            }
        }
    }
}

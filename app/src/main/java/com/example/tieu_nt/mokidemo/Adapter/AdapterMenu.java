package com.example.tieu_nt.mokidemo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tieu_nt.mokidemo.Model.DrawerItem;
import com.example.tieu_nt.mokidemo.Model.TaiKhoan;
import com.example.tieu_nt.mokidemo.Model.KhachHang;
import com.example.tieu_nt.mokidemo.Presenter.ItemClickListener;
import com.example.tieu_nt.mokidemo.R;
import com.example.tieu_nt.mokidemo.View.DanhSachBan.DanhSachBanActivity;
import com.example.tieu_nt.mokidemo.View.DanhSachMua.DanhSachMuaActivity;
import com.example.tieu_nt.mokidemo.View.DanhSachYeuThich.DanhSachYeuThichActivity;
import com.example.tieu_nt.mokidemo.View.GioiThieuMOKI.GioiThieuMOKIActivity;
import com.example.tieu_nt.mokidemo.View.ManHinhDangNhap.ManHinhDangKyActivity;
import com.example.tieu_nt.mokidemo.View.ManHinhDangNhap.ManHinhDangNhapActivity;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.ManHinhTrangChuActivity;
import com.example.tieu_nt.mokidemo.View.ThietLap.ThietLapActivity;
import com.example.tieu_nt.mokidemo.View.TinTuc.TinTucActivity;
import com.example.tieu_nt.mokidemo.View.TrungTamHoTro.TrungTamHoTroActivity;
import com.example.tieu_nt.mokidemo.View.TuThien.TuThienActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tieu_nt on 3/12/2018.
 */

public class AdapterMenu extends RecyclerView.Adapter<AdapterMenu.RecyclerViewHolder>{
    private List<DrawerItem> dsItems = new ArrayList<>();
    private Context context;
    private int position;
    private DrawerLayout drawerLayout;
    private KhachHang khachHang;

    private String[] tenItems = {"Trang chủ", "Tin tức", "Danh sách yêu thích", "Danh sách bán", "Danh sách mua",
            "Từ thiện", "Thiết lập", "Trung tâm hỗ trợ", "Giới thiệu MOKI", "Đăng xuất"};
    private int[] hinhItems = {R.drawable.home, R.drawable.newspaper, R.drawable.like, R.drawable.clipboards,
            R.drawable.shopping_cart, R.drawable.charity, R.drawable.settings, R.drawable.mail,
            R.drawable.info, R.drawable.logout};

    public AdapterMenu(Context context, int position, DrawerLayout drawerLayout, KhachHang khachHang){
        this.context = context;
        this.position = position;
        this.drawerLayout = drawerLayout;
        this.khachHang = khachHang;

        int length = tenItems.length;
        if (khachHang != null) tenItems[length - 1] = "Đăng xuất";
        else tenItems[length - 1] = "Đăng nhập";

        for (int i = 0; i < length; i++){
            dsItems.add(new DrawerItem(hinhItems[i], tenItems[i]));
        }
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_nav_layout, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        final int viTri = this.position;
        if (viTri == position){
            holder.textView.setTextColor(context.getResources().getColor(R.color.colorRed));
            holder.imgNext.setImageResource(R.drawable.next);
        }
        holder.textView.setText(dsItems.get(position).getTenItem());
        holder.imgItem.setImageResource(dsItems.get(position).getIcon());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(viTri == position){
                    drawerLayout.closeDrawers();
                }else{
                    switch (position){
                        case 0:
                            //trang chủ
                            Intent iTrangChu = new Intent(context, ManHinhTrangChuActivity.class);
                            iTrangChu.putExtra("khachHang", khachHang);
                            context.startActivity(iTrangChu);
                            break;
                        case 1:
                            //Tin tức
                            Intent iTinTuc = new Intent(context, TinTucActivity.class);
                            iTinTuc.putExtra("khachHang", khachHang);
                            context.startActivity(iTinTuc);
                            break;
                        case 2:
                            //Danh sách yêu thích
                            Intent iDSYeuThich = new Intent(context, DanhSachYeuThichActivity.class);
                            iDSYeuThich.putExtra("khachHang", khachHang);
                            context.startActivity(iDSYeuThich);
                            break;
                        case 3:
                            //Danh sách bán
                            Intent iDSBan = new Intent(context, DanhSachBanActivity.class);
                            iDSBan.putExtra("khachHang", khachHang);
                            context.startActivity(iDSBan);
                            break;
                        case 4:
                            //Danh sách mua
                            Intent iDSMua = new Intent(context, DanhSachMuaActivity.class);
                            iDSMua.putExtra("khachHang", khachHang);
                            context.startActivity(iDSMua);
                            break;
                        case 5:
                            //Từ thiện
                            Intent iTuThien = new Intent(context, TuThienActivity.class);
                            iTuThien.putExtra("khachHang", khachHang);
                            context.startActivity(iTuThien);
                            break;
                        case 6:
                            //Thiết lập
                            Intent iThietLap = new Intent(context, ThietLapActivity.class);
                            iThietLap.putExtra("khachHang", khachHang);
                            context.startActivity(iThietLap);
                            break;
                        case 7:
                            //Trung tâm hỗ trợ
                            Intent iTrungTamHoTro = new Intent(context, TrungTamHoTroActivity.class);
                            iTrungTamHoTro.putExtra("khachHang", khachHang);
                            context.startActivity(iTrungTamHoTro);
                            break;
                        case 8:
                            //Giới thiệu MOKI
                            Intent iGioiThieu = new Intent(context, GioiThieuMOKIActivity.class);
                            iGioiThieu.putExtra("khachHang", khachHang);
                            context.startActivity(iGioiThieu);
                            break;
                        case 9:
                            //Đăng xuất, Đăng nhập
                            if(khachHang != null){
                                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                View view1 = LayoutInflater.from(context).inflate(R.layout.dialog_thongbao_xacnhan, null);
                                Button btnHuy = (Button) view1.findViewById(R.id.btnHuy);
                                Button btnDongY = (Button) view1.findViewById(R.id.btnDongY);

                                builder.setView(view1);
                                final AlertDialog alertDialog = builder.create();
                                alertDialog.show();

                                btnHuy.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        alertDialog.dismiss();
                                    }
                                });

                                btnDongY.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent iDangNhap = new Intent(context, ManHinhDangNhapActivity.class);
                                        iDangNhap.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        context.startActivity(iDangNhap);
                                    }
                                });
                            }else{
                                Intent iDangNhap = new Intent(context, ManHinhDangNhapActivity.class);
                                iDangNhap.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                context.startActivity(iDangNhap);
                            }
                            break;
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dsItems.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        TextView textView;
        ImageView imgItem, imgNext;
        private ItemClickListener itemClickListener;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvTenItem);
            imgItem = itemView.findViewById(R.id.imgItem);
            imgNext = itemView.findViewById(R.id.imgNext);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(), true);
            return true;
        }
    }
}

package com.example.tieu_nt.mokidemo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tieu_nt.mokidemo.Model.DrawerItem;
import com.example.tieu_nt.mokidemo.Presenter.ItemClickListener;
import com.example.tieu_nt.mokidemo.R;
import com.example.tieu_nt.mokidemo.View.DanhSachBan.DanhSachBanActivity;
import com.example.tieu_nt.mokidemo.View.DanhSachMua.DanhSachMuaActivity;
import com.example.tieu_nt.mokidemo.View.DanhSachYeuThich.DanhSachYeuThichActivity;
import com.example.tieu_nt.mokidemo.View.GioiThieuMOKI.GioiThieuMOKIActivity;
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

    private String[] tenItems = {"Trang chủ", "Tin tức", "Danh sách yêu thích", "Danh sách bán", "Danh sách mua",
            "Từ thiện", "Thiết lập", "Trung tâm hỗ trợ", "Giới thiệu MOKI", "Đăng xuất"};
    private int[] hinhItems = {R.drawable.home, R.drawable.newspaper, R.drawable.like, R.drawable.clipboards,
            R.drawable.shopping_cart, R.drawable.charity, R.drawable.settings, R.drawable.mail,
            R.drawable.info, R.drawable.logout};

    public AdapterMenu(Context context, int position, DrawerLayout drawerLayout){
        this.context = context;
        this.position = position;
        this.drawerLayout = drawerLayout;

        for (int i = 0; i < tenItems.length; i++){
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
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
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
                switch (position){
                    case 0:
                        //trang chủ
                        if (viTri == position){
                            drawerLayout.closeDrawers();
                        }else {
                            Intent iTrangChu = new Intent(context, ManHinhTrangChuActivity.class);
                            context.startActivity(iTrangChu);
                        }
                        break;
                    case 1:
                        //Tin tức
                        if(viTri == position){
                            drawerLayout.closeDrawers();
                        }else {
                            Intent iTinTuc = new Intent(context, TinTucActivity.class);
                            context.startActivity(iTinTuc);
                        }
                        break;
                    case 2:
                        //Danh sách yêu thích
                        if(viTri == position){
                            drawerLayout.closeDrawers();
                        }else {
                            Intent iDSYeuThich = new Intent(context, DanhSachYeuThichActivity.class);
                            context.startActivity(iDSYeuThich);
                        }
                        break;
                    case 3:
                        //Danh sách bán
                        if(viTri == position){
                            drawerLayout.closeDrawers();
                        }else {
                            Intent iDSBan = new Intent(context, DanhSachBanActivity.class);
                            context.startActivity(iDSBan);
                        }
                        break;
                    case 4:
                        //Danh sách mua
                        if(viTri == position){
                            drawerLayout.closeDrawers();
                        }else {
                            Intent iDSMua = new Intent(context, DanhSachMuaActivity.class);
                            context.startActivity(iDSMua);
                        }
                        break;
                    case 5:
                        //Từ thiện
                        if(viTri == position){
                            drawerLayout.closeDrawers();
                        }else {
                            Intent iTuThien = new Intent(context, TuThienActivity.class);
                            context.startActivity(iTuThien);
                        }
                        break;
                    case 6:
                        //Thiết lập
                        if(viTri == position){
                            drawerLayout.closeDrawers();
                        }else {
                            Intent iThietLap = new Intent(context, ThietLapActivity.class);
                            context.startActivity(iThietLap);
                        }
                        break;
                    case 7:
                        //Trung tâm hỗ trợ
                        if(viTri == position){
                            drawerLayout.closeDrawers();
                        }else {
                            Intent iTrungTamHoTro = new Intent(context, TrungTamHoTroActivity.class);
                            context.startActivity(iTrungTamHoTro);
                        }
                        break;
                    case 8:
                        //Giới thiệu MOKI
                        if(viTri == position){
                            drawerLayout.closeDrawers();
                        }else {
                            Intent iGioiThieu = new Intent(context, GioiThieuMOKIActivity.class);
                            context.startActivity(iGioiThieu);
                        }
                        break;
                    case 9:
                        //Đăng xuất, Đăng nhập
                        break;
                }
//                Toast.makeText(context, "Item: " + dsItems.get(position).getTenItem(), Toast.LENGTH_SHORT).show();
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

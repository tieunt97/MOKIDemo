package com.example.tieu_nt.mokidemo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tieu_nt.mokidemo.Model.SanPham;
import com.example.tieu_nt.mokidemo.R;
import com.example.tieu_nt.mokidemo.View.TrangChu.ChiTietSanPhamActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tieu_nt on 4/7/2018.
 */

public class AdapterSanPhamList extends RecyclerView.Adapter<AdapterSanPhamList.ViewHolder>{
    private Context context;
    private List<SanPham> dsSanPham;

    public AdapterSanPhamList(Context context, List<SanPham> dsSanPham) {
        this.context = context;
        this.dsSanPham = dsSanPham;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout_sanpham_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    public void addAll(List<SanPham> dsSanPham){
        int size = this.dsSanPham.size();
        this.dsSanPham.addAll(dsSanPham);
        notifyItemInserted(size - 1);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final SanPham sanPham = dsSanPham.get(position);
        Picasso.get().load(sanPham.getHinhLon()).into(holder.imgSanPham);
        holder.tvTenSanPham.setText(sanPham.getTenSanPham());
        if(sanPham.getGia() == 0){
            holder.tvGiaSP.setText("Miễn phí");
            holder.imgFree.setVisibility(View.VISIBLE);
        }else{
            NumberFormat numberFormat = new DecimalFormat("###,###");
            String gia = numberFormat.format(sanPham.getGia()).toString();
            holder.tvGiaSP.setText(gia + " đ");
        }

        holder.tvMoTaSanPham.setText(sanPham.getMoTa());
        holder.tvThich.setText(String.valueOf(sanPham.getSoLuotThich()));
        holder.tvBinhLuan.setText(String.valueOf(sanPham.getSoBinhLuan()));

        holder.relaNguoiBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.linearSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSanPham = new Intent(context, ChiTietSanPhamActivity.class);
                intentSanPham.putExtra("sanPham", sanPham);
                context.startActivity(intentSanPham);
            }
        });
        holder.relaThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.relaBinhLuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.relateMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return dsSanPham.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView imgInfoShop;
        TextView tvTenShop, tvThoiGian, tvTenSanPham, tvGiaSP, tvMoTaSanPham, tvThich, tvBinhLuan;
        ImageView imgSanPham, imgFree;
        RelativeLayout relaThich, relaBinhLuan, relateMua, relaNguoiBan;
        LinearLayout linearSanPham;

        public ViewHolder(View itemView) {
            super(itemView);
            imgInfoShop = (CircleImageView) itemView.findViewById(R.id.imgInfoShop);
            imgSanPham = (ImageView) itemView.findViewById(R.id.imgSanPham);
            imgFree = (ImageView) itemView.findViewById(R.id.imgFree);
            tvTenShop = (TextView) itemView.findViewById(R.id.tvTenShop);
            tvThoiGian = (TextView) itemView.findViewById(R.id.tvThoiGian);
            tvTenSanPham = (TextView) itemView.findViewById(R.id.tvTenSanPham);
            tvGiaSP = (TextView) itemView.findViewById(R.id.tvGiaSP);
            tvMoTaSanPham = (TextView) itemView.findViewById(R.id.tvMoTaSanPham);
            tvThich = (TextView) itemView.findViewById(R.id.tvThich);
            tvBinhLuan = (TextView) itemView.findViewById(R.id.tvBinhLuan);
            relaThich = (RelativeLayout) itemView.findViewById(R.id.relaThich);
            relaBinhLuan = (RelativeLayout) itemView.findViewById(R.id.relaBinhLuan);
            relateMua = (RelativeLayout) itemView.findViewById(R.id.relaMua);
            relaNguoiBan = (RelativeLayout) itemView.findViewById(R.id.relaNguoiBan);
            linearSanPham = (LinearLayout) itemView.findViewById(R.id.linearLayoutSanPham);
        }
    }
}

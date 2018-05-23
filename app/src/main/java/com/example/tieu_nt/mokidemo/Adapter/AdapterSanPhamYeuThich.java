package com.example.tieu_nt.mokidemo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tieu_nt.mokidemo.Model.SanPham;
import com.example.tieu_nt.mokidemo.R;
import com.example.tieu_nt.mokidemo.View.TrangChu.ChiTietSanPhamActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by tieu_nt on 4/11/2018.
 */

public class AdapterSanPhamYeuThich extends RecyclerView.Adapter<AdapterSanPhamYeuThich.ViewHolder>{
    private Context context;
    private List<SanPham> dsSanPham;

    public AdapterSanPhamYeuThich(Context context, List<SanPham> dsSanPham) {
        this.context = context;
        this.dsSanPham = dsSanPham;
    }

    @Override
    public AdapterSanPhamYeuThich.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_dsyeuthich_sanpham, parent, false);
        AdapterSanPhamYeuThich.ViewHolder viewHolder = new AdapterSanPhamYeuThich.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterSanPhamYeuThich.ViewHolder holder, int position) {
        final SanPham sanPham = dsSanPham.get(position);
        Picasso.get().load(sanPham.getHinhLon()).into(holder.imgHinhSP);
        holder.tvTenSP.setText(sanPham.getTenSanPham());
        if(sanPham.getGia() == 0){
            holder.tvGia.setText("Miễn phí");
        }else {
            NumberFormat numberFormat = new DecimalFormat("###,###");
            String gia = numberFormat.format(sanPham.getGia()).toString();
            holder.tvGia.setText(gia);
        }
        holder.relaSanPhamYeuThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSanPham = new Intent(context, ChiTietSanPhamActivity.class);
                intentSanPham.putExtra("sanPham", sanPham);
                context.startActivity(intentSanPham);
            }
        });
        if (position != dsSanPham.size() -  1){
            holder.view.setVisibility(View.VISIBLE);
        }else{
            holder.view.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return dsSanPham.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenSP, tvGia;
        ImageView imgHinhSP;
        RelativeLayout relaSanPhamYeuThich;
        View view;
        public ViewHolder(View itemView) {
            super(itemView);
            relaSanPhamYeuThich = (RelativeLayout) itemView.findViewById(R.id.relaSanPhamYeuThich);
            tvTenSP = (TextView) itemView.findViewById(R.id.tvTenSP);
            tvGia = (TextView) itemView.findViewById(R.id.tvGiaSP);
            imgHinhSP = (ImageView) itemView.findViewById(R.id.imgHinhSP);
            view = (View) itemView.findViewById(R.id.view);
        }
    }
}

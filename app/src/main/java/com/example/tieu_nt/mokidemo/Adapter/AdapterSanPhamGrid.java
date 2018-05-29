package com.example.tieu_nt.mokidemo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tieu_nt.mokidemo.Model.SanPham;
import com.example.tieu_nt.mokidemo.R;
import com.example.tieu_nt.mokidemo.View.TrangChu.ChiTietSanPhamActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by tieu_nt on 4/3/2018.
 */

public class AdapterSanPhamGrid extends RecyclerView.Adapter<AdapterSanPhamGrid.ViewHolder>{

    private Context context;
    private List<SanPham> dsSanPham;

    public AdapterSanPhamGrid(Context context, List<SanPham> dsSanPham) {
        this.context = context;
        this.dsSanPham = dsSanPham;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout_sanpham, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final SanPham sanPham = dsSanPham.get(position);
        Picasso.get().load(sanPham.getHinhLon()).resize(145, 145).into(holder.imgHinhSP, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {

            }
        });

        Log.d("DUONGDAN", sanPham.getHinhLon());
        String tenSP = sanPham.getTenSanPham();
        if(tenSP.length() > 27){
            tenSP = tenSP.substring(0, 27) + "...";
        }
        holder.tvTenSP.setText(tenSP);
        int height = holder.tvTenSP.getLineHeight();
        holder.tvTenSP.getLayoutParams().height = height + height + 5;
        holder.tvYeuThich.setText(sanPham.getSoLuotThich() + "");
        holder.tvBinhLuan.setText(sanPham.getSoBinhLuan() + "");
        if(sanPham.getGia() == 0){
            holder.imgFree.setVisibility(View.VISIBLE);
            holder.tvGia.setText("Miễn phí");
        }else{
            holder.imgFree.setVisibility(View.GONE);
            int gia = sanPham.getGia()/1000;
            NumberFormat numberFormat = new DecimalFormat("###,###");
            holder.tvGia.setText(numberFormat.format(gia) + " K");
        }

        holder.linearLayoutSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSanPham = new Intent(context, ChiTietSanPhamActivity.class);
                intentSanPham.putExtra("sanPham", sanPham);
                context.startActivity(intentSanPham);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dsSanPham.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout linearLayoutSanPham;
        ImageView imgHinhSP, imgFree;
        TextView tvTenSP, tvYeuThich, tvBinhLuan, tvGia;
        ProgressBar progressBar;
        public ViewHolder(View itemView) {
            super(itemView);
            linearLayoutSanPham = (LinearLayout) itemView.findViewById(R.id.linearLayoutSanPham);
            imgHinhSP = (ImageView) itemView.findViewById(R.id.imgHinhSP);
            tvTenSP = (TextView) itemView.findViewById(R.id.tvTenSanPham);
            tvYeuThich = (TextView) itemView.findViewById(R.id.tvYeuThich);
            tvBinhLuan = (TextView) itemView.findViewById(R.id.tvBinhLuan);
            tvGia = (TextView) itemView.findViewById(R.id.tvGiaSP);
            imgFree = (ImageView) itemView.findViewById(R.id.imgFree);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress_bar_download);
        }
    }
}

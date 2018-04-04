package com.example.tieu_nt.mokidemo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tieu_nt.mokidemo.Model.SanPham;
import com.example.tieu_nt.mokidemo.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by tieu_nt on 4/3/2018.
 */

public class RecyclerViewAdapterSanPham extends RecyclerView.Adapter<RecyclerViewAdapterSanPham.ViewHolder>{

    private Context context;
    private List<SanPham> dsSanPham;

    public RecyclerViewAdapterSanPham(Context context, List<SanPham> dsSanPham) {
        this.context = context;
        this.dsSanPham = dsSanPham;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.o_sanpham_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        SanPham sanPham = dsSanPham.get(position);
        Picasso.get().load(sanPham.getHinhLon()).resize(165, 165).into(holder.imgHinhSP, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {

            }
        });
        holder.tvTenSP.setText(sanPham.getTenSanPham());
        holder.tvYeuThich.setText(sanPham.getSoLuotThich());
        holder.tvBinhLuan.setText(sanPham.getSoBinhLuan());
        holder.tvGia.setText(sanPham.getGia());
    }

    @Override
    public int getItemCount() {
        return dsSanPham.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgHinhSP;
        TextView tvTenSP, tvYeuThich, tvBinhLuan, tvGia;
        ProgressBar progressBar;
        public ViewHolder(View itemView) {
            super(itemView);
            imgHinhSP = (ImageView) itemView.findViewById(R.id.imgHinhSP);
            tvTenSP = (TextView) itemView.findViewById(R.id.tvTenSanPham);
            tvYeuThich = (TextView) itemView.findViewById(R.id.tvYeuThich);
            tvBinhLuan = (TextView) itemView.findViewById(R.id.tvBinhLuan);
            tvGia = (TextView) itemView.findViewById(R.id.tvGiaSP);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress_bar_download);
        }
    }
}

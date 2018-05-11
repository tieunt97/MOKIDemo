package com.example.tieu_nt.mokidemo.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tieu_nt.mokidemo.Model.DanhMuc;
import com.example.tieu_nt.mokidemo.R;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.DanhMucActivity;

import java.util.List;

/**
 * Created by tieu_nt on 5/7/2018.
 */

public class AdapterDanhMuc extends RecyclerView.Adapter<AdapterDanhMuc.ViewHolder>{
    private Context context;
    private List<DanhMuc> dsDanhMuc;
    private int idDanhMucCha;

    public AdapterDanhMuc(Context context, int idDanhMucCha, List<DanhMuc> dsDanhMuc) {
        this.context = context;
        this.idDanhMucCha = idDanhMucCha;
        this.dsDanhMuc = dsDanhMuc;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_item_danhmuc, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final DanhMuc danhMuc = dsDanhMuc.get(position);
        if (position == 0 && idDanhMucCha != 0){
            holder.tvTenLoaiSP.setText("Tất cả");
        }else{
            holder.tvTenLoaiSP.setText(danhMuc.getTenDanhMuc());
        }
        if (idDanhMucCha == 0 &&  danhMuc.getSoDanhMucCon() != 0){
            holder.imgNext.setVisibility(View.VISIBLE);
        }else if (idDanhMucCha != 0 && position == 0){
            holder.imgNext.setVisibility(View.GONE);
        }else if(danhMuc.getSoDanhMucCon() == 0){
            holder.imgNext.setVisibility(View.GONE);
        }else{
            holder.imgNext.setVisibility(View.VISIBLE);
        }

        holder.relaItemDM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(danhMuc.getSoDanhMucCon()  == 0 || (position == 0 && idDanhMucCha != 0)){
                    ((DanhMucActivity) context).setDanhMucTraVe(danhMuc);
                    ((DanhMucActivity) context).finish();
                }else{
                    Intent iDanhMuc = new Intent(context, DanhMucActivity.class);
                    iDanhMuc.putExtra("danhMuc", danhMuc);
                    ((DanhMucActivity) context).startActivityForResult(iDanhMuc, danhMuc.getIdDanhMuc());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dsDanhMuc.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenLoaiSP;
        RelativeLayout relaItemDM;
        ImageView imgNext;

        public ViewHolder(View itemView) {
            super(itemView);
            relaItemDM = (RelativeLayout) itemView.findViewById(R.id.relaItemDanhMuc);
            tvTenLoaiSP = (TextView) itemView.findViewById(R.id.tvTenLoaiSP);
            imgNext = (ImageView) itemView.findViewById(R.id.imgNext);
        }
    }
}

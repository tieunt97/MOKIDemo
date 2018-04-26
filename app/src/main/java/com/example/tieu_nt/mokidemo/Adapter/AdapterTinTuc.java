package com.example.tieu_nt.mokidemo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tieu_nt.mokidemo.Model.TinTuc;
import com.example.tieu_nt.mokidemo.R;
import com.example.tieu_nt.mokidemo.View.TinTuc.ChiTietTinTucActivity;

import java.util.List;

/**
 * Created by tieu_nt on 4/11/2018.
 */

public class AdapterTinTuc extends RecyclerView.Adapter<AdapterTinTuc.ViewHolder>{
    private Context context;
    private List<TinTuc> dsTinTuc;

    public AdapterTinTuc(Context context, List<TinTuc> dsTinTuc) {
        this.context = context;
        this.dsTinTuc = dsTinTuc;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_item_tintuc, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final TinTuc tinTuc = dsTinTuc.get(position);
        String ngayDang = tinTuc.getNgayDang()[2] + "/" + tinTuc.getNgayDang()[1] + "/" + tinTuc.getNgayDang()[0];
        holder.tvNgay.setText(ngayDang);
        String thoiGian = tinTuc.getGioDang()[0] + ":" + tinTuc.getGioDang()[1];
        holder.tvThoiGian.setText(thoiGian);
        holder.tvTinTuc.setText(tinTuc.getTieuDe());
        if(position != dsTinTuc.size() - 1){
            holder.view.setVisibility(View.VISIBLE);
        }else{
            holder.view.setVisibility(View.GONE);
        }
        holder.relaTinTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iChiTietTinTuc = new Intent(context, ChiTietTinTucActivity.class);
                iChiTietTinTuc.putExtra("tinTuc", tinTuc);
                context.startActivity(iChiTietTinTuc);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dsTinTuc.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relaTinTuc;
        TextView tvNgay, tvThoiGian, tvTinTuc;
        View view;
        public ViewHolder(View itemView) {
            super(itemView);
            relaTinTuc = (RelativeLayout) itemView.findViewById(R.id.relaTinTuc);
            tvNgay = (TextView) itemView.findViewById(R.id.tvNgay);
            tvThoiGian = (TextView) itemView.findViewById(R.id.tvThoiGian);
            tvTinTuc = (TextView) itemView.findViewById(R.id.tvTenTinTuc);
            view = (View) itemView.findViewById(R.id.view);
        }
    }
}

package com.example.tieu_nt.mokidemo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tieu_nt.mokidemo.Model.TinTuc;
import com.example.tieu_nt.mokidemo.R;

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
        TinTuc tinTuc = dsTinTuc.get(position);
        holder.tvNgay.setText(tinTuc.getNgayDang());
        holder.tvThoiGian.setText(tinTuc.getThoiGian());
        holder.tvTinTuc.setText(tinTuc.getTenThongBao());
    }

    @Override
    public int getItemCount() {
        return dsTinTuc.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNgay, tvThoiGian, tvTinTuc;
        public ViewHolder(View itemView) {
            super(itemView);
            tvNgay = (TextView) itemView.findViewById(R.id.tvNgay);
            tvThoiGian = (TextView) itemView.findViewById(R.id.tvThoiGian);
            tvTinTuc = (TextView) itemView.findViewById(R.id.tvTenTinTuc);
        }
    }
}

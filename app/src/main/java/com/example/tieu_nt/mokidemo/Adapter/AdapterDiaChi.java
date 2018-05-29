package com.example.tieu_nt.mokidemo.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tieu_nt.mokidemo.View.ThietLap.BottomSheetCapNhatDiaChi;
import com.example.tieu_nt.mokidemo.Model.DiaChi;
import com.example.tieu_nt.mokidemo.R;

import java.util.List;

/**
 * Created by tieu_nt on 4/23/2018.
 */

public class AdapterDiaChi extends RecyclerView.Adapter<AdapterDiaChi.ViewHolder>{
    private Context context;
    private List<DiaChi> dsDiaChi;
    private BottomSheetCapNhatDiaChi bottomSheetCapNhatDiaChi;
    private FragmentActivity fragmentActivity;

    public AdapterDiaChi(Context context, List<DiaChi> dsDiaChi) {
        this.context = context;
        this.fragmentActivity = (FragmentActivity) context;
        this.dsDiaChi = dsDiaChi;
        bottomSheetCapNhatDiaChi = new BottomSheetCapNhatDiaChi();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_item_diachi, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final DiaChi diaChi = dsDiaChi.get(position);
        String diaChiS[] = diaChi.getDiaChi().split("-");
        holder.tvXom.setText(diaChiS[0]);
        holder.tvXaPhuong.setText(diaChiS[1]);
        holder.tvQuanHuyen.setText(diaChiS[2]);
        holder.tvTinhTP.setText(diaChiS[3]);
        if (diaChi.getTrangThai() == 1) holder.tvMacDinh.setVisibility(View.VISIBLE);
        else if(diaChi.getTrangThai() == 0) holder.tvMacDinh.setVisibility(View.GONE);

        holder.imgTuyChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("position", position);
                bundle.putInt("macDinh", diaChi.getTrangThai());
                bottomSheetCapNhatDiaChi.setArguments(bundle);
                bottomSheetCapNhatDiaChi.show(fragmentActivity.getSupportFragmentManager(), "capNhatDiaChi");
            }
        });
    }

    @Override
    public int getItemCount() {
        return dsDiaChi.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvXom, tvXaPhuong, tvQuanHuyen, tvTinhTP, tvMacDinh;
        ImageView imgTuyChon;

        public ViewHolder(View itemView) {
            super(itemView);
            tvXom = (TextView) itemView.findViewById(R.id.tvXom);
            tvXaPhuong = (TextView) itemView.findViewById(R.id.tvXaPhuong);
            tvQuanHuyen = (TextView) itemView.findViewById(R.id.tvQuanHuyen);
            tvTinhTP = (TextView) itemView.findViewById(R.id.tvTinhTP);
            tvMacDinh = (TextView) itemView.findViewById(R.id.tvMacDinh);
            imgTuyChon = (ImageView) itemView.findViewById(R.id.imgTuyChon);
        }
    }
}

package com.example.tieu_nt.mokidemo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tieu_nt.mokidemo.Model.TrangChu.BinhLuan;
import com.example.tieu_nt.mokidemo.R;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.ManHinhTrangChuActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tieu_nt on 4/18/2018.
 */

public class AdapterBinhLuan extends RecyclerView.Adapter<AdapterBinhLuan.ViewHolder>{
    private Context context;
    private List<BinhLuan> dsBinhLuan;


    public AdapterBinhLuan(Context context, List<BinhLuan> dsBinhLuan) {
        this.context = context;
        this.dsBinhLuan = dsBinhLuan;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout_user_binhluan, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BinhLuan binhLuan = dsBinhLuan.get(position);
        Picasso.get().load(ManHinhTrangChuActivity.SERVER + binhLuan.getHinhKhachHang()).into(holder.imgKH);
        holder.tvTenKH.setText(binhLuan.getTenKhachHang());
        holder.tvNoiDung.setText(binhLuan.getNoiDungBL());
        String datetime[] = binhLuan.getThoiGianBL().split(" ");
        String ngayBL[] = datetime[0].split("-");
        String thoiGian[] = datetime[1].split(":");
        holder.tvThoiGian.setText(ngayBL[2] + "/" + ngayBL[1] + "/" + ngayBL[0]
            + " ("+ thoiGian[0] + ":" + thoiGian[1] + ")");
    }

    public void add(BinhLuan binhLuan) {
        dsBinhLuan.add(0, binhLuan);
        notifyItemInserted(0);
    }

    public void addAlL(List<BinhLuan> dsBinhLuan){
        this.dsBinhLuan.addAll(0, dsBinhLuan);
        notifyItemInserted(0);
    }

    @Override
    public int getItemCount() {
        return dsBinhLuan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imgKH;
        TextView tvTenKH, tvNoiDung, tvThoiGian;
        public ViewHolder(View itemView) {
            super(itemView);
            imgKH = (CircleImageView) itemView.findViewById(R.id.imgKhachHang);
            tvTenKH = (TextView) itemView.findViewById(R.id.tvTenKhachHang);
            tvNoiDung = (TextView) itemView.findViewById(R.id.tvNoiDungBinhLuan);
            tvThoiGian = (TextView) itemView.findViewById(R.id.tvThoiGian);
        }
    }
}

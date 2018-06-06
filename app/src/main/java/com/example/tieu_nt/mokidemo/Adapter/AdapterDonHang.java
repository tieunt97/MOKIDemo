package com.example.tieu_nt.mokidemo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tieu_nt.mokidemo.Model.DonHang;
import com.example.tieu_nt.mokidemo.Model.SanPham;
import com.example.tieu_nt.mokidemo.R;
import com.example.tieu_nt.mokidemo.View.TrangChu.LocSanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by tieu_nt on 6/6/2018.
 */

public class AdapterDonHang extends RecyclerView.Adapter<AdapterDonHang.ViewHolder>{
    private Context context;
    private List<DonHang> dsDonHang;
    private NumberFormat numberFormat = new DecimalFormat("###,###");
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy (hh:mm)");


    public AdapterDonHang(Context context, List<DonHang> dsDonHang) {
        this.context = context;
        this.dsDonHang = dsDonHang;
        Log.d("SOLUONG", dsDonHang.size() + "");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout_donhang, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DonHang donHang = dsDonHang.get(position);
        SanPham sanPham = donHang.getSanPham();
        Picasso.get().load(sanPham.getHinhLon()).into(holder.imgHinhSP);
        holder.tvTenSP.setText(sanPham.getTenSanPham());
        String gia = numberFormat.format(sanPham.getGia()).toString() + " đ";
        holder.tvGia.setText(gia);
        holder.tvSDT.setText("SĐT: " + donHang.getSoDT());
        holder.tvDiaChi.setText("Địa chỉ: " + donHang.getDiaChi());
        holder.tvNgayDat.setText("Ngày đặt: " + sdf.format(donHang.getNgayDat()).toString());
        if(donHang.getNgayGiao() != null){
            holder.tvNgayGiao.setText("Ngày giao: " + sdf.format(donHang.getNgayGiao()).toString());
        }
    }

    @Override
    public int getItemCount() {
        return dsDonHang.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinhSP;
        TextView tvTenSP, tvGia, tvSDT, tvDiaChi, tvNgayDat, tvNgayGiao;

        public ViewHolder(View itemView) {
            super(itemView);
            imgHinhSP = itemView.findViewById(R.id.imgHinhSP);
            tvTenSP = itemView.findViewById(R.id.tvTenSanPham);
            tvGia = itemView.findViewById(R.id.tvGiaSP);
            tvSDT = itemView.findViewById(R.id.tvSoDT);
            tvDiaChi = itemView.findViewById(R.id.tvDiaChi);
            tvNgayDat = itemView.findViewById(R.id.tvNgayDat);
            tvNgayGiao = itemView.findViewById(R.id.tvNgayGiao);
        }
    }
}

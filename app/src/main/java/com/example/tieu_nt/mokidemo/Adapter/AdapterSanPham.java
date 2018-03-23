package com.example.tieu_nt.mokidemo.Adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tieu_nt.mokidemo.Model.SanPhamDTO;
import com.example.tieu_nt.mokidemo.R;

import java.util.List;

/**
 * Created by tieu_nt on 3/7/2018.
 */

public class AdapterSanPham extends BaseAdapter {
    private Context context;
    private int layout;
    private List<SanPhamDTO> dsSanPham;

    public AdapterSanPham(Context context, int layout, List<SanPhamDTO> dsSanPham){
        this.context = context;
        this.layout = layout;
        this.dsSanPham = dsSanPham;
    }

    @Override
    public int getCount() {
        return dsSanPham.size();
    }

    @Override
    public Object getItem(int i) {
        return dsSanPham.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        ImageView imgHinh, imgYeuThich, imgComment;
        TextView tvYeuThich, tvComment, tvGia, tvTenSanPham;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, viewGroup, false);

            holder = new ViewHolder();
            holder.imgHinh = view.findViewById(R.id.imgHinh);
            holder.imgYeuThich = view.findViewById(R.id.imgYeuThich);
            holder.imgComment = view.findViewById(R.id.imgComment);
            holder.tvYeuThich = view.findViewById(R.id.tvYeuThich);
            holder.tvComment = view.findViewById(R.id.tvComment);
            holder.tvTenSanPham = view.findViewById(R.id.tvTenSanPham);
            holder.tvGia = view.findViewById(R.id.tvGia);

            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        SanPhamDTO sanPham = dsSanPham.get(i);
//        holder.imgHinh.setImageResource(sanPham.getHinh());
        byte[] img = sanPham.getDsHinhSanPham().get(0);
        holder.imgHinh.setImageBitmap(BitmapFactory.decodeByteArray(img, 0, img.length));
//        holder.imgYeuThich
//        holder.tvYeuThich.setText(sanPham.getSoLuotThich() + "");
//        holder.tvComment.setText(sanPham.getSoBinhLuan() + "");
        holder.tvTenSanPham.setText(sanPham.getTenSanPham());
        holder.tvGia.setText(sanPham.getGiaChuan() + " K");

        return view;
    }
}

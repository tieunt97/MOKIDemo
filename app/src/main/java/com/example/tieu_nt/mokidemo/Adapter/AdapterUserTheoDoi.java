package com.example.tieu_nt.mokidemo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tieu_nt.mokidemo.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tieu_nt on 4/8/2018.
 */

public class AdapterUserTheoDoi extends RecyclerView.Adapter<AdapterUserTheoDoi.ViewHolder>{
    private Context context;
    private List<String> dsUser;

    public AdapterUserTheoDoi(Context context, List<String> dsUser) {
        this.context = context;
        this.dsUser = dsUser;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout_user_theodoi, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String tenUser = dsUser.get(position);
        holder.tvTenUser.setText(tenUser);
    }

    @Override
    public int getItemCount() {
        return dsUser.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView imgInfoUser;
        TextView tvTenUser;
        ImageView imgTheoDoi;

        public ViewHolder(View itemView) {
            super(itemView);
            imgInfoUser = (CircleImageView) itemView.findViewById(R.id.imgInfoShop);
            tvTenUser = (TextView) itemView.findViewById(R.id.tvTenUser);
            imgTheoDoi = (ImageView) itemView.findViewById(R.id.imgTheoDoi);
        }
    }
}

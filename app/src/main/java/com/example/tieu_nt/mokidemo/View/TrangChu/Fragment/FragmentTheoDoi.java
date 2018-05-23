package com.example.tieu_nt.mokidemo.View.TrangChu.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tieu_nt.mokidemo.Adapter.AdapterUserTheoDoi;
import com.example.tieu_nt.mokidemo.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by tieu_nt on 4/8/2018.
 */

public class FragmentTheoDoi extends Fragment {
    private RecyclerView recyclerView;
    private List<String> dsUser = new ArrayList<>();
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_sanpham, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewSanPham);

        dsUser.add("Bùi Thị Diệu");
        dsUser.add("Trần Vân Trang");
        dsUser.add("Mai Thị Hương");

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterUserTheoDoi(getContext(), dsUser);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;
    }
}

package com.example.tieu_nt.mokidemo.View.DangNhapDangKy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import com.example.tieu_nt.mokidemo.Adapter.ExpanableListViewAdapter;
import com.example.tieu_nt.mokidemo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by tieu_nt on 3/22/2018.
 */

public class QuenMatKhauActivity extends AppCompatActivity{
    private ExpandableListView expandableListView;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    private ExpanableListViewAdapter expanableListViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_quenmatkhau);

        addControl();
        expanableListViewAdapter = new ExpanableListViewAdapter(QuenMatKhauActivity.this, listDataHeader, listDataChild);
        expandableListView.setAdapter(expanableListViewAdapter);
    }

    private void addControl() {
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<String, List<String>>();

        listDataHeader.add("MOKI là gì");
        listDataHeader.add("Sự tin cậy của MOKI như thế nào");
        listDataHeader.add("MOKI tiện lợi như thế nào");

        List<String> MOKILaGi = new ArrayList<>();
        MOKILaGi.add("MOKI là ứng dụng giúp các mẹ trao đổi, mua bán đồ, được phát triển bởi Công ty Giải pháp truyền thông và Công nghệ thông tin MQ.");

        List<String> suTinCayMOKI = new ArrayList<>();
        suTinCayMOKI.add("Giống như việc mua hàng thực tế, ban sẽ chỉ mất tiền khi bạn đã nhận hàng và hài lòng nhờ cơ chế kiểm soát đặc biệt của MOKI.");

        List<String> tienLoiMOKI = new ArrayList<>();
        tienLoiMOKI.add("Đăng bán sản phẩm trong 2 nốt nhạc.");

        listDataChild.put(listDataHeader.get(0), MOKILaGi);
        listDataChild.put(listDataHeader.get(1), suTinCayMOKI);
        listDataChild.put(listDataHeader.get(2), tienLoiMOKI);
    }
}

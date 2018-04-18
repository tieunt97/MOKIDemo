package com.example.tieu_nt.mokidemo.View.ManHinhTrangChu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.tieu_nt.mokidemo.Adapter.AdapterMenu;
import com.example.tieu_nt.mokidemo.Adapter.ViewPagerAdapterTrangChu;
import com.example.tieu_nt.mokidemo.Model.DrawerItem;
import com.example.tieu_nt.mokidemo.Model.TrangChu.MySingleton;
import com.example.tieu_nt.mokidemo.R;
import com.example.tieu_nt.mokidemo.View.ManHinhDangNhap.ManHinhDangKyActivity;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.Fragment.FragmentBeAn;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.Fragment.FragmentBeChoiMaHoc;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.Fragment.FragmentBeDiRaNgoai;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.Fragment.FragmentBeKhoeAnToan;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.Fragment.FragmentBeMac;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.Fragment.FragmentBeNgu;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.Fragment.FragmentBeTam;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.Fragment.FragmentBeVeSinh;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.Fragment.FragmentDanhChoMe;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.Fragment.FragmentDoDungGiaDinh;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.Fragment.FragmentMienPhi;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.Fragment.FragmentSanPhamKhac;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.Fragment.FragmentTatCa;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tieu_nt on 2/6/2018.
 */

public class ManHinhTrangChuActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener{
    //may tinh ca nhan
//    public static String SERVER = "http://192.168.1.110:8080/webmoki";
//    public static String SERVER_NAME_SANPHAM = "http://192.168.1.110:8080/webmoki/laydssanpham.php";

    //Genymotion
    public static String SERVER = "http://10.0.3.2:8080/webmoki";
    public static String SERVER_NAME_SANPHAM = "http://10.0.3.2:8080/webmoki/laydssanpham.php";
    public static String SERVER_NAME_DANGNHAP_DANGKY = "http://10.0.3.2:8080/webmoki/dangnhap_dangky.php";

    //Wifi HUST
//    public static String SERVER = "http://10.11.203.188:8080/webmoki";
//    public static String SERVER_NAME_SANPHAM = "http://10.11.203.188:8080/webmoki/laydssanpham.php";

    public static String uploadUrl = "http://192.168.1.110:8080/webmoki/dangnhap_dangky.php?ham=updateImgUserInfo";

    private FrameLayout trangChu;
    private LinearLayout linearUser;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private AdapterMenu adapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ViewFlipper viewFlipper;
    private Button btnSapXep, btnLoc, btnXung;
    private CircleImageView imgUserInfo;
    private FloatingActionButton fab;
    private float x1, x2;
    private final int IMG_REQUEST = 1;
    private boolean dangList = false;
    private Bitmap bitmap;
    private ViewPagerAdapterTrangChu viewPagerAdapter;
    private List<Fragment> list = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private  AlertDialog.Builder builder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trangchu_layout);
        anhXa();

        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        //create Navigation
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_nav_drawer, R.string.close_nav_drawer){
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                trangChu.setTranslationX(slideOffset * drawerView.getWidth());
            }
        };
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        adapter = new AdapterMenu(ManHinhTrangChuActivity.this, 0, drawerLayout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        viewFlipper.setInAnimation(this, android.R.anim.fade_in);
        viewFlipper.setOutAnimation(this, android.R.anim.fade_out);
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(3000);

        //set viewPager
        list.add(new FragmentTatCa());
        list.add(new FragmentMienPhi());
        list.add(new FragmentBeAn());
        list.add(new FragmentBeMac());
        list.add(new FragmentBeNgu());
        list.add(new FragmentBeTam());
        list.add(new FragmentBeVeSinh());
        list.add(new FragmentBeKhoeAnToan());
        list.add(new FragmentBeDiRaNgoai());
        list.add(new FragmentBeChoiMaHoc());
        list.add(new FragmentDanhChoMe());
        list.add(new FragmentDoDungGiaDinh());
        list.add(new FragmentSanPhamKhac());

        titles.add("Tất cả");
        titles.add("Miễn phí");
        titles.add("Bé ăn");
        titles.add("Bé mặc");
        titles.add("Bé ngủ");
        titles.add("Bé tắm");
        titles.add("Bé vệ sinh");
        titles.add("Bé khỏe-an toàn");
        titles.add("Bé đi ra ngoài");
        titles.add("Bé chơi mà học");
        titles.add("Dành cho mẹ");
        titles.add("Đồ dùng gia đình");
        titles.add("Sản phẩm khác");
        viewPagerAdapter = new ViewPagerAdapterTrangChu(getSupportFragmentManager(), list, titles);
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_trangchu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.itemTimKiem:
                Intent iTimKiem = new Intent(ManHinhTrangChuActivity.this, TimKiemActivity.class);
                startActivity(iTimKiem);
                break;
            case R.id.itemManHinh:
                dangList = !dangList;
                if(dangList) item.setIcon(R.drawable.layout);
                else item.setIcon(R.drawable.computer);
                int position = viewPager.getCurrentItem();
                setHienThiDanhSachSanPham(position);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

//        final AlertDialog.Builder builder = new AlertDialog.Builder(ManHinhTrangChuActivity.this);
//        View view = getLayoutInflater().inflate(R.layout.dialog_thongbao_dangnhap, null, false);
//        TextView tvNoiDung = (TextView) view.findViewById(R.id.tvNoiDung);
//        Button btnHuy = (Button) view.findViewById(R.id.btnHuy);
//        Button btnDangXuat = (Button) view.findViewById(R.id.btnDangXuat);
//
//        builder.setView(view);
//        final AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//
//        btnHuy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                alertDialog.dismiss();
//            }
//        });
//
//        btnDangXuat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                alertDialog.onBackPressed();
//            }
//        });
//
//        //đóng sau 3s
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(3000);
//                    if(alertDialog.isShowing())
//                        alertDialog.dismiss();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        thread.start();

        super.onBackPressed();
    }

    private void anhXa(){
        trangChu = (FrameLayout) findViewById(R.id.themFragment);
        linearUser = (LinearLayout) findViewById(R.id.linearUser);
        linearUser.setOnClickListener(this);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setOnPageChangeListener(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        btnSapXep = (Button) findViewById(R.id.btnSapXep);
        btnSapXep.setOnClickListener(this);
        btnLoc = (Button) findViewById(R.id.btnLoc);
        btnLoc.setOnClickListener(this);
        btnXung = (Button) findViewById(R.id.btnXung);
        btnXung.setOnClickListener(this);
        imgUserInfo = (CircleImageView) findViewById(R.id.imgUserInfo);
        imgUserInfo.setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }

    //action view flipper
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                if(x2 > x1){
                    viewFlipper.showPrevious();
                }else{
                    viewFlipper.showNext();
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.linearUser:
                Intent intentUser = new Intent(ManHinhTrangChuActivity.this, ThongTinNguoiDungActivity.class);
                startActivity(intentUser);
                break;
            case R.id.imgUserInfo:
                selectImage();
                break;
            case R.id.btnSapXep:
                Toast.makeText(ManHinhTrangChuActivity.this, "Sắp xếp", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnLoc:
//                Toast.makeText(ManHinhTrangChuActivity.this, "Lọc", Toast.LENGTH_SHORT).show();
                Intent intentCTSP = new Intent(ManHinhTrangChuActivity.this, HienThiChiTietSanPhamActivity.class);
                startActivity(intentCTSP);
                break;
            case R.id.btnXung:
                Toast.makeText(ManHinhTrangChuActivity.this, "Xung", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fab:
                Intent intent = new Intent(ManHinhTrangChuActivity.this, CameraActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void selectImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null){
            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                imgUserInfo.setImageBitmap(bitmap);
                uploadImage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImage(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, uploadUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String Response = jsonObject.getString("response");
                    Toast.makeText(ManHinhTrangChuActivity.this, Response, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", "1ImgUserInfo");
                params.put("image", imageToString(bitmap));

                return params;
            }
        };

        MySingleton.getInstance(ManHinhTrangChuActivity.this).addToRequestQue(stringRequest);
    }

    private String imageToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setHienThiDanhSachSanPham(position);
    }

    private void setHienThiDanhSachSanPham(int position){
        switch (position){
            case 0:
                ((FragmentTatCa) viewPagerAdapter.getItem(position)).setDangList(dangList);
                break;
            case 1:
                ((FragmentMienPhi) viewPagerAdapter.getItem(position)).setDangList(dangList);
                break;
            case 2:
                ((FragmentBeAn) viewPagerAdapter.getItem(position)).setDangList(dangList);
                break;
            case 3:
                ((FragmentBeMac) viewPagerAdapter.getItem(position)).setDangList(dangList);
                break;
            case 4:
                ((FragmentBeNgu) viewPagerAdapter.getItem(position)).setDangList(dangList);
                break;
            case 5:
                ((FragmentBeTam) viewPagerAdapter.getItem(position)).setDangList(dangList);
                break;
            case 6:
                ((FragmentBeVeSinh) viewPagerAdapter.getItem(position)).setDangList(dangList);
                break;
            case 7:
                ((FragmentBeKhoeAnToan) viewPagerAdapter.getItem(position)).setDangList(dangList);
                break;
            case 8:
                ((FragmentBeDiRaNgoai) viewPagerAdapter.getItem(position)).setDangList(dangList);
                break;
            case 9:
                ((FragmentBeChoiMaHoc) viewPagerAdapter.getItem(position)).setDangList(dangList);
                break;
            case 10:
                ((FragmentDanhChoMe) viewPagerAdapter.getItem(position)).setDangList(dangList);
                break;
            case 11:
                ((FragmentDoDungGiaDinh) viewPagerAdapter.getItem(position)).setDangList(dangList);
                break;
            case 12:
                ((FragmentSanPhamKhac) viewPagerAdapter.getItem(position)).setDangList(dangList);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

package com.example.tieu_nt.mokidemo.View.TrangChu;

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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.tieu_nt.mokidemo.Adapter.AdapterMenu;
import com.example.tieu_nt.mokidemo.Adapter.ViewPagerAdapterTrangChu;
import com.example.tieu_nt.mokidemo.Model.Constants;
import com.example.tieu_nt.mokidemo.Model.DangNhap;
import com.example.tieu_nt.mokidemo.R;
import com.example.tieu_nt.mokidemo.View.MainActivity;
import com.example.tieu_nt.mokidemo.View.TrangChu.CameraTrangChu.CameraActivity;
import com.example.tieu_nt.mokidemo.View.TrangChu.Fragment.FragmentBeAn;
import com.example.tieu_nt.mokidemo.View.TrangChu.Fragment.FragmentBeChoiMaHoc;
import com.example.tieu_nt.mokidemo.View.TrangChu.Fragment.FragmentBeDiRaNgoai;
import com.example.tieu_nt.mokidemo.View.TrangChu.Fragment.FragmentBeKhoeAnToan;
import com.example.tieu_nt.mokidemo.View.TrangChu.Fragment.FragmentBeMac;
import com.example.tieu_nt.mokidemo.View.TrangChu.Fragment.FragmentBeNgu;
import com.example.tieu_nt.mokidemo.View.TrangChu.Fragment.FragmentBeTam;
import com.example.tieu_nt.mokidemo.View.TrangChu.Fragment.FragmentBeVeSinh;
import com.example.tieu_nt.mokidemo.View.TrangChu.Fragment.FragmentDanhChoMe;
import com.example.tieu_nt.mokidemo.View.TrangChu.Fragment.FragmentDoDungGiaDinh;
import com.example.tieu_nt.mokidemo.View.TrangChu.Fragment.FragmentMienPhi;
import com.example.tieu_nt.mokidemo.View.TrangChu.Fragment.FragmentSanPham;
import com.example.tieu_nt.mokidemo.View.TrangChu.Fragment.FragmentSanPhamKhac;
import com.example.tieu_nt.mokidemo.View.TrangChu.Fragment.FragmentTatCa;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tieu_nt on 2/6/2018.
 */

public class TrangChuActivity extends MainActivity implements View.OnClickListener, ViewPager.OnPageChangeListener,
LocSanPham{

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
    private CircleImageView imgKhachHang;
    private TextView tvTenKhachHang;
    private FloatingActionButton fab;
    private float x1, x2;
    private boolean dangList = false;
    private ViewPagerAdapterTrangChu viewPagerAdapter;
    private List<Fragment> list = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    public static int idKhachHang = 0;
    private ImageView[] imgSapXep = new ImageView[5];
    private RelativeLayout[] relaSapXep = new RelativeLayout[5];
    private int viTriSapXep = -1;
    private final String SAPXEP_SPMOI = "idSanPham", SAPXEP_GIA = "giaChuan", SAPXEP_YEUTHICH = "soLuotThich",
        SAPXEP_BINHLUAN = "soBinhLuan", SAPXEP_GIAM = "DESC", SAPXEP_TANG = "ASC";

    private String sapXep = "", giaTri = "";
    private int position = 0;
    private DialogLoc dialogLoc;


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

        if (DangNhap.getInstance().getKhachHang() != null){
            tvTenKhachHang.setText(DangNhap.getInstance().getKhachHang().getTenKhachHang());
            idKhachHang = DangNhap.getInstance().getKhachHang().getIdKhachHang();
            if(!DangNhap.getInstance().getKhachHang().getAnhInfoKH().equals("null"))
                Picasso.get().load(Constants.SERVER + DangNhap.getInstance().getKhachHang().getAnhInfoKH()).into(imgKhachHang);
        }
        adapter = new AdapterMenu(TrangChuActivity.this, 0, drawerLayout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        viewFlipper.setInAnimation(this, android.R.anim.fade_in);
        viewFlipper.setOutAnimation(this, android.R.anim.fade_out);
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(3000);

        //set viewPager
        Bundle bundleIdKhachHang = new Bundle();
        bundleIdKhachHang.putInt("idKhachHang", idKhachHang);

        FragmentTatCa fragmentTatCa = new FragmentTatCa();
        fragmentTatCa.setArguments(bundleIdKhachHang);

        FragmentMienPhi fragmentMienPhi = new FragmentMienPhi();
        fragmentMienPhi.setArguments(bundleIdKhachHang);

        FragmentBeAn fragmentBeAn = new FragmentBeAn();
        fragmentBeAn.setArguments(bundleIdKhachHang);

        FragmentBeMac fragmentBeMac = new FragmentBeMac();
        fragmentBeMac.setArguments(bundleIdKhachHang);

        FragmentBeNgu fragmentBeNgu = new FragmentBeNgu();
        fragmentBeNgu.setArguments(bundleIdKhachHang);

        FragmentBeTam fragmentBeTam =  new FragmentBeTam();
        fragmentBeTam.setArguments(bundleIdKhachHang);

        FragmentBeVeSinh fragmentBeVeSinh = new FragmentBeVeSinh();
        fragmentBeVeSinh.setArguments(bundleIdKhachHang);

        FragmentBeKhoeAnToan fragmentBeKhoeAnToan = new FragmentBeKhoeAnToan();
        fragmentBeKhoeAnToan.setArguments(bundleIdKhachHang);

        FragmentBeDiRaNgoai fragmentBeDiRaNgoai = new FragmentBeDiRaNgoai();
        fragmentBeDiRaNgoai.setArguments(bundleIdKhachHang);

        FragmentBeChoiMaHoc fragmentBeChoiMaHoc = new FragmentBeChoiMaHoc();
        fragmentBeChoiMaHoc.setArguments(bundleIdKhachHang);

        FragmentDanhChoMe fragmentDanhChoMe = new FragmentDanhChoMe();
        fragmentDanhChoMe.setArguments(bundleIdKhachHang);

        FragmentDoDungGiaDinh fragmentDoDungGiaDinh = new FragmentDoDungGiaDinh();
        fragmentDoDungGiaDinh.setArguments(bundleIdKhachHang);

        FragmentSanPhamKhac fragmentSanPhamKhac = new FragmentSanPhamKhac();
        fragmentSanPhamKhac.setArguments(bundleIdKhachHang);

        list.add(fragmentTatCa);
        list.add(fragmentMienPhi);
        list.add(fragmentBeAn);
        list.add(fragmentBeMac);
        list.add(fragmentBeNgu);
        list.add(fragmentBeTam);
        list.add(fragmentBeVeSinh);
        list.add(fragmentBeKhoeAnToan);
        list.add(fragmentBeDiRaNgoai);
        list.add(fragmentBeChoiMaHoc);
        list.add(fragmentDanhChoMe);
        list.add(fragmentDoDungGiaDinh);
        list.add(fragmentSanPhamKhac);

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
                Intent iTimKiem = new Intent(TrangChuActivity.this, TimKiemActivity.class);
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
        imgKhachHang = (CircleImageView) findViewById(R.id.imgKhachHang);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        tvTenKhachHang = (TextView) findViewById(R.id.tvTenKhachHang);
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
                Intent intentUser = new Intent(TrangChuActivity.this, ThongTinNguoiDungActivity.class);
                startActivity(intentUser);
                break;
            case R.id.btnSapXep:
                sapXep();
                break;
            case R.id.btnLoc:
                loc();
                break;
            case R.id.btnXung:
                Toast.makeText(TrangChuActivity.this, "Xung", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fab:
                if(DangNhap.getInstance().getKhachHang() == null){
                    Toast.makeText(this, "Bạn cần đăng nhập để đăng bán sản phẩm", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(TrangChuActivity.this, CameraActivity.class);
                    startActivityForResult(intent, CameraActivity.IMG_GALLERY_REQUEST);
                }
                break;
        }
    }

    private void loc(){
        int idLoaiSP = 0;
        switch (position){
            case 0:
                break;
            case 1:
                idLoaiSP = Constants.ID_MIENPHI;
                break;
            case 2:
                idLoaiSP = Constants.ID_BEAN;
                break;
            case 3:
                idLoaiSP = Constants.ID_BEMAC;
                break;
            case 4:
                idLoaiSP = Constants.ID_BENGU;
                break;
            case 5:
                idLoaiSP = Constants.ID_BETAM;
                break;
            case 6:
                idLoaiSP = Constants.ID_BEVESINH;
                break;
            case 7:
                idLoaiSP = Constants.ID_BEKHOEANTOAN;
                break;
            case 8:
                idLoaiSP = Constants.ID_BEDIRANGOAI;
                break;
            case 9:
                idLoaiSP = Constants.ID_BECHOIMAHOC;
                break;
            case 10:
                idLoaiSP = Constants.ID_DANHCHOME;
                break;
            case 11:
                idLoaiSP = Constants.ID_DODUNGGIADINH;
                break;
            case 12:
                idLoaiSP = Constants.ID_SANPHAMKHAC;
                break;
        }
        dialogLoc = new DialogLoc(this, this, idLoaiSP);
        dialogLoc.show();
    }

    private void sapXep(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(TrangChuActivity.this);
        View view = getLayoutInflater().inflate(R.layout.dialog_sapxep, null, false);
        relaSapXep[0] = view.findViewById(R.id.relaGiaCaoDenThap);
        relaSapXep[1] = view.findViewById(R.id.relaGiaThapDenCao);
        relaSapXep[2] = view.findViewById(R.id.relaSanPhamMoi);
        relaSapXep[3] = view.findViewById(R.id.relaYeuThichNhat);
        relaSapXep[4] = view.findViewById(R.id.relaBinhLuan);

        imgSapXep[0] = view.findViewById(R.id.imgGiaCaoDenThap);
        imgSapXep[1] = view.findViewById(R.id.imgGiaThapDenCao);
        imgSapXep[2] = view.findViewById(R.id.imgSanPhamMoi);
        imgSapXep[3] = view.findViewById(R.id.imgYeuThich);
        imgSapXep[4] = view.findViewById(R.id.imgBinhLuan);

        Button btnHuy = (Button) view.findViewById(R.id.btnHuy);
        Button btnApDung = (Button) view.findViewById(R.id.btnApDung);

        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        relaSapXep[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viTriSapXep == 0){
                    imgSapXep[viTriSapXep].setVisibility(View.GONE);
                    viTriSapXep = -1;
                }else {
                    if(viTriSapXep != -1)
                        imgSapXep[viTriSapXep].setVisibility(View.GONE);
                    viTriSapXep = 0;
                    imgSapXep[viTriSapXep].setVisibility(View.VISIBLE);
                    giaTri = SAPXEP_GIA;
                    sapXep = SAPXEP_GIAM;
                }
            }
        });

        relaSapXep[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viTriSapXep == 1){
                    imgSapXep[viTriSapXep].setVisibility(View.GONE);
                    viTriSapXep = -1;
                }else {
                    if(viTriSapXep != -1)
                        imgSapXep[viTriSapXep].setVisibility(View.GONE);
                    viTriSapXep = 1;
                    imgSapXep[viTriSapXep].setVisibility(View.VISIBLE);
                    giaTri = SAPXEP_GIA;
                    sapXep = SAPXEP_TANG;
                }
            }
        });

        relaSapXep[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viTriSapXep == 2){
                    imgSapXep[viTriSapXep].setVisibility(View.GONE);
                    viTriSapXep = -1;
                }else {
                    if(viTriSapXep != -1)
                        imgSapXep[viTriSapXep].setVisibility(View.GONE);
                    viTriSapXep = 2;
                    imgSapXep[viTriSapXep].setVisibility(View.VISIBLE);
                    giaTri = SAPXEP_SPMOI;
                    sapXep = SAPXEP_GIAM;
                }
            }
        });

        relaSapXep[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viTriSapXep == 3){
                    imgSapXep[viTriSapXep].setVisibility(View.GONE);
                    viTriSapXep = -1;
                }else {
                    if(viTriSapXep != -1)
                        imgSapXep[viTriSapXep].setVisibility(View.GONE);
                    viTriSapXep = 3;
                    imgSapXep[viTriSapXep].setVisibility(View.VISIBLE);
                    giaTri = SAPXEP_YEUTHICH;
                    sapXep = SAPXEP_GIAM;
                }
            }
        });

        relaSapXep[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viTriSapXep == 4){
                    imgSapXep[viTriSapXep].setVisibility(View.GONE);
                    viTriSapXep = -1;
                }else {
                    if(viTriSapXep != -1)
                        imgSapXep[viTriSapXep].setVisibility(View.GONE);
                    viTriSapXep = 4;
                    imgSapXep[viTriSapXep].setVisibility(View.VISIBLE);
                    giaTri = SAPXEP_BINHLUAN;
                    sapXep = SAPXEP_GIAM;
                }
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viTriSapXep != -1){
                    viTriSapXep = -1;
                    giaTri = "";
                    sapXep = "";
                }
                alertDialog.dismiss();
            }
        });

        btnApDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viTriSapXep == -1) alertDialog.dismiss();
                else{
                    alertDialog.dismiss();
                    getFragment(position).layDanhSachSanPhamSapXep(giaTri, sapXep);
                }
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(this.position != position){
            this.giaTri = "";
            this.sapXep = "";
            getFragment(this.position).setGiaTriSapXepLoc();
            this.position = position;
            this.viTriSapXep = -1;
            setHienThiDanhSachSanPham(position);
        }
    }

    private void setHienThiDanhSachSanPham(int position){
        getFragment(position).setDangList(dangList);
    }

    private FragmentSanPham getFragment(int position){
        FragmentSanPham fragmentSanPham = (FragmentSanPham) viewPagerAdapter.getItem(position);
        return fragmentSanPham;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void locSanPham(int idLoaiSP, int giaThap, int giaCao) {
        getFragment(position).locSanPham(idLoaiSP, giaThap, giaCao);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            if(requestCode == CameraActivity.IMG_GALLERY_REQUEST){
                Uri uri = data.getData();
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                        /*Nếu lấy quality quá lớn sẽ có một số ảnh dung lượng lớn gây tràn bộ nhớ*/
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bStream);
                    byte[] byteImage = bStream.toByteArray();
                    Intent iThemSanPham = new Intent(this, ThemSanPhamActivity.class);
                    iThemSanPham.putExtra("image", byteImage);
//                    iThemSanPham.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(iThemSanPham);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

package com.example.tieu_nt.mokidemo.Presenter.DangNhapDangKy;

import android.os.AsyncTask;
import android.util.Log;

import com.example.tieu_nt.mokidemo.View.ManHinhDangNhap.ViewDangNhap;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.ManHinhTrangChuActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by tieu_nt on 4/3/2018.
 */

public class PresenterLogicDangNhap implements IPresenterDangNhap{
    private ViewDangNhap viewDangNhap;
    private int ketQua = 0;

    public PresenterLogicDangNhap(ViewDangNhap viewDangNhap) {
        this.viewDangNhap = viewDangNhap;
    }

    @Override
    public void kiemTraDangNhap(final String soDT, final String matKhau) {
        if(matKhau.equals("") && soDT.equals("")){
            viewDangNhap.dangNhapThatBai("Bạn chưa nhập Số điện thoại và Mật khẩu");
            return;
        }
        else if(soDT.equals("")){
            viewDangNhap.dangNhapThatBai("Bạn chưa nhập Số điện thoại");
            return;
        }
        else if(matKhau.equals("")){
            viewDangNhap.dangNhapThatBai("Bạn chưa nhập Mật khẩu");
            return;
        }
        else if(soDT.length() < 10){
            viewDangNhap.dangNhapThatBai("Số điện thoại không đúng");
            return;
        }
        else if(matKhau.length() < 6){
            viewDangNhap.dangNhapThatBai("Mật khẩu không hợp lệ, ít nhất 6 ký tự");
            return;
        }else{
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String login_url = ManHinhTrangChuActivity.SERVER + "/dangnhap_dangky.php";
                        Log.d("url", login_url);
                        URL url = new URL(login_url);
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("POST");
                        connection.setDoOutput(true);
                        connection.setDoInput(true);
                        OutputStream outputStream = connection.getOutputStream();
                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                        String post_data = URLEncoder.encode("ham", "UTF-8") + "=" + URLEncoder.encode("dangNhap", "UTF-8") +
                                "&" + URLEncoder.encode("soDT", "UTF-8") + "=" + URLEncoder.encode(soDT, "UTF-8") +
                                "&" + URLEncoder.encode("matKhau", "UTF-8") + "=" + URLEncoder.encode(matKhau, "UTF-8");

                        bufferedWriter.write(post_data);
                        bufferedWriter.flush();
                        bufferedWriter.close();
                        outputStream.close();

                        InputStream inputStream = connection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                        String result = "";
                        String line;
                        while((line = bufferedReader.readLine()) != null){
                            result += line;
                        }

                        bufferedReader.close();
                        inputStream.close();
                        connection.disconnect();
                        try{
                            ketQua = Integer.parseInt(result.toString().trim());
                            Log.d("result", ketQua + "");
                        }catch (NumberFormatException e){
                            e.printStackTrace();
                        }

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            thread.start();

            //không được đặt trong luồng thread
            if(ketQua == -1){
                viewDangNhap.dangNhapThatBai("Tài khoản chưa được kích hoạt");
                return;
            }
            if(ketQua > 0){
                viewDangNhap.dangNhapThanhCong();
                Log.d("ketQua", ketQua + "");
                return;
            }
            if(ketQua == 0){
                viewDangNhap.dangNhapThatBai("Số điện thoại hoặc mật khẩu không đúng." +
                        "\nXin vui lòng nhập lại");
                return;
            }
        }
    }

}

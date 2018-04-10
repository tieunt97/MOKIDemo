package com.example.tieu_nt.mokidemo.View.ManHinhDangNhap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.text.method.TextKeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.tieu_nt.mokidemo.R;

import org.w3c.dom.Text;

/**
 * Created by tieu_nt on 2/9/2018.
 */

public class XacNhanDangKyActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher{

    private EditText edt1, edt2, edt3, edt4;
    private Button btnGuiLai;
    private ImageButton imgBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xacnhandangky_layout);

        AnhXa();
        setActions();
    }

    private void AnhXa(){
        edt1 = (EditText) findViewById(R.id.edt1);
        edt2 = (EditText) findViewById(R.id.edt2);
        edt3 = (EditText) findViewById(R.id.edt3);
        edt4 = (EditText) findViewById(R.id.edt4);
        btnGuiLai = (Button) findViewById(R.id.btnGuiLai);
        imgBack = (ImageButton) findViewById(R.id.imgBack);
    }

    private void setActions(){
        btnGuiLai.setOnClickListener(this);
        imgBack.setOnClickListener(this);
        edt1.addTextChangedListener(this);
        edt2.addTextChangedListener(this);
        edt3.addTextChangedListener(this);
        edt4.addTextChangedListener(this);
    }

    private void guiLai(){
        Toast.makeText(this, "Gửi lại", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btnGuiLai:
                guiLai();
                break;
            case R.id.imgBack:
                finish();
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if(edt1.getText().hashCode() == charSequence.hashCode() && charSequence.length() > 0){
            edt2.requestFocus();
        }
        if(edt2.getText().hashCode() == charSequence.hashCode() && charSequence.length() > 0){
            edt3.requestFocus();
        }
        if(edt3.getText().hashCode() == charSequence.hashCode() && charSequence.length() > 0){
            edt4.requestFocus();
        }
        if(edt4.getText().hashCode() == charSequence.hashCode() && charSequence.length() > 0){
            if(edt1.getText().length() > 0 && edt2.getText().length() > 0 && edt3.getText().length() > 0){
                guiLai();
            }
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}

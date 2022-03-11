package com.example.nckh.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nckh.R;
import com.example.nckh.SQL.dulieusqllite;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class trangdangky extends AppCompatActivity {

    private EditText edtten,edtmk,edtdc,edtns;
    private EditText _EdtSdt;
    private TextView txt_back;
    private ImageView img;
    private Button btndy;
    private DatabaseReference databaseReference;
    public FirebaseAuth firebaseAuth;
    public FirebaseDatabase firebaseDatabase;
    public int key01 = 01;
    private Cursor cursor;
    private dulieusqllite dl;
    public ProgressDialog progressDialog;
    private Intent intent;
    private String Name = "Name";
    private ResultSet rs;
    private String dcx,dcy;
    private ResultSetMetaData rsmd;
    private Connection conn1 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        dangkynut();
        kiemtra();
        dangkysukien();
    }
    private void dangkynut()
    {
        img = findViewById(R.id.img_close);
        edtdc = findViewById(R.id.edt_address);
        edtmk = findViewById(R.id.edt_password);
        edtns = findViewById(R.id.edt_date_of_birth);
        _EdtSdt = findViewById(R.id.edt_phone_number);
        edtten = findViewById(R.id.edt_name);
        txt_back = findViewById(R.id.txt_lg);
        btndy = findViewById(R.id.btn_register);
        edtns.setEnabled(false);
        edtdc.setFocusable(false);
        _EdtSdt.setEnabled(false);
        edtmk.setEnabled(false);
        btndy.setEnabled(false);
    }
    private void kiemtra()
    {
        edtten.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if(event.getAction() == KeyEvent.ACTION_UP)
                {
                    if(edtten.getText().length() < 3)
                    {
                        edtmk.setEnabled(false);
                    }
                    else
                    {
                        edtmk.setEnabled(true);
                    }
                }
                return false;
            }
        });
        edtmk.setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if(event.getAction() == KeyEvent.ACTION_UP)
                {
                    if(edtmk.getText().length() < 8)
                    {
                        edtns.setEnabled(false);
                    }
                    else
                    {
                        edtns.setEnabled(true);
                    }
                }
                return false;
            }
        });
        edtns.setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if(event.getAction() == KeyEvent.ACTION_UP)
                {
                    if(edtns.getText().length() < 5)
                    {
                        _EdtSdt.setEnabled(false);
                    }
                    else
                    {
                        _EdtSdt.setEnabled(true);
                    }
                }
                return false;
            }
        });
        _EdtSdt.setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if(event.getAction() == KeyEvent.ACTION_UP)
                {
                    if(_EdtSdt.getText().length() < 9)
                    {
                        btndy.setEnabled(false);
                    }
                    else
                    {
                        btndy.setEnabled(true);
                    }
                }
                return false;
            }
        });
    }

    private void duathongtin()
    {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("dangnhap");
        firebaseAuth = FirebaseAuth.getInstance();
    }
    private void ax2()
    {
        progressDialog = new ProgressDialog(trangdangky.this);
        progressDialog.setMessage("Please wait........");
        progressDialog.show();
        String taikhoan = edtten.getText().toString();
        String mk = edtmk.getText().toString();
        firebaseAuth.createUserWithEmailAndPassword(taikhoan.trim(),mk.trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful()) {
                    String k = firebaseAuth.getCurrentUser().getUid();
                    DatabaseReference databaseReference2 = databaseReference.child(k);
                    databaseReference2.child("Tên").setValue(taikhoan);
                    databaseReference2.child("Mật khẩu").setValue(mk);
                    databaseReference2.child("Địa chỉ").setValue(edtdc.getText ().toString());
                    databaseReference2.child("Ngày sinh").setValue(edtns.getText().toString());
                    databaseReference2.child("Số điện thoại").setValue(_EdtSdt.getText().toString());
                    databaseReference2.child("Tình trạng").setValue(false);
                    databaseReference2.child("Name").setValue(Name);
                    String td = dcx + ","+ dcy;
                    taodt(k);
                    progressDialog.dismiss ();
                    finish();
                }
                else
                {
                    Toast.makeText(trangdangky.this,task.getException().toString(),Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
    private void taodt(String id)
    {
        try {
            dl = new dulieusqllite(trangdangky.this, "Users2.sqlite", null, 1);
            dl.truyvankhongtrakq("CREATE TABLE IF NOT EXISTS user(ID VARCHAR(50) PRIMARY KEY,Email VARCHAR(50),ten VARCHAR(50),matkhau VARCHAR(100),ngaysinh VARCHAR(20),diachi VARCHAR(200),SDT varchar(50))");
            dl.truyvancoketqua("INSERT INTO user VALUES('"+id+"','"+Name+"','"+edtten.getText().toString()+"','"+edtmk.getText().toString()+"','"+edtns.getText().toString()+"','"+edtdc.getText().toString()+"','"+_EdtSdt.getText().toString()+"')");
        }
        catch (Exception e)
        {
            Toast.makeText(trangdangky.this,"Error! An error occurred. Please try again later",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(trangdangky.this,MainActivity.class);
            startActivity(intent);
        }
    }
    private void dangkysukien()
    {
        btndy.setOnClickListener(new sukiencuatoi());
        img.setOnClickListener(new sukiencuatoi());
        edtdc.setOnClickListener(new sukiencuatoi());
        txt_back.setOnClickListener(new sukiencuatoi());
    }
    private void ax()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(trangdangky.this,R.style.AlertDialogStyle);
        builder.setTitle("Notification");
        builder.setMessage ("Do you want to exit?");
        builder.setIcon(R.drawable.panda);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });
        Dialog dialog1 = builder.create();
        dialog1.show();
    }
    private class sukiencuatoi implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {
            if(view.equals(btndy))
            {
                if(edtdc.getText().length() != 0)
                {
                    duathongtin();
                    ax2();
                }
                else
                {
                    Toast.makeText(trangdangky.this,"You are not select address",Toast.LENGTH_SHORT).show();
                }
            }

            if(view.equals(img) || view.equals(txt_back))
            {
                ax();
            }
            if(view.equals(edtdc))
            {
                intent = new Intent(trangdangky.this,Address_Us.class);
                startActivityForResult(intent,key01);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if(requestCode == key01 && resultCode == RESULT_OK && data != null)
        {
            Bundle bundle = data.getBundleExtra("dcc");
            String dc = bundle.getString("dcn");
            dcx = bundle.getString("dcx");
            dcy = bundle.getString("dcy");
            edtdc.setText(dc);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
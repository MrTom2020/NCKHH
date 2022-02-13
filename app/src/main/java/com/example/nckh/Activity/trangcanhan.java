package com.example.nckh.Activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nckh.R;
import com.example.nckh.SQL.dulieusqllite;
import com.example.nckh.model.thongtinnguoidung;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class trangcanhan extends AppCompatActivity
{
    private EditText edtten,edtmk,edtdc,edtns,edtsdt;
    private TextView txtkey;
    private Button btndy,btnluu;
    public Intent intent;
    public Cursor cursor;
    private dulieusqllite dl;
    private  int kk = 0;
    public FirebaseDatabase firebaseDatabase;
    public DatabaseReference databaseReference;
    public FirebaseAuth firebaseAuth =   FirebaseAuth.getInstance();
    public FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
    private ArrayList<thongtinnguoidung> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangcanhan);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("dangnhap");
        dangkynut();
        dangkysukien();
        taodt();
        ax();

    }
    private void dangkynut()
    {
        edtten = findViewById(R.id.edttencanhan);
        edtmk = findViewById(R.id.edtmkcanhan);
        edtdc = findViewById(R.id.edtdiachicanhan);
        edtns = findViewById(R.id.edtngaysinhcanhan);
        edtsdt = findViewById(R.id.edt_sdtcn);
        txtkey = findViewById(R.id.txtkey);
        btndy = findViewById(R.id.btndycn);
        btnluu = findViewById(R.id.btnluucanhan);
    }
    private void dangkysukien()
    {
        btnluu.setOnClickListener(new sukiencuatoi());
        btndy.setOnClickListener(new sukiencuatoi());
    }
    private void sukiendong()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(trangcanhan.this);
        builder.setTitle ("Notice");
        builder.setMessage ("Do you want to exit");
        builder.setPositiveButton ("Yes", new DialogInterface.OnClickListener () {
            @Override
            public void onClick (DialogInterface dialog, int which)
            {
                firebaseAuth.signOut();
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener () {
            @Override
            public void onClick (DialogInterface dialog, int which)
            {
                dialog.dismiss ();
            }
        });
        Dialog dialog1 = builder.create();
        dialog1.show();
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }

    private void taodt()
    {
        try {

            String ssa = MainActivity.tend;
            arrayList = new ArrayList<>();
            dl = new dulieusqllite(trangcanhan.this, "dulieunguoidung.sqlite", null, 1);
            dl.truyvankhongtrakq("CREATE TABLE IF NOT EXISTS nguoidung(ID VARCHAR(50) PRIMARY KEY,ten VARCHAR(50),matkhau VARCHAR(100),ngaysinh VARCHAR(20),diachi VARCHAR(200),SDT varchar(50),ChuoiBM VARCHAR(500))");
            cursor = dl.truyvancoketqua("SELECT * FROM nguoidung WHERE ID='" + ssa + "'");
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String ten = cursor.getString(1);
                    String mk = cursor.getString(2);
                    String ngaysinh = cursor.getString(3);
                    String diachi = cursor.getString(4);
                    String sdt = cursor.getString(5);
                    String key_chain = cursor.getString(6);
                    arrayList.add(new thongtinnguoidung(ten, mk, ngaysinh, diachi,sdt,key_chain));
                    kk = 4;
                }
            }
            DoDuLieu();
        }
        catch (Exception e)
        {
            Toast.makeText(trangcanhan.this,"Error! An error occurred. Please try again later",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(trangcanhan.this,MainActivity.class);
            startActivity(intent);
        }
    }


    private void DoDuLieu()
    {
        try {

            for (int i = 0; i < arrayList.size(); ++i) {
                edtten.setText(arrayList.get(i).getHoten());
                edtmk.setText(arrayList.get(i).getMatkhau());
                edtns.setText(arrayList.get(i).getNgaysinh());
                edtdc.setText(arrayList.get(i).getDiachi());
                edtsdt.setText(arrayList.get(i).getSdt());
                txtkey.setText(arrayList.get(i).getKey_chain());

            }
        }
        catch (Exception e)
        {
            Toast.makeText(trangcanhan.this,"Error! An error occurred. Please try again later",Toast.LENGTH_SHORT).show();
            intent = new Intent(trangcanhan.this,MainActivity.class);
            startActivity(intent);
        }
    }

    private void ax()
    {
        try {

            arrayList = new ArrayList<>();
            String id = MainActivity.tend;
            databaseReference.child(id).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String Ma = snapshot.getKey();
                        String Ten = snapshot.child("Tên").getValue().toString();
                        String Mk = snapshot.child("Mật khẩu").getValue().toString();
                        String diachi = snapshot.child("Địa chỉ").getValue().toString();
                        String ngaysinh = snapshot.child("Ngày sinh").getValue().toString();
                        String _key_chain = snapshot.child("Mã key").getValue().toString();
                        String _Sdt = snapshot.child("Số điện thoại").getValue().toString();
                    if (kk == 0)
                    {
                        dl.truyvankhongtrakq("INSERT INTO nguoidung VALUES('" + Ma + "','" + Ten + "','" + Mk + "','" + ngaysinh + "','" + diachi + "','"+_Sdt+"','"+_key_chain+"')");
                    }

                    arrayList.add(new thongtinnguoidung(Ten, Mk, ngaysinh, diachi,_Sdt,_key_chain));
                    for (int i = 0; i < arrayList.size(); ++i) {
                        edtten.setText(arrayList.get(i).getHoten());
                        edtmk.setText(arrayList.get(i).getMatkhau());
                        edtns.setText(arrayList.get(i).getNgaysinh());
                        edtdc.setText(arrayList.get(i).getDiachi());
                        edtsdt.setText(arrayList.get(i).getSdt());
                        txtkey.setText(arrayList.get(i).getKey_chain());
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error)
                {
                    Toast.makeText(trangcanhan.this,"Please check the internet speed",Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch (Exception e)
        {
            Toast.makeText(trangcanhan.this,"Data is conflicting, please login again",Toast.LENGTH_SHORT).show();
            intent = new Intent(trangcanhan.this,MainActivity.class);
            startActivity(intent);
        }

    }
    private void capnhatdl()
    {
        dl = new dulieusqllite(trangcanhan.this, "dulieunguoidung.sqlite", null, 1);
        firebaseUser = firebaseAuth.getCurrentUser();
         String ten =  edtten.getText().toString();
        String mk  = edtmk.getText().toString();
         String ns = edtns.getText().toString();
        String dc = edtdc.getText().toString();
        String _sdt = edtsdt.getText().toString();
        if(mk.trim().length() < 8 || ns.trim().length() < 5 || dc.trim().length() < 5)
        {
            Toast.makeText(trangcanhan.this,"Update failed",Toast.LENGTH_SHORT).show();
        }
        else
        {
            HashMap<String,Object> result = new HashMap<>();
            result.put("Ngày sinh",ns);
            result.put("Mật khẩu",mk);
            result.put("Tên",ten);
            result.put("Địa chỉ",dc);
            result.put("Số điện thoại",_sdt);
            databaseReference.child(MainActivity.tend).updateChildren(result);
            firebaseUser.updatePassword(mk).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(trangcanhan.this,"Change password successfully",Toast.LENGTH_SHORT).show();
                        dl.truyvankhongtrakq("UPDATE nguoidung SET ten='"+ten+"',matkhau = '"+mk+"',ngaysinh = '"+ns+"',diachi = '"+dc+"',SDT ='"+_sdt+"' WHERE ID = '"+MainActivity.tend+"'");
                    }
                }
            });
        }


    }
    private class sukiencuatoi implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {
            if(view.equals(btndy))
            {
                sukiendong();
            }
            if(view.equals(btnluu))
            {
                capnhatdl();
            }
        }
    }
}
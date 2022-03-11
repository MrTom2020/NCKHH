package com.example.nckh.Activity;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
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
    private TextView txtName,txtAddress;
    private Button btnluu;
    public Intent intent;
    private ImageView info_img_close;
    public Cursor cursor;
    private dulieusqllite dl;
    private int kk = 0;
    private int key02 = 02;
    public FirebaseDatabase firebaseDatabase;
    private Button btn_change;
    private EditText edt_new_Name;
    public DatabaseReference databaseReference;
    public FirebaseAuth firebaseAuth =   FirebaseAuth.getInstance();
    public FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
    private ArrayList<thongtinnguoidung> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("dangnhap");
        dangkynut();
        dangkysukien();
        taodt();
        ax();
    }
    private void dangkynut()
    {
        txtName = findViewById(R.id.info_tv_name);
        edtten = findViewById(R.id.info_edt_email);
        edtmk = findViewById(R.id.info_edt_password);
        edtdc = findViewById(R.id.info_edt_address);
        edtns = findViewById(R.id.info_edt_birth_day);
        edtsdt = findViewById(R.id.info_edt_phone_number);
        btnluu = findViewById(R.id.info_btn_save);
        info_img_close = findViewById(R.id.info_img_close);
        txtAddress = findViewById(R.id.tv_address);
        edtdc.setFocusable(false);
    }
    private void dangkysukien()
    {
        btnluu.setOnClickListener(new sukiencuatoi());
        info_img_close.setOnClickListener(new sukiencuatoi());
        txtName.setOnClickListener(new sukiencuatoi());
        edtdc.setOnClickListener(new sukiencuatoi());
    }
    private void sukiendong()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(trangcanhan.this,R.style.AlertDialogStyle);
        builder.setTitle("Notification");
        builder.setMessage ("Do you want to exit ?");
        builder.setIcon(R.drawable.panda);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                finish();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener()
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
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }

    private void Askuser()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(trangcanhan.this,R.style.AlertDialogStyle);
        builder.setTitle("Notification");
        builder.setMessage ("Do you want update address ?");
        builder.setIcon(R.drawable.panda);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                intent = new Intent(trangcanhan.this,Address_Us.class);
                startActivityForResult(intent, key02);
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener()
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

    private void taodt()
    {
        try {

            String ssa = MainActivity.tend;
            arrayList = new ArrayList<>();
            dl = new dulieusqllite(trangcanhan.this, "Users2.sqlite", null, 1);
            dl.truyvankhongtrakq("CREATE TABLE IF NOT EXISTS user(ID VARCHAR(50) PRIMARY KEY,Email VARCHAR(50),ten VARCHAR(50),matkhau VARCHAR(100),ngaysinh VARCHAR(20),diachi VARCHAR(200),SDT varchar(50))");
            cursor = dl.truyvancoketqua("SELECT * FROM user WHERE ID='" + ssa + "'");
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String Email = cursor.getString(1);
                    String ten = cursor.getString(2);
                    String mk = cursor.getString(3);
                    String ngaysinh = cursor.getString(4);
                    String diachi = cursor.getString(5);
                    String sdt = cursor.getString(6);
                    arrayList.add(new thongtinnguoidung(Email,ten, mk, ngaysinh, diachi,sdt));
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
                txtName.setText(arrayList.get(i).getEmail());
                edtten.setText(arrayList.get(i).getHoten());
                edtmk.setText(arrayList.get(i).getMatkhau());
                edtns.setText(arrayList.get(i).getNgaysinh());
                edtdc.setText(arrayList.get(i).getDiachi());
                edtsdt.setText(arrayList.get(i).getSdt());
                txtAddress.setText(arrayList.get(i).getDiachi());
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
                        String Name = snapshot.child("Name").getValue().toString();
                        String Ten = snapshot.child("Tên").getValue().toString();
                        String Mk = snapshot.child("Mật khẩu").getValue().toString();
                        String diachi = snapshot.child("Địa chỉ").getValue().toString();
                        String ngaysinh = snapshot.child("Ngày sinh").getValue().toString();
                        String _Sdt = snapshot.child("Số điện thoại").getValue().toString();
                    if (kk == 0)
                    {
                        dl.truyvankhongtrakq("INSERT INTO user VALUES('" + Ma + "','" + Name + "','" + Ten + "','" + Mk + "','" + ngaysinh + "','" + diachi + "','"+_Sdt+"')");
                    }

                    arrayList.add(new thongtinnguoidung(Name,Ten, Mk, ngaysinh, diachi,_Sdt));
                    for (int i = 0; i < arrayList.size(); ++i) {
                        txtName.setText(arrayList.get(i).getEmail());
                        edtten.setText(arrayList.get(i).getHoten());
                        edtmk.setText(arrayList.get(i).getMatkhau());
                        edtns.setText(arrayList.get(i).getNgaysinh());
                        edtdc.setText(arrayList.get(i).getDiachi());
                        edtsdt.setText(arrayList.get(i).getSdt());
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
        dl = new dulieusqllite(trangcanhan.this, "Users2.sqlite", null, 1);
        firebaseUser = firebaseAuth.getCurrentUser();
         String ten =  edtten.getText().toString();
        String mk  = edtmk.getText().toString();
         String ns = edtns.getText().toString();
        String dc = edtdc.getText().toString();
        String _sdt = edtsdt.getText().toString();
        String _name = txtName.getText().toString();
        if(mk.trim().length() < 8 || ns.trim().length() < 5 || dc.trim().length() < 5)
        {
            Toast.makeText(trangcanhan.this,"Update failed",Toast.LENGTH_SHORT).show();
        }
        else
        {
            HashMap<String,Object> result = new HashMap<>();
            result.put("Ngày sinh",ns);
            result.put("Mật khẩu",mk);
            result.put("Name",_name);
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
                        dl.truyvankhongtrakq("UPDATE user SET Email ='"+_name+"',matkhau = '"+mk+"',ngaysinh = '"+ns+"',diachi = '"+dc+"',SDT ='"+_sdt+"' WHERE ten = '"+ten+"'");
                    }
                }
            });
        }


    }
    public void ExportFileExcel()
    {
        Dialog dialog;
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.alertdialog_setname);
        btn_change = dialog.findViewById(R.id.btn_info_setName);
        edt_new_Name =dialog.findViewById(R.id.edt_newName);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = 1100;
        lp.height = 800;
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_new_Name.getText().toString().trim().length() != 0)
                {
                    txtName.setText(edt_new_Name.getText().toString());
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }
    private class sukiencuatoi implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {
            if(view.equals(btnluu))
            {
                capnhatdl();
            }
            if(view.equals(info_img_close))
            {
                sukiendong();
            }
            if(view.equals(txtName))
            {
                ExportFileExcel();
            }
            if(view.equals(edtdc))
            {
                Askuser();
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if(requestCode == key02 && resultCode == RESULT_OK && data != null)
        {
            Bundle bundle = data.getBundleExtra("dcc");
            String dc = bundle.getString("dcn");
            edtdc.setText(dc);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
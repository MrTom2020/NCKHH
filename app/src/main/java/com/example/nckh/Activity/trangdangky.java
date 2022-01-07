package com.example.nckh.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nckh.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class trangdangky extends AppCompatActivity {

    private EditText edtten,edtmk,edtns;
    private TextView txtdc;
    private Button btnthoat,btndy;
    private DatabaseReference databaseReference;
    public FirebaseAuth firebaseAuth;
    public FirebaseDatabase firebaseDatabase;
    public ProgressDialog progressDialog;
    private Intent intent;
    private String dcx,dcy;
    public int key01;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangdangky);
        dangkynut();
        kiemtra();
        dangkysukien();
    }
    private void dangkynut()
    {
        txtdc = findViewById(R.id.edtdiachi);
        edtmk = findViewById(R.id.edtmk);
        edtns = findViewById(R.id.editTextDate);
        edtten = findViewById(R.id.edtten);
        btndy = findViewById(R.id.btndd);
        btnthoat = findViewById(R.id.btnthoat);
        edtns.setEnabled(false);
       // edtdc.setFocusable(false);
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
                        edtten.setBackgroundColor(0xfffff000);
                    }
                    else
                    {
                        edtten.setBackgroundColor(0xfff0f0f0);
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
                        edtmk.setBackgroundColor(0xfffff000);
                    }
                    else
                    {
                        edtmk.setBackgroundColor(0xfff0f0f0);
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
                        edtns.setBackgroundColor(0xfffff000);
                    }
                    else
                    {
                        edtns.setBackgroundColor(0xfff0f0f0);
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
                    databaseReference2.child("Địa chỉ").setValue(txtdc.getText ().toString());
                    databaseReference2.child("Ngày sinh").setValue(edtns.getText().toString());
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
    private void dangkysukien()
    {
        btnthoat.setOnClickListener(new sukiencuatoi());
        btndy.setOnClickListener(new sukiencuatoi());
        txtdc.setOnClickListener(new sukiencuatoi());
    }
    private void ax()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(trangdangky.this);
        builder.setTitle("Notice");
        builder.setMessage ("Do you want to exit");
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
                duathongtin();
                ax2();
            }
            if(view.equals(btnthoat))
            {
                ax();
            }
            if(view.equals(txtdc))
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
            txtdc.setText(dc);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
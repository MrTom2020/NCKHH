package com.example.nckh.Activity;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.nckh.R;
import com.example.nckh.SQL.dulieusqllite;
import com.example.nckh.Service.ConnectionReceiver;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
public class MainActivity extends Activity {

    private EditText edtName,edtmk;
    private Button btndn;
    private TextView btndk,btn_qmk;
    public FirebaseAuth firebaseAuth;
    public Intent intent;
    static String tend,ten1;
    private ImageView img;
    public Cursor cursor;
    public dulieusqllite dl;
    public int numBer = 0;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dangkynut();
        check();
        dangkysukien();
    }
    private void check()
    {
        boolean ret = ConnectionReceiver.isConnected();
        String ms;
        if (ret)
        {
            ms = "The device has an Internet connection and can be done online";
            ten1 = "ok";
        }
        else
        {
            ms = "The device has no Internet connection and can be performed offline";
            ten1 = "ko";
            btndk.setEnabled (false);
        }
        Toast.makeText(MainActivity.this,ms,Toast.LENGTH_SHORT).show();
    }
    private void IsCheck()
    {
        try {
            dl = new dulieusqllite(MainActivity.this, "Users2.sqlite", null, 1);
            cursor = dl.truyvancoketqua("SELECT * FROM user WHERE ten='" + edtName.getText().toString().trim() + "' AND matkhau='" + edtmk.getText().toString().trim() + "'");
            if (cursor != null)
            {
               if(cursor.getCount() == 0)
                {
                    Toast.makeText(MainActivity.this, "Username or password incorrect", Toast.LENGTH_SHORT).show();
                }
                intent = new Intent(MainActivity.this, tranghai.class);
                while (cursor.moveToNext())
                {
                    tend = cursor.getString(0);
                    Toast.makeText(MainActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }
        }
        catch (Exception e)
        {
            Toast.makeText(MainActivity.this,"Login unsuccessful",Toast.LENGTH_SHORT).show();
        }
    }

    public void Askuser() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialogStyle);
        builder.setIcon(R.drawable.panda);
        builder.setTitle("Notification");
        builder.setMessage("Do you want exit ?");
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event)
    {
        Log.i("key pressed", String.valueOf(event.getKeyCode()));
        return super.dispatchKeyEvent(event);
    }

    @Override
    protected void onStart()
    {
        check();
        super.onStart();
    }

    private void dangkynut()
    {
        img = findViewById(R.id.img_close);
        edtName = findViewById(R.id.editTextEmail);
        edtmk = findViewById(R.id.editTextPassword);
        edtName.setText("tomhumchinvn@gmail.com");
        edtmk.setText("12345678");
        btndn = findViewById(R.id.cirLoginButton);
        btndk = findViewById(R.id.Btndangkytk);
        btn_qmk = findViewById(R.id.txt_forgot_password);
        edtName.setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if(event.getAction() == KeyEvent.ACTION_UP)
                {
                    if(edtName.getText().toString().trim().length() < 1)
                    {
                        btndn.setEnabled(false);
                    }
                }
                return false;
            }
        });
        edtmk.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if(event.getAction() == KeyEvent.ACTION_UP)
                {
                    if(edtmk.getText().toString().trim().length() < 1)
                    {
                        btndn.setEnabled(false);
                    }
                    else
                    {
                        btndn.setEnabled(true);
                    }
                }
                return false;
            }
        });
    }
    private void dangkysukien()
    {
        btndn.setOnClickListener(new sukiencuatoi());
        btndk.setOnClickListener(new sukiencuatoi());
        btn_qmk.setOnClickListener(new sukiencuatoi());
        img.setOnClickListener(new sukiencuatoi());
    }
    private void ax(String eMail)
    {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.sendPasswordResetEmail(eMail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull  Task<Void> task)
            {
                if(task.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "Please check your email for new password update", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private class sukiencuatoi implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {
          if(view.equals(btndn))
          {
             if(ten1.equals("ok"))
             {
                dangnhap();
             }
            if(ten1.equals("ko"))
             {
                 IsCheck();
             }
          }
          if(view.equals(btn_qmk))
          {
              intent = new Intent(MainActivity.this,QMK.class);
              startActivity(intent);
          }
          if(view.equals(btndk))
          {
              Intent intent = new Intent(MainActivity.this, trangdangky.class);
              startActivity(intent);
          }
          if(view.equals(img))
          {
              Askuser();
          }
        }
    }

    private void dangnhap()
    {
        try {
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Please wait a moment");
            progressDialog.show();
            String ten = edtName.getText().toString();
            String mk = edtmk.getText().toString();
            firebaseAuth = FirebaseAuth.getInstance();
            firebaseAuth.signInWithEmailAndPassword(ten.trim(), mk.trim()).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if (task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        intent = new Intent(MainActivity.this, tranghai.class);
                        tend = firebaseAuth.getCurrentUser().getUid();
                        startActivity(intent);
                    } else
                        {
                           progressDialog.dismiss();
                           numBer++;
                           if(numBer == 3)
                           {
                                ax(edtName.getText().toString());
                                numBer = 0;
                           }
                        Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();

                    }
                    progressDialog.dismiss();
                }

            });
        }
        catch (Exception e)
        {
            progressDialog.dismiss();
            Toast.makeText(MainActivity.this,"Login unsuccessful",Toast.LENGTH_SHORT).show();
        }
    }
}
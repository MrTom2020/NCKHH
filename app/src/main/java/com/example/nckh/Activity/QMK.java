package com.example.nckh.Activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nckh.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class QMK extends AppCompatActivity {

    private EditText edt_tl,edt_PassWord;
    private Spinner spn_ch;
    private Button btn_Exit,btn_send;
    private FirebaseAuth firebaseAuth;
    private ArrayAdapter<String> arrayAdapter;
    private String k = "";
    private String classs1 = "com.mysql.jdbc.Driver";
    private String url1 = "jdbc:mysql://us-cdbr-east-04.cleardb.com:3306/heroku_236e6eebaa8fa47";
    private String un1 = "b37c4a464e7dbf";
    private String password1 = "8a49de4d";
    private Statement st;
    private ResultSet rs;
    private ResultSetMetaData rsmd;
    private Connection conn1 = null;
    private String[] ch = {"Email login","Key chain"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qmk);
        AddConTrol();
        AddEvent();
        Connect1();
    }
    private void AddConTrol()
    {
        edt_tl = findViewById(R.id.edt_ctl);
        spn_ch = findViewById(R.id.spn_ask);
        edt_PassWord = findViewById(R.id.edt_mkmoi);
        btn_Exit = findViewById(R.id.btn_thoatt);
        btn_send = findViewById(R.id.btn_sendd);
        arrayAdapter = new ArrayAdapter<String>(QMK.this, android.R.layout.simple_spinner_item,ch);
        arrayAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spn_ch.setAdapter(arrayAdapter);
    }
    private void Connect1()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {

            Class.forName(classs1);

            conn1 = DriverManager.getConnection(url1, un1,password1);

            //Toast.makeText(this, "Connect Successfull!", Toast.LENGTH_SHORT).show();


        } catch (ClassNotFoundException e) {
            Toast.makeText(this, "ERROR 1", Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            Toast.makeText(this, "ERROR 2" + e.getMessage(), Toast.LENGTH_SHORT).show();
            System.out.println("ERROR: " + e.getMessage());

        }
    }
    private void AddEvent()
    {
        btn_send.setOnClickListener(new MyEvent());
        btn_Exit.setOnClickListener(new MyEvent());
        spn_ch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                k = ch[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void RestartPasswordWithString()
    {
        try {
            String chuoi = edt_tl.getText().toString();
            st = conn1.createStatement();
            rs = st.executeQuery("Call RestartPasswordWithString('"+chuoi+"')");
            rsmd = rs.getMetaData();

            while (rs.next())
            {
                edt_PassWord.setText(rs.getString(1));
                // Toast.makeText(this, rsmd.getColumnName(1) + ": " + rs.getString(1)  , Toast.LENGTH_SHORT).show();
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void RestartPasswordWithEmail()
    {
        String emaill = edt_tl.getText().toString().trim();
        if(emaill.length() != 0)
        {
            firebaseAuth = FirebaseAuth.getInstance();
            firebaseAuth.sendPasswordResetEmail(emaill).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task)
                {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(QMK.this,"Please check your email to update the new password and then update the password in the profile page",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(QMK.this,"Password update failed",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    private class MyEvent implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {
            if(view.equals(btn_Exit))
            {
                AskUser();
            }
            if(view.equals(btn_send))
            {
                if(k.length() > 1)
                {
                    switch (k)
                    {
                        case "Email login":
                            // Toast.makeText(QMK.this,"123",Toast.LENGTH_SHORT).show();
                            RestartPasswordWithEmail();
                            break;
                        case "Key chain":
                            RestartPasswordWithString();
                            break;
                    }
                }
            }
        }
    }
    private void AskUser()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(QMK.this,R.style.AlertDialogStyle);
        builder.setTitle ("Notification");
        builder.setMessage ("Do you want to exit ?");
        builder.setIcon(R.drawable.panda);
        builder.setPositiveButton ("Yes", new DialogInterface.OnClickListener () {
            @Override
            public void onClick (DialogInterface dialog, int which)
            {
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
}
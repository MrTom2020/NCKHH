package com.example.nckh.Activity;

import static com.example.nckh.R.id;
import static com.example.nckh.R.layout;
import static com.example.nckh.model.WifiApp.cb;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.nckh.Adapter.info_pm;
import com.example.nckh.Adapter.trangAdp;
import com.example.nckh.R;
import com.example.nckh.SQL.dulieusqllite;
import com.example.nckh.Service.ConnectionReceiver;
import com.example.nckh.model.pm;
import com.example.nckh.model.thongtin;
import com.github.anastr.speedviewlib.SpeedView;
import com.github.anastr.speedviewlib.components.Section;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class tranghienthi extends Activity implements
        OnChartGestureListener, OnChartValueSelectedListener
{
    private static final String TAG = "MainActivity";
    private LineChart lineChart;
    private Button btnmdkk;
    private TextView txtnd,txtda,txtmq135,txttt;
    private ImageView imageView;
    private ImageButton imageButton2;
    private FirebaseDatabase database;
    private Intent intent;
    public DatabaseReference databaseReference,databaseReference1;
    public String tt ="";
    private SpeedView sp1,sp2;
    public int nn = 1;
    private Double kk;
    public dulieusqllite dl;
    private TextView txt;
    private SpeedView speedView;
    private ListView lst;
    private info_pm pm1;
    private ArrayList<pm> arrayList = new ArrayList<>();
    public Cursor cursor;
    public Dialog dialog;
    private ArrayList<Entry> entryArrayList = new ArrayList<>();
    public ArrayList<thongtin> arrayList5 = new ArrayList<>(),arrayListtt = new ArrayList<>();
    public NotificationManagerCompat notificationManagerCompat;
    public trangAdp adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_tranghienthi);
       dangkynut();
       notificationManagerCompat =NotificationManagerCompat.from(this);
       ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
       // check();
        taodt();
       ax3();
        registerForContextMenu(imageButton2);
        dangkysukien();
    }
    private void dangkynut()
    {
        txtnd = findViewById(id.txtnd);
        txtda = findViewById(id.txtda);
        sp1 = findViewById(id.speedView4);
        sp2 = findViewById(id.speedView5);
        btnmdkk = findViewById(id.btnmdkk);
        imageButton2 = findViewById(id.imageButton);
        txtnd.setTextSize(13f);
        txttt = findViewById(id.txtttht);
        imageView = findViewById(id.imageView2);
        txtmq135 = findViewById(id.textView9);
    }
    private void taodt()
    {
        dl = new dulieusqllite(this,"Users2.sqlite",null,1);
        dl.truyvankhongtrakq("CREATE TABLE IF NOT EXISTS ThongTin(ID INTEGER PRIMARY KEY AUTOINCREMENT,nhietdo VARCHAR(50),doam VARCHAR(50),mq135 VARCHAR(50),density VARCHAR(50),time VARCHAR(50),date VARCHAR(50))");
    }
    private void addData(SpeedView speedView,String pm)
    {
        speedView.setMaxSpeed(500);
       speedView.getSections().clear();
        speedView.addSections(new Section(0f, .15f,0xff00fa01,  speedView.dpTOpx(30f))
                , new Section(.15f, .3f, 0xfffefb02, speedView.dpTOpx(30f))
                , new Section(.3f, .45f, 0xfffeaa00, speedView.dpTOpx(30f))
                , new Section(.45f, .6f, 0xffff2700, speedView.dpTOpx(30f))
                , new Section(.6f, 0.75f, 0xffdd2779, speedView.dpTOpx(30f))
                , new Section(0.75f, 1.f, 0xff961100,  speedView.dpTOpx(30f)));
        speedView.speedTo(Integer.parseInt(pm));
        speedView.setWithTremble(false);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case id.info_about_PM:
                intent = new Intent(tranghienthi.this,Tutorial_PM.class);
                startActivity(intent);
                break;
            case id.info_about_AQI:
                intent = new Intent(tranghienthi.this,Tutorial_AQI.class);
                startActivity(intent);
                break;
            case id.clear_t:
                ClearAllData();
                break;
            case id.dddl:
                DialoglistData();
                break;
            case id.save:
                GetAskUser();
                break;
            case id.export_data:
                ExportFileExcel();
                break;
        }
        return super.onContextItemSelected(item);
    }
    private void sendChannel(String title,String daTa,int hinhg)
    {
        NotificationManagerCompat notificationManagerCompat =  NotificationManagerCompat.from(this);
        Notification notification = new NotificationCompat.Builder(this,cb)
                .setContentTitle(title)
                .setSmallIcon(hinhg)
                .setContentInfo("Infon")
                .setStyle(new NotificationCompat.BigTextStyle().bigText(daTa))
                .setColor(0xfffff000)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManagerCompat.notify(1,notification);
    }
    public void ax3()
    {
        arrayList = new ArrayList<>();
            database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference("lichsu");
            databaseReference1 = database.getReference();
            databaseReference1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot)
                {
                    String clkk = snapshot.child("PM2,5").getValue().toString();
                    String da= snapshot.child("Humidity").getValue().toString();
                    String mdbui= snapshot.child("matdobui").getValue().toString();
                    String nd= snapshot.child("Temperature").getValue().toString();
                    kk = !mdbui.equals("") ? Double.parseDouble(mdbui) : 0;
                    double t = !clkk.equals("") ? Double.parseDouble(clkk) : 0;
                    txtnd.setText(nd + " C ");
                    addData(sp1,snapshot.child("PM10,0").getValue().toString());
                    addData(sp2,snapshot.child("PM2,5").getValue().toString());
                    txtnd.setCompoundDrawablesWithIntrinsicBounds(R.drawable.thermometer, 0, 0, 0);
                    txtda.setText(" : " + da + " % ");
                    txtda.setCompoundDrawablesWithIntrinsicBounds(R.drawable.droplets, 0, 0, 0);
                    txtmq135.setText(" PM  " + mdbui + "μg/m3");
                    GetRults(t,kk);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            databaseReference.limitToLast(5).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName)
                {
                    String kkk = String.valueOf(nn);
                    String nd = snapshot.child("nhietdo").getValue().toString();
                    String da= snapshot.child("doam").getValue().toString();
                    String tg= snapshot.child("Thoigian").getValue().toString();
                    String clkk= Objects.requireNonNull(snapshot.child("chatluongkk").getValue()).toString();
                    String mdb= Objects.requireNonNull(snapshot.child("matdobui").getValue()).toString();
                    double dd = Double.parseDouble(clkk);
                    nn++;
                    if(nn > 5)
                    {
                        nn = 1;
                    }
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
    }
    @SuppressLint("SetTextI18n")
    private void ClearAllData()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Warning");
        builder.setMessage("DO YOU WANT TO DELETE ALL DATA");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dl.truyvankhongtrakq("delete FROM ThongTin");
                Toast.makeText(tranghienthi.this,"The data was successfully deleted",Toast.LENGTH_SHORT).show();
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
        Dialog dialog2 = builder.create();
        dialog2.show();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }



    private void dangkysukien()
    {
        btnmdkk.setOnClickListener(new sukiencuatoi());
        txtmq135.setOnClickListener(new sukiencuatoi());
    }
    private void check()
    {
        boolean ret = ConnectionReceiver.isConnected();
        String ms;
        if(ret)
        {
            ms = "The device has an Internet connection and can be done online";
            tt = "ok";
            btnmdkk.setEnabled(true);
        }
        else
        {
            ms = "The device does not have an Internet connection and can be performed offline";
            tt = "ko";
            btnmdkk.setEnabled(false);
        }
        Toast.makeText(tranghienthi.this,ms,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart()
    {
        dangkynut();
        check();
        super.onStart();
    }
    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i(TAG,"onChartGestureStart X:" + me.getX() + "Y:" + me.getY());
    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i(TAG,"onChartGestureEnd " + lastPerformedGesture);
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.i(TAG,"onChartLongPressed");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
        Log.i(TAG,"onChartDoubleTapped");
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        Log.i(TAG,"onChartSingleTapped");
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
        Log.i(TAG,"onChartFling velox :" + velocityX + "veloy :"+ velocityY);
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        Log.i(TAG,"onChartScale: ScaleX :" +scaleX + "ScaleY :" + scaleY);
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        Log.i(TAG,"onChartTranslate dX :" + dX + "dY :" + dY);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.i(TAG,"onValueSelected :" + e.toString());
    }

    @Override
    public void onNothingSelected() {
        Log.i(TAG,"onNothingSelected ");
    }
    private void show_pm()
    {
        intent = new Intent(tranghienthi.this,show_PM.class);
        startActivity(intent);
    }
    private class sukiencuatoi implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {
            if(view.equals(btnmdkk))
            {
               ax3();
            }
            if(view.equals(txtmq135))
            {
                show_pm();
            }
        }
    }
    private void DialoglistData()
    {
        dialog = new Dialog(this);
        dialog.setContentView(layout.activity_danhsachluu);
        int w = ViewGroup.LayoutParams.MATCH_PARENT;
        int h = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setLayout(w,h);
        ListView listView = dialog.findViewById(id.listdata);
        d();
        adapter = new trangAdp(tranghienthi.this, layout.danhsach,arrayListtt);

        listView.setAdapter(adapter);
        dialog.show();
    }
    public void ExportFileExcel()
    {
        dialog = new Dialog(this);
        dialog.setContentView(layout.activity_exportdata);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = 1100;
        lp.height = 1800;

        Button btnsv,btnex;
        EditText Filename,Nameexcel;
        Filename = dialog.findViewById(id.edttf);
        Nameexcel = dialog.findViewById(id.edttenexcel);
        btnsv = dialog.findViewById(id.button3);
        btnex = dialog.findViewById(id.btnluu);

        btnsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                DataExcel(Filename,Nameexcel);
            }
        });
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }
    private void DataExcel(EditText edttenfile, EditText noidung)
    {
        try {

            Workbook workbook = new HSSFWorkbook();
            Cell cell = null;
            Sheet sheet = null;
            sheet = workbook.createSheet(noidung.getText().toString());
            Row row = sheet.createRow(0);

            cell = row.createCell(0);
            cell.setCellValue("Time");

            cell = row.createCell(1);
            cell.setCellValue("Temperature");

            cell = row.createCell(2);
            cell.setCellValue("Humidity");

            cell = row.createCell(3);
            cell.setCellValue("PM 2.5");

            cell = row.createCell(4);
            cell.setCellValue("AQI");

            for(int i = 1; i < arrayList5.size();i++)
            {
                Row row1 = sheet.createRow(i);
                cell = row1.createCell(0);
                cell.setCellValue(arrayList5.get(i).getDate() + arrayList5.get(i).getTime());

                cell = row1.createCell(1);
                cell.setCellValue(arrayList5.get(i).getNhietdo());

                cell = row1.createCell(2);
                cell.setCellValue(arrayList5.get(i).getDoam());

                cell = row1.createCell(3);
                cell.setCellValue(arrayList5.get(i).getDensity());

                cell = row1.createCell(4);
                cell.setCellValue(arrayList5.get(i).getMq135());
            }

            sheet.setColumnWidth(0, (3000));
            sheet.setColumnWidth(1, (3000));
            sheet.setColumnWidth(2, (3000));
            sheet.setColumnWidth(3, (3000));
            sheet.setColumnWidth(4, (3000));


            File file = new File(getExternalFilesDir(null), edttenfile.getText().toString()+".xls");
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(file);
                workbook.write(fileOutputStream);
                Toast.makeText(tranghienthi.this, "Save ok", Toast.LENGTH_SHORT).show();
            } catch (java.io.IOException e) {
                e.printStackTrace();
                try {
                    fileOutputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        catch (Exception e)
        {
            Toast.makeText(tranghienthi.this,e.toString(),Toast.LENGTH_SHORT).show();
        }
    }
    private void d()
    {
        arrayListtt = new ArrayList<>();
        cursor = dl.truyvancoketqua("SELECT * FROM ThongTin");
       if(cursor !=null)
        {
           while (cursor.moveToNext())
            {
               String id =cursor.getString(0);
               String nhietdo= cursor.getString(1);
               String doam = cursor.getString(2);
               String mq135 = cursor.getString(3);
               String density =cursor.getString(4);
               String time = cursor.getString(5);
               String date = cursor.getString(6);
             arrayListtt.add(new thongtin(id,nhietdo,doam,mq135,density,time,date));
            }
        }
    }
    private void GetRults(double kk, double bui)
    {
        txttt.setText("");
        String kqkk = kk > 300 ? "AQI : Everyone should stay indoors \n"
                :kk >= 201 ? "AQI : Sensitive groups should limit out. \n"
                : kk >= 101 ? "AQI : Sensitive groups should limit out. \n"
                : kk >= 51 ? "AQI : Sensitive groups should limit out. \n"
                :"AQI: Clear Air \n";
        txttt.setText(kqkk);
        String kqmdb = bui >= 350.5 ? "PM 2.5 : Bad effects on health. \n"
                :bui >= 250.5 ? "PM 2.5 : Danger \n"
                :bui >= 150.5 ? "PM 2.5 : Bad effects on health. \n"
                :bui >= 65.5 ? "PM 2.5 : Bad effects on health. \n"
                :bui >= 40.5 ? "PM 2.5 :  Affects sensitive groups \n"
                :bui >= 15.5 ? "PM 2.5 : Medium \n"
                :"PM 2.5: Good \n";
        int kqhinh = (bui >= 350.5) || (kk > 300) ? R.drawable.rattt
                :(bui >= 250.5) || (kk >= 201) ? R.drawable.rattt
                :(bui >= 150.5) || (kk >= 101) ? R.drawable.rattoite
                :(bui >= 65.5) || (kk >= 101) ? R.drawable.rattoite
                :(bui >= 40.5) || (kk >= 51) ? R.drawable.boy
                :(bui >= 15.5) ? R.drawable.userfuny
                : R.drawable.userfuny;
        String dataa = "AQI : " + kk +" "+ kqkk +"\nPm : " + bui + kqmdb;
        sendChannel("Notification",dataa,kqhinh);
        txttt.append(kqmdb);
        imageView.setBackgroundResource(kqhinh);
    }
    public void axx()
    {
        try {
            taodt();
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Waiting please.....");
            progressDialog.show();
            if (arrayList5.size() > 0) {
                for (int i = 0; i < arrayList5.size(); ++i) {
                    String nd = arrayList5.get(i).getNhietdo() + " ";
                    String da = arrayList5.get(i).getDoam() + " ";
                    String mq135 = arrayList5.get(i).getMq135() + " ";
                    String time = arrayList5.get(i).getTime() + " ";
                    String date = arrayList5.get(i).getDate() + " ";
                    String density = arrayList5.get(i).getDensity() + " ";
                    dl.truyvankhongtrakq("INSERT INTO ThongTin VALUES(null,'" + nd + "','" + da + "','" + mq135 + "','" + density + "','" + time + "','" + date + "')");
                }
            }
            arrayList5.clear();
            progressDialog.dismiss();
        }
        catch (Exception e)
        {
            Toast.makeText(tranghienthi.this,e.toString(),Toast.LENGTH_SHORT).show();
        }
    }
    private void GetAskUser()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("NOTICE");
        builder.setMessage("Do you have save file today ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                axx();
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
        Dialog dialog = builder.create();
        dialog.show();
    }
}
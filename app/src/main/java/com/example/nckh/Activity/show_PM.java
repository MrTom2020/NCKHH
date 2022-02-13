package com.example.nckh.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nckh.Adapter.info_pm;
import com.example.nckh.R;
import com.example.nckh.model.info_about_aqi_pm;
import com.github.anastr.speedviewlib.SpeedView;

import java.util.ArrayList;

public class show_PM extends AppCompatActivity {

//    private AnyChartView anyChartView;
//    private String[] montha = {"1","2","3"};
//    private int[] erning = {500,800,1000};
    private TextView txt;
    private SpeedView speedView;
    private ListView lst;
    private info_pm pm;
    private ArrayList<info_about_aqi_pm> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pm);
        addControl();
        addEvent();
        createSpeed();
//        txt = findViewById(R.id.txtPM);
//        //anyChartView = findViewById(R.id.any_chart);
//        //setupPieChart();
//        speedView = findViewById(R.id.speedView);
//        txt.setText("123");
//        speedView.getSections().clear();
//        speedView.setMinSpeed(0);
//        speedView.setMaxSpeed(300);
//        speedView.addSections(new Section(0f, .15f,0xff00fa01, speedView.dpTOpx(30f))
//                , new Section(.15f, .3f, 0xfffefb02, speedView.dpTOpx(30f))/*, Style.ROUND)*/
//                , new Section(.3f, .45f, 0xfffeaa00, speedView.dpTOpx(30f))
//                , new Section(.45f, .6f, 0xffff2700, speedView.dpTOpx(30f))
//                , new Section(.6f, 0.75f, 0xffdd2779, speedView.dpTOpx(30f))
//                , new Section(0.75f, 1.f, 0xff961100, speedView.dpTOpx(30f)));
//        speedView.setWithTremble(false);
//       //speedView.setUnitTextColor(0xffffffff);
//       // speedView.speedTo(10);
    }
    private void setupPieChart()
    {
//        Pie pie = AnyChart.pie();
//        List<DataEntry> dataEntries = new ArrayList<>();
//        for(int i = 0;i < montha.length;i++)
//        {
//            dataEntries.add(new ValueDataEntry(montha[i],erning[i]));
//        }
//        pie.data(dataEntries);
//        anyChartView.setChart(pie);


    }
    private void addControl()
    {
        lst = findViewById(R.id.ls_pm);
    }
    private void createSpeed()
    {
        arrayList.add(new info_about_aqi_pm(R.drawable.good,"30"));
        arrayList.add(new info_about_aqi_pm(R.drawable.good,"10"));
        pm = new info_pm(show_PM.this,R.layout.pm,arrayList);
        lst.setAdapter(pm);
    }
    private void addEvent()
    {

    }
    private class MyEvent implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {

        }
    }

}
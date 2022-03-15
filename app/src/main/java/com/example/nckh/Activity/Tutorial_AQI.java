package com.example.nckh.Activity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.nckh.Adapter.info_about_pm_aqi;
import com.example.nckh.R;
import com.example.nckh.model.info_about_aqi_pm;

import java.util.ArrayList;

public class Tutorial_AQI extends AppCompatActivity {

    private ConstraintLayout constraintLayout;
    private TextView txt;
    private ListView lst;
    private info_about_pm_aqi info_about_pm_aqi;
    private ArrayList<info_about_aqi_pm> info_about_aqi_pms = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_aqi);
        addcontrol();
    }
    private void addcontrol()
    {
        constraintLayout = findViewById(R.id.tutorial);
        txt = new TextView(Tutorial_AQI.this);
        txt.setWidth(constraintLayout.getMaxWidth());
        txt.setText("An Air Quality Index (AQI) is used by government agencies[1] to communicate to the public how polluted the air currently is or how\npolluted it is forecast to become.[2][3]\nPublic health risks increase as the AQI rises.");
        txt.setX(25);
        txt.setY(25);
        txt.setTextSize(16f);
        lst = new ListView(Tutorial_AQI.this);
        lst.setY(txt.getLineHeight() + 250);
        lst.setLayoutParams(new FrameLayout.LayoutParams(constraintLayout.getMaxWidth(),WindowManager.LayoutParams.FLAG_FULLSCREEN * 5/2 + 200));
        info_about_aqi_pms.add(new info_about_aqi_pm(R.drawable.good,"0 - 50 Good AQI\nGood air quality and little or no health risk.\nYou should let the indoor air circulate."));
        info_about_aqi_pms.add(new info_about_aqi_pm(R.drawable.medium,"51 - 100 Medium AQI\nSensitive individuals should avoid outdoor\nactivities as respiratory symptoms may occur"));
        info_about_aqi_pms.add(new info_about_aqi_pm(R.drawable.least,"101 - 150 Least AQI\nNot good for sensitive groups the general public\nand sensitive people in particular are at risk of\nirritation and respiratory problems"));
        info_about_aqi_pms.add(new info_about_aqi_pm(R.drawable.bbbad,"151 - 200 Bad AQI\nIncreased chance of people experiencing side\neffects and increased severity of heart and lung\ndisease - especially sensitive groups"));
        info_about_aqi_pms.add(new info_about_aqi_pm(R.drawable.verybad,"201 - 300 Very bad AQI\nEveryone will be significantly affected.\nSensitive groups of people will have\nreduced endurance in activities.\nThese people should stay indoors and limit\nvigorous activity."));
        info_about_aqi_pms.add(new info_about_aqi_pm(R.drawable.dengoures,"301 - 500 Dengoures AQI\nAll people and susceptible groups are at risk for\nserious reactions, adverse health effects,and\npossibly other illnesses. People should avoid\nexercise and stay indoors."));
        info_about_pm_aqi = new info_about_pm_aqi(Tutorial_AQI.this,R.layout.info_about_aqi_pm,info_about_aqi_pms);
        lst.setAdapter(info_about_pm_aqi);
        constraintLayout.addView(txt);
        constraintLayout.addView(lst);
    }
}
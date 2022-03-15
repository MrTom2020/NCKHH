package com.example.nckh.Activity;

import android.graphics.text.LineBreaker;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.TextViewCompat;

import com.example.nckh.Adapter.info_about_pm_aqi;
import com.example.nckh.R;
import com.example.nckh.model.info_about_aqi_pm;

import java.util.ArrayList;

public class Tutorial_PM extends AppCompatActivity {

    private ConstraintLayout constraintLayout;
    private TextView txt;
    private ListView lst;
    private int k = 0;
    private int h = 0;
    private com.example.nckh.Adapter.info_about_pm_aqi info_about_pm_aqi;
    private ArrayList<info_about_aqi_pm> info_about_aqi_pms = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_pm);
        addcontrol();
    }
    private void addcontrol()
    {
        constraintLayout = findViewById(R.id.tutorial_pm);
        txt = new TextView(Tutorial_PM.this);
        txt.setWidth(constraintLayout.getMaxWidth());
        txt.setText("Dust is a mixture of solid or liquid compounds suspended in the air, the compounds in dust are also known collectively as Particulate Matter - symbol PM. In which, dust particles with microscopic size (micrometer) are best known such as:");
        txt.setX(25);
        txt.setY(25);
        txt.setTextSize(16f);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            txt.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
        }
        TextViewCompat.setAutoSizeTextTypeWithDefaults(txt,TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        CardView cardView = null;
        for(int i = 1; i < 5;i++)
        {
            cardView = new CardView(Tutorial_PM.this);
            cardView.setY((txt.getLineHeight() + 250)*i);
            cardView.setX(25);
            cardView.setBackgroundResource(R.drawable.icon_aqi_pm);
            cardView.setMinimumHeight(250);
            cardView.setMinimumWidth(constraintLayout.getMaxWidth() - 100);
            cardView.setBackgroundColor(0xff333333);
            h += cardView.getMinimumHeight();
            addItem(i,cardView);
            constraintLayout.addView(cardView);
            int finalI = i;
            CardView finalCardView = cardView;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    if(k == 0)
                    {
                        createItem(finalI, finalCardView);
                        k = 1;
                    }
                    else
                    {
                        clearItem(finalI,finalCardView);
                        k = 0;
                    }
                }
            });
        }
        lst = new ListView(Tutorial_PM.this);
        lst.setY(h + 500);
        lst.setLayoutParams(new FrameLayout.LayoutParams(constraintLayout.getMaxWidth(), WindowManager.LayoutParams.FLAG_FULLSCREEN * 8/2 + 200));
        info_about_aqi_pms.add(new info_about_aqi_pm(R.drawable.good,"0 - 12 Good\nAffect health :No\nPrecautions :No"));
        info_about_aqi_pms.add(new info_about_aqi_pm(R.drawable.medium,"12,1 - 35,4 Medium\nAffect health :Sensitive people may experience respiratory diseases\nPrecautions :Sensitive individuals should reduce strenuous or prolonged activities."));
        info_about_aqi_pms.add(new info_about_aqi_pm(R.drawable.least,"35,5 - 55,4 Least\nAffect health :Increased risk of respiratory disease in susceptible individuals, exacerbation of heart and lung disease, increased risk of premature death in people with cardiopulmonary disease and the elderly.\nPrecautions :People with respiratory and cardiovascular diseases, the elderly and children should limit strenuous or prolonged activities."));
        info_about_aqi_pms.add(new info_about_aqi_pm(R.drawable.bbbad,"55,5 - 150,4 Bad\nAffect health :Exacerbation of heart and lung disease, increased risk of premature death in people with cardiopulmonary disease and the elderly, increased adverse effects on breathing in general\nPrecautions :People with respiratory and cardiovascular diseases, the elderly, and children should avoid strenuous and prolonged activities."));
        info_about_aqi_pms.add(new info_about_aqi_pm(R.drawable.verybad,"150,5 - 250,4 Very bad\nAffect health :Exacerbation of heart and lung disease, which significantly increases the risk of premature death in persons with cardiopulmonary disease and the elderly, significantly increases adverse respiratory effects in the general population\nPrecautions :People with respiratory disease, heart disease, the elderly and children should avoid all outdoor activities, the average person should avoid activities that involve prolonged exertion."));
        info_about_aqi_pms.add(new info_about_aqi_pm(R.drawable.dengoures,"250,5 - 500,4 Dengoures\nAffect health :Exacerbation of heart and lung disease, severely increasing risk of early death in people with cardiopulmonary disease, and severely affecting people's breathing\nPrecautions :People should avoid strenuous outdoor activities, people with respiratory or cardiovascular diseases, the elderly and children should stay indoors."));
        info_about_pm_aqi = new info_about_pm_aqi(Tutorial_PM.this,R.layout.info_about_aqi_pm,info_about_aqi_pms);
        lst.setAdapter(info_about_pm_aqi);
        constraintLayout.addView(txt);
        constraintLayout.addView(lst);

        //EPA (The U.S. Environmental Protection Agency – Cơ Quan Bảo Vệ Môi Trường Hoa Kỳ)
    }
    private void clearItem(int i,CardView cardView)
    {
        cardView.removeAllViews();
        TextView txt = new TextView(Tutorial_PM.this);
        txt.setTextColor(0xffffffff);
        String info = " ";
        switch (i)
        {
            case 1:
                info = "PM 10: ";
                break;
            case 2:
                info = "PM 2.5: ";
                break;
            case 3:
                info = "PM 1.0: ";
                break;
            case 4:
                info = "PM 0.1: ";
                break;
        }
        txt.setText(info);
        cardView.addView(txt);
    }
    private void addItem(int i,CardView view)
    {
        TextView txt = new TextView(Tutorial_PM.this);
        txt.setTextColor(0xffffffff);
        String info = " ";
        switch (i)
        {
            case 1:
                info = "PM 10: ";
                break;
            case 2:
                info = "PM 2.5: ";
                break;
            case 3:
                info = "PM 1.0: ";
                break;
            case 4:
                info = "PM 0.1: ";
                break;
        }
        txt.setText(info);
        view.addView(txt);
    }
    private void createItem(int i,CardView view)
    {
        TextView txt = new TextView(Tutorial_PM.this);
        txt.setTextColor(0xffffffff);
        String info = "";
        switch (i)
        {
            case 1:
                info = "PM10 dust is an aggregate of suspended dust particles with an aerodynamic diameter less than or equal to 10 µm.";
                txt.setText(info);
                break;
            case 2:
                info = "PM2.5 fine dust is microscopic dust particles that exist in the air with a size of 2.5 micrometers or less (about 30 times smaller than a human hair).";
                txt.setText(info);
                break;
            case 3:
                info = "Ultrafine dust or PM 1.0 dust is liquid or solid particles floating in the air. A fine dust particle of 1.0 has a diameter of less than or equal to 1 micrometer (micrometer is abbreviated as μm, equal to 1 millionth of a meter).";
                txt.setText(info);
                break;
            case 4:
                info = "Ultrafine dust is less than 0.1 micron in size.";
                txt.setText(info);
                break;
        }
        view.addView(txt);
        txt.setY(50);
    }
}
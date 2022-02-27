package com.example.nckh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.nckh.R;
import com.example.nckh.model.pm;
import com.github.anastr.speedviewlib.SpeedView;
import com.github.anastr.speedviewlib.components.Section;

import java.util.ArrayList;


public class info_pm extends BaseAdapter {

    public Context context;
    public int layout;
    public ArrayList<pm> about_pm_aqis;

    public info_pm(Context context, int layout, ArrayList<pm> about_pm_aqis) {
        this.context = context;
        this.layout = layout;
        this.about_pm_aqis = about_pm_aqis;
    }

    @Override
    public int getCount()
    {
        return about_pm_aqis.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Viewholer viewholer;
        if(convertView == null)
        {
            viewholer = new Viewholer();
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout,null);
            viewholer.c1 = (CheckBox)convertView.findViewById(R.id.c_pm1);
            viewholer.c2 = (CheckBox)convertView.findViewById(R.id.c_pm2);
            viewholer.c3 = (CheckBox)convertView.findViewById(R.id.c_pm3);
            viewholer.c4 = (CheckBox)convertView.findViewById(R.id.c_pm4);
            viewholer.c5 = (CheckBox)convertView.findViewById(R.id.c_pm5);
            viewholer.c6 = (CheckBox)convertView.findViewById(R.id.c_pm6);
            viewholer.txt_title = (TextView)convertView.findViewById(R.id.txtpmm);
            viewholer.speedView = (SpeedView)convertView.findViewById(R.id.speedView2);
            viewholer.speedView.setWithTremble(false);
            convertView.setTag(viewholer);
        }
        else
        {
            viewholer = (Viewholer) convertView.getTag();
        }
        pm info = about_pm_aqis.get(position);
        viewholer.txt_title.setText(info.getTitle());
        viewholer.speedView.getSections().clear();
        viewholer.speedView.addSections(new Section(0f, .15f,0xff00fa01,  viewholer.speedView.dpTOpx(30f))
                , new Section(.15f, .3f, 0xfffefb02,  viewholer.speedView.dpTOpx(30f))
                , new Section(.3f, .45f, 0xfffeaa00,  viewholer.speedView.dpTOpx(30f))
                , new Section(.45f, .6f, 0xffff2700,  viewholer.speedView.dpTOpx(30f))
                , new Section(.6f, 0.75f, 0xffdd2779,  viewholer.speedView.dpTOpx(30f))
                , new Section(0.75f, 1.f, 0xff961100,  viewholer.speedView.dpTOpx(30f)));
        viewholer.speedView.speedTo(Integer.parseInt(info.getValue_pm()));
        viewholer.c1.setBackgroundColor(0xff00E400);
        viewholer.c2.setBackgroundColor(0xffFFFF00);
        viewholer.c3.setBackgroundColor(0xffFF7E00);
        viewholer.c4.setBackgroundColor(0xffFF0000);
        viewholer.c5.setBackgroundColor(0xff8F3F97);
        viewholer.c6.setBackgroundColor(0xff7E0023);
        if(viewholer.txt_title.getText().toString() == "PM 2.5")
        {
            viewholer.c1.setText("0 -> 15.4");
            viewholer.c2.setText("15.5 -> 40.4");
            viewholer.c3.setText("40.5 -> 65.4");
            viewholer.c4.setText("65.5 -> 150.4");
            viewholer.c5.setText("150.5 -> 250.4");
            viewholer.c6.setText("250.5 -> 350.4");
        }
        if(viewholer.txt_title.getText().toString() == "PM 10")
        {
            viewholer.c1.setText("0 -> 15.4");
            viewholer.c2.setText("55 -> 154");
            viewholer.c3.setText("155 -> 254");
            viewholer.c4.setText("255 -> 354");
            viewholer.c5.setText("355 -> 424");
            viewholer.c6.setText("425 -> 504");
        }
//        TextViewCompat.setAutoSizeTextTypeWithDefaults(viewholer.txt,TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        return convertView;
    }
    public class Viewholer
    {
        TextView txt_title;
        SpeedView speedView;
        CheckBox c1,c2,c3,c4,c5,c6;
    }
}

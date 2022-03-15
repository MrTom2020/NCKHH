package com.example.nckh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nckh.R;
import com.example.nckh.model.info_about_aqi_pm;

import java.util.ArrayList;


public class info_about_pm_aqi extends BaseAdapter {

    public Context context;
    public int layout;
    public ArrayList<info_about_aqi_pm> about_pm_aqis;

    public info_about_pm_aqi(Context context, int layout, ArrayList<info_about_aqi_pm> about_pm_aqis) {
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
            viewholer.img = (ImageView) convertView.findViewById(R.id.img_tutorial);
            viewholer.txt = (TextView) convertView.findViewById(R.id.txt_tutorial);
            convertView.setTag(viewholer);
        }
        else
        {
            viewholer = (Viewholer) convertView.getTag();
        }
        info_about_aqi_pm info = about_pm_aqis.get(position);
        viewholer.txt.setWidth(WindowManager.LayoutParams.FLAG_FULLSCREEN - (WindowManager.LayoutParams.FLAG_FULLSCREEN)/4);

        viewholer.txt.setText(info.getInfo());
        viewholer.img.setBackgroundResource(info.getImg());
        return convertView;
    }
    public class Viewholer
    {
        ImageView img;
        TextView txt;
    }
}

package com.example.nckh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nckh.R;
import com.example.nckh.model.thongtin;

import java.util.List;

public class trangAdp extends BaseAdapter
{
    public Context context;
    public int layout;
    public List<thongtin> list;

    @Override
    public int getCount() {
        if(list.size() == 0)
        {
            return 0;
        }
        else
        {
            return list.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public class viewholer
    {
        TextView txtthoigian,txtngay,txtnhietdo,txtclkk,txtmdbui,txtdam,txttt;
        ImageView imageView;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        viewholer vh;
        if(convertView == null)
        {
            vh = new viewholer();
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout,null);
            vh.txtthoigian = (TextView)convertView.findViewById(R.id.txttgioluu);
            vh.txtngay = (TextView)convertView.findViewById(R.id.txtthoigian);
            vh.txtdam  = (TextView)convertView.findViewById(R.id.txtdam);
            vh.txtclkk = (TextView)convertView.findViewById(R.id.txtclkk);
            vh.txtnhietdo = (TextView)convertView.findViewById(R.id.txtnhietdo);
            vh.txtmdbui = (TextView)convertView.findViewById(R.id.txtmdbui);
            vh.txttt = (TextView)convertView.findViewById(R.id.txttt);
            vh.imageView = (ImageView)convertView.findViewById(R.id.imageView);
            convertView.setTag(vh);
        }
        else {
            vh = (viewholer)convertView.getTag();
        }
        thongtin tt = list.get(position);
        vh.txtthoigian.setText("--");
        vh.txtmdbui.setText(tt.getDensity());
        vh.txtnhietdo.setText(tt.getNhietdo()+ "C");
        vh.txtngay.setText("--");
        vh.txtclkk.setText(tt.getMq135());
        vh.txtdam.setText(tt.getDoam() + "%");
        double d = vh.txtclkk.getText().toString() != "" ? Double.parseDouble(vh.txtclkk.getText().toString()):0;
        double bui = vh.txtmdbui.getText().toString() != "" ? Double.parseDouble(vh.txtmdbui.getText().toString()):0;
        String kqttkk =  d > 300 ? "Air quality : " + "Does not affect health \n"
                :d >= 201 ? "Air quality : " + "Sensitive groups should limit their time outside \n"
                :d >= 101 ? "Air quality : " + "Sensitive groups should limit their time outside \n"
                :d >= 51 ? "Air quality : " + "Sensitive groups should limit their time outside \n"
                :"Air quality : " + "Does not affect health";
        String kqttbui = bui >= 350.5 ? "Dust density:" + "Very dangerous \n"
                :bui >= 250.5 ? "Dust density:" + "Danger \n"
                :bui >= 150.5 ? "Dust density:" + "Very bad impact on health \n"
                :bui >= 65.5 ? "Dust density:" + "Adverse to health \n"
                :bui >= 40.5 ? "Dust density:" + "Affect sensitive group \n"
                :bui >= 15.5 ? "Dust density:" + "Average"
                :"Dust density : " +"Good \n";
        int kqhinh = (bui >= 350.5) || (d > 505) ? R.drawable.rattt
                :(bui >= 250.5) || (d >= 425) ? R.drawable.rattt
                :(bui >= 150.5) || (d >= 355) ? R.drawable.rattoite
                :(bui >= 65.5) || (d>= 255) ? R.drawable.rattoite
                :(bui >= 40.5) || (d >= 155) ? R.drawable.boy
                :(bui >= 15.5) || (d >= 55) ? R.drawable.userfuny
                : R.drawable.userfuny;


        int kqmaukk = (d > 505)  ? 0xffdf0a00
                : (d >= 425) ? 0xffdf0a00
                : (d >= 355) ? 0xffdf0a00
                : (d >= 255)  ? 0xffff1820
                : (d >= 155)  ? 0xffffc51c
                : (d >= 55) ? 0xfff3ff24
                :0xff6cef00;



        int kqmaubui = (bui >= 350.5) ? 0xffdf0a00
                : (bui >= 250.5) ? 0xffdf0a00
                : (bui >= 150.5) ? 0xffdf0a00
                : (bui >= 65.5) ? 0xffff1820
                : (bui >= 40.5) ? 0xffffc51c
                : (bui >= 15.5) ? 0xfff3ff24
                :0xff6cef00;
        vh.txttt.setText(kqttkk);
        vh.txtclkk.setBackgroundColor(kqmaukk);
        vh.txttt.append(kqttbui);
        vh.txtmdbui.setBackgroundColor (kqmaubui);
        vh.imageView.setBackgroundResource(kqhinh);
        return convertView;
    }

    public trangAdp(Context context, int layout, List<thongtin> list)
    {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }
}

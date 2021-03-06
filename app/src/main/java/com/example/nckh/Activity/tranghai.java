package com.example.nckh.Activity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.nckh.R;
import com.example.nckh.Service.ConnectionReceiver;

public class tranghai extends TabActivity {

    private TabHost tabHost;
    private TabHost.TabSpec tabSpec;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tranghai);
        dangkynut();
        ax();
    }
    private void dangkynut()
    {
        tabHost = (TabHost)findViewById(android.R.id.tabhost);
    }
    private void check()
    {
        boolean ret = ConnectionReceiver.isConnected();
        String ms;
        if(ret)
        {
            ms = "The device has an Internet connection and can be done online";
        }
        else
        {
            ms = "The device does not have an Internet connection and can be performed offline";
        }
        Toast.makeText(tranghai.this,ms,Toast.LENGTH_SHORT).show();
    }

    private void ax()
    {
        tabSpec = tabHost.newTabSpec ("Info");
        tabSpec.setIndicator ("",getDrawable(R.drawable.map));
        intent = new Intent (this, tranghienthi.class);
        tabSpec.setContent (intent);
        tabHost.addTab (tabSpec);

        tabSpec = tabHost.newTabSpec ("Account");
        tabSpec.setIndicator ("",getDrawable(R.drawable.userrr));
        intent = new Intent (this, trangcanhan.class);
        tabSpec.setContent (intent);
        tabHost.addTab (tabSpec);


        tabSpec = tabHost.newTabSpec ("News");
        tabSpec.setIndicator ("",getDrawable(R.drawable.earth));
        intent = new Intent (this, tintuc.class);
        tabSpec.setContent (intent);
        tabHost.addTab (tabSpec);

       tabHost.setCurrentTab(1);

    }

    @Override
    protected void onStart()
    {
        check();
        super.onStart();
    }

    private class sukiencuatoi implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {

        }
    }
}
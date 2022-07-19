package com.example.nckh.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.nckh.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class tintuc extends AppCompatActivity {
    private TextView txtnd, txttdg, txtda, txtkk, txtten, txttt;
    private ImageView img, imgtt;
    private Button btn;
    private Spinner spn;
    private Spinner spn_tinh;
    private Spinner spn_sv;
    private int vt = 0;
    private ArrayAdapter<String> arrayAdapter;
    private final String[] SV = {"SV 1", "SV 2", "SV 3", "SV 4", "SV 5", "SV 6", "SV 7"};
    private final String[] Province = {"An Giang","Đà Nẳng","Gia Lai","Hà Nội","Quãng Ninh","Thái Nguyên","Tây Ninh","Lào Cai","Lạng Sơn","Hưng Yên","Hải Dương"
    ,"Cao Bằng","Bình Dương","Bắc Ninh","Hồ Chí Minh"};
    private String Address[];
    private final String A1 = "http://api.airvisual.com/v2/city?city=";
    private String A2 = "&country=Vietnam&key=292ac5de-7ee5-459c-b5f7-a485055f944";
    private String BD_TDM, BD_DiAn, BD_BenCat, BD_DauTieng, BN_TP_Bac_Ninh;
    private String BRVT_TP_BRVT, BRVT_DatDo, BG_TT_Neo;
    private String BL_TP_BacLieu, HY_TT_Nhu_Huynh;
    private String  CB_TP_Cao_Bang, DB_TP_Dien_Bien_Phu, DB, HT_TP_Ha_Tinh, HT_Ky_Anh, HT_Cam_Xuyen, HT_Duc_Tho, HT_Huong_Khe, HT_Nghi_Xuan, HT_Thach_Ha, HT_Vu_Quang;
    private String HD_TP_Hai_Duong, HD_Kinh_Mon, HD_Thanh_Ha, HD_Tu_Ky, HY_TP_Hung_Yen, HY_Yen_My, HY_Van_Giang, HY_Khoai_Chau, HY_An_Thi, HD_Kim_Thanh;
    private String TN_TP_Thai_Nguyen;
    private String AG_TP_Long_Xuyen;
    private String GL_Pleiku;
    private String HD_LaiCach, HD_NamSach,HD_PhuThai,HD_KinhMon;
    private String DN_TP_Da_Nang;
    private String DB_Muong_Nhe, BN_TT_Lim, BN_TT_Pho_Moi;
    private String SL_Phu_Yen, BN_TT_Cho, QN_TT_Troi;
    private String BN_TT_Thua;
    private String TN_TrangBang, TN_Tay_Ninh, TN_Chau_Thanh, TN_Tan_Chau, TN_Hoa_Thanh, TN_Ben_Cau, TN_Duong_Minh_Chau, TN_Tan_Bien, TN_Go_Dau;
    private String LC_Thi_tran_Pho_Lu, LS_TP_Lang_Son, LS_Binh_Gia, LS_Bac_Son, LS_Van_Quan, LS_Loc_Binh, LS_Dinh_Lap, LS_Huu_Lung;
    private String QN_TPCam_Pha, QN_TPHa_Long, QN_TPMong_Cai, QN_TPUong_Bi,
            QNCo_To, QNHai_Ha, QNQuang_Yen;
    private String TP_HCM;
    private String  HNHoan_Kiem, HNTay_Ho, HNSoc_Son,  HNHaNoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tintuc);
        addControl();
        addEvent();
        Ax();
        Angiang();
        huyen();

    }

    private void addControl() {
        txtnd = findViewById(R.id.txtndtt);
        txtda = findViewById(R.id.txtdamtt);
        txtten = findViewById(R.id.txtvt);
        txttt = findViewById(R.id.txtthongtin);
        txttdg = findViewById(R.id.txtgio);
        txtkk = findViewById(R.id.txtaqi);
        img = findViewById(R.id.imgtt);
        imgtt = findViewById(R.id.imgttt);
        btn = findViewById(R.id.btnl);
        spn_tinh = findViewById(R.id.spn_tinh);
        spn_sv = findViewById(R.id.spn_sv);
        spn = findViewById(R.id.sp_ds);
        arrayAdapter = new ArrayAdapter<String>(tintuc.this, android.R.layout.simple_spinner_item, Province);
        arrayAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spn_tinh.setAdapter(arrayAdapter);
        arrayAdapter = new ArrayAdapter<String>(tintuc.this, android.R.layout.simple_spinner_item, SV);
        arrayAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spn_sv.setAdapter(arrayAdapter);
    }

    private void huyen() {
        arrayAdapter = new ArrayAdapter<String>(tintuc.this, android.R.layout.simple_spinner_item, Address);
        arrayAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spn.setAdapter(arrayAdapter);
    }

    private void Angiang() {
        AG_TP_Long_Xuyen = A1 + "Long%20Xuyen&state=An%20Giang" + A2;
        Address = new String[]{"Long Xuyên city"};
    }
    private void Danang() {
        DN_TP_Da_Nang = A1 + "Da%20Nang&state=Da%20Nang" + A2;
        Address = new String[]{"Đà Nẳng city"};
    }
    private void LangSon() {
        LS_TP_Lang_Son = A1 + "Thanh%20Pho%20Lang%20Son&state=Tinh%20Lang%20Son" + A2;
        Address = new String[]{"Lạng Sơn City"};
    }

    private void TayNinh() {
        TN_TrangBang = A1 + "Trang%20Bang&state=Tinh%20Tay%20Ninh" + A2;
        TN_Tay_Ninh = A1 + "Tay%20Ninh&state=Tinh%20Tay%20Ninh" + A2;
        Address = new String[]{"Tây Ninh City", "Trảng Bàng District"};
    }



    private void QuangNinh() {
        QN_TPCam_Pha = A1 + "Cam%20Pha&state=Tinh%20Quang%20Ninh" + A2;
        QN_TPHa_Long = A1 + "Thanh%20Pho%20Ha%20Long&state=Tinh%20Quang%20Ninh" + A2;
        QN_TPUong_Bi = A1 + "Thanh%20Pho%20Uong%20Bi&state=Tinh%20Quang%20Ninh" + A2;
        QN_TT_Troi = A1 + "Troi&state=Tinh%20Quang%20Ninh" + A2;
        Address = new String[]{"Cẩm Phả City", "Hạ Long City", "Uông Bí City", "Trời Town"};
    }


    private void HaNoi() {
        HNHaNoi = A1 + "Hanoi&state=Hanoi" + A2;
        HNHoan_Kiem = A1 + "Hoan%20Kiem&state=Hanoi" + A2;
        HNTay_Ho = A1 + "Tay%20Ho&state=Hanoi" + A2;
        HNSoc_Son = A1 + "Soc%20Son&state=Hanoi" + A2;
        Address = new String[]{"Hà Nội Capital", "Hoàn Kiếm District", "Tây Hồ District", "Sóc Sơn District"};
    }


    private void GiaLai() {
        GL_Pleiku = A1 + "Pleiku&state=Gia%20Lai" + A2;
        Address = new String[]{"Pleiku City"};
    }

    private void BacNinh() {
        BN_TT_Thua = A1 + "Thua&state=Tinh%20Bac%20Ninh" + A2;
        BN_TT_Lim = A1 + "Lim&state=Tinh%20Bac%20Ninh" + A2;
        Address = new String[]{"Lim Town", "Thua Town"};
    }



    private void BinhDuong()//
    {
        BD_BenCat = A1 + "Ben%20Cat&state=Tinh%20Binh%20Duong" + A2;
        Address = new String[]{"Bến Cát District"};
    }

    private void CaoBang() {
        CB_TP_Cao_Bang = A1 + "Thanh%20Pho%20Cao%20Bang&state=Tinh%20Cao%20Bang" + A2;
        Address = new String[]{"Cao Bằng City"};
    }


    private void HaiDuong() {
        HD_LaiCach = A1 + "Lai%20Cach&state=Tinh%20Hai%20Duong" + A2;
        HD_NamSach = A1 + "Nam%20Sach&state=Tinh%20Hai%20Duong" + A2;
        HD_PhuThai = A1 + "Phu%20Thai&state=Tinh%20Hai%20Duong" + A2;
        HD_KinhMon = A1 + "Kinh%20Mon&state=Tinh%20Hai%20Duong" + A2;
        Address = new String[]{"Lai cách Town", "Kinh Môn Town","Nam Sách Town","Phú Hải Town"};
    }

    private void HungYen() {
        HY_TP_Hung_Yen = A1 + "Hung%20Yen&state=Tinh%20Hung%20Yen" + A2;
        HY_TT_Nhu_Huynh = A1 + "Nhu%20Quynh&state=Tinh%20Hung%20Yen" + A2;
        Address = new String[]{"Hưng Yên City","Như Huỳnh Town"};
    }

    private void LaoCai() {
        LC_Thi_tran_Pho_Lu = A1 + "Thi%20Tran%20Pho%20Lu&state=Tinh%20Lao%20Cai" + A2;
        Address = new String[]{"Phố Lu Town"};
    }


    private void ThaiNguyen()//
    {
        TN_TP_Thai_Nguyen = A1 + "Thai%20Nguyen&state=Tinh%20Thai%20Nguyen" + A2;
        Address = new String[]{"Thái Nguyên City"};
    }


    private void HCM() {
        TP_HCM = A1 + "Ho%20Chi%20Minh%20City&state=Ho%20Chi%20Minh%20City" + A2;
        Address = new String[]{"Hồ Chí Minh City"};

    }

    private void Ax() {
        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                vt = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void addEvent() {
        btn.setOnClickListener(new Myevent());
        spn_tinh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("AA00",String.valueOf(position));
                switch (position) {
                    case 0:
                        Angiang();
                        huyen();
                        break;
                    case 1:
                        Danang();
                        huyen();
                        break;
                    case 2:
                        GiaLai();
                        huyen();
                        break;
                    case 3:
                        HaNoi();
                        huyen();
                        break;
                    case 4:
                        QuangNinh();
                        huyen();
                        break;
                    case 5:
                        ThaiNguyen();
                        huyen();
                        break;
                    case 6:
                        TayNinh();
                        huyen();
                        break;
                    case 7:
                        LaoCai();
                        huyen();
                        break;
                    case 8:
                        LangSon();
                        huyen();
                        break;
                    case 9:
                        HungYen();
                        huyen();
                        break;
                    case 10:
                        HaiDuong();
                        huyen();
                        break;
                    case 11:
                        CaoBang();
                        huyen();
                        break;
                    case 12:
                        BinhDuong();
                        huyen();
                        break;
                    case 13:
                        BacNinh();
                        huyen();
                        break;
                    case 14:
                        HCM();
                        huyen();
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spn_sv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        A2 = "&country=Vietnam&key=a867adc0-769c-40d7-91d8-74e05a4dfdf9";
                        break;
                    case 1:
                        A2 = "&country=Vietnam&key=292ac5de-7ee5-459c-b5f7-a485055f9445";
                        break;
                    case 2:
                        A2 = "&country=Vietnam&key=292ac5de-7ee5-459c-b5f7-a485055f9445";
                        break;
                    case 3:
                        A2 = "&country=Vietnam&key=292ac5de-7ee5-459c-b5f7-a485055f9445";
                        break;
                    case 4:
                        A2 = "&country=Vietnam&key=292ac5de-7ee5-459c-b5f7-a485055f9445";
                        break;
                    case 5:
                        A2 = "&country=Vietnam&key=292ac5de-7ee5-459c-b5f7-a485055f9445";
                        break;
                    case 6:
                        A2 = "&country=Vietnam&key=292ac5de-7ee5-459c-b5f7-a485055f9445";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void doc(String chuoi) {
        Log.d("1122222222222222",chuoi);
        //chuoi = "http://api.openweathermap.org/data/2.5/air_pollution?lat=10.980682678323301&lon=106.6755117836531&appid=8eaaf173780245bef23e253c57ab04e5";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, chuoi, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONObject jsonObject = response.getJSONObject("data");
                    JSONObject jsonObject1 = jsonObject.getJSONObject("current");
                    JSONObject jsonObject2 = jsonObject1.getJSONObject("weather");
                    JSONObject jsonObject3 = jsonObject1.getJSONObject("pollution");
                    String Temperature = jsonObject2.getString("tp");
                    String Humidity = jsonObject2.getString("hu");
                    String wind_speed = jsonObject2.getString("ws");
                    String ic = jsonObject2.getString("ic");
                    String dt = jsonObject2.getString("ts");
                    String city = jsonObject.getString("city");
                    String state = jsonObject.getString("state");
                    String country = jsonObject.getString("country");
                    String Aqi = jsonObject3.getString("aqius");
                    double kk = Double.parseDouble(Aqi);
                    int kq2 = kk > 300 ? R.drawable.rattt : kk >= 201 ? R.drawable.rattt : kk >= 151 ? R.drawable.rattoite : kk >= 101 ? R.drawable.boy : R.drawable.userfuny;

                    String a = kk > 300 ? "Dangerous air quality"
                            : kk >= 201 ? "Very bad air quality"
                            : kk >= 151 ? "Evil Red air quality"
                            : kk >= 101 ? "Poor air quality"
                            : kk >= 51 ? "Average air quality"
                            : "Good air quality";

                    ic = ic.trim();
                    int kq = ic.equals("01d") ? R.drawable.dmot
                            : ic.equals("01n") ? R.drawable.nmot
                            : ic.equals("02d") ? R.drawable.dhai
                            : ic.equals("02n") ? R.drawable.nhai
                            : ic.equals("03d") ? R.drawable.dba
                            : ic.equals("03n") ? R.drawable.ban
                            : ic.equals("04d") ? R.drawable.dbon
                            : ic.equals("04n") ? R.drawable.bonnn
                            : ic.equals("09d") ? R.drawable.dchin
                            : ic.equals("09n") ? R.drawable.chinn
                            : ic.equals("10d") ? R.drawable.dmuoi
                            : ic.equals("10n") ? R.drawable.nmuoi
                            : ic.equals("11n") ? R.drawable.muoimotn
                            : ic.equals("11d") ? R.drawable.muoimotd
                            : ic.equals("13n") ? R.drawable.muoiban
                            : ic.equals("13d") ? R.drawable.muoibad
                            : ic.equals("50n") ? R.drawable.nammuoinpng
                            : R.drawable.nammuoid;
                    txttt.setText(a);
                    txttt.setTextColor(0xffFFFACD);
                    txtda.setText("Humidity :" + Humidity + "%");
                    txtda.setBackgroundColor(0xff333333);
                    txtda.setTextColor(0xffFFFACD);
                    txtnd.setText("Temperature :" + Temperature + "C");
                    txtnd.setBackgroundColor(0xff333333);
                    txtnd.setTextColor(0xffFFFACD);
                    txttdg.setText("Wind speed : " + wind_speed + "Km/h");
                    txttdg.setBackgroundColor(0xff333333);
                    txttdg.setTextColor(0xffFFFACD);
                    txtkk.setText("AQI : " + Aqi);
                    txtkk.setTextColor(0xffFFFACD);
                    txtkk.setBackgroundColor(0xff333333);
                    img.setImageResource(kq);
                    imgtt.setBackgroundResource(kq2);
                    txtten.setText("Country : " + country + "\nP    rovince : " + state + "\n : " + city);
                    txtten.setBackgroundColor(0xff333333);
                    txtten.setTextColor(0xffFFFACD);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(tintuc.this, "Please switch to another server", Toast.LENGTH_SHORT).show();
                Log.d("ERROR", error.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private class ReaJSONObject extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                Toast.makeText(tintuc.this, "ERROR", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray array = jsonObject.getJSONArray("dt");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject jsonObject1 = array.getJSONObject(i);
                    String ten = jsonObject1.getString("_id");
                    Toast.makeText(tintuc.this, ten, Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(s);
        }
    }


    private class Myevent implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.equals(btn)) {
                switch (Address[vt]) {
                    case "Đà Nẳng city":
                        doc(DN_TP_Da_Nang);
                        break;
                    case "Lạng Sơn City":
                        doc(LS_TP_Lang_Son);
                        break;
                    case "Tây Ninh City":
                        doc(TN_TrangBang);
                        break;
                    case "Trảng Bàng District":
                        doc(TN_Tay_Ninh);
                        break;
                    case "Cẩm Phả City":
                        doc(QN_TPCam_Pha);
                        break;
                    case "Hạ Long City":
                        doc(QN_TPHa_Long);
                        break;
                    case "Uông Bí City":
                        doc(QN_TPUong_Bi);
                        break;
                    case "Trời Town":
                        doc(QN_TT_Troi);
                        break;
                    case "Hà Nội Capital":
                        doc(HNHaNoi);
                        break;
                    case "Hoàn Kiếm District":
                        doc(HNHoan_Kiem);
                        break;
                    case "Tây Hồ District":
                        doc(HNTay_Ho);
                        break;
                    case "Sóc Sơn District":
                        doc(HNSoc_Son);
                        break;
                    case "Pleiku City":
                        doc(GL_Pleiku);
                        break;
                    case "Lim Town":
                        doc(BN_TT_Thua);
                        break;
                    case "Thua Town":
                        doc(BN_TT_Lim);
                        break;
                    case "Bến Cát District":
                        doc(BD_BenCat);
                        break;
                    case "Cao Bằng City":
                        doc(CB_TP_Cao_Bang);
                        break;
                    case "Lai cách Town":
                        doc(HD_LaiCach);
                        break;
                    case "Kinh Môn Town":
                        doc(HD_NamSach);
                        break;
                    case "Nam Sách Town":
                        doc(HD_PhuThai);
                        break;
                    case "Phú Hải Town":
                        doc(HD_KinhMon);
                        break;
                    case "Hưng Yên City":
                        doc(HY_TP_Hung_Yen);
                        break;
                    case "Như Huỳnh Town":
                        doc(HY_TT_Nhu_Huynh);
                        break;
                    case "Phố Lu Town":
                        doc(LC_Thi_tran_Pho_Lu);
                        break;
                    case "Thái Nguyên City":
                        doc(TN_TP_Thai_Nguyen);
                        break;
                    case "Hồ Chí Minh City":
                        doc(TP_HCM);
                        break;

                }
            }
        }
    }
}
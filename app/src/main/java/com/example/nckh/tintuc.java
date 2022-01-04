package com.example.nckh;

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
    private final String[] Province = {"Bà Rịa-Vũng Tàu", "Bạc Liêu", "Bắc Kạn", "Bắc Giang", "Bắc Ninh", "Bến Tre", "Bình Dương",
            "Bình Phước", "Bình Thuận", "Cà Mau", "Cao Bằng", "Điện Biên", "Đồng Nai", "Hà Giang", "Hà Tĩnh", "Hải Dương", "Hưng Yên", "Kiên Giang", "Lai Châu", "Lào Cai", "Nghệ An", "Ninh Bình", "Quảng Ngãi", "Sơn La", "Thái Bình", "Thái Nguyên", "Thanh Hóa", "Thừa Thiên – Huế", "Tiền Giang",
            "Trà Vinh", "Tuyên Quang", "Vĩnh Phúc", "Yên Bái", "Hồ Chí Minh", "Hà Nội", "Quảng Ninh", "Tây Ninh", "Lạng Sơn"};
    private String Address[];
    private final String A1 = "http://api.airvisual.com/v2/city?city=";
    private String A2 = "&country=Vietnam&key=8a86bcf0-789d-4ae8-963d-c63f8791196e";
    private String BD_TDM, BD_DiAn, BD_BenCat, BD_DauTieng, BN_TP_Bac_Ninh;
    private String BRVT_TP_BRVT, BRVT_DatDo, BG_TT_Neo;
    private String BL_TP_BacLieu, HY_TT_Nhu_Huynh;
    private String BC_TP_Bac_Kan, BC_Cho_Moi, BG_TP_Bac_Giang, BN_TP_BacNinh, BN_Gia_Binh, BT_TP_Ben_Tre, BT_Chau_Thanh, BT_Cho_Lach, BT_Giong_Trom, BT_Binh_Dai, BT_Ba_Tri, BT_Thanh_Phu;
    private String BP_Binh_Long, BP_Chon_Thanh, BP_Bu_Dop, CM_TP_Ca_Mau, CM_U_Minh, CM_Thoi_Binh, CM_Tran_Van_Thoi, CM_Cai_Nuoc, CM_Nam_Can, CM_Phu_Tan, CM_Ngoc_Hien;
    private String BTHUAN_Phan_Thiet, BTHUAN_La_Gi, BTHUAN_Phu_Quy, BTHUAN_Tanh_Linh, CB_TP_Cao_Bang, DB_TP_Dien_Bien_Phu, DB, HT_TP_Ha_Tinh, HT_Ky_Anh, HT_Cam_Xuyen, HT_Duc_Tho, HT_Huong_Khe, HT_Nghi_Xuan, HT_Thach_Ha, HT_Vu_Quang;
    private String HD_TP_Hai_Duong, HD_Kinh_Mon, HD_Thanh_Ha, HD_Tu_Ky, HY_TP_Hung_Yen, HY_Yen_My, HY_Van_Giang, HY_Khoai_Chau, HY_An_Thi, HD_Kim_Thanh;
    private String KG_TP_RachGia, KG_TP_Ha_Tien, KG_Kien_Luong, KG_Tan_Hiep, KG_Giong_Rieng, KG_Go_Quao, KG_An_Bien, KG_Vinh_Thuan, LC_TP_Lai_Chau, LC_Tam_Duong, LC_Phong_Tho, LC_Than_Uyen;
    private String LC_Sa_Pa, LC_Bat_Xat, LC_Bac_Ha, LC_Si_Ma_Cai, NA_TP_Vinh, NA_Dien_Chau, NA_Yen_Thanh, NA_Hung_Nguyen, NA_Quy_Hop, NA_Quy_Chau, NA_Tan_Ky, NA_Anh_Son, NA_Con_Cuong, NA_Tuong_Duong, NA_Nam_Dan, NA_Thanh_Chuong;
    private String NB_TP_Ninh_Binh, NB_TP_Tam_Diep, NB_Nho_Quan, QN_TP_Quang_Ngai, QN_Ba_To, QN_Binh_Son, QN_Ly_Son, QN_Minh_Long, QN_Mo_Duc, QN_Nghia_Hanh, QN_Son_Ha, QN_Son_Tinh, QN_Tra_Bong, QN_Tu_Nghia;
    private String SL_TP_Son_La, SL_Moc_Chau, SL_Bac_Yen, SL_Song_Ma, SL_Yen_Chau, TB_TP_Thai_Binh, TB_Dong_Hung, TB_Hung_Ha, TB_Tien_Hai, TB_Vu_Thu;
    private String TN_TP_Thai_Nguyen, TH_TP_Thanh_Hoa, TH_Bim_Son, TH_Quang_Xuong, TH_Quan_Xuong, TH_Hau_Loc, TH_Nga_Son, TH_Trieu_Son, TH_Nong_Cong, TH_Ngoc_Lac, TH_Cam_Thuy,
            TH_Vinh_Loc, TH_Tho_Xuan, TH_Thuong_Xuan, TH_Lang_Chanh, TH_Quan_Hoa, TH_Quan_Son, TH_Muong_Lat;
    private String TTH_Phong_Dien, TTH_Phu_Vang, TTH_Phu_Loc, TTH_A_Luoi, TG_Cho_Gao, TV_Cau_Ke, TV_Chau_Thanh, TV_Duyen_Hai, TV_Tieu_Can, TV_Tra_Cu, TTH_TP_Hue, TV_TP_Tra_Vinh;
    private String TQ_TP_Tuyen_Quang, TQ_Son_Duong, VP_Lap_Thach, VP_Vinh_Tuong, VP_Yen_Lac, YB_TP_Yen_Bai, HG_TP_Ha_Giang;
    private String DN_TP_Bien_Hoa;
    private String DB_Dien_Bien_Dong, DB_Muong_Ang, DB_Muong_Cha;
    private String DN_TP_Long_Khanh;
    private String DN_Long_Thanh, DN_Nhon_Trach, DN_Tan_Phu, DN_Thong_Nhat, DN_Trang_Bom;
    private String DB_Muong_Nhe, BN_TT_Lim, BN_TT_Pho_Moi;
    private String SL_Phu_Yen, BN_TT_Cho, QN_TT_Troi;
    private String TN_TrangBang, TN_Tay_Ninh, TN_Chau_Thanh, TN_Tan_Chau, TN_Hoa_Thanh, TN_Ben_Cau, TN_Duong_Minh_Chau, TN_Tan_Bien, TN_Go_Dau;
    private String LC_Thi_tran_Pho_Lu, LS_TP_Lang_Son, LS_Binh_Gia, LS_Bac_Son, LS_Van_Quan, LS_Loc_Binh, LS_Dinh_Lap, LS_Huu_Lung;
    private String QN_TPCam_Pha, QN_TPHa_Long, QN_TPMong_Cai, QN_TPUong_Bi,
            QNCo_To, QNHai_Ha, QNQuang_Yen;
    private String Quan_Binh_Thanh, Quan_Phu_Nhuan, Quan_Tan_Phu, Quan_Thu_Duc, Huyen_Can_Gio,
            Huyen_Cu_Chi, Huyen_Hoc_Mon, Huyen_Nha_Be, Quan_Bay, TP_HCM, Quan_Nam, Quan_Mot, Quan_Hai, Quan_Ba, Quan_Bon, Quan_Sau, Quan_Chin,
            Quan_Muoi, Quan_Muoi_Mot;
    private String HNCau_Giay, HNDong_Da, HNHa_Dong, HNHoan_Kiem, HNTay_Ho,
            HNThanh_Xuan, HNSon_Tay, HNDong_Anh, HNMe_Linh, HNPhu_Xuyen, HNPhuc_Tho, HNQuoc_Oai,
            HNSoc_Son, HNThach_That, HNThuong_Tin, HNHaNoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tintuc);
        addControl();
        addEvent();
        Ax();

        //doc(DiAn);

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

    private void LangSon() {
        LS_TP_Lang_Son = A1 + "Thanh%20Pho%20Lang%20Son&state=Tinh%20Lang%20Son" + A2;
        LS_Binh_Gia = A1 + "Binh%20Gia&state=Tinh%20Lang%20Son" + A2;
        LS_Bac_Son = A1 + "Bac%20Son&state=Tinh%20Lang%20Son" + A2;
        LS_Van_Quan = A1 + "Van%20Quan&state=Tinh%20Lang%20Son" + A2;
        LS_Loc_Binh = A1 + "Loc%20Binh&state=Tinh%20Lang%20Son" + A2;
        LS_Huu_Lung = A1 + "Huu%20Lung&state=Tinh%20Lang%20Son" + A2;
        Address = new String[]{"Lạng Sơn City", "Huyện Bình Gia", "Huyện Bắc Sơn", "Huyện Văn Quan", "Huyện Lộc Bình", "Huyện Hữu Lũng"};
    }

    private void TayNinh() {
        TN_TrangBang = A1 + "Trang%20Bang&state=Tinh%20Tay%20Ninh" + A2;
        TN_Tay_Ninh = A1 + "Tay%20Ninh&state=Tinh%20Tay%20Ninh" + A2;
        TN_Chau_Thanh = A1 + "Chau%20Thanh&state=Tinh%20Tay%20Ninh" + A2;
        TN_Tan_Chau = A1 + "Tan%20Chau&state=Tinh%20Tay%20Ninh" + A2;
        TN_Hoa_Thanh = A1 + "Hoa%20Thanh&state=Tinh%20Tay%20Ninh" + A2;
        TN_Ben_Cau = A1 + "Ben%20Cau&state=Tinh%20Tay%20Ninh" + A2;
        TN_Tan_Bien = A1 + "Tan%20Bien&state=Tinh%20Tay%20Ninh" + A2;
        TN_Go_Dau = A1 + "Go%20Dau&state=Tinh%20Tay%20Ninh" + A2;
        TN_Duong_Minh_Chau = A1 + "Duong%20Minh%20Chau&state=Tinh%20Tay%20Ninh" + A2;
        Address = new String[]{"Tây Ninh City", "Huyện Trảng Bàng", "Huyện châu thành", "Huyện Tân Châu", "Huyện Hòa Thành", "Huyện Bến Cầu", "Huyện Dương Minh Châu",
                "Huyện Tân Biên", "Huyện Gò Dầu"};
    }

    private void BaRia_VungTau() {
        BRVT_TP_BRVT = A1 + "Vung%20Tau&state=Tinh%20Ba%20Ria%20Vung%20Tau" + A2;
        BRVT_DatDo = A1 + "Dat%20Do&state=Tinh%20Ba%20Ria%20Vung%20Tau" + A2;
        Address = new String[]{"Vũng Tàu City", "Huyện Đất Đỏ"};
    }

    private void QuangNinh() {
        QN_TPCam_Pha = A1 + "Cam%20Pha&state=Tinh%20Quang%20Ninh" + A2;
        QN_TPMong_Cai = A1 + "Mong%20Cai&state=Tinh%20Quang%20Ninh" + A2;
        QNHai_Ha = A1 + "Quang%20Ha&state=Tinh%20Quang%20Ninh" + A2;
        QNQuang_Yen = A1 + "Quang%20Yen&state=Tinh%20Quang%20Ninh" + A2;
        QN_TPHa_Long = A1 + "Thanh%20Pho%20Ha%20Long&state=Tinh%20Quang%20Ninh" + A2;
        QN_TPUong_Bi = A1 + "Thanh%20Pho%20Uong%20Bi&state=Tinh%20Quang%20Ninh" + A2;
        QNCo_To = A1 + "Co%20To&state=Tinh%20Quang%20Ninh" + A2;
        QN_TT_Troi = A1 + "Troi&state=Tinh%20Quang%20Ninh" + A2;
        Address = new String[]{"Cẩm Phả City", "Móng Cái City", "Thị xã Quảng Hà", "Thị xã Quảng Yên", "Hạ Long City", "Uông Bí City", "Huyện Cô Tô", "Thị trấn Trời"};
    }

    private void BacLieu() {
        BL_TP_BacLieu = A1 + "Thanh%20Pho%20Bac%20Lieu&state=Tinh%20Bac%20Lieu" + A2;
        Address = new String[]{"Bạc Liêu City"};
    }

    private void HaNoi() {
        HNDong_Anh = A1 + "Dong%20Anh&state=Hanoi" + A2;
        HNHaNoi = A1 + "Hanoi&state=Hanoi" + A2;
        HNHoan_Kiem = A1 + "Hoan%20Kiem&state=Hanoi" + A2;
        HNCau_Giay = A1 + "Cau%20Giay&state=Hanoi" + A2;
        HNDong_Da = A1 + "Dong%20Da&state=Hanoi" + A2;
        HNHa_Dong = A1 + "Ha%20Dong&state=Hanoi" + A2;
        HNTay_Ho = A1 + "Tay%20Ho&state=Hanoi" + A2;
        HNThanh_Xuan = A1 + "Thanh%20Xuan&state=Hanoi" + A2;
        HNSon_Tay = A1 + "Son%20Tay&state=Hanoi" + A2;
        HNSoc_Son = A1 + "Soc%20Son&state=Hanoi" + A2;
        HNMe_Linh = A1 + "Me%20linh&state=Hanoi" + A2;
        HNPhu_Xuyen = A1 + "Phu%20Xuyen&state=Hanoi" + A2;
        HNThach_That = A1 + "Thach%20That&state=Hanoi" + A2;
        HNQuoc_Oai = A1 + "Quoc%20Oai&state=Hanoi" + A2;
        HNPhuc_Tho = A1 + "Phuc%20Tho&state=Hanoi" + A2;
        HNThuong_Tin = A1 + "Thuong%20Tin&state=Hanoi" + A2;
        Address = new String[]{"Quận Đông Anh", "Thủ đô Hà Nội", "Quận Hoàn Kiếm", "Quận Cầu Giấy", "Quận Đống Đa", "Quận Hà Đông", "Quận Tây Hồ"
                , "Quận Thanh Xuân", "Quận Sơn Tây", "Quận Sóc Sơn", "Quận Mê Linh", "Quận Phú Xuyên", "Quận Thạch Thất", "Quận Quốc Oai", "Quận Phúc Thọ"
                , "Quận Thường Tín"};
    }

    private void BacKan() {
        BC_TP_Bac_Kan = A1 + "Bac%20Kan&state=Tinh%20Bac%20Kan" + A2;
        BC_Cho_Moi = A1 + "Cho%20Moi&state=Tinh%20Bac%20Kan" + A2;
        Address = new String[]{"Bắc Kạn City", "Huyện Chợ Mới"};
    }

    private void BacGiang() {
        BG_TP_Bac_Giang = A1 + "Bac%20Giang&state=Tinh%20Bac%20Giang" + A2;
        BG_TT_Neo = A1 + "Neo&state=Tinh%20Bac%20Giang" + A2;
        Address = new String[]{"Bắc Giang City", "Thị trấn Neo"};
    }

    private void BacNinh() {
        BN_TP_BacNinh = A1 + "Bac%20Ninh&state=Tinh%20Bac%20Ninh" + A2;
        BN_Gia_Binh = A1 + "Gia%20Binh&state=Tinh%20Bac%20Ninh" + A2;
        BN_TP_Bac_Ninh = A1 + "Bac%20Ninh&state=Tinh%20Bac%20Ninh" + A2;
        BN_TT_Cho = A1 + "Cho&state=Tinh%20Bac%20Ninh" + A2;
        BN_TT_Lim = A1 + "Lim&state=Tinh%20Bac%20Ninh" + A2;
        BN_TT_Pho_Moi = A1 + "Pho%20Moi&state=Tinh%20Bac%20Ninh" + A2;
        Address = new String[]{"Bắc Ninh City", "Huyện Gia Bình", "City Bắc Ninh", "Thị trấn Chờ", "Thị trấn Lim", "Thị trấn Phố Mới"};
    }

    private void BenTre() {
        BT_TP_Ben_Tre = A1 + "Ben%20Tre&state=Tinh%20Ben%20Tre" + A2;
        BT_Chau_Thanh = A1 + "Chau%20Thanh&state=Tinh%20Ben%20Tre" + A2;
        BT_Cho_Lach = A1 + "Cho%20Lach&state=Tinh%20Ben%20Tre" + A2;
        BT_Giong_Trom = A1 + "Giong%20Trom&state=Tinh%20Ben%20Tre" + A2;
        BT_Binh_Dai = A1 + "Binh%20Dai&state=Tinh%20Ben%20Tre" + A2;
        BT_Ba_Tri = A1 + "Ba%20Tri&state=Tinh%20Ben%20Tre" + A2;
        BT_Thanh_Phu = A1 + "Thanh%20Phu&state=Tinh%20Ben%20Tre" + A2;
        Address = new String[]{"Bến Tre City", "Huyện Châu thành", "Huyện Chợ Lách", "Huyện Giồng Trôm", "Huyện Bình Đại",
                "Huyện Ba Tri", "Huyện Thạnh Phú"};
    }

    private void BinhDuong()//
    {
        BD_TDM = A1 + "Thu%20Dau%20Mot&state=Tinh%20Binh%20Duong" + A2;
        BD_DiAn = A1 + "Di%20An&state=Tinh%20Binh%20Duong" + A2;
        BD_BenCat = A1 + "Ben%20Cat&state=Tinh%20Binh%20Duong" + A2;
        BD_DauTieng = A1 + "Dau%20Tieng&state=Tinh%20Binh%20Duong" + A2;
        Address = new String[]{"Thủ Dầu Một City", "Dĩ An City", "Huyện Bến Cát", "Huyện Dầu Tiếng"};
    }

    private void BinhPhuoc() {
        BP_Binh_Long = A1 + "Binh%20Long&state=Tinh%20Binh%20Phuoc" + A2;
        BP_Chon_Thanh = A1 + "Chon%20Thanh&state=Tinh%20Binh%20Phuoc" + A2;
        BP_Bu_Dop = A1 + "Bu%20Dop&state=Tinh%20Binh%20Phuoc" + A2;
        ;
        Address = new String[]{"Thị Xã Bình Long", "Huyện Chơn Thành", "Huyện Bù đốp"};
    }

    private void BinhThuan() {
        BTHUAN_Phan_Thiet = A1 + "Phan%20Thiet&state=Tinh%20Binh%20Thuan" + A2;
        BTHUAN_La_Gi = A1 + "La%20Gi&state=Tinh%20Binh%20Thuan" + A2;
        BTHUAN_Phu_Quy = A1 + "Phu%20Quy&state=Tinh%20Binh%20Thuan" + A2;
        BTHUAN_Tanh_Linh = A1 + "Tanh%20Linh&state=Tinh%20Binh%20Thuan" + A2;
        BTHUAN_La_Gi = A1 + "La%20Gi&state=Tinh%20Binh%20Thuan" + A2;
        Address = new String[]{"Phan Thiết City", "Thị xã La Gi", "Huyện Phú Quý", "Huyện Tánh Linh", "Huyện La Gi"};
    }

    private void CaMau() {
        CM_TP_Ca_Mau = A1 + "Ca%20Mau&state=Tinh%20Ca%20Mau" + A2;
        CM_U_Minh = A1 + "U%20Minh&state=Tinh%20Ca%20Mau" + A2;
        CM_Thoi_Binh = A1 + "Thoi%20Binh&state=Tinh%20Ca%20Mau" + A2;
        CM_Tran_Van_Thoi = A1 + "Tran%20Van%20Thoi&state=Tinh%20Ca%20Mau" + A2;
        CM_Cai_Nuoc = A1 + "Cai%20Nuoc&state=Tinh%20Ca%20Mau" + A2;
        CM_Nam_Can = A1 + "Nam%20Can&state=Tinh%20Ca%20Mau" + A2;
        CM_Phu_Tan = A1 + "Phu%20Tan&state=Tinh%20Ca%20Mau" + A2;
        CM_Ngoc_Hien = A1 + "Ngoc%20Hien&state=Tinh%20Ca%20Mau" + A2;
        ;
        Address = new String[]{"Cà Mau City", "Huyện U Minh", "Huyện Thới Bình", "Huyện Trần Văn Thời", "Huyện Cái Nước", "Huyện Năm Căn",
                "Huyện Phú Tân", "Huyện Ngọc Hiển"};
    }

    private void CaoBang() {
        CB_TP_Cao_Bang = A1 + "Thanh%20Pho%20Cao%20Bang&state=Tinh%20Cao%20Bang" + A2;
        ;
        Address = new String[]{"Cao Bằng City"};
    }

    private void DienBien() {
        DB_TP_Dien_Bien_Phu = A1 + "Dien%20Bien%20Phu&state=Tinh%20Dien%20Bien" + A2;
        DB_Dien_Bien_Dong = A1 + "Dien%20Bien%20Dong&state=Tinh%20Dien%20Bien" + A2;
        DB_Muong_Ang = A1 + "Muong%20Ang&state=Tinh%20Dien%20Bien" + A2;
        DB_Muong_Cha = A1 + "Muong%20Cha&state=Tinh%20Dien%20Bien" + A2;
        DB_Muong_Nhe = A1 + "Muong%20Nhe&state=Tinh%20Dien%20Bien" + A2;
        ;
        Address = new String[]{"Điện Biên Phủ City", "Huyện Điện Biên Đông", "Huyện Mường Ảng", "Huyện Mường Chà", "Huyện Mường Nhé"};
    }

    private void DongNai() {
        DN_TP_Bien_Hoa = A1 + "Bien%20Hoa&state=Tinh%20Dong%20Nai" + A2;
        DN_TP_Long_Khanh = A1 + "Long%20Khanh&state=Tinh%20Dong%20Nai" + A2;
        DN_Long_Thanh = A1 + "Long%20Thanh&state=Tinh%20Dong%20Nai" + A2;
        DN_Nhon_Trach = A1 + "Nhon%20Trach&state=Tinh%20Dong%20Nai" + A2;
        DN_Tan_Phu = A1 + "Tan%20Phu&state=Tinh%20Dong%20Nai" + A2;
        DN_Thong_Nhat = A1 + "Thong%20Nhat&state=Tinh%20Dong%20Nai" + A2;
        DN_Trang_Bom = A1 + "Trang%20Bom&state=Tinh%20Dong%20Nai" + A2;
        Address = new String[]{"Biên Hòa City", "Long Khánh City", "Huyện Long Thành", "Huyện Nhơn Trạch", "Huyện Tân Phú", "Huyện Thống Nhất",
                "Huyện Trảng Bom"};
    }

    private void HaTinh() {
        HT_TP_Ha_Tinh = A1 + "Ha%20Tinh&state=Tinh%20Ha%20Tinh" + A2;
        HT_Ky_Anh = A1 + "Ky%20Anh&state=Tinh%20Ha%20Tinh" + A2;
        HT_Cam_Xuyen = A1 + "Cam%20Xuyen&state=Tinh%20Ha%20Tinh" + A2;
        HT_Duc_Tho = A1 + "Duc%20Tho&state=Tinh%20Ha%20Tinh" + A2;
        HT_Huong_Khe = A1 + "Huong%20Khe&state=Tinh%20Ha%20Tinh" + A2;
        HT_Nghi_Xuan = A1 + "Nghi%20Xuan&state=Tinh%20Ha%20Tinh" + A2;
        HT_Thach_Ha = A1 + "Thach%20Ha&state=Tinh%20Ha%20Tinh" + A2;
        HT_Vu_Quang = A1 + "Vu%20Quang&state=Tinh%20Ha%20Tinh" + A2;
        ;
        Address = new String[]{"Hà Tĩnh City", "Huyện Cẩm Xuyên", "Huyện Đức Thọ", "Huyện Hương Khê",
                "Huyện Kỳ Anh", "Huyện Nghi Xuân", "Huyện Thạch Hà", "Huyện Vũ Quang"};
    }

    private void HaiDuong() {
        HD_TP_Hai_Duong = A1 + "Thanh%20Pho%20Hai%20Duong&state=Tinh%20Hai%20Duong" + A2;
        HD_Kinh_Mon = A1 + "Kinh%20Mon&state=Tinh%20Hai%20Duong" + A2;
        HD_Thanh_Ha = A1 + "Thanh%20Ha&state=Tinh%20Hai%20Duong" + A2;
        HD_Kim_Thanh = A1 + "Phu%20Thai&state=Tinh%20Hai%20Duong" + A2;
        HD_Tu_Ky = A1 + "Tu%20Ky&state=Tinh%20Hai%20Duong" + A2;
        ;
        Address = new String[]{"Hải Dương City", "Thị xã Kinh Môn", "Huyện Thanh Hà", "Huyện Tứ Kỳ", "Huyện Kim Thành"};
    }

    private void HungYen() {
        HY_TP_Hung_Yen = A1 + "Hung%20Yen&state=Tinh%20Hung%20Yen" + A2;
        HY_Yen_My = A1 + "Yen%20My&state=Tinh%20Hung%20Yen" + A2;
        HY_Van_Giang = A1 + "Van%20Giang&state=Tinh%20Hung%20Yen" + A2;
        HY_Khoai_Chau = A1 + "Khoai%20Chau&state=Tinh%20Hung%20Yen" + A2;
        HY_An_Thi = A1 + "An%20Thi&state=Tinh%20Hung%20Yen" + A2;
        HY_TT_Nhu_Huynh = A1 + "Nhu%20Quynh&state=Tinh%20Hung%20Yen" + A2;
        Address = new String[]{"Hưng Yên City", "Huyện Yên Mỹ", "Huyện Văn Giang", "Huyện Khoái Châu", "Huyện Ân Thi", "Thị trấn Như Huỳnh"};
    }

    private void KienGiang() {
        KG_TP_RachGia = A1 + "Rach%20Gia&state=Tinh%20Kien%20Giang" + A2;
        KG_TP_Ha_Tien = A1 + "Ha%20Tien&state=Tinh%20Kien%20Giang" + A2;
        KG_Kien_Luong = A1 + "Kien%20Luong&state=Tinh%20Kien%20Giang" + A2;
        KG_Tan_Hiep = A1 + "Tan%20Hiep&state=Tinh%20Kien%20Giang" + A2;
        KG_Giong_Rieng = A1 + "Giong%20Rieng&state=Tinh%20Kien%20Giang" + A2;
        KG_Go_Quao = A1 + "Go%20Quao&state=Tinh%20Kien%20Giang" + A2;
        KG_An_Bien = A1 + "An%20Bien&state=Tinh%20Kien%20Giang" + A2;
        KG_Vinh_Thuan = A1 + "Vinh%20Thuan&state=Tinh%20Kien%20Giang" + A2;
        Address = new String[]{"Rạch Giá City", "Hà Tiên City", "Huyện Kiên Lương", "Huyện Tân Hiệp", "Huyện Giồng Riềng",
                "Huyện Gò Quao", "Huyện An Biên", "Huyện Vĩnh Thuận"};
    }

    private void LaiChau() {
        LC_TP_Lai_Chau = A1 + "Lai%20Chau&state=Tinh%20Lai%20Chau" + A2;
        LC_Tam_Duong = A1 + "Tam%20Duong&state=Tinh%20Lai%20Chau" + A2;
        LC_Phong_Tho = A1 + "Phong%20Tho&state=Tinh%20Lai%20Chau" + A2;
        LC_Than_Uyen = A1 + "Than%20Uyen&state=Tinh%20Lai%20Chau" + A2;
        Address = new String[]{"Lai Châu City", "Huyện Tam Đường", "Huyện Phong Thổ", "Huyện Than Uyên"};
    }

    private void LaoCai() {
        LC_Sa_Pa = A1 + "Sa%20Pa&state=Tinh%20Lao%20Cai" + A2;
        LC_Bat_Xat = A1 + "Bat%20Xat&state=Tinh%20Lao%20Cai" + A2;
        LC_Bac_Ha = A1 + "Bac%20Ha&state=Tinh%20Lao%20Cai" + A2;
        LC_Si_Ma_Cai = A1 + "Si%20Ma%20Cai&state=Tinh%20Lao%20Cai" + A2;
        LC_Thi_tran_Pho_Lu = A1 + "Thi%20Tran%20Pho%20Lu&state=Tinh%20Lao%20Cai" + A2;
        Address = new String[]{"Huyện Sa Pa", "Huyện Bát Xát", "Huyện Bắc Hà", "Huyện Si Ma Cai", "Thị trấn Phố Lu"};
    }

    private void NgheAn() {
        NA_TP_Vinh = A1 + "Vinh&state=Tinh%20Nghe%20An" + A2;
        NA_Dien_Chau = A1 + "Dien%20Chau&state=Tinh%20Nghe%20An" + A2;
        NA_Yen_Thanh = A1 + "Yen%20Thanh&state=Tinh%20Nghe%20An" + A2;
        NA_Hung_Nguyen = A1 + "Hung%20Nguyen&state=Tinh%20Nghe%20An" + A2;
        NA_Quy_Hop = A1 + "Quy%20Hop&state=Tinh%20Nghe%20An" + A2;
        NA_Quy_Chau = A1 + "Quy%20Chau&state=Tinh%20Nghe%20An" + A2;
        NA_Tan_Ky = A1 + "Tan%20Ky&state=Tinh%20Nghe%20An" + A2;
        NA_Anh_Son = A1 + "Anh%20Son&state=Tinh%20Nghe%20An" + A2;
        NA_Con_Cuong = A1 + "Con%20Cuong&state=Tinh%20Nghe%20An" + A2;
        NA_Tuong_Duong = A1 + "Tuong%20Duong&state=Tinh%20Nghe%20An" + A2;
        NA_Nam_Dan = A1 + "Nam%20Dan&state=Tinh%20Nghe%20An" + A2;
        NA_Thanh_Chuong = A1 + "Thanh%20Chuong&state=Tinh%20Nghe%20An" + A2;
        Address = new String[]{"Vinh City", "Huyện Diễn Châu", "Huyện Yên Thành", "Huyện Hưng Nguyên", "Huyện Quỳ Hợp", "Huyện Quỳ Châu", "Huyện Tân Kỳ", "Huyện Anh Sơn", "Huyện Con Cuông", "Huyện Tương Dương", "Huyện Nam Đàn", "Huyện Thanh Chương"};
    }

    private void NinhBinh() {
        NB_TP_Ninh_Binh = A1 + "Thanh%20Pho%20Ninh%20Binh&state=Tinh%20Ninh%20Binh" + A2;
        NB_TP_Tam_Diep = A1 + "Tam%20Diep&state=Tinh%20Ninh%20Binh" + A2;
        NB_Nho_Quan = A1 + "Nho%20Quan&state=Tinh%20Ninh%20Binh" + A2;
        Address = new String[]{"Ninh Bình City", "Tam Điệp City", "Huyện Nho Quan"};
    }

    private void QuangNgai()//
    {
        QN_TP_Quang_Ngai = A1 + "Quang%20Ngai&state=Tinh%20Quang%20Ngai" + A2;
        QN_Ba_To = A1 + "Ba_To&state=Tinh%20Tinh%20Quang%20Ngai" + A2;
        QN_Binh_Son = A1 + "Binh%20Son&state=Tinh%20Tinh%20Quang%20Ngai" + A2;
        QN_Ly_Son = A1 + "Ly%20Son&state=Tinh%20Quang%20Ngai" + A2;
        QN_Minh_Long = A1 + "Minh%20Long&state=Tinh%20Quang%20Ngai" + A2;
        QN_Mo_Duc = A1 + "Mo%20Duc&state=Tinh%20Quang%20Ngai" + A2;
        QN_Nghia_Hanh = A1 + "Nghia%20Hanh&state=Tinh%20Quang%20Ngai" + A2;
        QN_Son_Ha = A1 + "Son%20Ha&state=Tinh%20Quang%20Ngai" + A2;
        QN_Son_Tinh = A1 + "Son%20Tinh&state=Tinh%20Quang%20Ngai" + A2;
        QN_Tra_Bong = A1 + "Tra%20Bong&state=Tinh%20Quang%20Ngai" + A2;
        QN_Tu_Nghia = A1 + "Tu%20Nghia&state=Tinh%20Quang%20Ngai" + A2;
        Address = new String[]{"Quảng Ngãi City", "Huyện Ba Tơ", "Huyện Bình Sơn", "Huyện Lý Sơn", "Huyện Minh Long", "Huyện Mộ Đức",
                "Huyện Nghĩa Hành", "Huyện Sơn Hà", "Huyện Sơn Tịnh", "Huyện Trà Bồng", "Huyện Tư Nghĩa"};
    }

    private void SonLa()//
    {
        SL_TP_Son_La = A1 + "Son%20La&state=Tinh%20Son%20La" + A2;
        SL_Moc_Chau = A1 + "Moc%20Chau&state=Tinh%20Son%20La" + A2;
        SL_Phu_Yen = A1 + "Phu%20Chau&state=Tinh%20Son%20La" + A2;
        SL_Bac_Yen = A1 + "Bac%20Yen&state=Tinh%20Son%20La" + A2;
        SL_Song_Ma = A1 + "Song%20Ma&state=Tinh%20Son%20La" + A2;
        SL_Yen_Chau = A1 + "Yen%20Chau&state=Tinh%20Son%20La" + A2;
        LS_Dinh_Lap = A1 + "Dinh%20Lap&state=Tinh%20Lang%20Son" + A2;
        Address = new String[]{"Sơn La City", "Huyện Bắc Yên", "Huyện Mộc Châu", "Huyện Phù Yên", "Huyện Sông Mã", "Huyện Yên Châu", "Huyện Đinh Lập"};
    }

    private void ThaiBinh()//
    {
        TB_TP_Thai_Binh = A1 + "Thanh%20Pho%20Thai%20Binh&state=Tinh%20Thai%20Binh" + A2;
        TB_Dong_Hung = A1 + "Dong%20Hung&state=Tinh%20Thai%20Binh" + A2;
        TB_Hung_Ha = A1 + "Hung%20Ha&state=Tinh%20Thai%20Binh" + A2;
        TB_Tien_Hai = A1 + "Tien%20Hai&state=Tinh%20Thai%20Binh" + A2;
        TB_Vu_Thu = A1 + "Vu%20Thu&state=Tinh%20Thai%20Binh" + A2;
        Address = new String[]{"Thái Bình City", "Huyện Đông Hưng", "Huyện Hưng Hà", "Huyện Tiền Hải", "Huyện Vũ Thư"};
    }

    private void ThaiNguyen()//
    {
        TN_TP_Thai_Nguyen = A1 + "Thai%20Nguyen&state=Tinh%20Ba%20Ria%20Vung%20Tau%20" + A2;
        Address = new String[]{"Thái Nguyên City"};
    }

    private void ThanhHoa()//
    {
        TH_TP_Thanh_Hoa = A1 + "Thanh%20Hoa&state=Tinh%20Thanh%20Hoa" + A2;
        TH_Bim_Son = A1 + "Bim%20Son&state=Tinh%20Thanh%20Hoa" + A2;
        TH_Quang_Xuong = A1 + "Quang%20Xuong&state=Tinh%20Thanh%20Hoa" + A2;
        TH_Quan_Xuong = A1 + "Quan%20Xuong&state=Tinh%20Thanh%20Hoa" + A2;
        TH_Hau_Loc = A1 + "Hau%20Loc&state=Tinh%20Thanh%20Hoa" + A2;
        TH_Nga_Son = A1 + "Nga%20Son&state=Tinh%20Thanh%20Hoa" + A2;
        TH_Trieu_Son = A1 + "Trieu%20Son&state=Tinh%20Thanh%20Hoa" + A2;
        TH_Nong_Cong = A1 + "Nong%20Cong&state=Tinh%20Thanh%20Hoa" + A2;
        TH_Ngoc_Lac = A1 + "Ngoc%20Lac&state=Tinh%20Thanh%20Hoa" + A2;
        TH_Cam_Thuy = A1 + "Cam%20Thuy&state=Tinh%20Thanh%20Hoa" + A2;
        TH_Vinh_Loc = A1 + "Vinh%20Loc&state=Tinh%20Thanh%20Hoa" + A2;
        TH_Tho_Xuan = A1 + "Tho%20Xuan&state=Tinh%20Thanh%20Hoa" + A2;
        TH_Thuong_Xuan = A1 + "Thuong%20Xuan&state=Tinh%20Thanh%20Hoa" + A2;
        TH_Lang_Chanh = A1 + "Lang%20Chanh&state=Tinh%20Thanh%20Hoa" + A2;
        TH_Quan_Hoa = A1 + "Quan%20Hoa&state=Tinh%20Thanh%20Hoa" + A2;
        TH_Quan_Son = A1 + "Quan%20Son&state=Tinh%20Thanh%20Hoa" + A2;
        TH_Muong_Lat = A1 + "Muong%20Lat&state=Tinh%20Thanh%20Hoa" + A2;
        Address = new String[]{"Thanh Hóa City", "Thị xã Bỉm Sơn", "Huyện Quảng Xương", "Huyện Hậu Lộc",
                "Huyện Nga Sơn", "Huyện Triệu Sơn", "Huyện Nông Cống", "Huyện Ngọc Lặc", "Huyện Cẩm Thủy",
                "Huyện Vĩnh Lộc", "Huyện Thọ Xuân", "Huyện Thường Xuân", "Huyện Lang Chánh", "Huyện Quan Hóa", "Huyện Quan Sơn", "Huyện Mường Lát"};
    }

    private void ThuaThien_Hue()//
    {
        TTH_Phong_Dien = A1 + "Phong%20Dien&state=Tinh%20Thua%20Thien%20Hue" + A2;
        TTH_Phu_Vang = A1 + "Phu%20Vang&state=Tinh%20Thua%20Thien%20Hue" + A2;
        TTH_Phu_Loc = A1 + "Phu%20Loc&state=Tinh%20Thua%20Thien%20Hue" + A2;
        TTH_A_Luoi = A1 + "A%20Luoi&state=Tinh%20Thua%20Thien%20Hue" + A2;
        TTH_TP_Hue = A1 + "Hue&state=Tinh%20Thua%20Thien-Hue" + A2;
        Address = new String[]{"Huyện Phong Điền", "Huyện Phú Vang", "Huyện Phú Lộc", "Huyện A Lưới", "Huế City"};
    }

    private void TienGiang()//
    {
        TG_Cho_Gao = A1 + "Cho%20Gao&state=Tinh%20Tien%20Giang" + A2;
        Address = new String[]{"Huyện Chợ Gạo"};
    }

    private void TraVinh()//
    {
        TV_Cau_Ke = A1 + "Cau%20Ke&state=Tinh%20Tra%20Vinh" + A2;
        TV_Duyen_Hai = A1 + "Duyen%20Hai&state=Tinh%20Tra%20Vinh" + A2;
        TV_Tieu_Can = A1 + "Tieu%20Can&state=Tinh%20Tra%20Vinh" + A2;
        TV_Tra_Cu = A1 + "Tra%20Cu&state=Tinh%20Tra%20Vinh" + A2;
        TV_Chau_Thanh = A1 + "Chau%20Thanh&state=Tinh%20Tra%20Vinh" + A2;
        TV_TP_Tra_Vinh = A1 + "Tra%20Vinh&state=Tinh%20Tra%20Vinh" + A2;
        Address = new String[]{"Huyện Cầu Kè", "Huyện Duyên Hải", "Huyện Tiểu Cần", "Huyện Trà Cú", "Huyện Châu Thành", "Trà Vinh City"};
    }

    private void TuyenQuang()//
    {
        TQ_TP_Tuyen_Quang = A1 + "Thanh%20Pho%20Tuyen%20Quang&state=Tinh%20Tuyen%20Quang" + A2;
        TQ_Son_Duong = A1 + "Son%20Duong&state=Tinh%20Tuyen%20Quang" + A2;
        Address = new String[]{"Tuyên Quang City", "Huyện Sơn Dương"};
    }

    private void VinhPhuc()//
    {
        VP_Lap_Thach = A1 + "Lap%20Thach&state=Tinh%20Vinh%20Phuc" + A2;
        VP_Vinh_Tuong = A1 + "Vinh%20Tuong&state=Tinh%20Vinh%20Phuc" + A2;
        VP_Yen_Lac = A1 + "Yen%20Lac&state=Tinh%20Vinh%20Phuc" + A2;
        Address = new String[]{"Huyện Lập Thạch", "Huyện Vĩnh Tường", "Huyện Yên Lạc"};
    }

    private void YenBai()//
    {
        YB_TP_Yen_Bai = A1 + "Yen%20Bai&state=Tinh%20Yen%20Bai" + A2;
        Address = new String[]{"Yên Bái City"};
    }

    private void HCM() {
        Quan_Binh_Thanh = A1 + "Quan%20Binh%20Thanh&state=Ho%20Chi%20Minh%20City" + A2;
        Huyen_Nha_Be = A1 + "Nha%20Be&state=Ho%20Chi%20Minh%20City" + A2;
        Huyen_Can_Gio = A1 + "Can%20Gio&state=Ho%20Chi%20Minh%20City" + A2;
        Quan_Tan_Phu = A1 + "Quan%20Tan%20Phu&state=Ho%20Chi%20Minh%20City" + A2;
        Quan_Phu_Nhuan = A1 + "Quan%20Phu%20Nhuan&state=Ho%20Chi%20Minh%20City" + A2;
        Quan_Thu_Duc = A1 + "Thu%20Duc&state=Ho%20Chi%20Minh%20City" + A2;
        Huyen_Cu_Chi = A1 + "Cu%20Chi&state=Ho%20Chi%20Minh%20City" + A2;
        Huyen_Hoc_Mon = A1 + "Hoc%20Mon&state=Ho%20Chi%20Minh%20City" + A2;
        Quan_Bay = A1 + "Quan%20Bay&state=Ho%20Chi%20Minh%20City" + A2;
        TP_HCM = A1 + "Ho%20Chi%20Minh%20City&state=Ho%20Chi%20Minh%20City" + A2;
        Quan_Nam = A1 + "Quan%20Nam&state=Ho%20Chi%20Minh%20City" + A2;
        Quan_Mot = A1 + "Quan%20Mot&state=Ho%20Chi%20Minh%20City" + A2;
        Quan_Hai = A1 + "Quan%20Hai&state=Ho%20Chi%20Minh%20City" + A2;
        Quan_Ba = A1 + "Quan%20Ba&state=Ho%20Chi%20Minh%20City" + A2;
        Quan_Bon = A1 + "Quan%20Bon&state=Ho%20Chi%20Minh%20City" + A2;
        Quan_Sau = A1 + "Quan%20Sau&state=Ho%20Chi%20Minh%20City" + A2;
        Quan_Chin = A1 + "Quan%20Chin&state=Ho%20Chi%20Minh%20City" + A2;
        Quan_Muoi = A1 + "Quan%20Muoi&state=Ho%20Chi%20Minh%20City" + A2;
        Quan_Muoi_Mot = A1 + "Quan%20Muoi%20Mot&state=Ho%20Chi%20Minh%20City" + A2;
        Address = new String[]{"Quận Bình Thạnh", "Huyện Nhà Bè", "Huyện Cần Giờ", "Quận Tân Phú", "Quận Phú Nhuận",
                "Quận Thủ Đức", "Huyện Củ Chi", "Huyện Hóc Môn", "Hồ Chí Minh City", "Quận 5", "Quận 1", "Quận 2", "Quận 3"
                , "Quận 4", "Quận 6", "Quận 7", "Quận 9", "Quận 10", "Quận 11"};

    }

    private void HaGiang() {
        HG_TP_Ha_Giang = A1 + "Thanh%20Pho%20Ha%20Giang&state=Tinh%20Ha%20Giang" + A2;
        Address = new String[]{"Hà Giang City"};
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
                switch (position) {
                    case 0:
                        BaRia_VungTau();
                        huyen();
                        break;
                    case 1:
                        BacLieu();
                        huyen();
                        break;
                    case 2:
                        BacKan();
                        huyen();
                        break;
                    case 3:
                        BacGiang();
                        huyen();
                        break;
                    case 4:
                        BacNinh();
                        huyen();
                        break;
                    case 5:
                        BenTre();
                        huyen();
                        break;
                    case 6:
                        BinhDuong();
                        huyen();
                        break;
                    case 7:
                        BinhPhuoc();
                        huyen();
                        break;
                    case 8:
                        BinhThuan();
                        huyen();
                        break;
                    case 9:
                        CaMau();
                        huyen();
                        break;
                    case 10:
                        CaoBang();
                        huyen();
                        break;
                    case 11:
                        DienBien();
                        huyen();
                        break;
                    case 12:
                        DongNai();
                        huyen();
                        break;
                    case 13:
                        HaGiang();
                        huyen();
                        break;
                    case 14:
                        HaTinh();
                        huyen();
                        break;
                    case 15:
                        HaiDuong();
                        huyen();
                        break;
                    case 16:
                        HungYen();
                        huyen();
                        break;
                    case 17:
                        KienGiang();
                        huyen();
                        break;
                    case 18:
                        LaiChau();
                        huyen();
                        break;
                    case 19:
                        LaoCai();
                        huyen();
                        break;
                    case 20:
                        NgheAn();
                        huyen();
                        break;
                    case 21:
                        NinhBinh();
                        huyen();
                        break;
                    case 22:
                        QuangNgai();
                        huyen();
                        break;
                    case 23:
                        SonLa();
                        huyen();
                        break;
                    case 24:
                        ThaiBinh();
                        huyen();
                        break;
                    case 25:
                        ThaiNguyen();
                        huyen();
                        break;
                    case 26:
                        ThanhHoa();
                        huyen();
                        break;
                    case 27:
                        ThuaThien_Hue();
                        huyen();
                        break;
                    case 28:
                        TienGiang();
                        huyen();
                        break;
                    case 29:
                        TraVinh();
                        huyen();
                        break;
                    case 30:
                        TuyenQuang();
                        huyen();
                        break;
                    case 31:
                        VinhPhuc();
                        huyen();
                        break;
                    case 32:
                        YenBai();
                        huyen();
                        break;
                    case 33:
                        HCM();
                        huyen();
                        break;
                    case 34:
                        HaNoi();
                        huyen();
                        break;
                    case 35:
                        QuangNinh();
                        huyen();
                        break;
                    case 36:
                        TayNinh();
                        huyen();
                        break;
                    case 37:
                        LangSon();
                        huyen();

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
                        A2 = "&country=Vietnam&key=02e0b493-ba7c-45aa-a896-d210bc2c4e8d";
                        break;
                    case 1:
                        A2 = "&country=Vietnam&key=8a86bcf0-789d-4ae8-963d-c63f8791196e";
                        break;
                    case 2:
                        A2 = "&country=Vietnam&key=51bda31f-419b-4338-96b3-8b5882fcd6e0";
                        break;
                    case 3:
                        A2 = "&country=Vietnam&key=73a5b5e6-f504-4faa-9b16-93498eda0346";
                        break;
                    case 4:
                        A2 = "&country=Vietnam&key=fdff27a0-be6a-43d4-baa7-e9a2c51be6c2";
                        break;
                    case 5:
                        A2 = "&country=Vietnam&key=0209e5ba-1a34-47e3-ac69-cf1e87e73392";
                        break;
                    case 6:
                        A2 = "&country=Vietnam&key=34ef7f42-e37c-46ae-a41a-c5a425498bc5";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void doc(String chuoi) {
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

                    String a = kk > 300 ? "Tình trạng không khí Nguy hiểm với con người"
                            : kk >= 201 ? "Tình trạng không khí rất ô nhiễm"
                            : kk >= 151 ? "Tình trạng không khí ô nhiễm"
                            : kk >= 101 ? "Tình trạng không khí không tốt cho nhóm người nhạy cảm"
                            : kk >= 51 ? "Không khí thoáng"
                            : "Không khí trong lành";

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
                    txtda.setText("Độ ẩm :" + Humidity + "%");
                    txtda.setBackgroundColor(0xff333333);
                    txtda.setTextColor(0xffFFFACD);
                    txtnd.setText("Nhiệt độ :" + Temperature + "C");
                    txtnd.setBackgroundColor(0xff333333);
                    txtnd.setTextColor(0xffFFFACD);
                    txttdg.setText("Tốc độ gió : " + wind_speed + "Km/h");
                    txttdg.setBackgroundColor(0xff333333);
                    txttdg.setTextColor(0xffFFFACD);
                    txtkk.setText("AQI : " + Aqi);
                    txtkk.setTextColor(0xffFFFACD);
                    txtkk.setBackgroundColor(0xff333333);
                    img.setImageResource(kq);
                    imgtt.setBackgroundResource(kq2);
                    txtten.setText("Nước : " + country + "\nTỉnh : " + state + "\nHuyện : " + city);
                    txtten.setBackgroundColor(0xff333333);
                    txtten.setTextColor(0xffFFFACD);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(tintuc.this, "Vui lòng chuyển đổi Server khác", Toast.LENGTH_SHORT).show();
                Log.d("Lỗi nè", error.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private void doc4() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://nckh-2023.herokuapp.com/123/2", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonObject = response.getJSONArray("dt");
                    for (int i = 0; i < jsonObject.length(); i++) {
                        JSONObject jsonObject1 = jsonObject.getJSONObject(i);
                        String ten = jsonObject1.getString("_id");
                        Toast.makeText(tintuc.this, ten, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // Toast.makeText(tintuc.this, "Vui lòng chuyển đổi Server khác", Toast.LENGTH_SHORT).show();
                Log.d("Lỗi nè", error.toString());
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
                Toast.makeText(tintuc.this, "lỗi", Toast.LENGTH_SHORT).show();
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
                doc4();
                switch (Address[vt]) {
                    case "Thủ Dầu Một City":
                        doc(BD_TDM);
                        break;
                    case "Dĩ An City":
                        doc(BD_DiAn);
                        break;
                    case "Huyện Dầu Tiếng":
                        doc(BD_DauTieng);
                        break;
                    case "Huyện Bến Cát":
                        doc(BD_BenCat);
                        break;
                    case "Vũng Tàu City":
                        doc(BRVT_TP_BRVT);
                        break;
                    case "Huyện Đất Đỏ":
                        doc(BRVT_DatDo);
                        break;
                    case "Bạc Liêu City":
                        doc(BL_TP_BacLieu);
                        break;
                    case "Bắc Kạn City":
                        doc(BC_TP_Bac_Kan);
                        break;
                    case "Huyện Chợ Mới":
                        doc(BC_Cho_Moi);
                        break;
                    case "Bắc Giang City":
                        doc(BG_TP_Bac_Giang);
                        break;
                    case "Bắc Ninh City":
                        doc(BN_TP_BacNinh);
                        break;
                    case "Huyện Gia Bình":
                        doc(BN_Gia_Binh);
                        break;
                    case "Bến Tre City":
                        doc(BT_TP_Ben_Tre);
                        break;
                    case "Huyện Chợ Lách":
                        doc(BT_Cho_Lach);
                        break;
                    case "Huyện Giồng Trôm":
                        doc(BT_Giong_Trom);
                        break;
                    case "Huyện Bình Đại":
                        doc(BT_Binh_Dai);
                        break;
                    case "Huyện Ba Tri":
                        doc(BT_Ba_Tri);
                        break;
                    case "Huyện Thạnh Phú":
                        doc(BT_Thanh_Phu);
                        break;
                    case "Thị Xã Bình Long":
                        doc(BP_Binh_Long);
                        break;
                    case "Huyện Chơn Thành":
                        doc(BP_Chon_Thanh);
                        break;
                    case "Huyện Bù đốp":
                        doc(BP_Bu_Dop);
                        break;
                    case "Phan Thiết City":
                        doc(BTHUAN_Phan_Thiet);
                        break;
                    case "Thị xã La Gi":
                        doc(BL_TP_BacLieu);
                        break;
                    case "Huyện Phú Quý":
                        doc(BTHUAN_Phu_Quy);
                        break;
                    case "Huyện Tánh Linh":
                        doc(BTHUAN_Tanh_Linh);
                        break;
                    case "Cà Mau City":
                        doc(CM_TP_Ca_Mau);
                        break;
                    case "Huyện U Minh":
                        doc(CM_U_Minh);
                        break;
                    case "Huyện Thới Bình":
                        doc(CM_Thoi_Binh);
                        break;
                    case "Huyện Trần Văn Thời":
                        doc(CM_Tran_Van_Thoi);
                        break;
                    case "Huyện Cái Nước":
                        doc(CM_Cai_Nuoc);
                        break;
                    case "Huyện Năm Căn":
                        doc(CM_Nam_Can);
                        break;
                    case "Huyện Phú Tân":
                        doc(CM_Phu_Tan);
                        break;
                    case "Huyện Ngọc Hiển":
                        doc(CM_Ngoc_Hien);
                        break;
                    case "Cao Bằng City":
                        doc(CB_TP_Cao_Bang);
                        break;
                    case "Điện Biên Phủ City":
                        doc(DB_TP_Dien_Bien_Phu);
                        break;
                    case "Huyện Điện Biên Đông":
                        doc(DB_Dien_Bien_Dong);
                        break;
                    case "Huyện Mường Ảng":
                        doc(DB_Muong_Ang);
                        break;
                    case "Huyện Mường Chà":
                        doc(DB_Muong_Cha);
                        break;
                    case "Huyện Mường Nhé":
                        doc(DB_Muong_Nhe);
                        break;
                    case "Biên Hòa City":
                        doc(DN_TP_Bien_Hoa);
                        break;
                    case "Long Khánh City":
                        doc(DN_TP_Long_Khanh);
                        break;
                    case "Huyện Long Thành":
                        doc(DN_Long_Thanh);
                        break;
                    case "Huyện Nhơn Trạch":
                        doc(DN_Nhon_Trach);
                        break;
                    case "Huyện Tân Phú":
                        doc(DN_Tan_Phu);
                        break;
                    case "Huyện Thống Nhất":
                        doc(DN_Thong_Nhat);
                        break;
                    case "Huyện Trảng Bom":
                        doc(DN_Trang_Bom);
                        break;
                    case "Hà Tĩnh City":
                        doc(HT_TP_Ha_Tinh);
                        break;
                    case "Huyện Cẩm Xuyên":
                        doc(HT_Cam_Xuyen);
                        break;
                    case "Huyện Đức Thọ":
                        doc(HT_Duc_Tho);
                        break;
                    case "Huyện Hương Khê":
                        doc(HT_Huong_Khe);
                        break;
                    case "Huyện Kỳ Anh":
                        doc(HT_Ky_Anh);
                        break;
                    case "Huyện Nghi Xuân":
                        doc(HT_Nghi_Xuan);
                        break;
                    case "Huyện Thạch Hà":
                        doc(HT_Thach_Ha);
                        break;
                    case "Huyện Vũ Quang":
                        doc(HT_Vu_Quang);
                        break;
                    case "Hải Dương City":
                        doc(HD_TP_Hai_Duong);
                        break;
                    case "Thị xã Kinh Môn":
                        doc(HD_Kinh_Mon);
                        break;
                    case "Huyện Thanh Hà":
                        doc(HD_Thanh_Ha);
                        break;
                    case "Huyện Tứ Kỳ":
                        doc(HD_Tu_Ky);
                        break;
                    case "City Hưng Yên":
                        doc(HY_TP_Hung_Yen);
                        break;
                    case "Huyện Yên Mỹ":
                        doc(HY_Yen_My);
                        break;
                    case "Huyện Văn Giang":
                        doc(HY_Van_Giang);
                        break;
                    case "Huyện Khoái Châu":
                        doc(HY_Khoai_Chau);
                        break;
                    case "Huyện Ân Thi":
                        doc(HY_An_Thi);
                        break;
                    case "Rạch Giá City":
                        doc(KG_TP_RachGia);
                        break;
                    case "Hà Tiên City":
                        doc(KG_TP_Ha_Tien);
                        break;
                    case "Huyện Kiên Lương":
                        doc(KG_Kien_Luong);
                        break;
                    case "Huyện Tân Hiệp":
                        doc(KG_Tan_Hiep);
                        break;
                    case "Huyện Giồng Riềng":
                        doc(KG_Giong_Rieng);
                        break;
                    case "Huyện Gò Quao":
                        doc(KG_Go_Quao);
                        break;
                    case "Huyện An Biên":
                        doc(KG_An_Bien);
                        break;
                    case "Huyện Vĩnh Thuận":
                        doc(KG_Vinh_Thuan);
                        break;
                    case "Lai Châu City":
                        doc(LC_TP_Lai_Chau);
                        break;
                    case "Huyện Tam Đường":
                        doc(LC_Tam_Duong);
                        break;
                    case "Huyện Phong Thổ":
                        doc(LC_Phong_Tho);
                        break;
                    case "Huyện Than Uyên":
                        doc(LC_Than_Uyen);
                        break;
                    case "Huyện Sa Pa":
                        doc(LC_Sa_Pa);
                        break;
                    case "Huyện Bát Xát":
                        doc(LC_Bat_Xat);
                        break;
                    case "Huyện Bắc Hà":
                        doc(LC_Bac_Ha);
                        break;
                    case "Huyện Si Ma Cai":
                        doc(LC_Si_Ma_Cai);
                        break;
                    case "Vinh City":
                        doc(NA_TP_Vinh);
                        break;
                    case "Huyện Diễn Châu":
                        doc(NA_Dien_Chau);
                        break;
                    case "Huyện Yên Thành":
                        doc(NA_Yen_Thanh);
                        break;
                    case "Huyện Hưng Nguyên":
                        doc(NA_Hung_Nguyen);
                        break;
                    case "Huyện Quỳ Hợp":
                        doc(NA_Quy_Hop);
                        break;
                    case "Huyện Quỳ Châu":
                        doc(NA_Quy_Chau);
                        break;
                    case "Huyện Tân Kỳ":
                        doc(NA_Tan_Ky);
                        break;
                    case "Huyện Anh Sơn":
                        doc(NA_Anh_Son);
                        break;
                    case "Huyện Con Cuông":
                        doc(NA_Con_Cuong);
                        break;
                    case "Huyện Tương Dương":
                        doc(NA_Tuong_Duong);
                        break;
                    case "Huyện Nam Đàn":
                        doc(NA_Nam_Dan);
                        break;
                    case "Huyện Thanh Chương":
                        doc(NA_Thanh_Chuong);
                        break;
                    case "Ninh Bình City":
                        doc(NB_TP_Ninh_Binh);
                        break;
                    case "Tam Điệp City":
                        doc(NB_TP_Tam_Diep);
                        break;
                    case "Huyện Nho Quan":
                        doc(NB_Nho_Quan);
                        break;
                    case "Quảng Ngãi City":
                        doc(QN_TP_Quang_Ngai);
                        break;
                    case "Huyện Ba Tơ":
                        doc(QN_Ba_To);
                        break;
                    case "Huyện Bình Sơn":
                        doc(QN_Binh_Son);
                        break;
                    case "Huyện Lý Sơn":
                        doc(QN_Ly_Son);
                        break;
                    case "Huyện Minh Long":
                        doc(QN_Minh_Long);
                        break;
                    case "Huyện Mộ Đức":
                        doc(QN_Mo_Duc);
                        break;
                    case "Huyện Nghĩa Hành":
                        doc(QN_Nghia_Hanh);
                        break;
                    case "Huyện Sơn Hà":
                        doc(QN_Son_Ha);
                        break;
                    case "Huyện Sơn Tịnh":
                        doc(QN_Son_Tinh);
                        break;
                    case "Huyện Trà Bồng":
                        doc(QN_Tra_Bong);
                        break;
                    case "Huyện Tư Nghĩa":
                        doc(QN_Tu_Nghia);
                        break;
                    case "Sơn La City":
                        doc(SL_TP_Son_La);
                        break;
                    case "Huyện Bắc Yên":
                        doc(SL_Bac_Yen);
                        break;
                    case "Huyện Mộc Châu":
                        doc(SL_Moc_Chau);
                        break;
                    case "Huyện Phù Yên":
                        doc(SL_Phu_Yen);
                        break;
                    case "Huyện Sông Mã":
                        doc(SL_Song_Ma);
                        break;
                    case "Huyện Yên Châu":
                        doc(SL_Yen_Chau);
                        break;
                    case "Thái Bình City":
                        doc(TB_TP_Thai_Binh);
                        break;
                    case "Huyện Đông Hưng":
                        doc(TB_Dong_Hung);
                        break;
                    case "Huyện Hưng Hà":
                        doc(TB_Hung_Ha);
                        break;
                    case "Huyện Tiền Hải":
                        doc(TB_Tien_Hai);
                        break;
                    case "Huyện Vũ Thư":
                        doc(TB_Vu_Thu);
                        break;
                    case "Thái Nguyên City":
                        doc(TN_TP_Thai_Nguyen);
                        break;
                    case "Thanh Hóa City":
                        doc(TH_TP_Thanh_Hoa);
                        break;
                    case "Thị xã Bỉm Sơn":
                        doc(TH_Bim_Son);
                        break;
                    case "Huyện Quảng Xương":
                        doc(TH_Quan_Xuong);
                        break;
                    case "Huyện Hậu Lộc":
                        doc(TH_Hau_Loc);
                        break;
                    case "Huyện Nga Sơn":
                        doc(TH_Nga_Son);
                        break;
                    case "Huyện Triệu Sơn":
                        doc(TH_Trieu_Son);
                        break;
                    case "Huyện Nông Cống":
                        doc(TH_Nong_Cong);
                        break;
                    case "Huyện Ngọc Lặc":
                        doc(TH_Ngoc_Lac);
                        break;
                    case "Huyện Cẩm Thủy":
                        doc(TH_Cam_Thuy);
                        break;
                    case "Huyện Vĩnh Lộc":
                        doc(TH_Vinh_Loc);
                        break;
                    case "Huyện Thọ Xuân":
                        doc(TH_Tho_Xuan);
                        break;
                    case "Huyện Thường Xuân":
                        doc(TH_Thuong_Xuan);
                        break;
                    case "Huyện Lang Chánh":
                        doc(TH_Lang_Chanh);
                        break;
                    case "Huyện Quan Hóa":
                        doc(TH_Quan_Hoa);
                        break;
                    case "Huyện Quan Sơn":
                        doc(TH_Quan_Son);
                        break;
                    case "Huyện Mường Lát":
                        doc(TH_Muong_Lat);
                        break;
                    case "Huyện Phong Điền":
                        doc(TTH_Phong_Dien);
                        break;
                    case "Huyện Phú Vang":
                        doc(TTH_Phu_Vang);
                        break;
                    case "Huyện Phú Lộc":
                        doc(TTH_Phu_Loc);
                        break;
                    case "Huyện A Lưới":
                        doc(TTH_A_Luoi);
                        break;
                    case "Huyện Chợ Gạo":
                        doc(TG_Cho_Gao);
                        break;
                    case "Huyện Cầu Kè":
                        doc(TV_Cau_Ke);
                        break;
                    case "Huyện Châu thành":
                        doc(BT_Chau_Thanh);
                        break;
                    case "Huyện Duyên Hải":
                        doc(TV_Duyen_Hai);
                        break;
                    case "Huyện Tiểu Cần":
                        doc(TV_Tieu_Can);
                        break;
                    case "Huyện Trà Cú":
                        doc(TV_Tra_Cu);
                        break;
                    case "Tuyên Quang City":
                        doc(TQ_TP_Tuyen_Quang);
                        break;
                    case "Huyện Sơn Dương":
                        doc(TQ_Son_Duong);
                        break;
                    case "Huyện Lập Thạch":
                        doc(VP_Lap_Thach);
                        break;
                    case "Huyện Vĩnh Tường":
                        doc(VP_Vinh_Tuong);
                        break;
                    case "Huyện Yên Lạc":
                        doc(VP_Yen_Lac);
                        break;
                    case "Yên Bái City":
                        doc(YB_TP_Yen_Bai);
                        break;
                    case "Hà Giang City":
                        doc(HG_TP_Ha_Giang);
                        break;
                    case "Quận Bình Thạnh":
                        doc(Quan_Binh_Thanh);
                        break;
                    case "Huyện Nhà Bè":
                        doc(Huyen_Nha_Be);
                        break;
                    case "Huyện Cần Giờ":
                        doc(Huyen_Can_Gio);
                        break;
                    case "Quận Tân Phú":
                        doc(Quan_Tan_Phu);
                        break;
                    case "Quận Phú Nhuận":
                        doc(Quan_Phu_Nhuan);
                        break;
                    case "Quận Thủ Đức":
                        doc(Quan_Thu_Duc);
                        break;
                    case "Huyện Củ Chi":
                        doc(Huyen_Cu_Chi);
                        break;
                    case "Huyện Hóc Môn":
                        doc(Huyen_Hoc_Mon);
                        break;
                    case "Quận Đông Anh":
                        doc(HNDong_Anh);
                        break;
                    case "Thủ đô Hà Nội":
                        doc(HNHaNoi);
                        break;
                    case "Quận Hoàn Kiếm":
                        doc(HNHoan_Kiem);
                        break;
                    case "Quận Cầu Giấy":
                        doc(HNCau_Giay);
                        break;
                    case "Quận Đống Đa":
                        doc(HNDong_Da);
                        break;
                    case "Quận Hà Đông":
                        doc(HNHa_Dong);
                        break;
                    case "Quận Tây Hồ":
                        doc(HNTay_Ho);
                        break;
                    case "Quận Thanh Xuân":
                        doc(HNThanh_Xuan);
                        break;
                    case "Quận Sơn Tây":
                        doc(HNSon_Tay);
                        break;
                    case "Quận Sóc Sơn":
                        doc(HNSoc_Son);
                        break;
                    case "Quận Mê Linh":
                        doc(HNMe_Linh);
                        break;
                    case "Quận Phú Xuyên":
                        doc(HNPhu_Xuyen);
                        break;
                    case "Quận Thạch Thất":
                        doc(HNThach_That);
                        break;
                    case "Quận Quốc Oai":
                        doc(HNQuoc_Oai);
                        break;
                    case "Quận Thường Tín":
                        doc(HNThuong_Tin);
                        break;
                    case "Quận Phúc Thọ":
                        doc(HNPhuc_Tho);
                        break;
                    case "Quận 7":
                        doc(Quan_Bay);
                        break;
                    case "Hồ Chí Minh City":
                        doc(TP_HCM);
                        break;
                    case "Quận 5":
                        doc(Quan_Nam);
                        break;
                    case "Quận 1":
                        doc(Quan_Mot);
                        break;
                    case "Quận 2":
                        doc(Quan_Hai);
                        break;
                    case "Quận 3":
                        doc(Quan_Ba);
                        break;
                    case "Quận 4":
                        doc(Quan_Bon);
                        break;
                    case "Quận 6":
                        doc(Quan_Sau);
                        break;
                    case "Quận 9":
                        doc(Quan_Chin);
                        break;
                    case "Quận 10":
                        doc(Quan_Muoi);
                        break;
                    case "Quận 11":
                        doc(Quan_Muoi_Mot);
                        break;
                    case "Huyện Châu Thành":
                        doc(TV_Chau_Thanh);
                        break;
                    case "Huyện La Gi":
                        doc(BTHUAN_La_Gi);
                        break;
                    case "Huyện Kim Thành":
                        doc(HD_Kim_Thanh);
                        break;
                    case "Cẩm Phả City":
                        doc(QN_TPCam_Pha);
                        break;
                    case "Móng Cái City":
                        doc(QN_TPMong_Cai);
                        break;
                    case "Thị xã Quảng Hà":
                        doc(QNHai_Ha);
                        break;
                    case "Hạ Long City":
                        doc(QN_TPHa_Long);
                        break;
                    case "Thị trấn Phố Lu":
                        doc(LC_Thi_tran_Pho_Lu);
                        break;
                    case "Tây Ninh City":
                        doc(TN_Tay_Ninh);
                        break;
                    case "Huyện Trảng Bàng":
                        doc(TN_TrangBang);
                        break;
                    case "Huyện châu thành":
                        doc(TN_Chau_Thanh);
                        break;
                    case "Huyện Tân Châu":
                        doc(TN_Tan_Chau);
                        break;
                    case "Uông Bí City":
                        doc(QN_TPUong_Bi);
                        break;
                    case "Huyện Cô Tô":
                        doc(QNCo_To);
                        break;
                    case "Thị xã Quảng Yên":
                        doc(QNQuang_Yen);
                        break;
                    case "Huyện Hòa Thành":
                        doc(TN_Hoa_Thanh);
                        break;
                    case "Huyện Bến Cầu":
                        doc(TN_Ben_Cau);
                        break;
                    case "Huyện Dương Minh Châu":
                        doc(TN_Duong_Minh_Chau);
                        break;
                    case "Huyện Gò Dầu":
                        doc(TN_Go_Dau);
                        break;
                    case "Huyện Tân Biên":
                        doc(TN_Tan_Bien);
                        break;
                    case "Huế City":
                        doc(TTH_TP_Hue);
                        break;
                    case "Thị trấn Như Huỳnh":
                        doc(HY_TT_Nhu_Huynh);
                        break;
                    case "Trà Vinh City":
                        doc(TV_TP_Tra_Vinh);
                        break;
                    case "Lạng Sơn City":
                        doc(LS_TP_Lang_Son);
                        break;
                    case "Huyện Bình Gia":
                        doc(LS_Binh_Gia);
                        break;
                    case "Huyện Bắc Sơn":
                        doc(LS_Bac_Son);
                        break;
                    case "Huyện Văn Quan":
                        doc(LS_Van_Quan);
                        break;
                    case "Huyện Lộc Bình":
                        doc(LS_Loc_Binh);
                        break;
                    case "Huyện Đinh Lập":
                        doc(LS_Dinh_Lap);
                        break;
                    case "Huyện Hữu Lũng":
                        doc(LS_Huu_Lung);
                        break;
                    case "Thị trấn Neo":
                        doc(BG_TT_Neo);
                        break;
                    case "Thị trấn Chờ":
                        doc(BN_TT_Cho);
                        break;
                    case "Thị trấn Lim":
                        doc(BN_TT_Lim);
                        break;
                    case "Thị trấn Phố Mới":
                        doc(BN_TT_Pho_Moi);
                        break;
                    case "Thị trấn Trời":
                        doc(QN_TT_Troi);
                        break;
                }
            }
        }
    }
}
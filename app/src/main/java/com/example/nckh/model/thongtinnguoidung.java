package com.example.nckh.model;

public class thongtinnguoidung
{
    private String hoten;
    private String matkhau;
    private String ngaysinh;
    private String diachi;
    private String sdt;
    private String key_chain;

    public thongtinnguoidung(String hoten, String matkhau, String ngaysinh, String diachi, String sdt, String key_chain) {
        this.hoten = hoten;
        this.matkhau = matkhau;
        this.ngaysinh = ngaysinh;
        this.diachi = diachi;
        this.sdt = sdt;
        this.key_chain = key_chain;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getKey_chain() {
        return key_chain;
    }

    public void setKey_chain(String key_chain) {
        this.key_chain = key_chain;
    }
}

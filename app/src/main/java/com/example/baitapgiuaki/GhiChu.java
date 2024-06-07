package com.example.baitapgiuaki;

import java.io.Serializable;

public class GhiChu implements Serializable {
    String maGhiChu, tieuDe, noiDung, ngayTao;
    boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }
    public void setChecked(boolean isChecked){
        this.isChecked=isChecked;
    }

    public GhiChu(String maGhiChu, String tieuDe, String noiDung, String ngayTao, boolean isChecked) {
        this.maGhiChu = maGhiChu;
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
        this.ngayTao = ngayTao;
        this.isChecked = isChecked;
    }
    public GhiChu(String maGhiChu, String tieuDe, String noiDung) {
        this.maGhiChu = maGhiChu;
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
    }

    public GhiChu() {
    }

    public String getMaGhiChu() {
        return maGhiChu;
    }

    public void setMaGhiChu(String maGhiChu) {
        this.maGhiChu = maGhiChu;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    @Override
    public String toString() {
        return "GhiChu{" +
                "maGhiChu='" + maGhiChu + '\'' +
                ", tieuDe='" + tieuDe + '\'' +
                ", noiDung='" + noiDung + '\'' +
                ", ngayTao='" + ngayTao + '\'' +
                ", isChecked=" + isChecked +
                '}';
    }
}

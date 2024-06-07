package com.example.baitapgiuaki;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class ThongKe extends AppCompatActivity {
    TextView tvExit;
    public static List<GhiChu> data_ThongKe = new ArrayList<>();
    public static ListView lvThongKe;
    public static AdapterGhiChu adapterGhiChu;
    DBHoanThanh dbHoanThanh;
    TextView tvThongKe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        setControl();
        setEvent();
    }

    private void setEvent() {
        dbHoanThanh = new DBHoanThanh(this);
        data_ThongKe= KhoiTao();
        adapterGhiChu = new AdapterGhiChu(ThongKe.this,R.layout.item_ghichu,data_ThongKe);
        lvThongKe.setAdapter(adapterGhiChu);
        tvExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        LocalDate localDate = LocalDate.now();
        String month = String.valueOf(localDate.getMonthValue());
        String msg="Tổng số công việc đã hoàn thành cong tháng "+month+" là: " +TongCongViec();
        tvThongKe.setText(msg);
    }
    private int TongCongViec(){
        LocalDate localDate = LocalDate.now();
        String month = String.valueOf(localDate.getMonthValue());
        int tng =0;
        for (GhiChu data:data_ThongKe) {
            String thang = data.getNgayTao().substring(3,5);
            if (thang.equals(month)){
                tng++;
            }
        }
        return tng;
    }
    private List<GhiChu> KhoiTao(){
        data_ThongKe.clear();
        List<GhiChu> ds = new ArrayList<>();
        if (dbHoanThanh.DocDL()!=null) {
            ds.addAll(dbHoanThanh.DocDL());
        }
        return ds;
    }
    private void setControl() {
        tvExit = findViewById(R.id.tvExit);
        lvThongKe = findViewById(R.id.lvthongke);
        tvThongKe = findViewById(R.id.tvThongke);
    }
}
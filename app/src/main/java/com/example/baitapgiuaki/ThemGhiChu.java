package com.example.baitapgiuaki;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.Calendar;
import java.util.Locale;

import pk_Fragment.BanNhapFragment;
import pk_Fragment.DanhDauFragment;
import pk_Fragment.GhiChuFragment;

public class ThemGhiChu extends AppCompatActivity {
    EditText edtTieuDe, edtNoiDung;
    TextView tvThoat, tvTuyChon;
    Button btnThem;
    DBGhiChu dbGhiChu;
    DBBanNhap dbBanNhap;
    GhiChu data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_ghi_chu);

        setControl();
        setEvent();
    }

    private void setEvent() {
        data = (GhiChu) getIntent().getSerializableExtra("nhap");
        if (data!=null){
            edtTieuDe.setText(data.getTieuDe());
            edtNoiDung.setText(data.getNoiDung());
        }
        dbGhiChu = new DBGhiChu(ThemGhiChu.this);
        dbBanNhap = new DBBanNhap(ThemGhiChu.this);
        tvThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GhiChu item;
                // Sử dụng Calendar
                Calendar calendar = Calendar.getInstance();

                // Sử dụng SimpleDateFormat để chuyển đổi ngày thành chuỗi
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String formattedDate = dateFormat.format(calendar.getTime());


                int count = GhiChuFragment.ds;
                String ma = "";

                for (int i = 0; i < count; i++) {
                    if (!("GC" + i).equals(GhiChuFragment.data_GhiChu.get(i).getMaGhiChu())) {
                        ma = "GC" + i;
                        break;
                    }
                }
                ++count;
                if (ma.equals("")) {
                    ma += "GC" + count;
                }
                item = new GhiChu(ma, edtTieuDe.getText().toString(), edtNoiDung.getText().toString(), formattedDate, false);
                if (KtraItem(item)){
                    BanNhapFragment.adapterBanNhap = new AdapterBanNhap(ThemGhiChu.this, R.layout.item_bannhap,BanNhapFragment.data_Bannhap);
                    BanNhapFragment.lvBanNhap.setAdapter(BanNhapFragment.adapterBanNhap);
                    BanNhapFragment.adapterBanNhap.notifyDataSetChanged();
                }
                GhiChuFragment.data_GhiChu.add(item);
                GhiChuFragment.adapter_GhiChu = new AdapterGhiChu(ThemGhiChu.this, R.layout.item_ghichu, GhiChuFragment.data_GhiChu);
                GhiChuFragment.lvGhiChu.setAdapter(GhiChuFragment.adapter_GhiChu);
                GhiChuFragment.adapter_GhiChu.notifyDataSetChanged();
                dbGhiChu.ThemDL(item);
                //Xóa trong danh sách bản nháp
                for (GhiChu gc : BanNhapFragment.data_Bannhap) {
                    if (gc != null && data != null && gc.getMaGhiChu() != null && data.getMaGhiChu() != null &&
                            gc.getMaGhiChu().equals(data.getMaGhiChu())) {
                        BanNhapFragment.data_Bannhap.remove(gc);
                        BanNhapFragment.adapterBanNhap = new AdapterBanNhap(ThemGhiChu.this, R.layout.item_bannhap, BanNhapFragment.data_Bannhap);
                        BanNhapFragment.lvBanNhap.setAdapter(BanNhapFragment.adapterBanNhap);
                        BanNhapFragment.adapterBanNhap.notifyDataSetChanged();
                        break;
                    }
                }
                onBackPressed();
            }
        });
        registerForContextMenu(tvTuyChon);
    }
    private boolean KtraItem(GhiChu item){
        for (int i = 0; i < BanNhapFragment.Dem; i++){
            if (item.getMaGhiChu().equals(BanNhapFragment.data_Bannhap.get(i).getMaGhiChu())){
                BanNhapFragment.data_Bannhap.remove(item);
                return true;
            }
        }
        return false;
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context, menu);
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.mnLuuNhap){
            GhiChu tam;
            int nhap = BanNhapFragment.LayCount();
            String ma = "";

                    for (int j = 0; j < nhap; j++){
                        if (!(ma).equals(BanNhapFragment.data_Bannhap.get(j).getMaGhiChu())) {
                            ma = "BN" +j;
                            break;
                        }
                    }


            if (ma.equals("")) {
                ma += "GC" + nhap+1;
            }
            tam = new GhiChu(ma, edtTieuDe.getText().toString(), edtNoiDung.getText().toString());
            BanNhapFragment.data_Bannhap.add(tam);
            BanNhapFragment.adapterBanNhap = new AdapterBanNhap(ThemGhiChu.this, R.layout.item_bannhap,BanNhapFragment.data_Bannhap);
            BanNhapFragment.lvBanNhap.setAdapter(BanNhapFragment.adapterBanNhap);
            BanNhapFragment.adapterBanNhap.notifyDataSetChanged();
            dbBanNhap.ThemBN(tam);
            Toast.makeText(this, "Đã lưu vào nháp", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
        return super.onContextItemSelected(item);
    }

    private void setControl() {
        edtTieuDe = findViewById(R.id.edtTieuDe);
        edtNoiDung = findViewById(R.id.edtNoiDung);
        btnThem = findViewById(R.id.btnThem);
        tvThoat = findViewById(R.id.tvQuayLai);
        tvTuyChon = findViewById(R.id.tvTuyChon);
    }
}
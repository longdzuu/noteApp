package com.example.baitapgiuaki;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import pk_Fragment.BanNhapFragment;
import pk_Fragment.DanhDauFragment;
import pk_Fragment.GhiChuFragment;

public class ChiTietGhiChu extends AppCompatActivity {
    TextView tvExit, tvTieuDe,tvTuyChon;
    EditText edtNoiDung;
    DBGhiChu dbGhiChu;
    DBBanNhap dbBanNhap;
    DBHoanThanh dbHoanThanh;
    private GhiChu data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_ghi_chu);
        setCOntrol();
        setEvent();
    }

    private void setEvent() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        registerForContextMenu(tvTuyChon);
        dbGhiChu = new DBGhiChu(ChiTietGhiChu.this);
        dbHoanThanh = new DBHoanThanh(ChiTietGhiChu.this);
         data = (GhiChu) getIntent().getSerializableExtra("item");
        tvTieuDe.setText(data.getTieuDe().toString());
        edtNoiDung.setText(data.getNoiDung().toString());
        tvExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context2,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.mnXoa){
            XoaGhiChu(data);
        }else if(item.getItemId()==R.id.mnLuu){
            SuaGhiChu(data);
            Toast.makeText(this, "Sửa thành công", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId()==R.id.mnHoanThanh){
            HoanThanh(data);
            onBackPressed();
        }
        return super.onContextItemSelected(item);
    }
    private void XoaGhiChu(GhiChu data){
        for (GhiChu ghichu:GhiChuFragment.data_GhiChu) {
            if (ghichu.getMaGhiChu().equals(data.getMaGhiChu())){
                GhiChuFragment.data_GhiChu.remove(ghichu);
                dbGhiChu.XoaDL(ghichu);
                GhiChuFragment.adapter_GhiChu = new AdapterGhiChu(ChiTietGhiChu.this,R.layout.item_ghichu,GhiChuFragment.data_GhiChu);
                GhiChuFragment.lvGhiChu.setAdapter(GhiChuFragment.adapter_GhiChu);
                GhiChuFragment.adapter_GhiChu.notifyDataSetChanged();
                Toast.makeText(ChiTietGhiChu.this, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                break;
            }
        }
        dbBanNhap = new DBBanNhap(this);
        for (GhiChu ghichu: BanNhapFragment.data_Bannhap) {
            if (ghichu.getMaGhiChu().equals(data.getMaGhiChu())){
                BanNhapFragment.data_Bannhap.remove(ghichu);
                dbBanNhap.XoaBN(ghichu);

                BanNhapFragment.adapterBanNhap = new AdapterBanNhap(ChiTietGhiChu.this, R.layout.item_bannhap,BanNhapFragment.data_Bannhap);
                BanNhapFragment.lvBanNhap.setAdapter(BanNhapFragment.adapterBanNhap);
                BanNhapFragment.adapterBanNhap.notifyDataSetChanged();
                Toast.makeText(ChiTietGhiChu.this, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                break;
            }
        }
        for (GhiChu ghiChu: DanhDauFragment.data_GhiChu){
            if (data.getMaGhiChu().equals(ghiChu.getMaGhiChu())){
                DanhDauFragment.data_GhiChu.remove(ghiChu);
                DanhDauFragment.adapterGhiChu = new AdapterGhiChu(ChiTietGhiChu.this,R.layout.item_ghichu,DanhDauFragment.data_GhiChu);
                DanhDauFragment.lvDanhDau.setAdapter(DanhDauFragment.adapterGhiChu);
                DanhDauFragment.adapterGhiChu.notifyDataSetChanged();
            }
        }
        onBackPressed();
    }
    private void SuaGhiChu(GhiChu data){
        GhiChu ghiChu = new GhiChu();
        ghiChu.setMaGhiChu(data.getMaGhiChu());
        ghiChu.setTieuDe(data.getTieuDe());
        ghiChu.setNoiDung(edtNoiDung.getText().toString());
        dbGhiChu.SuaDL(ghiChu);
        for (GhiChu gc:GhiChuFragment.data_GhiChu) {
            if (gc.getMaGhiChu().equals(data.getMaGhiChu())){
                gc.setNoiDung(edtNoiDung.getText().toString());
                break;
            }
        }
        GhiChuFragment.adapter_GhiChu = new AdapterGhiChu(ChiTietGhiChu.this,R.layout.item_ghichu,GhiChuFragment.data_GhiChu);
        GhiChuFragment.lvGhiChu.setAdapter(GhiChuFragment.adapter_GhiChu);
        GhiChuFragment.adapter_GhiChu.notifyDataSetChanged();
        onBackPressed();
    }
    //Hoàn thành công việc
    private void HoanThanh(GhiChu ghiChu){
        for (GhiChu flag:GhiChuFragment.data_GhiChu) {
            if (flag.getMaGhiChu().equals(ghiChu.getMaGhiChu())){
                GhiChuFragment.data_GhiChu.remove(flag);
                dbGhiChu.XoaDL(flag);
                GhiChuFragment.adapter_GhiChu = new AdapterGhiChu(ChiTietGhiChu.this,R.layout.item_ghichu,GhiChuFragment.data_GhiChu);
                GhiChuFragment.lvGhiChu.setAdapter(GhiChuFragment.adapter_GhiChu);
                GhiChuFragment.adapter_GhiChu.notifyDataSetChanged();
                ThongKe.data_ThongKe.add(ghiChu);
                dbHoanThanh.ThemDL(ghiChu);
                Toast.makeText(ChiTietGhiChu.this, "Công việc đã hoàn thành", Toast.LENGTH_SHORT).show();
                break;
            }
        }
        for (GhiChu flag: DanhDauFragment.data_GhiChu){
            if (ghiChu.getMaGhiChu().equals(flag.getMaGhiChu())){
                DanhDauFragment.data_GhiChu.remove(flag);
                DanhDauFragment.adapterGhiChu = new AdapterGhiChu(ChiTietGhiChu.this,R.layout.item_ghichu,DanhDauFragment.data_GhiChu);
                DanhDauFragment.lvDanhDau.setAdapter(DanhDauFragment.adapterGhiChu);
                DanhDauFragment.adapterGhiChu.notifyDataSetChanged();
            }
        }

    }
    private void setCOntrol() {
        tvExit = findViewById(R.id.tvExit);
        tvTieuDe = findViewById(R.id.tvTieuDe);
        tvTuyChon = findViewById(R.id.tvTuyChon);
        edtNoiDung = findViewById(R.id.edtNoiDung);
    }
}
package com.example.baitapgiuaki;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.List;

import pk_Fragment.DanhDauFragment;
import pk_Fragment.GhiChuFragment;

public class AdapterGhiChu extends ArrayAdapter<GhiChu>{
    Context context;
    int resource;
    List<GhiChu> data;
    private boolean isDrawableStar =false;
    TextView tam;
    DBGhiChu dbGhiChu;



    public AdapterGhiChu(@NonNull Context context, int resource, @NonNull List<GhiChu> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        dbGhiChu = new DBGhiChu(getContext());
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, null);
        }
        TextView tvTieuDe = convertView.findViewById(R.id.tvTieuDe);
        TextView tvNgayTao = convertView.findViewById(R.id.tvThoiGian);
        final TextView tvDanhDau = convertView.findViewById(R.id.tvTrangThai);


        tam = tvDanhDau;
        GhiChu ghiChu = data.get(position);
        tvTieuDe.setText(ghiChu.getTieuDe());
        tvNgayTao.setText(ghiChu.getNgayTao().toString());

        // Sử dụng trạng thái của mỗi item để cập nhật biểu tượng
        if (ghiChu.isChecked()) {
            Drawable drawable = ContextCompat.getDrawable(context, R.drawable.baseline_star_white_24);
            tvDanhDau.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
        } else {
            Drawable drawable = ContextCompat.getDrawable(context, R.drawable.baseline_star_24);
            tvDanhDau.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
        }
        dbGhiChu.SuaTrangThai(ghiChu);

        tvDanhDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Đảo ngược trạng thái của item khi click
                boolean ktra = false;
                ghiChu.setChecked(!ghiChu.isChecked());
                    Drawable drawable;
                // Cập nhật biểu tượng dựa trên trạng thái mới
                if (ghiChu.isChecked()) {
                     drawable = ContextCompat.getDrawable(context, R.drawable.baseline_star_white_24);
                    tvDanhDau.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
                } else {
                     drawable = ContextCompat.getDrawable(context, R.drawable.baseline_star_24);
                    tvDanhDau.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
                }
                for (GhiChu data:DanhDauFragment.data_GhiChu){
                    if (data.getMaGhiChu().equalsIgnoreCase(ghiChu.getMaGhiChu())){
                        DanhDauFragment.data_GhiChu.remove(data);
                        ktra =true;
                        break;
                    }
                }
                if (ktra==false){
                    DanhDauFragment.data_GhiChu.add(ghiChu);
                }
                GhiChuFragment.adapter_GhiChu =  new AdapterGhiChu(getContext(),R.layout.item_ghichu,GhiChuFragment.data_GhiChu);
                DanhDauFragment.adapterGhiChu = new AdapterGhiChu(getContext(),R.layout.item_ghichu,DanhDauFragment.data_GhiChu);
                GhiChuFragment.lvGhiChu.setAdapter(GhiChuFragment.adapter_GhiChu);
                DanhDauFragment.lvDanhDau.setAdapter(DanhDauFragment.adapterGhiChu);

                dbGhiChu.SuaTrangThai(ghiChu);
            }
        });
        return convertView;
    }

}

package com.example.baitapgiuaki;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.List;

import pk_Fragment.DanhDauFragment;
import pk_Fragment.GhiChuFragment;

public class AdapterBanNhap extends ArrayAdapter<GhiChu> {
    Context context;
    int resource;
    List<GhiChu> data;
    DBBanNhap dbBanNhap;
    public AdapterBanNhap(@NonNull Context context, int resource, @NonNull List<GhiChu> data){
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        dbBanNhap = new DBBanNhap(getContext());
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, null);
        }
        TextView tvTieuDe = convertView.findViewById(R.id.tvTieuDe);
        GhiChu ghiChu = data.get(position);
        tvTieuDe.setText(ghiChu.getTieuDe());

        return convertView;
    }
}

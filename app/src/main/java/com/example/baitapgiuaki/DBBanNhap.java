package com.example.baitapgiuaki;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBBanNhap extends SQLiteOpenHelper {
    public DBBanNhap(@Nullable Context context) {
        super(context, "tbBanNhap", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "Create table tbBanNhap(maGhiChu text, tieuDe text, noiDung text)";
        db.execSQL(sql);
    }
    public void XoaBN(GhiChu ghiChu){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "Delete from tbBanNhap where maGhiChu=?";
        sqLiteDatabase.execSQL(sql,new String[]{ghiChu.getMaGhiChu()});
    }
    public void ThemBN(@NonNull GhiChu ghiChu){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "Insert into tbBanNhap values(?,?,?)";
        sqLiteDatabase.execSQL(sql,new String[]{ghiChu.getMaGhiChu(),ghiChu.getTieuDe(),ghiChu.getNoiDung()});
    }
    public List<GhiChu> DocDS(){
        List<GhiChu> ds = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String sql = "Select * from tbBanNhap";
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                GhiChu ghiChu = new GhiChu();
                ghiChu.setMaGhiChu(cursor.getString(0));
                ghiChu.setTieuDe(cursor.getString(1));
                ghiChu.setNoiDung(cursor.getString(2));

                ds.add(ghiChu);
            }while (cursor.moveToNext());
        }
        return ds;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

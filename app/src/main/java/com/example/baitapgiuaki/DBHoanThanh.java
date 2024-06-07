package com.example.baitapgiuaki;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHoanThanh extends SQLiteOpenHelper {
    List<GhiChu> ds = new ArrayList<>();
    public DBHoanThanh(@Nullable Context context) {
        super(context, "tbHoanThanh", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "Create table tbHoanThanh(maGhiChu text, tieuDe text, noiDung text, ngayTao text, trangThai text)";
        db.execSQL(sql);
    }
    public void SuaTrangThai(GhiChu ghiChu){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "Update tbHoanThanh set tieuDe=?,noiDung=?,ngayTao=?,trangthai=? where maGhiChu=?";
        boolean trangthai = ghiChu.isChecked();
        sqLiteDatabase.execSQL(sql, new String[]{ghiChu.getTieuDe(),ghiChu.getNoiDung(),ghiChu.getNgayTao(),trangthai+"",ghiChu.getMaGhiChu()});
        ds = DocDL();
    }
    public void SuaDL(GhiChu ghiChu) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "UPDATE tbHoanThanh SET noiDung=? WHERE maGhiChu=?";
        sqLiteDatabase.execSQL(sql, new String[]{ghiChu.getNoiDung(), ghiChu.getMaGhiChu()});
        ds = DocDL();
        sqLiteDatabase.close(); // Close the database connection after the operation.
    }
    public void XoaDL(GhiChu ghiChu){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "Delete from tbHoanThanh where maGhiChu=?";
        sqLiteDatabase.execSQL(sql,new String[]{ghiChu.getMaGhiChu()});
    }
    public void ThemDL(@NonNull GhiChu ghiChu){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "Insert into tbHoanThanh values(?,?,?,?,?)";
        sqLiteDatabase.execSQL(sql,new String[]{ghiChu.getMaGhiChu(),ghiChu.getTieuDe(),ghiChu.getNoiDung(),ghiChu.getNgayTao(),ghiChu.isChecked()+""});
    }

    public List<GhiChu>DocDL(){
        List<GhiChu> ds = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String sql = "Select * from tbHoanThanh";
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                GhiChu ghiChu = new GhiChu();
                ghiChu.setMaGhiChu(cursor.getString(0));
                ghiChu.setTieuDe(cursor.getString(1));
                ghiChu.setNoiDung(cursor.getString(2));
                ghiChu.setNgayTao(cursor.getString(3));
                if("true".equals(cursor.getString(4))){
                    ghiChu.setChecked(true);
                }else{
                    ghiChu.setChecked(false);
                }

                ds.add(ghiChu);
            }while (cursor.moveToNext());
        }
        return ds;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

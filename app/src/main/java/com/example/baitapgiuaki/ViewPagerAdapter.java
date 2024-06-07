package com.example.baitapgiuaki;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import pk_Fragment.BanNhapFragment;
import pk_Fragment.DanhDauFragment;
import pk_Fragment.GhiChuFragment;
import pk_Fragment.GhiChuNull;

public class ViewPagerAdapter extends FragmentStatePagerAdapter{
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new GhiChuFragment();
            case 1:
                return new DanhDauFragment();
            case 2:
                return new BanNhapFragment();
            default:
                return new GhiChuFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
               title = "Ghi Chú";
               break;
            case 1:
                title = "Đánh Dấu";
                break;
            case 2:
                title = "Bản Nháp";
                break;
        }
        return title;
    }
}

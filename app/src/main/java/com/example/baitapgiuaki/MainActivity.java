package com.example.baitapgiuaki;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import pk_Fragment.GhiChuFragment;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    public static ViewPager viewPager;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();

    }

    private void setEvent() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(viewPagerAdapter);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.baseline_menu_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.mnCaidat){
                    Toast.makeText(MainActivity.this, "Chức năng chưa được xử lý", Toast.LENGTH_SHORT).show();
                }else if (item.getItemId()==R.id.mnThungrac){
                    Toast.makeText(MainActivity.this, "Chức năng chưa được xử lý", Toast.LENGTH_SHORT).show();
                }else if (item.getItemId()==R.id.mnMaunen){
                    Toast.makeText(MainActivity.this, "Chức năng chưa được xử lý", Toast.LENGTH_SHORT).show();
                }else if (item.getItemId()==R.id.mnHoanThanh){
                    Intent intent = new Intent(MainActivity.this, ThongKe.class);
                    startActivity(intent);
                }
                return false;
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.mnSearch){
            Toast.makeText(this, "Chức năng chưa được xử lý!!", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
    private void setControl() {
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.draw_layout);
        navigationView = findViewById(R.id.navigation);
    }


}
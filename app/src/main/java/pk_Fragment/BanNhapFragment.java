package pk_Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.baitapgiuaki.AdapterBanNhap;
import com.example.baitapgiuaki.AdapterGhiChu;
import com.example.baitapgiuaki.ChiTietGhiChu;
import com.example.baitapgiuaki.DBBanNhap;
import com.example.baitapgiuaki.GhiChu;
import com.example.baitapgiuaki.R;
import com.example.baitapgiuaki.ThemGhiChu;

import java.util.ArrayList;
import java.util.List;

public class BanNhapFragment extends Fragment {
    public static ListView lvBanNhap;
    public static List<GhiChu> data_Bannhap = new ArrayList<>();
     public static AdapterBanNhap adapterBanNhap;
    View view;
    DBBanNhap dbBanNhap ;
    public static int Dem ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ban_nhap, container, false);
        setControl();
        dbBanNhap = new DBBanNhap(getActivity());
        data_Bannhap = KhoiTao();
        adapterBanNhap = new AdapterBanNhap(getActivity(), R.layout.item_bannhap,data_Bannhap);
        lvBanNhap.setAdapter(adapterBanNhap);
        adapterBanNhap.notifyDataSetChanged();
        setEvent();
        return view;
    }

    private void setEvent() {
        lvBanNhap.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GhiChu sendData = data_Bannhap.get(position);
                Intent intent = new Intent(getActivity(), ThemGhiChu.class);
                intent.putExtra("nhap",sendData);
                startActivity(intent);
            }
        });
    }
    public static int LayCount(){
        Dem =(int) data_Bannhap.stream().count();
        return Dem;
    }
    private void setControl() {
        lvBanNhap = view.findViewById(R.id.lvBanNhap);
    }
    private List<GhiChu> KhoiTao(){
        data_Bannhap.clear();
        List<GhiChu> ds = new ArrayList<>();
        if (dbBanNhap.DocDS()!=null) {
            ds.addAll(dbBanNhap.DocDS());
        }
        return ds;
    }
}
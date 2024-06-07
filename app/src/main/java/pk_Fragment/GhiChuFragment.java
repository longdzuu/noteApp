package pk_Fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baitapgiuaki.AdapterGhiChu;
import com.example.baitapgiuaki.ChiTietGhiChu;
import com.example.baitapgiuaki.GhiChu;
import com.example.baitapgiuaki.R;
import com.example.baitapgiuaki.ThemGhiChu;
import com.example.baitapgiuaki.DBGhiChu;

import java.util.ArrayList;
import java.util.List;

public class GhiChuFragment extends Fragment {
    public static ListView lvGhiChu;
    View view;
    public static List<GhiChu> data_GhiChu = new ArrayList<>();
    public static AdapterGhiChu adapter_GhiChu;
    public  static
    Button btnThem;
    DBGhiChu dbGhiChu;
    public static int ds;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_ghi_chu, container, false);
        dbGhiChu = new DBGhiChu(getActivity());
        data_GhiChu =  KhoiTao();
        // ánh xạ lvghi chu và khởi tạo adapter với cusrom layout
        lvGhiChu = view.findViewById(R.id.lvGhiChu);

        adapter_GhiChu = new AdapterGhiChu(getContext(),R.layout.item_ghichu,data_GhiChu);
        lvGhiChu.setAdapter(adapter_GhiChu);
        //Lấy id custom layout
        View ctom = inflater.inflate(R.layout.item_ghichu,container,false);
        TextView tvTuyChon = ctom.findViewById(R.id.tvTuyChon);
        registerForContextMenu(tvTuyChon);

        lvGhiChu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GhiChu sendData = data_GhiChu.get(position);
                Intent intent = new Intent(getActivity(),ChiTietGhiChu.class);
                intent.putExtra("item",sendData);
                startActivity(intent);
            }
        });
        btnThem=view.findViewById(R.id.btnThem);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ThemGhiChu.class);
                ds = (int) data_GhiChu.stream().count();
                startActivity(intent);
            }
        });
        return  view;
    }

    private List<GhiChu> KhoiTao(){
        data_GhiChu.clear();
        List<GhiChu> ds = new ArrayList<>();
        if (dbGhiChu.DocDL()!=null) {
            ds.addAll(dbGhiChu.DocDL());
        }
        return ds;
    }

}
package pk_Fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.baitapgiuaki.AdapterGhiChu;
import com.example.baitapgiuaki.ChiTietGhiChu;
import com.example.baitapgiuaki.GhiChu;
import com.example.baitapgiuaki.R;

import java.util.ArrayList;
import java.util.List;


public class DanhDauFragment extends Fragment{

   public static ListView lvDanhDau;
   public static List<GhiChu> data_GhiChu = new ArrayList<>();
   public static AdapterGhiChu adapterGhiChu;
    View view;
    TextView tvTrangThai;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_danh_dau, container, false);
        //Lấy dữ liệu từ List Ghi chu
        lvDanhDau = view.findViewById(R.id.lv_DanhDau);
        //Truyền dữ liệu sang listview đánh dấu
        for (GhiChu ghiChu:GhiChuFragment.data_GhiChu) {
            if (ghiChu.isChecked()){
                data_GhiChu.add(ghiChu);
            }
        }
        if (data_GhiChu.stream().count()>0){
            adapterGhiChu = new AdapterGhiChu(getContext(),R.layout.item_ghichu,data_GhiChu);
            lvDanhDau.setAdapter(adapterGhiChu);
            adapterGhiChu.notifyDataSetChanged();
        }

        View custom = inflater.inflate(R.layout.item_ghichu,null);
        tvTrangThai =custom.findViewById(R.id.tvTrangThai);
        tvTrangThai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Lấy item được chọn
                int position = (int) v.getTag();
                GhiChu ghiChu = data_GhiChu.get(position);
                //Gửi dữ liệu
                for (GhiChu data:data_GhiChu)
                {
                    if (data.getMaGhiChu().toString().equalsIgnoreCase(ghiChu.getMaGhiChu().toString())){
                        for (GhiChu ds:GhiChuFragment.data_GhiChu)
                        {
                            if (data.getMaGhiChu().equalsIgnoreCase(ds.getMaGhiChu())){
                               GhiChuFragment.adapter_GhiChu = new AdapterGhiChu(getContext(),R.layout.item_ghichu,data_GhiChu);
                               GhiChuFragment.lvGhiChu.setAdapter(GhiChuFragment.adapter_GhiChu);
                               break;
                            }
                        }
                        data_GhiChu.remove(data);
                        break;
                    }
                }
                //Set lại dữ lệu cho listview
                adapterGhiChu = new AdapterGhiChu(getContext(),R.layout.item_ghichu,data_GhiChu);
                lvDanhDau.setAdapter(adapterGhiChu);
            }
        });
        lvDanhDau.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Mở nội dung ghi chú
                GhiChu sendData = data_GhiChu.get(position);
                Intent intent = new Intent(getActivity(), ChiTietGhiChu.class);
                intent.putExtra("item",sendData);
                startActivity(intent);
            }
        });
        return view;
    }
}
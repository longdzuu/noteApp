package pk_Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.baitapgiuaki.AdapterBanNhap;
import com.example.baitapgiuaki.ChiTietGhiChu;
import com.example.baitapgiuaki.DBBanNhap;
import com.example.baitapgiuaki.GhiChu;
import com.example.baitapgiuaki.R;
import com.example.baitapgiuaki.ThemGhiChu;

import java.util.ArrayList;
import java.util.List;

public class GhiChuNull extends Fragment {
    View view;
    Button btnThem;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.layout_null, container, false);
        setControl();
        setEvent();
        return view;
    }

    private void setEvent() {
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ThemGhiChu.class);
                startActivity(intent);
            }
        });
    }
    private void setControl() {
        btnThem = view.findViewById(R.id.btnThemnull);
    }
}
package edu.ftiuksw.tr_pam_mamam.ui.Traditional_Resto_Folder;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import edu.ftiuksw.tr_pam_mamam.R;

public class TraditionalRestoFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_traditional_resto, container, false);

        Button mMasakanPadang,mPrasmanan,mSate,mAngkringan;
        mMasakanPadang = view.findViewById(R.id.btn_masakan_padang);
        mSate = view.findViewById(R.id.btn_sate);
        mMasakanPadang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Padang_Food.class);
                startActivity(intent);
            }
        });
        mSate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Sate.class);
                startActivity(intent);
            }
        });


        return view;
    }

}


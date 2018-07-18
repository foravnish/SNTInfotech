package com.sntinfotech.sntinfotech.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sntinfotech.sntinfotech.Util.Constant;
import com.sntinfotech.sntinfotech.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTab3 extends Fragment {


    Button detail;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_tab3, container, false);
        Constant.val=true;
        detail=(Button)view.findViewById(R.id.detail);
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment  fragment = new Product();
                //fragment.setArguments(args);
                FragmentManager frgManager = getFragmentManager();
                frgManager.beginTransaction().add(R.id.content_frame, fragment)
                        .commit();
            }
        });
        return  view;
    }

}

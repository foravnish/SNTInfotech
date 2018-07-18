package com.sntinfotech.sntinfotech.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.sntinfotech.sntinfotech.Activites.Menu2;
import com.sntinfotech.sntinfotech.R;
import com.sntinfotech.sntinfotech.Util.AppController;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateDetails extends Fragment {


    public UpdateDetails() {
        // Required empty public constructor
    }


    NetworkImageView p_photo;
    TextView p_name,date,p_desc;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_update_details, container, false);

        p_name=(TextView)view.findViewById(R.id.p_name);
        date=(TextView)view.findViewById(R.id.date);
        p_desc=(TextView)view.findViewById(R.id.p_desc);
        p_photo=(NetworkImageView)view.findViewById(R.id.p_photo);
        Menu2.searchLiner.setVisibility(View.VISIBLE);

        Log.d("dfhnjksdgfsdg",getArguments().getString("image"));
        p_name.setText(getArguments().getString("title").toString());
        date.setText(getArguments().getString("created_date").toString());
        p_desc.setText(getArguments().getString("description").toString());
        ImageLoader imageLoader= AppController.getInstance().getImageLoader();
        p_photo.setImageUrl(getArguments().getString("image").toString(),imageLoader);

        return view;
    }

}

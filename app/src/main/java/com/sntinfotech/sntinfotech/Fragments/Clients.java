package com.sntinfotech.sntinfotech.Fragments;


import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.sntinfotech.sntinfotech.Util.AppController;
import com.sntinfotech.sntinfotech.Util.Constant;
import com.sntinfotech.sntinfotech.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Clients extends Fragment {

    GridView grid_view;
    List<Constant> client=new ArrayList<Constant>();
    //String url3="http://www.bizzduniya.com/commonJson/jsondata.php?task=clientInformation&token={V_7ZOicnuMmuoo93CW3699T8um5tjay&getCompanyId=1003";
//    String url="http://www.bizzduniya.com/commonJson/jsondata.php?task=clientInformation";

    Dialog dialog;
    Adapter adapter=null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.clients, container, false);
        Constant.val=true;

        grid_view=(GridView)view.findViewById(R.id.grid_view);

        dialog=new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        Constant.showDialog(dialog);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Constant.BaseUrl2+"&token="+Constant.client_token+"&getCompanyId="+Constant.client_id, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                dialog.dismiss();

                try {
                    client.clear();
                    Log.d("dfgfghgfdfgd",jsonObject.toString());
                if(jsonObject.optString("success").equals("1")) {
                    //Log.d("dfgdfgd",jsonObject.toString());

                    JSONObject jsonObject1 = jsonObject.getJSONObject("serviceDetail");
                    //Toast.makeText(getActivity(), "ok", Toast.LENGTH_SHORT).show();

                    JSONArray jsonArray = jsonObject1.optJSONArray("clientList");
                    //Log.d("gdfghdfgfghgfthf",jsonObject2.length()+"");
                    for (int i = 0; i < jsonArray.length(); i++) {

                        Log.d("dfgdfgd",jsonArray.toString());
                        JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                        client.add(new Constant(jsonObject2.optString("id"), jsonObject2.optString("photo"),jsonObject2.optString("title")));

                        adapter = new Adapter();
                        grid_view.setAdapter(adapter);
                    }
                    adapter.notifyDataSetChanged();

                }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                dialog.dismiss();
                Toast.makeText(getActivity(), "Network Problem", Toast.LENGTH_SHORT).show();
//                Crouton.makeText(getActivity(), "" + "Some problem occured pls try again"(), Style.ALERT).show();
            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);



        return  view;
    }


    static class ViewHolder {
        NetworkImageView client_photo;
        TextView title;


    }

    class Adapter extends BaseAdapter {

        LayoutInflater inflater;

        Adapter() {
            inflater = (LayoutInflater) Clients.this.getActivity().getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }


        @Override
        public int getCount() {
            return client.size();
        }




        @Override
        public Object getItem(int position) {
            return client.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            convertView=inflater.inflate(R.layout.custom_client,parent,false);
            viewHolder=new ViewHolder();

            viewHolder.client_photo= (NetworkImageView) convertView.findViewById(R.id.client_photo);
            viewHolder.title= (TextView) convertView.findViewById(R.id.title);

            ImageLoader imageLoader=AppController.getInstance().getImageLoader();
            viewHolder.client_photo.setImageUrl(client.get(position).getTitle_client().toString(),imageLoader);
            viewHolder.title.setText(client.get(position).getPhoto_client().toString());




            return convertView;
        }
    }

}

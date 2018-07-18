package com.sntinfotech.sntinfotech.Fragments;


import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.sntinfotech.sntinfotech.Activites.Menu2;
import com.sntinfotech.sntinfotech.Util.Constant;
import com.sntinfotech.sntinfotech.R;
import com.sntinfotech.sntinfotech.Util.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUs extends Fragment {


    TextView CompanyName,address,city,state,pincode,phone,year,about,products,types;
    GridView  gridview;
    NetworkImageView photo;
    Dialog dialog;
  //  String url="http://www.bizzduniya.com/commonJson/jsondata.php?task=getCompanyDetail";
    Adapter adapter;
    List<Constant> CompanyType=new ArrayList<Constant>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.about_us, container, false);
        Menu2.searchLiner.setVisibility(View.GONE);
        Constant.val=true;


//        CompanyName=(TextView)view.findViewById(R.id.CompanyName);
//        address=(TextView)view.findViewById(R.id.address);
//        city=(TextView)view.findViewById(R.id.city);
//        state=(TextView)view.findViewById(R.id.state);
//        pincode=(TextView)view.findViewById(R.id.pincode);
//        phone=(TextView)view.findViewById(R.id.phone);
//        year=(TextView)view.findViewById(R.id.year);
        about=(TextView)view.findViewById(R.id.about);
//        products=(TextView)view.findViewById(R.id.products);
        //types=(TextView)view.findViewById(R.id.types);
//        gridview=(GridView)view.findViewById(R.id.gridview);


;

//        photo=(NetworkImageView) view.findViewById(R.id.photo);
        dialog=new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        Constant.showDialog(dialog);



        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, Constant.BaseUrl+"&token="+Constant.client_token+"&getCompanyId="+Constant.client_id, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
//                statelist.clear();
                dialog.dismiss();
                    try {

                        JSONObject jsonObject=response.getJSONObject("companyDetail");
                        //city_id=response.getJSONArray("id");
                            CompanyType.clear();
                            try {
                                Log.d("sdfsd",jsonObject.optString("0"));
                                JSONObject jsonObject1=jsonObject.getJSONObject("0");
                                JSONObject jsonObject2=jsonObject1.getJSONObject("companyProfile");
                                //jsonObject = jsonArray.optJSONObject(0);
//                                CompanyName.setText(jsonObject1.optString("company_name"));
//                                address.setText(jsonObject1.optString("company_address"));
//                                city.setText(jsonObject1.optString("company_city"));
//                                state.setText(jsonObject1.optString("company_state"));
//                                pincode.setText(jsonObject1.optString("company_pincode"));
//                                phone.setText(jsonObject1.optString("company_phone"));
//                                year.setText(jsonObject1.optString("year_established"));
                                about.setText(Html.fromHtml(jsonObject1.optString("about_company")));
//                                ImageLoader imageLoader = AppController.getInstance().getImageLoader();
//                                photo.setImageUrl(jsonObject1.optString("cover_photo"),imageLoader);
//                                products.setText(jsonObject2.optString("products"));
                                //types.setText(jsonObject2.optString("companyTypes"));

                                CompanyType.add(new Constant(jsonObject2.optString("companyTypes"),null));

                                adapter=new Adapter();
                                gridview.setAdapter(adapter);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            // Toast.makeText(MainActivity.this, ""+city_id, Toast.LENGTH_SHORT).show();



//
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    dialog.dismiss();
                    Toast.makeText(getActivity(), "Network Problem", Toast.LENGTH_SHORT).show();
//                    CompanyName.setText("");
//                    address.setText("");
//                    city.setText("");
//                    state.setText("");
//                    pincode.setText("");
//                    phone.setText("");
//                    year.setText("");
//                    about.setText("");
//                    products.setText("");
//                    types.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

        return view;
    }


    static class ViewHolder {
        TextView name;


    }
    class  Adapter extends BaseAdapter{
        LayoutInflater inflater;

        Adapter() {
            inflater = (LayoutInflater) AboutUs.this.getActivity().getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }
        @Override
        public int getCount() {
            return CompanyType.size();
        }

        @Override
        public Object getItem(int position) {
            return CompanyType.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            convertView=inflater.inflate(R.layout.custom_list_data,parent,false);
            viewHolder=new ViewHolder();

            viewHolder.name=(TextView)convertView.findViewById(R.id.name);
            viewHolder.name.setText(CompanyType.get(position).getcb().toString());




            return convertView;
        }
    }

}

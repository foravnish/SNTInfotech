package com.sntinfotech.sntinfotech.Fragments;


import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
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
import com.android.volley.toolbox.StringRequest;
import com.sntinfotech.sntinfotech.Activites.Menu2;
import com.sntinfotech.sntinfotech.Util.Constant;
import com.sntinfotech.sntinfotech.R;
import com.sntinfotech.sntinfotech.Util.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Product extends Fragment {



    TextView p_name,p_code,p_price,p_mode,p_desc;
    NetworkImageView p_photo;
    GridView grid_view;
    Button p_enquiry;
    List<Constant> paymentmode=new ArrayList<Constant>();
    Adapter adapter=null;
    //String url1="http://www.bizzduniya.com/commonJson/jsondata.php?task=viewDetail&token={V_7ZOicnuMmuoo93CW3699T8um5tjay&getCompanyId=1003";
//    String url="http://www.bizzduniya.com/commonJson/jsondata.php?task=viewDetail";
    Dialog dialog;
    String p_id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.product, container, false);
        Constant.val=true;
        p_name=(TextView)view.findViewById(R.id.p_name);
        p_code=(TextView)view.findViewById(R.id.p_code);
        p_price=(TextView)view.findViewById(R.id.p_price);
        //p_mode=(TextView)view.findViewById(R.id.p_mode);
        p_desc=(TextView)view.findViewById(R.id.p_desc);
        p_photo=(NetworkImageView)view.findViewById(R.id.p_photo);
        grid_view=(GridView)view.findViewById(R.id.grid_view);
        p_enquiry=(Button)view.findViewById(R.id.p_enquiry);

        Menu2.searchLiner.setVisibility(View.VISIBLE);
        Bundle bundle=getArguments();
        String val=bundle.getString("id");
//        mode.setText(val.toString());
        dialog=new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        Constant.showDialog(dialog);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Constant.BaseUrl6+"&token="+Constant.client_token+"&getCompanyId="+Constant.client_id+"&serviceId="+val.toString(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {


                dialog.dismiss();
                try {
                    JSONObject jsonObject1=jsonObject.getJSONObject("serviceDetail");
                    //Toast.makeText(getActivity(), "ok", Toast.LENGTH_SHORT).show();

                    JSONObject jsonObject2 = jsonObject1.optJSONObject("0");
                    Log.d("gdfghdfgfghgfthf",jsonObject2.length()+"");
                    p_id=jsonObject2.optString("id");
                    p_name.setText(jsonObject2.optString("service_name"));
                    p_code.setText(jsonObject2.optString("product_code"));
                    p_price.setText(jsonObject2.optString("app_price"));
                    //p_mode.setText(jsonObject2.optString("service_name"));
                    p_desc.setText(Html.fromHtml(jsonObject2.optString("service_desc")));

                    ImageLoader imageLoader=AppController.getInstance().getImageLoader();
                    p_photo.setImageUrl(jsonObject2.optString("photo"),imageLoader);







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


        JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(Request.Method.GET, Constant.BaseUrl6+"&token="+Constant.client_token+"&getCompanyId="+Constant.client_id+"&serviceId="+val.toString(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {


                try {
                    JSONObject jsonObject1=jsonObject.getJSONObject("serviceDetail");
                    //Toast.makeText(getActivity(), "ok", Toast.LENGTH_SHORT).show();

                    JSONArray jsonArray = jsonObject1.optJSONArray("paymentMode");
                    Log.d("gdfghdfgfghgfthf",jsonArray.toString()+"");
                    if (jsonArray!=null) {
                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jsonObject2 = jsonArray.getJSONObject(i);


//                        if (jsonObject2.isNull("null"){
//                            continue;
//                        }


                            //  Log.d("sdfsdfsdgsfg1",jsonArray.getJSONObject(0).toString());
                            //  Log.d("sdfsdfsdgsfg2",jsonArray.getJSONObject(1).toString());
//                        Log.d("sdfsdfsdgsfg3",jsonArray.getJSONObject(2).toString());
//                        Log.d("sdfsdfsdgsfg4",jsonArray.getJSONObject(3).toString());

                            Log.d("dmfbsdfjsgfs", jsonObject2 + "");
                            if (jsonObject2.toString() != "") {
                                paymentmode.add(new Constant(jsonObject2.optString("checkOrNote"), jsonObject2.optString("value")));
                            }

                            adapter = new Adapter();
                            grid_view.setAdapter(adapter);
                        }
                    }
                    adapter.notifyDataSetChanged();



                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("fgdfgdfgdfg",e.toString());
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

        AppController.getInstance().addToRequestQueue(jsonObjectRequest2);




        p_enquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new Enquiry();
                Bundle bundle=new Bundle();
                bundle.putString("name","Product");
                bundle.putString("id",p_id);
                //fragment.setArguments(args);
                FragmentManager frgManager = getFragmentManager();
                frgManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack("rte")
                        .commit();
                fragment.setArguments(bundle);

            }
        });


        return  view;
    }


    static class ViewHolder {
        CheckBox checkbox;


    }

    class Adapter extends BaseAdapter {

        LayoutInflater inflater;

        Adapter() {
            inflater = (LayoutInflater) Product.this.getActivity().getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }


        @Override
        public int getCount() {
            return paymentmode.size();
        }




        @Override
        public Object getItem(int position) {
            return paymentmode.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            convertView=inflater.inflate(R.layout.custom_paymentmode,parent,false);
            viewHolder=new ViewHolder();

            viewHolder.checkbox= (CheckBox) convertView.findViewById(R.id.checkbox);

            if(paymentmode.get(position).getcb().toString().equals("yes")){

                viewHolder.checkbox.setChecked(true);
            }
            else
            {
                viewHolder.checkbox.setChecked(false);
                //Toast.makeText(getActivity(), "no", Toast.LENGTH_SHORT).show();
            }
            viewHolder.checkbox.setText(paymentmode.get(position).getvalue().toString());



            return convertView;
        }
    }

}

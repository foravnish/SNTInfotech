package com.sntinfotech.sntinfotech.Fragments;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.TranslateAnimation;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
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

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTab1 extends Fragment {


   Button detail;

    TextView title;

    GridView gridview;
    List<Constant> Pro=new ArrayList<Constant>();
    Adapter adapter=null;
    Dialog dialog;
    //String url="http://www.bizzduniya.com/commonJson/jsondata.php?task=viewDetailByCategory&token=%7BV_7ZOicnuMmuoo93CW3699T8um5tjay&getCompanyId=1003&categortListId=6542";
//      String url="http://www.bizzduniya.com/commonJson/jsondata.php?task=viewDetailByCategory";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_tab1, container, false);
        Constant.val=true;

        gridview=(GridView) view.findViewById(R.id.gridview);

        dialog=new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,Constant.BaseUrl5+"&token="+Constant.client_token+"&getCompanyId="+Constant.client_id+"&categortListId="+getArguments().getString("id"), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                dialog.dismiss();
                try {
                    Pro.clear();
                if (jsonObject.optString("sucess").equals("1")) {
                    JSONArray jsonArray = jsonObject.getJSONArray("productCategoryList");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject2 = jsonArray.optJSONObject(i);

                        Log.d("gffsdfgsdfdgdf", jsonObject2.toString());

                        Pro.add(new Constant(jsonObject2.optString("id"), jsonObject2.optString("name"), jsonObject2.optString("photo"), jsonObject2.optString("service_code"), jsonObject2.optString("service_desc")));

                        adapter = new Adapter();
                        gridview.setAdapter(adapter);


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

        Constant.showDialog(dialog);
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);


        //title.setText(getArguments().getString("id"));

//        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Fragment fragment = new Product();
//                //fragment.setArguments(args);
//                Bundle bundle=new Bundle();
//                bundle.putString("id",Pro.get(position).getId().toString());
//                FragmentManager frgManager = getFragmentManager();
//                frgManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack("rte")
//                        .commit();
//                fragment.setArguments(bundle);
//            }
//        });
        return  view;
    }

    static class ViewHolder {

        NetworkImageView photoProduct;
        TextView name,code,desc;
        Button detail,enquiry;
        LinearLayout line1;


    }



    class Adapter extends BaseAdapter {

        LayoutInflater inflater;

        Adapter() {
            inflater = (LayoutInflater) FragmentTab1.this.getActivity().getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }


        @Override
        public int getCount() {
            return Pro.size();
        }




        @Override
        public Object getItem(int position) {
            return Pro.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;

//            TranslateAnimation animation = new TranslateAnimation(0,0,500,0);  //  new TranslateAnimation(xFrom,xTo, yFrom,yTo)
//            animation.setDuration(1000);  // animation duration
//            animation.setFillAfter(true);


            convertView=inflater.inflate(R.layout.custom_product,parent,false);
            viewHolder=new ViewHolder();

            viewHolder.photoProduct= (NetworkImageView) convertView.findViewById(R.id.photoProduct);
            viewHolder.name= (TextView) convertView.findViewById(R.id.name);
            viewHolder.code= (TextView) convertView.findViewById(R.id.code);
            //viewHolder.desc= (TextView) convertView.findViewById(R.id.desc);

            viewHolder.detail= (Button) convertView.findViewById(R.id.detail);
            viewHolder.enquiry= (Button) convertView.findViewById(R.id.enquiry);
            viewHolder.line1= (LinearLayout) convertView.findViewById(R.id.line1);

            ImageLoader imageLoader= AppController.getInstance().getImageLoader();
            viewHolder.photoProduct.setImageUrl(Pro.get(position).getPhoto(),imageLoader);

            viewHolder.name.setText(Pro.get(position).getName1().toString());
            viewHolder.code.setText(Pro.get(position).getCode().toString());
            //viewHolder.desc.setText(Pro.get(position).getDesc().toString());

//            viewHolder.line1.startAnimation(animation);

            viewHolder.detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment = new Product();
                //fragment.setArguments(args);
                Bundle bundle=new Bundle();
                bundle.putString("id",Pro.get(position).getId().toString());
                FragmentManager frgManager = getFragmentManager();
                frgManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack("rte")
                        .commit();
                fragment.setArguments(bundle);
                }
            });

            viewHolder.enquiry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment = new Enquiry();
                    Bundle bundle=new Bundle();
                    bundle.putString("name","Product");
                    bundle.putString("id",Pro.get(position).getId().toString());
                    FragmentManager frgManager = getFragmentManager();
                    frgManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack("rte")
                            .commit();
                    fragment.setArguments(bundle);
                }
            });

            return convertView;
        }
    }


}

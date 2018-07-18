package com.sntinfotech.sntinfotech.Fragments;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
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
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.sntinfotech.sntinfotech.Activites.Menu2;
import com.sntinfotech.sntinfotech.Util.AppController;
import com.sntinfotech.sntinfotech.Util.Constant;
import com.sntinfotech.sntinfotech.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SeeAll extends Fragment {


    List<Constant> AllProducts = new ArrayList<Constant>();
    List<Constant> ProductList = new ArrayList<Constant>();

//    String url = "http://www.bizzduniya.com/commonJson/jsondata.php?task=getCompanyDetail";

    String p_name;
    JSONObject jsonObject2;
    JSONArray jsonArray1;
    Adapter  listAdapter;
    GridView expListView;
    JSONObject jsonObject1;
    List<String> listDataHeader;
    Dialog dialog;
    HashMap<String, List<String>> listDataChild;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_see_all, container, false);
        Constant.val = true;


        expListView = (GridView) view.findViewById(R.id.lvExp);

//        AdView adView = (AdView)view. findViewById(R.id.search_ad_view);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        adView.loadAd(adRequest);

        dialog=new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        Menu2.searchLiner.setVisibility(View.VISIBLE);
        Log.d("sdfdsfsdgsdfgdsf",getArguments().getString("search"));
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Constant.BaseUrl + "&token=" + Constant.client_token + "&getCompanyId=" + Constant.client_id+"&keyword1="+getArguments().getString("search"), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                dialog.dismiss();
                try {
                    AllProducts.clear();
                    JSONObject jsonObject = response.getJSONObject("companyDetail");
                    JSONArray jsonArray = jsonObject.getJSONArray("productCategory");
                    for (int i = 0; i < jsonArray.length(); i++) {

                        jsonObject1 = jsonArray.optJSONObject(i);

                        AllProducts.add(new Constant(jsonObject1.optString("id").toString(),jsonObject1.optString("Name").toString(), jsonObject1.optString("counter")));
                        Log.d("gdfhdfh", jsonObject1.optString("Name").toString());

                        p_name = jsonObject1.optString("Name").toString();

                        if (jsonObject1.optString("counter").equalsIgnoreCase("0")){
                            Toast.makeText(getActivity(), "No Record Found...", Toast.LENGTH_SHORT).show();
                        }
                        jsonArray1= jsonObject1.getJSONArray("productCategoryList");

                        for (int j=0;j<jsonArray1.length();j++){

                            jsonObject2=jsonArray1.optJSONObject(j);

                            ProductList.add(new Constant(jsonObject2.optString("id"), jsonObject2.optString("name"), jsonObject2.optString("image")));

                            Log.d("fgdfh",jsonObject2.optString("id"));
                            Log.d("fhfgdfh",jsonObject2.optString("name"));

                        }


                        Log.d("ghgfgdfhdfh", jsonObject1.optString("counter").toString());

                        listAdapter = new Adapter();
                        // setting list adapter
                        expListView.setAdapter(listAdapter);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Toast.makeText(getActivity(), "Network Problem", Toast.LENGTH_SHORT).show();
            }
        });

        Constant.showDialog(dialog);
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);


        expListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment fragment=new Product();
                Bundle bundle =new Bundle();
                bundle.putString("id",ProductList.get(position).getId_client().toString());
                FragmentManager fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.content_frame,fragment).addToBackStack(null).commit();
                fragment.setArguments(bundle);


            }
        });



        return view;
    }



    static class ViewHolder {
        TextView name;
        NetworkImageView photoProduct;
        TextView code;
        TextView desc;
        LinearLayout line1;
    }



    class Adapter extends BaseAdapter {

        LayoutInflater inflater;


        Adapter() {
            inflater = (LayoutInflater) SeeAll.this.getActivity().getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public int getCount() {
            return ProductList.size();
        }

        @Override
        public Object getItem(int position) {
            return ProductList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;

            convertView=inflater.inflate(R.layout.list_group,parent,false);
            viewHolder=new ViewHolder();


            Log.d("dsfsdfdfdrfsrfsd",getArguments().getString("search"));
            Log.d("dsfsdfdfdrfsrfsd",ProductList.get(position).getTitle_client().toString());


//            if (getArguments().getString("search").equalsIgnoreCase(ProductList.get(position).getTitle_client().toString())){
//                Log.d("dsfdsgfsdgdfgsdgsdf","trueValue");
//            }
//            else{
//                Log.d("dsfdsgfsdgdfgsdgsdf","false");
//            }
            viewHolder.name= (TextView) convertView.findViewById(R.id.name);
            viewHolder.code= (TextView) convertView.findViewById(R.id.code);
            viewHolder.photoProduct= (NetworkImageView) convertView.findViewById(R.id.photoProduct);

            viewHolder.name.setText(ProductList.get(position).getTitle_client().toString());
            viewHolder.code.setText(ProductList.get(position).getTitle_client().toString());

            final Typeface tvFont = Typeface.createFromAsset(getActivity().getAssets(), "myriad.otf");
            viewHolder.name.setTypeface(tvFont);
            viewHolder.name.setTypeface(viewHolder.name.getTypeface(),Typeface.BOLD);
            viewHolder.code.setTypeface(tvFont);

            try {
                ImageLoader imageLoader=AppController.getInstance().getImageLoader();
                viewHolder.photoProduct.setImageUrl(ProductList.get(position).getPhoto_client(),imageLoader);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return convertView;
        }
    }

}
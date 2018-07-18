package com.sntinfotech.sntinfotech.Fragments;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.sntinfotech.sntinfotech.Activites.Menu2;
import com.sntinfotech.sntinfotech.R;
import com.sntinfotech.sntinfotech.Util.AppController;
import com.sntinfotech.sntinfotech.Util.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Updates extends Fragment {


    public Updates() {
        // Required empty public constructor
    }

    List<HashMap<String,String>> AllProducts ;
//    List<Constant>  AllProducts=new ArrayList<>();


//    String url = "http://www.bizzduniya.com/commonJson/jsondata.php?task=getCompanyDetail";

    String p_name;
    JSONObject jsonObject2;
    JSONArray jsonArray1;
    Adapter listAdapter;
    GridView expListView;
    JSONObject jsonObject1;
    List<String> listDataHeader;
    Dialog dialog;
    HashMap<String, List<String>> listDataChild;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_updates, container, false);
        Constant.val = true;
        expListView = (GridView) view.findViewById(R.id.lvExp);

//        AdView adView = (AdView)view. findViewById(R.id.search_ad_view);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        adView.loadAd(adRequest);

        AllProducts=new ArrayList<>();
        dialog=new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        Menu2.searchLiner.setVisibility(View.VISIBLE);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Constant.BaseUrlUpdate + "&token=" + Constant.client_token + "&getCompanyId=" + Constant.client_id, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                dialog.dismiss();
                try {
                   // AllProducts.clear();
                    Log.d("fgdgdfgdf",response.toString());
                   if (response.optString("success").equals("1")){

                       JSONArray jsonArray = response.getJSONArray("upcomingItemList");
                       for (int i = 0; i < jsonArray.length(); i++) {

                           jsonObject1 = jsonArray.optJSONObject(i);

//                           AllProducts.add(new Constant(jsonObject1.optString("id"),jsonObject1.optString("title"),jsonObject1.optString("image")));

                           HashMap<String,String> map=new HashMap<>();
                           map.put("id",jsonObject1.optString("id"));
                           map.put("company_id",jsonObject1.optString("company_id"));
                           map.put("image",jsonObject1.optString("image"));
                           map.put("title",jsonObject1.optString("title"));
                           map.put("description",jsonObject1.optString("description"));
                           map.put("created_date",jsonObject1.optString("created_date"));

                           AllProducts.add(map);


//                           AllProducts.add(new Constant(jsonObject1.optString("id").toString(),jsonObject1.optString("Name").toString(), jsonObject1.optString("counter")));
//                           Log.d("gdfhdfh", jsonObject1.optString("Name").toString());
//
//                           p_name = jsonObject1.optString("Name").toString();
//
//
//                           jsonArray1= jsonObject1.getJSONArray("productCategoryList");
//
//                           for (int j=0;j<jsonArray1.length();j++){
//
//                               jsonObject2=jsonArray1.optJSONObject(j);
//
//                               ProductList.add(new Constant(jsonObject2.optString("id"),jsonObject2.optString("name"),jsonObject2.optString("image")));
//
//                               Log.d("fgdfh",jsonObject2.optString("id"));
//                               Log.d("fhfgdfh",jsonObject2.optString("name"));
//
//                           }
//
//
//                           Log.d("ghgfgdfhdfh", jsonObject1.optString("counter").toString());


                       }
                       listAdapter = new Adapter();
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


//        expListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Fragment fragment=new UpdateDetails();
//                Bundle bundle =new Bundle();
//                bundle.putString("image",AllProducts.get(position).get("image"));
//                bundle.putString("title",AllProducts.get(position).get("title"));
//                bundle.putString("description",AllProducts.get(position).get("description"));
//                bundle.putString("created_date",AllProducts.get(position).get("created_date"));
//                FragmentManager fm=getFragmentManager();
//                FragmentTransaction ft=fm.beginTransaction();
//                ft.replace(R.id.content_frame,fragment).addToBackStack(null).commit();
//                fragment.setArguments(bundle);
//            }
//        });

        return view;

    }

    static class ViewHolder {
        TextView title,date;
        NetworkImageView photoProduct2;

    }

    class Adapter extends BaseAdapter {

        LayoutInflater inflater;


        Adapter() {
            inflater = (LayoutInflater) getActivity().getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public int getCount() {
            return AllProducts.size();
        }

        @Override
        public Object getItem(int position) {
            return AllProducts.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;

            convertView=inflater.inflate(R.layout.list_group_update,parent,false);
            viewHolder=new ViewHolder();

            viewHolder.title= (TextView) convertView.findViewById(R.id.title);
            viewHolder.date= (TextView) convertView.findViewById(R.id.date);
            viewHolder.photoProduct2= (NetworkImageView) convertView.findViewById(R.id.photoProduct2);

            viewHolder.title.setText(AllProducts.get(position).get("title").toString());
            viewHolder.date.setText(AllProducts.get(position).get("created_date").toString());

            final Typeface tvFont = Typeface.createFromAsset(getActivity().getAssets(), "myriad.otf");
            viewHolder.title.setTypeface(tvFont);
            viewHolder.title.setTypeface(viewHolder.title.getTypeface(),Typeface.BOLD);
            viewHolder.date.setTypeface(tvFont);

            try {
                ImageLoader imageLoader=AppController.getInstance().getImageLoader();
                viewHolder.photoProduct2.setImageUrl(AllProducts.get(position).get("image").toString(),imageLoader);
            } catch (Exception e) {
                e.printStackTrace();
            }

//            ImageRequest request = new ImageRequest(
//                    AllProducts.get(position).get("image").toString(), myResponseListener, maxWidth,
//                    maxHeight, scaleType, Bitmap.Config.RGB_565, myErrorListener);


            return convertView;
        }
    }

}

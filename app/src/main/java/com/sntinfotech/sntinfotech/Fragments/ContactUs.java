package com.sntinfotech.sntinfotech.Fragments;


import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sntinfotech.sntinfotech.Activites.Menu2;
import com.sntinfotech.sntinfotech.Util.AppController;
import com.sntinfotech.sntinfotech.Util.Constant;
import com.sntinfotech.sntinfotech.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactUs extends Fragment  {

    private MapView mapView;
    GoogleMap map;
//    String url="http://www.bizzduniya.com/commonJson/jsondata.php?task=getCompanyDetail";

    TextView address,city,state,contry,pincode,phone,website,emailid;
    Dialog dialog;
    Button p_enquiry;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.contact_us, container, false);
        Constant.val=true;
        address=(TextView)view.findViewById(R.id.address);
//        city=(TextView)view.findViewById(R.id.city);
//        state=(TextView)view.findViewById(R.id.state);
//        contry=(TextView)view.findViewById(R.id.contry);
//        pincode=(TextView)view.findViewById(R.id.pincode);
        phone=(TextView)view.findViewById(R.id.phone);
        website=(TextView)view.findViewById(R.id.website);
        emailid=(TextView)view.findViewById(R.id.emailid);
        p_enquiry=(Button)view.findViewById(R.id.p_enquiry);
        dialog=new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        Constant.showDialog(dialog);

        Menu2.searchLiner.setVisibility(View.GONE);

        p_enquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new Enquiry();
                Bundle bundle=new Bundle();
                bundle.putString("name","general");
                bundle.putString("id","");
                //fragment.setArguments(args);
                FragmentManager frgManager = getFragmentManager();
                frgManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack("rte")
                        .commit();
                fragment.setArguments(bundle);

            }
        });


        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, Constant.BaseUrl+"&token="+Constant.client_token+"&getCompanyId="+Constant.client_id, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                dialog.dismiss();
                try {

                    JSONObject jsonObject=response.getJSONObject("companyDetail");
                    //city_id=response.getJSONArray("id");

                    try {
                        Log.d("sdfsd",jsonObject.optString("0"));
                        JSONObject jsonObject1=jsonObject.getJSONObject("0");

                        //jsonObject = jsonArray.optJSONObject(0);
                        address.setText(jsonObject1.optString("company_address")+"\n"+jsonObject1.optString("company_city")+" "+
                                jsonObject1.optString("company_state")+"\n"+jsonObject1.optString("company_country")+" "+jsonObject1.optString("company_pincode"));

//                        city.setText(jsonObject1.optString("company_city"));
//                        state.setText(jsonObject1.optString("company_state"));
//                        contry.setText(jsonObject1.optString("company_country"));
//                        pincode.setText(jsonObject1.optString("company_pincode"));

                        phone.setText(jsonObject1.optString("company_mobile1"));
//                        phone.setText("+91 9810378448");
                        website.setText("http://www.foodprocessingmachinery.co.in/");
                        emailid.setText(jsonObject1.optString("email"));
                        Log.d("city",jsonObject1.optString("company_city"));
                        Log.d("contry",jsonObject1.optString("company_country"));


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
                dialog.dismiss();
                Toast.makeText(getActivity(), "Network Problem", Toast.LENGTH_SHORT).show();

            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);


//        mapView = (MapView)view.findViewById(R.id.map);
//        mapView.onCreate(savedInstanceState);
//        map = mapView.getMap();
//        try {
//            map.getUiSettings().setMyLocationButtonEnabled(false);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
////        map.setMyLocationEnabled(true);
//
//        // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
//        try{
//            MapsInitializer.initialize(this.getActivity());
//        }
//        catch (Exception e)
//        {e.printStackTrace();}
//
//        try {
//            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(28.663628,77.369767),15);
//            map.animateCamera(cameraUpdate);
//
//            final LatLng Elite = new LatLng(28.663628, 77.369767);
//            map.addMarker(new MarkerOptions().position(Elite).draggable(false));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        return view;
    }
//    @Override
//    public void onResume() {
//        mapView.onResume();
//        super.onResume();
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        mapView.onDestroy();
//    }
//
//    @Override
//    public void onLowMemory() {
//        super.onLowMemory();
//        mapView.onLowMemory();
//    }

}

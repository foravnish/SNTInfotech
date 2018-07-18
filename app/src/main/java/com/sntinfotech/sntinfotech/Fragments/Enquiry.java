package com.sntinfotech.sntinfotech.Fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.sntinfotech.sntinfotech.Activites.Menu2;
import com.sntinfotech.sntinfotech.Util.AppController;
import com.sntinfotech.sntinfotech.Util.Constant;
import com.sntinfotech.sntinfotech.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 */
public class Enquiry extends Fragment {

    TextView term;


    Button submit;
    AlertDialog alertDialog;
    EditText editEmail;
    JSONObject jsonObject;
    //    String url="http://www.bizzduniya.com/commonJson/jsondata.php?task=checkEmailId";
//    String url2="http://www.bizzduniya.com/commonJson/jsondata.php?task=sendQuery";
    //"&company_id=1003&generalOrProduct=&fav_service_id=&company_name=&fname=&mobile=&city=&state=&description=&email=avnish.akt@gmail.com";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.enquiry, container, false);
        Constant.val=true;

        submit=(Button) view.findViewById(R.id.submit);
        editEmail=(EditText)view.findViewById(R.id.editEmail);

        Menu2.searchLiner.setVisibility(View.GONE);

//        AdView adView = (AdView)view. findViewById(R.id.search_ad_view);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        adView.loadAd(adRequest);

        submit.setOnClickListener(new View.OnClickListener() {
            Dialog dialog2;
            EditText CompanyName,name,address,country,state,city,message,mobile;
            LinearLayout cname,pname,cityname;
            AppCompatCheckBox checkbox;

            Button submitEnquiry;

            @Override
            public void onClick(View v) {

                if(isValidEmaillId(editEmail.getText().toString().trim())){
                    dialog2=new Dialog(getActivity());
                    dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    Constant.showDialog(dialog2);
                    JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, Constant.BaseUrl3+"&token="+Constant.client_token+"&email="+editEmail.getText().toString(), null, new Response.Listener<JSONObject>() {
                        @Override

                        public void onResponse(JSONObject response) {
                            dialog2.dismiss();
                            try {
                                dialog2.dismiss();
                                jsonObject=response.getJSONObject("companyData");
                                Log.d("dfgdfh",jsonObject.optString("id"));



                                final AlertDialog.Builder dialog=new AlertDialog.Builder(getActivity());
                                dialog.setTitle("Send Enquiry");
                                //alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                LayoutInflater layoutInflater=LayoutInflater.from(getActivity());

                                final View view=layoutInflater.inflate(R.layout.custom_enquiry, null);
                                //Toast.makeText(getActivity(), "ghf", Toast.LENGTH_SHORT).show();
                                term=(TextView)view.findViewById(R.id.term);
                                submitEnquiry=(Button)view.findViewById(R.id.submitEnquiry);
                                checkbox=view.findViewById(R.id.checkbox);
                                cname=(LinearLayout)view.findViewById(R.id.cname);
                                pname=(LinearLayout)view.findViewById(R.id.pname);
                                cityname=(LinearLayout)view.findViewById(R.id.cityname);

                                CompanyName=(EditText)view.findViewById(R.id.CompanyName);
                                name=(EditText)view.findViewById(R.id.name);
                                //address=(EditText)view.findViewById(R.id.address);
                                //country=(EditText)view.findViewById(R.id.country);
                                //state=(EditText)view.findViewById(R.id.state);
                                city=(EditText)view.findViewById(R.id.city);
                                mobile=(EditText)view.findViewById(R.id.mobile);
                                message=(EditText)view.findViewById(R.id.message);

                                String text = "<font color=#d4020b>I agree to all the</font> <font color=#05abc5>Term & Conditions</font> ";
                                term.setText(Html.fromHtml(text));

                                submitEnquiry.setOnClickListener(new View.OnClickListener() {

                                    @Override
                                    public void onClick(View view) {


                                        if (checkValidation()) {
                                            if (checkbox.isChecked()) {
                                                dialog2 = new Dialog(getActivity());
                                                dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                                dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                                                Constant.showDialog(dialog2);
//

                                                StringRequest stringRequest = new StringRequest(Request.Method.GET, Constant.BaseUrl4+"&token="+Constant.client_token+
                                                        "&company_id="+Constant.client_id+
                                                        "&generalOrProduct="+getArguments().getString("name")+
                                                        "&fav_service_id="+getArguments().getString("id")+
                                                        "&company_name="+CompanyName.getText().toString()+
                                                        "&fname="+name.getText().toString()+
                                                        "&mobile="+mobile.getText().toString()+
                                                        "&city="+city.getText().toString()+
                                                        "&state="+""+
                                                        "&description="+message.getText().toString()+
                                                        "&email="+editEmail.getText().toString(), new Response.Listener<String>() {

                                                    @Override
                                                    public void onResponse(String response) {
                                                        dialog2.dismiss();
                                                        // Toast.makeText(getActivity(), "" + response, Toast.LENGTH_SHORT).show();
                                                        Log.d("dsfsdfsdgs", response);
                                                        try {
                                                            JSONObject jsonObject = new JSONObject(response);
                                                            JSONObject jsonObject1 = jsonObject.getJSONObject("response");
                                                            if (jsonObject1.optString("sucess").equalsIgnoreCase("1")) {
                                                                Toast.makeText(getActivity(), "Your Query Successfully Submited.", Toast.LENGTH_SHORT).show();


                                                                dialog2.dismiss();
                                                            } else if (jsonObject1.optString("sucess").equalsIgnoreCase("0")) {
                                                                Toast.makeText(getActivity(), "" + jsonObject1.optString("message"), Toast.LENGTH_SHORT).show();
                                                                dialog2.dismiss();
                                                            }
                                                        } catch (JSONException e) {

                                                            e.printStackTrace();
                                                        }

                                                    }
                                                }, new Response.ErrorListener() {
                                                    @Override
                                                    public void onErrorResponse(VolleyError error) {
                                                        dialog2.dismiss();
                                                        Toast.makeText(getActivity(), "Network Problem", Toast.LENGTH_SHORT).show();
                                                    }
                                                })

                                                {
                                                    @Override
                                                    protected Map<String, String> getParams() throws AuthFailureError {
                                                        Map<String, String> params = new HashMap<String, String>();

                                                        params.put("token", Constant.client_token);
                                                        params.put("company_id", Constant.client_id);
                                                        params.put("generalOrProduct", getArguments().getString("name"));
                                                        params.put("fav_service_id", getArguments().getString("id"));
                                                        params.put("company_name", CompanyName.getText().toString());
                                                        params.put("fname", name.getText().toString());
                                                        params.put("mobile", mobile.getText().toString());
                                                        params.put("city", city.getText().toString());
                                                        params.put("state", "");
                                                        params.put("description", message.getText().toString());
                                                        params.put("email", editEmail.getText().toString());


                                                        Log.d("token", Constant.client_token);
                                                        Log.d("company_id", Constant.client_id);
                                                        Log.d("generalOrProduct", getArguments().getString("name"));
                                                        Log.d("fav_service_id", getArguments().getString("id"));
                                                        Log.d("company_name", CompanyName.getText().toString());
                                                        Log.d("fname", name.getText().toString());
                                                        Log.d("mobile", mobile.getText().toString());
                                                        Log.d("city", city.getText().toString());
                                                        Log.d("state", "");
                                                        Log.d("description", message.getText().toString());
                                                        Log.d("email", editEmail.getText().toString());

                                                        return params;

                                                    }
                                                };


                                                stringRequest.setRetryPolicy(new DefaultRetryPolicy(27000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                                                stringRequest.setShouldCache(false);
                                                AppController.getInstance().addToRequestQueue(stringRequest);

                                            }
                                            else {
                                                Toast.makeText(getActivity(), "Please Check on Terms and Condition", Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    }
                                });
                                term.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                });
                                dialog.setView(view);
                                dialog.show();

                                if(jsonObject.optString("sucess").equals("1")){

                                    cname.setVisibility(View.GONE);
                                    pname.setVisibility(View.GONE);
                                    cityname.setVisibility(View.GONE);
                                    Log.d("dghngfgdfh",jsonObject.optString("id"));
//                                CompanyName.setText(jsonObject.optString("company_name"));
//                                name.setText(jsonObject.optString("fname"));
//                                address.setText(jsonObject.optString("fulladdress"));
//                                country.setText(jsonObject.optString("country_id"));
//                                state.setText(jsonObject.optString("state_id"));
//                                city.setText(jsonObject.optString("city_id"));
                                    //message.setText(jsonObject.optString("company_name"));

                                }
                                else
                                {
                                    cname.setVisibility(View.VISIBLE);
                                    pname.setVisibility(View.VISIBLE);
                                    cityname.setVisibility(View.VISIBLE);

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        private boolean checkValidation() {


                            if(!jsonObject.optString("sucess").equals("1")) {
                                if (TextUtils.isEmpty(CompanyName.getText().toString())) {
//
                                    CompanyName.setError("Oops! Company Name field blank");
                                    CompanyName.requestFocus();
                                    return false;
                                }
                            }

                            if(!jsonObject.optString("sucess").equals("1")) {
                            if (TextUtils.isEmpty(name.getText().toString())) {

                                name.setError("Oops! name field blank");
                                name.requestFocus();
                                return false;
                            }


                            }
                            if(!jsonObject.optString("sucess").equals("1")) {
                            if (TextUtils.isEmpty(city.getText().toString())) {

                                city.setError("Oops! City field blank");
                                city.requestFocus();
                                return false;
                            }

                            }
                            else if (TextUtils.isEmpty(mobile.getText().toString())){
                                mobile.setError("Oops! Mobile field blank");
                                mobile.requestFocus();
                                return false;
                            }
                            else if (TextUtils.isEmpty(message.getText().toString())){
                                message.setError("Oops! message field blank");
                                message.requestFocus();



                                message.requestFocus();
                                return false;
                            }

                            return true;
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            dialog2.dismiss();
                            Toast.makeText(getActivity(), "Network Problem", Toast.LENGTH_SHORT).show();
                        }
                    });
                    AppController.getInstance().addToRequestQueue(jsonObjectRequest);

//                    String text = "<font color=#d4020b>I agree to all the</font> <font color=#05abc5>Term & Conditions</font> <font color=#d4020b>of bizzduniya.com </font>";
//                    term.setText(Html.fromHtml(text));
//
//                    term.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//
//                        }
//                    });
//



                }else{
                    Toast.makeText(getActivity(), "Please Enter Valid Email ID", Toast.LENGTH_SHORT).show();
                }


            }
        });

        return  view;

    }

    private boolean isValidEmaillId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

}

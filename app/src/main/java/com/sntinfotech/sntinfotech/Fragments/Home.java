package com.sntinfotech.sntinfotech.Fragments;


import android.app.Dialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
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
import com.viewpagerindicator.CirclePageIndicator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import in.srain.cube.views.GridViewWithHeaderAndFooter;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {

    LinearLayout linearLayout;
    ViewPager viewPager;
    TextView sellAll;

    ImageView product;
//    GridView grid_view;
    GridViewWithHeaderAndFooter gridView;
    Dialog dialog;

//    String url="";
    List<Constant> products=new ArrayList<Constant>();

    //public static  String asd2="test2";
    CustomPagerAdapter mCustomPagerAdapter;
    int[] mResources = {
//            R.drawable.download1,
//            R.drawable.download2,
            R.drawable.dban1,
//            R.drawable.dowable,
//            R.drawable.dban1,
//            R.drawable.dowable

    };
    private static int currentPage = 0;
    List<HashMap<String,String>> AllProducts ;
    Adapter adapter=null;
    Adapter2 listAdapter;
    ListView expListView;
    JSONObject jsonObject1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.home, container, false);

     //   sellAll=(TextView)view.findViewById(R.id.sellAll);

        //product = (ImageView)view.findViewById(R.id.product);
//        grid_view = (GridView) view.findViewById(R.id.grid_view);
         gridView = (GridViewWithHeaderAndFooter) view.findViewById(R.id.gridView);
        Menu2.searchLiner.setVisibility(View.VISIBLE);

        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View headerView = layoutInflater.inflate(R.layout.test_header_view, null);
        View footerView = layoutInflater.inflate(R.layout.test_footer_view, null);
        gridView.addHeaderView(headerView);
        gridView.addFooterView(footerView);
        mCustomPagerAdapter = new CustomPagerAdapter(getActivity());

//        AdView adView = (AdView)view. findViewById(R.id.search_ad_view);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        adView.loadAd(adRequest);

        viewPager = (ViewPager) headerView .findViewById(R.id.slider);
        viewPager.setAdapter(mCustomPagerAdapter);
        viewPager.setPageTransformer(true, new RotateUpTransformer());

        Button getInst=(Button)footerView.findViewById(R.id.getInst);
        ImageView imge=(ImageView) footerView.findViewById(R.id.imge);
        imge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFullImageDialog(getActivity());
            }
        });
        getInst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment=new Enquiry();
                Bundle bundle=new Bundle();
                bundle.putString("name","general");
                bundle.putString("id","");
                FragmentManager fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                fragment.setArguments(bundle);
                ft.replace(R.id.content_frame,fragment).addToBackStack(null).commit();
            }
        });
        dialog=new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//        adapter=new Adapter();
//        grid_view.setAdapter(adapter);

        viewPager.setCurrentItem(0);

        ///Footer Grid view
        expListView = (ListView) footerView.findViewById(R.id.lvExp);

        AllProducts=new ArrayList<>();


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

                            listAdapter = new Adapter2();
                            expListView.setAdapter(listAdapter);

                            setDynamicHeight2(expListView);
                        }

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
        //// end Footer gridview

//        expListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Fragment fragment=new UpdateDetails();
//                Bundle bundle =new Bundle();
//                bundle.putString("image",AllProducts.get(i).get("image"));
//                bundle.putString("title",AllProducts.get(i).get("title"));
//                bundle.putString("description",AllProducts.get(i).get("description"));
//                bundle.putString("created_date",AllProducts.get(i).get("created_date"));
//                FragmentManager fm=getFragmentManager();
//                FragmentTransaction ft=fm.beginTransaction();
//                ft.replace(R.id.content_frame,fragment).addToBackStack(null).commit();
//                fragment.setArguments(bundle);
//            }
//        });

        Constant.showDialog(dialog);
        CirclePageIndicator indicator = (CirclePageIndicator)headerView.findViewById(R.id.indicat);
        indicator.setViewPager(viewPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == mResources.length) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);


//        sellAll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Fragment fragment = new SeeAll();
//                //fragment.setArguments(args);
//                FragmentManager frgManager = getFragmentManager();
//                frgManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack("rte")
//                        .commit();
//            }
//        });

//        product.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Fragment fragment = new Product();
//                //fragment.setArguments(args);
//                FragmentManager frgManager = getFragmentManager();
//                frgManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack("rte")
//                        .commit();
//            }
//        });



        JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(Request.Method.GET,Constant.BaseUrl+"&token="+Constant.client_token+"&getCompanyId="+Constant.client_id, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                dialog.dismiss();



                Log.d("gfdgfsdhgdfhdf","null: "+jsonObject.toString());
                try {
                    if (jsonObject.optString("success").equalsIgnoreCase("0")){
                        Toast.makeText(getActivity(), ""+jsonObject.optString("message"), Toast.LENGTH_SHORT).show();
                    }
                    products.clear();
                    JSONObject jsonObject1=jsonObject.getJSONObject("companyDetail");
                    //Toast.makeText(getActivity(), "ok", Toast.LENGTH_SHORT).show();

                    JSONArray jsonArray = jsonObject1.getJSONArray("productService");
                    Log.d("gdfghdfgfghgfthf",jsonArray.length()+"");
                    for (int i=0;i<jsonArray.length();i++){

                        JSONObject jsonObject2=jsonArray.optJSONObject(i);
                        Log.d("gffsdfgsdfdgdf",jsonObject2.toString());
                        products.add(new Constant(jsonObject2.optString("id"),jsonObject2.optString("service_name"),jsonObject2.optString("photo"),jsonObject2.optString("service_code"),null));

                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter=new Adapter();
                gridView.setAdapter(adapter);
                //setDynamicHeight(gridView);
                adapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                dialog.dismiss();
                Toast.makeText(getActivity(), "Network Problem", Toast.LENGTH_SHORT).show();
                Log.d("dfvdgfdgdf","null"+volleyError.toString());
//                Crouton.makeText(getActivity(), "" + "Some problem occured pls try again"(), Style.ALERT).show();
            }
        });

    AppController.getInstance().addToRequestQueue(jsonObjectRequest2);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment fragment = new Product();
                //fragment.setArguments(args);
                Bundle bundle=new Bundle();
                bundle.putString("id",products.get(position).getId().toString());
                FragmentManager frgManager = getFragmentManager();
                frgManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack("rte")
                        .commit();
                fragment.setArguments(bundle);
            }
        });
        return  view;

    }

    public static void showFullImageDialog(Context context) {
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        //dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.showfullimage);
        ImageView back_img = (ImageView) dialog.findViewById(R.id.back_img);
        ImageView fact_image = (ImageView) dialog.findViewById(R.id.fact_image);
        //showImage(context, "https://www.pinerria.com/upload_images/posting/1522574016img14.jpg", fact_image);
        PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(fact_image);
        //photoViewAttacher.onDrag(2,2);
        photoViewAttacher.update();
        TextView title = (TextView) dialog.findViewById(R.id.title);
        title.setText("Certificate");

        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
    public static void showImage(Context context, String url, ImageView imageView) {
        if (!url.isEmpty() && url != null) {
            //Picasso.with(context).load(url).placeholder(R.color.colorPrimary).error(R.color.gray2).into(imageView);
        }
    }

//    private void showFullImageDialog(Context context) {
//        final Dialog dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
//      //  dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.showfullimage);
//        ImageView back_img = (ImageView) dialog.findViewById(R.id.back_img);
////        ImageView fact_image = (ImageView) dialog.findViewById(R.id.fact_image);
//
//
////        Util.showImage(context, imageurl, fact_image);
////        PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(fact_image);
////        //photoViewAttacher.onDrag(2,2);
////        photoViewAttacher.update();
//        TextView title = (TextView) dialog.findViewById(R.id.title);
//        title.setText("Certificate");
//
//        back_img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.cancel();
//            }
//        });
//        dialog.show();
//
//    }


    public static void setDynamicHeight2(ListView listView) {
        ListAdapter adapter = listView.getAdapter();
        //check adapter if null
        if (adapter == null) {
            return;
        }
        int height = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, listView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            height += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams layoutParams = listView.getLayoutParams();
        layoutParams.height = height + (listView.getDividerHeight() * (adapter.getCount() - 1));
        listView.setLayoutParams(layoutParams);
        listView.requestLayout();
    }

    class CustomPagerAdapter extends PagerAdapter {

        Context mContext;
        LayoutInflater mLayoutInflater;

        public CustomPagerAdapter(Context context) {
            mContext = context;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mResources.length;
        }



        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((RelativeLayout) object);
        }



        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = mLayoutInflater.inflate(R.layout.page_item, container, false);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);




            imageView.setImageResource(mResources[position]);

//            Button gotopage=(Button)itemView.findViewById(R.id.goto_page);
//            gotopage.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    startActivity(new Intent(getApplicationContext(),Choose_Login.class));
//
//                }
//            });


            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((RelativeLayout) object);
        }
    }


    static class ViewHolder {
        TextView name;
        NetworkImageView photoProduct;
        TextView code;
        TextView desc;
        LinearLayout line1;
    }

    class Adapter extends BaseAdapter{

        LayoutInflater inflater;

        Adapter() {
            inflater = (LayoutInflater) Home.this.getActivity().getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }




        @Override
        public int getCount() {
            return products.size();
        }

        @Override
        public Object getItem(int position) {
            return products.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;

//            TranslateAnimation animation = new TranslateAnimation(0,0,500,0);  //  new TranslateAnimation(xFrom,xTo, yFrom,yTo)
//            animation.setDuration(1000);  // animation duration
//            animation.setFillAfter(true);



            convertView=inflater.inflate(R.layout.custom_products,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.photoProduct= (NetworkImageView) convertView.findViewById(R.id.photoProduct);
            viewHolder.name= (TextView) convertView.findViewById(R.id.name);
            viewHolder.code= (TextView) convertView.findViewById(R.id.code);
            viewHolder.line1= (LinearLayout) convertView.findViewById(R.id.line1);
            viewHolder.name.setText(products.get(position).getName1().toString());
            viewHolder.code.setText(products.get(position).getCode().toString());
            try {
                ImageLoader imageLoader=AppController.getInstance().getImageLoader();
                viewHolder.photoProduct.setImageUrl(products.get(position).getPhoto(),imageLoader);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // viewHolder.line1.startAnimation(animation);

            final Typeface tvFont = Typeface.createFromAsset(getActivity().getAssets(), "myriad.otf");
            viewHolder.name.setTypeface(tvFont);
            viewHolder.name.setTypeface(viewHolder.name.getTypeface(),Typeface.BOLD);

            viewHolder.code.setTypeface(tvFont);

            return convertView;
        }
    }


    static class ViewHolder2 {
        TextView title,date;
        NetworkImageView photoProduct2;

    }

    class Adapter2 extends BaseAdapter {

        LayoutInflater inflater;


        Adapter2() {
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
            ViewHolder2 viewHolder;

            convertView=inflater.inflate(R.layout.list_group_update,parent,false);
            viewHolder=new ViewHolder2();

            Log.d("fgsdgfdgdfgdf", String.valueOf(AllProducts.size()));

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

            return convertView;
        }
    }



}

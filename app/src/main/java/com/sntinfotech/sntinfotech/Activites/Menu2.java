package com.sntinfotech.sntinfotech.Activites;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.sntinfotech.sntinfotech.Fragments.AboutUs;
import com.sntinfotech.sntinfotech.Fragments.ContactUs;
import com.sntinfotech.sntinfotech.Fragments.Enquiry;
import com.sntinfotech.sntinfotech.Fragments.Home;
import com.sntinfotech.sntinfotech.Fragments.SeeAll;
import com.sntinfotech.sntinfotech.Fragments.Updates;
import com.sntinfotech.sntinfotech.R;

public class Menu2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView tv1,profile,product,update,enquiry,contact;
    Fragment fragment = null;
    EditText editSearch;
    TextView search;
    public static LinearLayout searchLiner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        search=(TextView)findViewById(R.id.search);
        editSearch=(EditText) findViewById(R.id.editSearch);
        searchLiner=(LinearLayout) findViewById(R.id.searchLiner);

        setTitle("Guru Nanak Engineering Works");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        TextView nav_footer_textview = findViewById(R.id.nav_footer_textview);

        navigationView.setNavigationItemSelectedListener(this);


        AdView adView = (AdView) findViewById(R.id.search_ad_view);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


        try {
            PackageInfo pInfo = getApplicationContext().getPackageManager().getPackageInfo(getPackageName(), 0);
            String ver = pInfo.versionName;
            nav_footer_textview.setText("BizzDuniya.com Seller\nVer: "+ver);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        profile = (TextView) findViewById(R.id.profile);
        product = (TextView) findViewById(R.id.product);
        update = (TextView)  findViewById(R.id.update);
        enquiry = (TextView) findViewById(R.id.enquiry);
        //contact = (TextView) findViewById(R.id.contact);


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.profile2, 0, 0);
                product.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.product1, 0, 0);
                update.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.update1, 0, 0);
                enquiry.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.enquiry1, 0, 0);
               // contact.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.contact1, 0, 0);

                profile.setTextColor(Color.parseColor("#d4020b"));
                product.setTextColor(Color.parseColor("#444648"));
                update.setTextColor(Color.parseColor("#444648"));
                enquiry.setTextColor(Color.parseColor("#444648"));
              //  contact.setTextColor(Color.parseColor("#444648"));
                Fragment fragment = new Home();
                FragmentManager frgManager = getSupportFragmentManager();
                frgManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null)
                        .commit();

            }
        });
        product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.profile1, 0, 0);
                product.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.produc2, 0, 0);
                update.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.update1, 0, 0);
                enquiry.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.enquiry1, 0, 0);
               // contact.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.contact1, 0, 0);
                profile.setTextColor(Color.parseColor("#444648"));
                product.setTextColor(Color.parseColor("#d4020b"));
                update.setTextColor(Color.parseColor("#444648"));
                enquiry.setTextColor(Color.parseColor("#444648"));
               // contact.setTextColor(Color.parseColor("#444648"));
                Fragment fragment = new SeeAll();
                Bundle bundle=new Bundle();
                bundle.putString("search","");
                FragmentManager frgManager = getSupportFragmentManager();
                frgManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null)
                        .commit();
                fragment.setArguments(bundle);

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.profile1, 0, 0);
                product.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.product1, 0, 0);
                update.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.updat2, 0, 0);
                enquiry.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.enquiry1, 0, 0);
               // contact.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.contact1, 0, 0);
                profile.setTextColor(Color.parseColor("#444648"));
                product.setTextColor(Color.parseColor("#444648"));
                update.setTextColor(Color.parseColor("#d4020b"));
                enquiry.setTextColor(Color.parseColor("#444648"));
               // contact.setTextColor(Color.parseColor("#444648"));

                Fragment fragment = new Updates();
                FragmentManager frgManager = getSupportFragmentManager();
                frgManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null)
                        .commit();
            }
        });
        enquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.profile1, 0, 0);
                product.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.product1, 0, 0);
                update.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.update1, 0, 0);
                enquiry.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.enquir2, 0, 0);
               // contact.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.contact1, 0, 0);
                profile.setTextColor(Color.parseColor("#444648"));
                product.setTextColor(Color.parseColor("#444648"));
                update.setTextColor(Color.parseColor("#444648"));
                enquiry.setTextColor(Color.parseColor("#d4020b"));
               // contact.setTextColor(Color.parseColor("#444648"));
                Bundle bundle=new Bundle();
                Fragment fragment = new Enquiry();
                bundle.putString("name","general");
                bundle.putString("id","");
                FragmentManager frgManager = getSupportFragmentManager();
                fragment.setArguments(bundle);
                frgManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null)
                        .commit();
            }
        });
//        contact.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                profile.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.profile1, 0, 0);
//                product.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.product1, 0, 0);
//                update.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.update1, 0, 0);
//                enquiry.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.enquiry1, 0, 0);
//                contact.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.contac2, 0, 0);
//                profile.setTextColor(Color.parseColor("#444648"));
//                product.setTextColor(Color.parseColor("#444648"));
//                update.setTextColor(Color.parseColor("#444648"));
//                enquiry.setTextColor(Color.parseColor("#444648"));
//                contact.setTextColor(Color.parseColor("#d4020b"));
//                Fragment fragment = new ContactUs();
//                FragmentManager frgManager = getSupportFragmentManager();
//                frgManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null)
//                        .commit();
//            }
//        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Toast.makeText(getApplicationContext(), editSearch.getText().toString(), Toast.LENGTH_SHORT).show();
                Fragment fragment = new SeeAll();
                Bundle bundle=new Bundle();
                bundle.putString("search",editSearch.getText().toString());
                FragmentManager frgManager = getSupportFragmentManager();
                frgManager.beginTransaction().add(R.id.content_frame, fragment).addToBackStack(null)
                        .commit();
                fragment.setArguments(bundle);
            }
        });

        if (savedInstanceState == null) {

            profile.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.profile2, 0, 0);
            product.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.product1, 0, 0);
            update.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.update1, 0, 0);
            enquiry.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.enquiry1, 0, 0);
           // contact.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.contact1, 0, 0);

            profile.setTextColor(Color.parseColor("#d4020b"));
            product.setTextColor(Color.parseColor("#444648"));
            update.setTextColor(Color.parseColor("#444648"));
            enquiry.setTextColor(Color.parseColor("#444648"));
           // contact.setTextColor(Color.parseColor("#444648"));
            fragment = new Home();
            FragmentManager frgManager = getSupportFragmentManager();
            frgManager.beginTransaction().add(R.id.content_frame, fragment)
                    .commit();

        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            fragment= new Home();
            replace();
        } else if (id == R.id.nav_profile) {
            fragment= new Home();
            replace();
        } else if (id == R.id.nav_product) {
            fragment= new SeeAll();
            Bundle bundle=new Bundle();
            bundle.putString("search","");
            fragment.setArguments(bundle);
            replace();
        } else if (id == R.id.nav_update) {
            fragment= new Updates();
            replace();
        } else if (id == R.id.nav_enquiry) {
            fragment= new Enquiry();

            Bundle bundle=new Bundle();
            bundle.putString("name","general");
            bundle.putString("id","");
            fragment.setArguments(bundle);

            replace();
        } else if (id == R.id.nav_about) {
            fragment= new AboutUs();
            replace();

        } else if (id == R.id.nav_contact) {
            fragment= new ContactUs();
            replace();


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void replace() {
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction ft=manager.beginTransaction();
        ft.replace(R.id.content_frame,fragment).addToBackStack("fragBack").commit();
    }


}

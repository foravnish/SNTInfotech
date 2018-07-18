package com.sntinfotech.sntinfotech.Activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sntinfotech.sntinfotech.R;

import me.wangyuwei.particleview.ParticleView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        ParticleView  mparticleView;
//
//        mparticleView = (ParticleView)findViewById(R.id.test1);
//
//        mparticleView.startAnim();
//        mparticleView.setCameraDistance(100);
//
//        mparticleView.animate();
//
//        mparticleView.setOnParticleAnimListener(new ParticleView.ParticleAnimListener() {
//            @Override
//            public void onAnimationEnd() {
//                startActivity(new Intent(getApplicationContext(),Menu2.class));
//                finish();
//            }
//        });


        Thread thread=new Thread(){
            @Override
            public void run() {
                super.run();

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent=new Intent(MainActivity.this,Menu2.class);
                startActivity(intent);
                finish();
            }

        };
        thread.start();






    }
}

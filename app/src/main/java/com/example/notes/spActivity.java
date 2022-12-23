 package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

 public class spActivity extends AppCompatActivity {
ImageView imageView;
Animation logo_anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp);

         imageView=findViewById(R.id.imageView);
        logo_anim= AnimationUtils.loadAnimation(this,R.anim.anim_splash);
        imageView.setAnimation(logo_anim) ;
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Anonymous object
               startActivity(new Intent(spActivity.this,MainActivity.class));
               finish();
            }
        },3000);
    }
}
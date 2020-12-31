package com.world_tech_point.name_identity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class OpeningActivity extends AppCompatActivity {

    Animation top,bottom;
    private static int SPLASH_SCREEN = 2000;
    private TextView appName, appDesc;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_opening);

        appName = findViewById(R.id.appName_id);
        appDesc = findViewById(R.id.appDesc_id);
        imageView = findViewById(R.id.imageView);

        top = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottom = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        appName.setAnimation(top);
        imageView.setAnimation(top);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(OpeningActivity.this,MainActivity.class));
                finish();
            }
        },SPLASH_SCREEN);

    }
}
package com.world_tech_point.name_identity.services;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.world_tech_point.name_identity.ContainActivity;
import com.world_tech_point.name_identity.LanguageName;
import com.world_tech_point.name_identity.MainActivity;
import com.world_tech_point.name_identity.R;
import com.world_tech_point.name_identity.SaveLanguage;

public class ChristianBabyActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
    TextView baby, girl;
    SaveLanguage saveLanguage;

    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_christian_baby);

        Toolbar toolbar = findViewById(R.id.christianToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Christianity Baby");

        adView = new AdView(this, LanguageName.FB_BANNER_ID, AdSize.BANNER_HEIGHT_50);
        LinearLayout adContainer = findViewById(R.id.christian_banner_container);
        adContainer.addView(adView);
        adView.loadAd();

        baby = findViewById(R.id.christianBoyBaby);
        girl = findViewById(R.id.christianGirlBaby);
        baby.setAnimation(AnimationUtils.loadAnimation(this,R.anim.slide_in_left));
        girl.setAnimation(AnimationUtils.loadAnimation(this,R.anim.slide_in_right));

        saveLanguage = new SaveLanguage(this);
        quizList(saveLanguage.getLanguage());

    }

    private void quizList(String value) {

        if (value.equals(LanguageName.BENGALI)) {
            bengaliList();
        }  else if (value.equals(LanguageName.HINDI)) {
            hindiList();
        }
    }

    private void bengaliList() {
        setTitle("খ্রিস্ট ধর্মের বেবি");
        baby.setText("ছেলে বাচ্চা");
        girl.setText("মেয়ে শিশু");
    }
    private void hindiList() {
        setTitle("ईसाइयत बेबी");
        baby.setText("लड़का बच्चा");
        girl.setText("छोटी लड़की");
    }




    public void subCategory(View view) {

        int id = view.getId();
        if (id==R.id.christianBoyBaby){
            Intent intent = new Intent(ChristianBabyActivity.this, ContainActivity.class);
            intent.putExtra("value","Christian boy baby");
            startActivity(intent);

        }else if (id==R.id.christianGirlBaby){
            Intent intent = new Intent(ChristianBabyActivity.this, ContainActivity.class);
            intent.putExtra("value","Christian girl baby");
            startActivity(intent);

        }

    }
}
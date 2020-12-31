package com.world_tech_point.name_identity.services;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.world_tech_point.name_identity.ContainActivity;
import com.world_tech_point.name_identity.LanguageName;
import com.world_tech_point.name_identity.MainActivity;
import com.world_tech_point.name_identity.R;
import com.world_tech_point.name_identity.SaveLanguage;

public class HinduismBabyActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_hinduism_baby);

        Toolbar toolbar = findViewById(R.id.hinduToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Hinduism Baby");

        adView = new AdView(this, LanguageName.FB_BANNER_ID, AdSize.BANNER_HEIGHT_50);
        LinearLayout adContainer = findViewById(R.id.hindi_banner_container);
        adContainer.addView(adView);
        adView.loadAd();

        baby = findViewById(R.id.hinduBoyBaby);
        girl = findViewById(R.id.hinduGirlBaby);

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
        setTitle("হিন্দু ধর্মের বেবি");
        baby.setText("ছেলে বাচ্চা");
        girl.setText("মেয়ে শিশু");
    }
    private void hindiList() {
        setTitle("हिंदू धर्म बेबी");
        baby.setText("लड़का बच्चा");
        girl.setText("छोटी लड़की");
    }





    public void subCategory(View view) {

        int id = view.getId();

        if (id==R.id.hinduBoyBaby){
            Intent intent = new Intent(HinduismBabyActivity.this, ContainActivity.class);
            intent.putExtra("value","Hinduism boy baby");
            startActivity(intent);

        }else if (id==R.id.hinduGirlBaby){
            Intent intent = new Intent(HinduismBabyActivity.this, ContainActivity.class);
            intent.putExtra("value","Hinduism girl baby");
            startActivity(intent);

        }

    }
}
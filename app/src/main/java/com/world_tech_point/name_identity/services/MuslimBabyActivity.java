package com.world_tech_point.name_identity.services;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

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
import com.world_tech_point.name_identity.R;
import com.world_tech_point.name_identity.SaveLanguage;

public class MuslimBabyActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    TextView baby, girl;
    CardView boyCard,girlCard;
    SaveLanguage saveLanguage;

    private AdView adView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muslim_baby);

        Toolbar toolbar = findViewById(R.id.muslimToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Muslim Baby");

        adView = new AdView(this, LanguageName.FB_BANNER_ID, AdSize.BANNER_HEIGHT_50);
        LinearLayout adContainer = findViewById(R.id.muslim_banner_container);
        adContainer.addView(adView);
        adView.loadAd();

        baby = findViewById(R.id.muslimBoyBaby);
        girl = findViewById(R.id.muslimGirlBaby);

        boyCard = findViewById(R.id.muslimCardBoy);
        girlCard = findViewById(R.id.muslimCardGirl);

        boyCard.setAnimation(AnimationUtils.loadAnimation(this,R.anim.slide_in_left));
        girlCard.setAnimation(AnimationUtils.loadAnimation(this,R.anim.slide_in_right));


        saveLanguage = new SaveLanguage(this);
        quizList(saveLanguage.getLanguage());

    }

    private void quizList(String value) {

        if (value.equals(LanguageName.BENGALI)) {
            bengaliList();
        }  else if (value.equals(LanguageName.HINDI)) {
            hindiList();

        }  else if (value.equals(LanguageName.ARABIC)) {
            arabicList();
        }
    }

    private void bengaliList() {
        setTitle("মুসলিম বেবি");
        baby.setText("ছেলে বাচ্চা");
        girl.setText("মেয়ে শিশু");
    }
    private void hindiList() {
        setTitle("मुस्लिम बेबी");
        baby.setText("लड़का बच्चा");
        girl.setText("छोटी लड़की");
    }
    private void arabicList() {
        setTitle("طفل مسلم");
        baby.setText("طفل رضيع");
        girl.setText("طفلة رضيعة");
    }





    public void subCategory(View view) {

        int id = view.getId();

        if (id==R.id.muslimBoyBaby){
            Intent intent = new Intent(MuslimBabyActivity.this, ContainActivity.class);
            intent.putExtra("value","Muslim boy baby");
            startActivity(intent);

        }else if (id==R.id.muslimGirlBaby){
            Intent intent = new Intent(MuslimBabyActivity.this, ContainActivity.class);
            intent.putExtra("value","Muslim girl baby");
            startActivity(intent);

        }

    }


}
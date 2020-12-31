package com.world_tech_point.name_identity;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.world_tech_point.name_identity.services.ChristianBabyActivity;
import com.world_tech_point.name_identity.services.HinduismBabyActivity;
import com.world_tech_point.name_identity.services.MuslimBabyActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {


    AlertDialog.Builder builder;
    AlertDialog alertDialog;
    SaveLanguage saveLanguage;
    Spinner spinner;
    TextView muslimBaby, hinduBaby, christianBaby, aboutUs, privacyPolicy, exits, shareApp;

    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.mainToolBar);
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.app_name));

        adView = new AdView(this, LanguageName.FB_BANNER_ID, AdSize.BANNER_HEIGHT_50);
        LinearLayout adContainer = findViewById(R.id.main_banner_container);
        adContainer.addView(adView);
        adView.loadAd();

        muslimBaby = findViewById(R.id.muslimBaby);
        hinduBaby = findViewById(R.id.hinduismBaby);
        christianBaby = findViewById(R.id.christianBaby);
        aboutUs = findViewById(R.id.aboutUs);
        privacyPolicy = findViewById(R.id.privacyPolicy);
        shareApp = findViewById(R.id.shareApp);
        exits = findViewById(R.id.exits);
        saveLanguage = new SaveLanguage(this);
        quizList(saveLanguage.getLanguage());
        spinner = findViewById(R.id.languageSelectSpinner);

        List<String> lan_list = new ArrayList<>();
        lan_list.add("SELECT LANGUAGE");
        lan_list.add(LanguageName.ENGLISH);
        lan_list.add(LanguageName.BENGALI);
        lan_list.add(LanguageName.HINDI);
        lan_list.add(LanguageName.ARABIC);


        ArrayAdapter<String> mainDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lan_list);
        mainDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(mainDataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
                selectLang(spinner.getSelectedItem().toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
            }
        });
        privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action_out_site("https://najatm3n.blogspot.com/2020/12/private-policy-of-modern-baby-name.html");
            }
        });

        shareApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareApp();

            }
        });
        exits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exitAlert();
            }
        });

    }

    private void action_out_site(String url) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        }
    }

    @Override
    public void onBackPressed() {
        exitAlert();
    }

    private void exitAlert() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Exits alert!")
                .setMessage("Are you sure to Exits?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void shareApp() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
        intent.putExtra(Intent.EXTRA_TEXT, "App link: https://play.google.com/store/apps/details?id=" + getPackageName());
        startActivity(Intent.createChooser(intent, "Baby Name App"));
    }


    public void mainCategory(View view) {

        int id = view.getId();
        if (id == R.id.muslimBaby) {
            Intent intent = new Intent(MainActivity.this, MuslimBabyActivity.class);
            startActivity(intent);
        } else if (id == R.id.hinduismBaby) {
            Intent intent = new Intent(MainActivity.this, HinduismBabyActivity.class);
            startActivity(intent);
        } else if (id == R.id.christianBaby) {
            Intent intent = new Intent(MainActivity.this, ChristianBabyActivity.class);
            startActivity(intent);
        }
    }

    private void quizList(String value) {

        if (value.equals(LanguageName.BENGALI)) {
            bengaliList();
        } else if (value.equals(LanguageName.HINDI)) {
            hindiList();

        } else if (value.equals(LanguageName.ARABIC)) {
            arabicList();
        }

    }

    private void bengaliList() {
        muslimBaby.setText("মুসলিম বেবি");
        hinduBaby.setText("হিন্দু ধর্মের বেবি");
        christianBaby.setText("খ্রিস্ট ধর্মের বেবি");
        aboutUs.setText("আমাদের সম্পর্কে");
        shareApp.setText("শেয়ার অ্যাপ্লিকেশন");
        privacyPolicy.setText("গোপনীয়তা নীতি");
        exits.setText("বাহির");


    }

    private void hindiList() {

        muslimBaby.setText("मुस्लिम बेबी");
        hinduBaby.setText("हिंदू धर्म बेबी");
        christianBaby.setText("ईसाइयत बेबी");
        aboutUs.setText("हमारे बारे में");
        shareApp.setText("आवेदन साझा करें");
        privacyPolicy.setText("गोपनीयता नीति");
        exits.setText("बाहर निकलता है");

    }

    private void arabicList() {

        muslimBaby.setText("طفل مسلم");
        hinduBaby.setText("طفل الهندوسية");
        christianBaby.setText("طفل المسيحية");
        aboutUs.setText("معلومات عنا");
        shareApp.setText("تطبيق حصة");
        privacyPolicy.setText("سياسة خاصة");
        exits.setText("مخارج");

    }

    private void selectLang(String lanName) {

        switch (lanName) {
            case LanguageName.ENGLISH:
                saveLanguage.setLanguage(LanguageName.ENGLISH);
                Toasty.success(MainActivity.this, "English Save Successfully", Toast.LENGTH_SHORT, true).show();
                restartPage();

                break;
            case LanguageName.BENGALI:
                saveLanguage.setLanguage(LanguageName.BENGALI);
                Toasty.success(MainActivity.this, "Bengali Save Successfully", Toast.LENGTH_SHORT, true).show();
                restartPage();

                break;
            case LanguageName.HINDI:
                saveLanguage.setLanguage(LanguageName.HINDI);
                Toasty.success(MainActivity.this, "Hindi Save Successfully", Toast.LENGTH_SHORT, true).show();
                restartPage();

                break;
            case LanguageName.ARABIC:
                saveLanguage.setLanguage(LanguageName.ARABIC);
                Toasty.success(MainActivity.this, "Arabic Save Successfully", Toast.LENGTH_SHORT, true).show();
                restartPage();
                break;
            default:
                break;
        }

    }

    private void restartPage() {
        startActivity(new Intent(MainActivity.this, MainActivity.class));
        finish();
    }


}
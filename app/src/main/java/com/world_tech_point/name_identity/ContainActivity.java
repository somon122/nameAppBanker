package com.world_tech_point.name_identity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;

import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.world_tech_point.name_identity.containSources.ChristianClass;
import com.world_tech_point.name_identity.containSources.HinduClass;
import com.world_tech_point.name_identity.containSources.MuslimClass;
import com.world_tech_point.name_identity.favourite.FavoriteActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ContainActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }else if (item.getItemId() == R.id.favourite_id){
            Intent intent = new Intent(ContainActivity.this, FavoriteActivity.class);
            intent.putExtra("value",category);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    RecyclerView recyclerView;
    List<ContainsClass>containsClassList;
    SaveLanguage saveLanguage;
    String category;
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contain);

        Toolbar toolbar = findViewById(R.id.containToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        saveLanguage = new SaveLanguage(this);

        adView = new AdView(this, LanguageName.FB_BANNER_ID, AdSize.BANNER_HEIGHT_50);
        LinearLayout adContainer = findViewById(R.id.contain_banner_container);
        adContainer.addView(adView);
        adView.loadAd();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            category = bundle.getString("value");
            setTitle(category);
            nameListDistribution(category);
        }
        recyclerView = findViewById(R.id.containRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.hasFixedSize();
        ContainAdapter containAdapter = new ContainAdapter(this,containsClassList,category);
        recyclerView.setAdapter(containAdapter);
        containAdapter.notifyDataSetChanged();
        updateListUsers(containsClassList);
    }

    private void nameListDistribution(final String value) {

        if (value.equals("Hinduism boy baby")){
           hinduismBoy(saveLanguage.getLanguage());
        }else if (value.equals("Hinduism girl baby")){
            hinduismGirl(saveLanguage.getLanguage());
        }

        else if (value.equals("Christian boy baby")){
            christianBoy(saveLanguage.getLanguage());
        }else if (value.equals("Christian girl baby")){
            christianGirl(saveLanguage.getLanguage());
        }

        else if (value.equals("Muslim boy baby")){
            muslimBoy(saveLanguage.getLanguage());
        }else if (value.equals("Muslim girl baby")){
            muslimGirl(saveLanguage.getLanguage());
        }
    }


    //// ============ Hindu List  ============
    private void hinduismBoy(String lang) {

        switch (lang){
            case LanguageName.ENGLISH:
                containsClassList = new HinduClass().getBoyEnglishNameList();
                break;
            case LanguageName.BENGALI:
                containsClassList = new HinduClass().getBoyBanglaNameList();
                break;
            case LanguageName.HINDI:
                containsClassList = new HinduClass().getBoyHindiNameList();
                break;
            case LanguageName.ARABIC:
                containsClassList = new HinduClass().getBoyEnglishNameList();
                break;

            default:
                containsClassList = new HinduClass().getBoyEnglishNameList();
                break;
        }
    }
    private void hinduismGirl(String lang) {

        switch (lang){
            case LanguageName.ENGLISH:
                containsClassList = new HinduClass().getGirlEnglishNameList();
                break;
            case LanguageName.BENGALI:
                containsClassList = new HinduClass().getGirlBanglaNameList();
                break;
            case LanguageName.HINDI:
                containsClassList = new HinduClass().getGirlHindiNameList();
                break;
            case LanguageName.ARABIC:
                containsClassList = new HinduClass().getGirlEnglishNameList();
                break;
            default:
                containsClassList = new HinduClass().getGirlEnglishNameList();
                break;
        }

    }


    //// ============ Christian List  ============
    private void christianBoy(String lang) {

        switch (lang){
            case LanguageName.ENGLISH:
                containsClassList = new ChristianClass().getBoyEnglishNameList();
                break;
            case LanguageName.BENGALI:
                containsClassList = new ChristianClass().getBoyBanglaNameList();
                break;
            case LanguageName.HINDI:
                containsClassList = new ChristianClass().getBoyHindiNameList();
                break;
                case LanguageName.ARABIC:
                    containsClassList = new ChristianClass().getBoyEnglishNameList();
                break;
            default:
                containsClassList = new ChristianClass().getBoyEnglishNameList();
                break;
        }
    }

    private void christianGirl(String lang) {
        switch (lang){
            case LanguageName.ENGLISH:
                containsClassList = new ChristianClass().getGirlEnglishNameList();
                break;
            case LanguageName.BENGALI:
                containsClassList = new ChristianClass().getGirlBanglaNameList();
                break;
            case LanguageName.HINDI:
                containsClassList = new ChristianClass().getGirlHindiNameList();
                break;

            case LanguageName.ARABIC:
                containsClassList = new ChristianClass().getGirlEnglishNameList();
                break;
            default:
                containsClassList = new ChristianClass().getGirlEnglishNameList();
                break;
        }
    }

    //// ============ Muslim List  ============
    private void muslimBoy(String lang) {

        switch (lang){
            case LanguageName.ENGLISH:
                containsClassList = new MuslimClass().getBoyEnglishNameList();
                break;
            case LanguageName.BENGALI:
                containsClassList = new MuslimClass().getBoyBanglaNameList();
                break;
            case LanguageName.HINDI:
                containsClassList = new MuslimClass().getBoyHindiNameList();
                break;
            case LanguageName.ARABIC:
                containsClassList = new MuslimClass().getBoyArabianNameList();
                break;
            default:
                containsClassList = new MuslimClass().getBoyEnglishNameList();
                break;
        }

    }

    private void muslimGirl(String lang) {
        switch (lang){
            case LanguageName.ENGLISH:
                containsClassList = new MuslimClass().getGirlEnglishNameList();
                break;
            case LanguageName.BENGALI:
                containsClassList = new MuslimClass().getGirlBanglaNameList();
                break;
            case LanguageName.HINDI:
                containsClassList = new MuslimClass().getGirlHindiNameList();
                break;
            case LanguageName.ARABIC:
                containsClassList = new MuslimClass().getGirlArabianNameList();
                break;
            default:
                containsClassList = new MuslimClass().getGirlEnglishNameList();
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.searchBar_id);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchQuestion(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                searchQuestion(newText);
                return false;
            }
        });
        return true;
    }



    private void searchQuestion(String recherche) {
        if (recherche.length() > 0)
            recherche = recherche.substring(0, 1) + recherche.substring(1);

        List<ContainsClass> results = new ArrayList<>();
        for(ContainsClass containsClass : containsClassList){
            if(containsClass.getName() != null && containsClass.getName().contains(recherche)){
                results.add(containsClass);
            }
        }
        updateListUsers(results);
    }

    private void updateListUsers(List<ContainsClass> listQuestion) {

        Collections.sort(listQuestion, new Comparator<ContainsClass>() {
            @Override
            public int compare(ContainsClass o1, ContainsClass o2) {
                int res = 1;
                if (o1.getName() == (o2.getName())) {
                    res = -1;
                }
                return res;
            }
        });
        ContainAdapter containAdapter = new ContainAdapter(this,listQuestion, category);
        recyclerView.setAdapter(containAdapter);
        containAdapter.notifyDataSetChanged();


    }


}
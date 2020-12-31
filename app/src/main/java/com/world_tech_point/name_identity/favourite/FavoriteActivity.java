package com.world_tech_point.name_identity.favourite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.world_tech_point.name_identity.ContainActivity;
import com.world_tech_point.name_identity.ContainAdapter;
import com.world_tech_point.name_identity.LanguageName;
import com.world_tech_point.name_identity.R;
import com.world_tech_point.name_identity.database.Favourite_DB_Manager;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity {
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    RecyclerView recyclerView;
    List<FavouriteClass>favouriteClassList;
    Favourite_DB_Manager favourite_db_manager;
    String category;

    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        Toolbar toolbar = findViewById(R.id.favoriteToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            category = bundle.getString("value");
            setTitle(category);
        }

        adView = new AdView(this, LanguageName.FB_BANNER_ID, AdSize.BANNER_HEIGHT_50);
        LinearLayout adContainer = findViewById(R.id.favourite_banner_container);
        adContainer.addView(adView);
        adView.loadAd();

        favourite_db_manager = new Favourite_DB_Manager(this);
        favouriteClassList = new ArrayList<>();
        favouriteClassList = favourite_db_manager.getData(category);
        recyclerView = findViewById(R.id.favouriteRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.hasFixedSize();
        FavouriteAdapter favouriteAdapter = new FavouriteAdapter(this,favouriteClassList);
        recyclerView.setAdapter(favouriteAdapter);
        favouriteAdapter.notifyDataSetChanged();

    }
}
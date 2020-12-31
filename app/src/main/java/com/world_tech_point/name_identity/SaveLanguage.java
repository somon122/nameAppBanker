package com.world_tech_point.name_identity;

import android.content.Context;
import android.content.SharedPreferences;

public class SaveLanguage {

    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SaveLanguage(Context context) {
        this.context = context;

        sharedPreferences = context.getSharedPreferences("LanguageData",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public void setLanguage (String language){
        editor.putString("language",language);
        editor.commit();
    }

    public String getLanguage (){
        String language = sharedPreferences.getString("language","ENGLISH");
        return language;
    }
    public void delete (){
        editor.clear();
        editor.commit();

    }


}
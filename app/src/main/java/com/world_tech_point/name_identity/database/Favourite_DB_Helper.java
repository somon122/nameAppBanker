package com.world_tech_point.name_identity.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class Favourite_DB_Helper extends SQLiteOpenHelper {

    private Context context;
    public static final String DB_NAME = "FavouriteName_db";
    public static final int VERSION = 1;
    public static final String FAVOURITE_TABLE = "Favourite_List";
    public static final String KEY_ID = "id";
    public static final String CATEGORY = "category";
    public static final String NAME = "name";
    public static final String MEANING = "meaning";

    private static final String Create_Table = "CREATE TABLE " + FAVOURITE_TABLE + "( " + KEY_ID + " INTEGER  PRIMARY KEY ," +
            "" + CATEGORY + " TEXT ," + NAME + " TEXT ," + MEANING + " TEXT )";


    public Favourite_DB_Helper(Context context) {
        super(context, DB_NAME, null, VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(Create_Table);
            //Toast.makeText(context, "onCreate is called", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            //Toast.makeText(context, "Exception", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + FAVOURITE_TABLE);
        onCreate(db);

    }
}

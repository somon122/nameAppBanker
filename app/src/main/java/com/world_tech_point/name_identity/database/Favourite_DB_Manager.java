package com.world_tech_point.name_identity.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.world_tech_point.name_identity.favourite.FavouriteClass;

import java.util.ArrayList;
import java.util.List;

public class Favourite_DB_Manager {
    Favourite_DB_Helper favouriteDb_Helper;
    SQLiteDatabase db;

    public Favourite_DB_Manager(Context context) {
        favouriteDb_Helper = new Favourite_DB_Helper(context);

    }

    public Boolean save_Data(FavouriteClass favouriteClass) {
        db = favouriteDb_Helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Favourite_DB_Helper.CATEGORY, favouriteClass.getCategory());
        contentValues.put(Favourite_DB_Helper.NAME, favouriteClass.getName());
        contentValues.put(Favourite_DB_Helper.MEANING, favouriteClass.getMeaning());
        long isInsert = db.insert(Favourite_DB_Helper.FAVOURITE_TABLE, null, contentValues);
        db.close();
        if (isInsert > 0) {
            return true;
        } else {
            return false;
        }
    }


   public Boolean update_Data(FavouriteClass favouriteClass) {
        db = favouriteDb_Helper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        long isInsert = db.update(Favourite_DB_Helper.FAVOURITE_TABLE,contentValues,"qId="+ "",null);
        db.close();
        if (isInsert > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<FavouriteClass> getData(String category) {
        List<FavouriteClass> favouriteClassList = new ArrayList<>();
        db = favouriteDb_Helper.getReadableDatabase();
        String Query = "Select * from " + Favourite_DB_Helper.FAVOURITE_TABLE + " where " + Favourite_DB_Helper.CATEGORY + " = ?";
        Cursor cursor = db.rawQuery(Query, new String[]{category});
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndex(Favourite_DB_Helper.KEY_ID));
                String category2 = cursor.getString(cursor.getColumnIndex(Favourite_DB_Helper.CATEGORY));
                String name = cursor.getString(cursor.getColumnIndex(Favourite_DB_Helper.NAME));
                String meaning = cursor.getString(cursor.getColumnIndex(Favourite_DB_Helper.MEANING));
                FavouriteClass favouriteClass = new FavouriteClass(id,category2,name,meaning);
                favouriteClassList.add(favouriteClass);

            } while (cursor.moveToNext());
            db.close();
        }
        return favouriteClassList;
    }

    public boolean delete(int id) {
        db = favouriteDb_Helper.getWritableDatabase();
        int d = db.delete(Favourite_DB_Helper.FAVOURITE_TABLE, "id="+ id, null);
        if (d > 0) {
            return true;
        } else {
            return false;
        }

    }
}

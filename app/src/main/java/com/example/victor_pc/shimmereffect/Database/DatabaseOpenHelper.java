package com.example.victor_pc.shimmereffect.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.victor_pc.shimmereffect.model.Item;
import com.example.victor_pc.shimmereffect.model.PinLock;

import java.util.ArrayList;
import java.util.List;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ShimmerEffect.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;
    Cursor cursor;

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String item = "CREATE TABLE Item(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Nama TEXT," +
                "Pekerjaan TEXT," +
                "Gender TEXT)";
        db.execSQL(item);
        String item2 = "CREATE TABLE Pin(" +
                "LockNumber TEXT," +
                "isRegistered TEXT)";
        db.execSQL(item2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertToDb(Item item) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Nama", item.getName());
        values.put("Pekerjaan", item.getPekerjaan());
        values.put("Gender", "Male");
        long newRowId = db.insert("Item", null, values);
    }

    public void updateDb(Item item, int position) {
        db = this.getWritableDatabase();
        String selection = "ID LIKE ?";
        String arg[] = {String.valueOf(position)};
        ContentValues values = new ContentValues();
        Log.d("<Update>", item.getName() + item.getPekerjaan());
        values.put("Nama", item.getName());
        values.put("Pekerjaan", item.getPekerjaan());
        values.put("Gender", "Male");
        long newRowId = db.update("Item", values, selection,  arg);
    }

    public List getItemData() {
        List itemList = new ArrayList<>();
        db = this.getReadableDatabase();
        String query = String.format("SELECT * FROM Item");
        cursor = db.rawQuery(query, new String[]{});
        if(cursor.moveToFirst()){
            do{
                Item item = new Item();
                item.setName(cursor.getString(1));
                item.setPekerjaan(cursor.getString(2));
                item.setGender(cursor.getString(3));
                itemList.add(item);
            }while(cursor.moveToNext());
        }
        return itemList;
    }

    public List getFilterList(String nama) {
        List itemList = new ArrayList<>();
        db = this.getReadableDatabase();
        String query = String.format("SELECT * FROM ITEM WHERE Nama LIKE '%s%%'", nama);
        cursor = db.rawQuery(query, new String[]{});
        if(cursor.moveToFirst()){
            do{
                Item item = new Item();
                item.setName(cursor.getString(1));
                item.setPekerjaan(cursor.getString(2));
                item.setGender(cursor.getString(3));
                itemList.add(item);
            }while(cursor.moveToNext());
        }
        return itemList;
    }

    public void insertPin(String pin) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("LockNumber", pin);
        values.put("IsRegistered", "True");
        long newRowId = db.insert("Pin", null, values);
    }

    public PinLock getPin() {
        PinLock pin = null;
        db = this.getReadableDatabase();
        String query = String.format("SELECT * FROM Pin");
        cursor = db.rawQuery(query, new String[]{});
        if(cursor.moveToFirst()){
           pin = new PinLock();
           pin.setLockNumber(cursor.getString(0));
           if(cursor.getString(1).equals("True")) {
               pin.setRegistered(true);
           }
        }
        cursor.close();
        return pin;
    }
}

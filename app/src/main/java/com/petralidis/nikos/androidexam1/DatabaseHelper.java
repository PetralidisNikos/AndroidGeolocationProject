package com.petralidis.nikos.androidexam1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    //στοιχεια/μορφη του πινακα των συντεταγμενων
    private static final int DATABASE_VERSION = 15;
    private static final String DATABASE_NAME = "GeolocationDatabase";
    private static final String TABLE_GEOLOCATION = "Geolocation";
    private static final String KEY_USERID = "USERID";
    private static final String KEY_LONGITUDE = "LONGITUDE";
    private static final String KEY_LATITUDE = "LATITUDE";
    private static final String KEY_TIMESTAMP = "TIMESTAMP";

    String tempuserid;
    String temptimestamp;

    //κονστρακτορας της κλασης
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //μεθοδος δημιουργιας/ανοιγματος του πινακα
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_GEOLOCATION_TABLE = " CREATE TABLE " + TABLE_GEOLOCATION + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +KEY_USERID +", "+ KEY_LONGITUDE +", "+ KEY_LATITUDE +", "+ KEY_TIMESTAMP +", "+ " text" +")";
        db.execSQL(CREATE_GEOLOCATION_TABLE);
    }


    //μεθοδος αναβαθμισης του πινακα
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GEOLOCATION);
        onCreate(db);
    }


    //μεθοδος προσθηκης συντεταγμενων στη βαση
    void addGeolocation(Geolocation geolocation) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERID, geolocation.getUserid());
        values.put(KEY_LONGITUDE, geolocation.getLongitude());
        values.put(KEY_LATITUDE, geolocation.getLatitude());
        values.put(KEY_TIMESTAMP, geolocation.getdt());

        db.insert(TABLE_GEOLOCATION, null, values);
        db.close();
    }

    //μεθοδος επιλογης TimeStamps για την δημιουργια του spinner(TIMESTAMPS)
    public List<String> Timestamps() {

        List<String> geolocationList = new ArrayList<>();
        String selectQuery = "SELECT DISTINCT TIMESTAMP FROM " + TABLE_GEOLOCATION;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {
                Geolocation geolocation = new Geolocation();
                geolocation.setdt(cursor.getString(0));
                geolocationList.add(geolocation.getdt());
            } while(cursor.moveToNext());
        }
        return (geolocationList);
    }

    //μεθοδος επιλογης συμφωνα με την επιλογη του χρηστη (SELECTGEOLOCATIONS)
    public List<Geolocation> SelectGeolocations(String tempuserid ,String temptimestamp) {

        List<Geolocation> geolocationList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_GEOLOCATION + " WHERE USERID = "  + "'"+tempuserid+ "'"+ " AND TIMESTAMP = "+ "'"+temptimestamp+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor2 = db.rawQuery(selectQuery,null);

        if (cursor2.moveToFirst()){
            do {
                Geolocation geolocation = new Geolocation();

                geolocation.setid(cursor2.getInt(0));
                geolocation.setUserid(cursor2.getString(1));
                geolocation.setLongitude(cursor2.getFloat(2));
                geolocation.setLatitude(cursor2.getFloat(3));
                geolocation.setdt(cursor2.getString(4));

                geolocationList.add(geolocation);

            } while(cursor2.moveToNext());
        }
        return (geolocationList);
    }

    public List<Geolocation> SelectAll(){

        List<Geolocation> geolocationList2 = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_GEOLOCATION;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor3 = db.rawQuery(selectQuery,null);

        if (cursor3.moveToFirst()){
            do {
                Geolocation geolocation = new Geolocation();
                geolocation.setid(cursor3.getInt(0));
                geolocation.setUserid(cursor3.getString(1));
                geolocation.setLongitude(cursor3.getFloat(2));
                geolocation.setLatitude(cursor3.getFloat(3));
                geolocation.setdt(cursor3.getString(4));

                geolocationList2.add(geolocation);

            } while(cursor3.moveToNext());
        }
        return (geolocationList2);
    }
    }

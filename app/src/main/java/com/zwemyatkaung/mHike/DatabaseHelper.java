package com.zwemyatkaung.mHike;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DBName = "Test.db";
    private static final int DBVersion = 1;
    private static final String TableName = "Hike";
    private static final String HikeID = "_id";
    private static final String HikeName = "name";
    private static final String Location = "location";
    private static final String Date = "date";
    private static final String Parking = "parking";
    private static final String Length = "length";
    private static final String Duration = "duration";
    private static final String Weather = "weather";
    private static final String Difficulty = "difficulty";
    private static final String Description = "description";
    private final Context context;

    DatabaseHelper(@Nullable Context context) {
        super(context, DBName, null, DBVersion);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TableName +
                " (" + HikeID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                HikeName + " TEXT, " +
                Location + " TEXT, " +
                Date + " TEXT, " +
                Parking + " TEXT, " +
                Length + " TEXT, " +
                Duration + " TEXT, " +
                Weather + " TEXT, " +
                Difficulty + " TEXT, " +
                Description + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(db);
    }

    void addHike(String name, String location, String date, String parking, String length,
                 String duration, String weather, String difficulty, String description) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(HikeName, name);
        cv.put(Location, location);
        cv.put(Date, date);
        cv.put(Parking, parking);
        cv.put(Length, length);
        cv.put(Duration, duration);
        cv.put(Weather, weather);
        cv.put(Difficulty, difficulty);
        cv.put(Description, description);

        long result = db.insert(TableName, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed to add a new Hike", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData() {
        String query = "SELECT * FROM " + TableName;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    void updateHike(String row_id, String name, String location, String date, String parking, String length,
                    String duration, String weather, String difficulty, String description) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(HikeName, name);
        cv.put(Location, location);
        cv.put(Date, date);
        cv.put(Parking, parking);
        cv.put(Length, length);
        cv.put(Duration, duration);
        cv.put(Weather, weather);
        cv.put(Difficulty, difficulty);
        cv.put(Description, description);

        long result = db.update(TableName, cv, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed to update", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully updated", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteHike(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TableName, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully deleted", Toast.LENGTH_SHORT).show();
        }
    }
}

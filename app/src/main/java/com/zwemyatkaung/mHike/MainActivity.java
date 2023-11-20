package com.zwemyatkaung.mHike;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton Add;

    DatabaseHelper dbh;

    ArrayList<String> id, name, location, date, parking, length, duration, weather, difficulty, description;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        Add = findViewById(R.id.btnAdd);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
                Log.i("Intent", "In MainActivity");
            }
        });

        dbh = new DatabaseHelper(MainActivity.this);
        id = new ArrayList<>();
        name = new ArrayList<>();
        location = new ArrayList<>();
        date = new ArrayList<>();
        parking = new ArrayList<>();
        length = new ArrayList<>();
        duration = new ArrayList<>();
        weather = new ArrayList<>();
        difficulty = new ArrayList<>();
        description = new ArrayList<>();

        storeData();

        customAdapter = new CustomAdapter(MainActivity.this, this, id, name, location, date,
                parking, length, duration, weather, difficulty, description);

        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    void storeData() {
        Cursor cursor = dbh.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                location.add(cursor.getString(2));
                date.add(cursor.getString(3));
                parking.add(cursor.getString(4));
                length.add(cursor.getString(5));
                duration.add(cursor.getString(6));
                weather.add(cursor.getString(7));
                difficulty.add(cursor.getString(8));
                description.add(cursor.getString(9));
            }
        }
    }
}
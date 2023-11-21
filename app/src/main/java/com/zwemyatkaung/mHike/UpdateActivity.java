package com.zwemyatkaung.mHike;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    EditText name, location, date, parking, length, duration, weather, difficulty, description;
    Button update;
    ImageButton delete;
    String id, Name, Location, Date, Parking, Length, Duration, Weather, Difficulty, Description;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name = findViewById(R.id.Name2);
        location = findViewById(R.id.Location2);
        date = findViewById(R.id.Date2);
        parking = findViewById(R.id.Parking2);
        length = findViewById(R.id.Length2);
        duration = findViewById(R.id.Duration2);
        weather = findViewById(R.id.Weather2);
        difficulty = findViewById(R.id.Difficulty2);
        description = findViewById(R.id.Description2);
        update = findViewById(R.id.Update);
        delete = findViewById(R.id.Delete);

        getAndSetIntentData();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper dbh = new DatabaseHelper(UpdateActivity.this);
                Name = name.getText().toString().trim();
                Location = location.getText().toString().trim();
                Date = date.getText().toString().trim();
                Parking = parking.getText().toString().trim();
                Length = length.getText().toString().trim();
                Duration = duration.getText().toString().trim();
                Weather = weather.getText().toString().trim();
                Difficulty = difficulty.getText().toString().trim();
                Description = description.getText().toString().trim();
                dbh.updateHike(id, Name, Location, Date, Parking, Length, Duration, Weather, Difficulty, Description);
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("location") && getIntent().hasExtra("date") &&
                getIntent().hasExtra("parking") && getIntent().hasExtra("length") &&
                getIntent().hasExtra("duration") && getIntent().hasExtra("weather") &&
                getIntent().hasExtra("difficulty") && getIntent().hasExtra("description")) {

            id = getIntent().getStringExtra("id");
            Name = getIntent().getStringExtra("name");
            Location = getIntent().getStringExtra("location");
            Date = getIntent().getStringExtra("date");
            Parking = getIntent().getStringExtra("parking");
            Length = getIntent().getStringExtra("length");
            Duration = getIntent().getStringExtra("duration");
            Weather = getIntent().getStringExtra("weather");
            Difficulty = getIntent().getStringExtra("difficulty");
            Description = getIntent().getStringExtra("description");

            name.setText(Name);
            location.setText(Location);
            date.setText(Date);
            parking.setText(Parking);
            length.setText(Length);
            duration.setText(Duration);
            weather.setText(Weather);
            difficulty.setText(Difficulty);
            description.setText(Description);

        } else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Delete " + name);
        alert.setMessage("Are you sure you want to delete " + name + " ?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseHelper dbh = new DatabaseHelper(UpdateActivity.this);
                dbh.deleteHike(id);
                finish();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.create().show();
    }
}
package com.zwemyatkaung.mHike;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    String parking = "";
    EditText name, location, date, length, duration, weather, difficulty, description;
    Button add, cancle;
    RadioButton yes, no;
    RadioGroup radioGroup;
    private DatabaseHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Log.i("Intent", "In Add Activity");

        dbh = new DatabaseHelper(this);

        name = findViewById(R.id.txtName);
        location = findViewById(R.id.txtLocation);
        date = findViewById(R.id.txtDate);
        radioGroup = findViewById(R.id.rdGroup);
        length = findViewById(R.id.txtLength);
        duration = findViewById(R.id.txtDuration);
        weather = findViewById(R.id.txtWeather);
        difficulty = findViewById(R.id.txtDiffculty);
        description = findViewById(R.id.txtDescription);
        add = findViewById(R.id.btnAdd);
        cancle = findViewById(R.id.btnCancel);

        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.i("StoreData", "In OnClick Function");

                if (name.getText().toString().trim().isEmpty()) {
                    Toast.makeText(AddActivity.this, "Please enter hike name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (location.getText().toString().trim().isEmpty()) {
                    Toast.makeText(AddActivity.this, "Please enter hike location", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (date.getText().toString().trim().isEmpty()) {
                    Toast.makeText(AddActivity.this, "Please enter hike date", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (length.getText().toString().trim().isEmpty()) {
                    Toast.makeText(AddActivity.this, "Please enter hike length", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (duration.getText().toString().trim().isEmpty()) {
                    Toast.makeText(AddActivity.this, "Please enter hike duration", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (weather.getText().toString().trim().isEmpty()) {
                    Toast.makeText(AddActivity.this, "Please enter hike weather", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (difficulty.getText().toString().trim().isEmpty()) {
                    Toast.makeText(AddActivity.this, "Please enter hike difficulty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (description.getText().toString().trim().isEmpty()) {
                    Toast.makeText(AddActivity.this, "Please enter hike description", Toast.LENGTH_SHORT).show();
                    return;
                }

                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                Log.i("CheckedSelectedRadioButtonID", Integer.toString(selectedRadioButtonId));

                if (selectedRadioButtonId == -1) {
                    Toast.makeText(AddActivity.this, "Please select parking availability", Toast.LENGTH_SHORT).show();
                    return;
                }

                RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

                if (selectedRadioButton.getId() == R.id.rdoYes) {
                    parking = "Yes";
                } else if (selectedRadioButton.getId() == R.id.rdoNo) {
                    parking = "No";
                } else {
                    Toast.makeText(AddActivity.this, "Please select parking availability", Toast.LENGTH_SHORT).show();
                    return; // Exit the function if no radio button is selected
                }

                dbh.addHike(name.getText().toString().trim(),
                        location.getText().toString().trim(),
                        date.getText().toString().trim(),
                        parking,
                        length.getText().toString().trim(),
                        duration.getText().toString().trim(),
                        weather.getText().toString().trim(),
                        difficulty.getText().toString().trim(),
                        description.getText().toString().trim());
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
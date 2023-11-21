package com.zwemyatkaung.mHike;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context context;
    Activity activity;
    ArrayList id, name, location, date, parking, length, duration, weather, difficulty, description;

    CustomAdapter(Activity activity, Context context, ArrayList id, ArrayList name, ArrayList location, ArrayList date,
                  ArrayList parking, ArrayList length, ArrayList duration, ArrayList weather,
                  ArrayList difficulty, ArrayList description) {
        this.activity = activity;
        this.context = context;
        this.id = id;
        this.name = name;
        this.location = location;
        this.date = date;
        this.parking = parking;
        this.length = length;
        this.duration = duration;
        this.weather = weather;
        this.difficulty = difficulty;
        this.description = description;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    public void filterList(ArrayList<String> filteredId, ArrayList<String> filteredName,ArrayList<String> filteredLocation,ArrayList<String> filteredDate,ArrayList<String> filteredParking,ArrayList<String> filteredLength,ArrayList<String> filteredDuration,ArrayList<String> filteredWeather,ArrayList<String> filteredDiffculty,ArrayList<String> filteredDescription) {
        id = filteredId;
        name = filteredName;
        location = filteredLocation;
        date=filteredDate;
        parking=filteredParking;
        length=filteredLength;
        duration=filteredDuration;
        weather=filteredWeather;
        difficulty=filteredDiffculty;
        description=filteredDescription;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.id.setText(String.valueOf(id.get(position)));
        holder.name.setText(String.valueOf(name.get(position)));
        holder.location.setText(String.valueOf(location.get(position)));
        holder.date.setText(String.valueOf(date.get(position)));

        holder.main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(id.get(position)));
                intent.putExtra("name", String.valueOf(name.get(position)));
                intent.putExtra("location", String.valueOf(location.get(position)));
                intent.putExtra("date", String.valueOf(date.get(position)));
                intent.putExtra("parking", String.valueOf(parking.get(position)));
                intent.putExtra("length", String.valueOf(length.get(position)));
                intent.putExtra("duration", String.valueOf(duration.get(position)));
                intent.putExtra("weather", String.valueOf(weather.get(position)));
                intent.putExtra("difficulty", String.valueOf(difficulty.get(position)));
                intent.putExtra("description", String.valueOf(description.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id, name, location, date;
        LinearLayout main;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.hike_id);
            name = itemView.findViewById(R.id.name);
            location = itemView.findViewById(R.id.location);
            date = itemView.findViewById(R.id.date);
            main = itemView.findViewById(R.id.main);
        }
    }
}

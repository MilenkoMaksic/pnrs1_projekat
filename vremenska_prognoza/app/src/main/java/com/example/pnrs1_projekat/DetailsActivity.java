package com.example.pnrs1_projekat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.view.View.GONE;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    TextView location;
    TextView day;
    String s;
    FrameLayout frameLayout;
    LinearLayout temperatureLayout, sunRiseSetLayout, windLayout;
    Button temperatureButton, sunRiseSetButton, windButton;
    Spinner unitForDegrees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        /* LOKACIJA */
        location = findViewById(R.id.location);
        Intent myIntent = getIntent();
        Bundle b = myIntent.getExtras();
        s = b.getString("location");
        s = location.getText() + " " + s;
        location.setText(s);

        /* DAN */
        Locale serbian = new Locale.Builder().setLanguage("sr").setRegion("RS").setScript("Latn").build();
        Date date = Calendar.getInstance(serbian).getTime();
        String today = new SimpleDateFormat("EEEE", serbian).format(date);
        today = today.substring(0, 1).toUpperCase() + today.substring(1);
        day = findViewById(R.id.day);
        day.setText(day.getText().toString() + " " + today);

        temperatureLayout = findViewById(R.id.temperatureLayout);
        sunRiseSetLayout = findViewById(R.id.sunRiseSetLayout);
        windLayout = findViewById(R.id.windLayout);
        frameLayout = findViewById(R.id.frameLayout);

        temperatureLayout.setVisibility(GONE);
        sunRiseSetLayout.setVisibility(GONE);
        windLayout.setVisibility(GONE);

        temperatureButton = findViewById(R.id.temperatureButton);
        sunRiseSetButton = findViewById(R.id.sunRiseSetButton);
        windButton = findViewById(R.id.windButton);

        temperatureButton.setOnClickListener(this);
        sunRiseSetButton.setOnClickListener(this);
        windButton.setOnClickListener(this);

        unitForDegrees = findViewById(R.id.spinnerJedinica);
        String[] items = new String[]{"°C", "°F"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        unitForDegrees.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.temperatureButton:
                temperatureLayout.setVisibility(v.VISIBLE);
                sunRiseSetLayout.setVisibility(v.INVISIBLE);
                windLayout.setVisibility(v.INVISIBLE);
                break;
            case R.id.sunRiseSetButton:
                temperatureLayout.setVisibility(v.INVISIBLE);
                sunRiseSetLayout.setVisibility(v.VISIBLE);
                windLayout.setVisibility(v.INVISIBLE);
                break;
            case R.id.windButton:
                temperatureLayout.setVisibility(v.INVISIBLE);
                sunRiseSetLayout.setVisibility(v.INVISIBLE);
                windLayout.setVisibility(v.VISIBLE);
                break;
        }
    }
}
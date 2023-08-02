package com.example.appsettings;

import static android.view.View.VISIBLE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * This app show a display name
 * according to the settings
 * User can change color of the name
 * using app settings
 */

public class MainActivity extends AppCompatActivity {

    TextView name;
    TextView surname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);

        //Get value from shared preferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String namePref = sharedPreferences.getString(SettingsActivity.KEY_PREF_DISPLAY_NAME, "Adam");
        Boolean surnamePref = sharedPreferences.getBoolean(SettingsActivity.KEY_PREF_SURNAME, false);
        String surnameTextPref = sharedPreferences.getString(SettingsActivity.KEY_PREF_SURNAME_TEXT, "Smith");
        String colorPref = sharedPreferences.getString(SettingsActivity.KEY_PREF_COLOR, "green");


        //Set color according to settings
        switch (colorPref){
            case "green":
                name.setTextColor(Color.parseColor("#10A516"));
                surname.setTextColor(Color.parseColor("#10A516"));
                break;
            case "red":
                name.setTextColor(Color.parseColor("#FF97160D"));
                surname.setTextColor(Color.parseColor("#FF97160D"));
                break;

        }

        //Set display name according to settings
        name.setText(namePref);

        //Set display surname according to settings
        if (surnamePref) {
            surname.setVisibility(View.VISIBLE);
            surname.setText(surnameTextPref);
        }
        else {
            surname.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate menu items
        getMenuInflater().inflate(R.menu.app_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.settings:
                Intent i = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
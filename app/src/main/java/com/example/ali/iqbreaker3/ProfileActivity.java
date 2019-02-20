package com.example.ali.iqbreaker3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    int theme;
    private static final int Theme = 0;
    private static final int Theme1 = 1;
    private static final int Theme2 = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button btn_start = findViewById(R.id.btn_start);
        TextView txtView = findViewById(R.id.textView7);
        TextView txtView1 = findViewById(R.id.textView);
        String userName = getIntent().getStringExtra("Username");


        Intent intent = getIntent();
        theme = intent.getIntExtra("theme", Theme);
        changeTheme(theme);

        SharedPreferences userPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean passwordPref = userPrefs.getBoolean("pref_sync", false);

        if (passwordPref) {
            txtView.setText("Your Increased Difficulty is on");

        } else {
            txtView.setText("Your Increased Difficulty is off");
        }

        txtView1.setText("Welcome " + userName);


        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),
                        QuizActivity.class);
                intent.putExtra("theme", theme); // pass the current score to the second screen
                startActivity(intent);
            }
        });


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionSettings:
                Intent intent = new Intent(ProfileActivity.this, UserPrefActivity.class);
                startActivity(intent);
        }
        switch (item.getItemId()) {
            case R.id.homeScreen:
                Intent intent = new Intent(getApplicationContext(),
                        MainActivity.class);
                startActivity(intent);
        }

        if (item.getItemId() == R.id.action_default) {
            theme = Theme;
            Toast.makeText(this, "Theme Changed", Toast.LENGTH_SHORT).show();
        }

        if (item.getItemId() == R.id.action_red_theme) {
            theme = Theme1;
            Toast.makeText(this, "Theme Changed", Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId() == R.id.action_green_theme) {
            theme = Theme2;
            Toast.makeText(this, "Theme Changed", Toast.LENGTH_SHORT).show();
        }
        changeTheme(theme);

        return true;
    }

    private void changeTheme(int c) {
        View view = this.getWindow().getDecorView();
        switch (c) {
            case 0:
                view.setBackgroundResource(R.color.Default);
                break;
            case 1:
                view.setBackgroundResource(R.color.red);
                break;
            case 2:
                view.setBackgroundResource(R.color.green);
                break;
        }
    }
}
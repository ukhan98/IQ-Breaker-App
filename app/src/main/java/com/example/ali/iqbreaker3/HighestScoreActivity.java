package com.example.ali.iqbreaker3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HighestScoreActivity extends AppCompatActivity {
    int theme;
    private static final int DefaultValue = 0;
    private static final int Theme = 0;
    private static final int Theme1 = 1;
    private static final int Theme2 = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highest_score);

        TextView txtScore = findViewById(R.id.textScore);
        TextView txtHighScore = findViewById(R.id.textHighScore);

        Button btn_back = findViewById(R.id.btn_back);
        // receive the score from last activity by Intent
        Intent intent = getIntent();
        int score = intent.getIntExtra("score", DefaultValue);
        // display current score
        txtScore.setText("Your score: " + score);
        // use Shared preferences to save the best score
        SharedPreferences mypref = getPreferences(MODE_PRIVATE);

        int highscore = mypref.getInt("highscore", 0);
        if (highscore >= score)
            txtHighScore.setText("High score: " + highscore);
        else
        {
            txtHighScore.setText("New highscore: "+score);
            SharedPreferences.Editor editor = mypref.edit();
            editor.putInt("highscore", score);
            editor.commit();
        }
        ;
        Intent intent1 = getIntent();
        theme = intent1.getIntExtra("theme", Theme);
        changeTheme(theme);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HighestScoreActivity.this, MainActivity.class);
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
                Intent intent = new Intent(HighestScoreActivity.this, UserPrefActivity.class);
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
            Toast.makeText(this, R.string.theme_changed, Toast.LENGTH_SHORT).show();
        }

        if (item.getItemId() == R.id.action_red_theme) {
            theme = Theme1;
            Toast.makeText(this, R.string.theme_changed, Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId() == R.id.action_green_theme) {
            theme = Theme2;
            Toast.makeText(this, R.string.theme_changed, Toast.LENGTH_SHORT).show();
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
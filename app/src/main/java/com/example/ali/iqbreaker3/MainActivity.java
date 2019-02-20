package com.example.ali.iqbreaker3;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button btn_sign_up,btn_login,btn_stats,btn_facts;
    int theme;
    private static final int ChangeDifficultyRequestCode = 1;
    private static final int Theme = 0;
    private static final int Theme1 = 1;
    private static final int Theme2 = 2;
    private static final String MY_CHANNEL_ID = "myChannelId";
    private static final String MY_CHANNEL_NAME = "myChannelName";
    private static final int MY_NOTIFICATION = 1;
    public static final int NOTIFICATION_TAPPED = 1;
    public static final int NOTIFICATION_DELETED = 2;
    // USE CONSTANTS FOR THEME CHANGES / COLOR
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_sign_up = findViewById(R.id.btn_registration);
        btn_login = findViewById(R.id.btn_login);
        btn_stats = findViewById(R.id.btn_stats);
        btn_facts = findViewById(R.id.btn_facts);


        Intent intent = getIntent();
        theme = intent.getIntExtra("theme", Theme);
        changeTheme(theme);

        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),
                        RegistrationActivity.class);

                intent.putExtra("theme", theme); // pass the current score to the second screen
                startActivity(intent);

            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                        LoginActivity.class);
                intent.putExtra("theme", theme); // pass the current score to the second screen
                startActivity(intent);
            }
        });

        btn_stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                        HighestScoreActivity.class);
                intent.putExtra("theme", theme); // pass the current score to the second screen

                startActivity(intent);
            }
        });

        btn_facts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                        FactsActivity.class);
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
                Intent intent = new Intent(MainActivity.this, UserPrefActivity.class);
                startActivityForResult(intent, ChangeDifficultyRequestCode);
        }
        switch (item.getItemId()) {
            case R.id.homeScreen:
                Intent intent = new Intent(getApplicationContext(),
                        MainActivity.class);
                startActivity(intent);
        }

        if(item.getItemId() == R.id.action_default){
            theme = Theme;
            Toast.makeText(this,R.string.theme_changed, Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId() == R.id.notif){
            displayNotification();
            Toast.makeText(this,R.string.notifications_allowed, Toast.LENGTH_SHORT).show();
        }

        if(item.getItemId() == R.id.action_red_theme) {
            theme = Theme1;
            Toast.makeText(this, R.string.theme_changed, Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId() == R.id.action_green_theme) {
            theme = Theme2;
            Toast.makeText(this,R.string.theme_changed, Toast.LENGTH_SHORT).show();
        }
        changeTheme(theme);


        return true;
    }
    private void changeTheme(int c){
        View view = this.getWindow().getDecorView();
        switch (c){
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

    private void displayNotification() {

        // create an explicit Intent to lauch SecondActivity
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

        intent.putExtra("data", "Tapped!");

        // Create a PendingIntent for Second Activity
        PendingIntent contentIntent = PendingIntent.getActivity(
                getApplicationContext(),
                MainActivity.NOTIFICATION_TAPPED,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent intent2 = new Intent(getApplicationContext(), LoginActivity.class);
        intent2.putExtra("data", "Dismissed!");

        // Create a PendingIntent for Second Activity
        PendingIntent deleteIntent = PendingIntent.getActivity(
                getApplicationContext(),
                MainActivity.NOTIFICATION_DELETED,
                intent2, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder builder =
                new Notification.Builder(getApplicationContext());

        // Use Notification Builder to build the Notification
        builder.setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.icon)
                .setTicker("Notification")
                .setContentTitle("Login to your account and")
                .setContentText("Improve Your Quiz Mark")
                .setContentIntent(contentIntent) // what to do when user taps
                .setDeleteIntent(deleteIntent)
                .setAutoCancel(true); // cancel the notification once user taps

        // get the Notification Manager
        NotificationManager manager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // check to see if we're in Oreo. If so make a channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel =
                    new NotificationChannel(MY_CHANNEL_ID, MY_CHANNEL_NAME,
                            NotificationManager.IMPORTANCE_HIGH);

            // put some settings on the Channel
            channel.enableLights(true);
            channel.enableVibration(true);

            // create the notification channel
            manager.createNotificationChannel(channel);

            builder.setChannelId(MY_CHANNEL_ID);
        } else {
            // we're in Nugget or below
            // set some things directly on the builder

            builder.setLights(Color.BLUE, 1000, 1000);

            builder.setVibrate(new long[] {500, 1000, 500, 1000});
        }

        Notification notify = builder.build();

        // Call notify off of the manager
        manager.notify(MY_NOTIFICATION, notify);

    }
}

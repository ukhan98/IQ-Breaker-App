package com.example.ali.iqbreaker3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {
    int theme;
    private static final int Theme = 0;
    private static final int Theme1 = 1;
    private static final int Theme2 = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btn_login = findViewById(R.id.btn_login);

        Intent intent = getIntent();
        theme = intent.getIntExtra("theme", Theme);
        changeTheme(theme);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
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
                Intent intent = new Intent(LoginActivity.this, UserPrefActivity.class);
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

    public void login() {


        UserDb myDb = new UserDb(this);
        SQLiteDatabase db = myDb.getReadableDatabase();

        EditText edtUserName = findViewById(R.id.edt_UserName);
        EditText edtPassword = findViewById(R.id.edt_Password);

        String userName = edtUserName.getText().toString();
        if (userName.trim().isEmpty()) {
            edtUserName.setError(getString(R.string.cannot_be_empty));
            return;
        }
        String password = edtPassword.getText().toString();
        if (password.trim().isEmpty()) {
            edtPassword.setError(getString(R.string.cannot_be_empty));
            return;
        }

        String[] columns = {"uname", "pass"};
        String[] cValues = {edtUserName.getText().toString(), edtPassword.getText().toString()};

        Cursor cursor = db.query("Student", columns, "uname = ? AND pass = ?", cValues, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                Intent intent = new Intent(this, ProfileActivity.class);
                intent.putExtra("Username", edtUserName.getText().toString());
                intent.putExtra("theme", theme); // pass the current score to the second screen
                startActivity(intent);

            } else {
                Toast.makeText(this, R.string.wrong_login_details, Toast.LENGTH_LONG).show();
            }
        }
    }

}

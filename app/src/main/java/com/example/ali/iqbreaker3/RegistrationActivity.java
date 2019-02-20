package com.example.ali.iqbreaker3;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    Button createAccount;
    private static final int Theme = 0;
    private static final int Theme1 = 1;
    private static final int Theme2 = 2;
    int theme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Button btnCreateAccount = findViewById(R.id.createAccount);
        Intent intent = getIntent();
        theme = intent.getIntExtra("theme", Theme);
        changeTheme(theme);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

    }

    public void register() {

        EditText edtFullName = findViewById(R.id.edt_FullName);
        EditText edtUserName = findViewById(R.id.edt_UserName);
        EditText edtPassword = findViewById(R.id.edt_Password);


        String name = edtFullName.getText().toString();
        String userName = edtUserName.getText().toString();
        String pass = edtPassword.getText().toString();

        if (name.trim().isEmpty() && userName.trim().isEmpty() && pass.trim().isEmpty()) {
            edtUserName.setError(getString(R.string.cannot_be_empty));
            edtFullName.setError(getString(R.string.cannot_be_empty));
            edtPassword.setError(getString(R.string.cannot_be_empty));
            return;
        } else if (name.trim().isEmpty()) {
            edtFullName.setError(getString(R.string.cannot_be_empty));
            return;
        }
        if (userName.trim().isEmpty()) {
            edtUserName.setError(getString(R.string.cannot_be_empty));
            return;
        }
        if (pass.trim().isEmpty()) {
            edtPassword.setError(getString(R.string.cannot_be_empty));
            return;
        }


        UserDb myDb = new UserDb(this);
        SQLiteDatabase db = myDb.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("uname", userName);
        values.put("pass", pass);

        db.insert("Student", null, values);
        Toast.makeText(this, R.string.sucessfully_registered, Toast.LENGTH_LONG).show();


        Intent intent = new Intent(getApplicationContext(),
                MainActivity.class);
        intent.putExtra("theme", theme); // pass the current score to the second screen
        startActivity(intent);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionSettings:
                Intent intent = new Intent(RegistrationActivity.this, UserPrefActivity.class);
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

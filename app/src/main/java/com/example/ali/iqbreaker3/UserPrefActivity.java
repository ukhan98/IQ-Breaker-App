package com.example.ali.iqbreaker3;

import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.os.Bundle;
import android.preference.PreferenceActivity;

public class UserPrefActivity extends PreferenceActivity {

    private CheckBoxPreference checkBoxVerification;
    public static boolean isChecked;

    public static boolean isChecked() {
        return isChecked;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.user_pref);

        checkBoxVerification = (CheckBoxPreference) findPreference("pref_sync");

        checkBoxVerification.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newVal) {
                isChecked = (Boolean) newVal;
                checkBoxVerification.setChecked(isChecked());
                return isChecked();

            }

        });


    }
}

package com.samsung.bookm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.samsung.bookm.MainActivity;
import com.samsung.bookm.R;

import java.util.Locale;

public class LanguageActivity extends AppCompatActivity {
    RadioGroup mChangeLanguageGroup;
    RadioButton mRadioButtonVN;
    RadioButton mRadioButtonEn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        mRadioButtonVN = findViewById(R.id.Rbtn_Vietnamese);
        mRadioButtonEn = findViewById(R.id.Rbtn_English);
        mChangeLanguageGroup = findViewById(R.id.radio_gr_change_language);
        mChangeLanguageGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                String lang = "en";
                if(i == R.id.Rbtn_Vietnamese) {
                    lang = "vi";
                }
                if (getResources().getConfiguration().locale.getLanguage().equals(lang)) {
                    return;
                }

                Locale myLocale = new Locale(lang);
                Locale.setDefault(myLocale);
                android.content.res.Configuration config = new android.content.res.Configuration();
                config.locale = myLocale;
                LanguageActivity.this.getBaseContext().getResources().updateConfiguration(config,
                        LanguageActivity.this.getBaseContext().getResources().getDisplayMetrics());
                Intent intent = new Intent(LanguageActivity.this, MainActivity.class);
                startActivity(intent);

                finish();
            }
        });
        if (getResources().getConfiguration().locale.getLanguage().equals("vi")) {
            mRadioButtonVN.setChecked(true);
        } else {
            mRadioButtonEn.setChecked(true);
        }
    }
}


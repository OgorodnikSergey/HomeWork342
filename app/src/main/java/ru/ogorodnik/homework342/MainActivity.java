package ru.ogorodnik.homework342;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Spinner langSpinner;
    private Spinner marginSpinner;
    private Button buttonOK;
    Locale myLocale;
    String lang;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_LANG = "Lang";
    SharedPreferences mSettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_main);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        initViews();
        langUpload();  // подгружаем положение спиннера для Языка

        // Нажатие кнопки OK ---------------------------------------------------------------------
        // Оставил Toast для красоты
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this
                        ,langSpinner.getSelectedItem().toString()
                                + " "
                                + marginSpinner.getSelectedItem().toString()
                                + " "
                        ,Toast.LENGTH_LONG)
                        .show();
                lang = langSpinner.getSelectedItem().toString();
                switch (lang) {
                    case "EN English":
                    case "EN Английский":
                        lang = "en";
                        changeLang(lang);
                        langSave();
                        break;
                    case "RU Russian":
                    case "RU Русский":
                        lang = "ru";
                        changeLang(lang);
                        langSave();
                        break;
                    default:
                        break;
                }
                String margin = marginSpinner.getSelectedItem().toString();
                switch (margin) {
                    case "Margin 1":
                    case "Отступ 1":
                        Utils.changeToTheme(MainActivity.this, Utils.THEME_MARGIN1);
                        break;
                    case "Margin 3":
                    case "Отступ 3":
                        Utils.changeToTheme(MainActivity.this, Utils.THEME_MARGIN3);
                        break;
                    case "Margin 10":
                    case "Отступ 10":
                        Utils.changeToTheme(MainActivity.this, Utils.THEME_MARGIN10);
                        break;
                    default:
                        break;
                }
            }
        });

    }
    private void initViews() {
        langSpinner = findViewById(R.id.langSpinner);
        marginSpinner = findViewById(R.id.marginSpinner);
        buttonOK = findViewById(R.id.buttonOK);
        initSpinnerLanguage();
        initSpinnerMargin();
    }

    private void initSpinnerLanguage() {
        ArrayAdapter<CharSequence> adapterLanguage = ArrayAdapter.createFromResource(this, R.array.language, android.R.layout.simple_spinner_item);
        adapterLanguage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        langSpinner.setAdapter(adapterLanguage);
    }

    private void initSpinnerMargin() {
        ArrayAdapter<CharSequence> adapterMargin = ArrayAdapter.createFromResource(this, R.array.margin, android.R.layout.simple_spinner_item);
        adapterMargin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        marginSpinner.setAdapter(adapterMargin);
    }

    public void changeLang(String lang)
    {
        if (lang.equalsIgnoreCase(""))
            return;
        myLocale = new Locale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        recreate();
    }

    public void langSave() {
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putInt(APP_PREFERENCES_LANG, langSpinner.getSelectedItemPosition());
        editor.apply();
    }

    public void langUpload(){
        if (mSettings.contains(APP_PREFERENCES_LANG)) {
            langSpinner.setSelection(mSettings.getInt(APP_PREFERENCES_LANG, 0));
        }
    }
}

// Мои черновики 8)
// selectedLang = langSpinner.getSelectedItemPosition();
// marginSpinner.setSelection(0);
// langSpinner.setSelection(selectedLang);
// int language =mSettings.getInt(APP_PREFERENCES_LANG, 1);
//int language = langSpinner.getSelectedItemPosition();
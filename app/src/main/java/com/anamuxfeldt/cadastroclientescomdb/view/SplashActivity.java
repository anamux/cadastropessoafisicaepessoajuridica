package com.anamuxfeldt.cadastroclientescomdb.view;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.anamuxfeldt.cadastroclientescomdb.database.AppDataBase;
import com.anamuxfeldt.cadastroclientescomdb.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {
    private ActivitySplashBinding binding;
    public static final int TIME_OUT_SPLASH = 3000;
    public static final String PREF_APP = "app_cliente_vip_pref";
    boolean isLembrarSenha = false;
    private SharedPreferences preferences;
    AppDataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        salvarSharedPreferences();
        restaurarSharedPreferences();
        dataBase = new AppDataBase(getApplicationContext());
        trocarTela();

    }

    private void trocarTela() {
        new Handler().postDelayed(new Runnable() {
            Intent intent;

            @Override
            
            public void run() {
                if (!isLembrarSenha) {
                    intent = new Intent(SplashActivity.this, Login.class);

                                   } else {
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                }
                startActivity(intent);
                finish();
            }
        },TIME_OUT_SPLASH);
    }

    private void salvarSharedPreferences() {
        preferences = getSharedPreferences(PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();


    }

    private void restaurarSharedPreferences() {
        preferences = getSharedPreferences(PREF_APP, MODE_PRIVATE);
        isLembrarSenha = preferences.getBoolean("loginAutomatico", false);

            }
}
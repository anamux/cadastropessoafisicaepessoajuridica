package com.anamuxfeldt.cadastroclientescomdb.view;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.anamuxfeldt.cadastroclientescomdb.controller.ClienteController;
import com.anamuxfeldt.cadastroclientescomdb.database.AppDataBase;
import com.anamuxfeldt.cadastroclientescomdb.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {
    private ActivitySplashBinding binding;
    public static final int TIME_OUT_SPLASH = 3000;
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
        trocarTela();

        dataBase = new AppDataBase(getApplicationContext());
    }

    private void trocarTela() {
        new Handler().postDelayed(new Runnable() {
            Intent intent;

            @Override
            
            public void run() {
                restaurarSharedPreferences();
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
        preferences = getSharedPreferences(ClienteController.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();

        dados.putBoolean("loginAutomático",false);
        dados.apply();

    }

    private void restaurarSharedPreferences() {
        preferences = getSharedPreferences(ClienteController.PREF_APP, MODE_PRIVATE);
        isLembrarSenha = preferences.getBoolean("Login automático", isLembrarSenha);
        Log.d(TAG, "restaurarSharedPreferences: Recuperar dados....");
        Log.d(TAG, "restaurarSharedPreferences: "+ preferences.getBoolean(String.valueOf(isLembrarSenha), false));
            }
}
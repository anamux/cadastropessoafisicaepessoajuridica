package com.anamuxfeldt.cadastroclientescomdb.view;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.anamuxfeldt.cadastroclientescomdb.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {
    private ActivitySplashBinding binding;
    public static final int TIME_OUT_SPLASH = 1000;
    public static final String PREF_APP = "app_cliente_vip_pref";
    boolean isLembrarSenha = false;
    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        restaurarSharedPreferences();
        salvarSharedPreferences();
        trocarTela();

    }

    private void trocarTela() {
        new Handler().postDelayed(new Runnable() {


            @Override
            
            public void run() {
                Intent intent;
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
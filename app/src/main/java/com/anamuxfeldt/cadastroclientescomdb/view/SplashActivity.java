package com.anamuxfeldt.cadastroclientescomdb.view;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.anamuxfeldt.cadastroclientescomdb.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {
    private ActivitySplashBinding binding;
    public static final int TIME_OUT_SPLASH = 3000;
    boolean isLembrarSenha = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        salvarSharedPreferences();
        trocarTela();

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



    }

    private void restaurarSharedPreferences() {

            }
}
package com.anamuxfeldt.cadastropessoafisicaepessoajuridica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.anamuxfeldt.cadastropessoafisicaepessoajuridica.databinding.ActivityLoginBinding;

public class Login extends AppCompatActivity {
private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnSejaVip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, CadastroNovoCliente.class);
                        startActivity(intent);
                        finish();
            }
        });

        binding.btnAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
        });

        binding.txtPoliticaETermos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
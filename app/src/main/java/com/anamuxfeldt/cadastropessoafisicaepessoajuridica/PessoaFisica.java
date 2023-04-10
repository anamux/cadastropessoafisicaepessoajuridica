package com.anamuxfeldt.cadastropessoafisicaepessoajuridica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.anamuxfeldt.cadastropessoafisicaepessoajuridica.databinding.ActivityCadastroPessoaFisicaBinding;

public class PessoaFisica extends AppCompatActivity {
private ActivityCadastroPessoaFisicaBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroPessoaFisicaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnSalvarEContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PessoaFisica.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        binding.btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PessoaFisica.this, Login.class);
                startActivity(intent);
                finish();

            }
        });
    }
}
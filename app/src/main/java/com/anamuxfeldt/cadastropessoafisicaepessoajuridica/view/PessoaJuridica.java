package com.anamuxfeldt.cadastropessoafisicaepessoajuridica.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.anamuxfeldt.cadastropessoafisicaepessoajuridica.databinding.ActivityCadastroClientePjBinding;

public class PessoaJuridica extends AppCompatActivity {
private ActivityCadastroClientePjBinding binding;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroClientePjBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.editCnpj.getText().toString();
        binding.editRazaoSocial.getText().toString();
        binding.editAbertura.getText().toString();
        binding.btnSalvarEContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PessoaJuridica.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        binding.btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PessoaJuridica.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

        binding.btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PessoaJuridica.this, "Obrigado pelo acesso...", Toast.LENGTH_SHORT).show();
                finish();

            }
        });
    }
}
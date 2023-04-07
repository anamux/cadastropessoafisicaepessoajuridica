package com.anamuxfeldt.cadastropessoafisicaepessoajuridica;

import androidx.appcompat.app.AppCompatActivity;

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
    }
}
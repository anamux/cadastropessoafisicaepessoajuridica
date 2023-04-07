package com.anamuxfeldt.cadastropessoafisicaepessoajuridica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.anamuxfeldt.cadastropessoafisicaepessoajuridica.databinding.ActivityCadastroNovoClienteBinding;

public class CadastroNovoCliente extends AppCompatActivity {
    private ActivityCadastroNovoClienteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroNovoClienteBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnSalvarEContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( binding.ckPessoaFisica.isChecked()){
                    ICadastroCliente cadastroCliente = new CadastrarClientePF();

                    Intent intent= cadastroCliente.cadastrar(CadastroNovoCliente.this);
                    startActivity(intent);

                }else{
                    ICadastroCliente cadastroCliente = new CadastrarClientePJ();

                    Intent intent = cadastroCliente.cadastrar(CadastroNovoCliente.this);
                    startActivity(intent);
                }
            }
        });

        binding.btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CadastroNovoCliente.this, Login.class);
                startActivity(intent);
            }
        });
    }
}
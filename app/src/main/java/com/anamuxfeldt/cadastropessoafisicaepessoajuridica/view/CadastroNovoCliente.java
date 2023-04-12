package com.anamuxfeldt.cadastropessoafisicaepessoajuridica.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.anamuxfeldt.cadastropessoafisicaepessoajuridica.model.CadastrarClientePF;
import com.anamuxfeldt.cadastropessoafisicaepessoajuridica.model.CadastrarClientePJ;
import com.anamuxfeldt.cadastropessoafisicaepessoajuridica.model.ICadastroCliente;
import com.anamuxfeldt.cadastropessoafisicaepessoajuridica.databinding.ActivityCadastroNovoClienteBinding;

public class CadastroNovoCliente extends AppCompatActivity {
    private ActivityCadastroNovoClienteBinding binding;
    boolean isDadosOk;
    ICadastroCliente cadastroCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroNovoClienteBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnSalvarEContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validarDados()) {
                    if (!validarSenha()) {
                        Toast.makeText(getApplicationContext(), "As senhas digitadas não conferem...",
                                Toast.LENGTH_LONG).show();
                    } else {
                        if (binding.ckPessoaFisica.isChecked()) {
                            cadastroCliente = new CadastrarClientePF();
                        } else if (validarSenha()) {
                            cadastroCliente = new CadastrarClientePJ();
                        }
                        Intent intent = cadastroCliente.cadastrar(CadastroNovoCliente.this);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });

        binding.btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CadastroNovoCliente.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private boolean validarDados() {
        boolean retorno = true;
        if (TextUtils.isEmpty(binding.editPrimeiroNome.getText().toString())) {
            binding.editPrimeiroNome.setError("*");
            binding.editPrimeiroNome.requestFocus();
            retorno = false;
        }
        if (TextUtils.isEmpty(binding.editSobrenome.getText().toString())) {
            binding.editSobrenome.setError("*");
            binding.editSobrenome.requestFocus();
            retorno = false;
        }
        if (TextUtils.isEmpty((binding.editSenha.getText().toString()))) {
            binding.editSenha.setError("*");
            binding.editSenha.requestFocus();
            retorno = false;
        }
        if (TextUtils.isEmpty((binding.editConfirmaSenha.getText().toString()))) {
            binding.editConfirmaSenha.setError("*");
            binding.editConfirmaSenha.requestFocus();
            retorno = false;
        }
        if (!binding.ckPoliticaEPrivacidade.isChecked()) {
            binding.ckPoliticaEPrivacidade.setError("Obrigatório");
            retorno = false;
        }
        return retorno;
    }

    private boolean validarSenha() {
        boolean retorno = false;
        int senhaA, senhaB;

        senhaA = Integer.parseInt(binding.editSenha.getText().toString());
        senhaB = Integer.parseInt(binding.editConfirmaSenha.getText().toString());

        retorno = (senhaA == senhaB);

        return retorno;
    }

}
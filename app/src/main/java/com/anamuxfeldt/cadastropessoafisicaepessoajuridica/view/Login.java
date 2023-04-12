package com.anamuxfeldt.cadastropessoafisicaepessoajuridica.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.anamuxfeldt.cadastropessoafisicaepessoajuridica.controller.ClienteController;
import com.anamuxfeldt.cadastropessoafisicaepessoajuridica.databinding.ActivityLoginBinding;
import com.anamuxfeldt.cadastropessoafisicaepessoajuridica.model.Cliente;

public class Login extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private SharedPreferences preferences;
    boolean isLembrarSenha;
    Cliente cliente;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        cliente = ClienteController.getClienteFake();
        salvarSharedPreferences();


        binding.txtPoliticaETermos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Política e Termos de uso",
                        Toast.LENGTH_LONG).show();
            }

        });
        binding.txtRecuperarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Carregando recuperação de senha",
                        Toast.LENGTH_LONG).show();
            }

        });
        binding.btnSejaVip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isDadosOk = validarFormulario();
                if (isDadosOk) {
                    if (validarDadosUsuario()) {
                        Intent intent = new Intent(Login.this, CadastroNovoCliente.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Verifique os dados...",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
        binding.btnAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isDadosOk = validarFormulario();
                if (isDadosOk) {
                    if (validarDadosUsuario()) {
                        salvarSharedPreferences();
                        Intent intent = new Intent(Login.this, CadastroNovoCliente.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Verifique os dados...",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private boolean validarDadosUsuario() {
        return ClienteController.validarDadosDoCliente(cliente,
                binding.editEmail.getText().toString(),
                binding.editSenha.getText().toString());
    }

    private boolean validarFormulario() {
        boolean isDadosOk = true;
        salvarSharedPreferences();

        if (TextUtils.isEmpty(binding.editEmail.getText().toString())) {
            binding.editEmail.setError("*");
            binding.editEmail.requestFocus();
            isDadosOk = false;
        }
        if (TextUtils.isEmpty((binding.editSenha.getText().toString()))) {
            binding.editSenha.setError("*");
            binding.editSenha.requestFocus();
            isDadosOk = false;

        }

        if (isDadosOk) {
            if (!validarSenha()) {
                binding.editSenha.setError(("*"));
            }
        }
        return isDadosOk;
    }

    private boolean validarSenha() {
        return true;
    }

    public void lembrarSenha(View view) {
        isLembrarSenha = binding.ckLembrar.isChecked();
        salvarSharedPreferences();
    }
    private void salvarSharedPreferences() {
        preferences = getSharedPreferences(ClienteController.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();

        dados.putBoolean("Login automático", isLembrarSenha);
        dados.putString("Login automático", binding.editEmail.getText().toString());
        dados.apply();

    }

    private void restaurarSharedPreferences() {
        preferences = getSharedPreferences(ClienteController.PREF_APP, MODE_PRIVATE);
        isLembrarSenha = preferences.getBoolean("Login automático", false);
        int teste = 0;

    }
}

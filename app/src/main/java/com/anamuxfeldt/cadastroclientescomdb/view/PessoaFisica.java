package com.anamuxfeldt.cadastroclientescomdb.view;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.anamuxfeldt.cadastroclientescomdb.controller.ClienteController;
import com.anamuxfeldt.cadastroclientescomdb.databinding.ActivityCadastroPessoaFisicaBinding;
import com.anamuxfeldt.cadastroclientescomdb.databinding.ActivityCadastroPessoaFisicaCardBinding;
import com.anamuxfeldt.cadastroclientescomdb.model.Cliente;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class PessoaFisica extends AppCompatActivity {
    private ActivityCadastroPessoaFisicaCardBinding binding;
    private SharedPreferences preferences;
    ClienteController controller;
    Cliente cliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroPessoaFisicaCardBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        cliente = new Cliente();
        controller = new ClienteController(this);
        binding.btnSalvarEContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validarFormulario()) {
                    salvarSharedPreferences();
                    controller.incluir(cliente);
                    Intent intent = new Intent(PessoaFisica.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
        });

        binding.btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PessoaFisica.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

        binding.btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(PessoaFisica.this)
                        .setTitle("Cancelar")
                        .setMessage("Tem certeza que deseja cancelar o preenchimento?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(PessoaFisica.this, "Formulário limpo", Toast.LENGTH_SHORT).show();
                                dialogInterface.dismiss();
                            }
                        })
                        .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(PessoaFisica.this, "Continuar preenchendo o formulário", Toast.LENGTH_SHORT).show();
                                dialogInterface.dismiss();
                            }
                        });
                builder.create();
                builder.show();

            }
        });
    }

    private boolean validarFormulario() {
        boolean isDadosOk = true;

        if (TextUtils.isEmpty(binding.editEmail.getText().toString())) {
            binding.editEmail.setError("*");
            binding.editEmail.requestFocus();
            isDadosOk = false;
        }
        if (TextUtils.isEmpty((binding.editCpf.getText().toString()))) {
            binding.editCpf.setError("*");
            binding.editCpf.requestFocus();
            isDadosOk = false;
        }
        if (TextUtils.isEmpty((binding.editDataNascimento.getText().toString()))) {
            binding.editDataNascimento.setError("*");
            binding.editDataNascimento.requestFocus();
            isDadosOk = false;
        }
        if (TextUtils.isEmpty((binding.editSenha.getText().toString()))) {
            binding.editSenha.setError("*");
            binding.editSenha.requestFocus();
            isDadosOk = false;
        }
        if (TextUtils.isEmpty((binding.editConfirmaSenha.getText().toString()))) {
            binding.editConfirmaSenha.setError("*");
            binding.editConfirmaSenha.requestFocus();
            isDadosOk = false;
        }
        if (!validarSenha()) {
            Toast.makeText(getApplicationContext(), "As senhas digitadas não conferem...",
                    Toast.LENGTH_LONG).show();
        }

        return isDadosOk;
    }

    private boolean validarSenha() {
        boolean retorno = false;
        int senhaA, senhaB;

        senhaA = Integer.parseInt(binding.editSenha.getText().toString());
        senhaB = Integer.parseInt(binding.editConfirmaSenha.getText().toString());

        retorno = (senhaA == senhaB);

        return retorno;
    }

    private void salvarSharedPreferences() {

        preferences = getSharedPreferences(SplashActivity.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();

        dados.putString("email", binding.editEmail.getText().toString());
        dados.putString("cpfCliente", binding.editCpf.getText().toString());
        dados.putString("dataNascimento", binding.editDataNascimento.getText().toString());
        dados.putString("senha", binding.editSenha.getText().toString());
        dados.apply();

    }


}
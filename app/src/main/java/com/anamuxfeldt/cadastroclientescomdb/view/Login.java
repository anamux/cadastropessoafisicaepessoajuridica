package com.anamuxfeldt.cadastroclientescomdb.view;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.anamuxfeldt.cadastroclientescomdb.controller.ClienteController;;
import com.anamuxfeldt.cadastroclientescomdb.databinding.ActivityLoginBinding;
import com.anamuxfeldt.cadastroclientescomdb.model.Cliente;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class Login extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private SharedPreferences preferences;
    boolean isLembrarSenha;
    Cliente cliente;
    ClienteController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        cliente = new Cliente();
        controller = new ClienteController(getApplicationContext());



        binding.txtPoliticaETermos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(Login.this)
                        .setTitle("Política de Privacidade e Termos de uso")
                        .setMessage("Texto sobre Política de Privacidade e Termos de uso")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(Login.this, "Positive Button Clicked", Toast.LENGTH_SHORT).show();
                                dialogInterface.dismiss();
                            }
                        })
                        .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(Login.this, "Negative Button Clicked", Toast.LENGTH_SHORT).show();
                                dialogInterface.dismiss();
                            }
                        });
                builder.create();
                builder.show();
            }

        });
        binding.txtRecuperarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(Login.this)
                        .setTitle("Recuperar senha")
                        .setMessage("Uma nova senha de acesso será enviada para o seu email.")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(Login.this, "Positive Button Clicked", Toast.LENGTH_SHORT).show();
                                dialogInterface.dismiss();
                            }
                        })
                        .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(Login.this, "Negative Button Clicked", Toast.LENGTH_SHORT).show();
                                dialogInterface.dismiss();
                            }
                        });
                builder.create();
                builder.show();
            }

        });
        binding.btnCadastrar.setOnClickListener(new View.OnClickListener() {
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
                boolean isDadosOk = validarFormulario();
                if (isDadosOk) {
                  //  restaurarSharedPreferences();
                    if (validarDadosUsuario()) {
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Verifique os dados...",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private boolean validarDadosUsuario() {
        return true;
    }

    private boolean validarFormulario() {
        boolean isDadosOk = true;
     //   salvarSharedPreferences();

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
                binding.editSenha.requestFocus();
            }
        }
        return isDadosOk;
    }

    private boolean validarSenha() {
        return true;
    }

    public void lembrarSenha(View view) {
        isLembrarSenha = binding.ckLembrar.isChecked();
       // salvarSharedPreferences();
    }

}

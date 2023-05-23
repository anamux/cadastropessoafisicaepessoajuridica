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


import com.anamuxfeldt.cadastroclientescomdb.controller.ClientePJController;
import com.anamuxfeldt.cadastroclientescomdb.databinding.ActivityCadastroClientePjCardBinding;
import com.anamuxfeldt.cadastroclientescomdb.model.Cliente;
import com.anamuxfeldt.cadastroclientescomdb.model.ClientePJ;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class PessoaJuridica extends AppCompatActivity {
    private ActivityCadastroClientePjCardBinding binding;
    Cliente cliente;
    ClientePJ clientePJ;
    ClientePJController controller;
    private SharedPreferences preferences;
    private boolean isSimplesNacional;
    private boolean isMei;
    int ultimoIDPF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroClientePjCardBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        clientePJ = new ClientePJ();
        cliente = new Cliente();
        controller = new ClientePJController(this);

        restaurarSharedPreferences();
        binding.btnSalvarEContinuar.setOnClickListener(new View.OnClickListener() {
            Intent intent;

            @Override
            public void onClick(View view) {
                boolean isDadosOk = validarFormulario();
                intent = new Intent();

                if (isDadosOk) {
                    clientePJ.setClientePFID(ultimoIDPF);
                    clientePJ.setCnpj(binding.editCnpj.getText().toString());
                    clientePJ.setRazaoSocial(binding.editRazaoSocial.getText().toString());
                    clientePJ.setDataAbertura(binding.editAbertura.getText().toString());
                    clientePJ.setSimplesNacional(isSimplesNacional);
                    clientePJ.setMei(isMei);


                    controller.incluir(clientePJ);
                    ultimoIDPF = controller.getUltimoID();
                    if (validarFormulario()) {
                        salvarSharedPreferences();
                        intent = new Intent(PessoaJuridica.this, MainActivity.class);
                    } else {
                        Toast.makeText(getApplicationContext(), "Verifique os dados...",
                                Toast.LENGTH_SHORT).show();
                    }
                    startActivity(intent);
                    finish();
                }
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
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(PessoaJuridica.this)
                        .setTitle("Cancelar")
                        .setMessage("Tem certeza que deseja cancelar o preenchimento?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(PessoaJuridica.this, "Formulário limpo", Toast.LENGTH_SHORT).show();
                                dialogInterface.dismiss();
                            }
                        })
                        .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(PessoaJuridica.this, "Continuar preenchendo o formulário", Toast.LENGTH_SHORT).show();
                                dialogInterface.dismiss();
                            }
                        });
                builder.create();
                builder.show();

            }
        });
    }

    private void restaurarSharedPreferences() {
        preferences = getSharedPreferences(SplashActivity.PREF_APP, MODE_PRIVATE);
        ultimoIDPF = preferences.getInt("ultimoIDClientePessoaPF", 1);
    }

    private boolean validarFormulario() {
        boolean isDadosOk = true;
        salvarSharedPreferences();

        String cnpj = binding.editCnpj.getText().toString();
        if (TextUtils.isEmpty(cnpj)) {
            binding.editCnpj.setError("*");
            binding.editCnpj.requestFocus();
            isDadosOk = false;
        }
        if (!ClientePJ.isCnpj(cnpj)){
            binding.editCnpj.setError("*");
            binding.editCnpj.requestFocus();
            isDadosOk = false;
            Toast.makeText(this, "CNPJ inválido, tente novamente", Toast.LENGTH_LONG).show();
        }else {
            binding.editCnpj.setText(ClientePJ.mascaraCnpj(cnpj));
        }
        if (TextUtils.isEmpty((binding.editRazaoSocial.getText().toString()))) {
            binding.editRazaoSocial.setError("*");
            binding.editRazaoSocial.requestFocus();
            isDadosOk = false;
        }
        if (TextUtils.isEmpty((binding.editAbertura.getText().toString()))) {
            binding.editAbertura.setError("*");
            binding.editAbertura.requestFocus();
            isDadosOk = false;
        }

        return isDadosOk;
    }

    private void salvarSharedPreferences() {
        preferences = getSharedPreferences(SplashActivity.PREF_APP, MODE_PRIVATE);
        Log.d(TAG, "salvarSharedPreferences: Pasta criada");
        SharedPreferences.Editor dados = preferences.edit();

        dados.putString("cnpj", binding.editCnpj.getText().toString());
        dados.putString("razãoSocial", binding.editRazaoSocial.getText().toString());
        dados.putString("dataDeAbertura", binding.editAbertura.getText().toString());
        dados.putBoolean("simplesNacional", isSimplesNacional);
        dados.putBoolean("mei", isMei);
        dados.putInt("ultimoClientePF", ultimoIDPF);
        dados.apply();

    }

    public void simplesNacional(View view) {
        isSimplesNacional = binding.ckSimplesNacional.isChecked();
        salvarSharedPreferences();
    }

    public void mei(View view) {
        isMei = binding.ckMei.isChecked();
        salvarSharedPreferences();
    }
}
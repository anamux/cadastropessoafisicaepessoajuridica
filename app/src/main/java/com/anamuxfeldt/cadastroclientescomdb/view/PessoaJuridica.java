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
import com.anamuxfeldt.cadastroclientescomdb.model.Cliente;
import com.anamuxfeldt.cadastropessoafisicaepessoajuridica.databinding.ActivityCadastroClientePjBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class PessoaJuridica extends AppCompatActivity {
private ActivityCadastroClientePjBinding binding;
    Cliente cliente;
    private SharedPreferences preferences;
    private boolean isSimplesNacional;
    private boolean isMei;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroClientePjBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnSalvarEContinuar.setOnClickListener(new View.OnClickListener() {
            Intent intent;
            @Override
            public void onClick(View view) {
                boolean isDadosOk = validarFormulario();
                intent = new Intent();

                if (isDadosOk) {
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
      private boolean validarFormulario() {
        boolean isDadosOk = true;
        salvarSharedPreferences();

        if (TextUtils.isEmpty(binding.editCnpj.getText().toString())) {
            binding.editCnpj.setError("*");
            binding.editCnpj.requestFocus();
            isDadosOk = false;
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
        preferences = getSharedPreferences(ClienteController.PREF_APP, MODE_PRIVATE);
        Log.d(TAG, "salvarSharedPreferences: Pasta criada");
        SharedPreferences.Editor dados = preferences.edit();

        dados.putString("cnpj", binding.editCnpj.getText().toString());
        dados.putString("razãoSocial", binding.editRazaoSocial.getText().toString());
        dados.putString("dataDeAbertura", binding.editAbertura.getText().toString());
        dados.putBoolean("simplesNacional", isSimplesNacional);
        dados.putBoolean("mei", isMei);
        dados.apply();

    }
    public void simplesNacional(View view){
       isSimplesNacional = binding.ckSimplesNacional.isChecked();
       salvarSharedPreferences();
    }
    public void mei(View view) {
        isMei = binding.ckMei.isChecked();
        salvarSharedPreferences();
    }
}
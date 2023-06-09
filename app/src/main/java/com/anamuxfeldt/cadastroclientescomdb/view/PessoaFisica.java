package com.anamuxfeldt.cadastroclientescomdb.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.anamuxfeldt.cadastroclientescomdb.controller.ClientePFController;
import com.anamuxfeldt.cadastroclientescomdb.databinding.ActivityCadastroPessoaFisicaCardBinding;
import com.anamuxfeldt.cadastroclientescomdb.model.Cliente;
import com.anamuxfeldt.cadastroclientescomdb.model.ClientePF;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class PessoaFisica extends AppCompatActivity {
    private ActivityCadastroPessoaFisicaCardBinding binding;
    private SharedPreferences preferences;
    ClientePFController controller;
    ClientePF clientePF;
    Cliente cliente;
    boolean isPessoaFisica;
    int clienteID, ultimoIDPF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroPessoaFisicaCardBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        clientePF = new ClientePF();
        cliente = new Cliente();

        controller = new ClientePFController(this);

        restaurarSharedPreferences();
        binding.btnSalvarEContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validarFormulario()) {
                    clientePF.setClienteID(clienteID);
                    clientePF.setCpf(binding.editCpf.getText().toString());
                    clientePF.setDataNascimento(binding.editDataNascimento.getText().toString());


                    controller.incluir(getApplicationContext(),clientePF);
                    ultimoIDPF = controller.getUltimoID(getApplicationContext());
                    salvarSharedPreferences();
                    Intent intent;
                    if (isPessoaFisica) {
                        intent = new Intent(PessoaFisica.this, MainActivity.class);
                    } else {
                        intent = new Intent(PessoaFisica.this, PessoaJuridica.class);
                    }
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

        String cpf = binding.editCpf.getText().toString();
        if (TextUtils.isEmpty(cpf)) {
            binding.editCpf.setError("*");
            binding.editCpf.requestFocus();
            isDadosOk = false;
        }
        if (!ClientePF.isCPF(cpf)){
            binding.editCpf.setError("*");
            binding.editCpf.requestFocus();
            isDadosOk = false;
            Toast.makeText(this, "CPF inválido, tente novamente", Toast.LENGTH_LONG).show();
        }else {
            binding.editCpf.setText(ClientePF.mascaraCPF(cpf));

        }
        if (TextUtils.isEmpty((binding.editDataNascimento.getText().toString()))) {
            binding.editDataNascimento.setError("*");
            binding.editDataNascimento.requestFocus();
            isDadosOk = false;
        }

        return isDadosOk;
    }

    private void salvarSharedPreferences() {

        preferences = getSharedPreferences(SplashActivity.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();


        dados.putString("cpfCliente", binding.editCpf.getText().toString());
        dados.putString("dataNascimento", binding.editDataNascimento.getText().toString());
        dados.putInt("ultimoClientePF", ultimoIDPF);
        dados.apply();

    }

    private void restaurarSharedPreferences() {
        preferences = getSharedPreferences(SplashActivity.PREF_APP, MODE_PRIVATE);
        isPessoaFisica = preferences.getBoolean("pessoaFisica", true);
        clienteID = preferences.getInt("clienteID", -1);

    }


}
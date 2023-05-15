package com.anamuxfeldt.cadastroclientescomdb.view;


import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;


import com.anamuxfeldt.cadastroclientescomdb.controller.ClienteController;
import com.anamuxfeldt.cadastroclientescomdb.databinding.ActivityCadastroNovoClienteCardBinding;
import com.anamuxfeldt.cadastroclientescomdb.model.CadastrarClientePF;
import com.anamuxfeldt.cadastroclientescomdb.model.CadastrarClientePJ;
import com.anamuxfeldt.cadastroclientescomdb.model.Cliente;
import com.anamuxfeldt.cadastroclientescomdb.model.ICadastroCliente;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class CadastroNovoCliente extends AppCompatActivity {
    private ActivityCadastroNovoClienteCardBinding binding;
    ICadastroCliente cadastroCliente;
    ClienteController clienteController;
    Cliente cliente;
    private SharedPreferences preferences;
    int ultimoID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroNovoClienteCardBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        cliente = new Cliente();
        clienteController = new ClienteController(this);
        binding.btnSalvarEContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validarDados()) {

                    if (binding.ckPessoaFisica.isChecked()) {
                        cadastroCliente = new CadastrarClientePF();
                    } else {
                        cadastroCliente = new CadastrarClientePJ();
                    }
                    cliente.setPrimeiroNome(binding.editPrimeiroNome.getText().toString());
                    cliente.setSobrenome(binding.editSobrenome.getText().toString());
                    cliente.setPessoaFisica(cliente.isPessoaFisica());
                    clienteController.incluir(cliente);
                    ultimoID = clienteController.getUltimoID();
                    salvarSharedPreferences();

                    Intent intent = cadastroCliente.cadastrar(CadastroNovoCliente.this);
                    startActivity(intent);
                    finish();
                }
            }
        });

        binding.btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(CadastroNovoCliente.this)
                        .setTitle("LIMPAR")
                        .setMessage("Tem certeza que deseja limpar o formulário?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(CadastroNovoCliente.this, "Formulário limpo", Toast.LENGTH_SHORT).show();
                                dialogInterface.dismiss();
                            }
                        })
                        .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(CadastroNovoCliente.this, "Continuar preenchendo o formulário", Toast.LENGTH_SHORT).show();
                                dialogInterface.dismiss();
                            }
                        });
                builder.create();
                builder.show();
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

        if (!binding.ckPoliticaEPrivacidade.isChecked()) {
            binding.ckPoliticaEPrivacidade.setError("Obrigatório");
            retorno = false;
        }
        return retorno;
    }

    private void salvarSharedPreferences() {
        preferences = getSharedPreferences(SplashActivity.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();

        dados.putString("primeiroNome", binding.editPrimeiroNome.getText().toString());
        dados.putString("sobreNome", binding.editSobrenome.getText().toString());
        dados.putBoolean("pessoaFisica", binding.ckPessoaFisica.isChecked());
        dados.putInt("ultimoID", ultimoID);
        dados.apply();
    }

}
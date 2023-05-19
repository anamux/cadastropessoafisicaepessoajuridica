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
import com.anamuxfeldt.cadastroclientescomdb.controller.TesteController;
import com.anamuxfeldt.cadastroclientescomdb.databinding.ActivityCadastroNovoClienteCardBinding;
import com.anamuxfeldt.cadastroclientescomdb.model.Cliente;
import com.anamuxfeldt.cadastroclientescomdb.model.ClienteTeste;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class CadastroNovoCliente extends AppCompatActivity {
    private ActivityCadastroNovoClienteCardBinding binding;
    ClienteController clienteController;
    Cliente cliente;
    ClienteTeste clienteTeste;
    TesteController testeController;
    private SharedPreferences preferences;
    int clienteID;
    boolean isPessoaFisica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroNovoClienteCardBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        cliente = new Cliente();
        clienteTeste = new ClienteTeste();
        clienteController = new ClienteController(this);
        testeController = new TesteController(this);

        binding.btnSalvarEContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validarDados()) {

                    cliente.setPrimeiroNome(binding.editPrimeiroNome.getText().toString());
                    cliente.setSobrenome(binding.editSobrenome.getText().toString());
                    cliente.setEmail(binding.editEmail.getText().toString());
                    cliente.setSenha(binding.editSenha.getText().toString());
                    cliente.setPessoaFisica(isPessoaFisica);

                    clienteTeste.setPrimeiroNome(binding.editPrimeiroNome.getText().toString());


                    clienteController.incluir(cliente);
               //     testeController.incluir(clienteTeste);
                    clienteID = clienteController.getUltimoID();
                    salvarSharedPreferences();


                        Intent intent = new Intent(CadastroNovoCliente.this, PessoaFisica.class);
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
        if (TextUtils.isEmpty(binding.editEmail.getText().toString())) {
            binding.editEmail.setError("*");
            binding.editEmail.requestFocus();
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
        if (!validarSenha()) {
            Toast.makeText(getApplicationContext(), "As senhas digitadas não conferem...",
                    Toast.LENGTH_LONG).show();
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


    private void salvarSharedPreferences() {
        preferences = getSharedPreferences(SplashActivity.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();

        dados.putString("primeiroNome", cliente.getPrimeiroNome());
        dados.putString("sobreNome",cliente.getSobrenome());
        dados.putString("email", binding.editEmail.getText().toString());
        dados.putString("senha", binding.editSenha.getText().toString());
        dados.putBoolean("pessoaFisica", cliente.isPessoaFisica());
        dados.putInt("clienteID", clienteID);
        dados.apply();
    }

    public void pessoaFisica(View view) {

        isPessoaFisica = binding.ckPessoaFisica.isChecked();

    }
}
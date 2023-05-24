package com.anamuxfeldt.cadastroclientescomdb.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.anamuxfeldt.cadastroclientescomdb.controller.ClienteController;
import com.anamuxfeldt.cadastroclientescomdb.databinding.ActivityMeusDadosBinding;
import com.anamuxfeldt.cadastroclientescomdb.model.Cliente;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MeusDados extends AppCompatActivity {
    private ActivityMeusDadosBinding binding;
    Cliente cliente;
    ClienteController controller;
    SharedPreferences preferences;
    int clienteID;
    boolean isPessoaFisica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMeusDadosBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        restaurarSharedPreferences();



        cliente = new Cliente();
        cliente.setId(clienteID);
        controller=new ClienteController(this);

        buscarDados();
    }

    private void buscarDados() {
    if (clienteID>=1){
    cliente = controller.getClienteById(cliente);
    binding.editPrimeiroNome.setText(cliente.getPrimeiroNome());
    binding.editSobrenome.setText(cliente.getSobrenome());
    binding.editEmail.setText(cliente.getEmail());
    binding.editSenha.setText(cliente.getSenha());
    binding.ckPessoaFisica.setChecked(cliente.isPessoaFisica());
    }else {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(MeusDados.this)
                .setTitle("Atenção")
                .setMessage("Não foi possível recuperar os dados do Cliente.")
                .setNegativeButton("Retornar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(MeusDados.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
        builder.create();
        builder.show();
    }
    }

    public void voltar(View view) {
        Intent intent = new Intent(MeusDados.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void restaurarSharedPreferences() {
        preferences = getSharedPreferences(SplashActivity.PREF_APP, MODE_PRIVATE);
        isPessoaFisica = preferences.getBoolean("pessoaFisica", true);
        clienteID = preferences.getInt("clienteID", 0);

      ///  clienteID= -1;
    }
}
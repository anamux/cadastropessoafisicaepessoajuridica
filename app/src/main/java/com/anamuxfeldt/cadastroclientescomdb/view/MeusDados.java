package com.anamuxfeldt.cadastroclientescomdb.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.anamuxfeldt.cadastroclientescomdb.R;
import com.anamuxfeldt.cadastroclientescomdb.controller.ClienteController;
import com.anamuxfeldt.cadastroclientescomdb.databinding.ActivityMainBinding;
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

        cliente = new Cliente();
        cliente.setId(clienteID);
        controller=new ClienteController(this);


        restaurarSharedPreferences();
        buscarDados();
    }

    private void buscarDados() {
    if (clienteID>=1){
    cliente= controller.getClienteId(cliente);
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
        clienteID = preferences.getInt("clienteID", -1);

        clienteID= -1;
    }
}
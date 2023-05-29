package com.anamuxfeldt.cadastroclientescomdb.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.anamuxfeldt.cadastroclientescomdb.controller.ClienteController;
import com.anamuxfeldt.cadastroclientescomdb.controller.ClientePFController;
import com.anamuxfeldt.cadastroclientescomdb.controller.ClientePJController;
import com.anamuxfeldt.cadastroclientescomdb.databinding.ActivityMainBinding;
import com.anamuxfeldt.cadastroclientescomdb.model.Cliente;
import com.anamuxfeldt.cadastroclientescomdb.model.ClientePF;
import com.anamuxfeldt.cadastroclientescomdb.model.ClientePJ;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    Cliente cliente;
    ClientePF clientePF;
    ClientePJ clientePJ;
    ClienteController clienteController;
    ClientePFController clientePFController;
    ClientePJController clientePJController;
    private SharedPreferences preferences;
    public static final String LOG_APP = "CLIENTE_LOG";
    List<Cliente> clientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        cliente = new Cliente();
        clientePF = new ClientePF();
        clientePJ = new ClientePJ();

        restaurarSharedPreferences();

        binding.bemVindo.setText("Olá " + cliente.getPrimeiroNome());


        clienteController = new ClienteController(this);
        clientePFController = new ClientePFController(this);
        clientePJController = new ClientePJController(this);


    }


    private void restaurarSharedPreferences() {
        preferences = getSharedPreferences(SplashActivity.PREF_APP, MODE_PRIVATE);

        cliente.setPrimeiroNome(preferences.getString("primeiroNome", "Nulo"));
        cliente.setSobrenome(preferences.getString("sobreNome", "Nulo"));
        cliente.setPessoaFisica(preferences.getBoolean("pessoaFisica", true));
        cliente.setId(preferences.getInt("clienteID", -1));

        clientePF.setEmail(preferences.getString("emailCliente", "Nulo"));
        clientePF.setSenha(preferences.getString("senha", "Nulo"));
        clientePF.setDataNascimento(preferences.getString("dataNascimento", "Nulo"));
        clientePF.setCpf(preferences.getString("cpfCliente", "nulo"));

        clientePJ.setCnpj(preferences.getString("cnpj", "nulo"));
        clientePJ.setRazaoSocial(preferences.getString("razãoSocial", "nulo"));
        clientePJ.setDataAbertura(preferences.getString("dataAbertura", "nulo"));
        clientePJ.setSimplesNacional(preferences.getBoolean("simplesNacional", false));
        clientePJ.setMei(preferences.getBoolean("mei", false));


    }

    public void meusDados(View view) {

        Intent intent = new Intent(MainActivity.this, MeusDados.class);
        startActivity(intent);

    }

    public void atualizarMeusDados(View view) {
        Intent intent = new Intent(MainActivity.this, AtualizarDadosActivity.class);
        startActivity(intent);
    }

    public void excluirMinhaConta(View view) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(MainActivity.this)
                .setTitle("Excluir sua conta?")
                .setMessage("Essa é uma ação definitiva. Confirma a exclusão?")
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                                cliente.setClientePF(clientePFController.getClientePFByFK(MainActivity.this,cliente.getId()));
                                clientePFController.deletar(MainActivity.this, cliente.getClientePF());
                                clienteController.deletar(MainActivity.this, cliente);
                                //cliente.setClientePJ();
                                Toast.makeText(MainActivity.this, cliente.getPrimeiroNome(
                                ) + " sua conta será excluída", Toast.LENGTH_SHORT).show();
                                dialogInterface.dismiss();


                                salvarSharedPreferences();
                                finish();
                            }

                        }
                )

                .setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Permanecer no aplicativo", Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                    }
                });
        builder.create();
        builder.show();


    }

    private void salvarSharedPreferences() {
        preferences = getSharedPreferences(SplashActivity.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();
    }

    public void consultarClientes(View view) {
        binding.btnConsultarClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ConsultarClientes.class);
                startActivity(intent);

            }
        });


    }

    public void sairDoAplicativo(View view) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(MainActivity.this)
                .setTitle("SAIR DO APLICATIVO")
                .setMessage("Deseja sair mesmo do aplicativo?")
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Obrigado, " + cliente.getPrimeiroNome(
                        ) + " volte sempre.", Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                        finish();
                    }
                })
                .setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Permanecer no aplicativo", Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                    }
                });
        builder.create();
        builder.show();

    }
}
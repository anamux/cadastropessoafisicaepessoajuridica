package com.anamuxfeldt.cadastropessoafisicaepessoajuridica.view;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.anamuxfeldt.cadastropessoafisicaepessoajuridica.controller.ClienteController;
import com.anamuxfeldt.cadastropessoafisicaepessoajuridica.databinding.ActivityMainBinding;
import com.anamuxfeldt.cadastropessoafisicaepessoajuridica.model.Cliente;
import com.anamuxfeldt.cadastropessoafisicaepessoajuridica.model.ClientePF;
import com.anamuxfeldt.cadastropessoafisicaepessoajuridica.model.ClientePJ;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    Cliente cliente;
    ClientePF clientePF;
    ClientePJ clientePJ;
    private SharedPreferences preferences;
    public static final String LOG_APP="CLIENTE_LOG";
    List <Cliente> clientes;

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
        buscarListadeClientes();

        binding.bemVindo.setText("Olá "+cliente.getPrimeiroNome());
       /* private void salvarSharedPreferences() {
            preferences = getSharedPreferences(ClienteController.PREF_APP, MODE_PRIVATE);
            Log.d(TAG, "salvarSharedPreferences: Pasta criada");
            SharedPreferences.Editor dados = preferences.edit();

            dados.putBoolean("Login automático", isLembrarSenha);
            dados.putString("emailCliente", binding.editEmail.getText().toString());
            dados.apply();

        }*/

    }

    private void buscarListadeClientes() {
        clientes = new ArrayList<>();
        clientes.add(cliente);

        Cliente novoCliente1 = new Cliente();
        novoCliente1.setPrimeiroNome("Novo Cliente 01");
        clientes.add(novoCliente1);

        Cliente novoCliente2 = new Cliente();
        novoCliente2.setPrimeiroNome("Novo Cliente 02");
        clientes.add(novoCliente2);

        Cliente novoCliente3 = new Cliente();
        novoCliente3.setPrimeiroNome("Novo Cliente 03");
        clientes.add(novoCliente3);


        for (Cliente obj: clientes) {
            Log.i(LOG_APP, "Obj: "+obj.getPrimeiroNome());

        }
    }

    private void restaurarSharedPreferences() {
        preferences = getSharedPreferences(ClienteController.PREF_APP, MODE_PRIVATE);

        cliente.setPrimeiroNome(preferences.getString("primeiroNome", "Nulo"));
        cliente.setSobrenome(preferences.getString("sobrenome", "Nulo"));
        cliente.setEmail(preferences.getString("emailCliente", "Nulo"));
        cliente.setSenha(preferences.getString("senha", "Nulo"));
        cliente.setDataNascimento(preferences.getString("dataNascimento", "Nulo"));
        cliente.setPessoaFisica(preferences.getBoolean("pessoaFisica", true));

        clientePF.setCpf(preferences.getString("cpfCliente", "nulo"));
        clientePF.setNomeCompleto(preferences.getString("nomeCompleto", "nulo"));

        clientePJ.setCnpj(preferences.getString("cnpj", "nulo"));
        clientePJ.setRazaoSocial(preferences.getString("razãoSocial", "nulo"));
        clientePJ.setDataAbertura(preferences.getString("dataAbertura", "nulo"));
        clientePJ.setSimplesNacional(preferences.getBoolean("simplesNacional", false));
        clientePJ.setMei(preferences.getBoolean("mei", false));

    }

    public void meusDados(View view) {

        Log.i(LOG_APP, "meusDados:**DADOS CLIENTE** ");
        Log.i(LOG_APP, "meusDados:Primeiro Nome:  "+cliente.getPrimeiroNome());
        Log.i(LOG_APP, "meusDados:Sobrenome:  "+cliente.getSobrenome());
        Log.i(LOG_APP, "meusDados:Email:  "+cliente.getEmail());
        Log.i(LOG_APP, "meusDados:Data Nascimento:  "+cliente.getDataNascimento());

    }

    public void atualizarMeusDados(View view) {
        if(cliente.isPessoaFisica()){
            cliente.setPrimeiroNome("Ana");
            cliente.setSobrenome("Muxfeldt");
            clientePF.setNomeCompleto("Ana Muxfeldt");

            //salvarSharedPreferences();

            Log.i(LOG_APP, "atualizarMeusDados: ***Alterando dados***");
            Log.i(LOG_APP, "Primeiro Nome: "+cliente.getPrimeiroNome());
            Log.i(LOG_APP, "Sobrenome: "+cliente.getSobrenome());
            Log.i(LOG_APP, "***Alterando dados PF***");
            Log.i(LOG_APP, "Nome Completo: "+clientePF.getSobrenome());

        }else{
            clientePJ.setCnpj("00111222000100");

            Log.i(LOG_APP, "***Alterando dados PJ***");
            Log.i(LOG_APP, "CNPJ: "+clientePJ.getCnpj());

        }
    }

    public void excluirMinhaConta(View view) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(MainActivity.this)
                .setTitle("Excluir sua conta?")
                .setMessage("Essa é uma ação definitiva. Confirma a exclusão?")
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, cliente.getPrimeiroNome(
                        ) + " sua conta será excluída", Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                        cliente = new Cliente();
                        clientePF = new ClientePF();
                        clientePJ = new ClientePJ();
                        
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
    }

    public void consultarClientes(View view) {
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
        finish();
    }
}
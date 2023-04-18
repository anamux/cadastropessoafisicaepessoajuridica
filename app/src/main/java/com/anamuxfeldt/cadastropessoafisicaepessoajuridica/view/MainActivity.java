package com.anamuxfeldt.cadastropessoafisicaepessoajuridica.view;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.anamuxfeldt.cadastropessoafisicaepessoajuridica.controller.ClienteController;
import com.anamuxfeldt.cadastropessoafisicaepessoajuridica.databinding.ActivityMainBinding;
import com.anamuxfeldt.cadastropessoafisicaepessoajuridica.model.Cliente;
import com.anamuxfeldt.cadastropessoafisicaepessoajuridica.model.ClientePF;
import com.anamuxfeldt.cadastropessoafisicaepessoajuridica.model.ClientePJ;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    Cliente cliente;
    ClientePF clientePF;
    ClientePJ clientePJ;
    private SharedPreferences preferences;

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
       /* private void salvarSharedPreferences() {
            preferences = getSharedPreferences(ClienteController.PREF_APP, MODE_PRIVATE);
            Log.d(TAG, "salvarSharedPreferences: Pasta criada");
            SharedPreferences.Editor dados = preferences.edit();

            dados.putBoolean("Login automático", isLembrarSenha);
            dados.putString("emailCliente", binding.editEmail.getText().toString());
            dados.apply();

        }*/

    }

    private void restaurarSharedPreferences() {
        preferences = getSharedPreferences(ClienteController.PREF_APP, MODE_PRIVATE);

        cliente.setPrimeiroNome(preferences.getString("primeiroNome", "Nulo"));
        cliente.setSobrenome(preferences.getString("sobrenome", "Nulo"));
        cliente.setEmail(preferences.getString("emailCliente", "Nulo"));
        cliente.setSenha(preferences.getString("senha", "Nulo"));
        cliente.setPessoaFisica(preferences.getBoolean("pessoaFisica", true));

        clientePF.setCpf(preferences.getString("cpfCliente", "nulo"));
        clientePF.setNomeCompleto(preferences.getString("nomeCompleto", "nulo"));

        clientePJ.setCnpj(preferences.getString("cnpj", "nulo"));
        clientePJ.setRazaoSocial(preferences.getString("razãoSocial", "nulo"));
        clientePJ.setDataAbertura(preferences.getString("dataAbertura", "nulo"));
        clientePJ.setSimplesNacional(preferences.getBoolean("simplesNacional", false));
        clientePJ.setMei(preferences.getBoolean("mei", false));

    }
}
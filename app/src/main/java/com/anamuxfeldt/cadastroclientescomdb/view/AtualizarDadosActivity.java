package com.anamuxfeldt.cadastroclientescomdb.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.anamuxfeldt.cadastroclientescomdb.controller.ClienteController;
import com.anamuxfeldt.cadastroclientescomdb.controller.ClientePFController;
import com.anamuxfeldt.cadastroclientescomdb.controller.ClientePJController;
import com.anamuxfeldt.cadastroclientescomdb.databinding.ActivityAtualizarDadosBinding;
import com.anamuxfeldt.cadastroclientescomdb.model.Cliente;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;


public class AtualizarDadosActivity extends AppCompatActivity {
    Cliente cliente;
    ClienteController controller;
    ClientePFController clientePFController;
    ClientePJController clientePJController;
    int clienteID;
    boolean isPessoaFisica;
    SharedPreferences preferences;
    private ActivityAtualizarDadosBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAtualizarDadosBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        restaurarSharedPreferences();

        cliente = new Cliente();
        cliente.setId(clienteID);

        controller = new ClienteController(this);
        clientePFController = new ClientePFController(this);
        clientePJController = new ClientePJController(this);


        buscarDados();
    }


    private void buscarDados() {
        if (clienteID >= 1) {
            cliente = controller.getClienteById(getApplicationContext(),cliente);
            cliente.setClientePF(clientePFController.getClientePFByFK(getApplicationContext(),clienteID));

            if (!cliente.isPessoaFisica())
                cliente.setClientePJ(clientePJController.getClientePJByFK(getApplicationContext(),cliente.getClientePF().getId()));


            //Obj Cliente
            binding.editPrimeiroNome.setText(cliente.getPrimeiroNome());
            binding.editSobrenome.setText(cliente.getSobrenome());
            binding.editEmail.setText(cliente.getEmail());
            binding.editSenha.setText(cliente.getSenha());
            binding.ckPessoaFisica.setChecked(cliente.isPessoaFisica());

            //Obj ClientePF
            binding.editCpf.setText(cliente.getClientePF().getCpf());
            binding.editDataNascimento.setText(cliente.getClientePF().getDataNascimento());

            //Obj ClientePJ
            if (!cliente.isPessoaFisica()) {
                binding.editRazaoSocial.setText(cliente.getClientePJ().getRazaoSocial());
                binding.editCnpj.setText(cliente.getClientePJ().getCnpj());
                binding.ckMei.setChecked(cliente.getClientePJ().isMei());
                binding.ckSimplesNacional.setChecked(cliente.getClientePJ().isSimplesNacional());
            }
        } else {
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(AtualizarDadosActivity.this)
                    .setTitle("Atenção")
                    .setMessage("Não foi possível recuperar os dados do Cliente.")
                    .setNegativeButton("Retornar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(AtualizarDadosActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
            builder.create();
            builder.show();
        }
    }

    /**
     * Método que ativa o botão Salvar e
     * desativa o botão Editar no Card Cliente
     *
     * @param view
     */
    public void editarCliente(View view) {
        binding.btnEditar.setEnabled(false);
        binding.btnSalvar.setEnabled(true);

        binding.editPrimeiroNome.setEnabled(true);
        binding.editSobrenome.setEnabled(true);
    }

    /**
     * Método que ativa o botão Salvar e
     * desativa o botão Editar no Card ClientePJ
     *
     * @param view
     */
    public void editarClientePJ(View view) {
        binding.btnEditarPJ.setEnabled(false);
        binding.btnSalvarPJ.setEnabled(true);
    }

    public void voltar(View view) {
        Intent intent = new Intent(AtualizarDadosActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void restaurarSharedPreferences() {
        preferences = getSharedPreferences(SplashActivity.PREF_APP, MODE_PRIVATE);
        isPessoaFisica = preferences.getBoolean("pessoaFisica", true);
        clienteID = preferences.getInt("clienteID", 0);
    }
}
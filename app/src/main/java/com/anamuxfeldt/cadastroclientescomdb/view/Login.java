package com.anamuxfeldt.cadastroclientescomdb.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.anamuxfeldt.cadastroclientescomdb.R;
import com.anamuxfeldt.cadastroclientescomdb.controller.ClienteController;
import com.anamuxfeldt.cadastroclientescomdb.databinding.ActivityLoginBinding;
import com.anamuxfeldt.cadastroclientescomdb.model.Cliente;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.squareup.picasso.Picasso;

public class Login extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private SharedPreferences preferences;
    boolean isLembrarSenha;
    Cliente cliente;
    ClienteController controller;
    public static final String URL_IMG_BACKGROUND = "https://www.marcomaddo.com.br/aluno/daazi/img/app-cliente-vip-login-bg.jpg";
    public static final String URL_IMG_LOGO = "https://www.marcomaddo.com.br/aluno/daazi/img/app-cliente-vip-logo.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //cliente = new Cliente();
       // controller = new ClienteController(getApplicationContext());

        /*for (int i = 0; i <30; i++) {


        cliente.setPrimeiroNome("Nome "+i);
        cliente.setSobrenome("Sobrenome "+i);
        cliente.setEmail(i+"@teste");
        cliente.setDataNascimento("16/12/1990");
        cliente.setSenha(i+"0123");
        controller.incluir(cliente);*/
           // cliente.setId(10);
            //controller.deletar(cliente);
        // }
        restaurarSharedPreferences();

        loadImagens();



        binding.txtPoliticaETermos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(Login.this)
                        .setTitle("Política de Privacidade e Termos de uso")
                        .setMessage("Texto sobre Política de Privacidade e Termos de uso")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(Login.this, "Positive Button Clicked", Toast.LENGTH_SHORT).show();
                                dialogInterface.dismiss();
                            }
                        })
                        .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(Login.this, "Negative Button Clicked", Toast.LENGTH_SHORT).show();
                                dialogInterface.dismiss();
                            }
                        });
                builder.create();
                builder.show();
            }

        });
        binding.txtRecuperarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(Login.this)
                        .setTitle("Recuperar senha")
                        .setMessage("Uma nova senha de acesso será enviada para o seu email.")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(Login.this, "Positive Button Clicked", Toast.LENGTH_SHORT).show();
                                dialogInterface.dismiss();
                            }
                        })
                        .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(Login.this, "Negative Button Clicked", Toast.LENGTH_SHORT).show();
                                dialogInterface.dismiss();
                            }
                        });
                builder.create();
                builder.show();
            }

        });
        binding.btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, CadastroNovoCliente.class);
                startActivity(intent);
                finish();
            }
        });
        binding.btnAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validarFormulario()) {

                    if (validarDadosUsuario()) {
                        salvarSharedPreferences();
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        return;
                    } else {
                        Toast.makeText(getApplicationContext(), "Verifique os dados...",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private void loadImagens() {
    Picasso.get().load(URL_IMG_BACKGROUND).placeholder(R.drawable.carregando_img).into(binding.imgBackground);
    Picasso.get().load(URL_IMG_LOGO).placeholder(R.drawable.carregando_animacao).into(binding.imgLogo);
    }

    private boolean validarDadosUsuario() {

        return true;
    }

    private boolean validarFormulario() {
        boolean isDadosOk = true;
        if (TextUtils.isEmpty(binding.editEmail.getText().toString())) {
            binding.editEmail.setError("*");
            binding.editEmail.requestFocus();
            isDadosOk = false;
        }
        if (TextUtils.isEmpty((binding.editSenha.getText().toString()))) {
            binding.editSenha.setError("*");
            binding.editSenha.requestFocus();
            isDadosOk = false;
        }
        return isDadosOk;
    }

    public void lembrarSenha(View view) {
        isLembrarSenha = binding.ckLembrar.isChecked();

    }

    private void salvarSharedPreferences(){
        preferences = getSharedPreferences(SplashActivity.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();

        dados.putBoolean("loginAutomatico", isLembrarSenha);
        dados.putString("emailCliente", binding.editEmail.getText().toString());
        dados.apply();
    }
    private void restaurarSharedPreferences() {
        preferences = getSharedPreferences(SplashActivity.PREF_APP, MODE_PRIVATE);

        cliente.setEmail(preferences.getString("email", "teste@teste.com"));
        cliente.setSenha(preferences.getString("senha", "12345"));

        isLembrarSenha = preferences.getBoolean("loginAutomatico", false);

    }

}

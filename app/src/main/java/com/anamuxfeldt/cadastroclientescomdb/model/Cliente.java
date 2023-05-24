package com.anamuxfeldt.cadastroclientescomdb.model;

import com.anamuxfeldt.cadastroclientescomdb.controller.ClienteController;

public class Cliente {
    private int id;
    private String primeiroNome;
    private String sobrenome;
    private String email;
    private String senha;
    private boolean pessoaFisica;

    private ClientePF clientePF;
    private ClientePJ clientePJ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(boolean pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }

    public ClientePF getClientePF() {
        return clientePF;
    }

    public void setClientePF(ClientePF clientePF) {
        this.clientePF = clientePF;
    }

    public ClientePJ getClientePJ() {
        return clientePJ;
    }

    public void setClientePJ(ClientePJ clientePJ) {
        this.clientePJ = clientePJ;
    }
}

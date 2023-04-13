package com.anamuxfeldt.cadastropessoafisicaepessoajuridica.controller;

import com.anamuxfeldt.cadastropessoafisicaepessoajuridica.model.Cliente;

public class ClienteController {// TODO: 12/04/2023 verificar DB
    public static final String PREF_APP = "App_Cadastro_Cliente";

    public static boolean validarDadosDoCliente(Cliente cliente, String email, String senha){

        boolean retorno = ((cliente.getEmail()).equals(email) && (cliente.getSenha().equals(senha)));
        return retorno;
    }
    public static Cliente getClienteFake(){
        Cliente fake = new Cliente();
        fake.setPrimeiroNome("Ana");
        fake.setSobrenome("mux");
        fake.setEmail("ana@teste");
        fake.setSenha("123");
        fake.setPessoaFisica(true);
        return fake;
    }
}

package com.anamuxfeldt.cadastropessoafisicaepessoajuridica.controller;

import com.anamuxfeldt.cadastropessoafisicaepessoajuridica.model.Cliente;

public class ClienteController {// TODO: 12/04/2023 verificar DB
    public static final String PREF_APP = "App_Cadastro_Cliente";

    public static boolean validarDadosDoCliente(Cliente cliente, String email, String senha){

        boolean retorno = ((cliente.getEmail()).equals(email) && (cliente.getSenha().equals(senha)));
        return retorno;
    }


}

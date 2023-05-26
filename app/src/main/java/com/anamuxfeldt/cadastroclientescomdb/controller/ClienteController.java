package com.anamuxfeldt.cadastroclientescomdb.controller;


import android.content.ContentValues;
import android.content.Context;

import com.anamuxfeldt.cadastroclientescomdb.database.AppDataBase;
import com.anamuxfeldt.cadastroclientescomdb.database.ClienteDataModel;
import com.anamuxfeldt.cadastroclientescomdb.model.Cliente;

import java.util.List;

public class ClienteController{
    private AppDataBase db;
    public static final String TABELA = ClienteDataModel.TABELA;
    private ContentValues dados;

    public ClienteController(Context context) {
        db = AppDataBase.getInstance(context);
    }

    public boolean incluir(Context context, Cliente obj){
        dados = new ContentValues();

        dados.put(ClienteDataModel.PRIMEIRO_NOME, obj.getPrimeiroNome());
        dados.put(ClienteDataModel.SOBRENOME, obj.getSobrenome());
        dados.put(ClienteDataModel.EMAIL, obj.getEmail());
        dados.put(ClienteDataModel.SENHA, obj.getSenha());
        dados.put(ClienteDataModel.PESSOAFISICA, obj.isPessoaFisica());

        AppDataBase db = AppDataBase.getInstance(context);

        return db.insert(TABELA, dados);
    }
   /* public boolean alterar(Cliente obj){
        dados = new ContentValues();

        dados.put(ClienteDataModel.ID, obj.getId());
        dados.put(ClienteDataModel.PRIMEIRO_NOME, obj.getPrimeiroNome());
        dados.put(ClienteDataModel.SOBRENOME, obj.getSobrenome());
        dados.put(ClienteDataModel.EMAIL, obj.getEmail());
        dados.put(ClienteDataModel.SENHA, obj.getSenha());
        dados.put(ClienteDataModel.PESSOAFISICA, obj.isPessoaFisica());

        return update(TABELA, dados);
    }
    public boolean deletar(Cliente obj){
        return delete(TABELA, obj.getId());
    }*/

    public List<Cliente> listar(Context context){

        AppDataBase db = AppDataBase.getInstance(context);
        return db.listClientes(TABELA);
    }

    public int getUltimoID(Context context){
        AppDataBase db = AppDataBase.getInstance(context);

        return  db.getLastPK(TABELA);
    }

    public Cliente getClienteById(Context context, Cliente obj){
        AppDataBase db = AppDataBase.getInstance(context);

       return db.getClienteById(ClienteDataModel.TABELA, obj);
    }
}

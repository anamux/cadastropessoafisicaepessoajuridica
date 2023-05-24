package com.anamuxfeldt.cadastroclientescomdb.controller;


import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.Nullable;

import com.anamuxfeldt.cadastroclientescomdb.database.AppDataBase;
import com.anamuxfeldt.cadastroclientescomdb.database.ClienteDataModel;
import com.anamuxfeldt.cadastroclientescomdb.database.ClientePFDataModel;
import com.anamuxfeldt.cadastroclientescomdb.model.Cliente;

import java.util.List;

public class ClienteController extends AppDataBase {
    public static final String TABELA = ClienteDataModel.TABELA;
    private ContentValues dados;

    public ClienteController(@Nullable Context context) {
        super(context);
    }

    public boolean alterar(Cliente obj){
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
    }
    public boolean incluir(Cliente obj){
        dados = new ContentValues();

        dados.put(ClienteDataModel.PRIMEIRO_NOME, obj.getPrimeiroNome());
        dados.put(ClienteDataModel.SOBRENOME, obj.getSobrenome());
        dados.put(ClienteDataModel.EMAIL, obj.getEmail());
        dados.put(ClienteDataModel.SENHA, obj.getSenha());
        dados.put(ClienteDataModel.PESSOAFISICA, obj.isPessoaFisica());

        return insert(TABELA, dados);
    }
    public List<Cliente> listar(){

        return listClientes(TABELA);
    }

    public int getUltimoID(){
        return  getLastPK(TABELA);
    }

    public Cliente getClienteById(Cliente obj){
       return getClienteById(ClienteDataModel.TABELA, obj);
    }
}

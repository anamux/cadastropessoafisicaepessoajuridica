package com.anamuxfeldt.cadastroclientescomdb.controller;


import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.Nullable;

import com.anamuxfeldt.cadastroclientescomdb.database.AppDataBase;
import com.anamuxfeldt.cadastroclientescomdb.database.ClientePFDataModel;
import com.anamuxfeldt.cadastroclientescomdb.model.Cliente;
import com.anamuxfeldt.cadastroclientescomdb.model.ClientePF;
import com.anamuxfeldt.cadastroclientescomdb.model.ClientePJ;

import java.util.List;

public class ClientePFController extends AppDataBase {
    public static final String TABELA = ClientePFDataModel.TABELA;
    private ContentValues dados;

    public ClientePFController(@Nullable Context context) {
        super(context);
    }

    public boolean alterar(ClientePF obj){
        dados = new ContentValues();

        dados.put(ClientePFDataModel.ID, obj.getId());
        dados.put(ClientePFDataModel.CPF, obj.getCpf());




        return update(TABELA, dados);
    }
    public boolean deletar(ClientePF obj){
        return delete(TABELA, obj.getId());
    }
    public boolean incluir(ClientePF obj){
        dados = new ContentValues();

        dados.put(ClientePFDataModel.FK, obj.getClienteID());
        dados.put(ClientePFDataModel.CPF, obj.getCpf());
        dados.put(ClientePFDataModel.DATA_NASCIMENTO, obj.getDataNascimento());


        return insert(TABELA, dados);
    }
    public List<ClientePF> listar(){

        return listClientesPF(TABELA);
    }

    public int getUltimoID(){
        return  getLastPK(TABELA);
    }
public ClientePF getClientePFByFK(int idFK){


    return getClientePFByFK(ClientePFDataModel.TABELA, idFK);
}
}

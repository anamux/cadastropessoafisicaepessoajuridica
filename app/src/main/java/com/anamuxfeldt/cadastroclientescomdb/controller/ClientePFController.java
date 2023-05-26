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

public class ClientePFController{
    public static final String TABELA = ClientePFDataModel.TABELA;
    private ContentValues dados;
    private AppDataBase db;

    public ClientePFController(Context context) {
        db = AppDataBase.getInstance(context);
    }


   /* public boolean alterar(ClientePF obj){
        dados = new ContentValues();

        dados.put(ClientePFDataModel.ID, obj.getId());
        dados.put(ClientePFDataModel.CPF, obj.getCpf());




        return update(TABELA, dados);
    }
    public boolean deletar(ClientePF obj){
        return delete(TABELA, obj.getId());
    }*/
    public boolean incluir(Context context, ClientePF obj){
        dados = new ContentValues();

        dados.put(ClientePFDataModel.FK, obj.getClienteID());
        dados.put(ClientePFDataModel.CPF, obj.getCpf());
        dados.put(ClientePFDataModel.DATA_NASCIMENTO, obj.getDataNascimento());

        AppDataBase db = AppDataBase.getInstance(context);

        return db.insert(TABELA, dados);
    }
    public List<ClientePF> listar(Context context){

        AppDataBase db = AppDataBase.getInstance(context);
        return db.listClientesPF(TABELA);
    }

    public int getUltimoID(Context context){
        AppDataBase db = AppDataBase.getInstance(context);
        return  db.getLastPK(TABELA);
    }
public ClientePF getClientePFByFK(Context context,int idFK){
    AppDataBase db = AppDataBase.getInstance(context);


    return db.getClientePFByFK(ClientePFDataModel.TABELA, idFK);
}
}

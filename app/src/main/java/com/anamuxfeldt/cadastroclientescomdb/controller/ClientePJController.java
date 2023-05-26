package com.anamuxfeldt.cadastroclientescomdb.controller;


import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.Nullable;

import com.anamuxfeldt.cadastroclientescomdb.database.AppDataBase;
import com.anamuxfeldt.cadastroclientescomdb.database.ClientePFDataModel;
import com.anamuxfeldt.cadastroclientescomdb.database.ClientePJDataModel;
import com.anamuxfeldt.cadastroclientescomdb.model.ClientePF;
import com.anamuxfeldt.cadastroclientescomdb.model.ClientePJ;

import java.util.List;

public class ClientePJController {
    private AppDataBase db;
    public static final String TABELA = ClientePJDataModel.TABELA;
    private ContentValues dados;

    public ClientePJController(Context context) {
        db = AppDataBase.getInstance(context);
    }

  /*  public boolean alterar(ClientePJ obj){
        dados = new ContentValues();

        dados.put(ClientePJDataModel.ID, obj.getId());
        dados.put(ClientePJDataModel.FK, obj.getClientePFID());
        dados.put(ClientePJDataModel.CNPJ, obj.getCnpj());
        dados.put(ClientePJDataModel.MEI, obj.isMei());
        dados.put(ClientePJDataModel.RAZAO_SOCIAL, obj.getRazaoSocial());
        dados.put(ClientePJDataModel.SIMPLES_NACIONAL, obj.isSimplesNacional());
        dados.put(ClientePJDataModel.DATA_ABERTURA, obj.getDataAbertura());



        return update(TABELA, dados);
    }
    public boolean deletar(ClientePJ obj){
        return delete(TABELA, obj.getId());
    }*/
    public boolean incluir(Context context, ClientePJ obj){
        dados = new ContentValues();

        dados.put(ClientePJDataModel.FK, obj.getClientePFID());
        dados.put(ClientePJDataModel.CNPJ, obj.getCnpj());
        dados.put(ClientePJDataModel.RAZAO_SOCIAL, obj.getRazaoSocial());
        dados.put(ClientePJDataModel.DATA_ABERTURA, obj.getDataAbertura());
        dados.put(ClientePJDataModel.SIMPLES_NACIONAL, obj.isSimplesNacional());
        dados.put(ClientePJDataModel.MEI, obj.isMei());

        AppDataBase db = AppDataBase.getInstance(context);
        return db.insert(TABELA, dados);
    }
    public List<ClientePJ> listar(Context context){

        AppDataBase db = AppDataBase.getInstance(context);
        return db.listClientesPJ(TABELA);
    }

    public int getUltimoID(Context context){
        AppDataBase db = AppDataBase.getInstance(context);
        return  db.getLastPK(TABELA);
    }
    public ClientePJ getClientePJByFK(Context context, int idFK){

        AppDataBase db = AppDataBase.getInstance(context);
        return db.getClientePJByFK(ClientePJDataModel.TABELA, idFK);
    }

}

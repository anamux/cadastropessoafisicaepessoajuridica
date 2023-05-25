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

public class ClientePJController extends AppDataBase {
    public static final String TABELA = ClientePJDataModel.TABELA;
    private ContentValues dados;

    public ClientePJController(@Nullable Context context) {
        super(context);
    }

    public boolean alterar(ClientePJ obj){
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
    }
    public boolean incluir(ClientePJ obj){
        dados = new ContentValues();

        dados.put(ClientePJDataModel.FK, obj.getClientePFID());
        dados.put(ClientePJDataModel.CNPJ, obj.getCnpj());
        dados.put(ClientePJDataModel.RAZAO_SOCIAL, obj.getRazaoSocial());
        dados.put(ClientePJDataModel.DATA_ABERTURA, obj.getDataAbertura());
        dados.put(ClientePJDataModel.SIMPLES_NACIONAL, obj.isSimplesNacional());
        dados.put(ClientePJDataModel.MEI, obj.isMei());

        return insert(TABELA, dados);
    }
    public List<ClientePJ> listar(){

        return listClientesPJ(TABELA);
    }

    public int getUltimoID(){
        return  getLastPK(TABELA);
    }
    public ClientePJ getClientePJByFK(int idFK){


        return getClientePJByFK(ClientePJDataModel.TABELA, idFK);
    }

}

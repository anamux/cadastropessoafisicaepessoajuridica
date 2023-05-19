package com.anamuxfeldt.cadastroclientescomdb.controller;


import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.Nullable;

import com.anamuxfeldt.cadastroclientescomdb.database.AppDataBase;
import com.anamuxfeldt.cadastroclientescomdb.database.ClienteDataModel;
import com.anamuxfeldt.cadastroclientescomdb.database.TesteDataModel;
import com.anamuxfeldt.cadastroclientescomdb.model.Cliente;
import com.anamuxfeldt.cadastroclientescomdb.model.ClienteTeste;

import java.util.List;

public class TesteController extends AppDataBase {
    public static final String TABELA = TesteDataModel.TABELA;
    private ContentValues dados;

    public TesteController(@Nullable Context context) {
        super(context);
    }

    public boolean alterar(ClienteTeste obj){
        dados = new ContentValues();

        dados.put(TesteDataModel.ID, obj.getId());
        dados.put(TesteDataModel.PRIMEIRO_NOME, obj.getPrimeiroNome());

        return update(TABELA, dados);
    }
    public boolean deletar(ClienteTeste obj){
        return delete(TABELA, obj.getId());
    }
    public boolean incluir(ClienteTeste obj){
        dados = new ContentValues();

        dados.put(TesteDataModel.PRIMEIRO_NOME, obj.getPrimeiroNome());


        return insert(TABELA, dados);
    }
  /*  public List<Cliente> listar(){

        return listClientes(TABELA);
    }*/

    public int getUltimoID(){
        return  getLastPK(TABELA);
    }
}

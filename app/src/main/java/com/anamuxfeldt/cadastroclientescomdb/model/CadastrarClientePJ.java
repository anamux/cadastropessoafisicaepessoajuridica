package com.anamuxfeldt.cadastroclientescomdb.model;

import android.content.Context;
import android.content.Intent;

import com.anamuxfeldt.cadastroclientescomdb.view.PessoaJuridica;

public class CadastrarClientePJ implements ICadastroCliente{
    @Override
    public Intent cadastrar(Context context) {
        return new Intent(context, PessoaJuridica.class);
    }
}

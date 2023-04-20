package com.anamuxfeldt.cadastroclientescomdb.model;

import android.content.Context;
import android.content.Intent;

import com.anamuxfeldt.cadastroclientescomdb.view.PessoaFisica;

public class CadastrarClientePF implements ICadastroCliente {
    @Override
    public Intent cadastrar(Context context) {
        return new Intent(context, PessoaFisica.class);
    }
}

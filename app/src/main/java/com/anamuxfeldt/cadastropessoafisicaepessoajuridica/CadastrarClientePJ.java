package com.anamuxfeldt.cadastropessoafisicaepessoajuridica;

import android.content.Context;
import android.content.Intent;

public class CadastrarClientePJ implements ICadastroCliente{
    @Override
    public Intent cadastrar(Context context) {
        return new Intent(context, PessoaJuridica.class);
    }
}

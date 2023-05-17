package com.anamuxfeldt.cadastroclientescomdb.database;

import android.util.Log;

public class ClientePFDataModel {

    public static final String TABELA = "clientePF";

    public static final String ID = "id";
    public static final String FK = "clienteID";
    public static final String CPF = "cpf";
    public static final String DATA_NASCIMENTO = "dataNascimento";
    private static final String DATA_INC = "datainc";
    private static final String DATA_ALT = "dataalt";

    private static String query;

    public static String gerarTabela(){

        query = "CREATE TABLE "+TABELA+" ( ";
        query += ID+" INTEGER PRIMARY KEY AUTOINCREMENT, ";
        query += FK+" INTEGER, ";
        query += CPF+" TEXT, ";
        query += DATA_NASCIMENTO+" TEXT, ";
        query += DATA_INC+" DATETIME DEFAULT CURRENT_TIMESTAMP, ";
        query += DATA_ALT+" DATETIME DEFAULT CURRENT_TIMESTAMP, ";
        query += "FOREIGN KEY("+FK+") REFERENCES cliente (id) ";

        query += ")";
        Log.d("ClientePFDataModel", "gerarTabela: " +query);
        return query;
    }
}

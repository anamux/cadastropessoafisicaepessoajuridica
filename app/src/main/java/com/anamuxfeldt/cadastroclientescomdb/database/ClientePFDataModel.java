package com.anamuxfeldt.cadastroclientescomdb.database;

public class ClientePFDataModel {

    private static final String TABELA = "clientePF";

    private static final String ID = "id";
    private static final String FK = "clienteID";
    private static final String CPF = "cpf";
    private static final String NOME_COMPLETO = "nomeCompleto";
    private static final String DATA_INC = "datainc";
    private static final String DATA_ALT = "dataalt";

    private static String query;

    public static String gerarTabela(){

        query = "CREATE TABLE "+TABELA+" ( ";
        query += ID+" INTEGER PRIMARY KEY AUTOINCREMENT, ";
        query += FK+" INTEGER, ";
        query += CPF+" TEXT, ";
        query += NOME_COMPLETO+" TEXT, ";
        query += DATA_INC+" TEXT, ";
        query += DATA_ALT+" TEXT, ";
        query += "FOREIGN KEY("+FK+") REFERENCES cliente(id) ";

        query += ")";

        return query;
    }
}

package com.anamuxfeldt.cadastroclientescomdb.database;

public class ClientePJDataModel {

    public static final String TABELA  = "clientePJ";

    public static final String ID = "id";
    public static final String FK = "clientePFID";
    public static final String RAZAO_SOCIAL = "razao_social";
    public static final String CNPJ = "cnpj";
    public static final String DATA_ABERTURA = "dataAbertura";
    public static final String SIMPLES_NACIONAL = "simplesNacional";
    public static final String MEI = "mei";
    private static final String DATA_INC = "datainc";
    private static final String DATA_ALT = "dataalt";

    private static String query;

    public static String gerarTabela(){

        query = "CREATE TABLE "+TABELA+" ( ";
        query += ID+" INTEGER PRIMARY KEY AUTOINCREMENT, ";
        query += FK+" INTEGER, ";
        query += CNPJ+" TEXT, ";
        query += RAZAO_SOCIAL+" TEXT, ";
        query += DATA_ABERTURA+" TEXT, ";
        query += SIMPLES_NACIONAL+" INTEGER, ";
        query += MEI+" INTEGER, ";
        query += DATA_INC+" DATETIME DEFAULT CURRENT_TIMESTAMP, ";
        query += DATA_ALT+" DATETIME DEFAULT CURRENT_TIMESTAMP, ";
        query += "FOREIGN KEY("+FK+") REFERENCES cliente (id) ";

        query += ")";

        return query;
    }
}

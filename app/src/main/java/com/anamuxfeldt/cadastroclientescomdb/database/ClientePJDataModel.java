package com.anamuxfeldt.cadastroclientescomdb.database;

public class ClientePJDataModel {

    private static final String TABELA  = "clientePJ";

    private static final String ID = "id";
    private static final String FK = "clientePFID";
    private static final String RAZAO_SOCIAL = "razao_social";
    private static final String DATA_ABERTURA = "dataAbertura";
    private static final String SIMPLES_NACIONAL = "simplesNacional";
    private static final String MEI = "mei";
    private static final String DATA_INC = "datainc";
    private static final String DATA_ALT = "dataalt";

    private static String query;

    public static String gerarTabela(){

        query = "CREATE TABLE "+TABELA+" ( ";
        query += ID+" INTEGER PRIMARY KEY AUTOINCREMENT, ";
        query += FK+" INTEGER, ";
        query += RAZAO_SOCIAL+" TEXT, ";
        query += DATA_ABERTURA+" TEXT, ";
        query += SIMPLES_NACIONAL+" INTEGER, ";
        query += MEI+" INTEGER, ";
        query += DATA_INC+" TEXT, ";
        query += DATA_ALT+" TEXT, ";
        query += "FOREIGN KEY("+FK+") REFERENCES clientePF(id) ";

        query += ")";

        return query;
    }
}

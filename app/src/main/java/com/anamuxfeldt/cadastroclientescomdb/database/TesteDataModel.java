package com.anamuxfeldt.cadastroclientescomdb.database;

public class TesteDataModel {

    public static final String TABELA = "teste";
    public static final String ID = "id";
    public static final String PRIMEIRO_NOME = "primeiroNome";
    private static final String DATA_INC = "dataInclusao";
    private static final String DATA_ALT = "dataAlteracao";

    private static String query;

    public static String gerarTabela(){

        query = "";
        query += "CREATE TABLE "+TABELA+" (";
        query += ID+" INTEGER PRIMARY KEY AUTOINCREMENT, ";
        query += PRIMEIRO_NOME+" TEXT, ";
        query += DATA_ALT+" DATETIME DEFAULT CURRENT_TIMESTAMP, ";
        query += DATA_INC+" DATETIME DEFAULT CURRENT_TIMESTAMP";
        query +=")";


        return query;
    }
}

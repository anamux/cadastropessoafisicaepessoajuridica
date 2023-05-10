package com.anamuxfeldt.cadastroclientescomdb.database;

public class ClienteDataModel {

    public static final String TABELA = "cliente";
    public static final String ID = "id";
    public static final String PRIMEIRO_NOME = "primeiroNome";
    public static final String SOBRENOME = "sobrenome";
    public static final String EMAIL = "email";
    public static final String SENHA = "senha";
    public static final String PESSOAFISICA = "pessoaFisica";
    private static final String DATA_INC = "dataInclusao";
    private static final String DATA_ALT = "dataAlteracao";

    private static String query;

    public static String gerarTabela(){

        query = "";
        query += "CREATE TABLE "+TABELA+" (";
        query += ID+" INTEGER PRIMARY KEY AUTOINCREMENT, ";
        query += PRIMEIRO_NOME+" TEXT, ";
        query += SOBRENOME+" TEXT, ";
        query += EMAIL+" TEXT, ";
        query += SENHA+" TEXT, ";
        query += PESSOAFISICA+" INTEGER, ";
        query += DATA_ALT+" TIMESTAMP DEFAULT CURRENT_TIMESTAMP, ";
        query += DATA_INC+" TIMESTAMP DEFAULT CURRENT_TIMESTAMP";
        query +=")";


        return query;
    }
}

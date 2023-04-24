package com.anamuxfeldt.cadastroclientescomdb.database;

public class ClienteDataModel {

    private static final String TABELA = "cliente";
    private static final String ID = "id";
    private static final String PRIMEIRO_NOME = "primeiroNome";
    private static final String SOBRENOME = "sobrenome";
    private static final String EMAIL = "email";
    private static final String SENHA = "senha";
    private static final String PESSOAFISICA = "pessoaFisica";
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
        query += DATA_ALT+" TEXT, ";
        query += DATA_INC+" TEXT";
        query +=")";


        return query;
    }
}

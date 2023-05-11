package com.anamuxfeldt.cadastroclientescomdb.database;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.anamuxfeldt.cadastroclientescomdb.model.Cliente;
import com.anamuxfeldt.cadastroclientescomdb.view.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class AppDataBase extends SQLiteOpenHelper {
    private static final String DB_NAME = "clienteDB.sqlite";
    private static final int DB_VERSION = 1;
    Cursor cursor;

    SQLiteDatabase db;

    public AppDataBase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {

            db.execSQL(ClienteDataModel.gerarTabela());
            Log.i(MainActivity.LOG_APP, "tabela cliente: " + ClienteDataModel.gerarTabela());

        } catch (SQLException e) {
            Log.e(MainActivity.LOG_APP, "Erro DB cliente:" + e.getMessage());
        }

        try {

            db.execSQL(ClientePFDataModel.gerarTabela());
            Log.i(MainActivity.LOG_APP, "tabela clientePF: " + ClientePFDataModel.gerarTabela());

        } catch (SQLException e) {
            Log.e(MainActivity.LOG_APP, "Erro DB cliente:" + e.getMessage());
        }

        try {

            db.execSQL(ClientePJDataModel.gerarTabela());
            Log.i(MainActivity.LOG_APP, "tabela clientePJ: " + ClientePJDataModel.gerarTabela());

        } catch (SQLException e) {
            Log.e(MainActivity.LOG_APP, "Erro DB cliente:" + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /**
     * Incluir dados no DB
     *
     * @return
     */
    public boolean insert(String tabela, ContentValues dados) {
        boolean sucesso = true;
        try {
            sucesso = db.insert(tabela, null, dados) > 0;
            Log.e(MainActivity.LOG_APP, tabela + " inserido com sucesso ");
        } catch (SQLException e) {
            Log.e(MainActivity.LOG_APP, tabela + " falha ao inserir " + e.getMessage());
        }
        return sucesso;
    }

    /**
     * Deletar dados no DB
     *
     * @return
     */
    public boolean delete(String tabela, int id) {
        boolean sucesso = true;
        try {
            sucesso = db.delete(tabela, "id=?", new String[]{Integer.toString(id)}) > 0;
            Log.e(MainActivity.LOG_APP, tabela + " deletado com sucesso ");
        } catch (SQLException e) {
            Log.e(MainActivity.LOG_APP, tabela + " falha ao deletar " + e.getMessage());
        }
        return sucesso;
    }

    /**
     * Alterar dados no DB
     *
     * @return
     */
    public boolean update(String tabela, ContentValues dados) {
        boolean sucesso = true;
        try {
            int id = dados.getAsInteger("id");
            sucesso = db.update(tabela, dados, "id=?", new String[]{Integer.toString(id)}) > 0;
            Log.e(MainActivity.LOG_APP, tabela + "alterado com sucesso ");
        } catch (SQLException e) {
            Log.e(MainActivity.LOG_APP, tabela + "falha ao alterar " + e.getMessage());
        }
        return sucesso;
    }

    /**
     * Listar dados no DB
     *
     * @return
     */
    public List<Cliente> list(String tabela) {
        List<Cliente> list = new ArrayList<>();
        Cliente cliente;
        String sql = "SELECT * FROM " + tabela;
        try {

            cursor = db.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                do {

                    cliente = new Cliente();

                    cliente.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ClienteDataModel.ID)));
                    cliente.setPrimeiroNome(cursor.getString(cursor.getColumnIndexOrThrow(ClienteDataModel.PRIMEIRO_NOME)));
                    cliente.setSobrenome(cursor.getString(cursor.getColumnIndexOrThrow(ClienteDataModel.SOBRENOME)));
                    cliente.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(ClienteDataModel.EMAIL)));
                    cliente.setSenha(cursor.getString(cursor.getColumnIndexOrThrow(ClienteDataModel.SENHA)));
                    cliente.setPessoaFisica(cursor.getInt(cursor.getColumnIndexOrThrow(ClienteDataModel.PESSOAFISICA)) == 1);

                    list.add(cliente);
                } while (cursor.moveToNext());
                Log.e(MainActivity.LOG_APP, tabela + " lista gerada");
            }

        } catch (SQLException e) {
            Log.e(MainActivity.LOG_APP, "Erro ao listar dados " + tabela);
            Log.e(MainActivity.LOG_APP, "Erro: " + e.getMessage());
        }
        return list;
    }
}

package com.anamuxfeldt.cadastroclientescomdb.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.anamuxfeldt.cadastroclientescomdb.view.MainActivity;

public class AppDataBase extends SQLiteOpenHelper {
    private static final String DB_NAME = "clienteDB.sqlite";
    private static final int DB_VERSION = 1;

    SQLiteDatabase db;

    public AppDataBase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db=getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {

            db.execSQL(ClienteDataModel.gerarTabela());
            Log.i(MainActivity.LOG_APP, "tabela cliente: "+ClienteDataModel.gerarTabela());

        }catch (SQLException e){
            Log.e(MainActivity.LOG_APP, "Erro DB cliente:"+e.getMessage());
        }

        try {

            db.execSQL(ClientePFDataModel.gerarTabela());
            Log.i(MainActivity.LOG_APP, "tabela clientePF: "+ClientePFDataModel.gerarTabela());

        }catch (SQLException e){
            Log.e(MainActivity.LOG_APP, "Erro DB cliente:"+e.getMessage());
        }

        try {

            db.execSQL(ClientePJDataModel.gerarTabela());
            Log.i(MainActivity.LOG_APP, "tabela clientePJ: "+ClientePJDataModel.gerarTabela());

        }catch (SQLException e){
            Log.e(MainActivity.LOG_APP, "Erro DB cliente:"+e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

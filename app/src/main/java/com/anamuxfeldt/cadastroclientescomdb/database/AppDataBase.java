package com.anamuxfeldt.cadastroclientescomdb.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.anamuxfeldt.cadastroclientescomdb.controller.ClienteController;
import com.anamuxfeldt.cadastroclientescomdb.controller.ClientePFController;
import com.anamuxfeldt.cadastroclientescomdb.controller.ClientePJController;
import com.anamuxfeldt.cadastroclientescomdb.model.Cliente;
import com.anamuxfeldt.cadastroclientescomdb.model.ClientePF;
import com.anamuxfeldt.cadastroclientescomdb.model.ClientePJ;
import com.anamuxfeldt.cadastroclientescomdb.view.MainActivity;
import com.anamuxfeldt.cadastroclientescomdb.view.SplashActivity;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

public class AppDataBase extends SQLiteOpenHelper {
    private static final String DB_NAME = "clienteDB.sqlite";
    private static final int DB_VERSION = 1;
    Context context;

    SQLiteDatabase db;
    private static AppDataBase instance;

    public AppDataBase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);


        this.context = context;

        db = getWritableDatabase();
        Log.d(MainActivity.LOG_APP, "AppDataBase: Criando DB");
    }

    public static synchronized AppDataBase getInstance(Context context) {
        if (instance == null) {
            instance = new AppDataBase(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /**
         * Criando as tabelas
         */
        try {

            db.execSQL(ClienteDataModel.gerarTabela());
            Log.d(MainActivity.LOG_APP, "tabela cliente: " + ClienteDataModel.gerarTabela());

        } catch (SQLException e) {
            Log.e(MainActivity.LOG_APP, "Erro DB cliente:" + e.getMessage());
        }

        try {

            db.execSQL(ClientePFDataModel.gerarTabela());
            Log.d(MainActivity.LOG_APP, "tabela clientePF: " + ClientePFDataModel.gerarTabela());

        } catch (SQLException e) {
            Log.e(MainActivity.LOG_APP, "Erro DB cliente:" + e.getMessage());
        }

        try {

            db.execSQL(ClientePJDataModel.gerarTabela());
            Log.d(MainActivity.LOG_APP, "tabela clientePJ: " + ClientePJDataModel.gerarTabela());

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
            Log.i(MainActivity.LOG_APP, tabela + " inserido com sucesso ");

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
    public List<Cliente> listClientes(String tabela) {
        List<Cliente> list = new ArrayList<>();
        Cliente cliente;
        ClientePFController controllerPF;
        ClientePJController clientePJController;

        String sql = "SELECT * FROM " + tabela;
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                do {
                    cliente = new Cliente();
                    controllerPF = new ClientePFController(context.getApplicationContext());
                    clientePJController = new ClientePJController(context.getApplicationContext());


                    cliente.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ClienteDataModel.ID)));
                    cliente.setPrimeiroNome(cursor.getString(cursor.getColumnIndexOrThrow(ClienteDataModel.PRIMEIRO_NOME)));
                    cliente.setSobrenome(cursor.getString(cursor.getColumnIndexOrThrow(ClienteDataModel.SOBRENOME)));
                    cliente.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(ClienteDataModel.EMAIL)));
                    cliente.setSenha(cursor.getString(cursor.getColumnIndexOrThrow(ClienteDataModel.SENHA)));
                    cliente.setPessoaFisica(cursor.getInt(cursor.getColumnIndexOrThrow(ClienteDataModel.PESSOAFISICA)) == 1);

                    cliente.setClientePF(controllerPF.getClientePFByFK(context.getApplicationContext(), cliente.getId()));

                    if (!cliente.isPessoaFisica())
                        cliente.setClientePJ(clientePJController.getClientePJByFK(context.getApplicationContext(),cliente.getClientePF().getId()));



                    Log.d(MainActivity.LOG_APP, "ID do cliente: " + cliente.getId());
                        Log.d(MainActivity.LOG_APP, "É pessoa física? " + cliente.isPessoaFisica());



                    list.add(cliente);
                } while (cursor.moveToNext());
                Log.e(MainActivity.LOG_APP, tabela + " lista gerada");
            }


        } catch (SQLException e) {
            Log.e(MainActivity.LOG_APP, "Erro ao listar dados " + tabela);
            Log.e(MainActivity.LOG_APP, "Erro: " + e.getMessage());
        }finally {
            if(cursor != null){
                cursor.close();
            }
        }
        return list;
    }
    public List<ClientePF> listClientesPF(String tabela) {
        List<ClientePF> list = new ArrayList<>();
        ClientePF clientePF;
        String sql = "SELECT * FROM " + tabela;
        Cursor cursor = null;
        try {

            cursor = db.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                do {

                    clientePF = new ClientePF();

                    clientePF.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ClientePFDataModel.ID)));
                    clientePF.setClienteID(cursor.getInt(cursor.getColumnIndexOrThrow(ClientePFDataModel.FK)));

                    clientePF.setCpf(cursor.getString(cursor.getColumnIndexOrThrow(ClientePFDataModel.CPF)));

                    list.add(clientePF);
                } while (cursor.moveToNext());
                Log.e(MainActivity.LOG_APP, tabela + " lista gerada");
            }

        } catch (SQLException e) {
            Log.e(MainActivity.LOG_APP, "Erro ao listar dados " + tabela);
            Log.e(MainActivity.LOG_APP, "Erro: " + e.getMessage());
        }
        return list;
    }
    public List<ClientePJ> listClientesPJ(String tabela) {
        List<ClientePJ> list = new ArrayList<>();
        ClientePJ clientePJ;
        String sql = "SELECT * FROM " + tabela;
        Cursor cursor = null;
        try {

            cursor = db.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                do {

                    clientePJ = new ClientePJ();

                    clientePJ.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ClientePJDataModel.ID)));
                    clientePJ.setClientePFID(cursor.getInt(cursor.getColumnIndexOrThrow(ClientePJDataModel.FK)));

                    clientePJ.setRazaoSocial(cursor.getString(cursor.getColumnIndexOrThrow(ClientePJDataModel.RAZAO_SOCIAL)));
                    clientePJ.setRazaoSocial(cursor.getString(cursor.getColumnIndexOrThrow(ClientePJDataModel.RAZAO_SOCIAL)));
                    clientePJ.setDataAbertura(cursor.getString(cursor.getColumnIndexOrThrow(ClientePJDataModel.DATA_ABERTURA)));
                    clientePJ.setSimplesNacional(cursor.getInt(cursor.getColumnIndexOrThrow(ClientePJDataModel.SIMPLES_NACIONAL))==1);
                    clientePJ.setMei(cursor.getInt(cursor.getColumnIndexOrThrow(ClientePJDataModel.MEI))==1);

                    list.add(clientePJ);
                } while (cursor.moveToNext());
                Log.e(MainActivity.LOG_APP, tabela + " lista gerada");
            }

        } catch (SQLException e) {
            Log.e(MainActivity.LOG_APP, "Erro ao listar dados " + tabela);
            Log.e(MainActivity.LOG_APP, "Erro: " + e.getMessage());
        }
        return list;
    }
    public int getLastPK (String tabela) {


        //SELECT seq FROM sqlite_sequence WHERE name="cliente"


        String sql = "SELECT seq FROM sqlite_sequence WHERE name= '" + tabela + "'";
        Cursor cursor = null;
        try {

            Log.e(MainActivity.LOG_APP, "SQL Raw: " + sql);
            cursor = db.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                do {

                    return cursor.getInt(cursor.getColumnIndexOrThrow("seq"));

                } while (cursor.moveToNext());
            }

        } catch (SQLException e) {
            Log.e(MainActivity.LOG_APP, "Erro ao recuperando ultimo PK " + tabela);
            Log.e(MainActivity.LOG_APP, "Erro: " + e.getMessage());
        }
        return -1;
    }
    public Cliente getClienteById(String tabela,Cliente obj ){

        Cliente cliente = new Cliente();;
        String sql = "SELECT * FROM "+tabela+" WHERE id = "+obj.getId();
        Cursor cursor = null;
        try{
            cursor=db.rawQuery(sql, null);
            if (cursor.moveToNext()){

                cliente.setPrimeiroNome(cursor.getString(cursor.getColumnIndexOrThrow(ClienteDataModel.PRIMEIRO_NOME)));
                cliente.setSobrenome(cursor.getString(cursor.getColumnIndexOrThrow(ClienteDataModel.SOBRENOME)));
                cliente.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(ClienteDataModel.EMAIL)));
                cliente.setSenha(cursor.getString(cursor.getColumnIndexOrThrow(ClienteDataModel.SENHA)));
                cliente.setPessoaFisica(cursor.getInt(cursor.getColumnIndexOrThrow(ClienteDataModel.PESSOAFISICA))==1);
            }
        }catch (SQLException e){

            Log.e(MainActivity.LOG_APP, "Erro: getClienteById "+obj.getId());
            Log.e(MainActivity.LOG_APP, "Erro: "+e.getMessage());
        }

        return cliente;
    }
    public ClientePF getClientePFByFK(String tabela, int idFK ){

        ClientePF clientePF = new ClientePF();;
        String sql = "SELECT * FROM " +tabela+ " WHERE "+ClientePFDataModel.FK+" = " +idFK;
        Cursor cursor = null;
        try{
            cursor=db.rawQuery(sql, null);
            if (cursor.moveToNext()){

                clientePF.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ClientePFDataModel.ID)));
                clientePF.setClienteID(cursor.getInt(cursor.getColumnIndexOrThrow(ClientePFDataModel.FK)));
                clientePF.setCpf(cursor.getString(cursor.getColumnIndexOrThrow(ClientePFDataModel.CPF)));
                clientePF.setDataNascimento(cursor.getString(cursor.getColumnIndexOrThrow(ClientePFDataModel.DATA_NASCIMENTO)));

            }
        }catch (SQLException e){

            Log.e(MainActivity.LOG_APP, "Erro: getClienteByFK "+idFK);
            Log.e(MainActivity.LOG_APP, "Erro: "+e.getMessage());
        }

        return clientePF;
    }
    public ClientePJ getClientePJByFK(String tabela, int idFK ){

        ClientePJ clientePJ = new ClientePJ();
        String sql = "SELECT * FROM " +tabela+ " WHERE "+ClientePJDataModel.FK+" = " +idFK;
        Cursor cursor = null;
        try{
            cursor=db.rawQuery(sql, null);
            if (cursor.moveToNext()){

                clientePJ.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ClientePJDataModel.ID)));
                clientePJ.setClientePFID(cursor.getInt(cursor.getColumnIndexOrThrow(ClientePJDataModel.FK)));
                clientePJ.setRazaoSocial(cursor.getString(cursor.getColumnIndexOrThrow(ClientePJDataModel.RAZAO_SOCIAL)));
                clientePJ.setCnpj(cursor.getString(cursor.getColumnIndexOrThrow(ClientePJDataModel.CNPJ)));
                clientePJ.setDataAbertura(cursor.getString(cursor.getColumnIndexOrThrow(ClientePJDataModel.DATA_ABERTURA)));
                clientePJ.setSimplesNacional(cursor.getInt(cursor.getColumnIndexOrThrow(ClientePJDataModel.SIMPLES_NACIONAL))==1);
                clientePJ.setMei(cursor.getInt(cursor.getColumnIndexOrThrow(ClientePJDataModel.MEI))==1);

            }
        }catch (SQLException e){

            Log.e(MainActivity.LOG_APP, "Erro: getClientePJByFK "+idFK);
            Log.e(MainActivity.LOG_APP, "Erro: "+e.getMessage());
        }

        return clientePJ;
    }
}

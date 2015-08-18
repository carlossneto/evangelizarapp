package org.comshalom.evangelizar.dao;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.comshalom.evangelizar.model.Cadastro;
import org.comshalom.evangelizar.model.Evangelizador;

public class DBHelper  extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the

    //version number.
    private static final int DATABASE_VERSION = 4;
    // Database Name
    private static final String DATABASE_NAME = "cadastro.db";

    public DBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here

        String CREATE_TABLE_CADASTRO = "CREATE TABLE IF NOT EXISTS  " + Cadastro.TABLE  + "("
                + Cadastro.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Cadastro.KEY_nome + " TEXT, "
                + Cadastro.KEY_bairro + " TEXT, "
                + Cadastro.KEY_facebook + " TEXT, "
                + Cadastro.KEY_idade + " TEXT, "
                + Cadastro.KEY_email + " TEXT, "
                + Cadastro.KEY_local + " TEXT, "
                + Cadastro.KEY_tel + " INTEGER, "
                + Cadastro.KEY_sync +" INTEGER )";

        String CREATE_TABLE_EVANGELIZADOR = "CREATE TABLE IF NOT EXISTS  " + Evangelizador.TABLE  + "("
                + Evangelizador.KEY_ID + " INTEGER PRIMARY KEY,"
                + Evangelizador.KEY_nome + " TEXT, "
                + Evangelizador.KEY_tipo + " INTEGER, "
                + Evangelizador.KEY_telefone + " TEXT, "
                + Evangelizador.KEY_email + " TEXT, "
                + Evangelizador.KEY_evento + " INTEGER )";

        db.execSQL(CREATE_TABLE_CADASTRO);
        db.execSQL(CREATE_TABLE_EVANGELIZADOR);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + Cadastro.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Evangelizador.TABLE);

        // Create tables again
        onCreate(db);

    }

}
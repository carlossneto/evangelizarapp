package org.comshalom.evangelizar.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.comshalom.evangelizar.model.Cadastro;
import org.comshalom.evangelizar.model.Evangelizador;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author matheus
 */
public class EvangelizadorDAO {

    private DBHelper dbHelper;

    public EvangelizadorDAO(Context context) {

        dbHelper = new DBHelper(context);
    }

    public void insert(Evangelizador evangelizador) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Evangelizador.KEY_ID, evangelizador.getId());
        values.put(Evangelizador.KEY_nome, evangelizador.getNome() );
        values.put(Evangelizador.KEY_tipo, evangelizador.getTipo() );
        values.put(Evangelizador.KEY_telefone, evangelizador.getTelefone() );
        values.put(Evangelizador.KEY_email, evangelizador.getEmail() );
        values.put(Evangelizador.KEY_evento, evangelizador.getEvento() );

        // Inserting Row
        db.insert(Evangelizador.TABLE, null, values);

        db.close(); // Closing database connection

    }

    public void update(Evangelizador evangelizador) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Evangelizador.KEY_nome, evangelizador.getNome() );
        values.put(Evangelizador.KEY_tipo, evangelizador.getTipo() );
        values.put(Evangelizador.KEY_telefone, evangelizador.getTelefone() );
        values.put(Evangelizador.KEY_email, evangelizador.getEmail() );
        values.put(Evangelizador.KEY_evento, evangelizador.getEvento());

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Evangelizador.TABLE, values, Evangelizador.KEY_ID + "= ?", new String[]{String.valueOf(evangelizador.getId())});
        db.close(); // Closing database connection
    }

    public Evangelizador getEvangelizadorById(int Id){

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selectQuery =  "SELECT  " +
                Evangelizador.KEY_nome + "," +
                Evangelizador.KEY_tipo + "," +
                Evangelizador.KEY_telefone + "," +
                Evangelizador.KEY_email + "," +
                Evangelizador.KEY_evento +
                " FROM " + Evangelizador.TABLE
                + " WHERE " +
                Evangelizador.KEY_ID + "= ?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount =0;

        Evangelizador evangelizador = null;

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                evangelizador = new Evangelizador();
                evangelizador.setNome(cursor.getString(cursor.getColumnIndex(Evangelizador.KEY_nome)));
                evangelizador.setTipo(cursor.getInt(cursor.getColumnIndex(Evangelizador.KEY_tipo)));
                evangelizador.setTelefone(cursor.getString(cursor.getColumnIndex(Evangelizador.KEY_telefone)));
                evangelizador.setEmail(cursor.getString(cursor.getColumnIndex(Evangelizador.KEY_email)));
                evangelizador.setEvento(cursor.getInt(cursor.getColumnIndex(Evangelizador.KEY_evento)));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return evangelizador;
    }
}
package org.comshalom.evangelizar.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.comshalom.evangelizar.model.Cadastro;

import java.util.ArrayList;
import java.util.HashMap;

public class CadastroDAO {
    private DBHelper dbHelper;

    public CadastroDAO(Context context) {

        dbHelper = new DBHelper(context);
    }

    public int insert(Cadastro cadastro) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Cadastro.KEY_nome, cadastro.getNome() );
        values.put(Cadastro.KEY_endereco, cadastro.getEndereco() );
        values.put(Cadastro.KEY_bairro, cadastro.getBairro() );
        values.put(Cadastro.KEY_facebook, cadastro.getFacebook() );
        values.put(Cadastro.KEY_idade, cadastro.getIdade() );
        values.put(Cadastro.KEY_email, cadastro.getEmail() );
        values.put(Cadastro.KEY_tel, cadastro.getTel() );
        values.put(Cadastro.KEY_local, cadastro.getLocal() );

        System.out.println("valores :" + values);

        /*
        String insert_sql = "INSERT INTO Cadastro (nome,endereco,bairro,nascimento,idade,email,tel) values" +
                                                  "('"+cadastro.nome +"'," +
                                                  "'" + cadastro.endereco +"'," +
                                                  "'" + cadastro.bairro +"'," +
                                                  "'" + cadastro.nascimento +"'," +
                                                        cadastro.idade +"," +
                                                  "'" + cadastro.email +"'," +
                                                        cadastro.tel +")";
                                                  //cadastro.local +")";
        System.out.println(insert_sql);
        db.execSQL(insert_sql);
        */
                // Inserting Row
                long cadastro_Id = db.insert(Cadastro.TABLE, null, values);

                //System.out.println("cadastro_Id: " + cadastro_Id); //apagar
                //System.out.println("nome_cadastro: " + cadastro.nome); //apagar
                //System.out.println(values);


                db.close(); // Closing database connection
        return (int) cadastro_Id;
    }

    public void delete(int cadastro_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Cadastro.TABLE, Cadastro.KEY_ID + "= ?", new String[] { String.valueOf(cadastro_Id) });
        db.close(); // Closing database connection
    }

    public void update(Cadastro cadastro) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Cadastro.KEY_nome, cadastro.getNome() );
        values.put(Cadastro.KEY_endereco, cadastro.getEndereco() );
        values.put(Cadastro.KEY_bairro, cadastro.getBairro() );
        values.put(Cadastro.KEY_facebook, cadastro.getFacebook() );
        values.put(Cadastro.KEY_idade, cadastro.getIdade() );
        values.put(Cadastro.KEY_email, cadastro.getEmail() );
        values.put(Cadastro.KEY_tel, cadastro.getTel() );
        values.put(Cadastro.KEY_local, cadastro.getLocal() );

        System.out.println(values.toString()); //teste

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Cadastro.TABLE, values, Cadastro.KEY_ID + "= ?", new String[] { String.valueOf(cadastro.getCadastro_ID()) });
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>>  getCadastroList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
        Cadastro.KEY_ID + "," +
        Cadastro.KEY_nome + "," +
        Cadastro.KEY_endereco + "," +
        Cadastro.KEY_bairro + "," +
        Cadastro.KEY_facebook + "," +
        Cadastro.KEY_idade + "," +
        Cadastro.KEY_email + "," +
        Cadastro.KEY_local + "," +
        Cadastro.KEY_tel +
        " FROM " + Cadastro.TABLE;

        //selectQuery="SELECT NOME from cadastro";

        System.out.println(selectQuery);


        //Cadastro cadastro = new Cadastro();
        ArrayList<HashMap<String, String>> cadastroList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                System.out.print((cursor.getColumnIndex(Cadastro.KEY_ID)));

                HashMap<String, String> cadastro = new HashMap<String, String>();
                cadastro.put("id", cursor.getString(cursor.getColumnIndex(Cadastro.KEY_ID)));
                cadastro.put("nome", cursor.getString(cursor.getColumnIndex(Cadastro.KEY_nome)));
                cadastroList.add(cadastro);

                //System.out.println("listagem nome : " + cursor.getString(cursor.getColumnIndex(Cadastro.KEY_nome)));
                //System.out.println("listagem id :" + cursor.getString(cursor.getColumnIndex(Cadastro.KEY_ID)));
                //System.out.println("listagem endereco :" + cursor.getString(cursor.getColumnIndex(Cadastro.KEY_endereco)));
                //System.out.println("listagem email : " + cursor.getString(cursor.getColumnIndex(Cadastro.KEY_email)));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return cadastroList;

    }

    public Cadastro getCadastroById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        //System.out.println("Id parametro:"+ Id);
        //db.execSQL("drop table if exists Cadastro");
        //original db.execSQL("CREATE TABLE Cadastro (id  INTEGER PRIMARY KEY AUTOINCREMENT,nome TEXT,endereco TEXT,bairro TEXT, nascimento TEXT,idade INTEGER,email TEXT,tel INTEGER, local TEXT)");

        //db.execSQL("CREATE TABLE Cadastro (id  INTEGER PRIMARY KEY AUTOINCREMENT,nome TEXT,endereco TEXT,bairro TEXT, nascimento TEXT,idade INTEGER,email TEXT,tel INTEGER, local TEXT)");

        //db.execSQL("delete from Cadastro");

        String selectQuery =  "SELECT  " +
                Cadastro.KEY_ID + "," +
                Cadastro.KEY_nome + "," +
                Cadastro.KEY_endereco + "," +
                Cadastro.KEY_bairro + "," +
                Cadastro.KEY_facebook + "," +
                Cadastro.KEY_idade + "," +
                Cadastro.KEY_email + "," +
                Cadastro.KEY_local + "," +
                Cadastro.KEY_tel +
                " FROM " + Cadastro.TABLE
                + " WHERE " +
                Cadastro.KEY_ID + "= ?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount =0;
        Cadastro cadastro = new Cadastro();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                cadastro.setCadastro_ID ( cursor.getInt(cursor.getColumnIndex(Cadastro.KEY_ID)) );
                //cadastro.nome = cursor.getString(cursor.getColumnIndex(Cadastro.KEY_nome));
                //cadastro.email  =cursor.getString(cursor.getColumnIndex(Cadastro.KEY_email));
                //cadastro.idade =cursor.getInt(cursor.getColumnIndex(Cadastro.KEY_idade));

                cadastro.setNome(cursor.getString(cursor.getColumnIndex(Cadastro.KEY_nome)));
                cadastro.setEndereco(cursor.getString(cursor.getColumnIndex(Cadastro.KEY_endereco)));
                cadastro.setBairro  ( cursor.getString(cursor.getColumnIndex(Cadastro.KEY_bairro)) );
                cadastro.setFacebook  ( cursor.getString(cursor.getColumnIndex(Cadastro.KEY_facebook)) );
                cadastro.setIdade  ( cursor.getInt(cursor.getColumnIndex(Cadastro.KEY_idade)) );
                cadastro.setEmail  ( cursor.getString(cursor.getColumnIndex(Cadastro.KEY_email)) );
                cadastro.setTel  ( cursor.getInt(cursor.getColumnIndex(Cadastro.KEY_tel)) );
                cadastro.setLocal  ( cursor.getString(cursor.getColumnIndex(Cadastro.KEY_local)) );

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return cadastro;
    }

}
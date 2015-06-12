package com.example.baldemar.biblioteca;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Baldemar on 27/05/2015.
 */
public class Conexion extends SQLiteOpenHelper{
    //Aqui se creo el constructor

    public Conexion(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
db.execSQL("create table biblioteca (numero_libro integer primary key, nombre_libro text, autor text, editorial text, isbn text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("drop table if exists biblioteca");
        db.execSQL("create table biblioteca (numero_libro integer primary key, nombre_libro text, autor text, editorial text, isbn text)");
    }
}
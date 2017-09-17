package com.example.jimmypi.jimmypi.SQLHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jimmypi.jimmypi.Objetos.Atajo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jimmy on 12/09/2017.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "JimmyPi";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creamos la BD
        String tabla = "CREATE TABLE atajos ( id INTEGER PRIMARY KEY AUTOINCREMENT, atajo TEXT, accion TEXT )";

        // Creamos la tabla
        db.execSQL(tabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS atajos");

        this.onCreate(db);
    }


    public void anadirAtajo(Atajo atajo){

        //Obtenemos una BD con permisos de escritura
        SQLiteDatabase db = this.getWritableDatabase();

        //Creamos los valores a insertar: Columna/valor
        ContentValues registro = new ContentValues();
        registro.put("atajo", atajo.getAtajo());
        registro.put("accion", atajo.getAccion());

        // Insertamos
        db.insert("atajos", null, registro);

        db.close();
    }

    public Atajo getAtajo(String atajo){

        // Obtenemos una BD con permisos de lectura
        SQLiteDatabase db = this.getReadableDatabase();

        // Creamos el cursor
        Cursor cursor = db.rawQuery("select * from atajos where atajo="+atajo+";",null);

        // Movemos el cursor al primer registro
        if (cursor != null)
            cursor.moveToFirst();

        // Construimos el nuevo atajo
        Atajo atajoBuscado = new Atajo();
        atajoBuscado.setAtajo(cursor.getString(0));
        atajoBuscado.setAccion(cursor.getString(1));

        db.close();

        return atajoBuscado;
    }

    public List<Atajo> getAtajos(){
        List<Atajo> atajos = new ArrayList<>();

        String query = "SELECT  * FROM  atajos";

        //Creamos una BD con permisos de escritura
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Atajo atajo = null;

        // Recorremos el cursor
        if (cursor.moveToFirst()) {
            do {
                atajo = new Atajo();
                atajo.setAtajo(cursor.getString(0));
                atajo.setAccion(cursor.getString(1));

                // Anadimos el atajo a la lista
                atajos.add(atajo);
            } while (cursor.moveToNext());
        }

        db.close();

        return atajos;
    }

    public void borrarAtajo(Atajo atajo){

        // Obtenemos una BD con permiso de escritura
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("atajos", "atajo = ?", new String[]{ atajo.getAtajo()});

        db.close();
    }

}

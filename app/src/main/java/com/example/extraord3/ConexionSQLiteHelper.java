package com.example.extraord3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.example.extraord3.utilidades.utilidades;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {



    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE productos (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, precio TEXT, cantidad TEXT, imagen TEXT)";
        db.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase ExtraOrdinarioBD, int versionAntigua, int versionNueva) {

    }
}

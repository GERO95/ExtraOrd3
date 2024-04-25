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
    public void onCreate(SQLiteDatabase ExtraOrdinarioBD) {
        ExtraOrdinarioBD.execSQL(utilidades.CREAR_TABLA_PRODUCTO);


    }

    @Override
    public void onUpgrade(SQLiteDatabase ExtraOrdinarioBD, int versionAntigua, int versionNueva) {

        ExtraOrdinarioBD.execSQL("DROP TABLE IF EXISTS PRODUCTOS");
        onCreate(ExtraOrdinarioBD);

    }
}

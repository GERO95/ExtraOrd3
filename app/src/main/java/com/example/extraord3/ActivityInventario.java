package com.example.extraord3;

import static com.example.extraord3.utilidades.utilidades.CAMPO_CANTIDAD;
import static com.example.extraord3.utilidades.utilidades.CAMPO_NOMBRE;
import static com.example.extraord3.utilidades.utilidades.CAMPO_PRECIO;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.extraord3.utilidades.utilidades;

public class ActivityInventario extends AppCompatActivity {

    EditText campoId, campoNombre, campoPrecio, campoCantidad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);

        campoId= (EditText) findViewById(R.id.campoId);
        campoNombre= (EditText) findViewById(R.id.campoNombre);
        campoPrecio= (EditText) findViewById(R.id.campoPrecio);
        campoCantidad= (EditText) findViewById(R.id.campoCantidad);

    }
    public void ActConsulta(View view){
        Intent ActConsulta = new Intent(this, ConsultaProductos.class);
        startActivity(ActConsulta);
    }
    public void onClick(View view) {

        registrarProductos();


    }

    private void registrarProductos() {

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this, "ExtraOrdinarioBD", null, 1);

        SQLiteDatabase ExtraOrdinarioBD=conn.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put (utilidades.CAMPO_ID,campoId.getText().toString());
        values.put (CAMPO_NOMBRE,campoNombre.getText().toString());
        values.put (utilidades.CAMPO_PRECIO,campoPrecio.getText().toString());
        values.put (utilidades.CAMPO_CANTIDAD,campoCantidad.getText().toString());


        Long idResultante=ExtraOrdinarioBD.insert(utilidades.TABLA_PRODUCTOS,utilidades.CAMPO_ID, values);
        Toast.makeText(getApplicationContext(),"Id Registro"+idResultante,Toast.LENGTH_SHORT).show();
        ExtraOrdinarioBD.close();

    }
}
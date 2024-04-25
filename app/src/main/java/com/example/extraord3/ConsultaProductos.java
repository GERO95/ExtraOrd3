package com.example.extraord3;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.extraord3.utilidades.utilidades;

public class ConsultaProductos extends AppCompatActivity {

    EditText campoId, campoNombre, campoPrecio, campoCantidad;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_productos);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"ExtraOrdinarioBD",null,1);


        campoId= (EditText) findViewById(R.id.campoIdConsulta);
        campoNombre= (EditText) findViewById(R.id.campoNombreConsulta);
        campoPrecio= (EditText) findViewById(R.id.campoPrecioConsulta);
        campoCantidad= (EditText) findViewById(R.id.campoCantidadConsulta);


    }

    public void onClick(View view){

        switch (view.getId()){
            case R.id.btnConsultar: consultar();
                break;

            
            case R.id.btnActualizarConsulta: actualizarProductos();
                break;

            case R.id.btnEliminarConsulta: eliminarProductos();
                break;

        }


    }


    private void eliminarProductos() {
        SQLiteDatabase ExtraOrdinarioBD=conn.getWritableDatabase();
        String[] parametros={campoId.getText().toString()};

        ExtraOrdinarioBD.delete(utilidades.TABLA_PRODUCTOS,utilidades.CAMPO_ID+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Ya se Eliminó el usuario",Toast.LENGTH_LONG).show();
        campoId.setText("");
        limpiar();
        ExtraOrdinarioBD.close();
    }

    private void actualizarProductos() {
        SQLiteDatabase ExtraOrdinarioBD=conn.getWritableDatabase();
        String[] parametros={campoId.getText().toString()};
        ContentValues values=new ContentValues();
        values.put(utilidades.CAMPO_NOMBRE,campoNombre.getText().toString());
        values.put(utilidades.CAMPO_PRECIO,campoPrecio.getText().toString());
        values.put(utilidades.CAMPO_CANTIDAD,campoCantidad.getText().toString());

        ExtraOrdinarioBD.update(utilidades.TABLA_PRODUCTOS,values,utilidades.CAMPO_ID+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Ya se actualizó el usuario",Toast.LENGTH_LONG).show();
        ExtraOrdinarioBD.close();

    }


    private void consultar() {
        SQLiteDatabase db=conn.getReadableDatabase();
        String[] parametros={campoId.getText().toString()};
        String[] campos={utilidades.CAMPO_NOMBRE, utilidades.CAMPO_PRECIO, utilidades.CAMPO_CANTIDAD};

        try {

            Cursor cursor= db.query(utilidades.TABLA_PRODUCTOS,campos,utilidades.CAMPO_ID+"=?",parametros,null,null,null);
            Cursor.moveToFirst();
            campoNombre.setText((cursor.getString(0)));
            campoPrecio.setText((cursor.getString(1)));
            campoCantidad.setText((cursor.getString(2)));
            cursor.close();

        }catch (Exception e){

            Toast.makeText(getApplicationContext(),"Lo que estas buscando no existe", Toast.LENGTH_LONG).show();
            limpiar();

        }




    }

    private void limpiar() {
        campoNombre.setText("");
        campoPrecio.setText("");
        campoCantidad.setText("");

    }


}
package com.example.extraord3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.extraord3.utilidades.utilidades;

import java.util.ArrayList;
import java.util.List;


public class ActivityVentas extends AppCompatActivity {
    Button revisarBD, regresar2;
    TextView mostrarInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventas);
        revisarBD = (Button) findViewById(R.id.btQuery);
        regresar2 = (Button) findViewById(R.id.returnBt2);
        mostrarInfo = (TextView) findViewById(R.id.impRows);

        revisarBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchData();
            }
        });

        regresar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void searchData(){
        ConexionSQLiteHelper ExtraordBD = new ConexionSQLiteHelper(this, "ExtraOrdinarioBD", null, 1);
        SQLiteDatabase baseDatos = ExtraordBD.getReadableDatabase();

        try{
            Cursor cursor = baseDatos.rawQuery("SELECT * FROM productos", null);
            String i="";
            while (cursor.moveToNext()){
                i+="nombre:"+cursor.getString(1)+"\nprecio:"+cursor.getString(2)+"\ncantidad:"+cursor.getString(3)+"\nimagen:"+cursor.getString(4)+"\n \n";
            }

            cursor.close();
            mostrarInfo.setText(i);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Aqui no hay nada... Falla"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
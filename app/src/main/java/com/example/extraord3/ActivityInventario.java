package com.example.extraord3;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityInventario extends AppCompatActivity {
    EditText nombreP, precioP, imgName, cantP;
    Button saveP, actP, elimP, regresar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);
        saveP = (Button) findViewById(R.id.btInsert);
        actP = (Button) findViewById(R.id.btUpdate);
        elimP = (Button) findViewById(R.id.btDelete);
        regresar1 = (Button) findViewById(R.id.returnBt);
        nombreP = (EditText) findViewById(R.id.edtNom);
        precioP = (EditText) findViewById(R.id.edtCost);
        imgName = (EditText) findViewById(R.id.edtImg);
        cantP = (EditText) findViewById(R.id.edtUnit);

        saveP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(ActivityInventario.this);
                final View customlayout = getLayoutInflater().inflate(R.layout.insertpdto, null);
                alert.setCancelable(false);
                EditText nombreP = customlayout.findViewById(R.id.edtNom);
                EditText precioP = customlayout.findViewById(R.id.edtCost);
                EditText cantP = customlayout.findViewById(R.id.edtUnit);
                EditText imgName = customlayout.findViewById(R.id.edtImg);

                alert.setView(customlayout);
                alert.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!nombreP.getText().toString().equals("") && !precioP.getText().toString().equals("")&&!cantP.getText().toString().equals("")&&!imgName.getText().toString().equals(""))
                            insertRow(nombreP.getText().toString(),precioP.getText().toString(),cantP.getText().toString(),imgName.getText().toString());
                        else
                            Toast.makeText(getApplicationContext(), "Campos incompletos! favor de llenar todos los campos", Toast.LENGTH_SHORT).show();
                    }
                });

                alert.setNegativeButton("Descartar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Toast.makeText(getApplicationContext(), "Cambios Descartados", Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });

        elimP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(ActivityInventario.this);
                final View customlayout = getLayoutInflater().inflate(R.layout.codepdto, null);
                alert.setCancelable(false);
                EditText nombreP = customlayout.findViewById(R.id.edtNom);

                alert.setView(customlayout);
                alert.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteRow(nombreP.getText().toString());
                    }
                });
                alert.setNegativeButton("Descartar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Toast.makeText(getApplicationContext(), "Cambios Descartados", Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();

            }
        });

        actP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(ActivityInventario.this);
                final View customlayout = getLayoutInflater().inflate(R.layout.updatepdto, null);
                alert.setCancelable(false);
                EditText precioP = customlayout.findViewById(R.id.edtCost);
                EditText cantP = customlayout.findViewById(R.id.edtUnit);
                EditText nombreP = customlayout.findViewById(R.id.edtNom);

                alert.setView(customlayout);
                alert.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        updateRow(nombreP.getText().toString(),cantP.getText().toString(),precioP.getText().toString());
                    }
                });
                alert.setNegativeButton("Descartar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Toast.makeText(getApplicationContext(), "Cambios Descartados", Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });
        regresar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void updateRow(String names, String unidades, String precios) {
        ConexionSQLiteHelper ExordBD = new ConexionSQLiteHelper(this ,"ExtraOrdinarioBD", null, 1);
        SQLiteDatabase baseDatos= ExordBD.getReadableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("Nombre", names);
        registro.put("Cantidad", unidades);
        registro.put("Precio", precios);
        baseDatos.update("Productos",registro,"Nombre='"+names+"'", null);
        baseDatos.update("Productos",registro,"Cantidad='"+unidades+"'", null);
        baseDatos.update("Productos",registro,"Precio='"+precios+"'", null);
        baseDatos.close();
        Intent intent = new Intent(getApplicationContext(), ActivityInventario.class);
        startActivity(intent);
        cantP.setText("");
        precioP.setText("");
        nombreP.setText("");
    }

    private void deleteRow(String nombres) {
        ConexionSQLiteHelper ExordBD = new ConexionSQLiteHelper(this, "ExtraOrdinarioBD", null, 1);
        SQLiteDatabase baseDatos = ExordBD.getReadableDatabase();
        baseDatos.delete( "Productos", "Nombre='"+nombres+"'", null);
        baseDatos.close();
        Toast.makeText(getApplicationContext(), "Elemento Eliminado", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), ActivityInventario.class);
        startActivity(intent);
        nombreP.setText("");
    }

    private void insertRow(String nombrespd, String preciospd, String cantidadpd, String imagenpd) {
        ConexionSQLiteHelper ExordBD = new ConexionSQLiteHelper(getApplicationContext(), "ExtraOrdinarioBD", null, 1);
        SQLiteDatabase basedatos= ExordBD.getReadableDatabase();
        ContentValues row = new ContentValues();
        row.put("Nombre", nombrespd);
        row.put("precio", preciospd);
        row.put("cantidad", cantidadpd);
        row.put("Imagen", imagenpd);
        basedatos.insert("Productos", null, row);
        basedatos.close();
        Toast.makeText(getApplicationContext(), "Agregado", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), ActivityInventario.class);
        startActivity(intent);
        nombreP.setText("");
        precioP.setText("");
        cantP.setText("");
        imgName.setText("");
    }
}
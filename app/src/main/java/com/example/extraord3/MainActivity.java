package com.example.extraord3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this, "ExtraOrdinarioBD", null, 1);

    }

    public void ActVentas(View view){
        Intent ActVentas = new Intent(this, ActivityVentas.class);
        startActivity(ActVentas);
    }

    public void ActInventario(View view){
        Intent ActInventario = new Intent(this, ActivityInventario.class);
        startActivity(ActInventario);
    }


    public void ActSalir(View view){
        //Intent ActSalir = new Intent(this, ActivityVentas.class);
        finishAffinity();

        //startActivity(ActSalir);
    }
    public void onClick(View view){
        Intent ActInsertar=null;
        switch (view.getId()){
            case R.id.btnInsertar:
                ActInsertar=new Intent(MainActivity.this,ActivityInventario.class);
                break;
        }
        if (ActInsertar!=null){
            startActivity(ActInsertar);
        }
    }


}
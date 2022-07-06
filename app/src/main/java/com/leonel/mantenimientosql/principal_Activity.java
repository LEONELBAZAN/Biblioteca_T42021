package com.leonel.mantenimientosql;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.leonel.mantenimientosql.dao.LibroDAO;
import com.leonel.mantenimientosql.entidades.Libro;

import java.util.ArrayList;
import java.util.List;

public class principal_Activity extends AppCompatActivity {
    RecyclerView rblibros;
    FloatingActionButton btnnuevo;
    LibroDAO libroDAO=new LibroDAO(this);
    List<Libro> listarlibros=new ArrayList<>();
    AdaptadorPersonalizado adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        AsingarRefencias();

        libroDAO.abrirBD();
        mostrarLibros();
    }

    private void mostrarLibros(){
        listarlibros=libroDAO.getLibros();
        adaptador=new AdaptadorPersonalizado( principal_Activity.this,listarlibros);
        rblibros.setAdapter(adaptador);
        rblibros.setLayoutManager(new LinearLayoutManager(principal_Activity.this));


    }

    private  void AsingarRefencias(){
        rblibros=findViewById(R.id.rblibros);
        btnnuevo=findViewById(R.id.btnnuevo);
        btnnuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(principal_Activity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
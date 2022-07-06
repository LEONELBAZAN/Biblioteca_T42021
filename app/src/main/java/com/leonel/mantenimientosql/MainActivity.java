package com.leonel.mantenimientosql;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.leonel.mantenimientosql.dao.LibroDAO;
import com.leonel.mantenimientosql.entidades.Libro;

public class MainActivity extends AppCompatActivity {

    EditText txtnombre, txtautor, txteditorial, txtprecio;
    Button btnregistrar;
    Libro libro;
   boolean registar = true;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        asignarReferecia();
        verificarSiActualizacion();

    }

   private void verificarSiActualizacion() {
        if (getIntent().hasExtra("id")) {
            registar = false;
            id = Integer.parseInt(getIntent().getStringExtra("id"));
            txtnombre.setText(getIntent().getStringExtra("nombre"));
            txtautor.setText(getIntent().getStringExtra("autor"));
            txteditorial.setText(getIntent().getStringExtra("editorial"));
            txtprecio.setText(getIntent().getStringExtra("precio"));


        }

    }


    private void asignarReferecia() {
        txtnombre = findViewById(R.id.txtnombre);
        txtautor = findViewById(R.id.txtautor);
        txteditorial = findViewById(R.id.txteditorial);
        txtprecio = findViewById(R.id.txtprecio);
        btnregistrar = findViewById(R.id.btnregistrar);


        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (capturarDatos()) {
                    LibroDAO libroDAO = new LibroDAO(MainActivity.this);

                    libroDAO.abrirBD();
                    String mensaje;
                    if (registar == true) {
                        mensaje = libroDAO.registrarLibro(libro);
                    } else {
                        mensaje = libroDAO.modedificarLibro(libro);

                    }
                    mostrarMensaj(mensaje);
                }
            }
        });
    }

    private void mostrarMensaj(String mensaje) {
        AlertDialog.Builder ventana = new AlertDialog.Builder(this);
        ventana.setTitle("Mensaje informativo");
        ventana.setMessage(mensaje);
        ventana.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(MainActivity.this, principal_Activity.class);
                startActivity(intent);
            }
        });
        ventana.create().show();
    }


    private boolean capturarDatos() {
        String nombre = txtnombre.getText().toString();
        String autor = txtautor.getText().toString();
        String editorial = txteditorial.getText().toString();
        String precio = txtprecio.getText().toString();
        boolean valida = true;
        if (nombre.equals("")) {
            txtnombre.setError("el nombre es obligatorio");
            valida = false;
        }
        if (autor.equals("")) {
            txtautor.setError("el autor es obligatorio");
            valida = false;
        }
        if (editorial.equals("")) {
            txteditorial.setError("la editorial es obligatorio");
            valida = false;
        }
        if (precio.equals("")) {
            txtprecio.setError("el precio es obligatorio");
            valida = false;
        }
        if (valida) {
            if (registar == true) {
                libro = new Libro(nombre, autor, editorial, Double.parseDouble(precio));
            } else {
                libro = new Libro(id, nombre, autor, editorial, Double.parseDouble(precio));
            }
        }
            return valida;
        }


    }

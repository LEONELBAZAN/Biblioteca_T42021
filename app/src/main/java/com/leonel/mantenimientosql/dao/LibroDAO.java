package com.leonel.mantenimientosql.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.leonel.mantenimientosql.entidades.Libro;
import com.leonel.mantenimientosql.util.Constantes;
import com.leonel.mantenimientosql.util.LibroBD;

import java.util.ArrayList;
import java.util.List;

public class LibroDAO {
    private LibroBD libroBD;
    private SQLiteDatabase db;
    private Context context;


    public LibroDAO(Context context) {
        libroBD = new LibroBD(context);
        this.context = context;
    }

    public void abrirBD() {
        db = libroBD.getReadableDatabase();
    }

    public String registrarLibro(Libro libro) {
        String mensaje = "";
        try {
            ContentValues valores = new ContentValues();
            valores.put("titulo", libro.getNombre());
            valores.put("autor", libro.getAutor());
            valores.put("editorial", libro.getEditorial());
            valores.put("precio", libro.getPrecio());
            long resultado = db.insert(Constantes.Nombre_Tabla, null, valores);
            if (resultado == -1) {
                mensaje = "Error al insertar";
            } else {
                mensaje = "Se registró correctamente";
            }
        } catch (Exception e) {
            Log.d("==>", e.getMessage());
        }
        return mensaje;
    }


    public String modedificarLibro(Libro libro) {
        String mensaje = "";
        try {
            ContentValues valores = new ContentValues();
            valores.put("titulo", libro.getNombre());
            valores.put("autor", libro.getAutor());
            valores.put("editorial", libro.getEditorial());
            valores.put("precio", libro.getPrecio());
            long resultado = db.update(Constantes.Nombre_Tabla, valores, "id=" + libro.getId(), null);
            if (resultado == -1) {
                mensaje = "Error al actualizar";
            } else {
                mensaje = "Se actualizo correctamente";
            }
        } catch (Exception e) {
            Log.d("==>", e.getMessage());
        }
        return mensaje;
    }

    public String eliminarLibro(int id) {
        String mensaje = "";

        try {

            long resultado = db.delete(Constantes.Nombre_Tabla, "id=" + id, null);

            if (resultado == -1) {
                mensaje = "Error al eliminar";
            } else {
                mensaje = "Se elimino  correctamente";
            }

        } catch (Exception e) {
            Log.d("==>", e.getMessage());
        }

        return mensaje;
    }



public List<Libro> getLibros(){
        List<Libro>listaLibros=new ArrayList<>();
        try {
            Cursor c=db.rawQuery("SELECT  * FROM "+Constantes.Nombre_Tabla,null);
            while (c.moveToNext()){
                listaLibros.add(new Libro(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getDouble(4)
                ));

            }

        }catch (Exception e){
            Log.d("==>",e.getMessage());
        }
        return  listaLibros;
    }
}

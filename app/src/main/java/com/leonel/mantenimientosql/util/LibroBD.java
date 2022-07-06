package com.leonel.mantenimientosql.util;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import  android.database.sqlite.SQLiteOpenHelper;

public class LibroBD extends SQLiteOpenHelper {

public  LibroBD(Context context){
    super(context, Constantes.Nombre_BD,null, Constantes.Version);

}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query=""+
                "CREATE TABLE "+ Constantes.Nombre_Tabla +
                "("+
                "id INTEGER  PRIMARY KEY AUTOINCREMENT,"+
                "titulo TEXT NOT NULL,"+
                "autor TEXT NOT NULL,"+
                "editorial TEXT NOT NULL,"+
                "precio NUMERIC NOT NULL"+

     ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }





}

package DTO;

import DataBase.DBHELPER;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class ProductoDTO {

    private Context contexto;
    private DBHELPER db;
    private SQLiteDatabase database;
    public ProductoDTO(Context context){
        contexto=context;
        db = new DBHELPER(contexto);
        database = db.getWritableDatabase();
    }

    public Cursor consulta() {
        String consulta = "SELECT * FROM "+DBHELPER.TABLA_PRODUCTOS;
        Cursor cursor = null;
        try {
            cursor = database.rawQuery(consulta, null);
            Toast.makeText(contexto, "Consulta exitosa", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(contexto, "Consulta no exitosa: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return cursor;
    }




}

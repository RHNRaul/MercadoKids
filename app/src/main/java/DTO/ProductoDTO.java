package DTO;

import DAO.ProductoDAO;
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

    public Cursor Producto(int idproducto){
        //Crear un Metodo ell cual me haga la consulta de un producto especifico haciendo que me regrese el ID unicamente
        String id = String.valueOf(idproducto);
        String consulta = "Select ID from "+DBHELPER.TABLA_PRODUCTOS+" WHERE ID = ?";
        Cursor cursor = null;
        try {
            cursor = database.rawQuery(consulta, new String[] {id});
            Toast.makeText(contexto, "Consulta exitosa", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(contexto, "Consulta no exitosa: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return cursor;

    }


    public Cursor Producto(ProductoDAO producto){
        //Crear un Metodo ell cual me haga la consulta de un producto especifico haciendo que me regrese el ID unicamente
        String nombre = producto.getNombre();
        String consulta = "Select ID from "+DBHELPER.TABLA_PRODUCTOS+" WHERE Nombre = ?";
        Cursor cursor = null;
        try {
            cursor = database.rawQuery(consulta, new String[] {nombre});
            Toast.makeText(contexto, "Consulta exitosa", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(contexto, "Consulta no exitosa: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return cursor;

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

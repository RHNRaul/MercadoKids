package DTO;

import DAO.CarritosDAO;
import DAO.HijoDAO;
import DAO.PadreDAO;
import DAO.ProductoDAO;
import DataBase.DBHELPER;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.io.Serializable;

import static DataBase.DBHELPER.*;


public class CarritosDTO implements Serializable {

    Context contexto;

    private DBHELPER db;
    private SQLiteDatabase database;
    public CarritosDTO(Context context){
        contexto=context;
        db = new DBHELPER(contexto);
        ChecarBase();
    }

    private void ChecarBase(){
        database = db.getWritableDatabase();
        if(database!=null){
           // Toast.makeText(contexto,"Se Creo la BD",Toast.LENGTH_LONG).show();
        }
        else{
            //Toast.makeText(contexto,"No se creo la BD",Toast.LENGTH_LONG).show();

        }
    }




    public void IngresarProducto(HijoDAO hijo, ProductoDAO producto){
      //Toast.makeText(contexto,"mi valor es: "+hijo.getCorreo(),Toast.LENGTH_LONG).show();
      //Toast.makeText(contexto,"mi valor es: "+hijo.getCodigoPateron(),Toast.LENGTH_LONG).show();
        Cursor getcodigohijo = consultarCarrito(hijo);
        ProductoDTO productos = new ProductoDTO(contexto);
        int idCarrito = 0; // Variable para almacenar el ID como entero

        if (getcodigohijo != null && getcodigohijo.moveToFirst()) {
            idCarrito = getcodigohijo.getInt(0); // Obtener el valor entero del primer campo (columna 0) del cursor
            Toast.makeText(contexto, "Consulta codigo carrito id: " + idCarrito, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(contexto, "Consulta no exitosa o sin resultados", Toast.LENGTH_LONG).show();
        }

        Cursor getcodigoproducto = productos.Producto(producto);
        int idProducto = 0;

        if (getcodigoproducto != null && getcodigoproducto.moveToFirst()) {
            idProducto = getcodigoproducto.getInt(0); // Obtener el valor entero del primer campo (columna 0) del cursor
            Toast.makeText(contexto, "Consulta codigo producto id: " + idProducto, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(contexto, "Consulta no exitosa o sin resultados", Toast.LENGTH_LONG).show();
        }

        Insertar(idCarrito,idProducto);


    }


    public void Ingresar(HijoDAO hijo,ProductoDAO producto){
        String nombre = producto.getNombre();
        int precio = producto.getPrecio();
        String descripcion = producto.getDescripcion();
        String codigoPaternal = hijo.getCodigoPateron();

        insertarProducto(nombre,precio,descripcion,codigoPaternal);
        //Toast.makeText(contexto,"Ingresado con Exito");
    }



    public void insertarProducto(String nombre, int precio, String descripcion, String codigoPaternal) {
        try {
            ContentValues values = new ContentValues();
            values.put("Nombre", nombre);
            values.put("Precio", precio);
            values.put("Descripcion", descripcion);
            values.put("codigopaternal", codigoPaternal);
            database.insert(TABLA_LISTADO, null, values);
            Toast.makeText(contexto,"Registro Exitoso",Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            // Capturar cualquier excepción y mostrar un mensaje de error
            e.printStackTrace();
            Toast.makeText(contexto, "Error al insertar producto: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor consultarProductos(String codigoPaternal) {
        String codigo = codigoPaternal;
        database = db.getWritableDatabase();
        try {
            String query = "SELECT * FROM " + TABLA_LISTADO + " WHERE codigopaternal = ?";
            return database.rawQuery(query, new String[]{codigo});
        } catch (Exception e) {
            // Capturar cualquier excepción y mostrar un mensaje de error
            e.printStackTrace();
            Toast.makeText(contexto, "Error al consultar productos: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }
    }




    public void Insertar(int idcarritohijo, int idproducto) {
        ContentValues valoresCarrito = new ContentValues();
        valoresCarrito.put("producto", idproducto);
        valoresCarrito.put("carrito", idcarritohijo);
        database = db.getWritableDatabase();

        try {
            // Insertar el registro en la tabla hijo
            long resultado = database.insert(TABLA_PROLIST, null, valoresCarrito);
            if (resultado != -1) {
                Toast.makeText(contexto, "Añadido al Carrito", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(contexto, "Fallo al Añadir", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(contexto, "Error al insertar en la base de datos: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    public Cursor consultarCarroProductos(int idcarrito){
        //Toast.makeText(contexto,"codigo: "+codigo,Toast.LENGTH_LONG).show();
        //  String consulta = "SELECT * FROM " + TABLA_CARRITO + " WHERE codigopaternal = ?";

        String id = String.valueOf(idcarrito);

        Cursor cursor = null;

        String consulta = "SELECT producto FROM "+TABLA_PROLIST+" WHERE carrito = ?";

        // Ejecutar la consulta con los argumentos correspondientes

        try {
            cursor = database.rawQuery(consulta, new String[] {id});
            Toast.makeText(contexto, "Consulta exitosa,Carrito ",Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(contexto, "Consulta no exitosa: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return cursor;

    }


    public Cursor consultarCarrito(PadreDAO hijo){

        String codigo= hijo.getCodigoarental();
        //Toast.makeText(contexto,"codigo: "+codigo,Toast.LENGTH_LONG).show();
        //  String consulta = "SELECT * FROM " + TABLA_CARRITO + " WHERE codigopaternal = ?";
        Cursor cursor = null;

        String consulta = "SELECT ID FROM "+TABLA_CARRITO+" WHERE codigopaternal = ?";

        // Ejecutar la consulta con los argumentos correspondientes

        try {
            cursor = database.rawQuery(consulta, new String[] {codigo});
            Toast.makeText(contexto, "Consulta exitosa,Carrito ",Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(contexto, "Consulta no exitosa: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return cursor;

    }



    public Cursor consultarCarrito(HijoDAO hijo){

        String codigo= hijo.getCodigoPateron();
        //Toast.makeText(contexto,"codigo: "+codigo,Toast.LENGTH_LONG).show();
      //  String consulta = "SELECT * FROM " + TABLA_CARRITO + " WHERE codigopaternal = ?";
        Cursor cursor = null;

        String consulta = "SELECT ID FROM "+TABLA_CARRITO+" WHERE codigopaternal = ?";

        // Ejecutar la consulta con los argumentos correspondientes

        try {
            cursor = database.rawQuery(consulta, new String[] {codigo});
            Toast.makeText(contexto, "Consulta exitosa,Carrito ",Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(contexto, "Consulta no exitosa: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return cursor;

    }


    public Cursor consultarCarrito(CarritosDAO carrito){
        String codigo= carrito.getCodigoparental();
        String consulta = "SELECT * FROM carrito";
        Cursor cursor = null;
        try {
            cursor = database.rawQuery(consulta, null);
            Toast.makeText(contexto, "Consulta exitosa", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(contexto, "Consulta no exitosa: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return cursor;

    }

  /*  public void CrearCarrito(CarritosDAO carrito){
        ContentValues valorescarrito = new ContentValues();
        String codigo = carrito.getCodigoparental();
        valorescarrito.put("ID",1);
        valorescarrito.put("codigopaternal",codigo);
        // Insertar el registro en la tabla hijo
        long resultado = database.insert(TABLA_CARRITO,null,valorescarrito);

        if (resultado == -1) {
        Toast.makeText(contexto,"Carrito Creado con Exito",Toast.LENGTH_LONG).show();
        } else {
        Toast.makeText(contexto,"Carrito No Creado Error",Toast.LENGTH_LONG).show();
        }
    } */

    public void CrearCarrito(CarritosDAO carrito) {
        ContentValues valorescarrito = new ContentValues();
        String codigo = carrito.getCodigoparental();
        valorescarrito.put("ID", 1);
        valorescarrito.put("codigopaternal", codigo);

        try {
            // Insertar el registro en la tabla carrito
            long resultado = database.insert(TABLA_CARRITO, null, valorescarrito);

            if (resultado != -1) {
                Toast.makeText(contexto, "Carrito Creado con Éxito", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(contexto, "Carrito No Creado. Ocurrió un Error", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(contexto, "Error al crear el carrito: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }



}




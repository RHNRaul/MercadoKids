package DTO;

import DAO.HijoDAO;
import DataBase.DBHELPER;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;

import static DataBase.DBHELPER.TABLA_HIJOS;

public class HijoDTO implements Serializable {

    Context contexto;

    private DBHELPER db;
    private SQLiteDatabase database;
    public HijoDTO(Context context){
        contexto=context;
        db = new DBHELPER(contexto);
        ChecarBase();
    }


    private void ChecarBase(){
        database = db.getWritableDatabase();
        if(database!=null){
            Toast.makeText(contexto,"Se Creo la BD",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(contexto,"No se creo la BD",Toast.LENGTH_LONG).show();

        }
    }

   public int registrarse(HijoDAO hijo){

       ContentValues valoresHijo = new ContentValues();
       valoresHijo.put("correo", hijo.getCorreo());
       valoresHijo.put("contrasena", hijo.getPass());
       valoresHijo.put("codigopaternal", hijo.getCodigoPateron());

       // Insertar el registro en la tabla hijo
       long resultado = database.insert(TABLA_HIJOS, null, valoresHijo);
       if (resultado == -1) {
           return 1; // Error al insertar el registro
       } else {
           return 0; // Registro insertado exitosamente
       }
   }

    public Cursor iniciarsesion(HijoDAO hijo) {

        String correo = hijo.getCorreo();
        String contrasena = hijo.getPass();

        // Consulta SQL para obtener todos los datos de la fila que coincida con el correo y contraseña proporcionados
        String consulta = "SELECT * FROM " + TABLA_HIJOS + " WHERE correo = ? AND contrasena = ?";

        // Ejecutar la consulta con los argumentos correspondientes
        Cursor cursor = database.rawQuery(consulta, new String[] {correo, contrasena});

        if (cursor.moveToFirst()) {
            Toast.makeText(contexto, "Se encontró", Toast.LENGTH_LONG).show();
            return cursor;
        } else {
            Toast.makeText(contexto, "Valor no encontrado", Toast.LENGTH_LONG).show();
            return cursor;
        }
    }





}

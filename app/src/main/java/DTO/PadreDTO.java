package DTO;

import DAO.PadreDAO;
import DataBase.DBHELPER;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import app.babyfeed.mercadokids.RegistroPadre;

import java.util.Random;

public class PadreDTO {
    Context contexto;

    private DBHELPER db;
    private SQLiteDatabase database;
    public PadreDTO(Context context){
        contexto=context;
        db = new DBHELPER(contexto);
    }

    private String generarCadenaAleatoria(int longitud) {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder cadenaAleatoria = new StringBuilder();
        Random rnd = new Random();
        while (cadenaAleatoria.length() < longitud) {
            int index = (int) (rnd.nextFloat() * caracteres.length());
            cadenaAleatoria.append(caracteres.charAt(index));
        }
        return cadenaAleatoria.toString();
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



    public int registro(PadreDAO papa){

        ChecarBase();
        ContentValues valoresPadre = new ContentValues();
        valoresPadre.put("correo", papa.getCorreo());
        valoresPadre.put("contrasena", papa.getPass());
        valoresPadre.put("nombre", papa.getNom());
        valoresPadre.put("apellidop", papa.getApellidop());
        valoresPadre.put("apellidom", papa.getApellidom());
        valoresPadre.put("codigopaternal", generarCadenaAleatoria(5));
        if(database.insert("padre", null, valoresPadre) != -1){

            return 0;
        }
        else{

            return 1;
        }

    }

    public Cursor  iniciarsesion(PadreDAO papa){
        ChecarBase();
        String correo = papa.getCorreo();
        String contrasena = papa.getPass();
        // Consulta SQL para obtener todos los datos de la fila que coincida con el correo y contraseña proporcionados
        String consulta = "SELECT * FROM padre WHERE correo = ? AND contrasena = ?";

        // Ejecutar la consulta con los argumentos correspondientes
        Cursor cursor = database.rawQuery(consulta, new String[] {correo, contrasena});

        if(cursor.moveToFirst()){
            Toast.makeText(contexto,"Se encontro",Toast.LENGTH_LONG).show();
            return cursor;
        }
        else {
            Toast.makeText(contexto,"Valor no Encontrado",Toast.LENGTH_LONG).show();
            return cursor;
        }
    }






}

package DataBase;
/* Administrador de la Base de Datos
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBHELPER  extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "MercadoKids.db";

    public static final String TABLA_PADRES = "padre";

    public static final String TABLA_HIJOS = "hijo";

    public static final String TABLA_PRODUCTOS = "producto";

    public static final String TABLA_CARRITO = "carrito";
    
    public static final String TABLA_PROLIST = "listaproductos";
   public DBHELPER(Context context){
       super(context,DATABASE_NOMBRE,null,DATABASE_VERSION);

   }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

       String crearbdcarrito = "CREATE TABLE IF NOT EXISTS "+TABLA_CARRITO + "(" +
               "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
               "codigopaternal TEXT UNIQUE NOT NULL)";
       
       String crearbdcarripro = "CREATE TABLE IF NOT EXISTS "+TABLA_PROLIST+ "(" +
               "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
               "producto_id INTEGER NOT NULL," +
               "carrito_id INTEGER NOT NULL)";

       String crearbdproductos = "CREATE TABLE IF NOT EXISTS "+TABLA_PRODUCTOS+"(" +
               "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
               "Nombre TEXT UNIQUE NOT NULL, " +
               "Precio INTEGER NOT NULL, " +
               "Descripcion TEXT NOT NULL )";

       String crearbdpadre = "CREATE TABLE IF NOT EXISTS "+TABLA_PADRES+"(\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    correo TEXT NOT NULL,\n" +
                "    contrasena TEXT NOT NULL,\n" +
                "    nombre TEXT NOT NULL,\n" +
                "    apellidop TEXT NOT NULL,\n" +
                "    apellidom TEXT NOT NULL,\n" +
                "    codigopaternal TEXT NOT NULL\n" +
                ")";

        String crearbdhijo = "CREATE TABLE IF NOT EXISTS "+TABLA_HIJOS+"(\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    correo TEXT NOT NULL,\n" +
                "    contrasena TEXT NOT NULL,\n" +
                "    codigopaternal TEXT NOT NULL\n" +
                ")";






       sqLiteDatabase.execSQL(crearbdpadre);
       sqLiteDatabase.execSQL(crearbdhijo);
      sqLiteDatabase.execSQL(crearbdproductos);
        sqLiteDatabase.execSQL(crearbdcarrito);
       sqLiteDatabase.execSQL(crearbdcarripro);


        ContentValues productos = new ContentValues();
        productos.put("Nombre","Mustang 1973");
        productos.put("Precio",52);
        productos.put("Descripcion","Carro HotWheels modelo Mustang 1973 Categoria C21");
        sqLiteDatabase.insert(TABLA_PRODUCTOS,null,productos);


        productos.put("Nombre","Lamborgini Cadillac 1973");
        productos.put("Precio",52);
        productos.put("Descripcion","Carro HotWheels modelo Lamborgini 1973 Categoria C24");
        sqLiteDatabase.insert(TABLA_PRODUCTOS,null,productos);

        productos.put("Nombre","Nissan Skyline GTR R34");
        productos.put("Precio",52);
        productos.put("Descripcion","Carro HotWheels modelo Nissan Skyline GTR R34 Categoria D25");
        sqLiteDatabase.insert(TABLA_PRODUCTOS,null,productos);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

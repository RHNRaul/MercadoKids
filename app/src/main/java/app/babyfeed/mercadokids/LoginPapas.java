package app.babyfeed.mercadokids;

import DAO.PadreDAO;
import DTO.PadreDTO;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class LoginPapas extends AppCompatActivity {
    private String nom,contra;
    private EditText et1,et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_papas);
        et1 = findViewById(R.id.CorreoLogi);
        et2 = findViewById(R.id.ContraLogi);
    }
    private void limpiar(){
        et1.setText("");
        et2.setText("");
    }
    private int Revisar(String nom,String contra){
        if(nom.isEmpty()||contra.isEmpty()){
            limpiar();
            return 1;
        }
        return 0;
    }
    private int ObtenerValores(){
        nom = et1.getText().toString();
        contra = et2.getText().toString();
        if(Revisar(nom,contra) == 1){
            return 1;
        }
        return 0;
    }


    public void IniciarSesion(View view){
        if(ObtenerValores() == 0){
            PadreDAO papa = new PadreDAO();
            PadreDAO padre = new PadreDAO();
            papa.setCorreo(nom);
            papa.setPass(contra);
            PadreDTO funcion = new PadreDTO(this);
           try(Cursor temp = funcion.iniciarsesion(papa)) {
            if(temp.moveToFirst()){
                padre.setNom(temp.getString(temp.getColumnIndex("nombre")));
                padre.setApellidop(temp.getString(temp.getColumnIndex("apellidop")));
                padre.setApellidom(temp.getString(temp.getColumnIndex("apellidom")));
                padre.setCodigoarental(temp.getString(temp.getColumnIndex("codigopaternal")));
                Intent ventana = new Intent(this, Perfil.class);
                ventana.putExtra("padre",padre);
                startActivity(ventana);
                Toast.makeText(this,"Sesion iniciada",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this,"Usuario no Existe",Toast.LENGTH_LONG).show();
            }
           } catch(Exception e) {
               Toast.makeText(this,"Error al Traer los Datos",Toast.LENGTH_LONG).show();
            }






        }

    }
    public void CrearRegistro(View view){
        Intent VentanaRegistro = new Intent(this, RegistroPadre.class);
        startActivity(VentanaRegistro);
    }



}
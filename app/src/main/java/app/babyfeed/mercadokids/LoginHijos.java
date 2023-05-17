package app.babyfeed.mercadokids;

import DAO.HijoDAO;
import DAO.PadreDAO;
import DTO.HijoDTO;
import DTO.PadreDTO;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class LoginHijos extends AppCompatActivity {
    private String nom,contra;
    private EditText et1,et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_hijos);
        et1 = findViewById(R.id.CorreoHijoLogin);
        et2 = findViewById(R.id.ContraHijoLogin);
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
            HijoDAO child = new HijoDAO();
            HijoDTO funcion = new HijoDTO(this);

            child.setCorreo(nom);
            child.setPass(contra);

            try(Cursor temp = funcion.iniciarsesion(child)) {
                if(temp.moveToFirst()){

                    Toast.makeText(this,"Sesion iniciada",Toast.LENGTH_LONG).show();
                    Intent iniciar = new Intent(this, PrimerLista.class);
                    startActivity(iniciar);
                }
                else {
                    Toast.makeText(this,"Usuario no Existe",Toast.LENGTH_LONG).show();
                }
            } catch(Exception e) {
                Toast.makeText(this,"Error al Traer los Datos",Toast.LENGTH_LONG).show();
            }






        }

    }







}
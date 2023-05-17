package app.babyfeed.mercadokids;

import DAO.PadreDAO;
import DTO.PadreDTO;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class RegistroPadre extends AppCompatActivity {


    String Correo;
    String Contra;
    String contrac;
    String nombre;
    String apellidop;
    String apellidom;
    EditText et1,et2,et3,et4,et5,et6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_padre);

        et1 = findViewById(R.id.Correo);
        et2 = findViewById(R.id.Contra);
        et3 = findViewById(R.id.ContraConfirmar);
        et4 = findViewById(R.id.Nombre);
        et5 = findViewById(R.id.Apellidop);
        et6 = findViewById(R.id.Apellidom);


    }

    private void limpiar(){
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        et5.setText("");
        et6.setText("");
    }


    private int VerificarVacio(String correo, String contra, String contrac, String nombre, String apellidop, String apellidom){

        if(correo.isEmpty()||contra.isEmpty()||contrac.isEmpty()||nombre.isEmpty()||apellidop.isEmpty()||apellidom.isEmpty()){
            limpiar();
            Toast.makeText(this,"Campos Vacios",Toast.LENGTH_LONG).show();
            return 1;
        }

        return 0;
    }

    private int ObtenerDatos(){

        Correo = et1.getText().toString();
        Contra = et2.getText().toString();
        contrac = et3.getText().toString();
        nombre = et4.getText().toString();
        apellidop = et5.getText().toString();
        apellidom = et6.getText().toString();

       if( VerificarVacio(Correo,Contra,contrac,nombre,apellidop,apellidom) == 1){
           return 1;
       }



        return 0;
    }

    private int VerificarContra(String contra, String contraConfi){
        if(!contra.equals(contraConfi)){
            Toast.makeText(this,"No coinciden las contraseñas",Toast.LENGTH_LONG).show();
            limpiar();
            return 1;
        }
        return 0;
    }

    public void Registrar(View view){
    if(ObtenerDatos() == 0){
        if(VerificarContra(Contra,contrac) == 0){
            PadreDAO papa = new PadreDAO();
            papa.setCorreo(Correo);
            papa.setPass(Contra);
            papa.setNom(nombre);
            papa.setApellidop(apellidop);
            papa.setApellidom(apellidom);
            PadreDTO funcional = new PadreDTO(this);
            if(funcional.registro(papa) == 0){
                Toast.makeText(this,"Registro Exitoso",Toast.LENGTH_LONG).show();
                finish();
            }
            else {
                Toast.makeText(this,"Algo Salio Mal",Toast.LENGTH_LONG).show();
            }


        }
    }
    }


}
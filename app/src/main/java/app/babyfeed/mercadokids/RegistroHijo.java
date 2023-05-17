package app.babyfeed.mercadokids;

import DAO.HijoDAO;
import DTO.HijoDTO;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.Serializable;

public class RegistroHijo extends AppCompatActivity implements Serializable {

    String correo,pass1,pass2,codi;
    private EditText et1,et2,et3,et4;
    private String codigo;

    private HijoDAO hijo = new HijoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_hijo);
        et1 = findViewById(R.id.CorreoHijo);
        et2 = findViewById(R.id.ContraHijo);
        et3 = findViewById(R.id.ContraSeguraHijo);
        et4 = findViewById(R.id.Codigo);
        codigo = (String) getIntent().getExtras().getSerializable("codigo");
        et4.setText(codigo);
    }


    private void limpiar(){
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText(codigo);

    }


    private int VerificarVacio(String correo,String pass,String pass2,String codigo){

        if(correo.isEmpty()||pass.isEmpty()||pass2.isEmpty()||codigo.isEmpty()){
            limpiar();
            Toast.makeText(this,"Campos Vacios",Toast.LENGTH_LONG).show();
            et4.setText(codigo);
            return 1;
        }

        return 0;
    }


    private int ObtenerDatos(){

        correo = et1.getText().toString();
        pass1 = et2.getText().toString();
        pass2 = et3.getText().toString();
        codigo = et4.getText().toString();


        if( VerificarVacio(correo,pass1,pass2,codigo) == 1){
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
            if(VerificarContra(pass1,pass2)==0){
                hijo.setCorreo(correo);
                hijo.setPass(pass1);
                hijo.setCodigoPateron(codigo);
                HijoDTO DBHIJO = new HijoDTO(this);
                if(DBHIJO.registrarse(hijo) == 0){
                    Toast.makeText(this,"Registro Exitoso",Toast.LENGTH_LONG).show();
                    finish();
                }
                else {
                    Toast.makeText(this,"Ocurrio un error al Registras",Toast.LENGTH_LONG).show();
                }


            }


        }



    }




}
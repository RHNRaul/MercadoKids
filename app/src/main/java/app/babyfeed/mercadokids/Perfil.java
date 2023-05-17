package app.babyfeed.mercadokids;

import DAO.PadreDAO;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.Serializable;

public class Perfil extends AppCompatActivity implements Serializable {
private TextView et1;
private PadreDAO padre;

    // Obtener el objeto PadreDAO del intent
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        et1 = findViewById(R.id.NombrePerfil);
        padre = (PadreDAO) getIntent().getExtras().getSerializable("padre");
        et1.setText("Nombre "+padre.getNom()+" "+padre.getApellidom()+" "+padre.getApellidop()+"\n Codigo Paternal: "+padre.getCodigoarental());

    }
   public void CrearHijo(View view){
        Intent ventana  = new Intent(this, RegistroHijo.class);
        ventana.putExtra("codigo",padre.getCodigoarental());
        startActivity(ventana);
   }


   public void CerrarSesion(View view){
        finish();
   }


}
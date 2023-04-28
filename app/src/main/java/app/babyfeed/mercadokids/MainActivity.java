package app.babyfeed.mercadokids;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void RegistroPadre(View view){
        Intent pestana = new Intent(this, LoginPapas.class);
        startActivity(pestana);
    }

    public void RegistrarHijo(View view){
        Intent pestana = new Intent(this, LoginHijos.class);
        startActivity(pestana);
    }


}
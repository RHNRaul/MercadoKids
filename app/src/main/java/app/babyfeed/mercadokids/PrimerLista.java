package app.babyfeed.mercadokids;

import DAO.ProductoDAO;
import android.content.Intent;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

public class PrimerLista extends AppCompatActivity {
RecyclerView RV2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primer_lista);

       init();
    }

public void init(){
      tarjetas tarjeta = new tarjetas(this,new tarjetas.OnItemClickListener() {
          @Override
          public void onItemClick(ProductoDAO pro) {
           Ventanita(pro);
          }
      });
     RecyclerView rv1 = findViewById(R.id.RV1);
      rv1.setLayoutManager(new LinearLayoutManager(this));
      rv1.setAdapter(tarjeta);
}
void Ventanita(ProductoDAO pro){
        Intent ventana = new Intent(this, Descripcion.class);
        ventana.putExtra("Producto",pro);
        startActivity(ventana);
    }

}
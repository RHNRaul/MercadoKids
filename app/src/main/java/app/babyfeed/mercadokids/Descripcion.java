package app.babyfeed.mercadokids;

import DAO.ProductoDAO;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Descripcion extends AppCompatActivity {
ImageView iv1;
TextView et1,et2,et3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion);
        ProductoDAO producto = (ProductoDAO) getIntent().getSerializableExtra("Producto");
        iv1 = findViewById(R.id.EV1);
        et1 = findViewById(R.id.ET1);
        et2 = findViewById(R.id.ET2);
        et3 = findViewById(R.id.ET3);
        init(producto);

    }
void init(ProductoDAO pro){
     int imagen = pro.getImagen();
     String nombre = pro.getNombre();
     String descrip = pro.getDescripcion();
     int Precio = pro.getPrecio();

     iv1.setImageResource(imagen);
     et1.setText(nombre);
     et2.setText(descrip);
     et3.setText(Precio+" MXN");

}

}

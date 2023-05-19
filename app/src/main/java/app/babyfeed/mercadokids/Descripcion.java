package app.babyfeed.mercadokids;

import DAO.HijoDAO;
import DAO.ProductoDAO;
import DTO.CarritosDTO;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Descripcion extends AppCompatActivity {
ImageView iv1;
TextView et1,et2,et3;
ProductoDAO producto = null;
HijoDAO hijo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion);
         producto = (ProductoDAO) getIntent().getSerializableExtra("Producto");
         hijo = (HijoDAO) getIntent().getSerializableExtra("Hijo");
        iv1 = findViewById(R.id.EV1);
        et1 = findViewById(R.id.ET1);
        et2 = findViewById(R.id.ET2);
        et3 = findViewById(R.id.ET3);
        init(producto);
      //  Toast.makeText(this,"Usuario: "+hijo.getCorreo(),Toast.LENGTH_LONG).show();
       // Toast.makeText(this,"Codigo Paternal; "+hijo.getCodigoPateron(),Toast.LENGTH_LONG).show();

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

public void agregarProducto(View view){
        CarritosDTO carrito = new CarritosDTO(this);
        //carrito.IngresarProducto(hijo,producto);
        carrito.Ingresar(hijo,producto);
}


}

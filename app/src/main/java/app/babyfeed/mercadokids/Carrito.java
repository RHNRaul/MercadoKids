package app.babyfeed.mercadokids;

import DAO.PadreDAO;
import DAO.ProductoDAO;
import android.content.Intent;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Carrito extends AppCompatActivity {

    PadreDAO papa = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        papa = (PadreDAO) getIntent().getExtras().getSerializable("papa");
        init();
    }

    public void init(){
        Toast.makeText(this,"Antes de empezar "+papa.getCodigoarental(),Toast.LENGTH_LONG);
        carro c = new carro(papa,this,new carro.OnItemClickListener() {@Override
        public void onItemClick(ProductoDAO pro) { Ventanita(pro);
        }
        });
        /*carro c = new carro(this,new carro.OnItemClickListener() {@Override
            public void onItemClick(ProductoDAO pro) { Ventanita(pro);
            }
        }); */
        //c.setPadre(papa);
        RecyclerView rv1 = findViewById(R.id.RV2);
        rv1.setLayoutManager(new LinearLayoutManager(this));
        //Toast.makeText(this,"Codigo paternal "+papa.getCodigoarental(),Toast.LENGTH_LONG).show();
        rv1.setAdapter(c);
    }



    void Ventanita(ProductoDAO pro){
        Intent ventana = new Intent(this, Descripcion.class);
        ventana.putExtra("Producto",pro);
       //ventana.putExtra("Hijo",hijo);
        startActivity(ventana);
    }



}